package com.example.Vaccine.Distribution.System.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Doctor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String docName;
    private String docDegree;
    private int noOfPatient;
    @ManyToOne
    @JsonIgnore
    VaccinationCenter vaccinationCenter;
    @ManyToMany
    List<Patient> patients;
}
