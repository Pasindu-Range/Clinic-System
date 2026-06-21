package com.example.clinic.controller;

import com.example.clinic.dto.patientdto.PatientCreateDto;
import com.example.clinic.dto.patientdto.PatientResponseDto;
import com.example.clinic.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/api/v1/patients")
    public List<PatientResponseDto> getAllPatients(){
        return patientService.findAll();
    }

    @GetMapping("/api/v1/patients/{id}")
    public PatientResponseDto getPatientById(@PathVariable Long id){
        return patientService.findById(id);
    }

    @PostMapping("/api/v1/patients")
    public PatientResponseDto createPatient(@RequestBody PatientCreateDto dto){
        return patientService.createPatient(dto);
    }

    @PutMapping("/api/v1/patients/{id}")
    public PatientResponseDto updatePatient(@PathVariable Long id, @RequestBody PatientCreateDto dto){
        return patientService.updatePatient(id, dto);
    }

    @DeleteMapping("/api/v1/patients/{id}")
    public String deletePatient(@PathVariable Long id){
        return patientService.deletePatient(id);
    }

    @GetMapping("/api/v1/doctors/{id}/patients")
    public List<PatientResponseDto> getPatientsByDoctorId(@PathVariable Long id){
        return patientService.getPatientsByDoctorId(id);
    }


}
