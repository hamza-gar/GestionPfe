package com.example.gestionpfe.Responses;

import com.example.gestionpfe.Entities.Jury;
import com.example.gestionpfe.Entities.Sujet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoutenanceResponse {
    private String idSoutenance;
    private String nomSujet;
    private String dateSoutenance;
    private String idSujet;
    private String idEquipe;
}
