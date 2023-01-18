package com.example.gestionpfe.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "roles")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_authority",
            joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id", unique = false),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id", unique = false))
    private List<Authority> authority;

}
