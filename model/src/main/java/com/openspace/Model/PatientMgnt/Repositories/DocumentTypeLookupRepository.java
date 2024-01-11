package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.DocumentTypeLookup;

@Repository
public interface DocumentTypeLookupRepository extends PagingAndSortingRepository<DocumentTypeLookup, Long> {

	DocumentTypeLookup findByFolderName(String folder);
}
