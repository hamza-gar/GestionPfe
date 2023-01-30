package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.SoutenanceDto;
import com.example.gestionpfe.Entities.Soutenance;
import com.example.gestionpfe.Responses.SoutenanceResponse;
import com.example.gestionpfe.Services.SoutenanceService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/soutenances")
public class SoutenanceController {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(SoutenanceController.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private SoutenanceService soutenanceService;

    @GetMapping(path="/{id}")
    public ResponseEntity<SoutenanceResponse> getSoutenanceById(@PathVariable String id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        SoutenanceDto soutenanceDto= soutenanceService.getSoutenanceByIdSoutenance(username,id);
        SoutenanceResponse soutenanceResponse = new SoutenanceResponse();
        soutenanceResponse = modelMapper.map(soutenanceDto, SoutenanceResponse.class);
        return ResponseEntity.ok(soutenanceResponse);
    }
    @GetMapping
    public ResponseEntity<List<SoutenanceResponse>> getAllSoutenances(@RequestParam(value= "page") int page, @RequestParam(value = "limit") int limit){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        List<SoutenanceResponse>soutenanceResponse = new ArrayList<>();
        List<SoutenanceDto> soutenances = soutenanceService.getAllSoutenance(username,page,limit);

        for(SoutenanceDto soutenanceDto : soutenances){
            SoutenanceResponse soutenance1 = new SoutenanceResponse();
            soutenance1 = modelMapper.map(soutenanceDto, SoutenanceResponse.class);

            soutenanceResponse.add(soutenance1);

        }
        return ResponseEntity.ok(soutenanceResponse);
    }
    @PostMapping
    public ResponseEntity<SoutenanceResponse> addSoutenance(){
        SoutenanceDto soutenance = new SoutenanceDto();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        SoutenanceDto soutenanceDto = soutenanceService.addSoutenance(username);
        SoutenanceResponse soutenanceResponse = new SoutenanceResponse();
        soutenanceResponse = modelMapper.map(soutenanceDto, SoutenanceResponse.class);
        return ResponseEntity.ok(soutenanceResponse);
    }
    @PutMapping(path="/{id}")
    public ResponseEntity<SoutenanceResponse> updateSoutenance(@PathVariable String id, @RequestBody Soutenance soutenance){
        SoutenanceDto soutenanceDto = new SoutenanceDto();
        soutenanceDto = modelMapper.map(soutenance, SoutenanceDto.class);
        SoutenanceDto soutenanceDto1 = soutenanceService.updateSoutenance(id,soutenanceDto);
        SoutenanceResponse soutenanceResponse = new SoutenanceResponse();
        soutenanceResponse = modelMapper.map(soutenanceDto1, SoutenanceResponse.class);
        return ResponseEntity.ok(soutenanceResponse);
    }
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteSoutenance(@PathVariable String id){
        soutenanceService.deleteSoutenance(id);
        return ResponseEntity.noContent().build();
    }
}
