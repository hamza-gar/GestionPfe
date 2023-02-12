package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.SoutenanceDto;
import com.example.gestionpfe.Entities.Soutenance;
import com.example.gestionpfe.Requests.SoutenanceRequest;
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
    SoutenanceService soutenanceService;

    @GetMapping(path = "/ofsujet")
    public ResponseEntity<SoutenanceResponse> getSoutenanceByIdSujet(@RequestParam(value = "idSujet") String idSujet){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        SoutenanceDto soutenanceDto = soutenanceService.getSoutenanceByIdSujet(username, idSujet);
        SoutenanceResponse soutenanceResponse = new SoutenanceResponse();
        soutenanceResponse = modelMapper.map(soutenanceDto, SoutenanceResponse.class);
        return ResponseEntity.ok(soutenanceResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SoutenanceResponse> getSoutenanceById(@PathVariable String id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        SoutenanceDto soutenanceDto = soutenanceService.getSoutenanceByIdSoutenance(username, id);
        SoutenanceResponse soutenanceResponse = new SoutenanceResponse();
        soutenanceResponse = modelMapper.map(soutenanceDto, SoutenanceResponse.class);
        return ResponseEntity.ok(soutenanceResponse);
    }

    @GetMapping(path="/isDateSet")
    public ResponseEntity<Boolean> isDateSet(@RequestParam(value = "idSujet") String idSujet){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        return ResponseEntity.ok(soutenanceService.isDateSet(username, idSujet));
    }

    @GetMapping
    public ResponseEntity<List<SoutenanceResponse>> getAllSoutenances(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        List<SoutenanceResponse> soutenanceResponse = new ArrayList<>();
        List<SoutenanceDto> soutenances = soutenanceService.getAllSoutenance(username, page, limit);

        for (SoutenanceDto soutenanceDto : soutenances) {
            SoutenanceResponse soutenance1 = new SoutenanceResponse();
            soutenance1 = modelMapper.map(soutenanceDto, SoutenanceResponse.class);
            soutenance1.setNomSujet(soutenanceDto.getSujet().getNomSujet());
            soutenance1.setIdEquipe(soutenanceDto.getSujet().getEquipe().get(0).getIdEquipe());
            soutenanceResponse.add(soutenance1);

        }
        return ResponseEntity.ok(soutenanceResponse);
    }

    @GetMapping(path = "/mine")
    public ResponseEntity<List<SoutenanceResponse>> getAllSoutenancesMine(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        List<SoutenanceResponse> soutenanceResponse = new ArrayList<>();
        List<SoutenanceDto> soutenances = soutenanceService.getAllMySoutenance(username, page, limit);

        for (SoutenanceDto soutenanceDto : soutenances) {
            SoutenanceResponse soutenance1 = new SoutenanceResponse();
            soutenance1 = modelMapper.map(soutenanceDto, SoutenanceResponse.class);
            soutenance1.setNomSujet(soutenanceDto.getSujet().getNomSujet());
            soutenance1.setIdEquipe(soutenanceDto.getSujet().getEquipe().get(0).getIdEquipe());
            logger.info("soutenance added.");
            soutenanceResponse.add(soutenance1);
        }
        return ResponseEntity.ok(soutenanceResponse);
    }


    @PostMapping
    public ResponseEntity<SoutenanceResponse> addSoutenance(@RequestBody SoutenanceRequest soutenanceRequest, @RequestParam(value = "idSujet") String idSujet) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        SoutenanceDto soutenance = new SoutenanceDto();
        soutenance.setDateSoutenance(soutenanceRequest.getDateSoutenance());
        SoutenanceDto soutenanceDto = soutenanceService.addSoutenance(username, soutenance, idSujet);

        SoutenanceResponse soutenanceResponse = new SoutenanceResponse();
        soutenanceResponse = modelMapper.map(soutenanceDto, SoutenanceResponse.class);
        return ResponseEntity.ok(soutenanceResponse);

    }

    @PutMapping(path = "/invite")
    public ResponseEntity<Boolean> inviteJury(@RequestBody SoutenanceRequest soutenanceRequest, @RequestParam(value = "emailEnseignant") String emailEnseignant, @RequestParam(value = "roleJury") String roleJury) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        SoutenanceDto soutenance = new SoutenanceDto();
        soutenance.setIdSoutenance(soutenanceRequest.getIdSoutenance());

        return ResponseEntity.ok(soutenanceService.inviteJurys(username, roleJury, emailEnseignant, soutenance));
    }


    @PutMapping(path = "/date-update")
    public ResponseEntity<SoutenanceResponse> updateDateSoutenance(@RequestBody SoutenanceRequest soutenanceRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        SoutenanceDto soutenanceDto = new SoutenanceDto();
        soutenanceDto.setIdSoutenance(soutenanceRequest.getIdSoutenance());
        soutenanceDto.setDateSoutenance(soutenanceRequest.getDateSoutenance());
        soutenanceDto = soutenanceService.updateDateSoutenance(username, soutenanceDto);
        SoutenanceResponse soutenanceResponse = new SoutenanceResponse();
        soutenanceResponse.setIdSoutenance(soutenanceDto.getIdSoutenance());
        soutenanceResponse.setDateSoutenance(soutenanceDto.getDateSoutenance().toString());
        soutenanceResponse.setIdSujet(soutenanceDto.getSujet().getIdSujet());
        return ResponseEntity.ok(soutenanceResponse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteSoutenance(@PathVariable String id) {
        soutenanceService.deleteSoutenance(id);
        return ResponseEntity.noContent().build();
    }
}
