package com.example.gestionpfe.Services;


public interface SchedulerService {
    void rappelRendezVous();
    void rappelSoutenance();
    void deleteRendezVousValide();
    void deleteSoutenanceValide();
}
