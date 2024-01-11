package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.Items;
import com.openspace.Model.DoctorManagement.ItemsTemplate;

@Repository
public interface ItemsTemplateRepository extends PagingAndSortingRepository<ItemsTemplate, Long> {
	
	ItemsTemplate findById(Long id);

}
