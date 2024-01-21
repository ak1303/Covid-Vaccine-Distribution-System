package com.example.Vaccine.Distribution.System.repository;

import com.example.Vaccine.Distribution.System.model.Doctor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    @Query(value="select * from doctor where vaccination_center_id=:id AND no_of_patient = (select min(no_of_patient) from doctor where vaccination_center_id=:id)",nativeQuery = true)
    List<Doctor> getDoctorWithMinPatientOnBasisOfVC(@Param("id") UUID Id);
    @Modifying
    @Transactional
    @Query(value = "update doctor set no_of_patient=:count where id=:id",nativeQuery = true)
    void updatePatientCountByOneInDoctor(@Param("count") int patientCount,@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = "insert into doctor_patients (doctor_id, patients_id) values(:docId,:patientId)", nativeQuery = true)
    public void insertPatientVsDoctor(@Param("patientId") UUID patientId,@Param("docId") UUID docId);
}
