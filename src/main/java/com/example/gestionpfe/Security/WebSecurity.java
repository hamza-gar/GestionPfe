package com.example.gestionpfe.Security;

import com.example.gestionpfe.Security.Administrateur.AdminAuthenticationFilter;
import com.example.gestionpfe.Security.Enseignant.EnseignantAuthenticationFilter;
import com.example.gestionpfe.Security.Etudiant.EtudiantAuthenticationFilter;

import com.example.gestionpfe.Services.EnseignantService;
import com.example.gestionpfe.Services.EtudiantService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@SuppressWarnings("deprecation")
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final EtudiantService etudiantDetailService;
    private final EnseignantService enseignantDetailService;
    private final EnseignantService adminDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(EtudiantService etudiantDetailService, EnseignantService enseignantDetailService, EnseignantService adminDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.etudiantDetailService = etudiantDetailService;
        this.enseignantDetailService = enseignantDetailService;
        this.adminDetailService = adminDetailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.ETUDIANT_SIGN_IN_URL).permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstants.ENSEIGNANT_SIGN_IN_URL).permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstants.ADMIN_SIGN_IN_URL).permitAll()
                .antMatchers(HttpMethod.GET,"/etudiants/verification/{id:[a-zA-Z0-9]{26}}").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(getEtudiantAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .addFilter(getEnseignantAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .addFilter(getAdminAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    protected EnseignantAuthenticationFilter getEnseignantAuthenticationFilter() throws Exception {
        final EnseignantAuthenticationFilter filter = new EnseignantAuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/enseignants/login");
        return filter;
    }

    protected EtudiantAuthenticationFilter getEtudiantAuthenticationFilter() throws Exception {
        final EtudiantAuthenticationFilter filter = new EtudiantAuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/etudiants/login");
        return filter;
    }
    protected AdminAuthenticationFilter getAdminAuthenticationFilter() throws Exception {
        final AdminAuthenticationFilter filter = new AdminAuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/admins/login");
        return filter;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(etudiantDetailService).passwordEncoder(bCryptPasswordEncoder);
        auth.userDetailsService(enseignantDetailService).passwordEncoder(bCryptPasswordEncoder);
        auth.userDetailsService(adminDetailService).passwordEncoder(bCryptPasswordEncoder);
    }
}
