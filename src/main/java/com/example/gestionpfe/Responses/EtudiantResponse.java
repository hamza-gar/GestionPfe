package com.example.gestionpfe.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantResponse {
    private String idEtudiant;
    private Long apogee;
    private String cne;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private Boolean emailVerificationStatus;
    private String nomFiliere;
}
