package com.example.Vaccine.Distribution.System.dto.request;

import com.example.Vaccine.Distribution.System.enums.VaccinePreference;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PatientSignupDTO {
    private String patientName;
    private String aadhaarNumber;
    private String vcPreference;
    private String phoneNumber;
    private String email;
    private String password;
    private String patientAddress;
    private String gender;
}
