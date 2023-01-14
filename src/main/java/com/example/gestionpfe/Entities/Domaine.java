package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "domaines")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Domaine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(nullable = false)
    private String nomDomaine;
    @Column(nullable = false)
    private String etablissement;
    @Column(nullable = false)
    private Boolean etudiant;
}
