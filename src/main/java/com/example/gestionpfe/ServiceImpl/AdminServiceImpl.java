package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.AdminDto;
import com.example.gestionpfe.Dto.DomaineDto;
import com.example.gestionpfe.Entities.Administrateur;
import com.example.gestionpfe.Entities.Domaine;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.AdminRepository;

import com.example.gestionpfe.Security.Administrateur.AdminPrincipal;
import com.example.gestionpfe.Services.AdminService;
import com.example.gestionpfe.Shared.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(InitialUsersSetup.class);

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    Utils util;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AdminDto addAdmin(AdminDto adminDto) {

        Administrateur checkAdmin = adminRepository.findByEmail(adminDto.getEmail());

        if(checkAdmin!=null) throw new RuntimeException("Admin deja exist !!!");
        Administrateur adminEntity = new Administrateur();
        adminEntity = modelMapper.map(adminDto, Administrateur.class);
        logger.info("AdminEntity mapping: "+adminEntity);

        adminEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(adminDto.getPassword()));
        adminEntity.setIdAdmin(util.generateUserId(32));


        Administrateur newAdmin = adminRepository.save(adminEntity);

        AdminDto newAdminDto = new AdminDto();
        newAdminDto = modelMapper.map(newAdmin, AdminDto.class);
        logger.info("AdminDto mapping: "+newAdminDto);

        return newAdminDto;
    }


    @Override
    public AdminDto getAdmin(String email) {
        Administrateur adminEntity =  adminRepository.findByEmail(email);
        if(adminEntity==null)throw new UsernameNotFoundException(email);

        AdminDto adminDto = new AdminDto();
        adminDto = modelMapper.map(adminEntity, AdminDto.class);
        logger.info("AdminDto mapping: "+adminDto);
        return adminDto;
    }

    @Override
    public AdminDto getAdminByIdAdmin(String id) {
        Administrateur adminEntity =  adminRepository.findByIdAdmin(id);

        if(adminEntity == null)throw new UsernameNotFoundException(id);

        AdminDto adminDto = new AdminDto();
        adminDto = modelMapper.map(adminEntity, AdminDto.class);
        logger.info("AdminDto mapping: "+adminDto);

        return adminDto;
    }

    @Override
    public AdminDto updateAdmin(String id, AdminDto adminDto) {
        Administrateur adminEntity =  adminRepository.findByIdAdmin(id);

        if(adminEntity == null)throw new UsernameNotFoundException(id);
        /*TODO: optional fields.*/
        adminEntity.setNom(adminDto.getNom());
        adminEntity.setPrenom(adminDto.getPrenom());
        adminEntity.setCin(adminDto.getCin());


        Administrateur adminUpdated = adminRepository.save(adminEntity);

        AdminDto newAdminDto = new AdminDto();
        newAdminDto = modelMapper.map(adminUpdated, AdminDto.class);
        logger.info("AdminDto mapping: "+newAdminDto);

        return newAdminDto;
    }

    @Override
    public void deleteAdmin(String id) {
        Administrateur adminEntity =  adminRepository.findByIdAdmin(id);

        if(adminEntity == null)throw new UsernameNotFoundException(id);

        adminRepository.delete(adminEntity);
    }

    @Override
    public List<AdminDto> getAllAdmins(int page, int limit) {
        List<AdminDto> adminDtos = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Administrateur> adminsPage = adminRepository.findAll(pageableRequest);
        List<Administrateur> admins = adminsPage.getContent();
        for (Administrateur adminEntity: admins) {
            AdminDto adminDto = new AdminDto();
            adminDto = modelMapper.map(adminEntity, AdminDto.class);
            adminDtos.add(adminDto);
        }
        return adminDtos;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Administrateur adminEntity =  adminRepository.findByEmail(email);

        if(adminEntity==null)throw new UsernameNotFoundException(email);

        return new AdminPrincipal(adminEntity);
    }
}
