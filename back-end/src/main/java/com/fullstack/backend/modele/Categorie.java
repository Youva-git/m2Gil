package com.fullstack.backend.modele;


import java.util.List;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "categorie")
public class Categorie extends AbstractEntity{

    @Column(name = "codeCategorie")
    private String codeCategorie;
    @ManyToMany
    @JoinColumn(name = "idProduit")
    private List<Produit> produits;
    @Column(name = "idBoutique")
    private Integer idBoutique;
}
