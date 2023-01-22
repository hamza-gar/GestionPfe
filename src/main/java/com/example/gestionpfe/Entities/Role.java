package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity(name = "roles")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_authority",
            joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authority;

}
