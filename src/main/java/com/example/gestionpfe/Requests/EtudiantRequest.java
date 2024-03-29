package com.example.gestionpfe.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantRequest {
    private Long apogee;
    private String cne;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private String password;

}
