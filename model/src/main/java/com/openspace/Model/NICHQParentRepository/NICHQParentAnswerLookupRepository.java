package com.openspace.Model.NICHQParentRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.NICHQParent.NICHQParentAnswerLookup;

@Repository
public interface NICHQParentAnswerLookupRepository extends PagingAndSortingRepository<NICHQParentAnswerLookup, Long>{

}
