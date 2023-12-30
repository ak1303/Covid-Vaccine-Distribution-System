package com.example.Vaccine.Distribution.System.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Patient {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String patientName;
    @Column(unique = true)
    private String aadhaarNumber;
    private String vcPreference;
    private int doseCount;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String password;
    private String patientAddress;
    private String gender;
}
