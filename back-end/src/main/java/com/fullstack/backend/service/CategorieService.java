package com.fullstack.backend.service;

import com.fullstack.backend.dto.CategorieDto;

import java.util.List;

public interface CategorieService {
    CategorieDto create(CategorieDto categorie);
    List<CategorieDto> read();
    CategorieDto findById(Integer id);
    CategorieDto findByCodeCategorie(String codeBoutique);
    CategorieDto update(Integer id, CategorieDto categorie);
    String delete(Integer id);
}
