package com.example.gestionpfe.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipeDto {
    private long id;
    private String idEquipe;
    private int tailleEquipe;
    private Boolean isPrivate;
    private String cryptedPassword;
    private long sujetId;
}
