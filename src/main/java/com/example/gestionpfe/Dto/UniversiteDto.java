package com.example.gestionpfe.Dto;

import com.example.gestionpfe.Entities.Etablissement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UniversiteDto {
    private long id;
    private String idUniversite;
    private String nomUniversite;
    private String adresse;
    private List<Etablissement> etablissements;

}
