package com.openspace.Model.NICHQParentRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.NICHQParent.NICHQParentCategoryLookup;

@Repository
public interface NICHQParentCategoryLookupRepository
		extends PagingAndSortingRepository<NICHQParentCategoryLookup, Long> {

	//NICHQParentCategoryLookup findByNameOrCategoryStatus(String name, String categoryStatus);

	NICHQParentCategoryLookup findByName(String name);

}
