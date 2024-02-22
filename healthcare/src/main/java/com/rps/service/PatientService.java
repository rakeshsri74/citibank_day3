package com.rps.service;

import com.rps.model.Patient;

public interface PatientService {

	public Iterable<Patient> getPatientDetails();
	public Patient getPatientDetailsById(int id);
	public Patient getPatientDetailsByName(String name);
	public Patient addPatientDetails(Patient patient);
	public Patient updatePatientDetails(Patient patient);
	public Patient deletePatientDetails(int id);
}
