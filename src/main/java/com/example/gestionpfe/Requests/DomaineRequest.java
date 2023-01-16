package com.example.gestionpfe.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DomaineRequest {
    private String nomDomaine;
    private String etablissement;
    private Boolean etudiant;
}
