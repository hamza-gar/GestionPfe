package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "pfes")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pfe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(nullable = false)
    private String nomSujet;
    @Column(nullable = false, length = 5000)
    private String descriptionSujet;

    /*TODO:
     *  + Add foreignKey "enseignant" "equipe" "soutenance"*/
}
