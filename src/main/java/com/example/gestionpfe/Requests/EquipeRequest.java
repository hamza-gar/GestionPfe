package com.example.gestionpfe.Requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipeRequest {
    private Boolean isPrivate;
    private String cryptedPassword;
    private String idEquipe;
    private String driveLink;
    private String sujetId;
}
