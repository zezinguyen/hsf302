package com.example.org.repository; // Updated package

import com.example.org.entity.Prescription; // Updated import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
