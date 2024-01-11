package com.openspace.Model.PatientMgnt.Repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.AppointmentInvoice;
import com.openspace.Model.DoctorManagement.AppointmentInvoiceTemplate;

@Repository
public interface AppointmentInvoiceTemplateRpository extends PagingAndSortingRepository<AppointmentInvoiceTemplate, Long> {
	
	AppointmentInvoiceTemplate findById(Long id);
	
}
