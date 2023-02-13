package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Dto.EtudiantDto;
import com.example.gestionpfe.Entities.Departement;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Entities.Role;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.DepartementRepository;
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
public class EnseignantServiceImpl implements EnseignantService {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(EnseignantServiceImpl.class);
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
    DepartementRepository departementRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public EnseignantDto addEnseignant(EnseignantDto enseignantDto) {

        String domaine = enseignantDto.getEmail().split("@")[1];
        if (domaineRepository.findByNomDomaine(domaine) == null || domaineRepository.findByNomDomaine(domaine).getEtudiant()){
            logger.info("le domaine ne figure pas dans la liste des domaines verifie !");
            throw new RuntimeException("le domaine ne figure pas dans la liste des domaines verifie !");
        } else {
            Enseignant checkEnseignant = enseignantRepository.findByEmail(enseignantDto.getEmail());

            if (checkEnseignant != null) {
                logger.warn("Enseignant deja exist !!!");
                throw new RuntimeException("Enseignant deja exist !!!");
            }
            Enseignant enseignantEntity = new Enseignant();
            enseignantEntity = modelMapper.map(enseignantDto, Enseignant.class);
            logger.info(enseignantDto.getDepartement().getIdDepartement());
            Departement departement = departementRepository.findByIdDepartement(enseignantDto.getDepartement().getIdDepartement());

            if (departement == null) {
                logger.warn("Departement not found !!!");
                throw new RuntimeException("Departement not found !!!");
            }
            enseignantEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(enseignantDto.getPassword()));
            enseignantEntity.setIdEnseignant(util.generateUserId(32));

            String token = util.generateUserId(26);
            enseignantEntity.setEmailVerificationToken(token);
            enseignantEntity.setEmailVerificationStatus(false);
            Role role = roleRepository.findByName("ROLE_ENSEIGNANT");
            enseignantEntity.setRole(role);
            enseignantEntity.setDepartement(departement);


            Enseignant newEnseignant = enseignantRepository.save(enseignantEntity);
            emailSender.sendVerificationMail(enseignantEntity.getEmail(), token, "enseignants");
            EnseignantDto newEnseignantDto = new EnseignantDto();
            newEnseignantDto = modelMapper.map(newEnseignant, EnseignantDto.class);

            logger.info("Enseignant added successfully.");

            return newEnseignantDto;
        }


    }

    @Override
    public EnseignantDto getEnseignant(String email) {
        Enseignant enseignantEntity = enseignantRepository.findByEmail(email);
        if (enseignantEntity == null) throw new UsernameNotFoundException(email);

        EnseignantDto enseignantDto = new EnseignantDto();
        enseignantDto = modelMapper.map(enseignantEntity, EnseignantDto.class);

        logger.info("Enseignant retrieved by email successfully.");
        return enseignantDto;
    }

    @Override
    public EnseignantDto getEnseignantByIdEnseignant(String id) {
        Enseignant enseignantEntity = enseignantRepository.findByIdEnseignant(id);

        if (enseignantEntity == null) throw new UsernameNotFoundException(id);

        EnseignantDto enseignantDto = new EnseignantDto();
        enseignantDto = modelMapper.map(enseignantEntity, EnseignantDto.class);

        logger.info("Enseignant retrieved by idEnseignant successfully.");

        return enseignantDto;
    }

    @Override
    public EnseignantDto updateEnseignant(String id, EnseignantDto enseignantDto) {
        Enseignant enseignantEntity = enseignantRepository.findByIdEnseignant(id);

        if (enseignantEntity == null) throw new UsernameNotFoundException(id);
        /*TODO: optional fields.*/


        if (enseignantDto.getNom() != null) {
            enseignantEntity.setNom(enseignantDto.getNom());
        }

        if (enseignantDto.getPrenom() != null) {
            enseignantEntity.setPrenom(enseignantDto.getPrenom());
        }
        if (enseignantDto.getCin() != null) {
            enseignantEntity.setCin(enseignantDto.getCin());
        }
        if (enseignantDto.getEmail() != null) {
            enseignantEntity.setEmail(enseignantDto.getEmail());
        }
        if (enseignantDto.getPassword() != null) {
            enseignantEntity.setEncryptedPassword(enseignantDto.getPassword());
        }

        Enseignant enseignantUpdated = enseignantRepository.save(enseignantEntity);

        EnseignantDto newenseignantDto = new EnseignantDto();
        newenseignantDto = modelMapper.map(enseignantUpdated, EnseignantDto.class);

        logger.info("Enseignant updated successfully.");

        return newenseignantDto;
    }


    @Override
    public EnseignantDto verifyEnseignant(String token) {
        Enseignant enseignant = enseignantRepository.findByEmailVerificationToken(token);
        if (enseignant == null) throw new UsernameNotFoundException(token);
        enseignant.setEmailVerificationStatus(true);
        Enseignant updatedEnseignant = enseignantRepository.save(enseignant);

        EnseignantDto enseignantDto = new EnseignantDto();
        enseignantDto = modelMapper.map(updatedEnseignant, EnseignantDto.class);

        logger.info("Enseignant verified successfully.");
        return enseignantDto;
    }

    @Override
    public EnseignantDto resendVerification(String enseignantId) {
        Enseignant enseignant = enseignantRepository.findByIdEnseignant(enseignantId);
        if (enseignant == null) throw new UsernameNotFoundException(enseignantId);
        emailSender.sendVerificationMail(enseignant.getEmail(), enseignant.getEmailVerificationToken(), "enseignants");
        EnseignantDto enseignantDto = new EnseignantDto();
        enseignantDto = modelMapper.map(enseignant, EnseignantDto.class);

        logger.info("Resent verification mail to: " + enseignantDto.getEmail());
        return enseignantDto;
    }

    @Override
    public List<EnseignantDto> getAllEnseignants(int page, int limit) {
        List<EnseignantDto> enseignantDtos = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Enseignant> enseignantsPage = enseignantRepository.findAll(pageableRequest);
        List<Enseignant> enseignants = enseignantsPage.getContent();
        for (Enseignant enseignantEntity : enseignants) {
            EnseignantDto enseignantDto = new EnseignantDto();
            enseignantDto = modelMapper.map(enseignantEntity, EnseignantDto.class);
            enseignantDtos.add(enseignantDto);
        }
        logger.info("All Enseignants retrieved successfully");
        return enseignantDtos;
    }

    @Override
    public void deleteEnseignant(String id) {
        Enseignant enseignantEntity = enseignantRepository.findByIdEnseignant(id);

        if (enseignantEntity == null) {
            logger.info("Enseignant not found with id:" + id);
            return;
        }
        logger.info("Enseignant deleted : " + enseignantEntity.getIdEnseignant());
        enseignantRepository.delete(enseignantEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Enseignant enseignantEntity = enseignantRepository.findByEmail(email);

        if (enseignantEntity == null) throw new UsernameNotFoundException(email);
        logger.info("Enseignant loaded by username: " + enseignantEntity.getIdEnseignant());
        return new EnseignantPrincipal(enseignantEntity);
    }
}
