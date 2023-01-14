package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "administrateurs")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Administrateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
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
