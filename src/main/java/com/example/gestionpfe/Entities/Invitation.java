package com.example.gestionpfe.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "invitation")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Invitation {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(nullable = false)
    private String idInvitation;

    @Column(nullable = false)
    private String emailInvite;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String idSoutenance;

}
