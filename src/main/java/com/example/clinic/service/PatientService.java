package com.example.clinic.service;

import com.example.clinic.dto.patientdto.PatientCreateDto;
import com.example.clinic.dto.patientdto.PatientResponseDto;
import com.example.clinic.entity.Doctor;
import com.example.clinic.entity.Patient;
import com.example.clinic.mapper.PatientMapper;
import com.example.clinic.repository.DoctorRepository;
import com.example.clinic.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public PatientService(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<PatientResponseDto> findAll(){
        List<Patient> Patient = patientRepository.findAll();

        return PatientMapper.toDtoList(Patient);
    }

    public PatientResponseDto findById(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(()->new RuntimeException("Patient not found"));
        return PatientMapper.toDto(patient);
    }

    public PatientResponseDto createPatient(PatientCreateDto dto){
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(()->new RuntimeException("Doctor not found"));
        Patient savedPatient = PatientMapper.toEntity(dto, doctor);
        patientRepository.save(savedPatient);

        return PatientMapper.toDto(savedPatient);
    }

    public PatientResponseDto updatePatient(Long id, PatientCreateDto dto){
        Doctor existedDoctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(()->new RuntimeException("Doctor not found"));

        Patient existedPatient = patientRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Patient not found"));

        PatientMapper.updateEntity(existedPatient, dto, existedDoctor);

        Patient savedPatient = patientRepository.save(existedPatient);

        return PatientMapper.toDto(savedPatient);
    }

    public String deletePatient(Long id){
        if(patientRepository.existsById(id)){
            patientRepository.deleteById(id);
            return "Patient has been deleted";
        }
        throw new RuntimeException("Patient not found");
    }

    public Page<PatientResponseDto> getPatients(int page, int size, String sortBy, String direction){

        Sort sort = direction.equalsIgnoreCase("desc")
                ?Sort.by(sortBy).descending()
                :Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return patientRepository.findAll(pageable).map(PatientMapper::toDto);
    }

    public List<PatientResponseDto> getPatientsByName(String name){
        List<Patient> Patients = patientRepository.findByNameIgnoreCase(name);

        if(Patients.isEmpty()){
            throw new RuntimeException("Patient not found");
        }
        return Patients.stream()
                .map(PatientMapper::toDto).toList();
    }

    public List<PatientResponseDto> getPatientsByDoctorId(Long doctorId){
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()->new RuntimeException("Doctor not found"));
        List<Patient> patients = patientRepository.findByDoctorId(doctorId);

        if (patients.isEmpty()){
            throw new RuntimeException("No Patient found the doctorId "+ doctorId);
        }
        return PatientMapper.toDtoList(patients);

    }
}
