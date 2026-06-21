package com.example.clinic.dto.appointmentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreateDto {
    private Long doctorId;
    private Long patientId;
    private LocalDate appointmentDate;
    private String reason;
}
