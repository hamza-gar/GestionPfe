package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.AdminDto;
import com.example.gestionpfe.Dto.DepartementDto;
import com.example.gestionpfe.Dto.DomaineDto;
import com.example.gestionpfe.Requests.DomaineRequest;

import java.util.List;

public interface DepartementService {
    DepartementDto addDepartement(DepartementDto departementDTO);

    DepartementDto getDepartement(String nomDepartement);

    DepartementDto getDepartementById(String id);

    DepartementDto updateDepartement(String id, DepartementDto departementDTO);

    void deleteDepartement(String id);

    List<DepartementDto> getAllDepartementsOfEtablissement(String nomEtablissement);

    List<DepartementDto> getAllDepartements(int page, int limit);


}
