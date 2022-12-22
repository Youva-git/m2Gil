package com.fullstack.backend.controller;

import com.fullstack.backend.dto.BoutiqueDto;
import com.fullstack.backend.dto.ProduitDto;
import com.fullstack.backend.service.BoutiqueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import static com.fullstack.backend.utils.Constants.APP_ROOT;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(APP_ROOT+"/boutiques")
@AllArgsConstructor
@Api(APP_ROOT + "/boutiques")
public class BoutiqueController {
    private final BoutiqueService vBoutiqueService;

    @ApiOperation(value = "Ajouter une boutique.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Boutique ajoutée avec succès."),
            @ApiResponse(code = 400, message = "Boutique non valide.")
    })
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BoutiqueDto create(@RequestBody BoutiqueDto boutique){
        return vBoutiqueService.create(boutique);
    }

    @ApiOperation(value = "Rechercher un boutique avec son ID.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Boutique trouvée dans la BDD."),
            @ApiResponse(code = 404, message = "Aucun boutique n'a été trouvée dans la BDD.")
    })
    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    BoutiqueDto findById(@PathVariable Integer id){
        return vBoutiqueService.findById(id);
    }

    @ApiOperation(value = "Rechercher une boutique avec son CODE.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Boutique trouvée dans la BDD."),
            @ApiResponse(code = 404, message = "Aucune boutique n'a été trouvée dans la BDD.")
    })
    @GetMapping(value = "/findByCodeBoutique/{codeBoutique}", produces = MediaType.APPLICATION_JSON_VALUE)
    BoutiqueDto findByCodeBoutique(@PathVariable String codeBoutique){
        return vBoutiqueService.findByCodeBoutique(codeBoutique);
    }

    @ApiOperation(value = "Afficher toutes les boutiques de la BDD.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des boutiques (Peut étre vide)."),
    })
    @GetMapping(value = "/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BoutiqueDto> read(){
        return vBoutiqueService.read();
    }

    @ApiOperation(value = "Mettre à jour une boutique avec son ID.", responseContainer = "List<BoutiqueDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Boutique mise à ajour avec succès."),
            @ApiResponse(code = 400, message = "Boutique non valide.")
    })
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BoutiqueDto update(@PathVariable Integer id, @RequestBody BoutiqueDto boutique){
        return vBoutiqueService.update(id, boutique);
    }

    @ApiOperation(value = "Supprimer une boutique avec son ID.", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Boutique supprimée avec succès."),
            @ApiResponse(code = 404, message = "Cette boutique ne peut pas étre supprimée.")
    })
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        return vBoutiqueService.delete(id);
    }
}
