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
    EquipeRepository equipeRepository;

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

        logger.info("Creating Authorities...");
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

        Authority GET_BY_IDSUJET_AUTHORITY = createAuthority("GET_BY_IDSUJET_AUTHORITY");
        sAdminAuthorities.add(GET_BY_IDSUJET_AUTHORITY);
        adminAuthorities.add(GET_BY_IDSUJET_AUTHORITY);
        enseignantAuthorities.add(GET_BY_IDSUJET_AUTHORITY);
        etudiantAuthorities.add(GET_BY_IDSUJET_AUTHORITY);

        Authority GET_ALL_SUJETS_AUTHORITY = createAuthority("GET_ALL_SUJETS_AUTHORITY");
        sAdminAuthorities.add(GET_ALL_SUJETS_AUTHORITY);
        adminAuthorities.add(GET_ALL_SUJETS_AUTHORITY);
        enseignantAuthorities.add(GET_ALL_SUJETS_AUTHORITY);
        etudiantAuthorities.add(GET_ALL_SUJETS_AUTHORITY);

        Authority ADD_SUJET_AUTHORITY = createAuthority("ADD_SUJET_AUTHORITY");
        enseignantAuthorities.add(ADD_SUJET_AUTHORITY);

        Authority UPDATE_SUJET_AUTHORITY = createAuthority("UPDATE_SUJET_AUTHORITY");
        enseignantAuthorities.add(UPDATE_SUJET_AUTHORITY);

        Authority DELETE_SUJET_AUTHORITY = createAuthority("DELETE_SUJET_AUTHORITY");
        enseignantAuthorities.add(DELETE_SUJET_AUTHORITY);
        sAdminAuthorities.add(DELETE_SUJET_AUTHORITY);
        adminAuthorities.add(DELETE_SUJET_AUTHORITY);

        Authority GET_BY_IDEQUIPE_AUTHORITY = createAuthority("GET_BY_IDEQUIPE_AUTHORITY");
        enseignantAuthorities.add(GET_BY_IDEQUIPE_AUTHORITY);
        etudiantAuthorities.add(GET_BY_IDEQUIPE_AUTHORITY);

        Authority GET_ALL_EQUIPES_AUTHORITY = createAuthority("GET_ALL_EQUIPES_AUTHORITY");
        enseignantAuthorities.add(GET_ALL_EQUIPES_AUTHORITY);
        etudiantAuthorities.add(GET_ALL_EQUIPES_AUTHORITY);

        Authority ADD_EQUIPE_AUTHORITY = createAuthority("ADD_EQUIPE_AUTHORITY");
        etudiantAuthorities.add(ADD_EQUIPE_AUTHORITY);

        Authority ADD_ETUDIANT_TO_EQUIPE_AUTHORITY = createAuthority("ADD_ETUDIANT_TO_EQUIPE_AUTHORITY");
        etudiantAuthorities.add(ADD_ETUDIANT_TO_EQUIPE_AUTHORITY);

