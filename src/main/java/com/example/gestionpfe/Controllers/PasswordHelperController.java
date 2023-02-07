package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Dto.PasswordHelperDto;
import com.example.gestionpfe.Entities.PasswordHelper;
import com.example.gestionpfe.Requests.PasswordHelperRequest;
import com.example.gestionpfe.Responses.EnseignantResponse;
import com.example.gestionpfe.Security.SecurityConstants;
import com.example.gestionpfe.Services.PasswordHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/forgotPassword")
public class PasswordHelperController {

    @Autowired
    PasswordHelperService passwordHelperService;

    @PostMapping
    public Boolean forgotPassword(@RequestBody PasswordHelperRequest passwordHelperRequest) {

        PasswordHelperDto passwordHelperDto = new PasswordHelperDto();

        passwordHelperDto.setEmail(passwordHelperRequest.getEmail());

        passwordHelperDto = passwordHelperService.createPasswordHelper(passwordHelperDto);

        return true;
    }

    @PostMapping(path="/checkKey")
    public ResponseEntity<?> checkKey(@RequestBody PasswordHelperRequest passwordHelperRequest, HttpServletResponse res) {
        HashMap<String, String> map = new HashMap<>();
        PasswordHelperDto passwordHelperDto = new PasswordHelperDto();

        passwordHelperDto.setEmail(passwordHelperRequest.getEmail());
        passwordHelperDto.setKey(passwordHelperRequest.getKey());
        Boolean operation = passwordHelperService.checkKey(passwordHelperDto);

        if (operation) {
            map.put("key", passwordHelperService.generateToken(passwordHelperRequest.getEmail()));
            res.addHeader(SecurityConstants.HEADER_STRING, operation ? passwordHelperService.generateToken(passwordHelperRequest.getEmail()) : "false");
        }


        return new ResponseEntity<>(map,HttpStatus.OK);
    }



    @PutMapping(path="/updatePass")
    public Boolean changePassword(@RequestBody PasswordHelperRequest passwordHelperRequest, HttpServletRequest req) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = principal.toString();

        if (!email.equals(passwordHelperRequest.getEmail()) && passwordHelperService.checkExpiration(req.getHeader(SecurityConstants.HEADER_STRING),passwordHelperRequest.getEmail())) {
            throw new RuntimeException("You are not allowed");
        }

        PasswordHelperDto passwordHelperDto = new PasswordHelperDto();

        passwordHelperDto.setEmail(passwordHelperRequest.getEmail());
        passwordHelperDto.setKey(passwordHelperRequest.getPassword());

        return passwordHelperService.updateUserPassword(passwordHelperDto);
    }
}
