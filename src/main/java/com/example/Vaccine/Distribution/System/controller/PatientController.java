package com.example.Vaccine.Distribution.System.controller;

import com.example.Vaccine.Distribution.System.dto.request.CreateAppointmentDTO;
import com.example.Vaccine.Distribution.System.dto.request.PatientLoginDTO;
import com.example.Vaccine.Distribution.System.dto.request.PatientSignupDTO;
import com.example.Vaccine.Distribution.System.dto.response.AppointmentDTO;
import com.example.Vaccine.Distribution.System.dto.response.GeneralMessageDTO;
import com.example.Vaccine.Distribution.System.enums.VaccinationCenterPrefrence;
import com.example.Vaccine.Distribution.System.exceptions.PatientDoesNotExistException;
import com.example.Vaccine.Distribution.System.exceptions.WrongCredentialException;
import com.example.Vaccine.Distribution.System.model.Patient;
import com.example.Vaccine.Distribution.System.repository.PatientRepository;
import com.example.Vaccine.Distribution.System.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    @PostMapping("/signup")
    public String signup(@RequestBody PatientSignupDTO patientSignupDTO){
        Patient patient = patientService.signup(patientSignupDTO);
        return "Patient registered successfully";
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody PatientLoginDTO patientLoginDTO){
        try{
            Patient patient = patientService.login(patientLoginDTO);
            return new ResponseEntity(patient, HttpStatus.OK);
        } catch (PatientDoesNotExistException patientDoesNotExistException) {
            return new ResponseEntity(new GeneralMessageDTO(patientDoesNotExistException.getMessage()),HttpStatus.NOT_FOUND);
        }catch (WrongCredentialException WrongCredentialException) {
            return new ResponseEntity(new GeneralMessageDTO(WrongCredentialException.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/createappointment")
    public ResponseEntity createAppointment(@RequestParam String email, @RequestParam VaccinationCenterPrefrence vaccinationCenterPreference){
        AppointmentDTO appointmentDTO = patientService.createAppointment(email,vaccinationCenterPreference.toString());
        return new ResponseEntity(appointmentDTO,HttpStatus.CREATED);
    }
}
