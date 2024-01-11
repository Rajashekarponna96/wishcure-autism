package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategory;

@Repository
public interface ISAABehaviorCategoryRepository extends PagingAndSortingRepository<ISAABehaviorCategory, Long> {

	List<ISAABehaviorCategory> findByISAABehaviorCategoryLookup_IdAndPatient_Id(Long categoryLookupId, Long patientId);

	List<ISAABehaviorCategory> findByPatient_Id(Long patientId);

	ISAABehaviorCategory findByPatient_IdAndISAABehaviorCategoryLookup_Id(Long patientId, Long categoryLookupId);
}
