package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "etablissements")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String idEtablissement;

    @Column(nullable = false)
    private String nomEtablissement;

    @Column(nullable = false)
    private String adresse;

    @ManyToOne
    @JoinColumn(name = "universite_id", nullable = false)
    private Universite universite;

    @OneToMany(mappedBy = "etablissement")
    private List<Departement> departements;
}
