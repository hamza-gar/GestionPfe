package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "administrateurs")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Administrateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "idAdmin", nullable = false)
    private String idAdmin;
    @Column(nullable = false, length = 20, unique = true)
    private String cin;
    @Column(nullable = false, length = 50)
    private String nom;
    @Column(nullable = false, length = 50)
    private String prenom;
    @Column(nullable = false, length = 120, unique = true)
    private String email;
    @Column(nullable = false)
    private String encryptedPassword;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "administrateurs_role",
            joinColumns = @JoinColumn(name = "administrateurs_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
    private Role role;

}
