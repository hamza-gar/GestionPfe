package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Dto.EtudiantDto;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Services.EnseignantService;
import com.example.gestionpfe.Shared.EmailSender;
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
    EmailSender emailSender;

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

        String token = util.generateUserId(26);
        enseignantEntity.setEmailVerificationToken(token);
        enseignantEntity.setEmailVerificationStatus(false);
        emailSender.sendVerificationMail(enseignantEntity.getEmail(), token,"enseignants");


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
    public EnseignantDto verifyEnseignant(String token) {
        Enseignant enseignant = enseignantRepository.findByEmailVerificationToken(token);
        if (enseignant == null) throw new UsernameNotFoundException(token);
        enseignant.setEmailVerificationStatus(true);
        Enseignant updatedEnseignant = enseignantRepository.save(enseignant);

        EnseignantDto enseignantDto = new EnseignantDto();

        BeanUtils.copyProperties(updatedEnseignant, enseignantDto);

        return enseignantDto;
    }

    @Override
    public EnseignantDto resendVerification(String enseignantId) {
        Enseignant enseignant = enseignantRepository.findByIdEnseignant(enseignantId);
        if (enseignant == null) throw new UsernameNotFoundException(enseignantId);
        emailSender.sendVerificationMail(enseignant.getEmail(), enseignant.getEmailVerificationToken(),"enseignants");
        EnseignantDto enseignantDto = new EnseignantDto();
        BeanUtils.copyProperties(enseignant, enseignantDto);
        return enseignantDto;
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
