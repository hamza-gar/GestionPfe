package com.example.gestionpfe.ServiceImpl;


import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Entities.Rendezvous;
import com.example.gestionpfe.Entities.Soutenance;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.EtudiantRepository;
import com.example.gestionpfe.Repositories.RendezvousRepository;
import com.example.gestionpfe.Repositories.SoutenanceRepository;
import com.example.gestionpfe.Services.SchedulerService;
import com.example.gestionpfe.Shared.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    RendezvousRepository rendezvousRepository;

    @Autowired
    SoutenanceRepository soutenanceRepository;

    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    EnseignantRepository enseignantRepository;

    @Autowired
    EmailSender mailSender;

    @Override
    public void rappelRendezVous() {
        Date today = new Date();
        List<Rendezvous> rendezvousList = (List<Rendezvous>) rendezvousRepository.findAllByDateRendezvous(today);
        for (Rendezvous rendezvous : rendezvousList) {
            for (Etudiant et : rendezvous.getEquipe().getEtudiant()) {
                mailSender.RendezVousReminder(et.getEmail(), rendezvous.getDateRendezvous());
            }
        }
    }

    @Override
    public void rappelSoutenance() {
        Date today = new Date();
        List<Soutenance> soutenanceList = soutenanceRepository.findAllByDateSoutenance(today);
        for (Soutenance soutenance : soutenanceList) {
            for (Etudiant et : soutenance.getSujet().getEquipe().get(0).getEtudiant()) {
                mailSender.SoutenanceReminder(et.getEmail(), soutenance.getDateSoutenance());
            }
        }
    }


    @Override
    public void deleteRendezVousValide() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();
        List<Rendezvous> rendezvousList = (List<Rendezvous>) rendezvousRepository.findAllByDateRendezvous(yesterday);

        for (Rendezvous rendezvous : rendezvousList) {
            if (rendezvous.getFixed()) {
                rendezvousRepository.delete(rendezvous);
            } else {
                mailSender.RendezVousNonPrisEns(rendezvous.getEncadrant().getEmail(), rendezvous.getEquipe().getSujet().getNomSujet());
                for (Etudiant et : rendezvous.getEquipe().getEtudiant()) {
                    mailSender.RendezVousNonPrisEt(et.getEmail());
                }
                rendezvous.setVote(0);
                rendezvous.setFlags("");
                rendezvous.setDateRendezvous(null);
                rendezvous.setFixed(false);
                rendezvousRepository.save(rendezvous);
            }
        }
    }

    @Override
    public void deleteSoutenanceValide() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();
        List<Soutenance> soutenanceList = (List<Soutenance>) soutenanceRepository.findAllByDateSoutenance(yesterday);

        for (Soutenance soutenance : soutenanceList) {
            soutenanceRepository.delete(soutenance);
        }
    }
}
