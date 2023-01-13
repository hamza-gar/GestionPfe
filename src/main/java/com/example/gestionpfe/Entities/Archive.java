package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "archives")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Archive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(nullable = false)
    private Float noteFinal;

    /* TODO:
    *   + Add ForeignKeys for "remarques"
    *   + Add ForeignKey for "etudiant" "encadrant" "pfe"*/
}
