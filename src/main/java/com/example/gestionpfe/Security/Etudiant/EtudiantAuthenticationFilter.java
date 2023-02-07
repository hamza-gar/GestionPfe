package com.example.gestionpfe.Security.Etudiant;

import com.example.gestionpfe.Dto.EtudiantDto;
import com.example.gestionpfe.Requests.EtudiantLoginRequest;
import com.example.gestionpfe.Security.SecurityConstants;
import com.example.gestionpfe.Services.EtudiantService;
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

public class    EtudiantAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public EtudiantAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            EtudiantLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), EtudiantLoginRequest.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                            FilterChain chain, Authentication auth)throws IOException , ServletException {
        String userName = ((EtudiantPrincipal)auth.getPrincipal()).getUsername();

        EtudiantService etudiantService = (EtudiantService) SpringApplicationContext.getBean("etudiantServiceImpl");
        EtudiantDto etudiantDto = etudiantService.getEtudiant(userName);

        String token = Jwts.builder().claim("id",etudiantDto.getIdEtudiant()).setSubject(userName).setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME)).
                signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();



        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        res.addHeader("etudiant_id",etudiantDto.getIdEtudiant());

        res.getWriter().write("{\"token\":\""+token+"\",\"id\":\""+etudiantDto.getIdEtudiant()+"\"}");
    }
}
