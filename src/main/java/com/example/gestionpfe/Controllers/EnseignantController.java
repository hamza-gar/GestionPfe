package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Controllers.Dto.EnseignantDto;
import com.example.gestionpfe.Requests.EnseignantRequest;
import com.example.gestionpfe.Responses.EnseignantResponse;
import com.example.gestionpfe.Services.EnseignantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController {
    @Autowired
    EnseignantService enseignantService;

    //added now
    @GetMapping(path="/{id}")
    public ResponseEntity<EnseignantResponse> getEnseignant(@PathVariable String id){
        EnseignantDto enseignantDto = enseignantService.getEnseignantByIdEnseignant(id);
        EnseignantResponse  enseignantResponse = new EnseignantResponse();
        BeanUtils.copyProperties(enseignantDto,enseignantResponse);
        return new ResponseEntity<EnseignantResponse>(enseignantResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnseignantResponse> addEnseignant(@RequestBody EnseignantRequest enseignantRequest){
        EnseignantDto enseignantDto = new EnseignantDto();
        BeanUtils.copyProperties(enseignantRequest,enseignantDto);

        EnseignantDto AddEnseignant = enseignantService.addEnseignant(enseignantDto);

        EnseignantResponse enseignantResponse = new EnseignantResponse();

        BeanUtils.copyProperties(AddEnseignant,enseignantResponse);

        return new ResponseEntity<EnseignantResponse>(enseignantResponse,HttpStatus.CREATED);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<EnseignantResponse> updateEnseignant(@PathVariable String id,@RequestBody EnseignantRequest enseignantRequest){

        EnseignantDto enseignantDto = new EnseignantDto();
        BeanUtils.copyProperties(enseignantRequest,enseignantDto);

        EnseignantDto UpdateEnseignant = enseignantService.updateEnseignant(id,enseignantDto);

        EnseignantResponse enseignantResponse = new EnseignantResponse();

        BeanUtils.copyProperties(UpdateEnseignant,enseignantResponse);

        return new ResponseEntity<EnseignantResponse>(enseignantResponse,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteEnseignant(@PathVariable String id){
        enseignantService.deleteEnseignant(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
