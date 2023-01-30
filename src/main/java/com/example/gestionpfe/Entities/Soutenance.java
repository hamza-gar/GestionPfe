package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private String idSoutenance;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSoutenance;


    @OneToOne
    @JoinColumn(name = "sujet_id")
    private Sujet sujet;

    @OneToMany(mappedBy = "soutenance")
    private List<Jury> jurys;

    /*TODO:
    *  + Add ForeignKeys for "jurys"
    *  + Add ForeignKey for "encadrant" "equipes"*/
}
