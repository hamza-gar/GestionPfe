package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "archives")
@Data
@ToString
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
