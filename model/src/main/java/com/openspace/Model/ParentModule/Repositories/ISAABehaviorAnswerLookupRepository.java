package com.openspace.Model.ParentModule.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorAnswerLookup;
@Repository
public interface ISAABehaviorAnswerLookupRepository  extends PagingAndSortingRepository<ISAABehaviorAnswerLookup,Long> {

}
