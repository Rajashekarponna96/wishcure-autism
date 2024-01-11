package com.openspace.PatientManagement.controller;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.DoctorManagement.Document;

public interface DocumentRepository extends PagingAndSortingRepository<Document, Long> {

	//List<Document> findByPatient_IdAndDocumentType(Long patientId, DocumentType status);
	List<Document> findByPatient_IdAndFolder_Id(Long patientId,Long folderId);
	
	List<Document> findByFolder_Id(Long folderId);

}
