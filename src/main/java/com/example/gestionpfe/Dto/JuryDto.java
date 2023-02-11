package com.example.gestionpfe.Dto;

import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Soutenance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JuryDto {
    private long id;
    private String idJury;
    private String typeJury;
    private Soutenance soutenance;
    private Enseignant enseignant;
}
