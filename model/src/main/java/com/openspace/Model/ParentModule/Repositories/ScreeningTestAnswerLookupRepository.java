package com.openspace.Model.ParentModule.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.screeningtest.ScreeningTestAnswerLookup;
@Repository
public interface ScreeningTestAnswerLookupRepository  extends PagingAndSortingRepository<ScreeningTestAnswerLookup,Long> {

}
