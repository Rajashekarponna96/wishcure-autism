package com.openspace.Model.NICHQTeacher.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.NICHQTeacherLookup.NichqTeacherAnswerLookup;

@Repository
public interface NichqTeacherAnswerLookupRepository extends PagingAndSortingRepository<NichqTeacherAnswerLookup, Long> {

	
}
