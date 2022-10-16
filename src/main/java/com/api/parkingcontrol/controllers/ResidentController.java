package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ResidentDto;
import com.api.parkingcontrol.models.ApartmentModel;
import com.api.parkingcontrol.models.ResidentModel;
import com.api.parkingcontrol.repositories.ApartmentRepository;
import com.api.parkingcontrol.services.ResidentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@Api( value="API REST Inquilinos")
public class ResidentController {

    final ResidentService residentService;

    final ApartmentRepository apartmentRepository;

    public ResidentController(ResidentService residentService, ApartmentRepository apartmentRepository) {
        this.residentService = residentService;
        this.apartmentRepository = apartmentRepository;
    }

    @GetMapping("/resident")
    @ApiOperation(value = "Retorna todos os Inquilinos do Condominio")
    public ResponseEntity<Page<ResidentModel>> getAllResident(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(residentService.findAll(pageable));
    }

    @PostMapping("/resident/cadastro")
    @ApiOperation(value = "Salva um novo Inquilino")
    public ResponseEntity<Object> saveResident(@RequestBody @Valid ResidentDto residentDto){
        Optional<ApartmentModel> apartmentModel = apartmentRepository.findById(residentDto.getId_apartment());
        if(residentService.existsByResponsibleName(residentDto.getResponsibleName())){
            return ResponseEntity.status((HttpStatus.CONFLICT)).body("Essa pessoa já tem um cadastro!");
        }
        if(residentService.existsByEmail(residentDto.getEmail())){
            return ResponseEntity.status((HttpStatus.CONFLICT)).body("Este email já está em uso!");
        }
        if(residentService.existsByTelephone(residentDto.getTelephone())){
            return ResponseEntity.status((HttpStatus.CONFLICT)).body("Este Telefone já está cadastrado!");
        }
        var residentModel = new ResidentModel();
        BeanUtils.copyProperties(residentDto, residentModel);
        residentModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        residentModel.setId_apartment(apartmentModel.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(residentService.save(residentModel));
    }

    @GetMapping("/resident/{id}")
    @ApiOperation(value = "Retorna um unico Inquilino")
    public ResponseEntity<Object> getOneResident(@PathVariable(value = "id") Long id){
        Optional<ResidentModel> residentModelOptional = residentService.findById(id);
        if (!residentModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este Apartamento não existe!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(residentModelOptional.get());
    }
    @DeleteMapping("/resident/{id}")
    @ApiOperation(value = "Deleta um Inquilino")
    public ResponseEntity<Object> deleteResident(@PathVariable(value = "id") Long id){
        Optional<ResidentModel> residentModelOptional = residentService.findById(id);
        if (!residentModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este apartamento não existe!!");
        }
        residentService.delete(residentModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Ocupação de apartamento excluida com sucesso!");
    }
    @PutMapping("/resident/{id}")
    @ApiOperation("Atualiza um inquilino")
    public ResponseEntity<Object> updateResident(@PathVariable(value = "id") Long id, @RequestBody @Valid ResidentDto residentDto){
        Optional<ResidentModel> residentModelOptional = residentService.findById(id);
        if (!residentModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este apartamento não existe!");
        }
        var apartmentModel = new ResidentModel();
        BeanUtils.copyProperties(residentDto, apartmentModel);
        apartmentModel.setId(residentModelOptional.get().getId());
        apartmentModel.setRegistrationDate(residentModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(residentService.save(apartmentModel));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
