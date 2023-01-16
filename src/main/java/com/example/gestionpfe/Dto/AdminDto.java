package com.example.gestionpfe.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    private long id;
    private String idAdmin;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String encryptedPassword;
    private int authorityLevel;
}
