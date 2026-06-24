package com.example.clinic.dto.doctordto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorCreateDto {
    @NotBlank(message = "Doctor name should include.")
    private String name;
    @NotBlank(message = "Specialization should be included.")
    private String specialization;
}
