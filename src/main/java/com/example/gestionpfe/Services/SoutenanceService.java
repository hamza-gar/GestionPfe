package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.SoutenanceDto;

import java.util.List;

public interface SoutenanceService {
    SoutenanceDto addSoutenance(String username);
    SoutenanceDto getSoutenanceByIdSoutenance(String username,String id);
    SoutenanceDto updateSoutenance(String id, SoutenanceDto soutenanceDto);
    void deleteSoutenance(String id);
    List<SoutenanceDto> getAllSoutenance(String username, int page, int limit);
}
