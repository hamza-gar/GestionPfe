package com.example.gestionpfe.Security.Administrateur;

import com.example.gestionpfe.Entities.Administrateur;
import com.example.gestionpfe.Entities.Authority;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminPrincipal implements UserDetails {
    Administrateur administrateur;

    public AdminPrincipal(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Role role = administrateur.getRole();
        if (role == null) return grantedAuthorities;
        List<Authority> authorities = new ArrayList<>(role.getAuthority());

        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        if (authorities == null) return grantedAuthorities;
        for (Authority authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return administrateur.getEncryptedPassword();
    }

    @Override
    public String getUsername() {
        return administrateur.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
