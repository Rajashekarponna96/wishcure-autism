package com.openspace.Model.NICHQParentRepository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.NICHQParent.NICHQParentQuestionLookup;

@Repository
public interface NICHQParentQuestionLookupRepository extends PagingAndSortingRepository<NICHQParentQuestionLookup, Long>{

	NICHQParentQuestionLookup findByName(String name);
	
	List<NICHQParentQuestionLookup> findByNichqParentCategoryLookup_Id(Long categoryId);

}
