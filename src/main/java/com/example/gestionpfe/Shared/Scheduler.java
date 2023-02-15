package com.example.gestionpfe.Shared;

import com.example.gestionpfe.Services.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduler {

    @Autowired
    SchedulerService schedulerService;

    // 0 0 5 * * * : every day at 5:00 AM
    //cron = "0 0 5 * * *"
    @Scheduled(cron = "0 0 5 * * *")
    public void runTasks() {
        schedulerService.deleteRendezVousValide();
        schedulerService.deleteSoutenanceValide();
        schedulerService.rappelRendezVous();
        schedulerService.rappelSoutenance();
    }
}
