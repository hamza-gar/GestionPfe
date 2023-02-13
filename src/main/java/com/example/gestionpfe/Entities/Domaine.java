package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "domaines")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Domaine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(nullable = false)
    private String nomDomaine;

    @ManyToOne
    @JoinColumn(name = "universite_id", nullable = false)
    private Universite universite;

    @Column(nullable = false)
    private Boolean etudiant;
}
