package com.rps.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rps.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	public Patient findByName(String name);
}
