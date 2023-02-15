package com.example.gestionpfe;

import com.example.gestionpfe.Entities.*;
import com.example.gestionpfe.Repositories.*;
import com.example.gestionpfe.Shared.EmailSender;
import com.example.gestionpfe.Shared.Utils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Component
//@Transactional
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
    UniversiteRepository universiteRepository;

    @Autowired
    EtablissementRepository etablissementRepository;

    @Autowired
    DepartementRepository departementRepository;

    @Autowired
    SoutenanceRepository soutenanceRepository;

    @Autowired
    JuryRepository juryRepository;

    @Autowired
    EmailSender emailSender;

//    @EventListener
//    @Transactional
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

        Authority GET_EQUIPES_OF_SUJETS_AUTHORITY = createAuthority("GET_EQUIPES_OF_SUJETS_AUTHORITY");
        enseignantAuthorities.add(GET_EQUIPES_OF_SUJETS_AUTHORITY);
        etudiantAuthorities.add(GET_EQUIPES_OF_SUJETS_AUTHORITY);

        Authority GET_LOCKED_EQUIPES_AUTHORITY = createAuthority("GET_LOCKED_EQUIPES_AUTHORITY");
        enseignantAuthorities.add(GET_LOCKED_EQUIPES_AUTHORITY);

        Authority ADD_EQUIPE_AUTHORITY = createAuthority("ADD_EQUIPE_AUTHORITY");
        etudiantAuthorities.add(ADD_EQUIPE_AUTHORITY);

        Authority ADD_ETUDIANT_TO_EQUIPE_AUTHORITY = createAuthority("ADD_ETUDIANT_TO_EQUIPE_AUTHORITY");
        etudiantAuthorities.add(ADD_ETUDIANT_TO_EQUIPE_AUTHORITY);

