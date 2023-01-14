package com.example.gestionpfe.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponse {
    private String idEnseignant;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
}
