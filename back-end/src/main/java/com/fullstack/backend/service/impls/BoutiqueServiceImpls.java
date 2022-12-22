package com.fullstack.backend.service.impls;

import com.fullstack.backend.dto.BoutiqueDto;
import com.fullstack.backend.exception.EntityNotFoundException;
import com.fullstack.backend.exception.ErrorCodes;
import com.fullstack.backend.exception.InvalideEntityException;
import com.fullstack.backend.modele.Boutique;
import com.fullstack.backend.repository.BoutiqueRepository;
import com.fullstack.backend.service.BoutiqueService;
import com.fullstack.backend.validator.BoutiqueValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BoutiqueServiceImpls implements BoutiqueService {

    private final BoutiqueRepository vBoutiqueRepository;

    @Override
    public BoutiqueDto create(BoutiqueDto boutique) {
        List<String> errors = BoutiqueValidator.validate(boutique);
        if(errors.size() > 0){
            log.error("Boutique non valide !", boutique);
            throw new InvalideEntityException("La Boutique n'est pas valide !", ErrorCodes.PRODUIT_NOT_FOUND, errors);
        }
        return BoutiqueDto.fromEntity(vBoutiqueRepository.save(BoutiqueDto.toEntity(boutique)));
    }

    @Override
    public List<BoutiqueDto> read() {
        return vBoutiqueRepository.findAll().stream()
                .map(BoutiqueDto::fromEntity)
                .collect(Collectors.toList());
    }

    public BoutiqueDto findById(Integer id){
        if(id == null){
            log.error("Boutique ID null !");
            return null;
        }
        Optional<Boutique> boutique = vBoutiqueRepository.findById(id);
        return Optional.of(BoutiqueDto.fromEntity(boutique.get()))
                .orElseThrow(() -> new EntityNotFoundException
                        ("Aucune Boutique porte l'ID = "+id+" dans la BDD",
                                ErrorCodes.BOUTIQUE_NOT_FOUND));
    }
    public BoutiqueDto findByCodeBoutique(String codeBoutique){
        if(codeBoutique.isEmpty()){
            log.error("Code Boutique null !");
            return null;
        }
        Optional<Boutique> boutique = vBoutiqueRepository.findByCodeBoutique(codeBoutique);
        return Optional.of(BoutiqueDto.fromEntity(boutique.get()))
                .orElseThrow(() -> new EntityNotFoundException
                        ("Aucune Boutique porte le code = "+codeBoutique+" dans la BDD",
                                ErrorCodes.BOUTIQUE_NOT_FOUND));
    }
    @Override
    public BoutiqueDto update(Integer id, BoutiqueDto boutique) {
        return BoutiqueDto.fromEntity(vBoutiqueRepository.findById(id)
                .map(b-> {
                    b.setNom(boutique.getNom());
                    b.setDescription(boutique.getDescription());
                    b.setHoraire(boutique.getHoraire());
                    b.setConge(boutique.getConge());
                    return vBoutiqueRepository.save(BoutiqueDto.toEntity(BoutiqueDto.fromEntity(b)));
                }).orElseThrow(() -> new RuntimeException("Boutique introvable !")));
    }

    @Override
    public String delete(Integer id) {
        vBoutiqueRepository.deleteById(id);
        return "Boutique supprimé !";
    }
}
