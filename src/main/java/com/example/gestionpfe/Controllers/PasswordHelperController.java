package com.example.gestionpfe.Controllers;

import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Dto.PasswordHelperDto;
import com.example.gestionpfe.Entities.PasswordHelper;
import com.example.gestionpfe.Responses.EnseignantResponse;
import com.example.gestionpfe.Services.PasswordHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forgotPassword")
public class PasswordHelperController {

    @Autowired
    PasswordHelperService passwordHelperService;

    @PostMapping
    public Boolean forgotPassword(@RequestParam(value = "email") String email) {

        PasswordHelperDto passwordHelperDto = new PasswordHelperDto();

        passwordHelperDto.setEmail(email);

        passwordHelperDto = passwordHelperService.createPasswordHelper(passwordHelperDto);

        return true;
    }

    @GetMapping
    public Boolean checkKey(@RequestParam(value = "key") String key, @RequestParam(value = "email") String email) {

        PasswordHelperDto passwordHelperDto = new PasswordHelperDto();

        passwordHelperDto.setEmail(email);
        passwordHelperDto.setKey(key);

        return passwordHelperService.checkKey(passwordHelperDto);
    }

    @PutMapping Boolean changePassword(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        PasswordHelperDto passwordHelperDto = new PasswordHelperDto();

        passwordHelperDto.setEmail(email);
        passwordHelperDto.setKey(password);

        return passwordHelperService.updateUserPassword(passwordHelperDto);
    }
}
