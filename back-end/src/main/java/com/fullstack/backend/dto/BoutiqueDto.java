package com.fullstack.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fullstack.backend.modele.Boutique;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
@Data
@Builder
public class BoutiqueDto {
    private Integer id;
    private String nom;
    private String description;
    private String codeBoutique;
    private Instant creationData;
    private String horaire;
    private Boolean conge;
    @JsonIgnore
    private List<CategorieDto> categories;
    public static BoutiqueDto fromEntity(Boutique boutique){
        if(boutique == null){
            return null;
        }
        return BoutiqueDto.builder()
                .id(boutique.getId())
                .nom(boutique.getNom())
                .description(boutique.getDescription())
                .codeBoutique(boutique.getCodeBoutique())
                .creationData(boutique.getCreationData())
                .horaire(boutique.getHoraire())
                .conge(boutique.getConge())
                .build();
    }

    public static Boutique toEntity(BoutiqueDto boutiqueDto){
        if(boutiqueDto == null){
            return null;
        }
        Boutique vBoutique = new Boutique();
        vBoutique.setId(boutiqueDto.getId());
        vBoutique.setNom(boutiqueDto.getNom());
        vBoutique.setDescription(boutiqueDto.getDescription());
        vBoutique.setCodeBoutique(boutiqueDto.getCodeBoutique());
        vBoutique.setCreationData(boutiqueDto.getCreationData());
        vBoutique.setHoraire(boutiqueDto.getHoraire());
        vBoutique.setConge(boutiqueDto.getConge());
        return vBoutique;
    }
}
