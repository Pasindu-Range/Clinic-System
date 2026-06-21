package com.example.clinic.dto.appointmentDto;

import com.example.clinic.entity.AppointmentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDto {

    private Long appointmentId;
    private String doctorName;
    private String patientName;
    private LocalDate appointmentDate;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}
