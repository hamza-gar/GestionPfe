package com.example.gestionpfe.Controllers;


import com.example.gestionpfe.Requests.InvitationRequest;
import com.example.gestionpfe.Requests.JuryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jurys")
public class JuryController {
    @PutMapping
    public ResponseEntity<Boolean> reponseInvitation(@RequestBody InvitationRequest invitationRequest){

        return null;
    }
}
