package com.example.gestionpfe.Dto;

import com.example.gestionpfe.Entities.Departement;
import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Etudiant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiliereDto {

    private long id;
    private String nomFiliere;
    private Departement departement;
    private Enseignant responsable;
}
