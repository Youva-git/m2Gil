package com.fullstack.backend.validator;

import com.fullstack.backend.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    public static List<String> validate(UserDto userDto){
        List<String> errors = new ArrayList<>();
        if(userDto.getNom().isEmpty()){
            errors.add("nom vide !");
        }
        return errors;
    }
}
