package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "enseignants")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(name = "idEnseignant",nullable=false)
    private String idEnseignant;
    @Column(nullable = false, length = 20)
    private String cin;
    @Column(nullable = false, length = 50)
    private String nom;
    @Column(nullable = false, length = 50)
    private String prenom;
    @Column(nullable = false ,length = 120,unique = true)
    private String email;
    @Column(nullable = false)
    private String encryptedPassword;
    @Column(nullable = true)
    private String emailVerificationToken;
    @Column(nullable = false)//columnDefinition = "boolean default false"
    private Boolean emailVerificationStatus = false;

}
