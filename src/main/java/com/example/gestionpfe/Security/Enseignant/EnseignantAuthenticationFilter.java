package com.example.gestionpfe.Security.Enseignant;

import com.example.gestionpfe.Dto.EnseignantDto;
import com.example.gestionpfe.Requests.EnseignantLoginRequest;

import com.example.gestionpfe.Security.SecurityConstants;
import com.example.gestionpfe.Services.EnseignantService;
import com.example.gestionpfe.SpringApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class EnseignantAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public EnseignantAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            EnseignantLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), EnseignantLoginRequest.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                            FilterChain chain, Authentication auth)throws IOException , ServletException {
        String userName = ((EnseignantPrincipal)auth.getPrincipal()).getUsername();
        String token = Jwts.builder().setSubject(userName).setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME)).
                signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();
        EnseignantService enseignantService = (EnseignantService) SpringApplicationContext.getBean("enseignantServiceImpl");
        EnseignantDto enseignantDto = enseignantService.getEnseignant(userName);
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        res.addHeader("enseignant_id",enseignantDto.getIdEnseignant());

        res.getWriter().write("{\"token\":\""+token+"\",\"id\":\""+enseignantDto.getIdEnseignant()+"\"}");
    }
}
