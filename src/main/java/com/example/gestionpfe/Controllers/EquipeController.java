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
    @GetMapping(path="/{id}")
    public ResponseEntity<EquipeResponse> getEquipe(@PathVariable String id){
        EquipeDto equipeDto = equipeService.getEquipeById(id);
        EquipeResponse  equipeResponse = new EquipeResponse();
        equipeResponse = modelMapper.map(equipeDto,EquipeResponse.class);
        return new ResponseEntity<EquipeResponse>(equipeResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GET_ALL_EQUIPES_AUTHORITY')")
    @GetMapping
    public List<EquipeResponse>getAllEquipe(@RequestParam(value = "page") int page, @RequestParam(value="limit" ) int limit){
        List<EquipeResponse> equipeResponse = new ArrayList<>();
        List<EquipeDto> equipes = equipeService.getAllEquipes(page,limit);

        for(EquipeDto equipeDto:equipes){
            EquipeResponse equipe = new EquipeResponse();
            equipe = modelMapper.map(equipeDto,EquipeResponse.class);

            equipeResponse.add(equipe);
        }
        return equipeResponse;
    }

    @PreAuthorize("hasAuthority('ADD_EQUIPE_AUTHORITY')")
    @PostMapping(path="/{id}")
    public ResponseEntity<EquipeResponse> addEquipe(@PathVariable String id,@RequestBody EquipeRequest equipeRequest){

        EtudiantDto etudiantdto = etudiantService.getEtudiantByIdEtudiant(id);
        if (etudiantdto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(modelMapper.map(etudiantdto,Etudiant.class));
        EquipeDto equipedto = new EquipeDto();
        equipedto.setIsPrivate(equipeRequest.getIsPrivate());
        equipedto.setCryptedPassword(equipeRequest.getCryptedPassword());

//        equipedto = modelMapper.map(equipeRequest,EquipeDto.class);
        equipedto.setEtudiant(etudiants);

        EquipeDto createdEquipe = equipeService.addEquipe(equipeRequest.getSujetId(),equipedto);
        EquipeResponse equipeResponse = new EquipeResponse();
        equipeResponse = modelMapper.map(createdEquipe,EquipeResponse.class);
        return new ResponseEntity<EquipeResponse>(equipeResponse, HttpStatus.CREATED);
    }

    @PutMapping(path="/add/{id}/{idEtudiant}")
    public ResponseEntity<EquipeResponse> addEtudiantToEquipe(@PathVariable String id, @PathVariable String idEtudiant){

        EtudiantDto etudiantdto = etudiantService.getEtudiantByIdEtudiant(idEtudiant);
        if (etudiantdto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        EquipeDto equipeDto = equipeService.getEquipeByIdEquipe(id);
        if (equipeDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Etudiant> etudiants = equipeDto.getEtudiant();

        for (Etudiant etudiant:etudiants){
            if (etudiant.getIdEtudiant().equals(idEtudiant)){
                logger.info("etudiant already in equipe");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }

        if (etudiants.size() == equipeDto.getTailleEquipe()){
            logger.info("equipe is full");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        etudiants.add(modelMapper.map(etudiantdto,Etudiant.class));
        equipeDto.setEtudiant(etudiants);

        equipeService.updateEquipe(equipeDto);

        EquipeResponse equipeResponse = new EquipeResponse();
        equipeResponse = modelMapper.map(equipeDto,EquipeResponse.class);
        return new ResponseEntity<EquipeResponse>(equipeResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DELETE_EQUIPE_AUTHORITY')")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteEquipe(@PathVariable String id){
        equipeService.deleteEquipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*TODO quitter equipe*/
    @PutMapping(path="/{id}")
    public ResponseEntity<EquipeResponse> removeSelfFromEquipe(@PathVariable String id){
        EquipeDto equipeDto = equipeService.getEquipeByIdEquipe(id);
        if (equipeDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Etudiant> etudiants = equipeDto.getEtudiant();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (etudiants.size() != 0){
            logger.info("etudiants :"+etudiants.size());
            for(int index = 0; index < etudiants.size(); index++){
                if (etudiants.get(index).getEmail().equals(username)){
                    etudiants.remove(index);
                }
            }
        }
        logger.info("etudiants :"+etudiants.size());
        equipeDto.setEtudiant(null);
        equipeDto.setEtudiant(etudiants);
        logger.info("etudiants :"+equipeDto.getEtudiant().size());
        if(equipeDto.getEtudiant().size() == 0){
            equipeService.deleteEquipe(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        equipeService.updateEquipe(equipeDto);
        EquipeResponse equipeResponse = new EquipeResponse();
        equipeResponse = modelMapper.map(equipeDto,EquipeResponse.class);
        return new ResponseEntity<EquipeResponse>(equipeResponse, HttpStatus.OK);
    }


}
