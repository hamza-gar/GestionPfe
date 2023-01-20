package com.example.gestionpfe.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiliereResponse {
    private long id;
    private String nomFiliere;
    private long departementId;
    private long responsableId;
}
