package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "jurys")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Jury {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false,length = 50)
    private String typeJury;

    /*TODO:
    *  + Add ForeignKey for "enseignant"*/
}
