package com.example.gestionpfe.Security.Etudiant;

import com.example.gestionpfe.Entities.Authority;
import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EtudiantPrincipal implements UserDetails {
    Etudiant etudiant;

    public EtudiantPrincipal(Etudiant etudiantEntity) {
        etudiant = etudiantEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Role role = etudiant.getRole();
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
        return etudiant.getEncryptedPassword();
    }

    @Override
    public String getUsername() {
        return etudiant.getEmail();
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
        return etudiant.getEmailVerificationStatus();
    }
}
