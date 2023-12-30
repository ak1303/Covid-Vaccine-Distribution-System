package com.example.Vaccine.Distribution.System.controller;

import com.example.Vaccine.Distribution.System.model.Doctor;
import com.example.Vaccine.Distribution.System.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/register")
    public String registerDoctor(@RequestBody Doctor doctor){
        Doctor doc = doctorService.registerDoctor(doctor);
        return "Doctor registered successfully";
    }
}
