package com.openspace.Model.PatientMgnt.Repositories;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.Holiday;

@Repository
public interface HolidaysRepository extends PagingAndSortingRepository<Holiday, Long> {

	Holiday findByreason(String name);

	List<Holiday> findById(Long id);

	Page<Holiday> findByDoctor_Id(Long id,Pageable pageable);

	/* List<Holiday> findByDoctor_Id(Long id, PageRequest pageRequest); */

	public static String GETALL_HOLIDAYS_BY_DOCTOR_ID_BETWEENDATES = "SELECT h FROM Holiday h WHERE h.doctor.id=:id and  :presentdate BETWEEN h.toDate AND h.fromTime";

	@Query(value = GETALL_HOLIDAYS_BY_DOCTOR_ID_BETWEENDATES)
	List<Holiday> getAllHolidaysByRole(@Param("id") Long id, @Param("presentdate") LocalDate presentdate);

	List<Holiday> findByDoctor_IdAndToTimeGreaterThanEqualAndFromTimeLessThanEqual(Long id, Timestamp start,
			Timestamp end);

	List<Holiday> findByDoctor_IdAndFromDateGreaterThanEqual(Long id, LocalDate toDate);

	List<Holiday> findByDoctor_IdAndFromDateGreaterThanEqualAndToDateLessThanEqual(Long id, LocalDate fromDate, LocalDate toDate);

	List<Holiday> findByDoctor_Id(Long id);
	
	List<Holiday> findByDoctor_IdAndFromDateOrToDate(Long id, LocalDate fromDate, LocalDate toDate);

	List<Holiday> findByDoctor_IdAndFromDate(Long id, LocalDate localDate);

}
