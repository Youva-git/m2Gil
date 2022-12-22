package com.fullstack.backend.validator;

import com.fullstack.backend.dto.ProduitDto;

import java.util.ArrayList;
import java.util.List;

public class ProduitValidator {
    public static List<String> validate(ProduitDto produitDto){
        List<String> errors = new ArrayList<>();
        if(produitDto.getNom().isEmpty()){
            errors.add("nom vide !");
        }
        return errors;
    }
}
