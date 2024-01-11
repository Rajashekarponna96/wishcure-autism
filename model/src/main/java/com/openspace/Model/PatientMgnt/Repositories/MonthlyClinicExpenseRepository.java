package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Payment.MonthlyClinicExpense;

@Repository
public interface MonthlyClinicExpenseRepository extends PagingAndSortingRepository<MonthlyClinicExpense, Long> {

	List<MonthlyClinicExpense> findByCompany_Id(Long companyId);

	MonthlyClinicExpense findByClinicExpenseType_IdAndUserAccount_Id(Long expenseTypeId, Long userId);
	
	/*
	 * @Query("SELECT new com.openspace.Model.PatientMgnt.Repositories.MonthlyWiseExpenseDTO(u.date, sum(u.amount)) FROM MonthlyClinicExpense u WHERE u.userAccount.username = :name group by u.date"
	 * ) List<MonthlyWiseExpenseDTO> findMonthWiseExpenseByUserName(@Param("name")
	 * String name);
	 */
}

