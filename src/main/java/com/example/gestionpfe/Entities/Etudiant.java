package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "etudiants")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "idEtudiant", nullable = false)
    private String idEtudiant;
    @Column(nullable = false)
    private Long apogee;
    @Column(nullable = false, length = 20)
    private String cne;
    @Column(nullable = false, length = 20)
    private String cin;
    @Column(nullable = false, length = 50)
    private String nom;
    @Column(nullable = false, length = 50)
    private String prenom;
    @Column(nullable = false, length = 120, unique = true)
    private String email;
    @Column(nullable = false)
    private String encryptedPassword;
    @Column(nullable = true)
    private String emailVerificationToken;
    @Column(nullable = false)//columnDefinition = "boolean default false"
    private Boolean emailVerificationStatus = false;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "etudiants_role",
            joinColumns = @JoinColumn(name = "etudiants_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private Role role;

    @ManyToOne
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;

}
