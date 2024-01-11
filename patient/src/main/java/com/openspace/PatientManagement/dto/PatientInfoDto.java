package com.openspace.PatientManagement.dto;

import java.util.List;

import com.openspace.Model.DoctorManagement.Document;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.Lookups.DocumentTypeLookup;

public class PatientInfoDto {

	private Patient patient;

	private DocumentTypeLookup documentTypeLookup;

	private List<Document> documents;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public DocumentTypeLookup getDocumentTypeLookup() {
		return documentTypeLookup;
	}

	public void setDocumentTypeLookup(DocumentTypeLookup documentTypeLookup) {
		this.documentTypeLookup = documentTypeLookup;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
	
	
	

}
