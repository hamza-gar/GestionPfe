package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.EtudiantDto;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Requests.EtudiantRequest;
import com.example.gestionpfe.Responses.EtudiantResponse;
import com.example.gestionpfe.Services.EtudiantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {
    @Autowired
    EtudiantService etudiantService;

    //added now
    @GetMapping(path = "/{id}")
    public ResponseEntity<EtudiantResponse> getEtudiant(@PathVariable String id) {
        EtudiantDto etudiantdto = etudiantService.getEtudiantByIdEtudiant(id);
        EtudiantResponse etudiantResponse = new EtudiantResponse();
        BeanUtils.copyProperties(etudiantdto, etudiantResponse);
        return new ResponseEntity<EtudiantResponse>(etudiantResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/verification/{token}")
    public ResponseEntity<EtudiantResponse> verifyEtudiant(@PathVariable String token) {
        EtudiantDto etudiantDto = etudiantService.verifyEtudiant(token);
        EtudiantResponse etudiantResponse = new EtudiantResponse();
        BeanUtils.copyProperties(etudiantDto, etudiantResponse);

        return new ResponseEntity<EtudiantResponse>(etudiantResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EtudiantResponse> addEtudiant(@RequestBody EtudiantRequest etudiantrequest) {
        EtudiantDto etudiantdto = new EtudiantDto();
        BeanUtils.copyProperties(etudiantrequest, etudiantdto);

        EtudiantDto AddEtudiant = etudiantService.addEtudiant(etudiantdto);
        if(AddEtudiant==null)
            return new ResponseEntity<EtudiantResponse>(new EtudiantResponse(), HttpStatus.NOT_ACCEPTABLE);

        EtudiantResponse etudiantResponse = new EtudiantResponse();

        BeanUtils.copyProperties(AddEtudiant, etudiantResponse);

        return new ResponseEntity<EtudiantResponse>(etudiantResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EtudiantResponse> updateEtudiant(@PathVariable String id, @RequestBody EtudiantRequest etudiantrequest) {

        EtudiantDto etudiantdto = new EtudiantDto();
        BeanUtils.copyProperties(etudiantrequest, etudiantdto);

        EtudiantDto UpdateEtudiant = etudiantService.updateEtudiant(id, etudiantdto);

        EtudiantResponse etudiantResponse = new EtudiantResponse();

        BeanUtils.copyProperties(UpdateEtudiant, etudiantResponse);

        return new ResponseEntity<EtudiantResponse>(etudiantResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping(path="/sendVerification/{id}")
    public ResponseEntity<EtudiantResponse> sendVerification(@PathVariable String id){

        EtudiantDto etudiantdto = etudiantService.resendVerification(id);

        EtudiantResponse etudiantResponse = new EtudiantResponse();
        BeanUtils.copyProperties(etudiantdto, etudiantResponse);

        return new ResponseEntity<EtudiantResponse>(etudiantResponse, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteEtudiant(@PathVariable String id) {
        etudiantService.deleteEtudiant(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
