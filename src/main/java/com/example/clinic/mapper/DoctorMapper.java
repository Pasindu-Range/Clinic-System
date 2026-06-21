package com.example.clinic.mapper;

import com.example.clinic.dto.doctordto.DoctorCreateDto;
import com.example.clinic.dto.doctordto.DoctorDetailsDto;
import com.example.clinic.dto.doctordto.DoctorResponseDto;
import com.example.clinic.dto.patientdto.PatientSimpleDto;
import com.example.clinic.entity.Doctor;

import java.util.List;

public class DoctorMapper {
    public static DoctorResponseDto toDto (Doctor doctor){
        return new  DoctorResponseDto(doctor.getId(), doctor.getName(), doctor.getSpecialization());
    }

    public static List<DoctorResponseDto> toDtoList (List<Doctor> doctors){
        return doctors.stream()
                .map(DoctorMapper::toDto).toList();
    }

    public static Doctor toEntity(DoctorCreateDto dto){
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());

        return doctor;
    }

    public static void updateEntity(Doctor doctor, DoctorCreateDto dto){
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
    }

    public static DoctorDetailsDto toDetailsDto(Doctor doctor){
        List<PatientSimpleDto> patients = doctor.getPatients().stream()
                .map(t-> new PatientSimpleDto(t.getId(),t.getName())).toList();

        DoctorDetailsDto dto = new DoctorDetailsDto();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setPatients(patients);

        return dto;

    }
}
