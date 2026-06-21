package com.example.clinic.controller;

import com.example.clinic.dto.doctordto.DoctorCreateDto;
import com.example.clinic.dto.doctordto.DoctorDetailsDto;
import com.example.clinic.dto.doctordto.DoctorResponseDto;
import com.example.clinic.service.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping("/api/v1/doctors")
    public List<DoctorResponseDto> getAll(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("api/v1/doctors/{id}")
    public DoctorDetailsDto getDoctorById(@PathVariable Long id){
        return doctorService.getDoctorById(id);
    }

    @PostMapping("api/v1/doctors")
    public DoctorResponseDto createDoctor(@RequestBody DoctorCreateDto dto){
        return doctorService.createDoctor(dto);
    }

    @PutMapping("api/v1/doctors/{id}")
    public DoctorResponseDto updateDoctor(@PathVariable Long id, @RequestBody DoctorCreateDto dto){
        return doctorService.updateDoctor(id, dto);
    }

    @DeleteMapping("/api/v1/doctors/{id}")
    public String deleteDoctor(@PathVariable Long id){
        return doctorService.deleteDoctor(id);
    }

    @GetMapping("/api/v1/doctors/filter")
    public List<DoctorResponseDto> getBySpecialization(@RequestParam String specialization){
        return doctorService.getBySpecialization(specialization);
    }

    @GetMapping("/api/v1/doctors/sort")
    public Page<DoctorResponseDto> getDoctors(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam (defaultValue = "asc") String direction){
        return doctorService.getDoctors(page,size,sortBy,direction);
    }
}