package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Controllers.Dto.EtudiantDto;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Repositories.EtudiantRepository;
import com.example.gestionpfe.Services.EtudiantService;
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
public class EtudiantServiceImpl implements EtudiantService {
    @Autowired
    EtudiantRepository etudianRepository;

    @Autowired
    Utils util;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public EtudiantDto addEtudiant(EtudiantDto etudiantDto) {

        Etudiant checkEtudiant = etudianRepository.findByEmail(etudiantDto.getEmail());

        if(checkEtudiant!=null) throw new RuntimeException("Etudiant deja exist !!!");
        Etudiant etudianEntity = new Etudiant();
        BeanUtils.copyProperties(etudiantDto,etudianEntity);

        etudianEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(etudiantDto.getPassword()));
        etudianEntity.setIdEtudiant(util.generateUserId(32));


        Etudiant newEtudiant = etudianRepository.save(etudianEntity);

        EtudiantDto newEtudiantDto = new EtudiantDto();

        BeanUtils.copyProperties(newEtudiant,newEtudiantDto);

        return newEtudiantDto;
    }

    @Override
    public EtudiantDto getEtudiant(String email) {
        Etudiant etudiantEntity =  etudianRepository.findByEmail(email);
        if(etudiantEntity==null)throw new UsernameNotFoundException(email);

        EtudiantDto etudianDto = new EtudiantDto();

        BeanUtils.copyProperties(etudiantEntity,etudianDto);
        return etudianDto;
    }

    @Override
    public EtudiantDto getEtudiantByIdEtudiant(String id) {
        Etudiant etudiantEntity =  etudianRepository.findByIdEtudiant(id);

        if(etudiantEntity == null)throw new UsernameNotFoundException(id);

        EtudiantDto etudiantDto = new EtudiantDto();

        BeanUtils.copyProperties(etudiantEntity,etudiantDto);

        return etudiantDto;
    }

    @Override
    public EtudiantDto updateEtudiant(String id, EtudiantDto etudiantdto) {
        Etudiant etudiantEntity =  etudianRepository.findByIdEtudiant(id);

        if(etudiantEntity == null)throw new UsernameNotFoundException(id);
        /*TODO: optional fields.*/
        etudiantEntity.setNom(etudiantdto.getNom());
        etudiantEntity.setPrenom(etudiantdto.getPrenom());
        etudiantEntity.setCin(etudiantdto.getCin());
        etudiantEntity.setCne(etudiantdto.getCne());
        etudiantEntity.setApogee(etudiantdto.getApogee());

        Etudiant etudiantUpdated = etudianRepository.save(etudiantEntity);

        EtudiantDto etudiantDto = new EtudiantDto();

        BeanUtils.copyProperties(etudiantUpdated,etudiantDto);

        return etudiantDto;
    }

    @Override
    public void deleteEtudiant(String id) {
        Etudiant etudiantEntity =  etudianRepository.findByIdEtudiant(id);

        if(etudiantEntity == null)throw new UsernameNotFoundException(id);

        etudianRepository.delete(etudiantEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Etudiant etudiantEntity =  etudianRepository.findByEmail(email);

        if(etudiantEntity==null)throw new UsernameNotFoundException(email);

        return new User(etudiantEntity.getEmail(),etudiantEntity.getEncryptedPassword(),new ArrayList<>());
    }
}
