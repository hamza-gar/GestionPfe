package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "pfes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pfe {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String nomSujet;
    @Column(nullable = false, length = 5000)
    private String descriptionSujet;

    /*TODO:
     *  + Add foreignKey "enseignant" "equipe" "soutenance"*/
}
