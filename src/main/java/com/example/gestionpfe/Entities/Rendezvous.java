package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "rendezvous")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Rendezvous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(nullable = false)
    private String idRendezvous;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRendezvous;

    @Column(nullable = false)
    private int vote;

    @Column(nullable = false)
    private Boolean fixed;

    @Column
    private String flags;

    @OneToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "enseignant_id")
    private Enseignant encadrant;


    /*TODO:
    *  + Add ForeignKey for "equipe" "enseignant" */

}
