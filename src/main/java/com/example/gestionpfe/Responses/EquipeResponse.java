package com.example.gestionpfe.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipeResponse {
    private String idEquipe;
    private int tailleEquipe;
    private Boolean isPrivate;
    private String cryptedPassword;
    private String driveLink;
    private String sujetId;
}
