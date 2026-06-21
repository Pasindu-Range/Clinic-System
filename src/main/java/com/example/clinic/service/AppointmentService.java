package com.example.clinic.service;

import com.example.clinic.dto.appointmentDto.AppointmentCreateDto;
import com.example.clinic.dto.appointmentDto.AppointmentResponseDto;
import com.example.clinic.entity.Appointment;
import com.example.clinic.entity.AppointmentStatus;
import com.example.clinic.entity.Doctor;
import com.example.clinic.entity.Patient;
import com.example.clinic.mapper.AppointmentMapper;
import com.example.clinic.repository.AppointmentRepository;
import com.example.clinic.repository.DoctorRepository;
import com.example.clinic.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorRepository doctorRepository,
                              PatientRepository patientRepository)
    {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public AppointmentResponseDto getById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("appointment not found"));

        return AppointmentMapper.toDto(appointment);
    }

    public List<AppointmentResponseDto> getByDoctorId(Long doctorId){

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()->new RuntimeException("Doctor not found."));

        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
        return AppointmentMapper.toDtoList(appointments);
    }

    public List<AppointmentResponseDto> getByPatientId(Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()->new RuntimeException("Patient not found."));

        List<Appointment> appointment = appointmentRepository.findByPatientId(patientId);
        return AppointmentMapper.toDtoList(appointment);
    }

    public AppointmentResponseDto createAppointment(AppointmentCreateDto dto){
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(()->new RuntimeException("Doctor not found"));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(()->new RuntimeException("Patient not found"));

        Appointment newAppointment = AppointmentMapper.toEntity(patient,doctor,dto);

        appointmentRepository.save(newAppointment);

        return AppointmentMapper.toDto(newAppointment);
    }

    public AppointmentResponseDto cancelAppointment(Long appointmentId) {
        Appointment existedAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()->new RuntimeException("appointment not found"));

        if(existedAppointment.getAppointmentStatus()!=AppointmentStatus.BOOKED){
            throw new RuntimeException("Only Booked Appointments can be cancelled.");
        }

        existedAppointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(existedAppointment);
        return AppointmentMapper.toDto(existedAppointment);
    }

    public AppointmentResponseDto completeAppointment(Long appointmentId) {
        Appointment existedAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()->new RuntimeException("appointment not found"));

        if(existedAppointment.getAppointmentStatus()!=AppointmentStatus.BOOKED){
            throw new RuntimeException("Only Booked Appointments can be completed.");
        }
        existedAppointment.setAppointmentStatus(AppointmentStatus.COMPLETED);
        appointmentRepository.save(existedAppointment);
        return AppointmentMapper.toDto(existedAppointment);
    }

    public Page<AppointmentResponseDto> getAppointments(int size, int page, String sortBy, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("desc")
                ?Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page,size,sort);

        return appointmentRepository.findAll(pageable).map(AppointmentMapper :: toDto);
    }
}
