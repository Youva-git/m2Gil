package com.fullstack.backend.repository;

import com.fullstack.backend.modele.Boutique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoutiqueRepository extends JpaRepository<Boutique, Integer> {
    Optional<Boutique> findByCodeBoutique(String codeBoutique);
}
