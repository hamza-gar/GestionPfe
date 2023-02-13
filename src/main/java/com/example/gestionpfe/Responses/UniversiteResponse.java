package com.example.gestionpfe.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UniversiteResponse {
    private String idUniversite;
    private String nomUniversite;
    private String adresse;
}
