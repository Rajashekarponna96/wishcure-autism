package com.openspace.Model.ParentModule.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.csbs.CsbsAnswerLookup;
@Repository
public interface CsbsAnswerLookupRepository  extends PagingAndSortingRepository<CsbsAnswerLookup,Long> {

}
