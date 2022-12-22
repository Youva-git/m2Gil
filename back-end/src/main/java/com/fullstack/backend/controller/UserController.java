package com.fullstack.backend.controller;

import com.fullstack.backend.dto.ProduitDto;
import com.fullstack.backend.dto.UserDto;
import com.fullstack.backend.service.UserService;
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
@RequestMapping(APP_ROOT+"/users")
@AllArgsConstructor
@Api(APP_ROOT + "/users")
public class UserController {
    private final UserService vUserService;

    @ApiOperation(value = "Ajouter un utilasateur.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilasateur ajouté avec succès."),
            @ApiResponse(code = 400, message = "Utilasateur non valide.")
    })
    @PostMapping("/create")
    public UserDto create(@RequestBody UserDto user){
        return vUserService.create(user);
    }

    @ApiOperation(value = "Rechercher un utilasateur avec son ID.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilasateur trouvé dans la BDD."),
            @ApiResponse(code = 404, message = "Aucun utilasateur n'a été trouvé dans la BDD.")
    })
    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto findById(@PathVariable Integer id){
        return vUserService.findById(id);
    }

    @ApiOperation(value = "Afficher tous les utilasateurs de la BDD.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des utilasateurs (Peut étre vide)."),
    })
    @GetMapping("/read")
    public List<UserDto> read(){
        return vUserService.read();
    }

    @ApiOperation(value = "Rechercher un utilasateur avec son MAIL.", response = ProduitDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilasateur trouvé dans la BDD."),
            @ApiResponse(code = 404, message = "Aucun utilasateur n'a été trouvé dans la BDD.")
    })
    @GetMapping(value = "/findByMail/{mail}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto findById(@PathVariable String mail){
        return vUserService.findByMail(mail);
    }


    @ApiOperation(value = "Mettre à jour un utilasateur avec son ID.", responseContainer = "List<UserDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilasateur mis à ajour avec succès."),
            @ApiResponse(code = 400, message = "Utilasateur non valide.")
    })
    @PutMapping("/update/{id}")
    public UserDto update(@PathVariable Integer id, @RequestBody UserDto user){
        return vUserService.update(id, user);
    }

    @ApiOperation(value = "Supprimer un utilasateur avec son ID.", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilasateur supprimé avec succès."),
            @ApiResponse(code = 404, message = "Cette utilasateur ne peut pas étre supprimé.")
    })
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        return vUserService.delete(id);
    }

}
