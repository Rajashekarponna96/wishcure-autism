package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.DocumentTypeLookup;
import com.openspace.Model.PatientMgnt.Repositories.DocumentTypeLookupRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class DocumentTypeLookupServiceImpl implements DocumentTypeLookupService {

	@Autowired
	private DocumentTypeLookupRepository documentTypeLookupRepository;

	@Override
	public void add(DocumentTypeLookup documentTypeLookup) {
		DocumentTypeLookup documentTypeLookup2 = documentTypeLookupRepository
				.findByFolderName(documentTypeLookup.getFolderName());
		if (documentTypeLookup2 != null) {
			throw new RuntimeException(ErrorMessageHandler.documentLookupALreadyExists);
		}
		documentTypeLookupRepository.save(documentTypeLookup);
	}

	@Override
	public Page<DocumentTypeLookup> getall(int page, int size) {
		return documentTypeLookupRepository.findAll(new PageRequest(page, size, Sort.Direction.DESC, "id"));
	}

	@Override
	public void updateDocumentTypeLookup(DocumentTypeLookup documentTypeLookup) {
		// TODO Auto-generated method stub
		DocumentTypeLookup dbDocumentTypeLookup = documentTypeLookupRepository.findOne(documentTypeLookup.getId());
		if (dbDocumentTypeLookup == null) {
			throw new RuntimeException(ErrorMessageHandler.documentLookupDoesNotExists);
		}
		DocumentTypeLookup documentTypeLookup2 = documentTypeLookupRepository
				.findByFolderName(documentTypeLookup.getFolderName());
		if (documentTypeLookup2 == null) {
			dbDocumentTypeLookup.setFolderName(documentTypeLookup.getFolderName());
			dbDocumentTypeLookup.setDescription(documentTypeLookup.getDescription());
			dbDocumentTypeLookup.setStatus(documentTypeLookup.getStatus());
		} else if (documentTypeLookup2.getId().equals(documentTypeLookup.getId())) {
			dbDocumentTypeLookup.setFolderName(documentTypeLookup.getFolderName());
			dbDocumentTypeLookup.setDescription(documentTypeLookup.getDescription());
			dbDocumentTypeLookup.setStatus(documentTypeLookup.getStatus());
		} else {
			throw new RuntimeException(ErrorMessageHandler.documentLookupALreadyExists);
		}
		dbDocumentTypeLookup.setFolderName(documentTypeLookup.getFolderName());
		dbDocumentTypeLookup.setDescription(documentTypeLookup.getDescription());
		dbDocumentTypeLookup.setStatus(documentTypeLookup.getStatus());
		documentTypeLookupRepository.save(dbDocumentTypeLookup);
	}

	@Override
	public void deleteDocumentTypeLookup(Long id) {
		DocumentTypeLookup documentTypeLookup = documentTypeLookupRepository.findOne(id);
		if (documentTypeLookup != null) {
			try {
				documentTypeLookupRepository.delete(documentTypeLookup);
			} catch (Exception e) {
				throw new RuntimeException("Can't Delete, It's Already In Use....!!");
			}
		}
	}

	@Override
	public List<DocumentTypeLookup> getAll() {
		return (List<DocumentTypeLookup>) documentTypeLookupRepository.findAll();
	}

}
