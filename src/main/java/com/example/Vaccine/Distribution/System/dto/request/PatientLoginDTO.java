package com.example.Vaccine.Distribution.System.dto.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PatientLoginDTO {
    String email;
    String password;
}
