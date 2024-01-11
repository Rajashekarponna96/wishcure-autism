package com.openspace.Model.spec;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.openspace.Model.DoctorManagement.Appointment;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Occurance;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.SubAppointment;
import com.openspace.Model.Dto.PaidStatus;
import com.openspace.Model.Lookups.Department;

public class SubAppointmentSpec {
	
	public static Specification<SubAppointment> searchByStratDateAndEndDateAndPaidStatusAndOccuranceAndDepartmentIdAndAppointmentType(
			LocalDate stDate,LocalDate endDate,PaidStatus paidStatus,Department dept,Occurance occurance){
		return new Specification<SubAppointment>() {
			List<Predicate> predicates = new ArrayList<Predicate>();

			@Override
			public Predicate toPredicate(Root<SubAppointment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				/*if (stDate != null) {
					predicates.add(cb.equal(root.<LocalDate> get("appointmentStartedDate"), stDate));
				}
				if (endDate != null) {
					predicates.add(cb.equal(root.<LocalDate> get("appointmentEndedDate"), endDate));
				}*/
				if (stDate != null && endDate != null) {
					if (stDate.isBefore(endDate)) {
						predicates.add(cb.between(root.<LocalDate> get("appointmentStartedDate")
								, stDate, endDate));
					} else {
						throw new RuntimeException("Please select StartDate Should be Lessthan EndDate");
					}
				}
				if (paidStatus != null) {
					predicates.add(cb.equal(root.<PaidStatus> get("paidStatus"), paidStatus));
				}
				if (dept != null) {
					predicates.add(cb.equal(root.<Doctor> get("doctor").<Department> get("department"), dept));
				}
				if (occurance != null) {
					predicates.add(cb.equal(root.<Occurance> get("occurance"), occurance));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		};
	}

}
