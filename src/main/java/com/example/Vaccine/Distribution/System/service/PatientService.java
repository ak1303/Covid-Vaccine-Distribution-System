package com.example.Vaccine.Distribution.System.service;

import com.example.Vaccine.Distribution.System.dto.request.CreateAppointmentDTO;
import com.example.Vaccine.Distribution.System.dto.request.PatientLoginDTO;
import com.example.Vaccine.Distribution.System.dto.request.PatientSignupDTO;
import com.example.Vaccine.Distribution.System.dto.response.AppointmentDTO;
import com.example.Vaccine.Distribution.System.exceptions.PatientDoesNotExistException;
import com.example.Vaccine.Distribution.System.exceptions.WrongCredentialException;
import com.example.Vaccine.Distribution.System.model.Doctor;
import com.example.Vaccine.Distribution.System.model.Patient;
import com.example.Vaccine.Distribution.System.model.VaccinationCenter;
import com.example.Vaccine.Distribution.System.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    VaccinationCenterService vaccinationCenterService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    MailService mailService;

    public Patient signup(PatientSignupDTO patientSignupDTO){

        Patient patient = new Patient();

        patient.setPatientAddress(patientSignupDTO.getPatientAddress());
        patient.setPatientName(patientSignupDTO.getPatientName());
        patient.setEmail(patientSignupDTO.getEmail());
        patient.setGender(patientSignupDTO.getGender());
        patient.setPassword(patientSignupDTO.getPassword());
        patient.setAadhaarNumber(patientSignupDTO.getAadhaarNumber());
        patient.setPhoneNumber(patientSignupDTO.getPhoneNumber());
        patient.setVcPreference(patientSignupDTO.getVcPreference());

        patientRepository.save(patient);
        return patient;
    }

    public Patient login(PatientLoginDTO patientLoginDTO){
        String email = patientLoginDTO.getEmail();
        String pass = patientLoginDTO.getPassword();
        Patient patient = patientRepository.getPatientByEmail(email);
        if(patient==null){
            throw new PatientDoesNotExistException("Patient email Id is not registered on this portal");
        }
        if(!patient.getPassword().equals(pass)){
            throw new WrongCredentialException("Password entered is Wrong");
        }
        return  patient;
    }

    public AppointmentDTO createAppointment(String email, String type){
        Patient p = patientRepository.getPatientByEmail(email);
        String vaccine = p.getVcPreference();
        List<VaccinationCenter> vcList = vaccinationCenterService.getVccOnTypeAndPreference(type,vaccine);
        VaccinationCenter center = vcList.get(0);
        List<Doctor> docList = doctorService.getDoctorWithMinPatientOnBasisOfVC(center.getId());
        Doctor doctor = docList.get(0);
        //increase patient count for particular doctor and vaccination center
        vaccinationCenterService.updatePatientCountByOneInVC(center);
        doctorService.updatePatientCountByOneInDoctor(doctor);
        //increase dose count of patient
        updateDoseCountByOne(p);
        doctor.getPatients().add(p);
        //assign this patient to a doctor
        doctorService.insertPatientVsDoctor(p.getId(),doctor.getId());

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatient(p);
        appointmentDTO.setVcID(center.getId());
        appointmentDTO.setDocID(doctor.getId());
        appointmentDTO.setDocName(doctor.getDocName());
        appointmentDTO.setVaccinationCenterName(center.getVccName());
        appointmentDTO.setDoseNumber(p.getDoseCount()+1);
        // to send mail
        String to= p.getEmail();
        String sub = String.format("Congratulations !! %s, your slot for vaccination booked",p.getPatientName());
        String text = String.format("""
                        Hi %s your Appointment got created, below are your details
                         1. Dose count : %d
                         2. Doctor Name : %s
                         3. Vaccination Center: %s
                         4. Address: %s""",
                p.getPatientName(),(p.getDoseCount()+1),doctor.getDocName(),center.getVccName(),center.getVccAddress()
        );
        mailService.generateMail(to,sub,text);

        return appointmentDTO;
    }

    public void updateDoseCountByOne(Patient patient){
        patientRepository.updateDoseCountByOne(patient.getId(),patient.getDoseCount()+1);
    }

}
