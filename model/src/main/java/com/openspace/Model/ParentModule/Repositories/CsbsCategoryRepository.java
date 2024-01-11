package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.csbs.CsbsCategory;
@Repository
public interface CsbsCategoryRepository extends PagingAndSortingRepository<CsbsCategory, Long>{


	List<CsbsCategory> findByCsbsCategoryLookup_IdAndUserAccount_Id(Long id, Long id2);
	
	List<CsbsCategory> findByPatient_Id(Long patientId);
	
	//List<CsbsCategory> findByPatient_IdAnd(Long patientId);
	
	//List<CsbsCategory> findByCsbsCategoryLookup_IdAndUserAccount_Id(Long id, Long id2);

	

}
