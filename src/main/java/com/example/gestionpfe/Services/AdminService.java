package com.example.gestionpfe.Services;

import com.example.gestionpfe.Dto.AdminDto;

import com.example.gestionpfe.Dto.DomaineDto;
import com.example.gestionpfe.Entities.Administrateur;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminService extends UserDetailsService {
    AdminDto addAdmin(AdminDto adminDto);
    AdminDto getAdmin(String email);
    AdminDto getAdminByIdAdmin(String id);
    AdminDto updateAdmin(String id,AdminDto adminDto);
    void deleteAdmin(String id);
    List<AdminDto> getAllAdmins(int page, int limit);
}
