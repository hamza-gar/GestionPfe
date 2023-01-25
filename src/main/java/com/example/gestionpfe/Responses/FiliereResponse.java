package com.example.gestionpfe.Responses;

import com.example.gestionpfe.Entities.Enseignant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiliereResponse {
    private String idFiliere;
    private String nomFiliere;
    private String etablissement;
    private String nomDepartement;
    private EnseignantResponse responsable;
}
