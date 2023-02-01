package com.example.gestionpfe.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemarqueResponse {
    private String idRemarque;
    private String remarque;
    private Float note;
    private String target;
    private String idEtudiant;
}
