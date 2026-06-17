package com.example.clinic.mapper;

import com.example.clinic.dto.DoctorCreateDto;
import com.example.clinic.dto.DoctorResponseDto;
import com.example.clinic.entity.Doctor;

import java.util.List;

public class DoctorMapper {
    public static DoctorResponseDto toDto (Doctor doctor){
        return DoctorMapper.toDto(doctor);
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
}
