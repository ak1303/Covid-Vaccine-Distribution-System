package com.example.Vaccine.Distribution.System.controller;

import com.example.Vaccine.Distribution.System.model.VaccinationCenter;
import com.example.Vaccine.Distribution.System.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/vc")
public class VaccinationCenterController {
    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @PostMapping("/register")
    public VaccinationCenter registerVaccinationCenter(@RequestBody VaccinationCenter vaccinationCenter){
        return vaccinationCenterService.registerVaccinationCenter(vaccinationCenter);
    }
}
