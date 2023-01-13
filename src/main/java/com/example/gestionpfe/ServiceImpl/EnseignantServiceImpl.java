package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Services.EnseignantService;
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
public class EnseignantServiceImpl implements EnseignantService {
    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    Utils util;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public EnseignantDto addEnseignant(EnseignantDto enseignantDto) {

        Enseignant checkEnseignant = enseignantRepository.findByEmail(enseignantDto.getEmail());

        if(checkEnseignant!=null) throw new RuntimeException("Enseignant deja exist !!!");
        Enseignant enseignantEntity = new Enseignant();
        BeanUtils.copyProperties(enseignantDto,enseignantEntity);

        enseignantEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(enseignantDto.getPassword()));
        enseignantEntity.setIdEnseignant(util.generateUserId(32));


        Enseignant newEnseignant = enseignantRepository.save(enseignantEntity);

        EnseignantDto newEnseignantDto = new EnseignantDto();

        BeanUtils.copyProperties(newEnseignant,newEnseignantDto);

        return newEnseignantDto;
    }

    @Override
    public EnseignantDto getEnseignant(String email) {
        Enseignant enseignantEntity =  enseignantRepository.findByEmail(email);
        if(enseignantEntity==null)throw new UsernameNotFoundException(email);

        EnseignantDto enseignantDto = new EnseignantDto();

        BeanUtils.copyProperties(enseignantEntity,enseignantDto);
        return enseignantDto;
    }

    @Override
    public EnseignantDto getEnseignantByIdEnseignant(String id) {
        Enseignant enseignantEntity =  enseignantRepository.findByIdEnseignant(id);

        if(enseignantEntity == null)throw new UsernameNotFoundException(id);

        EnseignantDto enseignantDto = new EnseignantDto();

        BeanUtils.copyProperties(enseignantEntity,enseignantDto);

        return enseignantDto;
    }

    @Override
    public EnseignantDto updateEnseignant(String id, EnseignantDto enseignantDto) {
        Enseignant enseignantEntity =  enseignantRepository.findByIdEnseignant(id);

        if(enseignantEntity == null)throw new UsernameNotFoundException(id);
        /*TODO: optional fields.*/
        enseignantEntity.setNom(enseignantDto.getNom());
        enseignantEntity.setPrenom(enseignantDto.getPrenom());
        enseignantEntity.setCin(enseignantDto.getCin());


        Enseignant enseignantUpdated = enseignantRepository.save(enseignantEntity);

        EnseignantDto newenseignantDto = new EnseignantDto();

        BeanUtils.copyProperties(enseignantUpdated,newenseignantDto);

        return newenseignantDto;
    }

    @Override
    public void deleteEnseignant(String id) {
        Enseignant enseignantEntity =  enseignantRepository.findByIdEnseignant(id);

        if(enseignantEntity == null)throw new UsernameNotFoundException(id);

        enseignantRepository.delete(enseignantEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Enseignant enseignantEntity =  enseignantRepository.findByEmail(email);

        if(enseignantEntity==null)throw new UsernameNotFoundException(email);

        return new User(enseignantEntity.getEmail(),enseignantEntity.getEncryptedPassword(),new ArrayList<>());
    }
}
