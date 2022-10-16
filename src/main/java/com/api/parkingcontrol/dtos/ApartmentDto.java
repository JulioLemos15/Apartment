package com.api.parkingcontrol.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ApartmentDto {

    @NotBlank(message = "É Obrigatório informa a quantidade de Quartos")
    private String bedrooms;
    @NotBlank(message = "É Obrigatório informar a quantidade de Banheiros")
    private String bathroom;

    private Double condominium;

    private Double rent;
    @NotBlank(message = "É Obrigatorio informar o numero do Apartamento")
    private String numberApartment;
    @NotBlank(message = "É Obrigatorio informar o bloco")
    private String block;
    @NotNull(message = "É obrigatorio informar o id do estacionamento para vincular com o apartamento")
    private Long id_parkingSpot;

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public Double getCondominium() {
        return condominium;
    }

    public void setCondominium(Double condominium) {
        this.condominium = condominium;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getNumberApartment() {
        return numberApartment;
    }

    public void setNumberApartment(String numberApartment) {
        this.numberApartment = numberApartment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public Long getId_parkingSpot() {
        return id_parkingSpot;
    }

    public void setId_parkingSpot(Long id_parkingSpot) {
        this.id_parkingSpot = id_parkingSpot;
    }
}
