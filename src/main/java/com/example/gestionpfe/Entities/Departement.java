package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "departements")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Departement implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String idDepartement;

    @Column(nullable = false)
    private String nomDepartement;

    @ManyToOne
    @JoinColumn(name = "etablissement_id",unique = true,nullable = false)
    private Etablissement etablissement;

    @OneToMany(mappedBy = "departement", cascade = {CascadeType.ALL})
    private List<Enseignant> enseignants;

    @OneToMany(mappedBy = "departement")
    private List<Filiere> filieres;
}
