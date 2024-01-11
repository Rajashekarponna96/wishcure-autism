package com.openspace.Model.NICHQParentRepository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.NICHQParent.NichqParentQuestion;

@Repository
public interface NichqParentQuestionRepository extends PagingAndSortingRepository<NichqParentQuestion, Long>{

	List<NichqParentQuestion> findByNichqParentCategory_Id(Long categoryId);
	
	List<NichqParentQuestion> findByNichqParentCategory_Patient_IdAndNichqParentCategory_Id(Long patientId,Long categoryId);
	
	List<NichqParentQuestion> findByNichqParentCategory_Patient_IdAndNichqParentCategoryLookup_Id(Long patientId,Long categoryId);

	List<NichqParentQuestion> findByNichqParentCategory_Patient_Id(Long patientId);

	List<NichqParentQuestion> findByPatient_Id(Long patientId);
}
