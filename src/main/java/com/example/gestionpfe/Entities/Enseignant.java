package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "enseignants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enseignant {
    @Id
    @GeneratedValue
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
