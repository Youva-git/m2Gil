package com.fullstack.backend.dto;

import com.fullstack.backend.modele.Produit;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class ProduitDto {
    private Integer id;
    private String nom;
    private String description;
    private String codeProduit;
    private Instant creationData;
    private BigDecimal prix;
    private String photo;
    private Integer idBoutique;

    public static ProduitDto fromEntity(Produit produit){
        if(produit == null){
            return null;
        }
        return ProduitDto.builder()
                .id(produit.getId())
                .nom(produit.getNom())
                .description(produit.getDescription())
                .codeProduit(produit.getCodeProduit())
                .prix(produit.getPrix())
                .photo(produit.getPhoto())
                .build();
    }

    public static Produit toEntity(ProduitDto produitDto){
        if(produitDto == null){
            return null;
        }
        Produit vProduit = new Produit();
        vProduit.setId(produitDto.getId());
        vProduit.setNom(produitDto.getNom());
        vProduit.setDescription(produitDto.getDescription());
        vProduit.setCodeProduit(produitDto.getCodeProduit());
        vProduit.setCreationData(produitDto.getCreationData());
        vProduit.setPrix(produitDto.getPrix());
        vProduit.setPhoto(produitDto.getPhoto());
        return vProduit;
    }
}
