package com.example.gestionpfe.Responses;

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
public class JuryResponse {
    private String idJury;
    private String typeJury;
    private String idSoutenance;
    private String nomJury;
    private String mailJury;
}
