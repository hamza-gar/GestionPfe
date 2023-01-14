package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "remarques")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Remarque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(nullable = false,length = 1000)
    private String remarque;
    @Column(nullable = false)
    private Float note;

    /*TODO:
    *  + Add ForeignKey for "etudiant" "jury"*/

}
