package com.example.Vaccine.Distribution.System.dto.response;

import com.example.Vaccine.Distribution.System.model.Patient;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppointmentDTO {
    int doseNumber;
    Patient patient;
    UUID docID;
    String docName;
    UUID vcID;
    String vaccinationCenterName;
}
