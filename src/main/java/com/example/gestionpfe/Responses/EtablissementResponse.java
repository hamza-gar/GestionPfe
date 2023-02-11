package com.example.gestionpfe.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EtablissementResponse {
    private String idEtablissement;
    private String nomEtablissement;
    private String adresse;
}
