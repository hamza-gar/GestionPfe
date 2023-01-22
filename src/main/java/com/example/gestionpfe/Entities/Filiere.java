package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "filieres")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Filiere implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nomFiliere;

    @ManyToOne
    @JoinColumn(name = "departement_id",unique = true,nullable = false)
    private Departement departement;

    @OneToOne
    @JoinColumn(name = "responsable_id",unique = true,nullable = false)
    private Enseignant responsable;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "filiere_etudiant",
            joinColumns = @JoinColumn(name = "filiere_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "etudiant_id", referencedColumnName = "id"))
    private List<Etudiant> etudiants;

}
