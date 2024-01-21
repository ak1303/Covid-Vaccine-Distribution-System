package com.example.Vaccine.Distribution.System.repository;

import com.example.Vaccine.Distribution.System.model.VaccinationCenter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, UUID> {
    @Query(value = "select * from vaccination_center where doc_count = (select min(doc_count) from vaccination_center)", nativeQuery = true)
    public List<VaccinationCenter> getMinimumDoctorVaccinationCenter();
    @Query(value ="select * from vaccination_center where type=:type AND sputnik_count!=0 AND patient_count = (select min(patient_count) from vaccination_center where type=:type AND sputnik_count!=0)", nativeQuery = true)
    public List<VaccinationCenter> getVccOnTypeAndSputnikCount(String type);
    @Query(value ="select * from vaccination_center where type=:type AND covishield_count!=0 AND patient_count = (select min(patient_count) from vaccination_center where type=:type AND covishield_count!=0)", nativeQuery = true)
    public List<VaccinationCenter> getVccOnTypeAndCovishieldCount(String type);
    @Query(value ="select * from vaccination_center where type=:type AND covaxin_count!=0 AND patient_count = (select min(patient_count) from vaccination_center where type=:type AND covaxin_count!=0)", nativeQuery = true)
    public List<VaccinationCenter> getVccOnTypeAndCovaxinCount(String type);

    @Modifying
    @Transactional
    @Query(value = "update vaccination_center set doc_count=:count where id=:id",nativeQuery = true)
    public void updateDoctorCountByOne(@Param("count") int docCount,@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = "update vaccination_center set patient_count=:count where id=:id", nativeQuery = true)
    public void updatePatientCountByOneInVC(@Param("count") int patientCount,@Param("id") UUID id);


}
