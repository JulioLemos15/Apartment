package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.LoginDto;
import com.api.parkingcontrol.models.LoginModel;
import com.api.parkingcontrol.services.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@Api( value = "API REST Login")
public class LoginController {

    final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    @GetMapping("/login")
    @ApiOperation(value = "Retorna o Login existente")
    public ResponseEntity<List<LoginModel>> getAllLogin(){
        return ResponseEntity.status(HttpStatus.OK).body(loginService.findAll());
    }
    @PostMapping("/login/cadastro")
    @ApiOperation(value = "Cadastra um novo Login")
    public ResponseEntity<Object> saveLogin(@RequestBody @Valid LoginDto loginDto){
        var loginModel = new LoginModel();
        BeanUtils.copyProperties(loginDto, loginModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginService.save(loginModel));
    }
    @PutMapping("/login/{id}")
    @ApiOperation(value = "Atualizar Login e senha")
    public ResponseEntity<Object> updateLogin(@PathVariable(value = "id") Long id, @RequestBody @Valid LoginDto loginDto){
        Optional<LoginModel> loginModelOptional = loginService.findById(id);
        if(!loginModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este usuario não existe");
        }
        var loginModel = new LoginModel();
         BeanUtils.copyProperties(loginDto, loginModel);
         loginModel.setId(loginModelOptional.get().getId());
         return ResponseEntity.status(HttpStatus.OK).body(loginService.save(loginModel));
    }

    @PostMapping("/login/logar")
    @ApiOperation(value = "Fazer Login")
    public ResponseEntity<LoginModel> validPassword(@Valid @RequestBody LoginModel loginModel) {
        Boolean valid = loginService.validPassword(loginModel);
        if (!valid){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(200).build();
    }
}
