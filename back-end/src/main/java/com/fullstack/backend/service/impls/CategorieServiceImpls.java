package com.fullstack.backend.service.impls;

import com.fullstack.backend.dto.CategorieDto;
import com.fullstack.backend.exception.EntityNotFoundException;
import com.fullstack.backend.exception.ErrorCodes;
import com.fullstack.backend.exception.InvalideEntityException;
import com.fullstack.backend.modele.Categorie;
import com.fullstack.backend.repository.CategorieRepository;
import com.fullstack.backend.service.CategorieService;
import com.fullstack.backend.validator.CategorieValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CategorieServiceImpls implements CategorieService {

    private final CategorieRepository vCategorieRepository;

    @Override
    public CategorieDto create(CategorieDto categorie) {
        List<String> errors = CategorieValidator.validate(categorie);
        if(errors.size() > 0){
            log.error("Categorie non valide !", categorie);
            throw new InvalideEntityException("La Categorie n'est pas valide !", ErrorCodes.PRODUIT_NOT_FOUND, errors);
        }
        return CategorieDto.fromEntity(vCategorieRepository.save(CategorieDto.toEntity(categorie)));
    }

    @Override
    public List<CategorieDto> read() {
        return vCategorieRepository.findAll().stream()
                .map(CategorieDto::fromEntity)
                .collect(Collectors.toList());
    }

    public CategorieDto findById(Integer id){
        if(id == null){
            log.error("Categorie ID null !");
            return null;
        }
        Optional<Categorie> categorie = vCategorieRepository.findById(id);
        return Optional.of(CategorieDto.fromEntity(categorie.get()))
                .orElseThrow(() -> new EntityNotFoundException
                        ("Aucune categorie porte l'ID = "+id+" dans la BDD",
                                ErrorCodes.CATEGORIE_NOT_FOND));
    }
    public CategorieDto findByCodeCategorie(String CodeCategorie){
        if(CodeCategorie.isEmpty()){
            log.error("Code Categorie null !");
            return null;
        }
        Optional<Categorie> categorie = vCategorieRepository.findByCodeCategorie(CodeCategorie);
        return Optional.of(CategorieDto.fromEntity(categorie.get()))
                .orElseThrow(() -> new EntityNotFoundException
                        ("Aucune Categorie porte le code = "+CodeCategorie+" dans la BDD",
                                ErrorCodes.CATEGORIE_NOT_FOND));
    }
    @Override
    public CategorieDto update(Integer id, CategorieDto categorie) {
        return CategorieDto.fromEntity(vCategorieRepository.findById(id)
                .map(c-> {
                    c.setNom(categorie.getNom());
                    c.setDescription(categorie.getDescription());
                    return vCategorieRepository.save(CategorieDto.toEntity(CategorieDto.fromEntity(c)));
                }).orElseThrow(() -> new RuntimeException("Categorie introvable !")));
    }

    @Override
    public String delete(Integer id) {
        vCategorieRepository.deleteById(id);
        return "Categorie supprimé !";
    }
}
