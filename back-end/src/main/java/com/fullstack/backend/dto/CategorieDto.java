package com.fullstack.backend.dto;

import com.fullstack.backend.modele.Categorie;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
@Data
@Builder
public class CategorieDto {
    private Integer id;
    private String nom;
    private String description;
    private Instant creationData;
    private String codeCategorie;
    private List<ProduitDto> produits;
    private Integer idBoutique;


    public static CategorieDto fromEntity(Categorie categorie){
        if(categorie == null){
            return null;
        }
        return CategorieDto.builder()
                .id(categorie.getId())
                .nom(categorie.getNom())
                .description(categorie.getDescription())
                .creationData(categorie.getCreationData())
                .codeCategorie(categorie.getCodeCategorie())
                .idBoutique(categorie.getIdBoutique())
                .build();
    }

    public static Categorie toEntity(CategorieDto categorieDto){
        if(categorieDto == null){
            return null;
        }
        Categorie vCategorie = new Categorie();
        vCategorie.setId(categorieDto.getId());
        vCategorie.setNom(categorieDto.getNom());
        vCategorie.setDescription(categorieDto.getDescription());
        vCategorie.setCodeCategorie(categorieDto.getCodeCategorie());
        vCategorie.setCreationData(categorieDto.getCreationData());
        vCategorie.setCodeCategorie(categorieDto.getCodeCategorie());
        vCategorie.setIdBoutique(categorieDto.getIdBoutique());
        return vCategorie;
    }
}
