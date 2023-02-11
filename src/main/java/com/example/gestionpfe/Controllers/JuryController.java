package com.example.gestionpfe.Controllers;


import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Dto.InvitationDto;
import com.example.gestionpfe.Dto.RemarqueDto;
import com.example.gestionpfe.Dto.SoutenanceDto;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Requests.InvitationRequest;
import com.example.gestionpfe.Requests.JuryRequest;
import com.example.gestionpfe.Requests.RemarqueRequest;
import com.example.gestionpfe.Responses.EnseignantResponse;
import com.example.gestionpfe.Responses.SoutenanceResponse;
import com.example.gestionpfe.Services.JuryService;
import com.example.gestionpfe.Services.RemarqueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jurys")
public class JuryController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    JuryService juryService;

    @Autowired
    RemarqueService remarqueService;

    @GetMapping(path = "/validate")
    public ResponseEntity<Boolean> reponseInvitation(@RequestParam(value = "idInvitation") String idInvitation, @RequestParam(value = "accept") String reponse) {
        Boolean accept = Boolean.parseBoolean(reponse);
        InvitationDto invitationDto = new InvitationDto();
        invitationDto.setIdInvitation(idInvitation);
        invitationDto.setAccepted(accept);

        return ResponseEntity.ok(juryService.reponseInvitation(invitationDto));
    }

    @GetMapping(path = "/soutenances")
    public ResponseEntity<List<SoutenanceResponse>> getSoutenanceByJury(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        List<SoutenanceDto> soutenanceDtos = juryService.getSoutenanceByJury(username, page, limit);
        List<SoutenanceResponse> soutenanceResponses = new ArrayList<>();
        for (SoutenanceDto soutenanceDto : soutenanceDtos) {
            SoutenanceResponse soutenanceResponse = new SoutenanceResponse();
            soutenanceResponse = modelMapper.map(soutenanceDto, SoutenanceResponse.class);
            soutenanceResponses.add(soutenanceResponse);
        }
        return ResponseEntity.ok(soutenanceResponses);
    }

    @PostMapping(path = "/add-remarque")
    public ResponseEntity<Boolean> addRemarque(@RequestBody RemarqueRequest remarqueRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        Etudiant etudiant = new Etudiant();
        RemarqueDto remarqueDto = new RemarqueDto();

        remarqueDto.setRemarque(remarqueRequest.getRemarque());
        remarqueDto.setNote(remarqueRequest.getNote());
        remarqueDto.setTarget(remarqueRequest.getTarget());
        etudiant.setIdEtudiant(remarqueRequest.getIdEtudiant());
        remarqueDto.setEtudiant(etudiant);

        remarqueService.addRemarque(username, remarqueDto);

        return ResponseEntity.ok(true);
    }

    @GetMapping(path = "/jurys")
    public ResponseEntity<List<EnseignantResponse>> getAllEnseignantsToInvite(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        List<EnseignantResponse> enseignantResponse = new ArrayList<>();
        List<EnseignantDto> enseignants = juryService.getEnseignantsToInvite(username, page, limit);

        for (EnseignantDto enseignantDto : enseignants) {
            EnseignantResponse enseignant = new EnseignantResponse();
            enseignant = modelMapper.map(enseignantDto, EnseignantResponse.class);

            enseignantResponse.add(enseignant);
        }
        return ResponseEntity.ok(enseignantResponse);
    }
}
