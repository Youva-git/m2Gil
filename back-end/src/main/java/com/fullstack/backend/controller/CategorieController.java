package com.fullstack.backend.controller;

import com.fullstack.backend.dto.CategorieDto;
import com.fullstack.backend.dto.ProduitDto;
import com.fullstack.backend.service.CategorieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.fullstack.backend.utils.Constants.APP_ROOT;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(APP_ROOT+"/categories")
@AllArgsConstructor
@Api(APP_ROOT + "/categories")
public class CategorieController {
    private final CategorieService vCategorieService;

    @ApiOperation(value = "Ajouter une catégorie.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Catégorie ajoutée avec succès."),
            @ApiResponse(code = 400, message = "Catégorie non valide.")
    })
    @PostMapping("/create")
    public CategorieDto create(@RequestBody CategorieDto categorie){
        return vCategorieService.create(categorie);
    }

    @ApiOperation(value = "Afficher toutes les categories de la BDD.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories (Peut étre vide)."),
    })
    @GetMapping("/read")
    public List<CategorieDto> read(){
        return vCategorieService.read();
    }

    @ApiOperation(value = "Rechercher une categorie avec son ID.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Categorie trouvée dans la BDD."),
            @ApiResponse(code = 404, message = "Aucune categorie n'a été trouvée dans la BDD.")
    })
    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieDto findById(@PathVariable Integer id){
        return vCategorieService.findById(id);
    }

    @ApiOperation(value = "Rechercher une categorie avec son CODE.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Categorie trouvée dans la BDD."),
            @ApiResponse(code = 404, message = "Aucune categorie n'a été trouvée dans la BDD.")
    })
    @GetMapping(value = "/findByCodeProduit/{codeCategorie}", produces = MediaType.APPLICATION_JSON_VALUE)
    CategorieDto findByCodeCategorie(@PathVariable String codeCategorie){
        return vCategorieService.findByCodeCategorie(codeCategorie);
    }

    @ApiOperation(value = "Mettre à jour une categorie avec son ID.", responseContainer = "List<CategorieDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Categorie mise à ajour avec succès."),
            @ApiResponse(code = 400, message = "Categorie non valide.")
    })
    @PutMapping("/update/{id}")
    public CategorieDto update(@PathVariable Integer id, @RequestBody CategorieDto categorie){
        return vCategorieService.update(id, categorie);
    }

    @ApiOperation(value = "Supprimer une categorie avec son ID.", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Categorie supprimée avec succès."),
            @ApiResponse(code = 404, message = "Cette categorie ne peut pas étre supprimée.")
    })
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        return vCategorieService.delete(id);
    }
}
