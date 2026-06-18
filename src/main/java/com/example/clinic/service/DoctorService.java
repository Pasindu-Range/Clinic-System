package com.example.clinic.service;

import com.example.clinic.dto.doctordto.DoctorCreateDto;
import com.example.clinic.dto.doctordto.DoctorDetailsDto;
import com.example.clinic.dto.doctordto.DoctorResponseDto;
import com.example.clinic.entity.Doctor;
import com.example.clinic.mapper.DoctorMapper;
import com.example.clinic.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorResponseDto> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        return DoctorMapper.toDtoList(doctors);
    }

    public DoctorDetailsDto getDoctorById(Long id){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(()->new RuntimeException("doctor not found"));

        return DoctorMapper.toDetailsDto(doctor);
    }

    public DoctorResponseDto createDoctor(DoctorCreateDto dto){
        Doctor doctor = DoctorMapper.toEntity(dto);
        Doctor savedDoctor = doctorRepository.save(doctor);

        return DoctorMapper.toDto(savedDoctor);
    }

    public DoctorResponseDto updateDoctor(Long id, DoctorCreateDto dto){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(()->new RuntimeException("doctor not found"));

        DoctorMapper.updateEntity(doctor,dto);
        return DoctorMapper.toDto(doctor);
    }

    public String deleteDoctor(Long id){
        if(doctorRepository.existsById(id)){
            doctorRepository.deleteById(id);
            return "doctor deleted";
        }
        throw new RuntimeException("doctor not found");
    }

    public List<DoctorResponseDto> getBySpecialization(String specialization){
        List<Doctor> doctors = doctorRepository.findBySpecializationIgnoreCase(specialization);
        if(doctors.isEmpty()){
            throw new RuntimeException("No doctor found for the specialization " + specialization);
        }
        return doctors.stream()
                .map(DoctorMapper::toDto).toList();
    }

    public Page<DoctorResponseDto> getDoctors(int page, int size, String sortBy, String direction){

        Sort sort = direction.equalsIgnoreCase("desc")
                ?Sort.by(sortBy).descending()
                :Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page,size,sort);

        return doctorRepository.findAll(pageable).map(DoctorMapper::toDto);
    }
}
