package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "jurys")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Jury {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(nullable = false)
    private String idJury;

    @Column(nullable = false,length = 50)
    private String typeJury;

    @ManyToOne
    @JoinColumn(name = "soutenance_id", referencedColumnName = "id",nullable = false)
    private Soutenance soutenance;

    @OneToOne
    @JoinColumn(name = "enseignant_id", referencedColumnName = "id",nullable = false)
    private Enseignant enseignant;
}
