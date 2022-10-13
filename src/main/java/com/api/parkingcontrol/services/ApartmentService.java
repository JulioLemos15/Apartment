package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ApartmentModel;
import com.api.parkingcontrol.repositories.ApartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApartmentService {

    final ApartmentRepository apartmentRepository;

    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public Page<ApartmentModel> findAll(Pageable pageable) {
        return apartmentRepository.findAll(pageable);
    }

    public Optional<ApartmentModel> findById(Long id) {
        return apartmentRepository.findById(id);
    }

    public Object save(ApartmentModel apartmentModel) {
        return apartmentRepository.save(apartmentModel);
    }

    public void delete(ApartmentModel apartmentModel) {
        apartmentRepository.delete(apartmentModel);
    }
}
