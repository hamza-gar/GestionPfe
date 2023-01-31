package com.example.gestionpfe.Requests;

import com.example.gestionpfe.Entities.Enseignant;
import com.example.gestionpfe.Entities.Soutenance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JuryRequest {
    private String idJury;
    private String typeJury;
    private String idSoutenance;
    private String idEnseignant;
}
