package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.DomaineDto;
import com.example.gestionpfe.Dto.EtudiantDto;
import com.example.gestionpfe.Dto.RemarqueDto;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Exceptions.exception;
import com.example.gestionpfe.Requests.EtudiantRequest;
import com.example.gestionpfe.Responses.DomaineResponse;
import com.example.gestionpfe.Responses.ErrorMessages;
import com.example.gestionpfe.Responses.EtudiantResponse;
import com.example.gestionpfe.Responses.RemarqueResponse;
import com.example.gestionpfe.Services.EtudiantService;
import com.example.gestionpfe.Services.RemarqueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    EtudiantService etudiantService;

    @Autowired
    controllerVerification controllerVeri;

    @Autowired
    RemarqueService remarqueService;

    //added now
    @PreAuthorize("hasAuthority('GET_BY_IDETUDIANT_AUTHORITY')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<EtudiantResponse> getEtudiant(@PathVariable String id) {
        EtudiantDto etudiantdto = etudiantService.getEtudiantByIdEtudiant(id);
        EtudiantResponse etudiantResponse = new EtudiantResponse();
        etudiantResponse = modelMapper.map(etudiantdto, EtudiantResponse.class);
        etudiantResponse.setNomFiliere(etudiantdto.getFiliere().getNomFiliere());
        return new ResponseEntity<EtudiantResponse>(etudiantResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/byEmail/{email}")
    public ResponseEntity<EtudiantResponse> getEtudiantByEmail(@PathVariable String email) {
        EtudiantDto etudiantdto = etudiantService.getEtudiantByEmail(email);
        EtudiantResponse etudiantResponse = new EtudiantResponse();
        etudiantResponse = modelMapper.map(etudiantdto, EtudiantResponse.class);
        etudiantResponse.setNomFiliere(etudiantdto.getFiliere().getNomFiliere());
        return new ResponseEntity<EtudiantResponse>(etudiantResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/verification/{token}")
    public void verifyEtudiant(@PathVariable String token , HttpServletResponse response) {
        EtudiantDto etudiantDto = etudiantService.verifyEtudiant(token);
        EtudiantResponse etudiantResponse = new EtudiantResponse();
        etudiantResponse = modelMapper.map(etudiantDto, EtudiantResponse.class);

         controllerVeri.redirectToAngularPage(response);
    }
    @PreAuthorize("hasAuthority('GET_ALL_ETUDIANT_AUTHORITY')")
    @GetMapping
    public List<EtudiantResponse> getAllEtudiants(@RequestParam(value = "page") int page, @RequestParam(value="limit" )int limit){
        List<EtudiantResponse> etudiantResponse = new ArrayList<>();
        List<EtudiantDto> etudiants = etudiantService.getAllEtudiants(page,limit);

        for(EtudiantDto etudiantDto:etudiants){
            EtudiantResponse etudiant = new EtudiantResponse();
            etudiant = modelMapper.map(etudiantDto,EtudiantResponse.class);

            etudiantResponse.add(etudiant);
        }
        return etudiantResponse;
    }

    @PostMapping
    public ResponseEntity<EtudiantResponse> addEtudiant(@RequestBody EtudiantRequest etudiantrequest) {

        EtudiantDto etudiantdto = new EtudiantDto();
        etudiantdto = modelMapper.map(etudiantrequest, EtudiantDto.class);

        EtudiantDto AddEtudiant = etudiantService.addEtudiant(etudiantdto);
        if(AddEtudiant==null)
            return new ResponseEntity<EtudiantResponse>(new EtudiantResponse(), HttpStatus.NOT_ACCEPTABLE);

        EtudiantResponse etudiantResponse = new EtudiantResponse();
        etudiantResponse = modelMapper.map(AddEtudiant, EtudiantResponse.class);

        return new ResponseEntity<EtudiantResponse>(etudiantResponse, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('UPDATE_ETUDIANT_AUTHORITY')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<EtudiantResponse> updateEtudiant(@PathVariable String id, @RequestBody EtudiantRequest etudiantrequest) {

        EtudiantDto etudiantdto = new EtudiantDto();
        etudiantdto = modelMapper.map(etudiantrequest, EtudiantDto.class);

        EtudiantDto UpdateEtudiant = etudiantService.updateEtudiant(id, etudiantdto);

        EtudiantResponse etudiantResponse = new EtudiantResponse();
        etudiantResponse = modelMapper.map(UpdateEtudiant, EtudiantResponse.class);

        return new ResponseEntity<EtudiantResponse>(etudiantResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping(path="/sendVerification/{id}")
    public ResponseEntity<EtudiantResponse> sendVerification(@PathVariable String id){

        EtudiantDto etudiantdto = etudiantService.resendVerification(id);

        EtudiantResponse etudiantResponse = new EtudiantResponse();
        etudiantResponse = modelMapper.map(etudiantdto, EtudiantResponse.class);
        return new ResponseEntity<EtudiantResponse>(etudiantResponse, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('DELETE_ETUDIANT_AUTHORITY')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteEtudiant(@PathVariable String id) {
        etudiantService.deleteEtudiant(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/voirremarque")
    public ResponseEntity<List<RemarqueResponse>> voirRemarque() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        List<RemarqueDto> remarqueDtos = remarqueService.getMyRemarques(username);
        List<RemarqueResponse> remarqueResponses = new ArrayList<>();
        for (RemarqueDto remarqueDto : remarqueDtos) {
            RemarqueResponse remarqueResponse = new RemarqueResponse();
            remarqueResponse = modelMapper.map(remarqueDto, RemarqueResponse.class);
            remarqueResponses.add(remarqueResponse);
        }
        return new ResponseEntity<List<RemarqueResponse>>(remarqueResponses, HttpStatus.OK);
    }

    @GetMapping(path = "/estpostulant")
    public ResponseEntity<Boolean> estPostulant() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        boolean estPostulant = etudiantService.estPostulant(username);
        return new ResponseEntity<Boolean>(estPostulant, HttpStatus.OK);
    }

    @GetMapping(path = "/isWorking")
    public ResponseEntity<Boolean> isWorking() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        boolean isWorking = etudiantService.isWorking(username);
        return new ResponseEntity<Boolean>(isWorking, HttpStatus.OK);
    }

    @GetMapping(path = "/hasFinished")
    public ResponseEntity<Boolean> hasFinished() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        boolean isWorking = etudiantService.hasFinished(username);
        return new ResponseEntity<Boolean>(isWorking, HttpStatus.OK);
    }

    @GetMapping(path = "/hasSoutenance")
    public ResponseEntity<Boolean> hasSoutenance() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        boolean isWorking = etudiantService.hasSoutenance(username);
        return new ResponseEntity<Boolean>(isWorking, HttpStatus.OK);
    }

}
