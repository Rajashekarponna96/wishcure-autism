package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestion;

public interface ISAABehaviorQuestionRepository extends PagingAndSortingRepository<ISAABehaviorQuestion, Long> {

	List<ISAABehaviorQuestion> findByISAABehaviorCategory_Id(Long categoryId);

	List<ISAABehaviorQuestion> findByISAABehaviorCategory_Patient_IdAndIsaaBehaviorCategoryLookup_Id(Long patientId,
			Long categoryId);

	List<ISAABehaviorQuestion> findByISAABehaviorCategory_Patient_IdAndISAABehaviorCategory_Id(Long patientId,
			Long categoryId);

}
