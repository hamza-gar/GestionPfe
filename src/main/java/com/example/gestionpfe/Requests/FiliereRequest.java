package com.example.gestionpfe.Requests;

import com.example.gestionpfe.Entities.Departement;
import com.example.gestionpfe.Entities.Enseignant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiliereRequest {

    private String nomFiliere;
    private Departement departement;
    private String etablissement;
    private EnseignantRequest responsable;

}
