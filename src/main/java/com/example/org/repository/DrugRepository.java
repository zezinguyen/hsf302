package com.example.org.repository;

import com.example.org.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
    Optional<Drug> findByDrugName(String drugName);
}
