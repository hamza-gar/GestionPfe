package com.example.gestionpfe.Security;

import com.example.gestionpfe.Entities.Administrateur;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Repositories.AdminRepository;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.EtudiantRepository;
import com.example.gestionpfe.Security.Administrateur.AdminPrincipal;
import com.example.gestionpfe.Security.Enseignant.EnseignantPrincipal;
import com.example.gestionpfe.Security.Etudiant.EtudiantPrincipal;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private EtudiantRepository etudiantRepository;
    private EnseignantRepository enseignantRepository;
    private AdminRepository adminRepository;


    public AuthorizationFilter(AuthenticationManager authenticationManager,
                               AdminRepository adminRepository,
                               EtudiantRepository etudiantRepository,
                               EnseignantRepository enseignantRepository) {
        super(authenticationManager);
        this.etudiantRepository = etudiantRepository;
        this.adminRepository = adminRepository;
        this.enseignantRepository = enseignantRepository;

    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(SecurityConstants.HEADER_STRING);
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);

        if (token != null) {
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
            String user = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET).parseClaimsJws(token).getBody().getSubject();
            if (user != null) {
                Etudiant etudiant = etudiantRepository.findByEmail(user);
                if (etudiant != null) {
                    EtudiantPrincipal etudiantPrincipal = new EtudiantPrincipal(etudiant);
                    return new UsernamePasswordAuthenticationToken(user, null, etudiantPrincipal.getAuthorities());
                }
                Enseignant enseignant = enseignantRepository.findByEmail(user);
                if (enseignant != null) {
                    EnseignantPrincipal enseignantPrincipal = new EnseignantPrincipal(enseignant);
                    return new UsernamePasswordAuthenticationToken(user, null, enseignantPrincipal.getAuthorities());
                }
                Administrateur administrateur = adminRepository.findByEmail(user);
                if (administrateur != null) {
                    AdminPrincipal adminPrincipal = new AdminPrincipal(administrateur);
                    return new UsernamePasswordAuthenticationToken(user, null, adminPrincipal.getAuthorities());
                }
            }
            return null;
        }
        return null;
    }

}
