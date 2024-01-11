package com.openspace.PatientManagement.ClinicExpense;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.ClinicExpenseType;
import com.openspace.Model.PatientMgnt.Repositories.ClinicExpenseRepository;
import com.openspace.Model.PatientMgnt.Repositories.MonthlyClinicExpenseRepository;
import com.openspace.Model.PatientMgnt.Repositories.MonthlyWiseExpenseDTO;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.Payment.MonthlyClinicExpense;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class ClinicExpenseServiceImpl implements ClinicExpenseService {

	@Autowired
	private ClinicExpenseRepository clinicExpenseRepository;
	
	@Autowired
	private MonthlyClinicExpenseRepository monthlyClinicExpenseRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public void saveClinicExpenseType(ClinicExpenseType clinicExpenseType) {
		
		UserAccount dbUserAccount=userAccountRepository.findByUsername(clinicExpenseType.getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		clinicExpenseType.setCompany(dbUserAccount.getCompany());
		clinicExpenseType.setUserAccount(dbUserAccount);
		
		ClinicExpenseType dbExpenseType = null;

		
		dbExpenseType = clinicExpenseRepository.findByExpenseTypeAndUserAccount_Id(
				clinicExpenseType.getExpenseType(), dbUserAccount.getId());
		

		if (dbExpenseType != null) {
			throw new RuntimeException(ErrorMessageHandler.expenseTypeAlreadyExists);
		}
		
		
		
		clinicExpenseRepository.save(clinicExpenseType);
	}

	@Override
	public List<ClinicExpenseType> getAllClinicExpenseTypes() {
		// TODO Auto-generated method stub
		return  (List<ClinicExpenseType>) clinicExpenseRepository.findAll();
	}

	@Override
	public void updateClinicExpenseType(ClinicExpenseType clinicExpenseType) {
		clinicExpenseRepository.save(clinicExpenseType);		
	}

	@Override
	public void deleteClinicExpenseType(Long id) {
		clinicExpenseRepository.delete(id);		
	}

	@Override
	public List<ClinicExpenseType> getAllExpenseTypesByCompanyId(Long companyId) {
		// TODO Auto-generated method stub
		return clinicExpenseRepository.findByCompany_Id(companyId);
	}

	@Override
	public List<ClinicExpenseType> getAllExpenseTypesByCompanyUserName(String username) {
		
	
		UserAccount dbUserAccount = userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<ClinicExpenseType> clinicExpenseTypeList = new ArrayList<ClinicExpenseType>();
		
		if (dbUserAccount.getCompany() != null) {
			clinicExpenseTypeList = clinicExpenseRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
		
	}
		return clinicExpenseTypeList;
	}

	@Override
	public void saveMonthlyClinicExpense(List<MonthlyClinicExpense> monthlyClinicExpenseList) {
		UserAccount dbUserAccount=userAccountRepository.findByUsername(monthlyClinicExpenseList.get(0).getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		
		monthlyClinicExpenseList.forEach(monthlyClinicExpense->{
			monthlyClinicExpense.setCompany(dbUserAccount.getCompany());
			monthlyClinicExpense.setUserAccount(dbUserAccount);
		});
		
		monthlyClinicExpenseRepository.save(monthlyClinicExpenseList);
	}

	/*
	 * @Override public List<MonthlyWiseExpenseDTO>
	 * getAllExpenseMonthWiseByLoggedInUserName(String username) { UserAccount
	 * dbUserAccount=userAccountRepository.findByUsername(username); if
	 * (dbUserAccount == null) { throw new
	 * RuntimeException(ErrorMessageHandler.userDoesNotExists); }
	 * List<MonthlyWiseExpenseDTO> expenseList = new ArrayList<>(); expenseList =
	 * monthlyClinicExpenseRepository.findMonthWiseExpenseByUserName(dbUserAccount.
	 * getUsername()); return expenseList; }
	 */

	@Override
	public void saveClinicExpenses(MonthlyClinicExpense monthlyClinicExpense) {
		UserAccount dbUserAccount=userAccountRepository.findByUsername(monthlyClinicExpense.getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		monthlyClinicExpense.setCompany(dbUserAccount.getCompany());
		monthlyClinicExpense.setUserAccount(dbUserAccount);
		

		
		
		monthlyClinicExpenseRepository.save(monthlyClinicExpense);		
	}

	@Override
	public List<MonthlyClinicExpense> getAllExpenseByCompanyWise(String username) {
			
		
			UserAccount dbUserAccount = userAccountRepository.findByUsername(username);
			if (dbUserAccount == null) {
				throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
			}
			List<MonthlyClinicExpense> clinicExpensesList = new ArrayList<MonthlyClinicExpense>();
			
			if (dbUserAccount.getCompany() != null) {
				clinicExpensesList = monthlyClinicExpenseRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
			
		}
			return clinicExpensesList;
		}
	


	


}
