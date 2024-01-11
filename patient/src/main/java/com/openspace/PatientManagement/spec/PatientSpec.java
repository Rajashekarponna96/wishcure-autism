package com.openspace.PatientManagement.spec;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections.functors.PredicateDecorator;
import org.springframework.data.jpa.domain.Specification;

import com.openspace.Model.DoctorManagement.Appointment;
import com.openspace.Model.DoctorManagement.Occurance;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.SubAppointment;
import com.openspace.Model.Dto.PaidStatus;
import com.openspace.Model.Lookups.ClientType;
import com.openspace.Model.Util.ErrorMessageHandler;

public class PatientSpec {

	public static Specification<Patient> findByEmailAndMobilenumberAndUciAndpatientname(String emailId,
			Long mobileNumber, String uciNumber, String patientName) {
		return new Specification<Patient>() {
			List<Predicate> predicates = new ArrayList<Predicate>();

			@Override
			public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if (emailId != null) {
					predicates.add(cb.equal(root.<String> get("email"), emailId));
				}
				if (mobileNumber != null) {
					predicates.add(cb.equal(root.<Long> get("mobileNumber"), mobileNumber));
				}

				if (uciNumber != null) {
					predicates.add(cb.equal(root.<String> get("ucl"), uciNumber));
				}
				if (patientName != null) {
					predicates.add(cb.equal(root.<String> get("firstName"), patientName));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	public static Specification<Patient> serachByAppointTypeAndPaymentTypeAndClientTypeAndDepatmentTypeAndDates(
			LocalDate startDate, LocalDate endDate, Long clientTypeId, Long departmentId, Occurance occurance,
			PaidStatus status) {
		return new Specification<Patient>() {

			List<Predicate> predicates = new ArrayList<Predicate>();

			@Override
			public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				if (startDate != null && endDate != null) {
					if (startDate.isBefore(endDate)) {
						predicates.add(cb.between(root.<List<Appointment>> get("appointments")
								.<List<SubAppointment>> get("subAppointments")
								.<LocalDate> get("appointmentStartedDate"), startDate, endDate));
					} else {
						throw new RuntimeException(ErrorMessageHandler.yourSelectedStartdateShouldBeLessthanEnddate);
					}

				}
				if (status != null) {
					predicates.add(cb.equal(root.<List<Appointment>> get("appointments")
							.<List<SubAppointment>> get("subAppointments").<PaidStatus> get("paidStatus"), status));
				}

				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		};
	}


	public static Specification<Patient> searchByClientTypeAndActive(ClientType clientType, boolean b){
		return new Specification<Patient>() {
			List<Predicate> predicates = new ArrayList<Predicate>();

			@Override
			public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if (clientType != null) {
					predicates.add(cb.equal(root.<ClientType> get("clientType"), clientType));
					predicates.add(cb.equal(root.<Boolean> get("active"), b));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	
	
}
