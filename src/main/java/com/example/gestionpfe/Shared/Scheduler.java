package com.example.gestionpfe.Shared;

import com.example.gestionpfe.Dto.EtudiantDto;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Repositories.EtudiantRepository;
import com.example.gestionpfe.Services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduler {

    @Autowired
    EmailSender emailSender;

    @Autowired
    EtudiantRepository etudiantRepository;

    /*TODO:
     * + Check if the date of the rendezvous is passed : if yes and some of the students did not vote -> reset votes + date + resend mail to notify everyone.
     * if the date has passed and all the students voted yes -> delete it from the database.
     *
     * */

    // 0 0 6 * * * : every day at 6:00 AM
    @Scheduled(cron = "0 0 6 * * *")
    public void runTask() {
        List<Etudiant> etudiants = (List<Etudiant>) etudiantRepository.findAll();

        for(Etudiant etudiant : etudiants){
            if(etudiant.getEquipe() != null){
                emailSender.notifyYOUAREINATEAM(etudiant.getEmail());
            }
        }

    }
}