//        Authority DELETE_ETUDIANT_FROM_EQUIPE_AUTHORITY = createAuthority("DELETE_ETUDIANT_FROM_EQUIPE_AUTHORITY");
//        etudiantAuthorities.add(DELETE_ETUDIANT_FROM_EQUIPE_AUTHORITY);

        Authority DELETE_EQUIPE_AUTHORITY = createAuthority("DELETE_EQUIPE_AUTHORITY");
        enseignantAuthorities.add(DELETE_EQUIPE_AUTHORITY);


        Authority ADD_GDRIVELINK_AUTHORITY = createAuthority("ADD_GDRIVELINK_AUTHORITY");
        etudiantAuthorities.add(ADD_GDRIVELINK_AUTHORITY);

        Authority GET_EMAILS_OFMEMBERS_AUTHORITY = createAuthority("GET_EMAILS_OFMEMBERS_AUTHORITY");
        etudiantAuthorities.add(GET_EMAILS_OFMEMBERS_AUTHORITY);

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

        Authority GET_ALL_RENDEZVOUS_AUTHORITY = createAuthority("GET_ALL_RENDEZVOUS_AUTHORITY");
        enseignantAuthorities.add(GET_ALL_RENDEZVOUS_AUTHORITY);

        Authority TAKE_RENDEZVOUS_AUTHORITY = createAuthority("TAKE_RENDEZVOUS_AUTHORITY");
        etudiantAuthorities.add(TAKE_RENDEZVOUS_AUTHORITY);

        Authority FIX_RENDEZVOUS_AUTHORITY = createAuthority("FIX_RENDEZVOUS_AUTHORITY");
        enseignantAuthorities.add(FIX_RENDEZVOUS_AUTHORITY);

        Authority VOTE_RENDEZVOUS_AUTHORITY = createAuthority("VOTE_RENDEZVOUS_AUTHORITY");
        etudiantAuthorities.add(VOTE_RENDEZVOUS_AUTHORITY);

        logger.info("All Authorities created.");

        logger.info("Creating Roles...");
        Role sAdminRole = createRole("ROLE_SUPER_ADMIN", sAdminAuthorities);
        Role adminRole = createRole("ROLE_ADMIN", adminAuthorities);
        Role etudiantRole = createRole("ROLE_ETUDIANT", etudiantAuthorities);
        Role enseignantRole = createRole("ROLE_ENSEIGNANT", enseignantAuthorities);

        logger.info("All Roles created.");



        logger.info("Creating Universities...");
        Universite universite = createUniversite("Universite Abdelmalek Essaadi", "Tanger-Tétouan-Al Hoceïma");
        Universite universite2 = createUniversite("Universite Hassan II", "Casablanca-Settat");
        Universite universite3 = createUniversite("Universite Mohammed V", "Rabat-Salé-Kénitra");

        logger.info("All Universities created.");

        logger.info("Creating Domaines...");
        Domaine etuDomaine = createDomaine("etu.uae.ac.ma", true,universite);
        Domaine ensDomaine = createDomaine("uae.ac.ma", false,universite);
        Domaine fake1 = createDomaine("gmail.com", true,universite);
        Domaine fake2 = createDomaine("hotmail.com", false,universite);
        logger.info("All Domaines created.");

        logger.info("Creating Facultes...");
        Etablissement etablissement = createEtablissement("Faculte des Sciences et Techniques", universite);
        Etablissement etablissement2 = createEtablissement("Faculte des Sciences Juridiques Economiques et Sociales", universite);
        Etablissement etablissement3 = createEtablissement("Faculte des Sciences de la Sante", universite2);


        logger.info("Creating Departements...");
        Departement departement = createDepartement("Departement Informatique", etablissement);
        Departement departement2 = createDepartement("Departement Mathematique", etablissement);
        Departement departement3 = createDepartement("Departement Physique", etablissement);
        Departement departement4 = createDepartement("Departement de mathematique financiere", etablissement2);
        Departement departement5 = createDepartement("Departement de droit", etablissement2);
        Departement departement6 = createDepartement("Departement de neurologie", etablissement3);
        Departement departement7 = createDepartement("Departement d'ancologie", etablissement3);
        logger.info("Departement Informatique created.");

        logger.info("Creating Users...");
        Administrateur administrateur = createAdministrateur(sAdminRole);
        Administrateur administrateur2 = createAdministrateur2(adminRole);
        logger.info("Super Admin created.");


        logger.info("Creating Enseignants...");
        Enseignant enseignant = createEnseignant("abdellah.lakhssassi@prof.com", enseignantRole, departement);
        Enseignant enseignant2 = createEnseignant("ens@hotmail.com", enseignantRole, departement);
        Enseignant enseignant3 = createEnseignant("ens2@gmail.com", enseignantRole, departement);
        Enseignant enseignant4 = createEnseignant("ens3@etu.uae.ac.ma", enseignantRole, departement);
        Enseignant enseignant5 = createEnseignant("ens4@etu.uae.ac.ma", enseignantRole, departement);
        Enseignant enseignant6 = createEnseignant("ens5@etu.uae.ac.ma", enseignantRole, departement);
        Enseignant enseignant7 = createEnseignant("ens6@etu.uae.ac.ma", enseignantRole, departement);
        Enseignant enseignant8 = createEnseignant("ens7@etu.uae.ac.ma", enseignantRole, departement);
        Enseignant enseignant9 = createEnseignant("ens8@etu.uae.ac.ma", enseignantRole, departement);
        Enseignant enseignant10 = createEnseignant("ens9@etu.uae.ac.ma", enseignantRole, departement);

        Enseignant enseignant21 = createEnseignant("ekdiha@prof.com", enseignantRole, departement2);
        Enseignant enseignant22 = createEnseignant("zitan@prof.com", enseignantRole, departement2);
        Enseignant enseignant23 = createEnseignant("math@prof.com", enseignantRole, departement2);
        Enseignant enseignant24 = createEnseignant("math2@prof.com", enseignantRole, departement2);
        Enseignant enseignant25 = createEnseignant("math3@prof.com", enseignantRole, departement2);
        Enseignant enseignant26 = createEnseignant("math4@prof.com", enseignantRole, departement2);
        Enseignant enseignant27 = createEnseignant("math5@prof.com", enseignantRole, departement2);
        Enseignant enseignant28 = createEnseignant("math6@prof.com", enseignantRole, departement2);
        Enseignant enseignant29 = createEnseignant("math7@prof.com", enseignantRole, departement2);
        Enseignant enseignant30 = createEnseignant("math8@prof.com", enseignantRole, departement2);

        Enseignant enseignant31 = createEnseignant("kamili@prof.com", enseignantRole, departement3);
        Enseignant enseignant32 = createEnseignant("physique@prof.com", enseignantRole, departement3);
        Enseignant enseignant33 = createEnseignant("physique2@prof.com", enseignantRole, departement3);
        Enseignant enseignant34 = createEnseignant("physique3@prof.com", enseignantRole, departement3);
        Enseignant enseignant35 = createEnseignant("physique4@prof.com", enseignantRole, departement3);
        Enseignant enseignant36 = createEnseignant("physique5@prof.com", enseignantRole, departement3);
        Enseignant enseignant37 = createEnseignant("physique6@prof.com", enseignantRole, departement3);
        Enseignant enseignant38 = createEnseignant("physique7@prof.com", enseignantRole, departement3);
        Enseignant enseignant39 = createEnseignant("physique8@prof.com", enseignantRole, departement3);
        Enseignant enseignant40 = createEnseignant("physique9@prof.com", enseignantRole, departement3);

        Enseignant enseignant41 = createEnseignant("finance@prof.com", enseignantRole, departement4);
        Enseignant enseignant42 = createEnseignant("finance1@prof.com", enseignantRole, departement4);
        Enseignant enseignant43 = createEnseignant("finance2@prof.com", enseignantRole, departement4);
        Enseignant enseignant44 = createEnseignant("finance3@prof.com", enseignantRole, departement4);
        Enseignant enseignant45 = createEnseignant("finance4@prof.com", enseignantRole, departement4);
        Enseignant enseignant46 = createEnseignant("finance5@prof.com", enseignantRole, departement4);
        Enseignant enseignant47 = createEnseignant("finance6@prof.com", enseignantRole, departement4);
        Enseignant enseignant48 = createEnseignant("finance7@prof.com", enseignantRole, departement4);
        Enseignant enseignant49 = createEnseignant("finance8@prof.com", enseignantRole, departement4);
        Enseignant enseignant50 = createEnseignant("finance9@prof.com", enseignantRole, departement4);

        Enseignant enseignant51 = createEnseignant("economist1@prof.com", enseignantRole, departement5);
        Enseignant enseignant52 = createEnseignant("economist2@prof.com", enseignantRole, departement5);
        Enseignant enseignant53 = createEnseignant("economist3@prof.com", enseignantRole, departement5);
        Enseignant enseignant54 = createEnseignant("economist4@prof.com", enseignantRole, departement5);
        Enseignant enseignant55 = createEnseignant("economist5@prof.com", enseignantRole, departement5);
        Enseignant enseignant56 = createEnseignant("economist6@prof.com", enseignantRole, departement5);
        Enseignant enseignant57 = createEnseignant("economist7@prof.com", enseignantRole, departement5);
        Enseignant enseignant58 = createEnseignant("economist8@prof.com", enseignantRole, departement5);
        Enseignant enseignant59 = createEnseignant("economist9@prof.com", enseignantRole, departement5);
        Enseignant enseignant60 = createEnseignant("economist10@prof.com", enseignantRole, departement5);

        Enseignant enseignant11 = createEnseignant("medecin1@prof.com", enseignantRole, departement6);
        Enseignant enseignant12 = createEnseignant("medecin2@prof.com", enseignantRole, departement6);
        Enseignant enseignant13 = createEnseignant("medecin3@prof.com", enseignantRole, departement6);
        Enseignant enseignant14 = createEnseignant("medecin4@prof.com", enseignantRole, departement6);
        Enseignant enseignant15 = createEnseignant("medecin5@prof.com", enseignantRole, departement6);
        Enseignant enseignant16 = createEnseignant("medecin6@prof.com", enseignantRole, departement6);
        Enseignant enseignant17 = createEnseignant("medecin7@prof.com", enseignantRole, departement6);
        Enseignant enseignant18 = createEnseignant("medecin8@prof.com", enseignantRole, departement6);
        Enseignant enseignant19 = createEnseignant("medecin9@prof.com", enseignantRole, departement6);
        Enseignant enseignant20 = createEnseignant("medecin10@prof.com", enseignantRole, departement6);

        Enseignant enseignant61 = createEnseignant("medecin11@prof.com", enseignantRole, departement7);
        Enseignant enseignant62 = createEnseignant("medecin12@prof.com", enseignantRole, departement7);
        Enseignant enseignant63 = createEnseignant("medecin13@prof.com", enseignantRole, departement7);
        Enseignant enseignant64 = createEnseignant("medecin14@prof.com", enseignantRole, departement7);
        Enseignant enseignant65 = createEnseignant("medecin15@prof.com", enseignantRole, departement7);
        Enseignant enseignant66 = createEnseignant("medecin16@prof.com", enseignantRole, departement7);
        Enseignant enseignant67 = createEnseignant("medecin17@prof.com", enseignantRole, departement7);
        Enseignant enseignant68 = createEnseignant("medecin18@prof.com", enseignantRole, departement7);
        Enseignant enseignant69 = createEnseignant("medecin19@prof.com", enseignantRole, departement7);
        Enseignant enseignant70 = createEnseignant("medecin20@prof.com", enseignantRole, departement7);
        logger.info("Enseignants created.");


        logger.info("Creating Filieres...");
        Filiere filiere = createFiliere("Informatique", enseignant, departement);
        Filiere filiereMath = createFiliere("Mathematique", enseignant21, departement2);
        Filiere filierePhysique = createFiliere("Physique", enseignant31, departement3);
        Filiere filiereEconomie = createFiliere("Economie", enseignant41, departement4);
        Filiere filiereDroit = createFiliere("Droit", enseignant51, departement5);
        Filiere filiereMedecineGeneral = createFiliere("Ancologie", enseignant61, departement7);
        logger.info("Filieres created.");

        logger.info("Creating Etudiants...");
        Etudiant etudiantInfo1 = createEtudiant("hamza.garmouch@etu.uae.ac.ma", etudiantRole, filiere);
        Etudiant etudiantInfo2 = createEtudiant("abdellah.lakhssassi@hotmail.com", etudiantRole, filiere);
        Etudiant etudiantInfo3 = createEtudiant("aba2@etu.com", etudiantRole, filiere);
        Etudiant etudiantInfo4 = createEtudiant("aba3@etu.com", etudiantRole, filiere);
        Etudiant etudiantInfo5 = createEtudiant("aba4@etu.com", etudiantRole, filiere);

        Etudiant etudiantEco1 = createEtudiant("etueco@etu.com", etudiantRole, filiereEconomie);
        Etudiant etudiantEco2 = createEtudiant("etueco1@etu.com", etudiantRole, filiereEconomie);
        Etudiant etudiantEco3 = createEtudiant("etueco3@etu.com", etudiantRole, filiereEconomie);
        Etudiant etudiantEco4 = createEtudiant("etueco4@etu.com", etudiantRole, filiereEconomie);
        Etudiant etudiantEco5 = createEtudiant("etueco5@etu.com", etudiantRole, filiereEconomie);
        Etudiant etudiantEco6 = createEtudiant("etueco6@etu.com", etudiantRole, filiereEconomie);

        Etudiant etudiantDroit1 = createEtudiant("droitetu@etu.com", etudiantRole, filiereDroit);
        Etudiant etudiantDroit2 = createEtudiant("droitetu1@etu.com", etudiantRole, filiereDroit);
        Etudiant etudiantDroit3 = createEtudiant("droitetu2@etu.com", etudiantRole, filiereDroit);
        Etudiant etudiantDroit4 = createEtudiant("droitetu3@etu.com", etudiantRole, filiereDroit);
        Etudiant etudiantDroit5 = createEtudiant("droitetu4@etu.com", etudiantRole, filiereDroit);
        Etudiant etudiantDroit6 = createEtudiant("droitetu5@etu.com", etudiantRole, filiereDroit);

        Etudiant etudiantMath1 = createEtudiant("mathetu@etu.com", etudiantRole, filiereMath);
        Etudiant etudiantMath2 = createEtudiant("mathetu2@etu.com", etudiantRole, filiereMath);
        Etudiant etudiantMath3 = createEtudiant("mathetu3@etu.com", etudiantRole, filiereMath);
        Etudiant etudiantMath4 = createEtudiant("mathetu4@etu.com", etudiantRole, filiereMath);
        Etudiant etudiantMath5 = createEtudiant("mathet5@etu.com", etudiantRole, filiereMath);
        Etudiant etudiantMath6 = createEtudiant("mathetu6@etu.com", etudiantRole, filiereMath);

        Etudiant etudiantPC1 = createEtudiant("pcetu@etu.com", etudiantRole, filierePhysique);
        Etudiant etudiantPC2 = createEtudiant("pcetu2@etu.com", etudiantRole, filierePhysique);
        Etudiant etudiantPC3 = createEtudiant("pcetu3@etu.com", etudiantRole, filierePhysique);
        Etudiant etudiantPC4 = createEtudiant("pcetu4@etu.com", etudiantRole, filierePhysique);
        Etudiant etudiantPC5 = createEtudiant("pcetu5@etu.com", etudiantRole, filierePhysique);
        Etudiant etudiantPC6 = createEtudiant("pcetu6@etu.com", etudiantRole, filierePhysique);

        Etudiant etudiantMed1 = createEtudiant("medetu@etu.com", etudiantRole, filiereMedecineGeneral);
        Etudiant etudiantMed2 = createEtudiant("medetu2@etu.com", etudiantRole, filiereMedecineGeneral);
        Etudiant etudiantMed3 = createEtudiant("medetu3@etu.com", etudiantRole, filiereMedecineGeneral);
        Etudiant etudiantMed4 = createEtudiant("medetu4@etu.com", etudiantRole, filiereMedecineGeneral);
        Etudiant etudiantMed6 = createEtudiant("medetu6@etu.com", etudiantRole, filiereMedecineGeneral);

        logger.info("Etudiants created.");


        logger.info("Creating subjects...");
        Sujet sujet = createSujet("Chess AI", "Chess has been around for centuries, but the game has recently seen a major " +
                "shift in its competitive landscape. Thanks to developments in artificial intelligence (AI) technology," +
                " the use of computer-aided analysis is becoming increasingly prevalent in chess. This begs the question—what " +
                "are the implications of using AI to play chess? In this blog post, we'll explore the history of chess, how" +
                " AI is being used to increase competitiveness in the game," +
                " and some ethical considerations that come with introducing new technologies into an already established game.", 2, enseignant, true, true);
        Sujet sujet1 = createSujet("Google Map API", "All the information you need to bring the real world to your web and mobile" +
                " apps with Google Maps Platform SDKs and APIs for Maps, Routes, and Places.", 4, enseignant, true, false);
        Sujet sujet2 = createSujet("Gestion de Stock", "La gestion de stock est une étude stratégique des besoins de votre" +
                " commerce en prévision des ventes sur le marché.", 3, enseignant, false, false);
        Sujet sujet3 = createSujet("Gestion pfe", "La gestion de pfe est une étude stratégique des besoins de votre besoin", 3, enseignant, false, false);
        Sujet sujet4 = createSujet("Robot cuisinier", "Un robot de cuisine ou robot ménager ou robot multifonction, parfois abrégé simplement en « robot », est un appareil électrique de cuisine utilisé pour faciliter" +
                " diverses tâches répétitives dans le processus de préparation des aliments. ", 2, enseignant, true, true);

        Sujet sujet5 = createSujet("System de securite de porte", "Un système de sécurité de porte est un dispositif ou une combinaison de dispositifs conçus " +
                "pour protéger une porte contre les intrusions indésirables. ", 3, enseignant2, false, false);
        Sujet sujet6 = createSujet("Gestion de Location de voiture", "Sujet 1", 3, enseignant2, false, false);
        Sujet sujet7 = createSujet("Gestion de vente", "Sujet 1", 3, enseignant2, false, false);
        Sujet sujet8 = createSujet("système de covoiturage", "Sujet 1", 3, enseignant2, false, false);
        Sujet sujet9 = createSujet(" crypto-systèmes modernes", "Sujet 1", 3, enseignant2, false, false);

        Sujet sujet10 = createSujet("Les associations engagées dans l'aide caritative", "Sujet 1", 3, enseignant3, false, false);
        Sujet sujet11 = createSujet("la gestion des parkings", "Sujet 1", 3, enseignant3, false, false);
        Sujet sujet12 = createSujet("la ligue sportive du sport", "Sujet 1", 3, enseignant3, false, false);
        Sujet sujet13 = createSujet("la gestion des clubs", "Sujet 1", 3, enseignant3, false, false);
        Sujet sujet14 = createSujet("la gestion des associations", "Sujet 1", 3, enseignant3, false, false);

        Sujet sujet15 = createSujet("Filetrage d'images", "Sujet 1", 3, enseignant4, false, false);
        Sujet sujet16 = createSujet("la gestion des poubelles en ville.", "Sujet 1", 3, enseignant4, false, false);
        Sujet sujet17 = createSujet("la gestion des déchets", "Sujet 1", 3, enseignant4, false, false);
        Sujet sujet18 = createSujet("les analyses médicales", "Sujet 1", 3, enseignant4, false, false);
        Sujet sujet19 = createSujet("la gestion des maladies", "Sujet 1", 3, enseignant4, false, false);

        Sujet sujet20 = createSujet("site web dynamique pour poster les annonces", "Sujet 1", 3, enseignant5, false, false);
        Sujet sujet21 = createSujet("la gestion des annonces", "Sujet 1", 3, enseignant5, false, false);
        Sujet sujet22 = createSujet(" suivi de malades en milieu hospitalier", "Sujet 1", 3, enseignant5, false, false);
        Sujet sujet23 = createSujet(" calculer la retraite d’un salarié", "Sujet 1", 3, enseignant5, false, false);
        Sujet sujet24 = createSujet("Segmentation d’une image", "Sujet 1", 3, enseignant5, false, false);

        Sujet sujet25 = createSujet("L'implémentation d'un chat securisé", "Sujet 1", 3, enseignant6, false, false);
        Sujet sujet26 = createSujet("La compression des données ", "Sujet 1", 3, enseignant6, false, false);
        Sujet sujet27 = createSujet("La gestion des fichiers ", "Sujet 1", 3, enseignant6, false, false);
        Sujet sujet28 = createSujet(" bounding box", "Sujet 1", 3, enseignant6, false, false);
        Sujet sujet29 = createSujet("la standarisation automatique des dataset d'image ", "Sujet 1", 3, enseignant6, false, false);

        Sujet sujet30 = createSujet("Un éditeur graphique pour les RDP", "Sujet 1", 3, enseignant7, false, false);
        Sujet sujet31 = createSujet("Calcul matriciel complexe ", "Sujet 1", 3, enseignant7, false, false);
        Sujet sujet32 = createSujet("e gestion des notes pour la faculté de droit ", "Sujet 1", 3, enseignant7, false, false);
        Sujet sujet33 = createSujet(" l'analyse des fichiers", "Sujet 1", 3, enseignant7, false, false);
        Sujet sujet34 = createSujet("Filtrage des images médicales ", "Sujet 1", 3, enseignant7, false, false);

        Sujet sujet35 = createSujet("Un éditeur graphique pour les RDP", "Sujet 1", 3, enseignant8, false, false);
        Sujet sujet36 = createSujet("Calcul matriciel complexe ", "Sujet 1", 3, enseignant8, false, false);
        Sujet sujet37 = createSujet("e gestion des notes pour la faculté de droit ", "Sujet 1", 3, enseignant8, false, false);
        Sujet sujet38 = createSujet(" l'analyse des fichiers", "Sujet 1", 3, enseignant8, false, false);
        Sujet sujet39 = createSujet("Filtrage des images médicales ", "Sujet 1", 3, enseignant8, false, false);

        Sujet sujet40 = createSujet("site web dynamique pour poster les annonces", "Sujet 1", 3, enseignant9, false, false);
        Sujet sujet41 = createSujet("la gestion des annonces", "Sujet 1", 3, enseignant9, false, false);
        Sujet sujet42 = createSujet(" suivi de malades en milieu hospitalier", "Sujet 1", 3, enseignant9, false, false);
        Sujet sujet43 = createSujet(" calculer la retraite d’un salarié", "Sujet 1", 3, enseignant9, false, false);
        Sujet sujet44 = createSujet("Segmentation d’une image", "Sujet 1", 3, enseignant9, false, false);
        Sujet sujet180 = createSujet("Segmentation d’une image", "Sujet 1", 3, enseignant10, false, false);

        Sujet sujet45 = createSujet("Étude semiclassique des modes de pression chaotiques", "Dans cette section, nous nous intéressons aux pulsateurs" +
                " en rotation rapide et plus particulièrement aux δ Scuti. Nous verrons que les spectres d’oscillation des δ Scuti ont des propriétés " +
                "très variées en terme de gamme, de nombre et d’amplitudes des" +
                " fréquences observées. Pour mieux les comprendre d’un point de vue théorique, ", 3, enseignant21, false, false);
        Sujet sujet46 = createSujet("Les jeux mathématiques : les aspects théoriques", "Il est facile de constater que le jeu est partout présent dans notre quotidien. Qu’il soit pratiqué en solitaire ou à plusieurs nous observons du jeu en permanence : dans les journaux, à la télévision, à la radio et plus encore sur les ordinateurs, smartphones et autres tablettes." +
                " Mais que savons-nous réellement du jeu et de son" +
                " utilité dans un cadre comme celui de l’éducation ?", 2, enseignant21, false, false);

        Sujet sujet47 = createSujet("Régulation court terme du trafic aérien et optimisation combinatoire", "L’espace aérien est une ressource limitée qui est soumise à une forte demande dans certaines parties du monde telles que l’Europe de l’Ouest, l’Amérique du Nord et l’Asie du Sud Est. Afin d’assurer le respect des normes de sécurité imposant une séparation physique minimale entre deux avions, " +
                "cet espace aérien a été divisé dans ces zones en différents secteurs aériens", 3, enseignant21, false, false);

        Sujet sujet48 = createSujet("Retour sur le comportement mécanique", "Toujours en lien avec l’étude du comportement des voies ferroviaires, nous présentons ici plusieurs études (semi-)analytiques et numériques réalisées auparavant. Tout comme la section précédente, cette partie a simplement pour" +
                " but d’illustrer les différentes démarches possibles à l’aide de certains travaux,", 3, enseignant22, false, false);

        Sujet sujet49 = createSujet("Modes de pression dans les étoiles en rotation rapide ", "Dans cette section, nous nous intéressons aux pulsateurs en rotation rapide et plus particulièrement aux Scuti." +
                " Nous verrons que les spectres d’oscillation des Scuti ont des propriétés très variées en terme de gamme,", 4, enseignant22, false, false);

        Sujet sujet50 = createSujet("Modelisation mathematique etformulation numerique ", "Les écoulements des fluides dans les conduites ont fait l’objet de très nombreux travaux aussi" +
                " bien sur le plan expérimental, théorique que numérique.", 3, enseignant22, false, false);

        Sujet sujet51 = createSujet("Rappels sur les processus stochastiques","sujet47",2,enseignant23,false,false);
        Sujet sujet52 = createSujet("Rappels sur les processus stochastiques","sujet48",3,enseignant23,false,false);
        Sujet sujet53 = createSujet("Rappels sur les processus stochastiques","sujet49",4,enseignant23,false,false);

        Sujet sujet54 = createSujet("Rappels sur les processus stochastiques","sujet50",2,enseignant24,false,false);
        Sujet sujet55 = createSujet("Rappels sur les processus stochastiques","sujet51",3,enseignant24,false,false);
        Sujet sujet56 = createSujet("Rappels sur les processus stochastiques","sujet52",4,enseignant24,false,false);

        Sujet sujet57 = createSujet("Rappels sur les processus stochastiques","sujet53",2,enseignant25,false,false);
        Sujet sujet58 = createSujet("Rappels sur les processus stochastiques","sujet54",3,enseignant25,false,false);
        Sujet sujet59 = createSujet("Rappels sur les processus stochastiques","sujet55",4,enseignant25,false,false);

        Sujet sujet60 = createSujet("Rappels sur les processus stochastiques","sujet56",2,enseignant26,false,false);
        Sujet sujet61 = createSujet("Rappels sur les processus stochastiques","sujet57",3,enseignant26,false,false);
        Sujet sujet62 = createSujet("Rappels sur les processus stochastiques","sujet58",4,enseignant26,false,false);

        Sujet sujet63 = createSujet("Rappels sur les processus stochastiques","sujet56",2,enseignant27,false,false);
        Sujet sujet64 = createSujet("Rappels sur les processus stochastiques","sujet57",3,enseignant28,false,false);
        Sujet sujet65 = createSujet("Rappels sur les processus stochastiques","sujet58",4,enseignant28,false,false);
        Sujet sujet66 = createSujet("Rappels sur les processus stochastiques","sujet58",3,enseignant29,false,false);
        Sujet sujet67 = createSujet("Rappels sur les processus stochastiques","sujet58",5,enseignant29,false,false);
        Sujet sujet68 = createSujet("Rappels sur les processus stochastiques","sujet58",4,enseignant30,false,false);

        Sujet sujet69 = createSujet("Rayonnements directement ionisants et indirectement ionisants","Les TNE se développent au sein des organes ayant un rôle glandulaire ou dans un tissu voisin, à partir des cellules neuroendocrines. Elles sont disséminées dans le système neuroendocrine diffus, qui est composé des glandes neuroendocrines" +
                " et d’autres tissus tels que les poumons, l’appareil digestif ou encore la peau.",4,enseignant31,false,false);
        Sujet sujet70 = createSujet("Système de micro-cogénération et de ventilation adapté aux logements à très faibles besoins énergétiques","Les défis énergétiques et environnementaux actuels (réchauffement climatique, limitation des ressources énergétiques) appellent diverses solutions. Dans le secteur du bâtiment, ces solutions passent par une réduction des consommations d’énergie," +
                " en particulier pour les nouvelles constructions.",4,enseignant31,false,false);
        Sujet sujet71 = createSujet("Méthodes sismologiques pour l’étude de la fracturation dans les glaciers alpins"," On parle de mouvement gravitaire lorsqu’un volume d’un matériau quelconque (ce peut être de la roche, de la glace, de la neige, de la boue…) " +
                "est soumis à un déplacement sous l’effet de son propre poids.",4,enseignant31,false,false);

        Sujet sujet72 = createSujet("Interactions et mobilité des organes abdominaux sous sollicitations dynamiques","     D’un point de vue mécanique, la cavité abdomino-pelvienne dépend des parois qui la composent. Ces parois sont constituées d’un ensemble de muscles qui s’insèrent sur le rachis, le gril costal et le squelette du bassin. Ils existent sur tout le pourtour de la cavité abdominale sauf en bas où celle-ci communique avec la cavité pelvienne. Afin de faciliter la compréhension des parois abdominales, nous considérons que" +
                " l’abdomen a la forme d’un cylindre qui présente quatre parois (Tableau 1).",2,enseignant32,false,false);

        Sujet sujet73 = createSujet("Etude de la perception de la voix chez le patient sourd post lingual implanté cochléaire unilatéral"," première étape de transformation de l’énergie sonore : l’information reçue par le tympan va être transformée en information " +
                "mécanique et acheminée jusqu’à l’oreille interne.",3,enseignant32,false,false);

        Sujet sujet74 = createSujet("Méthodes numériques pour l’écoulement de Stokes 3D","Les mucus ne sont pas des fluides homogènes : ils sont essentiellement composés d’eau, comme tous les fluides  biologiques, ainsi que de plusieurs agents : protéines, particules inhalées, diverses cellules… Parmi" +
                " ces agents, une protéine joue un rôle clé : la mucine. Elle est sécrétée",1,enseignant32,false,false);

        Sujet sujet75 = createSujet("Evaluation de la technologie Li-ion","sujet70",2,enseignant33,false,false);
        Sujet sujet76 = createSujet("Evaluation de la technologie Li-ion","sujet71",3,enseignant33,false,false);
        Sujet sujet77 = createSujet("Evaluation de la technologie Li-ion","sujet72",4,enseignant34,false,false);
        Sujet sujet78 = createSujet("Evaluation de la technologie Li-ion","sujet73",1,enseignant35,false,false);
        Sujet sujet79 = createSujet("Evaluation de la technologie Li-ion","sujet74",3,enseignant36,false,false);
        Sujet sujet80 = createSujet("Evaluation de la technologie Li-ion","sujet75",2,enseignant37,false,false);
        Sujet sujet81 = createSujet("Evaluation de la technologie Li-ion","sujet76",1,enseignant37,false,false);
        Sujet sujet82 = createSujet("Evaluation de la technologie Li-ion","sujet77",3,enseignant37,false,false);
        Sujet sujet83 = createSujet("Evaluation de la technologie Li-ion","sujet78",2,enseignant38,false,false);
        Sujet sujet84 = createSujet("Evaluation de la technologie Li-ion","sujet79",1,enseignant39,false,false);
        Sujet sujet85 = createSujet("Evaluation de la technologie Li-ion","sujet80",3,enseignant39,false,false);
        Sujet sujet86 = createSujet("Evaluation de la technologie Li-ion","sujet81",2,enseignant40,false,false);

        Sujet sujet87 = createSujet("Système bancaire marocain","Système bancaire marocain Système bancaire marocain Système bancaire marocain",1,enseignant41,false,false);
        Sujet sujet88 = createSujet("Gestion du risque de crédit","Gestion du risque de crédit Gestion du risque de crédit Gestion du risque de crédit",2,enseignant41,false,false);
        Sujet sujet89 = createSujet("L’audit financier « cycle ventes/clients »","L’audit financier « cycle ventes/clients » Système bancaire marocain Système bancaire marocain",3,enseignant41,false,false);

        Sujet sujet90 = createSujet("Le Tourisme à la région","Le Tourisme à la régionLe Tourisme à la régionLe Tourisme à la région",2,enseignant42,false,false);
        Sujet sujet91 = createSujet("L’impact du budget sur la production","L’impact du budget sur la productionL’impact du budget sur la productionL’impact du budget sur la production",2,enseignant42,false,false);
        Sujet sujet92 = createSujet("La stratégie de fidélisation bancaire","La stratégie de fidélisation bancaireLa stratégie de fidélisation bancaireLa stratégie de fidélisation bancaire",2,enseignant42,false,false);

        Sujet sujet93 = createSujet("Le Tourisme à la région","Le Tourisme à la régionLe Tourisme à la régionLe Tourisme à la région",3,enseignant43,false,false);
        Sujet sujet94 = createSujet("L’impact du budget sur la production","L’impact du budget sur la productionL’impact du budget sur la productionL’impact du budget sur la production",2,enseignant43,false,false);
        Sujet sujet95 = createSujet("La stratégie de fidélisation bancaire","La stratégie de fidélisation bancaireLa stratégie de fidélisation bancaireLa stratégie de fidélisation bancaire",2,enseignant43,false,false);

        Sujet sujet96 = createSujet("Le Tourisme à la région","Le Tourisme à la régionLe Tourisme à la régionLe Tourisme à la région",1,enseignant44,false,false);
        Sujet sujet97 = createSujet("L’impact du budget sur la production","L’impact du budget sur la productionL’impact du budget sur la productionL’impact du budget sur la production",2,enseignant44,false,false);
        Sujet sujet98 = createSujet("La stratégie de fidélisation bancaire","La stratégie de fidélisation bancaireLa stratégie de fidélisation bancaireLa stratégie de fidélisation bancaire",2,enseignant45,false,false);

        Sujet sujet99 = createSujet("Le Tourisme à la région","Le Tourisme à la régionLe Tourisme à la régionLe Tourisme à la région",5,enseignant45,false,false);
        Sujet sujet100 = createSujet("L’impact du budget sur la production","L’impact du budget sur la productionL’impact du budget sur la productionL’impact du budget sur la production",2,enseignant45,false,false);
        Sujet sujet101 = createSujet("La stratégie de fidélisation bancaire","La stratégie de fidélisation bancaireLa stratégie de fidélisation bancaireLa stratégie de fidélisation bancaire",2,enseignant46,false,false);

        Sujet sujet102 = createSujet("Le Tourisme à la région","Le Tourisme à la régionLe Tourisme à la régionLe Tourisme à la région",3,enseignant47,false,false);
        Sujet sujet103 = createSujet("L’impact du budget sur la production","L’impact du budget sur la productionL’impact du budget sur la productionL’impact du budget sur la production",2,enseignant47,false,false);
        Sujet sujet104 = createSujet("La stratégie de fidélisation bancaire","La stratégie de fidélisation bancaireLa stratégie de fidélisation bancaireLa stratégie de fidélisation bancaire",2,enseignant48,false,false);

        Sujet sujet105 = createSujet("Le Tourisme à la région","Le Tourisme à la régionLe Tourisme à la régionLe Tourisme à la région",4,enseignant49,false,false);
        Sujet sujet106 = createSujet("L’impact du budget sur la production","L’impact du budget sur la productionL’impact du budget sur la productionL’impact du budget sur la production",2,enseignant50,false,false);
        Sujet sujet107 = createSujet("La stratégie de fidélisation bancaire","La stratégie de fidélisation bancaireLa stratégie de fidélisation bancaireLa stratégie de fidélisation bancaire",2,enseignant50,false,false);

        Sujet sujet108 = createSujet("Le prêt à intérêt en droit marocain","Le prêt à intérêt en droit marocain Le prêt à intérêt en droit marocain Le prêt à intérêt en droit marocain",2,enseignant51,false,false);
        Sujet sujet109 = createSujet("Le capital marque","Le capital marqueLe capital marqueLe capital marqueLe capital marque",3,enseignant51,false,false);
        Sujet sujet110 = createSujet("un objectif primordial","un objectif primordialun objectif primordialun objectif primordialun objectif primordial",4,enseignant51,false,false);
        Sujet sujet111 = createSujet("Le marketing social pour fidéliser les cadres","Le marketing social pour fidéliser les cadresLe marketing social pour fidéliser les cadresLe marketing social pour fidéliser les cadres",3,enseignant52,false,false);
        Sujet sujet112 = createSujet("La promotion est-elle un levier de fidélisation ","La promotion est-elle un levier de fidélisation La promotion est-elle un levier de fidélisation ",5,enseignant52,false,false);
        Sujet sujet113 = createSujet("Géomarketing : localisation commerciale multiple","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant53,false,false);

        Sujet sujet114 = createSujet("Mesure de l’efficacité publicitaire: Évaluation de la campagne publicitaire","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant54,false,false);
        Sujet sujet115 = createSujet("Stratégie de fidélisation dans le marketing des service","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant55,false,false);
        Sujet sujet116 = createSujet("L’appui de l’INDH aux associations de Laâyoune","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",3,enseignant55,false,false);
        Sujet sujet117 = createSujet("Investissement culturel et développement ","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant55,false,false);
        Sujet sujet118 = createSujet("Le développement communal cas des communes de la province d’Oujda","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",5,enseignant56,false,false);
        Sujet sujet119 = createSujet("La politique de restructuration des bidonvilles au Maroc","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant56,false,false);
        Sujet sujet120 = createSujet("La pratique de l’évaluation des actions publiques ","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant57,false,false);
        Sujet sujet121 = createSujet("La pratique de l’évaluation des actions publiques ","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",2,enseignant58,false,false);
        Sujet sujet122 = createSujet("La pratique de l’évaluation des actions publiques ","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant59,false,false);
        Sujet sujet123 = createSujet("Géomarketing : localisation commerciale multiple","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",3,enseignant59,false,false);
        Sujet sujet124 = createSujet("La pratique de l’évaluation des actions publiques ","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant60,false,false);

        Sujet sujet125 = createSujet("Les maladies endémiques dans le cercle de Matam"," La ruée européenne en Afrique dans la seconde moitié du XIXe siècle a eu des conséquences profondes sur les sociétés africaines. En effet, sous l’égide de la civilisation et de l’évangélisation, ",4,enseignant11,false,false);
        Sujet sujet126 = createSujet("Les facteurs de risques aux infections respiratoires aigues","Les infections respiratoires aigües comptent dans le monde entier parmi les maladies infantiles les plus courantes. Elles constituent partout la première ou la deuxième cause de présentation de jeune enfant dans les structures sanitaires. ",2,enseignant11,false,false);
        Sujet sujet127 = createSujet("Vaccination des patients atteints de maladies auto-immunes systémiques en Martinique","La vaccination prévient 2 à 3 millions de décès dans le monde chaque année (1). Chez les patients atteints de maladies auto-immunes (MAI) elle a fait preuve de son efficacité en réduisant l’incidence de certaines infections (2).",4,enseignant12,false,false);
        Sujet sujet128 = createSujet("Transferts tendineux pour la prise en charge du pied varus équin post-AVC","L’insuffisance respiratoire aiguë (IRA) est la défaillance d’un ou plusieurs composants du système respiratoire, entrainant une altération aigüe de l’hématose.",3,enseignant13,false,false);
        Sujet sujet129 = createSujet("L’insuffisance respiratoire aiguë (IRA)","En France, on dénombre chaque année plus de 140 000 nouveaux cas d’accidents vasculaires cérébraux, soit un toutes les quatre minutes. L’AVC représente la première cause de handicap physique acquis de l’adulte.",4,enseignant13,false,false);
        Sujet sujet130 = createSujet("Généralités sur la téléphonie mobile en santé","Généralités sur la téléphonie mobile en santé",5,enseignant14,false,false);
        Sujet sujet131 = createSujet("Généralités sur la téléphonie mobile en santé ","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",5,enseignant14,false,false);
        Sujet sujet132 = createSujet("L’insuffisance respiratoire aiguë (IRA)","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",2,enseignant14,false,false);
        Sujet sujet133 = createSujet("Les maladies endémiques dans le cercle de Matam","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",1,enseignant15,false,false);
        Sujet sujet134 = createSujet("Transferts tendineux pour la prise en charge du pied varus équin post-AVC","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant16,false,false);
        Sujet sujet135 = createSujet("Vaccination des patients atteints de maladies auto-immunes systémiques en Martinique","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant17,false,false);
        Sujet sujet136 = createSujet("Les maladies endémiques dans le cercle de Matam ","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",3,enseignant18,false,false);
        Sujet sujet137 = createSujet("Les maladies endémiques dans le cercle de Matam","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant19,false,false);
        Sujet sujet138 = createSujet("Généralités sur la téléphonie mobile en santé ","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",2,enseignant20,false,false);

        Sujet sujet139 = createSujet("L’insuffisance respiratoire aiguë (IRA)","En France, on dénombre chaque année plus de 140 000 nouveaux cas d’accidents vasculaires cérébraux, soit un toutes les quatre minutes. L’AVC représente la première cause de handicap physique acquis de l’adulte.",4,enseignant61,false,false);
        Sujet sujet140 = createSujet("Généralités sur la téléphonie mobile en santé","Généralités sur la téléphonie mobile en santé",2,enseignant62,false,false);
        Sujet sujet141 = createSujet("Généralités sur la téléphonie mobile en santé ","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",5,enseignant63,false,false);
        Sujet sujet142 = createSujet("L’insuffisance respiratoire aiguë (IRA)","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",2,enseignant64,false,false);
        Sujet sujet143 = createSujet("Les maladies endémiques dans le cercle de Matam","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",1,enseignant65,false,false);
        Sujet sujet144 = createSujet("Transferts tendineux pour la prise en charge du pied varus équin post-AVC","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant66,false,false);
        Sujet sujet145 = createSujet("Vaccination des patients atteints de maladies auto-immunes systémiques en Martinique","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant67,false,false);
        Sujet sujet146 = createSujet("Les maladies endémiques dans le cercle de Matam ","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",3,enseignant68,false,false);
        Sujet sujet147 = createSujet("Les maladies endémiques dans le cercle de Matam","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",4,enseignant69,false,false);
        Sujet sujet148 = createSujet("Généralités sur la téléphonie mobile en santé ","Géomarketing : localisation commerciale multipleGéomarketing : localisation commerciale multiple",2,enseignant70,false,false);


        logger.info("Sujets created.");

        logger.info("Creating groupes...");
        Equipe groupeInfo = createEquipe(2,sujet,"ha");
        Equipe groupeInfo2 = createEquipe(3,sujet2,"ha");
        Equipe groupeInfo3= createEquipe(3,sujet3,"ha");
        Equipe groupeInfo4 = createEquipe(3,sujet7,"ha");

        Equipe groupeMath = createEquipe(3,sujet45,"ha");
        Equipe groupeMath2 = createEquipe(2,sujet46,"ha");
        Equipe groupeMath3= createEquipe(4,sujet49,"ha");
        Equipe groupeMath4 = createEquipe(3,sujet50,"ha");

        Equipe groupePhysique = createEquipe(4,sujet69,"ha");
        Equipe groupePhysique2 = createEquipe(2,sujet72,"ha");
        Equipe groupePhysique3 = createEquipe(1,sujet74,"ha");
        Equipe groupePhysique4 = createEquipe(3,sujet73,"ha");

        Equipe groupeMathFincance = createEquipe(1,sujet87,"ha");
        Equipe groupeMathFincance2 = createEquipe(2,sujet90,"ha");
        Equipe groupeMathFincance3 = createEquipe(2,sujet92,"ha");
        Equipe groupeMathFincance4 = createEquipe(3,sujet88,"ha");

        Equipe groupeMathDroite = createEquipe(2,sujet108,"ha");
        Equipe groupeMathDroite2 = createEquipe(4,sujet110,"ha");
        Equipe groupeMathDroite3 = createEquipe(5,sujet112,"ha");
        Equipe groupeMathDroite4 = createEquipe(4,sujet114,"ha");

        Equipe groupeMed = createEquipe(4,sujet125,"ha");
        Equipe groupeMed2 = createEquipe(2,sujet126,"ha");
        Equipe groupeMed3 = createEquipe(3,sujet128,"ha");
        Equipe groupeMed4 = createEquipe(5,sujet130,"ha");

        Equipe groupeMed5 = createEquipe(4,sujet139,"ha");
        Equipe groupeMed6 = createEquipe(2,sujet140,"ha");
        Equipe groupeMed7 = createEquipe(4,sujet147,"ha");
        Equipe groupeMed8 = createEquipe(2,sujet148,"ha");



        logger.info("Group created.");

        logger.info("Adding students to group...");
        groupeInfo = addEtudiantToEquipe(etudiantInfo1, groupeInfo);
        groupeInfo = addEtudiantToEquipe(etudiantInfo2, groupeInfo);
        groupeInfo2 = addEtudiantToEquipe(etudiantInfo3, groupeInfo2);
        groupeInfo2 = addEtudiantToEquipe(etudiantInfo4, groupeInfo2);
        groupeInfo2 = addEtudiantToEquipe(etudiantInfo1, groupeInfo2);
        groupeInfo3 = addEtudiantToEquipe(etudiantInfo5, groupeInfo3);
        groupeInfo3 = addEtudiantToEquipe(etudiantInfo2, groupeInfo3);
        groupeInfo3 = addEtudiantToEquipe(etudiantInfo3, groupeInfo3);
        groupeInfo4 = addEtudiantToEquipe(etudiantInfo4, groupeInfo4);
        groupeInfo4 = addEtudiantToEquipe(etudiantInfo5, groupeInfo4);
        groupeInfo4 = addEtudiantToEquipe(etudiantInfo1, groupeInfo4);


        groupeMath = addEtudiantToEquipe(etudiantMath1, groupeMath);
        groupeMath = addEtudiantToEquipe(etudiantMath3, groupeMath);
        groupeMath = addEtudiantToEquipe(etudiantMath4, groupeMath);
        groupeMath2 = addEtudiantToEquipe(etudiantMath2, groupeMath2);
        groupeMath2 = addEtudiantToEquipe(etudiantMath5, groupeMath2);
        groupeMath3 = addEtudiantToEquipe(etudiantMath2, groupeMath3);
        groupeMath3 = addEtudiantToEquipe(etudiantMath5, groupeMath3);
        groupeMath3 = addEtudiantToEquipe(etudiantMath3, groupeMath3);
        groupeMath3 = addEtudiantToEquipe(etudiantMath1, groupeMath3);
        groupeMath4 = addEtudiantToEquipe(etudiantMath2, groupeMath4);
        groupeMath4 = addEtudiantToEquipe(etudiantMath3, groupeMath4);
        groupeMath4 = addEtudiantToEquipe(etudiantMath1, groupeMath4);

        groupePhysique = addEtudiantToEquipe(etudiantPC1, groupePhysique);
        groupePhysique = addEtudiantToEquipe(etudiantPC4, groupePhysique);
        groupePhysique = addEtudiantToEquipe(etudiantPC6, groupePhysique);
        groupePhysique = addEtudiantToEquipe(etudiantPC2, groupePhysique);
        groupePhysique2 = addEtudiantToEquipe(etudiantPC3, groupePhysique2);
        groupePhysique2 = addEtudiantToEquipe(etudiantPC5, groupePhysique2);
        groupePhysique3 = addEtudiantToEquipe(etudiantPC1, groupePhysique3);
        groupePhysique4 = addEtudiantToEquipe(etudiantPC1, groupePhysique4);
        groupePhysique4 = addEtudiantToEquipe(etudiantPC3, groupePhysique4);
        groupePhysique4 = addEtudiantToEquipe(etudiantPC5, groupePhysique4);

        groupeMathFincance = addEtudiantToEquipe(etudiantEco1, groupeMathFincance);
        groupeMathFincance2 = addEtudiantToEquipe(etudiantEco2, groupeMathFincance2);
        groupeMathFincance2 = addEtudiantToEquipe(etudiantEco3, groupeMathFincance2);
        groupeMathFincance3 = addEtudiantToEquipe(etudiantEco4, groupeMathFincance3);
        groupeMathFincance3 = addEtudiantToEquipe(etudiantEco5, groupeMathFincance3);
        groupeMathFincance4 = addEtudiantToEquipe(etudiantEco1, groupeMathFincance4);
        groupeMathFincance4 = addEtudiantToEquipe(etudiantEco2, groupeMathFincance4);
        groupeMathFincance4 = addEtudiantToEquipe(etudiantEco3, groupeMathFincance4);

        groupeMathDroite = addEtudiantToEquipe(etudiantDroit1, groupeMathDroite);
        groupeMathDroite = addEtudiantToEquipe(etudiantDroit2, groupeMathDroite);
        groupeMathDroite2 = addEtudiantToEquipe(etudiantDroit3, groupeMathDroite2);
        groupeMathDroite2 = addEtudiantToEquipe(etudiantDroit4, groupeMathDroite2);
        groupeMathDroite2 = addEtudiantToEquipe(etudiantDroit5, groupeMathDroite2);
        groupeMathDroite2 = addEtudiantToEquipe(etudiantDroit1, groupeMathDroite2);
        groupeMathDroite3 = addEtudiantToEquipe(etudiantDroit2, groupeMathDroite3);
        groupeMathDroite3 = addEtudiantToEquipe(etudiantDroit3, groupeMathDroite3);
        groupeMathDroite3 = addEtudiantToEquipe(etudiantDroit4, groupeMathDroite3);
        groupeMathDroite3 = addEtudiantToEquipe(etudiantDroit5, groupeMathDroite3);
        groupeMathDroite3 = addEtudiantToEquipe(etudiantDroit1, groupeMathDroite3);
        groupeMathDroite4 = addEtudiantToEquipe(etudiantDroit2, groupeMathDroite4);
        groupeMathDroite4 = addEtudiantToEquipe(etudiantDroit3, groupeMathDroite4);
        groupeMathDroite4 = addEtudiantToEquipe(etudiantDroit4, groupeMathDroite4);
        groupeMathDroite4 = addEtudiantToEquipe(etudiantDroit5, groupeMathDroite4);

        groupeMed= addEtudiantToEquipe(etudiantMed1, groupeMed);
        groupeMed = addEtudiantToEquipe(etudiantMed2, groupeMed);
        groupeMed = addEtudiantToEquipe(etudiantMed3, groupeMed);
        groupeMed = addEtudiantToEquipe(etudiantMed4, groupeMed);
        groupeMed2 = addEtudiantToEquipe(etudiantMed3, groupeMed2);
        groupeMed2 = addEtudiantToEquipe(etudiantMed4, groupeMed2);
        groupeMed3 = addEtudiantToEquipe(etudiantMed1, groupeMed3);
        groupeMed3 = addEtudiantToEquipe(etudiantMed2, groupeMed3);
        groupeMed3 = addEtudiantToEquipe(etudiantMed3, groupeMed3);
        groupeMed4 = addEtudiantToEquipe(etudiantMed4, groupeMed4);
        groupeMed4 = addEtudiantToEquipe(etudiantMed1, groupeMed4);
        groupeMed4 = addEtudiantToEquipe(etudiantMed2, groupeMed4);
        groupeMed4 = addEtudiantToEquipe(etudiantMed3, groupeMed4);
        groupeMed4 = addEtudiantToEquipe(etudiantMed6, groupeMed4);

        groupeMed5 = addEtudiantToEquipe(etudiantMed1, groupeMed5);
        groupeMed5 = addEtudiantToEquipe(etudiantMed2, groupeMed5);
        groupeMed5 = addEtudiantToEquipe(etudiantMed3, groupeMed5);
        groupeMed5 = addEtudiantToEquipe(etudiantMed4, groupeMed5);

        groupeMed6 = addEtudiantToEquipe(etudiantMed1, groupeMed6);
        groupeMed6 = addEtudiantToEquipe(etudiantMed2, groupeMed6);
        groupeMed7 = addEtudiantToEquipe(etudiantMed3, groupeMed7);
        groupeMed7 = addEtudiantToEquipe(etudiantMed4, groupeMed7);
        groupeMed7 = addEtudiantToEquipe(etudiantMed1, groupeMed7);
        groupeMed7 = addEtudiantToEquipe(etudiantMed6, groupeMed7);

        groupeMed8 = addEtudiantToEquipe(etudiantMed1, groupeMed8);
        groupeMed8 = addEtudiantToEquipe(etudiantMed2, groupeMed8);

        logger.info("Students added to group.");

        logger.info("creating soutenances..");
        Soutenance soutenance = createSoutenance(new Date(), sujet);

        logger.info("adding jury to group...");
        Jury jury = addJurytoSoutenance(enseignant, soutenance, "President");
        Jury jury2 = addJurytoSoutenance(enseignant4, soutenance, "Invite");
        Jury jury3 = addJurytoSoutenance(enseignant3, soutenance, "Rapporteur");
        logger.info("jury added to group.");

        logger.info("Testing mails");
//        emailSender.ShareLienDriveJury("abdellah.samourail@gmail.com",new Date(), "youtube.com");
    }

    @Transactional
    public Soutenance createSoutenance(Date date, Sujet s) {
        Soutenance soutenance = new Soutenance();
        soutenance.setIdSoutenance(utils.generateUserId(32));
        soutenance.setDateSoutenance(date);
        soutenance.setSujet(s);
        soutenance.setEnded(false);
        soutenance = soutenanceRepository.save(soutenance);
        return soutenance;
    }

    @Transactional
    public Jury addJurytoSoutenance(Enseignant enseignant, Soutenance soutenance, String typeJury) {
        Jury jury = new Jury();
        jury.setIdJury(utils.generateUserId(32));
        jury.setEnseignant(enseignant);
        jury.setSoutenance(soutenance);
        jury.setTypeJury(typeJury);
        jury = juryRepository.save(jury);
        return jury;
    }


    @Transactional
    public Universite createUniversite(String nomUniversite, String adresse) {
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);
        if (universite == null) {
            universite = new Universite();
            universite.setIdUniversite(utils.generateUserId(32));
            universite.setNomUniversite(nomUniversite);
            universite.setAdresse(adresse);
            universite = universiteRepository.save(universite);
        }
        return universite;
    }

    @Transactional
    public Etablissement createEtablissement(String nomEtablissement, Universite universite) {
        Etablissement etablissement = etablissementRepository.findByNomEtablissement(nomEtablissement);
        if (etablissement == null) {
            etablissement = new Etablissement();
            etablissement.setIdEtablissement(utils.generateUserId(32));
            etablissement.setNomEtablissement(nomEtablissement);
            etablissement.setAdresse("Adresse 111111");
            etablissement.setUniversite(universite);
            logger.info("Etablissement " + etablissement.getNomEtablissement() + " " + etablissement.getIdEtablissement() + " created.");
            etablissement = etablissementRepository.save(etablissement);
        }
        return etablissement;
    }

    @Transactional
    public Equipe createEquipe(int tailleEquipe, Sujet sujet, String driveLink) {
        Equipe equipe = equipeRepository.findByIdEquipe(utils.generateUserId(32));
        if (equipe == null) {
            equipe = new Equipe();
            equipe.setIdEquipe(utils.generateUserId(32));
            equipe.setTailleEquipe(tailleEquipe);
            equipe.setIsPrivate(false);
            equipe.setCryptedPassword("123456");
            equipe.setSujet(sujet);
            if (driveLink != null)
                equipe.setDriveLink("https://drive.google.com/file/d/1zbRP86HsOvicB1YrzPCn2hQv_AGX5Vm3/view?usp=sharing");
            equipe = equipeRepository.save(equipe);
        }
        return equipe;
    }

    @Transactional
    public Equipe addEtudiantToEquipe(Etudiant etudiant, Equipe equipe) {
        if (equipe.getEtudiant() == null) {
            equipe.setEtudiant(new ArrayList<>());
        }
        equipe.getEtudiant().add(etudiant);
        equipe = equipeRepository.save(equipe);
        return equipe;
    }

    @Transactional
    public Sujet createSujet(String s, String s1, int tailleEquipe, Enseignant encadrant, Boolean locked, Boolean done) {
        Sujet sujet = sujetRepository.findByNomSujet(s);
        if (sujet == null) {
            sujet = new Sujet();
            sujet.setIdSujet(utils.generateUserId(32));
            sujet.setNomSujet(s);
            sujet.setDescriptionSujet(s1);
            sujet.setTailleEquipe(tailleEquipe);
            sujet.setEncadrant(encadrant);
            sujet.setLocked(locked);
            sujet.setDone(done);
            sujet = sujetRepository.save(sujet);
        }
        return sujet;
    }


    @Transactional
    public Departement createDepartement(String departementName, Etablissement etablissement) {
        Departement departement = departementRepository.findByNomDepartement(departementName);
        if (departement == null) {
            departement = new Departement();
            departement.setIdDepartement(utils.generateUserId(32));
            departement.setNomDepartement(departementName);
            departement.setEtablissement(etablissement);
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
    public Administrateur createAdministrateur2(Role role) {
        Administrateur administrateur = adminRepository.findByEmail("abdellah.samourail@prof.com");
        if (administrateur == null) {
            administrateur = new Administrateur();
            administrateur.setCin("kb19022");
            administrateur.setNom("lakhssassi");
            administrateur.setPrenom("Abdellah");
            administrateur.setEmail("hgarmouche@gmail.com");
            administrateur.setIdAdmin(utils.generateUserId(32));
            administrateur.setEncryptedPassword(bCryptPasswordEncoder.encode("654321"));
            administrateur.setRole(role);
            administrateur = adminRepository.save(administrateur);
        }
        return administrateur;
    }

    @Transactional
    public Domaine createDomaine(String s, boolean b,Universite universite) {
        Domaine domaine = domaineRepository.findByNomDomaine(s);
        if (domaine == null) {
            domaine = new Domaine();
            domaine.setEtudiant(b);
            domaine.setNomDomaine(s);
            domaine.setUniversite(universite);
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
    public Enseignant createEnseignant(String mail, Role enseignantRole, Departement departement) {
        Enseignant enseignant = enseignantRepository.findByEmail("abdellah.samourail@prof.com");
        if (enseignant == null) {
            enseignant = new Enseignant();

            enseignant.setCin("kb19022");
            enseignant.setNom("lakhssassi");
            enseignant.setPrenom("Abdellah");
            enseignant.setEmail(mail);
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
