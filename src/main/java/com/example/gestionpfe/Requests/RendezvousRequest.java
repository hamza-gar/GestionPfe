package com.example.gestionpfe.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RendezvousRequest {
    private String idRendezvous;
    private Date dateRendezvous;
    private int vote;
    private Boolean fixed;
}
