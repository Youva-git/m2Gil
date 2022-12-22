package com.fullstack.backend.service.impls;

import com.fullstack.backend.dto.UserDto;
import com.fullstack.backend.exception.EntityNotFoundException;
import com.fullstack.backend.exception.ErrorCodes;
import com.fullstack.backend.exception.InvalideEntityException;
import com.fullstack.backend.modele.User;
import com.fullstack.backend.repository.UserRepository;
import com.fullstack.backend.service.UserService;
import com.fullstack.backend.validator.UserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpls implements UserService {

    private final UserRepository vUserRepository;

    @Override
    public UserDto create(UserDto user) {
        List<String> errors = UserValidator.validate(user);
        if(errors.size() > 0){
            log.error("user non valide !", user);
            throw new InvalideEntityException("L'utilisateur n'est pas valide !", ErrorCodes.USER_NOT_FOUND, errors);
        }
        return UserDto.fromEntity(vUserRepository.save(UserDto.toEntity(user)));
    }

    @Override
    public List<UserDto> read() {
        return vUserRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    public UserDto findById(Integer id){
        if(id == null){
            log.error("Utilisateur ID null !");
            return null;
        }
        Optional<User> user = vUserRepository.findById(id);
        return Optional.of(UserDto.fromEntity(user.get()))
                .orElseThrow(() -> new EntityNotFoundException
                        ("Aucun utilisateur porte l'ID = "+id+" dans la BDD",
                                ErrorCodes.PRODUIT_NOT_FOUND));
    }

    public UserDto findByMail(String mail){
        if(mail == null){
            log.error("Utilisateur mail null !");
            return null;
        }
        Optional<User> user = vUserRepository.findByMail(mail);
        return Optional.of(UserDto.fromEntity(user.get()))
                .orElseThrow(() -> new EntityNotFoundException
                        ("Aucun utilisateur avec cette email = "+mail+" dans la BDD",
                                ErrorCodes.USER_NOT_FOUND));
    }

    @Override
    public UserDto update(Integer id, UserDto user) {
        return UserDto.fromEntity(vUserRepository.findById(id)
                .map(u-> {
                    u.setNom(user.getNom());
                    u.setPrenom(user.getPrenom());
                    u.setMail(user.getMail());
                    u.setMdp(user.getMdp());
                    return vUserRepository.save(UserDto.toEntity(UserDto.fromEntity(u)));
                }).orElseThrow(() -> new RuntimeException("Utilisateur introvable !")));
    }

    @Override
    public String delete(Integer id) {
        vUserRepository.deleteById(id);
        return "Produit supprimé !";
    }
}
