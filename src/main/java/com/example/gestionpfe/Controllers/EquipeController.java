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
        List<EquipeDto> equipes = equipeService.getAllEquipes(page, limit);

        for (EquipeDto equipeDto : equipes) {
            EquipeResponse equipe = new EquipeResponse();
            equipe = modelMapper.map(equipeDto, EquipeResponse.class);

            equipeResponse.add(equipe);
        }
        return equipeResponse;
    }

    /*TODO:
     *  * FIX ID -> SECURITYCONTEXT.GETPRINCIPAL.GETUSERNAME.*/
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
        System.out.println("joinEquipe");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        System.out.println("username: " + username);
        EquipeDto equipeDto = new EquipeDto();
        equipeDto.setIdEquipe(equipeRequest.getIdEquipe());

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

        EquipeDto equipeDto = equipeService.getEquipeByIdEquipe(id);
        if (equipeDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Etudiant> etudiants = equipeDto.getEtudiant();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (etudiants.size() != 0) {
            logger.info("etudiants :" + etudiants.size());
            for (int index = 0; index < etudiants.size(); index++) {
                if (etudiants.get(index).getEmail().equals(username)) {
                    etudiants.remove(index);
                }
            }
        }
        logger.info("etudiants :" + etudiants.size());
        equipeDto.setEtudiant(null);
        equipeDto.setEtudiant(etudiants);
        logger.info("etudiants :" + equipeDto.getEtudiant().size());
        if (equipeDto.getEtudiant().size() == 0) {
            equipeService.deleteEquipe(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        equipeService.updateEquipe(equipeDto);
        EquipeResponse equipeResponse = new EquipeResponse();
        equipeResponse = modelMapper.map(equipeDto, EquipeResponse.class);
        return new ResponseEntity<EquipeResponse>(equipeResponse, HttpStatus.OK);
    }


}
