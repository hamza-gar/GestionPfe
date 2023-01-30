package com.example.gestionpfe.Requests;

import com.example.gestionpfe.Entities.Sujet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoutenanceRequest {
    private String idSoutenance;
    private Date dateSoutenance;
}
