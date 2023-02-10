package com.example.gestionpfe.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RendezvousResponse {
    private String idRendezvous;
    private Date dateRendezvous;
    private Boolean fixed;
    private String idEquipe;
    private String nomSujet;
}
