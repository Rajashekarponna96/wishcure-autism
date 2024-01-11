package com.openspace.Model.PatientMgnt.Repositories;

import java.time.Month;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.DoctorManagement.Occurance;
import com.openspace.Model.Payment.ModeOfPaymentType;
import com.openspace.Model.Payment.Payment;

public interface PaymentRepository extends PagingAndSortingRepository<Payment, Long> {

	List<Payment> findByPatient_Id(Long id);
	
	List<Payment> findByPatient_IdAndMonth(Long id,Month month);
	
	List<Payment> findByPatient_Email(String userName);
	
	Page<Payment> findByPatient_AdminUserAndPatient_Company_Id(String userName, Long id,  Pageable pageable);

	Page<Payment> findByPatient_AdminUser(String userName, Pageable pageable);

	Page<Payment> findByPatientIdAndPaidAmountGreaterThan(Long id, Double i,  Pageable pageable);

	Page<Payment> findByDoctor_IdAndPaidAmountGreaterThan(Long id, Double i, Pageable pageable);

	Page<Payment> findByPatient_AdminUserAndPaidAmountGreaterThan(String username, Double i, Pageable pageable);

	/*List<Payment> findByModeOfPaymentTypesAndDoctor_Department_IdAndPatient_ClientType_Id(ModeOfPaymentType modeOfPaymentTypes, Long id, Long id2);*/

	List<Payment> findByOccuranceAndDoctor_Department_IdAndPatient_ClientType_Id(Occurance occurance,
			Long id, Long id2);

	List<Payment> findByOccuranceOrDoctor_Department_IdOrPatient_ClientType_Id(Occurance occurance, Long id, Long id2);

	List<Payment> findByDoctor_Department_IdAndPatient_ClientType_Id(Long id2, Long id);

	List<Payment> findByOccuranceAndPatient_ClientType_Id(Occurance occurance, Long id);

	List<Payment> findByOccuranceAndDoctor_Department_Id(Occurance occurance, Long id);

	List<Payment> findByOccurance(Occurance occurance);

	List<Payment> findByMonthAndYear(Month month, int year);

	List<Payment> findByPatientIdAndMonthAndYear(Long id, Month month, int year);
			
	List<Payment> findByPaymentTypes_Id(Long id);
}

