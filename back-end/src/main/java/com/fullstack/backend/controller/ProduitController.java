package com.fullstack.backend.controller;

import com.fullstack.backend.dto.ProduitDto;
import com.fullstack.backend.service.ProduitService;
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
@AllArgsConstructor
@RequestMapping(APP_ROOT+"/produits")
@Api(APP_ROOT + "/produits")
public class ProduitController {

    private final ProduitService vProduitService;

    @ApiOperation(value = "Ajouter un produit.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produit ajouté avec succès."),
            @ApiResponse(code = 400, message = "Produit non valide.")
    })
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProduitDto create(@RequestBody ProduitDto produit){
        return vProduitService.create(produit);
    }

    @ApiOperation(value = "Rechercher un produit avec son ID.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produit trouvé dans la BDD."),
            @ApiResponse(code = 404, message = "Aucun produit n'a été trouvé dans la BDD.")
    })
    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProduitDto findById(@PathVariable Integer id){
        return vProduitService.findById(id);
    }

    @ApiOperation(value = "Rechercher un produit avec son CODE.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produit trouvé dans la BDD."),
            @ApiResponse(code = 404, message = "Aucun produit n'a été trouvé dans la BDD.")
    })
    @GetMapping(value = "/findByCodeProduit/{codeProduit}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProduitDto findByCodeProduit(@PathVariable String codeProduit){
        return vProduitService.findByCodeProduit(codeProduit);
    }

    @ApiOperation(value = "Afficher tous les produit de la BDD.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des produits (Peut étre vide)."),
    })
    @GetMapping(value = "/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProduitDto> read(){
        return vProduitService.read();
    }

    @ApiOperation(value = "Mettre à jour un produit avec son ID.", responseContainer = "List<ProduitDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produit mis à ajour avec succès."),
            @ApiResponse(code = 400, message = "Produit non valide.")
    })
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProduitDto update(@PathVariable Integer id, @RequestBody ProduitDto produit){
        return vProduitService.update(id, produit);
    }

    @ApiOperation(value = "Supprimer un produit avec son ID.", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produit supprimé avec succès."),
            @ApiResponse(code = 404, message = "Ce produit ne peut pas étre supprimé.")
    })
    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable Integer id){
        return vProduitService.delete(id);
    }
}
