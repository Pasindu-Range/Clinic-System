package com.example.clinic.dto.patientdto;

import com.example.clinic.entity.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientCreateDto {
    private String name;
    private int age;
    private String phone;

    private Long doctorId;
}
