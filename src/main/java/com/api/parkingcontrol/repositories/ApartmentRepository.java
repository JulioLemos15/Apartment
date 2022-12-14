package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ApartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<ApartmentModel, Long> {
}
