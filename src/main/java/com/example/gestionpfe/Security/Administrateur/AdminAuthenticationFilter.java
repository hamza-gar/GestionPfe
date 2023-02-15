package com.example.gestionpfe.Security.Administrateur;

import com.example.gestionpfe.Dto.AdminDto;
import com.example.gestionpfe.Requests.AdminLoginRequest;

import com.example.gestionpfe.Security.SecurityConstants;
import com.example.gestionpfe.Services.AdminService;
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

public class AdminAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;


    public AdminAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            System.out.println("trying to authenticate");
            AdminLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), AdminLoginRequest.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
        }catch (IOException e){
            System.out.println("error in authentication");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
                                            FilterChain chain, Authentication auth)throws IOException , ServletException {
        System.out.println("authentication successful");
        String userName = ((AdminPrincipal)auth.getPrincipal()).getUsername();
        String token = Jwts.builder().setSubject(userName).setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME)).
                signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();
        AdminService adminService = (AdminService) SpringApplicationContext.getBean("adminServiceImpl");
        AdminDto adminDto = adminService.getAdmin(userName);
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        res.addHeader("admin_id",adminDto.getIdAdmin());

        res.getWriter().write("{\"token\":\""+token+"\",\"admin_id\":\""+adminDto.getIdAdmin()+"\",\"user\":\"admin\"}");
    }
}
