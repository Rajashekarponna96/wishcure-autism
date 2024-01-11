package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.Lookups.DocumentTypeLookup;

public interface DocumentTypeLookupService {

	void add(DocumentTypeLookup documentTypeLookup);

	Page<DocumentTypeLookup>  getall(int page,int size);

	void updateDocumentTypeLookup(DocumentTypeLookup documentTypeLookup);

	void deleteDocumentTypeLookup(Long id);

	List<DocumentTypeLookup> getAll();

}
