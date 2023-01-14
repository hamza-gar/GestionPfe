package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "equipes")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    /* TODO:
    *   + Add ForeignKeys for "etudiants" in "equipe" */
}
