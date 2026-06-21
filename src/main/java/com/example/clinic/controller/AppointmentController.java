package com.example.clinic.controller;

import com.example.clinic.dto.appointmentDto.AppointmentCreateDto;
import com.example.clinic.dto.appointmentDto.AppointmentResponseDto;
import com.example.clinic.service.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @GetMapping("api/v1/appointments/{id}")
    public AppointmentResponseDto getAppointmentById(@PathVariable Long id){
        return appointmentService.getById(id);
    }

    @GetMapping("/api/v1/doctors/{id}/appointments")
    public List<AppointmentResponseDto> getAppointmentsByDoctorId(@PathVariable Long id){
        return appointmentService.getByDoctorId(id);
    }

    @GetMapping("/api/v1/patients/{id}/appointments")
    public List<AppointmentResponseDto> getAppointmentsByPatientId(@PathVariable Long id){
        return appointmentService.getByPatientId(id);
    }

    @PostMapping("/api/v1/appointments")
    public AppointmentResponseDto createAppointment(@RequestBody AppointmentCreateDto dto){
        return appointmentService.createAppointment(dto);
    }

    @PatchMapping("/api/v1/appointments/{id}/cancel")
    public AppointmentResponseDto cancelAppointment(@PathVariable Long id){
        return appointmentService.cancelAppointment(id);
    }

    @PatchMapping("/api/v1/appointments/{id}/complete")
    public AppointmentResponseDto completeAppointment(@PathVariable Long id){
        return appointmentService.completeAppointment(id);
    }

    @GetMapping("/api/v1/appointments")
    public Page<AppointmentResponseDto> getAppointments(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ){
        return appointmentService.getAppointments(size, page, sortBy, sortDirection);
    }
}
