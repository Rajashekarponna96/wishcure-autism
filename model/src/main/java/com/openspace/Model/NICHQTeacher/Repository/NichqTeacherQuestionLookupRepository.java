package com.openspace.Model.NICHQTeacher.Repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.NICHQTeacherLookup.NichqTeacherQuestionLookup;

@Repository
public interface NichqTeacherQuestionLookupRepository
		extends PagingAndSortingRepository<NichqTeacherQuestionLookup, Long> {
	
	NichqTeacherQuestionLookup findByName(String name);
	
	List<NichqTeacherQuestionLookup> findByNichqTeacherCategoryLookup_Id(Long categoryId);

}
