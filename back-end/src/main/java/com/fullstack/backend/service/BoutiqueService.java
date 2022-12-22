package com.fullstack.backend.service;

import com.fullstack.backend.dto.BoutiqueDto;

import java.util.List;

public interface BoutiqueService {
    BoutiqueDto  create(BoutiqueDto boutique);
    List<BoutiqueDto> read();
    BoutiqueDto findById(Integer id);
    BoutiqueDto findByCodeBoutique(String codeBoutique);
    BoutiqueDto update(Integer id, BoutiqueDto boutique);
    String delete(Integer id);
}
