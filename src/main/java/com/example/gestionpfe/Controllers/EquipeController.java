package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.EquipeDto;
import com.example.gestionpfe.Dto.EtudiantDto;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Requests.EquipeRequest;
import com.example.gestionpfe.Responses.EquipeResponse;
import com.example.gestionpfe.Services.EquipeService;
import com.example.gestionpfe.Services.EtudiantService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/equipes")
public class EquipeController {
    Logger logger = org.slf4j.LoggerFactory.getLogger(EquipeController.class);

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    EquipeService equipeService;

    @Autowired
    EtudiantService etudiantService;


    @PreAuthorize("hasAuthority('GET_BY_IDEQUIPE_AUTHORITY')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<EquipeResponse> getEquipe(@PathVariable String id) {
        EquipeDto equipeDto = equipeService.getEquipeById(id);
        EquipeResponse equipeResponse = new EquipeResponse();
        equipeResponse = modelMapper.map(equipeDto, EquipeResponse.class);
        return new ResponseEntity<EquipeResponse>(equipeResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GET_ALL_EQUIPES_AUTHORITY')")
    @GetMapping
    public List<EquipeResponse> getAllEquipe(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        List<EquipeResponse> equipeResponse = new ArrayList<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        List<EquipeDto> equipes = equipeService.getAllEquipes(username,page, limit);

        for (EquipeDto equipeDto : equipes) {
            EquipeResponse equipe = new EquipeResponse();
            equipe = modelMapper.map(equipeDto, EquipeResponse.class);

            equipeResponse.add(equipe);
        }
        return equipeResponse;
    }

    @PreAuthorize("hasAuthority('GET_EQUIPES_OF_SUJETS_AUTHORITY')")
    @GetMapping(path = "/sujets/{id}")
    public ResponseEntity<List<EquipeResponse>> getEquipesOfSujet(@PathVariable String id, @RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        List<EquipeResponse> equipeResponse = new ArrayList<>();
        List<EquipeDto> equipes = equipeService.getGroupesOfSujets(username,id, page, limit);

        for (EquipeDto equipeDto : equipes) {
            EquipeResponse equipe = new EquipeResponse();
            equipe = modelMapper.map(equipeDto, EquipeResponse.class);

            equipeResponse.add(equipe);
        }
        return new ResponseEntity<List<EquipeResponse>>(equipeResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GET_LOCKED_EQUIPES_AUTHORITY')")
    @GetMapping(path = "/locked")
    public ResponseEntity<List<EquipeResponse>> getLockedEquipes(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        List<EquipeResponse> equipeResponse = new ArrayList<>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        List<EquipeDto> equipes = equipeService.getLockedEquipes(username, page, limit);

        for (EquipeDto equipeDto : equipes) {
            EquipeResponse equipe = new EquipeResponse();
            equipe = modelMapper.map(equipeDto, EquipeResponse.class);

            equipeResponse.add(equipe);
        }
        return new ResponseEntity<List<EquipeResponse>>(equipeResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADD_EQUIPE_AUTHORITY')")
    @PostMapping
    public ResponseEntity<EquipeResponse> addEquipe(@RequestBody EquipeRequest equipeRequest) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        EquipeDto equipedto = new EquipeDto();
        equipedto.setIsPrivate(equipeRequest.getIsPrivate());
        equipedto.setCryptedPassword(equipeRequest.getCryptedPassword());
        equipedto.setEtudiant(new ArrayList<>());

        EquipeDto createdEquipe = equipeService.addEquipe(username, equipeRequest.getSujetId(), equipedto);

        EquipeResponse equipeResponse = new EquipeResponse();
        equipeResponse = modelMapper.map(createdEquipe, EquipeResponse.class);
        return new ResponseEntity<EquipeResponse>(equipeResponse, HttpStatus.CREATED);
    }

    @PostAuthorize("hasAuthority('ADD_ETUDIANT_TO_EQUIPE_AUTHORITY')")
    @PutMapping(path = "/join")
    public ResponseEntity<EquipeResponse> joinEquipe(@RequestBody EquipeRequest equipeRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        EquipeDto equipeDto = new EquipeDto();
        equipeDto.setIdEquipe(equipeRequest.getIdEquipe());
        equipeDto.setCryptedPassword(equipeRequest.getCryptedPassword() == null ? "" : equipeRequest.getCryptedPassword());

        EquipeDto updatedEquipe = equipeService.joinEquipe(username, equipeDto);

        return new ResponseEntity<EquipeResponse>(modelMapper.map(updatedEquipe, EquipeResponse.class), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DELETE_EQUIPE_AUTHORITY')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteEquipe(@PathVariable String id) {
        equipeService.deleteEquipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /*TODO quitter equipe*/
    @PreAuthorize("hasAuthority('DELETE_SELF_FROM_EQUIPE_AUTHORITY')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<EquipeResponse> removeSelfFromEquipe(@PathVariable String id) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        EquipeDto equipeDto = equipeService.removeEtudiant(username, id);


        return new ResponseEntity<EquipeResponse>(modelMapper.map(equipeDto, EquipeResponse.class), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADD_GDRIVELINK_AUTHORITY')")
    @PutMapping(path = "/drive-link")
    public ResponseEntity<EquipeResponse> updateDriveLink(@RequestBody EquipeRequest equipeRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        EquipeDto equipeDto = new EquipeDto();
        equipeDto.setIdEquipe(equipeRequest.getIdEquipe());
        equipeDto.setDriveLink(equipeRequest.getDriveLink());

        EquipeDto updatedEquipe = equipeService.addDriveLink(username, equipeDto);

        return new ResponseEntity<EquipeResponse>(modelMapper.map(updatedEquipe, EquipeResponse.class), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GET_EMAILS_OFMEMBERS_AUTHORITY')")
    @GetMapping(path = "/drive-link")
    public ResponseEntity<List<String>> getMembersDriveLink(@RequestBody EquipeRequest equipeRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        EquipeDto equipeDto = new EquipeDto();
        equipeDto.setIdEquipe(equipeRequest.getIdEquipe());
        List<String> driveLinks = equipeService.getEmailsOfEquipe(username, equipeDto);

        return new ResponseEntity<List<String>>(driveLinks, HttpStatus.OK);
    }

    @PostMapping(path = "/share-drive")
    public ResponseEntity<Boolean> shareDrive(@RequestBody EquipeRequest equipeRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        EquipeDto equipeDto = new EquipeDto();
        equipeDto.setIdEquipe(equipeRequest.getIdEquipe());


        return new ResponseEntity<Boolean>(equipeService.shareDriveLink(username, equipeDto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GET_BY_IDEQUIPE_AUTHORITY')")
    @GetMapping(path = "/get-members")
    public ResponseEntity<List<String[]>> getMembers(@RequestParam(value = "idEquipe") String idEquipe) {

        List<String[]> members = equipeService.getEquipeMembers(idEquipe);

        return new ResponseEntity<List<String[]>>(members, HttpStatus.OK);
    }

    @GetMapping(path = "/self")
    public ResponseEntity<EquipeResponse> getSelfEquipe() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        EquipeDto equipeDto = equipeService.getMyEquipe(username);

        return new ResponseEntity<EquipeResponse>(modelMapper.map(equipeDto, EquipeResponse.class), HttpStatus.OK);
    }

    @GetMapping(path = "/getbysujet")
    public ResponseEntity<EquipeResponse> getEquipeBySujet(@RequestParam(value = "idSujet") String idSujet) {

        EquipeDto equipeDto = equipeService.getEquipeBySujetId(idSujet);


        return new ResponseEntity<EquipeResponse>(modelMapper.map(equipeDto, EquipeResponse.class), HttpStatus.OK);
    }



}
