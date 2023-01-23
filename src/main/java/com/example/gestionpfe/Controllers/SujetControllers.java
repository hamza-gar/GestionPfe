package com.example.gestionpfe.Controllers;


import com.example.gestionpfe.Dto.SujetDto;
import com.example.gestionpfe.Requests.SujetRequest;
import com.example.gestionpfe.Responses.SujetResponse;
import com.example.gestionpfe.Services.SujetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sujets")
public class SujetControllers {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    SujetService sujetService;
    @PreAuthorize("hasAuthority('GET_BY_IDSUJET_AUTHORITY')")
    @GetMapping(path="/{id}")
    public ResponseEntity<SujetResponse> getSujet(@PathVariable String id){
        SujetDto sujetDto = sujetService.getSujetById(id);
        SujetResponse  sujetResponse = new SujetResponse();
        sujetResponse = modelMapper.map(sujetDto,SujetResponse.class);
        return new ResponseEntity<SujetResponse>(sujetResponse, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('GET_ALL_SUJETS_AUTHORITY')")
    @GetMapping
    public List<SujetResponse> getAllSujets(@RequestParam(value = "page") int page, @RequestParam(value="limit" ) int limit){
        List<SujetResponse> sujetResponse = new ArrayList<>();
        List<SujetDto> sujets = sujetService.getAllSujets(page,limit);

        for(SujetDto sujetDto:sujets){
            SujetResponse sujet = new SujetResponse();
            sujet = modelMapper.map(sujetDto,SujetResponse.class);

            sujetResponse.add(sujet);
        }
        return sujetResponse;
    }

    @PreAuthorize("hasAuthority('ADD_SUJET_AUTHORITY')")
    @PostMapping
    public ResponseEntity<SujetResponse> addSujet(@RequestBody SujetRequest sujetRequest){
        SujetDto sujetDto = new SujetDto();
        sujetDto = modelMapper.map(sujetRequest,SujetDto.class);

        SujetDto createdSujet = sujetService.addSujet(sujetDto);
        SujetResponse sujetResponse = new SujetResponse();
        sujetResponse = modelMapper.map(createdSujet,SujetResponse.class);
        return new ResponseEntity<SujetResponse>(sujetResponse, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('UPDATE_SUJET_AUTHORITY')")
    @PutMapping(path="/{id}")
    public ResponseEntity<SujetResponse> updateSujet(@PathVariable String id,@RequestBody SujetRequest sujetRequest){
        SujetDto sujetDto = new SujetDto();
        sujetDto = modelMapper.map(sujetRequest,SujetDto.class);

        SujetDto updatedSujet = sujetService.updateSujet(id,sujetDto);
        SujetResponse sujetResponse = new SujetResponse();
        sujetResponse = modelMapper.map(updatedSujet,SujetResponse.class);
        return new ResponseEntity<SujetResponse>(sujetResponse, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('DELETE_SUJET_AUTHORITY')")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteSujet(@PathVariable String id){
        sujetService.deleteSujet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
