package com.example.gestionpfe.Dto;

import com.example.gestionpfe.Entities.Equipe;
import com.example.gestionpfe.Entities.Filiere;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDto implements Serializable {
    private long id;
    private String idEtudiant;
    private Long apogee;
    private String cne;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private String nomFiliere;
    private String password;
    private Filiere filiere;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus;

}
