package com.example.gestionpfe.Responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SujetResponse {
    private long id;
    private String nomSujet;
    private String descriptionSujet;
    private int tailleEquipe;
}
