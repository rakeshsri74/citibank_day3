package com.rps.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rps.dao.PatientRepository;
import com.rps.model.Patient;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Override
	public Iterable<Patient> getPatientDetails() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}

	@Override
	public Patient getPatientDetailsById(int id) {
		// TODO Auto-generated method stub
		return patientRepository.findById(id).get();
		
	}

	@Override
	public Patient addPatientDetails(Patient patient) {
		// TODO Auto-generated method stub
		return patientRepository.save(patient);
	}

	@Override
	public Patient updatePatientDetails(Patient patient) {
		Optional<Patient> patientFind = patientRepository.findById(patient.getId());
		if(patientFind.isPresent()) {
			Patient patientUpdate = patientFind.get();
			patientUpdate.setName(patient.getName());
			patientUpdate.setAge(patient.getAge());
			patientUpdate.setGender(patient.getGender());
			patientUpdate.setAddress(patient.getAddress());
			patientUpdate.setDescription(patient.getDescription());
			patientRepository.save(patientUpdate);
			return patientUpdate;
		}
		return null;
	}

	@Override
	public Patient deletePatientDetails(int id) {
		Optional<Patient> patientFind = patientRepository.findById(id);
		if(patientFind.isPresent()) {
			Patient patientDelete = patientFind.get();
			patientRepository.delete(patientDelete);
			return patientDelete;
		}
		return null;
	}

	@Override
	public Patient getPatientDetailsByName(String name) {
		// TODO Auto-generated method stub
		return patientRepository.findByName(name);
	}

}
