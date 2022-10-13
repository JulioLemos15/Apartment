package com.api.parkingcontrol.controllers;
import com.api.parkingcontrol.dtos.ApartmentDto;
import com.api.parkingcontrol.models.ApartmentModel;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import com.api.parkingcontrol.services.ApartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/apartment")
public class ApartmentController {

    final ApartmentService apartmentService;
    final ParkingSpotRepository parkingSpotRepository;

    public ApartmentController(ApartmentService apartmentService, ParkingSpotRepository parkingSpotRepository) {
        this.apartmentService = apartmentService;
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @GetMapping
    public ResponseEntity<Page<ApartmentModel>> getAllApartment(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(apartmentService.findAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOneApartment(@PathVariable(value = "id") Long id){
        Optional<ApartmentModel> apartmentModelOptional = apartmentService.findById(id);
        if (!apartmentModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este Apartamento não Existe");
        }
        return ResponseEntity.status(HttpStatus.OK).body(apartmentModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveApartment(@RequestBody @Valid ApartmentDto apartmentDto){
        Optional<ParkingSpotModel> parkingSpotModel = parkingSpotRepository.findById(apartmentDto.getId_parkingSpot());
        var apartmentModel = new ApartmentModel();
        BeanUtils.copyProperties(apartmentDto, apartmentModel);
        apartmentModel.setId_parkingSpot(parkingSpotModel.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(apartmentService.save(apartmentModel));
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateApartment(@PathVariable(value = "id") Long id, @RequestBody @Valid ApartmentDto apartmentDto){
        Optional<ApartmentModel> apartmentModelOptional = apartmentService.findById(id);
        if (!apartmentModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este apartamento não existe!");
        }
        var apartmentModel = new ApartmentModel();
        BeanUtils.copyProperties(apartmentDto, apartmentModel);
        apartmentModel.setId(apartmentModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(apartmentService.save(apartmentModel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteApartment(@PathVariable(value = "id") Long id){
        Optional<ApartmentModel> apartmentModelOptional = apartmentService.findById(id);
        if (!apartmentModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este apartamento não existe");
        }
        apartmentService.delete(apartmentModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Apartamento Excluido!");
    }
}
