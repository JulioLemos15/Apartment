package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ResidentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<ResidentModel, Long> {

    boolean existsByResponsibleName (String responsibleName);

    boolean existsByEmail (String email);

    boolean existsByTelephone (String telephone);
}
