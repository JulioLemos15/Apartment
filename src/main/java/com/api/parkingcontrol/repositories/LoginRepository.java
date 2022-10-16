package com.api.parkingcontrol.repositories;


import com.api.parkingcontrol.models.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, Long> {
}
