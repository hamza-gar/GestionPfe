package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.SoutenanceDto;

import java.util.List;

public interface SoutenanceService {
    SoutenanceDto addSoutenance(String username, SoutenanceDto soutenanceDto, String idSujet);
    SoutenanceDto getSoutenanceByIdSoutenance(String username,String id);
    Boolean inviteJurys(String username,String roleJury ,String emailJury,SoutenanceDto soutenanceDto);
    SoutenanceDto updateDateSoutenance(String username,SoutenanceDto soutenanceDto);
    SoutenanceDto updateSoutenance(String id, SoutenanceDto soutenanceDto);
    void deleteSoutenance(String id);
    List<SoutenanceDto> getAllSoutenance(String username, int page, int limit);
}
