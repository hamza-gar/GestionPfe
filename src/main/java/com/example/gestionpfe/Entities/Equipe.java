package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "equipes")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(nullable = false)
    private String idEquipe;

    @Column(nullable = false)
    private int tailleEquipe;

    @Column(nullable = false)
    private Boolean isPrivate;

    @Column
    private String cryptedPassword;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "equipe_etudiant",
            joinColumns = @JoinColumn(name = "equipe_id"),
            inverseJoinColumns = @JoinColumn(name = "etudiant_id"))
    private List<Etudiant> etudiant;

    /* TODO:
    *   + Add ForeignKeys for "etudiants" in "equipe" */
}
