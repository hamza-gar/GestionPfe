package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "soutenances")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Soutenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSoutenance;

    /*TODO:
    *  + Add ForeignKeys for "jurys"
    *  + Add ForeignKey for "encadrant" "equipes"*/
}
