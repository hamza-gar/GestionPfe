package com.example.gestionpfe.ServiceImpl;

import com.example.gestionpfe.Dto.DomaineDto;
import com.example.gestionpfe.Dto.EtudiantDto;
import com.example.gestionpfe.Entities.*;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Repositories.*;
import com.example.gestionpfe.Security.Etudiant.EtudiantPrincipal;
import com.example.gestionpfe.Services.DomaineService;
import com.example.gestionpfe.Services.EtudiantService;
import com.example.gestionpfe.Services.SujetService;
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
import java.util.stream.Collectors;


@Service
public class EtudiantServiceImpl implements EtudiantService {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(EtudiantServiceImpl.class);
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

    @Autowired
    SujetRepository sujetRepository;

    @Autowired
    FiliereRepository filiereRepository;

    @Override
    public EtudiantDto addEtudiant(EtudiantDto etudiantDto) {

        String domaine = etudiantDto.getEmail().split("@")[1];

        if (domaineRepository.existsByNomDomaineAndEtudiantIsTrue(domaine)) {
            Etudiant checkEtudiant = etudianRepository.findByEmail(etudiantDto.getEmail());

            if (checkEtudiant != null) {
                logger.error("Etudiant already exist !");
                throw new RuntimeException("Etudiant deja exist !");
            }
            Filiere filiere = filiereRepository.findByNomFiliere(etudiantDto.getNomFiliere());
            if (filiere == null) {
                logger.error("Filiere not found !");
                throw new RuntimeException("Filiere not found !");
            }
            Etudiant etudianEntity = new Etudiant();
            etudianEntity = modelMapper.map(etudiantDto, Etudiant.class);

            etudianEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(etudiantDto.getPassword()));
            etudianEntity.setIdEtudiant(util.generateUserId(32));
            String token = util.generateUserId(26);
            etudianEntity.setEmailVerificationToken(token);
            etudianEntity.setFiliere(filiere);
            etudianEntity.setEmailVerificationStatus(false);
            Role erole = roleRepository.findByName("ROLE_ETUDIANT");
            etudianEntity.setRole(erole);

            emailSender.sendVerificationMail(etudianEntity.getEmail(), token, "etudiants");

            Etudiant newEtudiant = etudianRepository.save(etudianEntity);

            EtudiantDto newEtudiantDto = new EtudiantDto();

            newEtudiantDto = modelMapper.map(newEtudiant, EtudiantDto.class);

            logger.info("Etudiant created:" + newEtudiantDto.getEmail());
            return newEtudiantDto;
        } else {
            logger.error("le domaine ne figure pas dans la liste des domaines verifie !");
            throw new RuntimeException("le domaine ne figure pas dans la liste des domaines verifie !");
        }


    }


    @Override
    public EtudiantDto getEtudiant(String email) {
        Etudiant etudiantEntity = etudianRepository.findByEmail(email);
        if (etudiantEntity == null) throw new UsernameNotFoundException(email);

        EtudiantDto etudiantDto = new EtudiantDto();

        etudiantDto.setIdEtudiant(etudiantEntity.getIdEtudiant());
        etudiantDto.setApogee(etudiantEntity.getApogee());
        etudiantDto.setCne(etudiantEntity.getCne());
        etudiantDto.setCin(etudiantEntity.getCin());
        etudiantDto.setNom(etudiantEntity.getNom());
        etudiantDto.setPrenom(etudiantEntity.getPrenom());
        etudiantDto.setEmail(etudiantEntity.getEmail());
        etudiantDto.setPassword(etudiantEntity.getEncryptedPassword());
        etudiantDto.setFiliere(etudiantEntity.getFiliere());
        etudiantDto.setEncryptedPassword(etudiantEntity.getEncryptedPassword());
        etudiantDto.setEmailVerificationStatus(etudiantEntity.getEmailVerificationStatus());
        etudiantDto.setEmailVerificationToken(etudiantEntity.getEmailVerificationToken());

        logger.info("Etudiant retrieved:" + etudiantDto.getEmail());
        return etudiantDto;
    }

    @Override
    public EtudiantDto getEtudiantByIdEtudiant(String id) {
        Etudiant etudiantEntity = etudianRepository.findByIdEtudiant(id);

        if (etudiantEntity == null) throw new UsernameNotFoundException(id);

        EtudiantDto etudiantDto = new EtudiantDto();

        etudiantDto.setIdEtudiant(etudiantEntity.getIdEtudiant());
        etudiantDto.setApogee(etudiantEntity.getApogee());
        etudiantDto.setCne(etudiantEntity.getCne());
        etudiantDto.setCin(etudiantEntity.getCin());
        etudiantDto.setNom(etudiantEntity.getNom());
        etudiantDto.setPrenom(etudiantEntity.getPrenom());
        etudiantDto.setEmail(etudiantEntity.getEmail());
        etudiantDto.setPassword(etudiantEntity.getEncryptedPassword());
        etudiantDto.setFiliere(etudiantEntity.getFiliere());
        etudiantDto.setEncryptedPassword(etudiantEntity.getEncryptedPassword());
        etudiantDto.setEmailVerificationStatus(etudiantEntity.getEmailVerificationStatus());
        etudiantDto.setEmailVerificationToken(etudiantEntity.getEmailVerificationToken());


        logger.info("Etudiant retrieved:" + etudiantDto.getEmail());

        return etudiantDto;
    }


    @Override
    public EtudiantDto getEtudiantByEmail(String email) {
        Etudiant etudiantEntity = etudianRepository.findByEmail(email);

        if (etudiantEntity == null) throw new UsernameNotFoundException(email);

        EtudiantDto etudiantDto = new EtudiantDto();

        etudiantDto.setIdEtudiant(etudiantEntity.getIdEtudiant());
        etudiantDto.setApogee(etudiantEntity.getApogee());
        etudiantDto.setCne(etudiantEntity.getCne());
        etudiantDto.setCin(etudiantEntity.getCin());
        etudiantDto.setNom(etudiantEntity.getNom());
        etudiantDto.setPrenom(etudiantEntity.getPrenom());
        etudiantDto.setEmail(etudiantEntity.getEmail());
        etudiantDto.setPassword(etudiantEntity.getEncryptedPassword());
        etudiantDto.setFiliere(etudiantEntity.getFiliere());
        etudiantDto.setEncryptedPassword(etudiantEntity.getEncryptedPassword());
        etudiantDto.setEmailVerificationStatus(etudiantEntity.getEmailVerificationStatus());
        etudiantDto.setEmailVerificationToken(etudiantEntity.getEmailVerificationToken());


        logger.info("Etudiant retrieved:" + etudiantDto.getEmail());

        return etudiantDto;
    }

    @Override
    public EtudiantDto updateEtudiant(String id, EtudiantDto etudiantdto) {
        Etudiant etudiantEntity = etudianRepository.findByIdEtudiant(id);

        if (etudiantEntity == null) throw new UsernameNotFoundException(id);
        /*TODO: optional fields.*/


        if (etudiantdto.getNom() != null) {
            etudiantEntity.setNom(etudiantdto.getNom());
        }
        if (etudiantdto.getPrenom() != null) {
            etudiantEntity.setPrenom(etudiantdto.getPrenom());
        }
        if (etudiantdto.getCin() != null) {
            etudiantEntity.setCin(etudiantdto.getCin());
        }
        if (etudiantdto.getCne() != null) {
            etudiantEntity.setCne(etudiantdto.getCne());
        }
        if (etudiantdto.getApogee() != null) {
            etudiantEntity.setApogee(etudiantdto.getApogee());
        }
        if (etudiantdto.getEmail() != null) {
            etudiantEntity.setEmail(etudiantdto.getEmail());
        }
        if (etudiantdto.getPassword() != null) {
            etudiantEntity.setEncryptedPassword(etudiantdto.getPassword());
        }


        Etudiant etudiantUpdated = etudianRepository.save(etudiantEntity);

        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto.setIdEtudiant(etudiantUpdated.getIdEtudiant());
        etudiantDto.setApogee(etudiantUpdated.getApogee());
        etudiantDto.setCne(etudiantUpdated.getCne());
        etudiantDto.setCin(etudiantUpdated.getCin());
        etudiantDto.setNom(etudiantUpdated.getNom());
        etudiantDto.setPrenom(etudiantUpdated.getPrenom());
        etudiantDto.setEmail(etudiantUpdated.getEmail());
        etudiantDto.setPassword(etudiantUpdated.getEncryptedPassword());
        etudiantDto.setFiliere(etudiantUpdated.getFiliere());
        etudiantDto.setEncryptedPassword(etudiantUpdated.getEncryptedPassword());
        etudiantDto.setEmailVerificationStatus(etudiantUpdated.getEmailVerificationStatus());
        etudiantDto.setEmailVerificationToken(etudiantUpdated.getEmailVerificationToken());
        logger.info("Etudiant updated successfully : " + etudiantDto.getEmail());

        return etudiantDto;
    }

    @Override
    public EtudiantDto verifyEtudiant(String token) {
        Etudiant etudiant = etudianRepository.findByEmailVerificationToken(token);
        if (etudiant == null) throw new UsernameNotFoundException(token);
        etudiant.setEmailVerificationStatus(true);
        Etudiant updatedEtudiant = etudianRepository.save(etudiant);

        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto.setIdEtudiant(updatedEtudiant.getIdEtudiant());
        etudiantDto.setApogee(updatedEtudiant.getApogee());
        etudiantDto.setCne(updatedEtudiant.getCne());
        etudiantDto.setCin(updatedEtudiant.getCin());
        etudiantDto.setNom(updatedEtudiant.getNom());
        etudiantDto.setPrenom(updatedEtudiant.getPrenom());
        etudiantDto.setEmail(updatedEtudiant.getEmail());
        etudiantDto.setPassword(updatedEtudiant.getEncryptedPassword());
        etudiantDto.setFiliere(updatedEtudiant.getFiliere());
        etudiantDto.setEncryptedPassword(updatedEtudiant.getEncryptedPassword());
        etudiantDto.setEmailVerificationStatus(updatedEtudiant.getEmailVerificationStatus());
        etudiantDto.setEmailVerificationToken(updatedEtudiant.getEmailVerificationToken());
        logger.info("Etudiant verified." + etudiantDto.getEmail());
        return etudiantDto;
    }

    @Override
    public EtudiantDto resendVerification(String etudiantId) {
        Etudiant etudiant = etudianRepository.findByIdEtudiant(etudiantId);
        if (etudiant == null) throw new UsernameNotFoundException(etudiantId);
        emailSender.sendVerificationMail(etudiant.getEmail(), etudiant.getEmailVerificationToken(), "etudiants");
        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto.setIdEtudiant(etudiant.getIdEtudiant());
        etudiantDto.setApogee(etudiant.getApogee());
        etudiantDto.setCne(etudiant.getCne());
        etudiantDto.setCin(etudiant.getCin());
        etudiantDto.setNom(etudiant.getNom());
        etudiantDto.setPrenom(etudiant.getPrenom());
        etudiantDto.setEmail(etudiant.getEmail());
        etudiantDto.setPassword(etudiant.getEncryptedPassword());
        etudiantDto.setFiliere(etudiant.getFiliere());
        etudiantDto.setEncryptedPassword(etudiant.getEncryptedPassword());
        etudiantDto.setEmailVerificationStatus(etudiant.getEmailVerificationStatus());
        etudiantDto.setEmailVerificationToken(etudiant.getEmailVerificationToken());

        logger.info("Resent verification to student : " + etudiantDto.getEmail());
        return etudiantDto;
    }

    @Override
    public void deleteEtudiant(String id) {
        Etudiant etudiantEntity = etudianRepository.findByIdEtudiant(id);

        if (etudiantEntity == null) throw new UsernameNotFoundException(id);

        etudianRepository.delete(etudiantEntity);
        logger.info("etudiant deleted successfully");
    }

    @Override
    public List<EtudiantDto> getAllEtudiants(int page, int limit) {
        List<EtudiantDto> etudiantDtos = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Etudiant> etudiantPages = etudianRepository.findAll(pageableRequest);
        List<Etudiant> etudiants = etudiantPages.getContent();
        for (Etudiant etudiant : etudiants) {
            EtudiantDto etudiantDto = new EtudiantDto();

            etudiantDto.setIdEtudiant(etudiant.getIdEtudiant());
            etudiantDto.setApogee(etudiant.getApogee());
            etudiantDto.setCne(etudiant.getCne());
            etudiantDto.setCin(etudiant.getCin());
            etudiantDto.setNom(etudiant.getNom());
            etudiantDto.setPrenom(etudiant.getPrenom());
            etudiantDto.setEmail(etudiant.getEmail());
            etudiantDto.setPassword(etudiant.getEncryptedPassword());
            etudiantDto.setFiliere(etudiant.getFiliere());
            etudiantDto.setEncryptedPassword(etudiant.getEncryptedPassword());
            etudiantDto.setEmailVerificationStatus(etudiant.getEmailVerificationStatus());
            etudiantDto.setEmailVerificationToken(etudiant.getEmailVerificationToken());


            etudiantDtos.add(etudiantDto);
        }
        logger.info("all etudiants found successfully");
        return etudiantDtos;
    }

    @Override
    public Boolean etudiantAlreadyInSujet(String idEtudiant, String idSujet) {
        Etudiant etudiant = etudianRepository.findByIdEtudiant(idEtudiant);
        if (etudiant == null) {
            logger.info("etudiantEntity not found with id :" + idEtudiant);
            throw new UsernameNotFoundException(idEtudiant);
        }
        Sujet sujet = sujetRepository.findByIdSujet(idSujet);
        if (sujet == null) {
            logger.info("sujet not found with id :" + idSujet);
            throw new UsernameNotFoundException(idSujet);
        }
        List<Equipe> equipes = etudiant.getEquipe();
        logger.info("Retrieving equipes of sujet : " + idSujet);
        for (Equipe equipe : equipes) {
            logger.info("Equipe found : " + equipe.getIdEquipe());
            logger.info("comparing sujet id : " + equipe.getSujet().getIdSujet() + " with " + idSujet);
            if (equipe.getSujet().getIdSujet().equals(idSujet)) {
                logger.info("Etudiant already in sujet");
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean etudiantIn3Sujets(String idEtudiant, String idEnseignant) {
        Etudiant etudiant = etudianRepository.findByIdEtudiant(idEtudiant);
        if (etudiant == null) {
            logger.info("etudiantEntity not found with id :" + idEtudiant);
            throw new UsernameNotFoundException(idEtudiant);
        }
        List<Sujet> sujets = etudiant.getEquipe().stream().map(Equipe::getSujet).collect(Collectors.toList());

        int count = 0;
        for (Sujet sujet : sujets) {
            if (sujet.getEncadrant().getIdEnseignant().equals(idEnseignant)) {
                count++;

            }
            if (count == 3) {
                logger.info("Etudiant already in 3 sujets");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean estPostulant(String username) {
        Etudiant etudiant = etudianRepository.findByEmail(username);
        if (etudiant == null) {
            logger.info("etudiantEntity not found with email :" + username);
            throw new UsernameNotFoundException(username);
        }
        List<Equipe> equipes = etudiant.getEquipe();
        if (equipes.size() != 1)
            return true;
        if (equipes.get(0).getSujet() == null)
            return true;
        if (!equipes.get(0).getSujet().getLocked())
            return true;
        return false;
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
