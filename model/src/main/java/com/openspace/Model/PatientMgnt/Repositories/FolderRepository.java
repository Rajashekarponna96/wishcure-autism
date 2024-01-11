package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Parent.Folder;

@Repository
public interface FolderRepository extends PagingAndSortingRepository<Folder, Long> {

	Folder findByName(String name);
	List<Folder> findById(Long id);
	
	Folder findByNameAndPatient_Id(String name,Long id);
	
	List<Folder> findByPatient_Id(Long patientId);
	
}
