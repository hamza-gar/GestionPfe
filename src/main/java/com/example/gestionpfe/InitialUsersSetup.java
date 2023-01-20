package com.example.gestionpfe;

import com.example.gestionpfe.Entities.*;
import com.example.gestionpfe.Repositories.*;
import com.example.gestionpfe.Shared.Utils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class InitialUsersSetup {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(InitialUsersSetup.class);

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    DomaineRepository domaineRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    FiliereRepository filiereRepository;

    @Autowired
    SujetRepository sujetRepository;


    @Autowired
    DepartementRepository departementRepository;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        /* TODO:
         *   ADD AUTHORITIES.*/
        List<Authority> sAdminAuthorities = new ArrayList<>();
        List<Authority> adminAuthorities = new ArrayList<>();
        List<Authority> etudiantAuthorities = new ArrayList<>();
        List<Authority> enseignantAuthorities = new ArrayList<>();

        Authority GET_BY_IDETUDIANT_AUTHORITY = createAuthority("GET_BY_IDETUDIANT_AUTHORITY");
        sAdminAuthorities.add(GET_BY_IDETUDIANT_AUTHORITY);
        adminAuthorities.add(GET_BY_IDETUDIANT_AUTHORITY);
        etudiantAuthorities.add(GET_BY_IDETUDIANT_AUTHORITY);

        Authority GET_ALL_ETUDIANT_AUTHORITY = createAuthority("GET_ALL_ETUDIANT_AUTHORITY");
        sAdminAuthorities.add(GET_ALL_ETUDIANT_AUTHORITY);
        adminAuthorities.add(GET_ALL_ETUDIANT_AUTHORITY);


        Authority UPDATE_ETUDIANT_AUTHORITY = createAuthority("UPDATE_ETUDIANT_AUTHORITY");
        sAdminAuthorities.add(UPDATE_ETUDIANT_AUTHORITY);
        adminAuthorities.add(UPDATE_ETUDIANT_AUTHORITY);
        etudiantAuthorities.add(UPDATE_ETUDIANT_AUTHORITY);

        Authority DELETE_ETUDIANT_AUTHORITY = createAuthority("DELETE_ETUDIANT_AUTHORITY");
        sAdminAuthorities.add(DELETE_ETUDIANT_AUTHORITY);
        adminAuthorities.add(DELETE_ETUDIANT_AUTHORITY);
        etudiantAuthorities.add(DELETE_ETUDIANT_AUTHORITY);

        Authority GET_BY_IDADMIN_AUTHORITY = createAuthority("GET_BY_IDADMIN_AUTHORITY");
        sAdminAuthorities.add(GET_BY_IDADMIN_AUTHORITY);
        adminAuthorities.add(GET_BY_IDADMIN_AUTHORITY);


        Authority ADD_ADMIN_AUTHORITY = createAuthority("ADD_ADMIN_AUTHORITY");
        sAdminAuthorities.add(ADD_ADMIN_AUTHORITY);

        Authority UPDATE_ADMIN_AUTHORITY = createAuthority("UPDATE_ADMIN_AUTHORITY");
        sAdminAuthorities.add(UPDATE_ADMIN_AUTHORITY);
        adminAuthorities.add(UPDATE_ADMIN_AUTHORITY);

        Authority DELETE_ADMIN_AUTHORITY = createAuthority("DELETE_ADMIN_AUTHORITY");
        sAdminAuthorities.add(DELETE_ADMIN_AUTHORITY);


        Authority GET_LIST_ADMIN_AUTHORITY = createAuthority("GET_LIST_ADMIN_AUTHORITY");
        sAdminAuthorities.add(GET_LIST_ADMIN_AUTHORITY);

        Authority GET_DOMAINE_AUTHORITY = createAuthority("GET_DOMAINE_AUTHORITY");
        sAdminAuthorities.add(GET_DOMAINE_AUTHORITY);
        adminAuthorities.add(GET_DOMAINE_AUTHORITY);

        Authority GET_ALL_DOMAINE_AUTHORITY = createAuthority("GET_ALL_DOMAINE_AUTHORITY");
        sAdminAuthorities.add(GET_ALL_DOMAINE_AUTHORITY);
        adminAuthorities.add(GET_ALL_DOMAINE_AUTHORITY);

        Authority ADD_DOMAINE_AUTHORITY = createAuthority("ADD_DOMAINE_AUTHORITY");
        sAdminAuthorities.add(ADD_DOMAINE_AUTHORITY);
        adminAuthorities.add(ADD_DOMAINE_AUTHORITY);

        Authority UPDATE_DOMAINE_AUTHORITY = createAuthority("UPDATE_DOMAINE_AUTHORITY");
        sAdminAuthorities.add(UPDATE_DOMAINE_AUTHORITY);
        adminAuthorities.add(UPDATE_DOMAINE_AUTHORITY);

        Authority DELETE_DOMAINE_AUTHORITY = createAuthority("DELETE_DOMAINE_AUTHORITY");
        sAdminAuthorities.add(DELETE_DOMAINE_AUTHORITY);
        adminAuthorities.add(DELETE_DOMAINE_AUTHORITY);

        Authority GET_BY_IDENSEIGNANT_AUTHORITY = createAuthority("GET_BY_IDENSEIGNANT_AUTHORITY");
        sAdminAuthorities.add(GET_BY_IDENSEIGNANT_AUTHORITY);
        adminAuthorities.add(GET_BY_IDENSEIGNANT_AUTHORITY);
        enseignantAuthorities.add(GET_BY_IDENSEIGNANT_AUTHORITY);

        Authority GET_ALL_ENSEIGNANT_AUTHORITY = createAuthority("GET_ALL_ENSEIGNANT_AUTHORITY");
        sAdminAuthorities.add(GET_ALL_ENSEIGNANT_AUTHORITY);
        adminAuthorities.add(GET_ALL_ENSEIGNANT_AUTHORITY);
        enseignantAuthorities.add(GET_ALL_ENSEIGNANT_AUTHORITY);

        Authority UPDATE_ENSEIGNANT_AUTHORITY = createAuthority("UPDATE_ENSEIGNANT_AUTHORITY");
        sAdminAuthorities.add(UPDATE_ENSEIGNANT_AUTHORITY);
        adminAuthorities.add(UPDATE_ENSEIGNANT_AUTHORITY);
        enseignantAuthorities.add(UPDATE_ENSEIGNANT_AUTHORITY);

        Authority DELETE_ENSEIGNANT_AUTHORITY = createAuthority("DELETE_ENSEIGNANT_AUTHORITY");
        sAdminAuthorities.add(DELETE_ENSEIGNANT_AUTHORITY);
        adminAuthorities.add(DELETE_ENSEIGNANT_AUTHORITY);
        enseignantAuthorities.add(DELETE_ENSEIGNANT_AUTHORITY);


        Role sAdminRole = createRole("ROLE_SUPER_ADMIN", sAdminAuthorities);
        logger.info("Role super Admin created.");
        Role adminRole = createRole("ROLE_ADMIN", adminAuthorities);
        logger.info("Role Admin created.");
        Role etudiantRole = createRole("ROLE_ETUDIANT", etudiantAuthorities);
        logger.info("Role Etudiant created.");
        Role enseignantRole = createRole("ROLE_ENSEIGNANT", enseignantAuthorities);
        logger.info("Role Enseignant created.");


        Domaine etuDomaine = createDomaine("etu.uae.ac.ma", true);
        logger.info("Domaine etu.uae.ac.ma created.");
        Domaine ensDomaine = createDomaine("uae.ac.ma", false);
        logger.info("Domaine super Admin created.");
        Domaine fake1 = createDomaine("gmail.com", true);
        logger.info("Domaine gmail.com for etudiant created.");
        Domaine fake2 = createDomaine("hotmail.com", false);
        logger.info("Domaine hotmail.com for enseignant created.");

        Administrateur administrateur = createAdministrateur(sAdminRole);
        logger.info("Super Admin created.");

        Departement departement = createDepartement("Departement Informatique");


        Enseignant enseignant = createEnseignant(enseignantRole,departement);
        logger.info("Enseignant created.");

        Filiere filiere = createFiliere("Informatique",  enseignant, departement);
        logger.info("Filiere Informatique created.");

        Sujet sujet = createSujet("Sujet 1", "Sujet 1", 3);
    }

    @Transactional
    public Sujet createSujet(String s, String s1, int tailleEquipe) {
        Sujet sujet = new Sujet();
        sujet.setNomSujet(s);
        sujet.setDescriptionSujet(s1);
        sujet.setTailleEquipe(tailleEquipe);
        sujetRepository.save(sujet);
        return sujet;
    }


    @Transactional
    public Departement createDepartement(String departementName) {
        Departement departement = departementRepository.findByNomDepartement(departementName);
        if (departement == null) {
            departement = new Departement();
            departement.setNomDepartement(departementName);
            departement = departementRepository.save(departement);
        }
        return departement;
    }

    @Transactional
    public Filiere createFiliere(String informatique, Enseignant enseignant, Departement departement) {
        Filiere filiere = new Filiere();
        logger.info("Filiere created.");
        filiere.setNomFiliere(informatique);
        logger.info("Filiere nom created.");
        filiere.setDepartement(departement);
        logger.info("Filiere departement created.");
        filiere.setResponsable(enseignant);
        logger.info("Filiere responsable created.");
        filiereRepository.save(filiere);
        logger.info("Filiere saved.");
        return filiere;
    }


    @Transactional
    public Administrateur createAdministrateur(Role role) {
        Administrateur administrateur = adminRepository.findByEmail("abdellah.samourail@prof.com");
        if (administrateur == null) {
            administrateur = new Administrateur();
            administrateur.setCin("kb19022");
            administrateur.setNom("lakhssassi");
            administrateur.setPrenom("Abdellah");
            administrateur.setEmail("abdellah.samourail@prof.com");
            administrateur.setIdAdmin(utils.generateUserId(32));
            administrateur.setEncryptedPassword(bCryptPasswordEncoder.encode("654321"));
            administrateur.setRole(role);
            administrateur = adminRepository.save(administrateur);
        }
        return administrateur;
    }

    @Transactional
    public Domaine createDomaine(String s, boolean b) {
        Domaine domaine = domaineRepository.findByNomDomaine(s);
        if (domaine == null) {
            domaine = new Domaine();
            domaine.setEtudiant(b);
            domaine.setNomDomaine(s);
            domaine.setEtablissement("abdelmalek Essaadi");
            domaine = domaineRepository.save(domaine);
        }
        return domaine;
    }

    @Transactional
    public Etudiant createEtudiant(Role etudiantRole) {
        Etudiant etudiant = etudiantRepository.findByEmail("abdellah.samourail@gmail.com");

        if (etudiant == null) {
            etudiant = new Etudiant();
            etudiant.setApogee(123543217L);
            etudiant.setCne("p139375051");
            etudiant.setCin("kb19022");
            etudiant.setNom("lakhssassi");
            etudiant.setPrenom("Abdellah");
            etudiant.setEmail("abdellah.samourail@gmail.com");
            etudiant.setIdEtudiant(utils.generateUserId(32));
            etudiant.setEmailVerificationToken(utils.generateUserId(32));
            etudiant.setEmailVerificationStatus(true);
            etudiant.setEncryptedPassword(bCryptPasswordEncoder.encode("123456"));
            etudiant.setRole(etudiantRole);
            etudiant = etudiantRepository.save(etudiant);
        }
        return etudiant;
    }

    @Transactional
    public Enseignant createEnseignant(Role enseignantRole,Departement departement) {
        Enseignant enseignant = enseignantRepository.findByEmail("abdellah.samourail@prof.com");
        if (enseignant == null) {
            enseignant = new Enseignant();

            enseignant.setCin("kb19022");
            enseignant.setNom("lakhssassi");
            enseignant.setPrenom("Abdellah");
            enseignant.setEmail("abdellah.samourail@prof.com");
            enseignant.setIdEnseignant(utils.generateUserId(32));
            enseignant.setEmailVerificationToken(utils.generateUserId(32));
            enseignant.setEmailVerificationStatus(true);
            enseignant.setEncryptedPassword(bCryptPasswordEncoder.encode("123456"));
            enseignant.setRole(enseignantRole);
            enseignant.setDepartement(departement);
            enseignant.setFiliere(null);

            enseignant = enseignantRepository.save(enseignant);
        }
        return enseignant;
    }

    @Transactional
    public Role createRole(String name, List<Authority> authorities) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setAuthority(authorities);
            role = roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    public Authority createAuthority(String name) {
        Authority authority = authorityRepository.findByName(name);
        if (authority == null) {
            authority = new Authority();
            authority.setName(name);
            authority = authorityRepository.save(authority);
        }
        return authority;
    }
}
