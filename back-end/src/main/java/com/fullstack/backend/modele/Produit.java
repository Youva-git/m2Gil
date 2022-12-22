package com.fullstack.backend.modele;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "produit")
public class Produit extends AbstractEntity {

    @Column(name = "codeProduit", nullable = false)
    private String codeProduit;
    @Column(name = "prix", nullable = false)
    private BigDecimal prix;
    @Column(name = "photo")
    private String photo;
    @Column(name = "idBoutique")
    private Integer idBoutique;

}
