package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "administrateurs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Administrateur {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "idAdmin",nullable=false)
    private String idAdmin;
    @Column(nullable = false, length = 20,unique = true)
    private String cin;
    @Column(nullable = false, length = 50)
    private String nom;
    @Column(nullable = false, length = 50)
    private String prenom;
    @Column(nullable = false ,length = 120,unique = true)
    private String email;
    @Column(nullable = false)
    private String encryptedPassword;
    @Column(nullable = false)
    private int authorityLevel;
}
