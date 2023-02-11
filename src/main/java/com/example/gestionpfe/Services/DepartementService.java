package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.AdminDto;
import com.example.gestionpfe.Dto.DepartementDto;

import java.util.List;

public interface DepartementService {
    DepartementDto addDepartement(DepartementDto departementDTO);

    DepartementDto getDepartement(String nomDepartement);

    DepartementDto getDepartementById(String id);

    DepartementDto updateDepartement(String id, DepartementDto departementDTO);

    void deleteDepartement(String id);

    List<DepartementDto> getAllDepartementsOfEtablissement(String idEtablissement);

    List<DepartementDto> getAllDepartements(int page, int limit);
}
