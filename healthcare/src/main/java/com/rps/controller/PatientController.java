package com.rps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rps.model.Patient;
import com.rps.service.PatientService;

@RestController
@RequestMapping("/api/v1")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@PostMapping("/patients")
	public ResponseEntity<?> addPatientDetails(@RequestBody Patient patient){
		return new ResponseEntity<Patient>(patientService.addPatientDetails(patient),HttpStatus.CREATED);
	}
	
	@GetMapping("/patients")
	public ResponseEntity<?> getPatientDetails(){
		return new ResponseEntity<Iterable<Patient>>(patientService.getPatientDetails(),HttpStatus.OK);
	}
	
	@GetMapping("/patients/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable int id){
		return new ResponseEntity<Patient>(patientService.getPatientDetailsById(id),HttpStatus.OK);
	}
	
	@GetMapping("/patients/byName/{name}")
	public ResponseEntity<?> getPatientByName(@PathVariable String name){
		return new ResponseEntity<Patient>(patientService.getPatientDetailsByName(name),HttpStatus.OK);
	}
	
	@PutMapping("/patients")
	public ResponseEntity<?> updatePatientDetails(@RequestBody Patient patient){
		return new ResponseEntity<Patient>(patientService.updatePatientDetails(patient),HttpStatus.OK);
	}
	
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<?> deletePatientDetails(@PathVariable int id){
		return new ResponseEntity<Patient>(patientService.deletePatientDetails(id),HttpStatus.OK);
	}
}
