package com.example.gestionpfe.Dto;

import com.example.gestionpfe.Entities.Departement;
import com.example.gestionpfe.Entities.Universite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EtablissementDto {
    private long id;
    private String idEtablissement;
    private String nomEtablissement;
    private String adresse;
    private Universite universite;
    private List<Departement> departements;
}
