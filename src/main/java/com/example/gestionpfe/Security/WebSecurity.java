package com.example.gestionpfe.Security;

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
public class WebSecurity extends WebSecurityConfigurerAdapter{
    private final EtudiantService etudiantDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(EtudiantService etudiantDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.etudiantDetailService = etudiantDetailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST,SecurityConstants.SIGN_IN_URL).
                permitAll().anyRequest().authenticated().and().addFilter(getAuthenticationFilter()).addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    protected AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/users/login");
        return filter;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(etudiantDetailService).passwordEncoder(bCryptPasswordEncoder);
    }
}
