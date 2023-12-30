package com.example.Vaccine.Distribution.System.service;

import com.example.Vaccine.Distribution.System.model.VaccinationCenter;
import com.example.Vaccine.Distribution.System.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VaccinationCenterService {
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;
    public VaccinationCenter registerVaccinationCenter(VaccinationCenter vaccinationCenter){
        vaccinationCenterRepository.save(vaccinationCenter);
        return vaccinationCenter;
    }
    public List<VaccinationCenter> getMinimumDoctorVaccinationCenter(){
        List<VaccinationCenter> vcList =vaccinationCenterRepository.getMinimumDoctorVaccinationCenter();
        return vcList;
    }
    public void updateDoctorCountByOne(VaccinationCenter vcCenter){
        int docCount = vcCenter.getDocCount()+1;
        UUID vccId = vcCenter.getId();
        vaccinationCenterRepository.updateDoctorCountByOne(docCount,vccId);
    }

    public List<VaccinationCenter> getVccOnTypeAndPreference(String type, String vaccine){
        if(vaccine.equals("Sputnuk")){
            return vaccinationCenterRepository.getVccOnTypeAndSputnikCount(type);
        }else if(vaccine.equals("Covishield")){
            return vaccinationCenterRepository.getVccOnTypeAndCovishieldCount(type);
        }else {
            return vaccinationCenterRepository.getVccOnTypeAndCovaxinCount(type);
        }
    }
    public void updatePatientCountByOneInVC(VaccinationCenter vc){
        vaccinationCenterRepository.updatePatientCountByOneInVC(vc.getPatient_count()+1,vc.getId());
    }
}
