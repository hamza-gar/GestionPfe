package com.example.gestionpfe.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemarqueRequest {
    private String remarque;
    private Float note;
    private String target;
    private String idEtudiant;
}
