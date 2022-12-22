package com.fullstack.backend.service;

import com.fullstack.backend.dto.ProduitDto;

import java.util.List;

public interface ProduitService {
    ProduitDto create(ProduitDto produit);
    ProduitDto findById(Integer id);
    ProduitDto findByCodeProduit(String codeProduit);
    List<ProduitDto> read();
    ProduitDto update(Integer id, ProduitDto produit);
    String delete(Integer id);
}
