package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.EtablissementDto;
import com.example.gestionpfe.Responses.EtablissementResponse;
import com.example.gestionpfe.Services.EtablissementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/etablissements")
public class EtablissementController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    EtablissementService etablissementService;

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
}
