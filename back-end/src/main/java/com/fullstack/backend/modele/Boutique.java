package com.fullstack.backend.modele;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "boutique")
public class Boutique extends AbstractEntity{
    @Column(name = "codeBoutique" )
    private String codeBoutique;
    private String horaire;
    private Boolean conge;
    @OneToMany
    @JoinColumn(name = "idCategorie")
    private List<Categorie> categories;
    @Column(name = "idUser")
    private Integer idUser;

}
