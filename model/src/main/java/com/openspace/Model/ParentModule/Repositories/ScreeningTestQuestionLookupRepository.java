package com.openspace.Model.ParentModule.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.screeningtest.ScreeningTestQuestionLookup;
@Repository
public interface ScreeningTestQuestionLookupRepository extends PagingAndSortingRepository<ScreeningTestQuestionLookup,Long>{

	ScreeningTestQuestionLookup findByName(String name);

}
