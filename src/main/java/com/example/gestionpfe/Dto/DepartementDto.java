package com.example.gestionpfe.Dto;

import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Filiere;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartementDto {
    private long id;
    private String nomDepartement;
    private List<Enseignant> enseignants;
    private List<Filiere> filieres;
}
