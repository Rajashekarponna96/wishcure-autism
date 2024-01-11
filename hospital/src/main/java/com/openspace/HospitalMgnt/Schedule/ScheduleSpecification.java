package com.openspace.HospitalMgnt.Schedule;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.openspace.Model.DoctorManagement.Schedule;


public class ScheduleSpecification {

	public static  Specification<Schedule> searchSchedule(String username){
	//public static Specification<Schedule> search(LocalDate fromTime, LocalDate toTime) { 

		return new Specification<Schedule>() {

			@Override
			public Predicate toPredicate(Root<Schedule> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				
				
				/*if (username != null) {
					predicates.add(cb.like(x, pattern)(
							root.<LocalDate> get("openDate"), fromTime));

				}*/
				

				return cb.and(predicates.toArray(new Predicate[predicates
						.size()]));
			}

		};

	}
	
	
	
	
/*	public static  Specification<Schedule> saveSchedule(ScheduleDto scheduleDto,LocalDate toTime, LocalDate toTime){
	//public static Specification<Schedule> search(LocalDate startDate, LocalDate endDate) { 

		return new Specification<Schedule>() {

			@Override
			public Predicate toPredicate(Root<Schedule> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();

				
				
				if (startDate != null) {
					predicates.add(cb.greaterThanOrEqualTo(
							root.<LocalDate> get("openDate"), startDate));

				}
				
				if (endDate != null) {
					predicates.add(cb.lessThanOrEqualTo(
							root.<LocalDate> get("closedDate"), endDate));

				}
				if (startDate != null && endDate != null) {

					if (startDate.equals(endDate)) {

						predicates.add(cb.equal(
								root.<LocalDate> get("openDate"), endDate));

					} else if (startDate.isBefore(endDate)) {

						predicates.add(cb.lessThanOrEqualTo(
								root.<LocalDate> get("openDate"), endDate));

					} else if (endDate.isAfter(startDate)) {

						predicates.add(cb.greaterThanOrEqualTo(
								root.<LocalDate> get("closedDate"), startDate));

					}

				}

				return cb.and(predicates.toArray(new Predicate[predicates
						.size()]));
			}

		};

	}*/
	
	
	
	

}
