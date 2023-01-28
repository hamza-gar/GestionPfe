package com.example.gestionpfe.Dto;


import com.example.gestionpfe.Entities.Enseignant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SujetDto {
    private long id;
    private String idSujet;
    private String nomSujet;
    private String descriptionSujet;
    private Boolean locked;
    private int tailleEquipe;
    private Enseignant encadrant;
}
