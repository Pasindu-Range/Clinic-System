package com.example.clinic.dto.doctordto;

import com.example.clinic.dto.patientdto.PatientSimpleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDetailsDto {
    private Long id;
    private String name;
    private String specialization;

    private List<PatientSimpleDto> patients;
}
