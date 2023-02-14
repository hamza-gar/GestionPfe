package com.example.gestionpfe.Responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DomaineResponse {
    private String nomDomaine;
    private String idUniversite;
    private Boolean etudiant;
}
