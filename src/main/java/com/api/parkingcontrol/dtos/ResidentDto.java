package com.api.parkingcontrol.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResidentDto {
    @NotBlank
    private String responsibleName;
    @NotBlank
    private String email;
    @NotBlank
    @Size(max = 13)
    private String telephone;
    @NotBlank
    @Size(max = 14)
    private String cpf;
    @NotBlank
    private String apartment;
    @NotBlank
    private String block;
    @NotNull
    private Long id_apartment;

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getId_apartment() {
        return id_apartment;
    }

    public void setId_apartment(Long id_apartment) {
        this.id_apartment = id_apartment;
    }
}


