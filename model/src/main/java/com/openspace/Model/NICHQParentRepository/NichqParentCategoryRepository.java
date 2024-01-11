package com.openspace.Model.NICHQParentRepository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.NICHQParent.NichqParentCategory;

@Repository
public interface NichqParentCategoryRepository extends PagingAndSortingRepository<NichqParentCategory, Long>{

	/*List<NichqParentCategory> findByNICHQParentCategoryLookup_IdAndUserAccount_Id(Long id, Long id2);
*/
	List<NichqParentCategory> findByPatient_Id(Long patientId);
	
	NichqParentCategory findByPatient_IdAndNICHQParentCategoryLookup_Id(Long patientId,Long categoryLookupId);

	
	
	
}
