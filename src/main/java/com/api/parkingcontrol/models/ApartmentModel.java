package com.api.parkingcontrol.models;

import javax.persistence.*;

@Entity
@Table(name = "TB_APARTMENT")
public class ApartmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 45)
    private String bedrooms;
    @Column(nullable = false, length = 45)
    private String bathroom;
    @Column(nullable = false, length = 45)
    private Double condominium;
    @Column(nullable = false, length = 45)
    private Double rent;
    @Column(nullable = false, length = 30)
    private String numberApartment;
    @Column(nullable = false, length = 30)
    private String block;
    @OneToOne
    @JoinColumn(name = "id_parkingSpot")
    public ParkingSpotModel id_parkingSpot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ParkingSpotModel getId_parkingSpot() {
        return id_parkingSpot;
    }

    public void setId_parkingSpot(ParkingSpotModel id_parkingSpot) {
        this.id_parkingSpot = id_parkingSpot;
    }
}
