/*package com.openspace.Model.PatientMgnt.Repositories;

import java.time.Month;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.Payment.PaymentForAppointment;

public interface PaymentForAppointmentRepository222 extends PagingAndSortingRepository<PaymentForAppointment, Long> {

	List<PaymentForAppointment> findByPatientId(Long id);
	
	List<PaymentForAppointment> findByPatientIdAndMonth(Long id,Month month);
	
	List<PaymentForAppointment> findByPatient_Email(String userName);
	
	Page<PaymentForAppointment> findByPatient_AdminUserAndPatient_Company_Id(String userName, Long id,  Pageable pageable);

	Page<PaymentForAppointment> findByPatient_AdminUser(String userName, Pageable pageable);

	Page<PaymentForAppointment> findByPatientIdAndPaidAmountGreaterThan(Long id, Double i,  Pageable pageable);

	Page<PaymentForAppointment> findByDoctor_IdAndPaidAmountGreaterThan(Long id, Double i, Pageable pageable);

	Page<PaymentForAppointment> findByPatient_AdminUserAndPaidAmountGreaterThan(String username, Double i, Pageable pageable);
}

*/