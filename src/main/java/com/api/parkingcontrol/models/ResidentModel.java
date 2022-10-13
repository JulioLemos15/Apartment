package com.api.parkingcontrol.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_RESIDENT")
public class ResidentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 130)
    private String responsibleName;
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    @Column(nullable = false, unique = true, length = 14)
    private String telephone;
    @Column(nullable = false, unique = true, length = 14)
    private String cpf;
    @Column(nullable = false, length = 30)
    private String apartment;
    @Column(nullable = false, length = 30)
    private String block;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @OneToOne
    @JoinColumn(name = "id_apartment")
    public ApartmentModel id_apartment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public ApartmentModel getId_apartment() {
        return id_apartment;
    }

    public void setId_apartment(ApartmentModel id_apartment) {
        this.id_apartment = id_apartment;
    }
}
