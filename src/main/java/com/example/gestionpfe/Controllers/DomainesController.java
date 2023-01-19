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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/domaines")
public class DomainesController {
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    DomaineService domaineService;

    @GetMapping(path="/{id}")
    public ResponseEntity<DomaineResponse> getDomaine(@PathVariable String id){
        DomaineDto domainDto = domaineService.getDomaineById(id);
        DomaineResponse  domaineResponse = new DomaineResponse();
        domaineResponse = modelMapper.map(domainDto,DomaineResponse.class);
        //BeanUtils.copyProperties(domainDto,domaineResponse);
        return new ResponseEntity<DomaineResponse>(domaineResponse, HttpStatus.OK);
    }

    @GetMapping
    public List<DomaineResponse> getAllDomaines(@RequestParam(value = "page") int page,@RequestParam(value="limit" )int limit){
        List<DomaineResponse> domaineResponse = new ArrayList<>();
        List<DomaineDto> domaines = domaineService.getAllDomaines(page,limit);

        for(DomaineDto domainDto:domaines){
            DomaineResponse domaine = new DomaineResponse();
            domaine = modelMapper.map(domainDto,DomaineResponse.class);
            //BeanUtils.copyProperties(domainDto,domaine);

            domaineResponse.add(domaine);
        }
        return domaineResponse;
    }


    @PostMapping
    public ResponseEntity<DomaineResponse> addDomaine(@RequestBody DomaineRequest domaineRequest){
        DomaineDto domainDto = new DomaineDto();
        domainDto = modelMapper.map(domaineRequest,DomaineDto.class);
        //BeanUtils.copyProperties(domaineRequest,domainDto);

        DomaineDto AddDomaine = domaineService.addDomaine(domainDto);

        DomaineResponse  domaineResponse = new DomaineResponse();
        domaineResponse = modelMapper.map(AddDomaine,DomaineResponse.class);
        //BeanUtils.copyProperties(AddDomaine,domaineResponse);

        return new ResponseEntity<DomaineResponse>(domaineResponse,HttpStatus.CREATED);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<DomaineResponse> updateDomaine(@PathVariable String id,@RequestBody DomaineRequest domaineRequest){

        DomaineDto domainDto = new DomaineDto();
        domainDto = modelMapper.map(domaineRequest,DomaineDto.class);
        //BeanUtils.copyProperties(domaineRequest,domainDto);

        DomaineDto UpdateDomaine = domaineService.updateDomaine(id,domainDto);

        DomaineResponse  domaineResponse = new DomaineResponse();
        domaineResponse = modelMapper.map(UpdateDomaine,DomaineResponse.class);
        //BeanUtils.copyProperties(UpdateDomaine,domaineResponse);

        return new ResponseEntity<DomaineResponse>(domaineResponse,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Object> deleteDomaine(@PathVariable String id){
        domaineService.deleteDomaine(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
