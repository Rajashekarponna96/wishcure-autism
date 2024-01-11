package com.openspace.Model.NICHQTeacher.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.NICHQTeacherLookup.NichqTeacherCategoryLookup;

@Repository
public interface NichqTeacherCategorylookupRepository extends PagingAndSortingRepository<NichqTeacherCategoryLookup, Long> {
	
	NichqTeacherCategoryLookup findByName(String name);

}
