package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "jurys")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Jury {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(nullable = false,length = 50)
    private String typeJury;

    /*TODO:
    *  + Add ForeignKey for "enseignant"*/
}
