package com.example.Vaccine.Distribution.System.model;

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
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    private String vccName;
    private String type;//govt or private
    private int covishield_count;
    private int covaxin_count;
    private int sputnik_count;
    private int patient_count;
    private String vccAddress;
    private int docCount;
    @OneToMany(mappedBy = "vaccinationCenter")
    List<Doctor> doctors;
}
