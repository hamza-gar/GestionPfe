package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.AdminDto;
import com.example.gestionpfe.Dto.DomaineDto;
import com.example.gestionpfe.Requests.AdminRequest;
import com.example.gestionpfe.Requests.DomaineRequest;
import com.example.gestionpfe.Responses.AdminResponse;
import com.example.gestionpfe.Responses.DomaineResponse;
import com.example.gestionpfe.Services.AdminService;
import com.example.gestionpfe.Services.DomaineService;
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
@RequestMapping("/domaines")
public class DomainesController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    DomaineService domaineService;

    @PreAuthorize("hasAuthority('GET_DOMAINE_AUTHORITY')")
    @GetMapping(path="/{id}")
    public ResponseEntity<DomaineResponse> getDomaine(@PathVariable String id){
        DomaineDto domainDto = domaineService.getDomaineById(id);
        DomaineResponse  domaineResponse = new DomaineResponse();
        domaineResponse = modelMapper.map(domainDto,DomaineResponse.class);
        return new ResponseEntity<DomaineResponse>(domaineResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('GET_ALL_DOMAINE_AUTHORITY')")
    @GetMapping
    public List<DomaineResponse> getAllDomaines(@RequestParam(value = "page") int page,@RequestParam(value="limit" )int limit){
        List<DomaineResponse> domaineResponse = new ArrayList<>();
        List<DomaineDto> domaines = domaineService.getAllDomaines(page,limit);

        for(DomaineDto domainDto:domaines){
            DomaineResponse domaine = new DomaineResponse();
            domaine = modelMapper.map(domainDto,DomaineResponse.class);

            domaineResponse.add(domaine);
        }
        return domaineResponse;
    }


    @PreAuthorize("hasAuthority('ADD_DOMAINE_AUTHORITY')")
    @PostMapping
    public ResponseEntity<DomaineResponse> addDomaine(@RequestBody DomaineRequest domaineRequest){
        DomaineDto domainDto = new DomaineDto();
        domainDto = modelMapper.map(domaineRequest,DomaineDto.class);

        DomaineDto AddDomaine = domaineService.addDomaine(domainDto);

        DomaineResponse  domaineResponse = new DomaineResponse();
        domaineResponse = modelMapper.map(AddDomaine,DomaineResponse.class);

        return new ResponseEntity<DomaineResponse>(domaineResponse,HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('UPDATE_DOMAINE_AUTHORITY')")
    @PutMapping(path="/{id}")
    public ResponseEntity<DomaineResponse> updateDomaine(@PathVariable String id,@RequestBody DomaineRequest domaineRequest){

        DomaineDto domainDto = new DomaineDto();
        domainDto = modelMapper.map(domaineRequest,DomaineDto.class);

        DomaineDto UpdateDomaine = domaineService.updateDomaine(id,domainDto);

        DomaineResponse  domaineResponse = new DomaineResponse();
        domaineResponse = modelMapper.map(UpdateDomaine,DomaineResponse.class);

        return new ResponseEntity<DomaineResponse>(domaineResponse,HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('DELETE_DOMAINE_AUTHORITY')")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteDomaine(@PathVariable String id){
        domaineService.deleteDomaine(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
