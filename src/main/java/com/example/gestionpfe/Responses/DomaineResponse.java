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
    private long id;
    private String nomDomaine;
    private String idUniversite;
    private String nomUniversite;
    private Boolean etudiant;
}
