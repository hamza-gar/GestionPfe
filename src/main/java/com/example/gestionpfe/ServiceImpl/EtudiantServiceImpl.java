package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.DomaineDto;
import com.example.gestionpfe.Dto.EtudiantDto;
import com.example.gestionpfe.Entities.Domaine;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Entities.Role;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.DomaineRepository;
import com.example.gestionpfe.Repositories.EtudiantRepository;
import com.example.gestionpfe.Repositories.RoleRepository;
import com.example.gestionpfe.Security.Etudiant.EtudiantPrincipal;
import com.example.gestionpfe.Services.DomaineService;
import com.example.gestionpfe.Services.EtudiantService;
import com.example.gestionpfe.Shared.EmailSender;
import com.example.gestionpfe.Shared.Utils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EtudiantServiceImpl implements EtudiantService {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(InitialUsersSetup.class);
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    EtudiantRepository etudianRepository;

    @Autowired
    Utils util;

    @Autowired
    DomaineService domaineService;

    @Autowired
    DomaineRepository domaineRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmailSender emailSender;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public EtudiantDto addEtudiant(EtudiantDto etudiantDto) {

        String domaine = etudiantDto.getEmail().split("@")[1];

        if (domaineRepository.existsByNomDomaineAndEtudiantIsTrue(domaine)) {
            Etudiant checkEtudiant = etudianRepository.findByEmail(etudiantDto.getEmail());
            logger.info("checkEtudiant : " + checkEtudiant);

            if (checkEtudiant != null) throw new RuntimeException("Etudiant deja exist !!!");
            Etudiant etudianEntity = new Etudiant();
            etudianEntity = modelMapper.map(etudiantDto, Etudiant.class);
            logger.info("etudianEntity mapping: " + etudianEntity);


            etudianEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(etudiantDto.getPassword()));
            etudianEntity.setIdEtudiant(util.generateUserId(32));
            String token = util.generateUserId(26);
            etudianEntity.setEmailVerificationToken(token);
            etudianEntity.setEmailVerificationStatus(false);
            Role erole = roleRepository.findByName("ROLE_ETUDIANT");
            etudianEntity.setRole(erole);
            emailSender.sendVerificationMail(etudianEntity.getEmail(), token, "etudiants");
            logger.info("emailSender : " + emailSender);
            Etudiant newEtudiant = etudianRepository.save(etudianEntity);

            EtudiantDto newEtudiantDto = new EtudiantDto();

            newEtudiantDto = modelMapper.map(newEtudiant, EtudiantDto.class);
            logger.info("newEtudiantDto mapping: " + newEtudiantDto);

            return newEtudiantDto;
        } else throw new RuntimeException("le domaine ne figure pas dans la liste des domaines verifie  !!!");



    }


    @Override
    public EtudiantDto getEtudiant(String email) {
        Etudiant etudiantEntity = etudianRepository.findByEmail(email);
        if (etudiantEntity == null) throw new UsernameNotFoundException(email);

        EtudiantDto etudianDto = new EtudiantDto();
        etudianDto = modelMapper.map(etudiantEntity, EtudiantDto.class);
        logger.info("etudianDto mapping: " + etudianDto);
        return etudianDto;
    }

    @Override
    public EtudiantDto getEtudiantByIdEtudiant(String id) {
        Etudiant etudiantEntity = etudianRepository.findByIdEtudiant(id);

        if (etudiantEntity == null) throw new UsernameNotFoundException(id);

        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto = modelMapper.map(etudiantEntity, EtudiantDto.class);
        logger.info("etudiantDto mapping: " + etudiantDto);

        return etudiantDto;
    }

    @Override
    public EtudiantDto updateEtudiant(String id, EtudiantDto etudiantdto) {
        Etudiant etudiantEntity = etudianRepository.findByIdEtudiant(id);

        if (etudiantEntity == null) throw new UsernameNotFoundException(id);
        /*TODO: optional fields.*/
        etudiantEntity.setNom(etudiantdto.getNom());
        etudiantEntity.setPrenom(etudiantdto.getPrenom());
        etudiantEntity.setCin(etudiantdto.getCin());
        etudiantEntity.setCne(etudiantdto.getCne());
        etudiantEntity.setApogee(etudiantdto.getApogee());

        Etudiant etudiantUpdated = etudianRepository.save(etudiantEntity);

        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto = modelMapper.map(etudiantUpdated, EtudiantDto.class);
        logger.info("etudiantDto mapping: " + etudiantDto);

        return etudiantDto;
    }

    @Override
    public EtudiantDto verifyEtudiant(String token) {
        Etudiant etudiant = etudianRepository.findByEmailVerificationToken(token);
        if (etudiant == null) throw new UsernameNotFoundException(token);
        etudiant.setEmailVerificationStatus(true);
        Etudiant updatedEtudiant = etudianRepository.save(etudiant);

        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto = modelMapper.map(updatedEtudiant, EtudiantDto.class);
        logger.info("etudiantDto mapping: " + etudiantDto);
        return etudiantDto;
    }

    @Override
    public EtudiantDto resendVerification(String etudiantId) {
        Etudiant etudiant = etudianRepository.findByIdEtudiant(etudiantId);
        if (etudiant == null) throw new UsernameNotFoundException(etudiantId);
        emailSender.sendVerificationMail(etudiant.getEmail(), etudiant.getEmailVerificationToken(), "etudiants");
        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto = modelMapper.map(etudiant, EtudiantDto.class);
        logger.info("etudiantDto mapping: " + etudiantDto);
        return etudiantDto;
    }

    @Override
    public void deleteEtudiant(String id) {
        Etudiant etudiantEntity = etudianRepository.findByIdEtudiant(id);
        logger.info("etudiantEntity : " + etudiantEntity);
        if (etudiantEntity == null) throw new UsernameNotFoundException(id);

        etudianRepository.delete(etudiantEntity);
    }

    @Override
    public List<EtudiantDto> getAllEtudiants(int page, int limit) {
        List<EtudiantDto>etudiantDtos = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Etudiant> etudiantPages = etudianRepository.findAll(pageableRequest);
        List<Etudiant> etudiants = etudiantPages.getContent();
        for(Etudiant etudiant : etudiants){
            EtudiantDto etudiantDto = new EtudiantDto();
            etudiantDto = modelMapper.map(etudiant, EtudiantDto.class);
            logger.info("all etudiants found successfully");
            etudiantDtos.add(etudiantDto);
        }
        return etudiantDtos;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Etudiant etudiantEntity = etudianRepository.findByEmail(email);

        if (etudiantEntity == null) {
            logger.info("etudiantEntity not found with email :" + email);
            throw new UsernameNotFoundException(email);
        }
        logger.info("etudiantEntity found with email :" + email);
        return new EtudiantPrincipal(etudiantEntity);
    }
}
