package com.openspace.Model.PatientMgnt.Repositories;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.AppointmentInvoice;

@Repository
public interface AppointmentInvoiceRpository extends PagingAndSortingRepository<AppointmentInvoice, Long> {
	
	AppointmentInvoice findById(Long id);
	
	AppointmentInvoice findByInvoiceNumber(String invoiceNo);

	AppointmentInvoice findByInvoiceNumberAndPatient_Id(String invoiceNumber, Long patientId);

	List<AppointmentInvoice> findByPaymethod_Id(Long id);
	
}
