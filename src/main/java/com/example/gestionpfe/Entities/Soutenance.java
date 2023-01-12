package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "soutenances")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Soutenance {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSoutenance;

    /*TODO:
    *  + Add ForeignKeys for "jurys"
    *  + Add ForeignKey for "encadrant" "equipes"*/
}
