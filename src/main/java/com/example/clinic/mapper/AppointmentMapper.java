package com.example.clinic.mapper;

import com.example.clinic.dto.appointmentDto.AppointmentCreateDto;
import com.example.clinic.dto.appointmentDto.AppointmentResponseDto;
import com.example.clinic.entity.Appointment;
import com.example.clinic.entity.AppointmentStatus;
import com.example.clinic.entity.Doctor;
import com.example.clinic.entity.Patient;

import java.util.List;

public class AppointmentMapper {
    public static AppointmentResponseDto toDto(Appointment appointment) {

        return new AppointmentResponseDto(appointment.getId(), appointment.getDoctor().getName(), appointment.getPatient().getName(), appointment.getAppointmentDate(), appointment.getAppointmentStatus());
    }

    public static List<AppointmentResponseDto> toDtoList(List<Appointment> appointments) {
        return appointments.stream()
                .map(AppointmentMapper::toDto).toList();
    }

    public static Appointment toEntity(Patient patient, Doctor doctor, AppointmentCreateDto dto){
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setReason(dto.getReason());

        appointment.setAppointmentStatus(AppointmentStatus.BOOKED);

        return appointment;
    }
}
