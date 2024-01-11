package com.openspace.Model.ParentModule.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.csbs.CsbsQuestionLookup;
@Repository
public interface CsbsQuestionLookupRepository extends PagingAndSortingRepository<CsbsQuestionLookup,Long>{

	CsbsQuestionLookup findByName(String name);

	List<CsbsQuestionLookup> findByCsbsCategoryLookup_id(Long catId);
	
	

	

}
