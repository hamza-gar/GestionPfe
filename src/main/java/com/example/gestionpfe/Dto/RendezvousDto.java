package com.example.gestionpfe.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RendezvousDto {
    private long id;
    private String idRendezvous;
    private Date dateRendezvous;
    private int vote;
    private Boolean fixed;
    private String idEquipe;
    private String nomSujet;
}
