package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "remarques")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Remarque {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false,length = 1000)
    private String remarque;
    @Column(nullable = false)
    private Float note;

    /*TODO:
    *  + Add ForeignKey for "etudiant" "jury"*/

}
