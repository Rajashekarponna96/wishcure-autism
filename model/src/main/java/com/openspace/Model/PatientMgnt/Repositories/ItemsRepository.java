package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.Items;

@Repository
public interface ItemsRepository extends PagingAndSortingRepository<Items, Long> {
	
	Items findById(Long id);

	List<Items> findByAppointmentInvoice_Id(Long id);

}
