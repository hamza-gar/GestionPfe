package com.example.gestionpfe.Dto;

import com.example.gestionpfe.Entities.Departement;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Etudiant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiliereDto {

    private long id;
    private String idFiliere;
    private String nomFiliere;
    private String etablissement;
    private Departement departement;
    private Enseignant responsable;
}
