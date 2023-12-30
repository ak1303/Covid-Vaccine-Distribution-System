package com.example.Vaccine.Distribution.System.dto.request;

import com.example.Vaccine.Distribution.System.enums.VaccinationCenterPrefrence;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CreateAppointmentDTO {
    UUID id;
    VaccinationCenterPrefrence vccPreference;
}
