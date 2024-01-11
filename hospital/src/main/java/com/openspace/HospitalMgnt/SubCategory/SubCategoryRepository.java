package com.openspace.HospitalMgnt.SubCategory;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.SubCategoryType;

@Repository
public interface SubCategoryRepository extends PagingAndSortingRepository<SubCategoryType, Long>{
	
	SubCategoryType findByName(String name);
	List<SubCategoryType> findById(Long id);
	List<SubCategoryType> findByCategoryType_Id(Long id);

}
