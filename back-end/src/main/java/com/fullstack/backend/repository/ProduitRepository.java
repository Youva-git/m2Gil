package com.fullstack.backend.repository;

import com.fullstack.backend.modele.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    Optional<Produit> findByCodeProduit(String codeProduit);
}
