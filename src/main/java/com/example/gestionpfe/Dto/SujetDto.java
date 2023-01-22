package com.example.gestionpfe.Dto;


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
    private int tailleEquipe;
}
