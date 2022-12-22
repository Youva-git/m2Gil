package com.fullstack.backend.dto;

import com.fullstack.backend.modele.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Data
@Builder
@Getter
@Setter
public class UserDto {
    private Integer id;
    private String nom;
    private String prenom;
    private String pseudo;
    private String mail;
    private String mdp;

    public static UserDto fromEntity(User user){
        if(user == null){
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .pseudo(user.getPseudo())
                .mail(user.getMail())
                .mdp(user.getMdp())
                .build();
    }

    public static User toEntity(UserDto userDto){
        if(userDto == null){
            return null;
        }
        User vUser = new User();
        vUser.setId(userDto.getId());
        vUser.setNom(userDto.getNom());
        vUser.setPrenom(userDto.getPrenom());
        vUser.setPseudo(userDto.getPseudo());
        vUser.setMail(userDto.getMail());
        vUser.setMdp(userDto.getMdp());
        return vUser;
    }
}
