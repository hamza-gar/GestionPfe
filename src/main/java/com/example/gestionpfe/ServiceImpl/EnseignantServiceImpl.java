package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Dto.EtudiantDto;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Entities.Role;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.DomaineRepository;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.RoleRepository;
import com.example.gestionpfe.Security.Enseignant.EnseignantPrincipal;
import com.example.gestionpfe.Security.Etudiant.EtudiantPrincipal;
import com.example.gestionpfe.Services.DomaineService;
import com.example.gestionpfe.Services.EnseignantService;
import com.example.gestionpfe.Shared.EmailSender;
import com.example.gestionpfe.Shared.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
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
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(InitialUsersSetup.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    Utils util;

    @Autowired
    EmailSender emailSender;

    @Autowired
    DomaineService domaineService;

    @Autowired
    DomaineRepository domaineRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public EnseignantDto addEnseignant(EnseignantDto enseignantDto) {

        String domaine = enseignantDto.getEmail().split("@")[1];
        if(domaineRepository.existsByNomDomaineAndEtudiantIsTrue(domaine))
        {
            throw new RuntimeException("le domaine ne figure pas dans la liste des domaines verifie  !!!");
        }else{
            Enseignant checkEnseignant = enseignantRepository.findByEmail(enseignantDto.getEmail());

            if(checkEnseignant!=null) throw new RuntimeException("Enseignant deja exist !!!");
            Enseignant enseignantEntity = new Enseignant();
            enseignantEntity = modelMapper.map(enseignantDto,Enseignant.class);
            logger.info("EnseignantEntity mapping: "+enseignantEntity);

            enseignantEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(enseignantDto.getPassword()));
            enseignantEntity.setIdEnseignant(util.generateUserId(32));

            String token = util.generateUserId(26);
            enseignantEntity.setEmailVerificationToken(token);
            enseignantEntity.setEmailVerificationStatus(false);
            Role role = roleRepository.findByName("ROLE_ENSEIGNANT");
            enseignantEntity.setRole(role);
            emailSender.sendVerificationMail(enseignantEntity.getEmail(), token,"enseignants");


            Enseignant newEnseignant = enseignantRepository.save(enseignantEntity);

            EnseignantDto newEnseignantDto = new EnseignantDto();
            newEnseignantDto = modelMapper.map(newEnseignant,EnseignantDto.class);
            logger.info("newEnseignantDto mapping: "+newEnseignantDto);

            return newEnseignantDto;
        }


    }

    @Override
    public EnseignantDto getEnseignant(String email) {
        Enseignant enseignantEntity =  enseignantRepository.findByEmail(email);
        if(enseignantEntity==null)throw new UsernameNotFoundException(email);

        EnseignantDto enseignantDto = new EnseignantDto();
        enseignantDto = modelMapper.map(enseignantEntity,EnseignantDto.class);
        logger.info("enseignantDto mapping: "+enseignantDto);
        return enseignantDto;
    }

    @Override
    public EnseignantDto getEnseignantByIdEnseignant(String id) {
        Enseignant enseignantEntity =  enseignantRepository.findByIdEnseignant(id);

        if(enseignantEntity == null)throw new UsernameNotFoundException(id);

        EnseignantDto enseignantDto = new EnseignantDto();
        enseignantDto = modelMapper.map(enseignantEntity,EnseignantDto.class);
        logger.info("enseignantDto mapping: "+enseignantDto);

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
        newenseignantDto = modelMapper.map(enseignantUpdated,EnseignantDto.class);
        logger.info("newenseignantDto mapping: "+newenseignantDto);

        return newenseignantDto;
    }


    @Override
    public EnseignantDto verifyEnseignant(String token) {
        Enseignant enseignant = enseignantRepository.findByEmailVerificationToken(token);
        if (enseignant == null) throw new UsernameNotFoundException(token);
        enseignant.setEmailVerificationStatus(true);
        Enseignant updatedEnseignant = enseignantRepository.save(enseignant);

        EnseignantDto enseignantDto = new EnseignantDto();
        enseignantDto = modelMapper.map(updatedEnseignant,EnseignantDto.class);
        logger.info("enseignantDto mapping: "+enseignantDto);

        return enseignantDto;
    }

    @Override
    public EnseignantDto resendVerification(String enseignantId) {
        Enseignant enseignant = enseignantRepository.findByIdEnseignant(enseignantId);
        if (enseignant == null) throw new UsernameNotFoundException(enseignantId);
        emailSender.sendVerificationMail(enseignant.getEmail(), enseignant.getEmailVerificationToken(),"enseignants");
        EnseignantDto enseignantDto = new EnseignantDto();
        enseignantDto = modelMapper.map(enseignant,EnseignantDto.class);
        logger.info("enseignantDto mapping: "+enseignantDto);
        return enseignantDto;
    }

    @Override
    public void deleteEnseignant(String id) {
        Enseignant enseignantEntity =  enseignantRepository.findByIdEnseignant(id);
        logger.info("enseignantEntity mapping: "+enseignantEntity);
        if(enseignantEntity == null)throw new UsernameNotFoundException(id);

        enseignantRepository.delete(enseignantEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Enseignant enseignantEntity =  enseignantRepository.findByEmail(email);
        logger.info("enseignantEntity mapping: "+enseignantEntity);
        if(enseignantEntity==null)throw new UsernameNotFoundException(email);

        return new EnseignantPrincipal(enseignantEntity);
    }
}
