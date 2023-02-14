package com.example.gestionpfe.Controllers;


import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Dto.SujetDto;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Sujet;
import com.example.gestionpfe.Requests.SujetRequest;
import com.example.gestionpfe.Responses.SujetResponse;
import com.example.gestionpfe.Services.EnseignantService;
import com.example.gestionpfe.Services.SujetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sujets")
public class SujetController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    SujetService sujetService;

    @Autowired
    EnseignantService enseignantService;


    @GetMapping(path = "/{id}")
    public ResponseEntity<SujetResponse> getSujet(@PathVariable String id) {
        SujetDto sujetDto = sujetService.getSujetById(id);
        SujetResponse sujetResponse = new SujetResponse();
        sujetResponse = modelMapper.map(sujetDto, SujetResponse.class);
        sujetResponse.setEnseignantId(sujetDto.getEncadrant().getIdEnseignant());
        sujetResponse.setNomEnseignant(sujetDto.getEncadrant().getNom().substring(0, 1).toUpperCase() + sujetDto.getEncadrant().getNom().substring(1) +
                " " + sujetDto.getEncadrant().getPrenom().substring(0, 1).toUpperCase() + sujetDto.getEncadrant().getPrenom().substring(1));
        sujetResponse.setEmailEnseignant(sujetDto.getEncadrant().getEmail());
        return new ResponseEntity<SujetResponse>(sujetResponse, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<SujetResponse>> getAllSujets(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        List<SujetResponse> sujetResponse = new ArrayList<>();
        List<SujetDto> sujets = sujetService.getAllSujets(username, page, limit);

        for (SujetDto sujetDto : sujets) {
            EnseignantDto enseignant = enseignantService.getEnseignantByIdEnseignant(sujetDto.getEncadrant().getIdEnseignant());
            SujetResponse sujet = new SujetResponse();
            sujet = modelMapper.map(sujetDto, SujetResponse.class);
            sujet.setNomEnseignant(enseignant.getNom().substring(0, 1).toUpperCase() + enseignant.getNom().substring(1) +
                    " " + enseignant.getPrenom().substring(0, 1).toUpperCase() + enseignant.getPrenom().substring(1));
            sujet.setEmailEnseignant(enseignant.getEmail());
            sujetResponse.add(sujet);
        }
        System.out.println(sujetResponse);
        return new ResponseEntity<List<SujetResponse>>(sujetResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/filtered")
    public ResponseEntity<List<SujetResponse>> getAllSujetsFiltered(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit,@RequestParam(value = "universite") String universite,@RequestParam(value = "etablissement") String etablissement,@RequestParam(value = "departement") String departement) {


        List<SujetResponse> sujetResponse = new ArrayList<>();
        List<SujetDto> sujets = sujetService.getAllSujetsFiltered(page, limit,universite,etablissement,departement);
        List<SujetResponse> sujetList = new ArrayList<>();
        SujetResponse sujet = new SujetResponse();

        for (SujetDto sujetDto : sujets) {
            sujet = modelMapper.map(sujetDto, SujetResponse.class);

            sujetResponse.add(sujet);
        }
        System.out.println(sujetResponse);
        return new ResponseEntity<List<SujetResponse>>(sujetResponse, HttpStatus.OK);
    }


    @GetMapping(path = "/filiere")
    public ResponseEntity<List<SujetResponse>> getAllSujetsByFiliere(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit, @RequestParam(value = "filiere") String idFiliere) {
        List<SujetResponse> sujetResponse = new ArrayList<>();
        List<SujetDto> sujets = sujetService.getAllSujetsByFiliere(idFiliere, page, limit);

        for (SujetDto sujetDto : sujets) {
            SujetResponse sujet = new SujetResponse();
            sujet = modelMapper.map(sujetDto, SujetResponse.class);

            sujetResponse.add(sujet);
        }
        return new ResponseEntity<List<SujetResponse>>(sujetResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Long> getCountAllSujets() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        Long count = sujetService.countSujets(username);
        return new ResponseEntity<Long>(count, HttpStatus.OK);
    }

    @GetMapping(path = "/posted")
    public ResponseEntity<List<SujetResponse>> getMySujets(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        List<SujetResponse> sujetResponse = new ArrayList<>();
        List<SujetDto> sujets = sujetService.getAllMySujets(username, page, limit);

        for (SujetDto sujetDto : sujets) {
            SujetResponse sujet = new SujetResponse();
            sujet = modelMapper.map(sujetDto, SujetResponse.class);
            sujetResponse.add(sujet);
        }
        return new ResponseEntity<List<SujetResponse>>(sujetResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/mylocked")
    public ResponseEntity<List<SujetResponse>> getMyLockedSujets(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        List<SujetResponse> sujetResponse = new ArrayList<>();
        List<SujetDto> sujets = sujetService.getAllMyLockedSujets(username, page, limit);

        for (SujetDto sujetDto : sujets) {
            SujetResponse sujet = new SujetResponse();
            sujet = modelMapper.map(sujetDto, SujetResponse.class);
            sujetResponse.add(sujet);
        }
        return new ResponseEntity<List<SujetResponse>>(sujetResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADD_SUJET_AUTHORITY')")
    @PostMapping
    public ResponseEntity<SujetResponse> addSujet(@RequestBody SujetRequest sujetRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        SujetDto sujetDto = new SujetDto();
        sujetDto = modelMapper.map(sujetRequest, SujetDto.class);

        SujetDto createdSujet = sujetService.addSujet(sujetDto, username);
        SujetResponse sujetResponse = new SujetResponse();
        sujetResponse = modelMapper.map(createdSujet, SujetResponse.class);

        return new ResponseEntity<SujetResponse>(sujetResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('UPDATE_SUJET_AUTHORITY')")
    @PutMapping(path = "/lock")
    public ResponseEntity<SujetResponse> lockSujet(@RequestParam(value = "idSujet") String idSujet, @RequestParam(value = "idEquipe") String idEquipe) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String mail = principal.toString();

        SujetDto sujetDto = sujetService.lockSujet(mail, idSujet, idEquipe);

        return new ResponseEntity<SujetResponse>(modelMapper.map(sujetDto, SujetResponse.class), HttpStatus.OK);
    }

    @PutMapping(path = "/validate")
    public ResponseEntity<SujetResponse> validateSujet(@RequestBody SujetRequest sujetRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String mail = principal.toString();

        SujetDto sujetDto = new SujetDto();
        sujetDto.setIdSujet(sujetRequest.getIdSujet());
        SujetDto response = sujetService.validateSujet(mail, sujetDto, sujetRequest.getDone());


        return new ResponseEntity<SujetResponse>(modelMapper.map(response, SujetResponse.class), HttpStatus.OK);
    }

    @GetMapping(path = "/isValidated")
    public ResponseEntity<Boolean> isSujetValidated(@RequestParam(value = "idSujet") String idSujet) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String mail = principal.toString();

        Boolean response = sujetService.isValidated(mail, idSujet);

        return new ResponseEntity<Boolean>(response, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('UPDATE_SUJET_AUTHORITY')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<SujetResponse> updateSujet(@PathVariable String id, @RequestBody SujetRequest sujetRequest) {
        SujetDto sujetDto = new SujetDto();
        sujetDto = modelMapper.map(sujetRequest, SujetDto.class);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        SujetDto updatedSujet = sujetService.updateSujet(username, id, sujetDto);
        SujetResponse sujetResponse = new SujetResponse();
        sujetResponse = modelMapper.map(updatedSujet, SujetResponse.class);
        return new ResponseEntity<SujetResponse>(sujetResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DELETE_SUJET_AUTHORITY')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteSujet(@PathVariable String id) {
        sujetService.deleteSujet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
