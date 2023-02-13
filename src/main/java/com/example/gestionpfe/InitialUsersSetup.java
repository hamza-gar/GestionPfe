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
        logger.info("Super Admin created.");


        logger.info("Creating Enseignants...");
        Enseignant enseignant = createEnseignant("abdellah.lakhssassi@prof.com", enseignantRole, departement);
        Enseignant enseignant2 = createEnseignant("ens@hotmail.com", enseignantRole, departement);
        Enseignant enseignant3 = createEnseignant("ens2@gmail.com", enseignantRole, departement);
        Enseignant enseignant4 = createEnseignant("ens3@etu.uae.ac.ma", enseignantRole, departement);
        Enseignant enseignant5 = createEnseignant("medecin1@prof.com", enseignantRole, departement6);
        Enseignant enseignant6 = createEnseignant("medecin2@prof.com", enseignantRole, departement7);
        Enseignant enseignant7 = createEnseignant("medecin3@prof.com", enseignantRole, departement7);
        Enseignant enseignant8 = createEnseignant("medecin4@prof.com", enseignantRole, departement6);
        Enseignant enseignant9 = createEnseignant("economist1@prof.com", enseignantRole, departement5);
        Enseignant enseignant10 = createEnseignant("economist2@prof.com", enseignantRole, departement5);
        Enseignant enseignant11 = createEnseignant("economist3@prof.com", enseignantRole, departement4);
        Enseignant enseignant12 = createEnseignant("ekdiha@prof.com", enseignantRole, departement2);
        Enseignant enseignant13 = createEnseignant("zitan@prof.com", enseignantRole, departement2);
        Enseignant enseignant14 = createEnseignant("kamili@prof.com", enseignantRole, departement3);
        logger.info("Enseignants created.");


        logger.info("Creating Filieres...");
        Filiere filiere = createFiliere("Informatique", enseignant, departement);
        Filiere filiereMath = createFiliere("Mathematique", enseignant12, departement2);
        Filiere filierePhysique = createFiliere("Physique", enseignant14, departement3);
        Filiere filiereEconomie = createFiliere("Economie", enseignant11, departement4);
        Filiere filiereDroit = createFiliere("Droit", enseignant9, departement5);
        Filiere filiereMedecineGeneral = createFiliere("Ancologie", enseignant7, departement7);
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
        Etudiant etudiantEco4 = createEtudiant("droitetu@etu.com", etudiantRole, filiereDroit);
        Etudiant etudiantEco5 = createEtudiant("droitetu1@etu.com", etudiantRole, filiereDroit);
        Etudiant etudiantEco6 = createEtudiant("droitetu2@etu.com", etudiantRole, filiereDroit);
        Etudiant etudiantMath1 = createEtudiant("mathetu@etu.com", etudiantRole, filiereMath);
        Etudiant etudiantMath2 = createEtudiant("mathetu2@etu.com", etudiantRole, filiereMath);
        Etudiant etudiantPC1 = createEtudiant("pcetu@etu.com", etudiantRole, filierePhysique);
        Etudiant etudiantPC2 = createEtudiant("pcetu2@etu.com", etudiantRole, filierePhysique);
        Etudiant etudiantPC3 = createEtudiant("pcetu3@etu.com", etudiantRole, filierePhysique);
        Etudiant etudiantPC4 = createEtudiant("pcetu4@etu.com", etudiantRole, filierePhysique);
        Etudiant etudiantMed1 = createEtudiant("medetu@etu.com", etudiantRole, filiereMedecineGeneral);
        Etudiant etudiantMed2 = createEtudiant("medetu2@etu.com", etudiantRole, filiereMedecineGeneral);
        Etudiant etudiantMed3 = createEtudiant("medetu3@etu.com", etudiantRole, filiereMedecineGeneral);
        Etudiant etudiantMed4 = createEtudiant("medetu4@etu.com", etudiantRole, filiereMedecineGeneral);

        logger.info("Etudiants created.");


        logger.info("Creating subjects...");
        Sujet sujet = createSujet("Chess AI", "Sujet 1", 2, enseignant2, true, true);
        Sujet sujet1 = createSujet("Google Map API", "Sujet 1", 4, enseignant2, true, false);
        Sujet sujet2 = createSujet("Gestion de Stock", "Sujet 1", 3, enseignant2, false, false);
        Sujet sujet3 = createSujet("Gestion pfe", "Sujet 1", 3, enseignant3, false, false);
        Sujet sujet4 = createSujet("Robot cuisinier", "Sujet 1", 2, enseignant2, true, true);
        Sujet sujet5 = createSujet("System de securite de porte", "Sujet 1", 3, enseignant2, false, false);
        Sujet sujet6 = createSujet("Gestion de Location de voiture", "Sujet 1", 3, enseignant3, false, false);
        Sujet sujet7 = createSujet("Jeux 2D", "Sujet 1", 2, enseignant4, false, false);
        Sujet sujetMed1 = createSujet("Cancer de la prostate", "Sujet 1", 3, enseignant5, false, false);
        Sujet sujetMed2 = createSujet("Cancer du poumon", "Sujet 1", 3, enseignant5, false, false);
        Sujet sujetMed3 = createSujet("Cancer du colon", "Sujet 1", 2, enseignant6, false, false);
        Sujet sujetMed4 = createSujet("Cancer du foie", "Sujet 1", 3, enseignant6, false, false);
        Sujet sujetMed5 = createSujet("Cancer du rein", "Sujet 1", 3, enseignant7, false, false);
        Sujet sujetEco1 = createSujet("Recherche sur l'economie", "Sujet 1", 2, enseignant9, false, false);
        Sujet sujetEco2 = createSujet("Recherche sur la sociologie", "Sujet 1", 3, enseignant9, false, false);
        Sujet sujetMath1 = createSujet("Algebre", "Sujet 1", 3, enseignant12, false, false);
        Sujet sujetMath2 = createSujet("Analyse", "Sujet 1", 2, enseignant12, false, false);
        Sujet sujetMath3 = createSujet("Calcul", "Sujet 1", 3, enseignant12, false, false);
        Sujet sujetPC1 = createSujet("Physique", "Sujet 1", 3, enseignant14, false, false);
        Sujet sujetPC2 = createSujet("Chimie", "Sujet 1", 2, enseignant14, false, false);
        logger.info("Sujets created.");

        logger.info("Creating groupes...");
        Equipe groupe = createEquipe(2, sujet, "ha");
        Equipe groupe2 = createEquipe(2, sujet1, "ha");
        Equipe groupe3 = createEquipe(3, sujet2, "ha");
        Equipe groupe4 = createEquipe(3, sujet3, "ha");
        Equipe groupe5 = createEquipe(2, sujet4, "ha");
        Equipe groupe6 = createEquipe(3, sujet5, "ha");
        Equipe groupe7 = createEquipe(3, sujet6, "ha");
        Equipe groupe8 = createEquipe(2, sujet7, "ha");
        Equipe groupe9 = createEquipe(3, sujetMed1, "ha");
        Equipe groupe10 = createEquipe(3, sujetMed2, "ha");
        Equipe groupe11 = createEquipe(2, sujetMed3, "ha");
        Equipe groupe12 = createEquipe(3, sujetMed4, "ha");
        Equipe groupe13 = createEquipe(3, sujetMed5, "ha");
        Equipe groupe14 = createEquipe(2, sujetEco1, "ha");
        Equipe groupe15 = createEquipe(3, sujetEco2, "ha");
        Equipe groupe16 = createEquipe(3, sujetMath1, "ha");
        Equipe groupe17 = createEquipe(2, sujetMath2, "ha");
        Equipe groupe18 = createEquipe(3, sujetMath3, "ha");
        Equipe groupe19 = createEquipe(3, sujetPC1, "ha");
        Equipe groupe20 = createEquipe(2, sujetPC2, "ha");

        logger.info("Group created.");

        logger.info("Adding students to group...");
        groupe = addEtudiantToEquipe(etudiantInfo1, groupe);
        groupe = addEtudiantToEquipe(etudiantInfo2, groupe);
        groupe2 = addEtudiantToEquipe(etudiantInfo3, groupe2);
        groupe2 = addEtudiantToEquipe(etudiantInfo4, groupe2);
        groupe2 = addEtudiantToEquipe(etudiantInfo1, groupe2);
        groupe3 = addEtudiantToEquipe(etudiantInfo5, groupe3);
        groupe3 = addEtudiantToEquipe(etudiantInfo2, groupe3);
        groupe3 = addEtudiantToEquipe(etudiantInfo3, groupe3);
        groupe4 = addEtudiantToEquipe(etudiantInfo4, groupe4);
        groupe4 = addEtudiantToEquipe(etudiantInfo5, groupe4);
        groupe4 = addEtudiantToEquipe(etudiantInfo1, groupe4);
        groupe5 = addEtudiantToEquipe(etudiantInfo2, groupe5);
        groupe9 = addEtudiantToEquipe(etudiantMed1, groupe9);
        groupe9 = addEtudiantToEquipe(etudiantMed2, groupe9);
        groupe10 = addEtudiantToEquipe(etudiantMed3, groupe10);
        groupe10 = addEtudiantToEquipe(etudiantMed4, groupe10);
        groupe11 = addEtudiantToEquipe(etudiantMed1, groupe11);
        groupe11 = addEtudiantToEquipe(etudiantMed2, groupe11);
        groupe12 = addEtudiantToEquipe(etudiantMed3, groupe12);
        groupe12 = addEtudiantToEquipe(etudiantMed4, groupe12);
        groupe13 = addEtudiantToEquipe(etudiantMed1, groupe13);
        groupe13 = addEtudiantToEquipe(etudiantMed2, groupe13);
        groupe14 = addEtudiantToEquipe(etudiantEco1, groupe14);
        groupe14 = addEtudiantToEquipe(etudiantEco2, groupe14);
        groupe15 = addEtudiantToEquipe(etudiantEco1, groupe15);
        groupe15 = addEtudiantToEquipe(etudiantEco2, groupe15);
        groupe16 = addEtudiantToEquipe(etudiantMath1, groupe16);
        groupe16 = addEtudiantToEquipe(etudiantMath2, groupe16);
        groupe17 = addEtudiantToEquipe(etudiantMath1, groupe17);
        groupe17 = addEtudiantToEquipe(etudiantMath2, groupe17);
        groupe18 = addEtudiantToEquipe(etudiantMath1, groupe18);
        groupe18 = addEtudiantToEquipe(etudiantMath2, groupe18);
        groupe19 = addEtudiantToEquipe(etudiantPC1, groupe19);
        groupe19 = addEtudiantToEquipe(etudiantPC2, groupe19);
        groupe20 = addEtudiantToEquipe(etudiantPC1, groupe20);

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
