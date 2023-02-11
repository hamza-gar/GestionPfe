package com.example.gestionpfe.Requests;

import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Etablissement;
import com.example.gestionpfe.Entities.Filiere;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepartementRequest {
    private String idDepartement;
    private String nomDepartement;
    private String idEtablissement;
    private List<String> idEnseignants;
    private List<String> idFilieres;
}
