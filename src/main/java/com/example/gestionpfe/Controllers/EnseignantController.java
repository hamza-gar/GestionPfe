package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Dto.EtudiantDto;
import com.example.gestionpfe.Requests.EnseignantRequest;
import com.example.gestionpfe.Responses.EnseignantResponse;
import com.example.gestionpfe.Responses.EtudiantResponse;
import com.example.gestionpfe.Services.EnseignantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    EnseignantService enseignantService;

    //added now
    @PreAuthorize("hasAuthority('GET_BY_IDENSEIGNANT_AUTHORITY')")
    @GetMapping(path="/{id}")
    public ResponseEntity<EnseignantResponse> getEnseignant(@PathVariable String id){
        EnseignantDto enseignantDto = enseignantService.getEnseignantByIdEnseignant(id);
        EnseignantResponse  enseignantResponse = new EnseignantResponse();
        enseignantResponse = modelMapper.map(enseignantDto,EnseignantResponse.class);
        return new ResponseEntity<EnseignantResponse>(enseignantResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GET_ALL_ENSEIGNANT_AUTHORITY')")
    @GetMapping
    public List<EnseignantResponse> getAllEnseignants(@RequestParam(value = "page") int page, @RequestParam(value="limit" )int limit){
        List<EnseignantResponse> enseignantResponse = new ArrayList<>();
        List<EnseignantDto> enseignants = enseignantService.getAllEnseignants(page,limit);

        for(EnseignantDto enseignantDto:enseignants){
            EnseignantResponse enseignant = new EnseignantResponse();
            enseignant = modelMapper.map(enseignantDto,EnseignantResponse.class);

            enseignantResponse.add(enseignant);
        }
        return enseignantResponse;
    }

    @GetMapping(path = "/verification/{token}")
    public ResponseEntity<EnseignantResponse> verifyEnseignant(@PathVariable String token) {
        EnseignantDto enseignantDto = enseignantService.verifyEnseignant(token);
        EnseignantResponse enseignantResponse = new EnseignantResponse();
        enseignantResponse = modelMapper.map(enseignantDto,EnseignantResponse.class);

        return new ResponseEntity<EnseignantResponse>(enseignantResponse, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADD_ENSEIGNANT_AUTHORITY')")
    @PostMapping
    public ResponseEntity<EnseignantResponse> addEnseignant(@RequestBody EnseignantRequest enseignantRequest){
        EnseignantDto enseignantDto = new EnseignantDto();
        enseignantDto = modelMapper.map(enseignantRequest,EnseignantDto.class);


        EnseignantDto AddEnseignant = enseignantService.addEnseignant(enseignantDto);

        if(AddEnseignant==null)
            return new ResponseEntity<EnseignantResponse>(new EnseignantResponse(), HttpStatus.NOT_ACCEPTABLE);

        EnseignantResponse enseignantResponse = new EnseignantResponse();
        enseignantResponse = modelMapper.map(AddEnseignant,EnseignantResponse.class);

        return new ResponseEntity<EnseignantResponse>(enseignantResponse,HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('UPDATE_ENSEIGNANT_AUTHORITY')")
    @PutMapping(path="/{id}")
    public ResponseEntity<EnseignantResponse> updateEnseignant(@PathVariable String id,@RequestBody EnseignantRequest enseignantRequest){

        EnseignantDto enseignantDto = new EnseignantDto();
        enseignantDto = modelMapper.map(enseignantRequest,EnseignantDto.class);

        EnseignantDto UpdateEnseignant = enseignantService.updateEnseignant(id,enseignantDto);

        EnseignantResponse enseignantResponse = new EnseignantResponse();
        enseignantResponse = modelMapper.map(UpdateEnseignant,EnseignantResponse.class);

        return new ResponseEntity<EnseignantResponse>(enseignantResponse,HttpStatus.ACCEPTED);
    }

    @GetMapping(path="/sendVerification/{id}")
    public ResponseEntity<EnseignantResponse> sendVerification(@PathVariable String id){

        EnseignantDto enseignantDto = enseignantService.resendVerification(id);

        EnseignantResponse enseignantResponse = new EnseignantResponse();
        enseignantResponse = modelMapper.map(enseignantDto,EnseignantResponse.class);

        return new ResponseEntity<EnseignantResponse>(enseignantResponse, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('DELETE_ENSEIGNANT_AUTHORITY')")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteEnseignant(@PathVariable String id){
        enseignantService.deleteEnseignant(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
