package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "universites")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Universite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String idUniversite;

    @Column(nullable = false)
    private String nomUniversite;

    @Column
    private String adresse;

    @OneToMany(mappedBy = "universite")
    private List<Etablissement> etablissements;

    @OneToMany(mappedBy = "universite")
    private List<Domaine> domaines;
}
