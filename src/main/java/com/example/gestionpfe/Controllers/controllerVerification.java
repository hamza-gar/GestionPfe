package com.example.gestionpfe.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class controllerVerification {

    @GetMapping("/example")
    public void redirectToAngularPage(HttpServletResponse response) {
        response.setStatus(302);
        response.setHeader("Location", "http://localhost:4200/verification");
        }
}

