package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.CategoryType;
import com.openspace.Model.Lookups.Department;

@Repository
public interface CategoryTypeServiceRepository extends PagingAndSortingRepository<CategoryType, Long> {
	
	CategoryType findByName(String name);
	List<CategoryType> findByStatus(boolean b);
	List<CategoryType> findByPatientRegistrationType_Id(Long id);

}
