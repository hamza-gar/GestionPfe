package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "notifications")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(nullable = false,length = 300)
    private String message;
    @Column(nullable = false)
    private int typeNotification;
    @Column(nullable = false)
    private Boolean vu = false;

    /*TODO:
    *  + Add ForeignKey for "etudiant" "admin" "enseignant"*/
}