//        Authority DELETE_ETUDIANT_FROM_EQUIPE_AUTHORITY = createAuthority("DELETE_ETUDIANT_FROM_EQUIPE_AUTHORITY");
//        etudiantAuthorities.add(DELETE_ETUDIANT_FROM_EQUIPE_AUTHORITY);

        Authority DELETE_EQUIPE_AUTHORITY = createAuthority("DELETE_EQUIPE_AUTHORITY");
        sAdminAuthorities.add(DELETE_EQUIPE_AUTHORITY);
        adminAuthorities.add(DELETE_EQUIPE_AUTHORITY);
        enseignantAuthorities.add(DELETE_EQUIPE_AUTHORITY);


        Authority DELETE_SELF_FROM_EQUIPE_AUTHORITY = createAuthority("DELETE_SELF_FROM_EQUIPE_AUTHORITY");
        etudiantAuthorities.add(DELETE_SELF_FROM_EQUIPE_AUTHORITY);

        Authority GET_BY_IDFILIERE_AUTHORITY = createAuthority("GET_BY_IDFILIERE_AUTHORITY");
        sAdminAuthorities.add(GET_BY_IDFILIERE_AUTHORITY);
        adminAuthorities.add(GET_BY_IDFILIERE_AUTHORITY);
        enseignantAuthorities.add(GET_BY_IDFILIERE_AUTHORITY);
        etudiantAuthorities.add(GET_BY_IDFILIERE_AUTHORITY);

        Authority GET_ALL_FILIERES_AUTHORITY = createAuthority("GET_ALL_FILIERES_AUTHORITY");
        sAdminAuthorities.add(GET_ALL_FILIERES_AUTHORITY);
        adminAuthorities.add(GET_ALL_FILIERES_AUTHORITY);
        enseignantAuthorities.add(GET_ALL_FILIERES_AUTHORITY);
        etudiantAuthorities.add(GET_ALL_FILIERES_AUTHORITY);

        Authority ADD_FILIERE_AUTHORITY = createAuthority("ADD_FILIERE_AUTHORITY");
        sAdminAuthorities.add(ADD_FILIERE_AUTHORITY);
        adminAuthorities.add(ADD_FILIERE_AUTHORITY);

        Authority UPDATE_FILIERE_AUTHORITY = createAuthority("UPDATE_FILIERE_AUTHORITY");
        sAdminAuthorities.add(UPDATE_FILIERE_AUTHORITY);
        adminAuthorities.add(UPDATE_FILIERE_AUTHORITY);

        Authority DELETE_FILIERE_AUTHORITY = createAuthority("DELETE_FILIERE_AUTHORITY");
        sAdminAuthorities.add(DELETE_FILIERE_AUTHORITY);
        adminAuthorities.add(DELETE_FILIERE_AUTHORITY);



        logger.info("All Authorities created.");

        logger.info("Creating Roles...");
        Role sAdminRole = createRole("ROLE_SUPER_ADMIN", sAdminAuthorities);
        Role adminRole = createRole("ROLE_ADMIN", adminAuthorities);
        Role etudiantRole = createRole("ROLE_ETUDIANT", etudiantAuthorities);
        Role enseignantRole = createRole("ROLE_ENSEIGNANT", enseignantAuthorities);

        logger.info("All Roles created.");

        logger.info("Creating Domaines...");
        Domaine etuDomaine = createDomaine("etu.uae.ac.ma", true);
        Domaine ensDomaine = createDomaine("uae.ac.ma", false);
        Domaine fake1 = createDomaine("gmail.com", true);
        Domaine fake2 = createDomaine("hotmail.com", false);
        logger.info("All Domaines created.");

        logger.info("Creating Departements...");
        Departement departement = createDepartement("Departement Informatique");

        logger.info("Creating Users...");
        Administrateur administrateur = createAdministrateur(sAdminRole);
        logger.info("Super Admin created.");


        logger.info("Creating Enseignants...");
        Enseignant enseignant = createEnseignant(enseignantRole, departement);
        logger.info("Enseignant created.");


        logger.info("Creating Filieres...");
        Filiere filiere = createFiliere("Informatique", enseignant, departement);
        logger.info("Filiere Informatique created.");

        logger.info("Creating Etudiants...");
        Etudiant etudiant1 = createEtudiant("aba@etu.com", etudiantRole, filiere);
        Etudiant etudiant2 = createEtudiant("aba1@etu.com", etudiantRole, filiere);
        Etudiant etudiant3 = createEtudiant("aba2@etu.com", etudiantRole, filiere);
        logger.info("Etudiants created.");



        logger.info("Creating subjects...");
        Sujet sujet = createSujet("Sujet 1", "Sujet 1", 3, enseignant);
        logger.info("Subject created.");

        logger.info("Creating groupes...");
        Equipe groupe = createEquipe(3,sujet);

        logger.info("Group created.");

        logger.info("Adding students to group...");
        groupe=addEtudiantToEquipe(etudiant1, groupe);
        groupe=addEtudiantToEquipe(etudiant2, groupe);
        groupe=addEtudiantToEquipe(etudiant3, groupe);
        logger.info("Students added to group.");

    }

    @Transactional
    public Equipe createEquipe(int tailleEquipe,Sujet sujet) {
        Equipe equipe = equipeRepository.findByIdEquipe(utils.generateUserId(32));
        if (equipe == null) {
            equipe = new Equipe();
            equipe.setIdEquipe(utils.generateUserId(32));
            equipe.setTailleEquipe(tailleEquipe);
            equipe.setIsPrivate(false);
            equipe.setCryptedPassword("123456");
            equipe.setSujet(sujet);
            equipe = equipeRepository.save(equipe);
        }
        return equipe;
    }

    @Transactional
    public Equipe addEtudiantToEquipe(Etudiant etudiant, Equipe equipe) {
        if(equipe.getEtudiant() == null) {
            equipe.setEtudiant(new ArrayList<>());
        }
        equipe.getEtudiant().add(etudiant);
        equipe = equipeRepository.save(equipe);
        return equipe;
    }

    @Transactional
    public Sujet createSujet(String s, String s1, int tailleEquipe, Enseignant encadrant) {
        Sujet sujet = sujetRepository.findByNomSujet(s);
        if (sujet == null) {
            sujet = new Sujet();
            sujet.setIdSujet(utils.generateUserId(32));
            sujet.setNomSujet(s);
            sujet.setDescriptionSujet(s1);
            sujet.setTailleEquipe(tailleEquipe);
            sujet.setEncadrant(encadrant);
            sujet = sujetRepository.save(sujet);
        }
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
        Filiere filiere = filiereRepository.findByNomFiliere(informatique);
        if (filiere == null) {
            filiere = new Filiere();
            filiere.setNomFiliere(informatique);
            filiere.setDepartement(departement);
            filiere.setResponsable(enseignant);
            filiere.setIdFiliere(utils.generateUserId(32));
            filiere.setEtablissement("Abdelmalek Essaadi");
            filiereRepository.save(filiere);
        }
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
    public Etudiant createEtudiant(String mail, Role etudiantRole, Filiere filiere) {
        Etudiant etudiant = etudiantRepository.findByEmail(mail);

        if (etudiant == null) {
            etudiant = new Etudiant();
            etudiant.setApogee(123543217L);
            etudiant.setCne("p139375051");
            etudiant.setCin("kb19022");
            etudiant.setNom("lakhssassi");
            etudiant.setPrenom("Abdellah");
            etudiant.setEmail(mail);
            etudiant.setIdEtudiant(utils.generateUserId(32));
            etudiant.setEmailVerificationToken(utils.generateUserId(32));
            etudiant.setEmailVerificationStatus(true);
            etudiant.setEncryptedPassword(bCryptPasswordEncoder.encode("123456"));
            etudiant.setRole(etudiantRole);
            etudiant.setFiliere(filiere);
            etudiant = etudiantRepository.save(etudiant);
        }
        return etudiant;
    }

    @Transactional
    public Enseignant createEnseignant(Role enseignantRole, Departement departement) {
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
