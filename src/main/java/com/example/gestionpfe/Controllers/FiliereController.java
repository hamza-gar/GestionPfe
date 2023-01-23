package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.FiliereDto;
import com.example.gestionpfe.Entities.Filiere;
import com.example.gestionpfe.InitialUsersSetup;
import com.example.gestionpfe.Responses.FiliereResponse;
import com.example.gestionpfe.Services.FiliereService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filieres")
public class FiliereController {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(InitialUsersSetup.class);

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    FiliereService filiereService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<FiliereResponse> getFiliere(@PathVariable String id) {
        FiliereDto filiereDto = filiereService.getFiliereById(id);
        FiliereResponse filiereResponse = new FiliereResponse();
        filiereResponse = modelMapper.map(filiereDto, FiliereResponse.class);
        return ResponseEntity.ok(filiereResponse);
    }


    @GetMapping
    public ResponseEntity<List<FiliereResponse>> getAllFilieres(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        List<FiliereResponse> filiereResponse = new ArrayList<>();
        List<FiliereDto> filieres = filiereService.getAllFilieres(page, limit);

        logger.info("uhmmmm");
        for (FiliereDto filiereDto : filieres) {
            FiliereResponse filiere = new FiliereResponse();
            filiere = modelMapper.map(filiereDto, FiliereResponse.class);

            filiereResponse.add(filiere);

        }
        return ResponseEntity.ok(filiereResponse);

    }

    @PostMapping
    public ResponseEntity<FiliereResponse> addFiliere(@RequestBody Filiere filiere) {
        FiliereDto filiereDto = new FiliereDto();
        filiereDto = modelMapper.map(filiere, FiliereDto.class);

        FiliereDto createdFiliere = filiereService.addFiliere(filiereDto);
        FiliereResponse filiereResponse = new FiliereResponse();
        filiereResponse = modelMapper.map(createdFiliere, FiliereResponse.class);
        return ResponseEntity.ok(filiereResponse);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<FiliereResponse> updateFiliere(@PathVariable String id, @RequestBody Filiere filiere) {
        FiliereDto filiereDto = new FiliereDto();
        filiereDto = modelMapper.map(filiere, FiliereDto.class);

        FiliereDto updatedFiliere = filiereService.updateFiliere(id, filiereDto);
        FiliereResponse filiereResponse = new FiliereResponse();
        filiereResponse = modelMapper.map(updatedFiliere, FiliereResponse.class);
        return ResponseEntity.ok(filiereResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteFiliere(@PathVariable String id) {
        filiereService.deleteFiliere(id);
        return ResponseEntity.noContent().build();
    }

}
