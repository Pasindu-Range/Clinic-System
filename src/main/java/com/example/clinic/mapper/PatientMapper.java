package com.example.clinic.mapper;

import com.example.clinic.dto.patientdto.PatientCreateDto;
import com.example.clinic.dto.patientdto.PatientResponseDto;
import com.example.clinic.entity.Doctor;
import com.example.clinic.entity.Patient;

import java.util.List;

public class PatientMapper {

    public static PatientResponseDto toDto(Patient patient){
        return new PatientResponseDto(patient.getId(), patient.getName(), patient.getAge(), patient.getPhone());
    }

    public static List<PatientResponseDto> toDtoList(List<Patient> patients){
        return patients.stream()
                .map(PatientMapper::toDto).toList();
    }

    public static Patient toEntity(PatientCreateDto dto, Doctor doctor){
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setPhone(dto.getPhone());
        patient.setDoctor(doctor);

        return patient;
    }

    public static void updateEntity(Patient patient, PatientCreateDto dto, Doctor doctor){
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setPhone(dto.getPhone());
        patient.setDoctor(doctor);
    }




}
