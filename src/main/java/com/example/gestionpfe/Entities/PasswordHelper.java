package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "passwordHelper")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PasswordHelper implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(nullable = false)
    private String idPasswordHelper;

    @Column(nullable = false)
    private String key;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Boolean isEtudiant;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;

}
