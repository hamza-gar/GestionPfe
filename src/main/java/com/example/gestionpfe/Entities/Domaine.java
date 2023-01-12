package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "domaines")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Domaine {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String nomDomaine;
    @Column(nullable = false)
    private String etablissement;
    @Column(nullable = false)
    private Boolean etudiant;
}
