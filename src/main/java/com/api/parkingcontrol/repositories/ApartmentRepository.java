package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ApartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<ApartmentModel, Long> {
}
