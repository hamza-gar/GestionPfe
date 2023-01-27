package com.example.gestionpfe.Controllers;


import com.example.gestionpfe.Dto.RendezvousDto;
import com.example.gestionpfe.Entities.Rendezvous;
import com.example.gestionpfe.Responses.RendezvousResponse;
import com.example.gestionpfe.Services.RendezvousService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rendezvous")
public class RendezvousController {
    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(RendezvousController.class);
    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private RendezvousService rendezvousService;

    @PreAuthorize("hasAuthority('GET_BY_IDRENDEZVOUS_AUTHORITY')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<RendezvousResponse>getRendezvousByIdRendezvous(@PathVariable String id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        RendezvousDto rendezvousDto = rendezvousService.getRendezvousByIdRendezvous(username,id);
        RendezvousResponse rendezvousResponse = new RendezvousResponse();
        rendezvousResponse = modelMapper.map(rendezvousDto, RendezvousResponse.class);
        return ResponseEntity.ok(rendezvousResponse);
    }

    @PreAuthorize("hasAuthority('GET_ALL_RENDEZVOUS_AUTHORITY')")
    @GetMapping
    public ResponseEntity<List<RendezvousResponse>>   getAllRendezvous(@RequestParam(value= "page") int page, @RequestParam(value = "limit") int limit){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        List<RendezvousResponse> rendezvousResponse = new ArrayList<>();
        List<RendezvousDto> rendezvous = rendezvousService.getAllRendezvous(username,page,limit);

        for(RendezvousDto rendezvousDto : rendezvous){
            RendezvousResponse rendezvous1 = new RendezvousResponse();
            rendezvous1 = modelMapper.map(rendezvousDto, RendezvousResponse.class);

            rendezvousResponse.add(rendezvous1);

        }
        return ResponseEntity.ok(rendezvousResponse);
    }



    // @PreAuthorize("hasAuthority('ADD_RENDEZVOUS_AUTHORITY')")
    @PostMapping
    public ResponseEntity<RendezvousResponse> addRendezvous(@RequestBody Rendezvous rendezvous){
       RendezvousDto rendezvousDto = new RendezvousDto();
         rendezvousDto = modelMapper.map(rendezvous, RendezvousDto.class);

        RendezvousDto rendezvousDto1 = rendezvousService.addRendezvous(rendezvousDto);
        RendezvousResponse rendezvousResponse = new RendezvousResponse();
        rendezvousResponse = modelMapper.map(rendezvousDto1, RendezvousResponse.class);
        return ResponseEntity.ok(rendezvousResponse);
    }

    // @PreAuthorize("hasAuthority('UPDATE_RENDEZVOUS_AUTHORITY')")
    @PutMapping(path = "/{id}")
    public ResponseEntity<RendezvousResponse> updateRendezvous(@PathVariable String id, @RequestBody Rendezvous rendezvous){
        RendezvousDto rendezvousDto = new RendezvousDto();

        if(rendezvousDto==null) throw new RuntimeException("rendezvous not found");

        rendezvousDto = modelMapper.map(rendezvous, RendezvousDto.class);

        RendezvousDto rendezvousDto1 = rendezvousService.updateRendezvous(id,rendezvousDto);
        RendezvousResponse rendezvousResponse = new RendezvousResponse();

        rendezvousResponse = modelMapper.map(rendezvousDto1, RendezvousResponse.class);
        return ResponseEntity.ok(rendezvousResponse);
    }

    // @PreAuthorize("hasAuthority('DELETE_RENDEZVOUS_AUTHORITY')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteRendezvous(@PathVariable String id){
        rendezvousService.deleteRendezvous(id);
        return ResponseEntity.noContent().build();
    }

}
