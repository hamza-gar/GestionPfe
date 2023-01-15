package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.AdminDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends UserDetailsService {
    AdminDto addAdmin(AdminDto adminDto);
    AdminDto getAdmin(String email);
    AdminDto getAdminByIdAdmin(String id);
    AdminDto updateAdmin(String id,AdminDto adminDto);
    void deleteAdmin(String id);
}
