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

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRendezvous;

    @OneToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant encadrant;


    /*TODO:
    *  + Add ForeignKey for "equipe" "enseignant" */

}
