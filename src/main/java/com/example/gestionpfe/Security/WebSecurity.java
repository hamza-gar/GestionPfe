package com.example.gestionpfe.Security;

import com.example.gestionpfe.Repositories.AdminRepository;
import com.example.gestionpfe.Repositories.EnseignantRepository;
import com.example.gestionpfe.Repositories.EtudiantRepository;
import com.example.gestionpfe.Security.Administrateur.AdminAuthenticationFilter;
import com.example.gestionpfe.Security.Enseignant.EnseignantAuthenticationFilter;
import com.example.gestionpfe.Security.Etudiant.EtudiantAuthenticationFilter;

import com.example.gestionpfe.Services.AdminService;
import com.example.gestionpfe.Services.EnseignantService;
import com.example.gestionpfe.Services.EtudiantService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final EtudiantService etudiantDetailService;
    private final EnseignantService enseignantDetailService;
    private final AdminService adminDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EtudiantRepository etudiantRepository;
    private final EnseignantRepository enseignantRepository;
    private final AdminRepository adminRepository;

    public WebSecurity(EtudiantService etudiantDetailService,
                       EnseignantRepository enseignantRepository,
                       EtudiantRepository etudiantRepository,
                       EnseignantService enseignantDetailService,
                       AdminService adminDetailService,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       AdminRepository adminRepository) {
        this.etudiantDetailService = etudiantDetailService;
        this.enseignantDetailService = enseignantDetailService;
        this.adminDetailService = adminDetailService;
        this.etudiantRepository = etudiantRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.enseignantRepository = enseignantRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.ETUDIANT_SIGN_IN_URL).permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstants.ENSEIGNANT_SIGN_IN_URL).permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstants.ADMIN_SIGN_IN_URL).permitAll()
                .antMatchers("/domaines").permitAll()
                .antMatchers(HttpMethod.GET, "/enseignants/verification/{id:[a-zA-Z0-9]{26}}").permitAll()
                .antMatchers(HttpMethod.GET, "/etudiants/verification/{id:[a-zA-Z0-9]{26}}").permitAll()
                .antMatchers(HttpMethod.GET, "/etudiants/*").hasRole("ETUDIANT")
                .antMatchers(HttpMethod.GET, "/enseignants/*").hasRole("ENSEIGNANT")
                .antMatchers(HttpMethod.GET,"/admins/*").hasRole("SUPERADMIN")
                .anyRequest().authenticated()
                .and()
                .addFilter(getEtudiantAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager(), adminRepository, etudiantRepository, enseignantRepository))
                .addFilter(getEnseignantAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager(), adminRepository, etudiantRepository, enseignantRepository))
                .addFilter(getAdminAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager(), adminRepository, etudiantRepository, enseignantRepository))
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
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(etudiantDetailService).passwordEncoder(bCryptPasswordEncoder);
        auth.userDetailsService(enseignantDetailService).passwordEncoder(bCryptPasswordEncoder);
        auth.userDetailsService(adminDetailService).passwordEncoder(bCryptPasswordEncoder);
    }
}
