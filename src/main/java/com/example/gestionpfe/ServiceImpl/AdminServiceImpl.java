package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.AdminDto;
import com.example.gestionpfe.Entities.Administrateur;
import com.example.gestionpfe.Repositories.AdminRepository;

import com.example.gestionpfe.Services.AdminService;
import com.example.gestionpfe.Shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminServiceImpl implements AdminService {
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
        BeanUtils.copyProperties(adminDto,adminEntity);

        adminEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(adminDto.getPassword()));
        adminEntity.setIdAdmin(util.generateUserId(32));


        Administrateur newAdmin = adminRepository.save(adminEntity);

        AdminDto newAdminDto = new AdminDto();

        BeanUtils.copyProperties(newAdmin,newAdminDto);

        return newAdminDto;
    }


    @Override
    public AdminDto getAdmin(String email) {
        Administrateur adminEntity =  adminRepository.findByEmail(email);
        if(adminEntity==null)throw new UsernameNotFoundException(email);

        AdminDto adminDto = new AdminDto();

        BeanUtils.copyProperties(adminEntity,adminDto);
        return adminDto;
    }

    @Override
    public AdminDto getAdminByIdAdmin(String id) {
        Administrateur adminEntity =  adminRepository.findByIdAdmin(id);

        if(adminEntity == null)throw new UsernameNotFoundException(id);

        AdminDto adminDto = new AdminDto();

        BeanUtils.copyProperties(adminEntity,adminDto);

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

        BeanUtils.copyProperties(adminUpdated,newAdminDto);

        return newAdminDto;
    }

    @Override
    public void deleteAdmin(String id) {
        Administrateur adminEntity =  adminRepository.findByIdAdmin(id);

        if(adminEntity == null)throw new UsernameNotFoundException(id);

        adminRepository.delete(adminEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Administrateur adminEntity =  adminRepository.findByEmail(email);

        if(adminEntity==null)throw new UsernameNotFoundException(email);

        return new User(adminEntity.getEmail(),adminEntity.getEncryptedPassword(),new ArrayList<>());
    }
}
