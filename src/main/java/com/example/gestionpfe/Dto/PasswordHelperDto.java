package com.example.gestionpfe.Dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PasswordHelperDto {
    private long id;
    private String idPasswordHelper;
    private String key;
    private String email;
    private Boolean isEtudiant;
}
