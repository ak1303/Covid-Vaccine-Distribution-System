package com.example.Vaccine.Distribution.System.repository;

import com.example.Vaccine.Distribution.System.model.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    @Query(value="select * from patient where email=:email",nativeQuery = true)
    public Patient getPatientByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "update patient set dose_count=:doseCount where id=:id",nativeQuery = true)
    void updateDoseCountByOne(@Param("id") UUID id, @Param("doseCount") int doseCount);
}
