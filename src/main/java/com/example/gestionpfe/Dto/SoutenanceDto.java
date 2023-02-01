package com.example.gestionpfe.Dto;

import com.example.gestionpfe.Entities.Jury;
import com.example.gestionpfe.Entities.Sujet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoutenanceDto {
    private long id;
    private String idSoutenance;
    private Date dateSoutenance;
    private Boolean ended;
    private Sujet sujet;
    private List<Jury> jurys;
}
