package com.example.gestionpfe.Responses;

import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Etablissement;
import com.example.gestionpfe.Entities.Filiere;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartementResponse {
    private String idDepartement;
    private String nomDepartement;
    private Etablissement etablissement;
    private List<Enseignant> enseignants;
    private List<Filiere> filieres;
}
