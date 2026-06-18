package com.example.clinic.service;

import com.example.clinic.dto.patientdto.PatientResponseDto;
import com.example.clinic.entity.Patient;
import com.example.clinic.mapper.PatientMapper;
import com.example.clinic.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDto> findAll(){
        List<Patient> Patient = patientRepository.findAll();

        return PatientMapper.toDtoList(Patient);
    }

    public PatientResponseDto findById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(()->new RuntimeException("Patient not found"));
        return PatientMapper.toDto(patient);
    }
}
