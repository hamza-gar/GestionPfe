package com.example.gestionpfe.Requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SujetRequest {
    private String idSujet;
    private String nomSujet;
    private String descriptionSujet;
    private Boolean done;
    private int tailleEquipe;
}
