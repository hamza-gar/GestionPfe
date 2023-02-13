package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.DepartementDto;
import com.example.gestionpfe.Dto.EtablissementDto;
import com.example.gestionpfe.Dto.UniversiteDto;
import com.example.gestionpfe.Repositories.UniversiteRepository;
import com.example.gestionpfe.Responses.DepartementResponse;
import com.example.gestionpfe.Responses.EtablissementResponse;
import com.example.gestionpfe.Responses.UniversiteResponse;
import com.example.gestionpfe.Services.DepartementService;
import com.example.gestionpfe.Services.EtablissementService;
import com.example.gestionpfe.Services.UniversiteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/etablissements")
public class EtablissementController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    EtablissementService etablissementService;

    @Autowired
    DepartementService departementService;

    @Autowired
    UniversiteService universiteService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<EtablissementResponse>> getAllEtablissements() {
        List<EtablissementResponse> etablissementResponse = new ArrayList<>();
        List<EtablissementDto> etablissements = etablissementService.getAllEtablissements();

        for (EtablissementDto etablissementDto : etablissements) {
            EtablissementResponse etablissement = new EtablissementResponse();
            etablissement = modelMapper.map(etablissementDto, EtablissementResponse.class);

            etablissementResponse.add(etablissement);
        }
        return new ResponseEntity<List<EtablissementResponse>>(etablissementResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/universites")
    public ResponseEntity<List<UniversiteResponse>> getAllUniversites() {
        List<UniversiteResponse> universiteResponses = new ArrayList<>();
        List<UniversiteDto> universiteDtos = universiteService.getAllUniversites();

        for (UniversiteDto universiteDto : universiteDtos) {
            UniversiteResponse universite = new UniversiteResponse();
            universite = modelMapper.map(universiteDto, UniversiteResponse.class);

            universiteResponses.add(universite);
        }
        return new ResponseEntity<List<UniversiteResponse>>(universiteResponses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<DepartementResponse>> getDepartementsByEtablissementId(@RequestBody DepartementResponse nomEtablissement) {
        List<DepartementResponse> departementResponse = new ArrayList<>();
        List<DepartementDto> departements = departementService.getAllDepartementsOfEtablissement(nomEtablissement.getNomEtablissement());
        for (DepartementDto departementDto : departements) {
            DepartementResponse departement = new DepartementResponse();
            departement.setIdDepartement(departementDto.getIdDepartement());
            departement.setNomDepartement(departementDto.getNomDepartement());
            departement.setNomEtablissement(departementDto.getEtablissement().getNomEtablissement());

            departementResponse.add(departement);
        }
        return new ResponseEntity<List<DepartementResponse>>(departementResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/byUniversite")
    public ResponseEntity<List<EtablissementResponse>> getEtablissementsByUniversiteId(@RequestBody UniversiteResponse nomUniversite) {
        List<EtablissementResponse> etablissementResponse = new ArrayList<>();
        List<EtablissementDto> etablissements = universiteService.getAllEtablissementsOfUniversite(nomUniversite.getNomUniversite());
        for (EtablissementDto etablissementDto : etablissements) {
            EtablissementResponse etablissement = modelMapper.map(etablissementDto, EtablissementResponse.class);
            etablissementResponse.add(etablissement);
        }
        return new ResponseEntity<List<EtablissementResponse>>(etablissementResponse, HttpStatus.OK);
    }

}
