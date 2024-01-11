package com.openspace.PatientManagement.ClinicExpense;

import java.util.List;

import com.openspace.Model.Lookups.ClinicExpenseType;
import com.openspace.Model.PatientMgnt.Repositories.MonthlyWiseExpenseDTO;
import com.openspace.Model.Payment.MonthlyClinicExpense;



public interface ClinicExpenseService {

	void saveClinicExpenseType(ClinicExpenseType clinicExpenseType);
	
	void saveMonthlyClinicExpense(List<MonthlyClinicExpense> monthlyClinicExpense); 

	List<ClinicExpenseType> getAllClinicExpenseTypes();

	void updateClinicExpenseType(ClinicExpenseType clinicExpenseType);

	void deleteClinicExpenseType(Long id);

	List<ClinicExpenseType> getAllExpenseTypesByCompanyId(Long companyId);

	List<ClinicExpenseType> getAllExpenseTypesByCompanyUserName(String name);

	//List<MonthlyWiseExpenseDTO> getAllExpenseMonthWiseByLoggedInUserName(String username);

	void saveClinicExpenses(MonthlyClinicExpense monthlyClinicExpense);

	List<MonthlyClinicExpense> getAllExpenseByCompanyWise(String username);
	

	
	



}

