package com.example.gestionpfe.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DomaineDto {

    private long id;
    private String nomDomaine;
    private String etablissement;
    private String idUniversite;
    private Boolean etudiant;
}
