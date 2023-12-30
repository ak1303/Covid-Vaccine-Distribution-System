package com.example.Vaccine.Distribution.System.service;

import com.example.Vaccine.Distribution.System.model.Doctor;
import com.example.Vaccine.Distribution.System.model.VaccinationCenter;
import com.example.Vaccine.Distribution.System.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VaccinationCenterService vaccinationCenterService;

    public Doctor registerDoctor(Doctor doctor){
        List<VaccinationCenter> vcList = vaccinationCenterService.getMinimumDoctorVaccinationCenter();
        VaccinationCenter vcCenter = vcList.get(0);
        doctor.setVaccinationCenter(vcCenter);
        vaccinationCenterService.updateDoctorCountByOne(vcCenter);
        doctorRepository.save(doctor);
        return doctor;
    }
    public List<Doctor> getDoctorWithMinPatientOnBasisOfVC(UUID id){
        return doctorRepository.getDoctorWithMinPatientOnBasisOfVC(id);
    }
    public void updatePatientCountByOneInDoctor(Doctor doctor){
        doctorRepository.updatePatientCountByOneInDoctor(doctor.getNoOfPatient()+1,doctor.getId());
    }
    public void insertPatientVsDoctor(UUID patientId, UUID docId){
        doctorRepository.insertPatientVsDoctor(patientId,docId);
    }
}
