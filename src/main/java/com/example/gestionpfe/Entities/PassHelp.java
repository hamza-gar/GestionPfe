package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "passHelp")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PassHelp implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;
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
