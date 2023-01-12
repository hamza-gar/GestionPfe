package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.EtudiantDto;
import com.example.gestionpfe.Requests.EtudiantRequest;
import com.example.gestionpfe.Responses.EtudiantResponse;
import com.example.gestionpfe.Services.EtudiantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {
    @Autowired
    EtudiantService etduiantService;

    //added now
    @GetMapping(path="/{id}")
    public EtudiantResponse getEtudiant(@PathVariable String id){
        EtudiantDto etudiantdto = etduiantService.getEtudiantByIdEtudiant(id);
        EtudiantResponse  etudiantResponse = new EtudiantResponse();
        BeanUtils.copyProperties(etudiantdto,etudiantResponse);
        return etudiantResponse;
    }

    @PostMapping
    public EtudiantResponse addEtudiant(@RequestBody EtudiantRequest etudiantrequest){
        EtudiantDto etudiantdto = new EtudiantDto();
        BeanUtils.copyProperties(etudiantrequest,etudiantdto);

        EtudiantDto AddEtudiant = etduiantService.addEtudiant(etudiantdto);

        EtudiantResponse etudiantResponse = new EtudiantResponse();

        BeanUtils.copyProperties(AddEtudiant,etudiantResponse);

        return etudiantResponse;
    }

    @PutMapping
    public String updateEtudiant(){
        return "update etudiant was called";
    }
    @DeleteMapping
    public String deleteEtudiant(){
        return "delete etudiant was called";
    }
}
