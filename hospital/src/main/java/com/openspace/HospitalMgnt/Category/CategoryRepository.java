package com.openspace.HospitalMgnt.Category;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.Category;
@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
	
	Category findByCategoryName(String name);
	List<Category> findById(Long id);
}
