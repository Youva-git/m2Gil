package com.fullstack.backend.validator;

import com.fullstack.backend.dto.BoutiqueDto;

import java.util.ArrayList;
import java.util.List;

public class BoutiqueValidator {
    public static List<String> validate(BoutiqueDto boutiqueDto){
        List<String> errors = new ArrayList<>();
        if(boutiqueDto.getNom().isEmpty()){
            errors.add("nom vide !");
        }
        return errors;
    }
}
