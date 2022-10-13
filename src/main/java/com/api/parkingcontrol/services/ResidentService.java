package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ResidentModel;
import com.api.parkingcontrol.repositories.ResidentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ResidentService {

    final ResidentRepository residentRepository;

    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public Page<ResidentModel> findAll(Pageable pageable) {
        return residentRepository.findAll(pageable);
    }
    @Transactional
    public ResidentModel save(ResidentModel residentModel) {
        return residentRepository.save(residentModel);
    }

    public Optional<ResidentModel> findById(Long id) {
        return residentRepository.findById(id);
    }

    public void delete(ResidentModel residentModel) {
        residentRepository.delete(residentModel);
    }

    public boolean existsByResponsibleName(String responsibleName) {
        return residentRepository.existsByResponsibleName(responsibleName);
    }

    public boolean existsByEmail(String email) {
        return residentRepository.existsByEmail(email);
    }

    public boolean existsByTelephone(String telephone) {
        return residentRepository.existsByTelephone(telephone);
    }
}
