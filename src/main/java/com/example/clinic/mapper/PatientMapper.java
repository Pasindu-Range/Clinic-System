package com.example.clinic.mapper;

import com.example.clinic.dto.patientdto.PatientResponseDto;
import com.example.clinic.entity.Patient;

import java.util.List;

public class PatientMapper {

    public static PatientResponseDto toDto(Patient patient){
        return PatientMapper.toDto(patient);
    }

    public static List<PatientResponseDto> toDtoList(List<Patient> patients){
        return patients.stream()
                .map(PatientMapper::toDto).toList();
    }




}
