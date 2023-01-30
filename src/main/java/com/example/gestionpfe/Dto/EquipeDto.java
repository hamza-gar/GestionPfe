package com.example.gestionpfe.Dto;

import com.example.gestionpfe.Entities.Etudiant;
import com.example.gestionpfe.Entities.Sujet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipeDto {
    private long id;
    private String idEquipe;
    private int tailleEquipe;
    private Boolean isPrivate;
    private String driveLink;
    private String cryptedPassword;
    private List<Etudiant> etudiant;
    private Sujet sujet;
}
