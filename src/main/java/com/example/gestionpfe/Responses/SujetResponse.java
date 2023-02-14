package com.example.gestionpfe.Responses;

import com.example.gestionpfe.Entities.Enseignant;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SujetResponse {
    private String idSujet;
    private String nomSujet;
    private String descriptionSujet;
    private int tailleEquipe;
    private String nomEnseignant;
    private String emailEnseignant;
    private String enseignantId;
    private Boolean done;

}
