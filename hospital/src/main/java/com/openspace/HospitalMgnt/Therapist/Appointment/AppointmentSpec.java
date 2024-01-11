package com.openspace.HospitalMgnt.Therapist.Appointment;

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

public class AppointmentSpec {

	public static Specification<Appointment> search(Long doctorId, LocalDate startDate, LocalDate endDate) {
		return new Specification<Appointment>() {

			@Override
			public Predicate toPredicate(Root<Appointment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (doctorId != null) {
					predicates.add(cb.equal(root.<Doctor> get("doctor").<Long> get("id"), doctorId));
				}
				if (startDate != null) {
					predicates.add(cb.greaterThanOrEqualTo(root.<LocalDate> get("appointmentStartedDate"), startDate));
				}
				if (endDate != null) {
					predicates.add(cb.lessThanOrEqualTo(root.<LocalDate> get("appointmentEndedDate"), endDate));
				}
				if (startDate != null && endDate != null) {

					if (startDate.equals(endDate)) {

						predicates.add(cb.equal(root.<LocalDate> get("appointmentStartedDate"), endDate));

					} else if (endDate.isAfter(startDate)) {

						predicates.add(cb.lessThanOrEqualTo(root.<LocalDate> get("appointmentEndedDate"), startDate));

					}
					if (startDate.isBefore(endDate)) {

						predicates
								.add(cb.greaterThanOrEqualTo(root.<LocalDate> get("appointmentStartedDate"), endDate));

					}

				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};

	}

}
