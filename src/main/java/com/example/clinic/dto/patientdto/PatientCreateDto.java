package com.example.clinic.dto.patientdto;

import com.example.clinic.entity.AppointmentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientCreateDto {
    @NotBlank(message = "Patient name should included.")
    private String name;
    private int age;
    @NotBlank(message = "Phone number should be included.")
    private String phone;
    private Long doctorId;
}
