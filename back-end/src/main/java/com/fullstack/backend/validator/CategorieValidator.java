package com.fullstack.backend.validator;

import antlr.StringUtils;
import com.fullstack.backend.dto.CategorieDto;

import java.util.ArrayList;
import java.util.List;

public class CategorieValidator {
    public static List<String> validate(CategorieDto categorieDto){
        List<String> errors = new ArrayList<>();
        if(categorieDto == null || categorieDto.getNom().isEmpty()){
            errors.add("nom vide !");
        }
        return errors;
    }
}
