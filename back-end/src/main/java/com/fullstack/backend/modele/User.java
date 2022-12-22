package com.fullstack.backend.modele;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 32)
    private String nom;
    @Column(length = 32)
    private String prenom;
    @Column(length = 32)
    private String pseudo;
    private String mail;
    private String mdp;

}
