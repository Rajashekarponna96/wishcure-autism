package com.openspace.Model.NICHQParentRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.NICHQParent.NichqParentAnswer;

@Repository
public interface NichqParentAnswerRepository extends PagingAndSortingRepository<NichqParentAnswer, Long>{

}
