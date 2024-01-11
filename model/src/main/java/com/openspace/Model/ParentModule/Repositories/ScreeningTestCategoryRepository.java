package com.openspace.Model.ParentModule.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.screeningtest.ScreeningTestCategory;
@Repository
public interface ScreeningTestCategoryRepository extends PagingAndSortingRepository<ScreeningTestCategory, Long>{

	List<ScreeningTestCategory> findByCategoryStatusAndUserAccount_Id(String categoryStatus, Long id);

	List<ScreeningTestCategory> findByCategoryStatusAndUserAccount_IdAndDate(String categoryStatus, Long id,
			LocalDate now);
	
	List<ScreeningTestCategory> findByIdAndUserAccount_IdAndDate(Long id, Long id1,
			LocalDate now);
	
	ScreeningTestCategory findByCategoryStatusAndDateAndUserAccount_Id(String categoryStatus,LocalDate now, Long id);

	List<ScreeningTestCategory> findByScreeningTestCategoryLookup_IdAndUserAccount_Id(Long id, Long id1);

	List<ScreeningTestCategory> findByScreeningTestCategoryLookup_IdAndUserAccount_IdAndDate(Long id, Long id2,
			LocalDate date);

	List<ScreeningTestCategory> findByUserAccount_Id(Long id);

}
