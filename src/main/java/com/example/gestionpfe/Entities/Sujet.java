package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sujets")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Sujet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(nullable = false)
    private String idSujet;

    @Column(nullable = false)
    private String nomSujet;

    @Column(nullable = false, length = 5000)
    private String descriptionSujet;

    @Column(nullable = false)
    private int tailleEquipe;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sujet_equipe",
            joinColumns = @JoinColumn(name = "sujet_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "equipe_id", referencedColumnName = "id"))
    private List<Equipe> equipe;

    @ManyToOne
    @JoinColumn(name = "encadrant_id", referencedColumnName = "id",nullable = false)
    private Enseignant encadrant;

    /*TODO:
     *  + Add foreignKey "enseignant" "soutenance"*/
}
