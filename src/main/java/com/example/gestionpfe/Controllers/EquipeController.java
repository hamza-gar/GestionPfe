package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.EquipeDto;
import com.example.gestionpfe.Dto.EtudiantDto;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Responses.EquipeResponse;
import com.example.gestionpfe.Services.EquipeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/equipes")
public class EquipeController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    EquipeService equipeService;

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
    @PostMapping
    public ResponseEntity<EquipeResponse>addEquipe(@RequestBody EquipeDto equipeDto){
        EquipeDto createdEquipe = new EquipeDto();
        createdEquipe = equipeService.addEquipe(equipeDto);
        EquipeResponse equipeResponse = new EquipeResponse();
        equipeResponse = modelMapper.map(createdEquipe,EquipeResponse.class);
        return new ResponseEntity<EquipeResponse>(equipeResponse, HttpStatus.CREATED);
    }

    /*TODO add etudiant to equipe*/

    @PreAuthorize("hasAuthority('DELETE_EQUIPE_AUTHORITY')")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteEquipe(@PathVariable String id){
        equipeService.deleteEquipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    /*TODO quitter equipe*/
}
