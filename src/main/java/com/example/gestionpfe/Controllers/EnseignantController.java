package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Dto.EtablissementDto;
import com.example.gestionpfe.Entities.Departement;
import com.example.gestionpfe.Requests.EnseignantRequest;
import com.example.gestionpfe.Responses.EnseignantResponse;
import com.example.gestionpfe.Responses.EtablissementResponse;
import com.example.gestionpfe.Services.EnseignantService;
import com.example.gestionpfe.Services.EtablissementService;
import com.example.gestionpfe.Services.SujetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    EnseignantService enseignantService;

    @Autowired
    SujetService sujetService;

    @Autowired
    controllerVerification controllerVeri;

    @Autowired
    EtablissementService etablissementService;

    //added now
    @PreAuthorize("hasAuthority('GET_BY_IDENSEIGNANT_AUTHORITY')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<EnseignantResponse> getEnseignant(@PathVariable String id) {
        EnseignantDto enseignantDto = enseignantService.getEnseignantByIdEnseignant(id);
        EnseignantResponse enseignantResponse = new EnseignantResponse();
        enseignantResponse = modelMapper.map(enseignantDto, EnseignantResponse.class);
        enseignantResponse.setNomDepartement(enseignantDto.getDepartement().getNomDepartement());
        return new ResponseEntity<EnseignantResponse>(enseignantResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GET_ALL_ENSEIGNANT_AUTHORITY')")
    @GetMapping
    public List<EnseignantResponse> getAllEnseignants(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
        List<EnseignantResponse> enseignantResponse = new ArrayList<>();
        List<EnseignantDto> enseignants = enseignantService.getAllEnseignants(page, limit);

        for (EnseignantDto enseignantDto : enseignants) {
            EnseignantResponse enseignant = new EnseignantResponse();
            enseignant = modelMapper.map(enseignantDto, EnseignantResponse.class);

            enseignantResponse.add(enseignant);
        }
        return enseignantResponse;
    }

    @GetMapping(path = "/verification/{token}")
    public void verifyEnseignant(@PathVariable String token, HttpServletResponse response) {
        EnseignantDto enseignantDto = enseignantService.verifyEnseignant(token);
        EnseignantResponse enseignantResponse = new EnseignantResponse();
        enseignantResponse = modelMapper.map(enseignantDto, EnseignantResponse.class);

        controllerVeri.redirectToAngularPage(response);
    }

    @PostMapping
    public ResponseEntity<EnseignantResponse> addEnseignant(@RequestBody EnseignantRequest enseignantRequest) {
        Departement departement = new Departement();
        departement.setIdDepartement(enseignantRequest.getIdDepartement());

        EnseignantDto enseignantDto = new EnseignantDto();
        enseignantDto = modelMapper.map(enseignantRequest, EnseignantDto.class);

        enseignantDto.setDepartement(departement);
        EnseignantDto addEnseignant = enseignantService.addEnseignant(enseignantDto);

        if (addEnseignant == null)
            return new ResponseEntity<EnseignantResponse>(new EnseignantResponse(), HttpStatus.NOT_ACCEPTABLE);

        EnseignantResponse enseignantResponse = new EnseignantResponse();
        enseignantResponse = modelMapper.map(addEnseignant, EnseignantResponse.class);

        return new ResponseEntity<EnseignantResponse>(enseignantResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('UPDATE_ENSEIGNANT_AUTHORITY')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<EnseignantResponse> updateEnseignant(@PathVariable String id, @RequestBody EnseignantRequest enseignantRequest) {

        EnseignantDto enseignantDto = new EnseignantDto();
        enseignantDto = modelMapper.map(enseignantRequest, EnseignantDto.class);

        EnseignantDto UpdateEnseignant = enseignantService.updateEnseignant(id, enseignantDto);

        EnseignantResponse enseignantResponse = new EnseignantResponse();
        enseignantResponse = modelMapper.map(UpdateEnseignant, EnseignantResponse.class);

        return new ResponseEntity<EnseignantResponse>(enseignantResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/sendVerification/{id}")
    public ResponseEntity<EnseignantResponse> sendVerification(@PathVariable String id) {

        EnseignantDto enseignantDto = enseignantService.resendVerification(id);

        EnseignantResponse enseignantResponse = new EnseignantResponse();
        enseignantResponse = modelMapper.map(enseignantDto, EnseignantResponse.class);

        return new ResponseEntity<EnseignantResponse>(enseignantResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DELETE_ENSEIGNANT_AUTHORITY')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteEnseignant(@PathVariable String id) {
        enseignantService.deleteEnseignant(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping(path = "/password")
    public ResponseEntity<EnseignantResponse> updatePassword(@RequestParam(value = "email") String email, @RequestParam(value = "key") String key, @RequestParam(value = "password") String password) {
        EnseignantDto enseignantDto = new EnseignantDto();
        enseignantDto.setEmail(email);

//        EnseignantDto UpdateEnseignant = enseignantService.updatePassword(enseignantDto);

        EnseignantResponse enseignantResponse = new EnseignantResponse();
//        enseignantResponse = modelMapper.map(UpdateEnseignant,EnseignantResponse.class);

        return new ResponseEntity<EnseignantResponse>(enseignantResponse, HttpStatus.ACCEPTED);
    }



//    @PostMapping(path="/addSujet")
//    public ResponseEntity<SujetResponse> addSujet(@RequestBody SujetRequest sujetRequest){
//        SujetDto sujetDto = new SujetDto();
//        sujetDto = modelMapper.map(sujetRequest,SujetDto.class);
//
//
//        SujetDto AddSujet = sujetService.addSujet(sujetDto);
//
//        if(AddSujet==null)
//            return new ResponseEntity<SujetResponse>(new SujetResponse(), HttpStatus.NOT_ACCEPTABLE);
//
//        SujetResponse sujetResponse = new SujetResponse();
//        sujetResponse = modelMapper.map(AddSujet,SujetResponse.class);
//
//        return new ResponseEntity<SujetResponse>(sujetResponse,HttpStatus.CREATED);
//    }

}
