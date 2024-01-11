
package com.openspace.PatientManagement.ClinicExpense;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Lookups.ClinicExpenseType;
import com.openspace.Model.Payment.MonthlyClinicExpense;

@RestController
public class ClinicExpenseController {

	@Autowired
	private ClinicExpenseService clinicExpenseService;

	@RequestMapping(value = "/saveClinicExpenseType", method = RequestMethod.POST)
	public @ResponseBody void saveClinicExpenseType(@RequestBody ClinicExpenseType clinicExpenseType) {
		clinicExpenseService.saveClinicExpenseType(clinicExpenseType);
	}
	
	@RequestMapping(value = "/saveMonthlyClinicExpense", method = RequestMethod.POST)
	public @ResponseBody void saveMonthlyClinicExpense(@RequestBody List<MonthlyClinicExpense> monthlyClinicExpense) {
		clinicExpenseService.saveMonthlyClinicExpense(monthlyClinicExpense);
	}

	@RequestMapping(value = "/getAllClinicExpenseTypes", method = RequestMethod.GET)
	public @ResponseBody List<ClinicExpenseType> getAllClinicExpenseTypes() {
		return clinicExpenseService.getAllClinicExpenseTypes();
	}

	@RequestMapping(value = "/updateClinicExpenseType", method = RequestMethod.PUT)
	public @ResponseBody void updateClinicExpenseType(@RequestBody ClinicExpenseType clinicExpenseType) {
		clinicExpenseService.updateClinicExpenseType(clinicExpenseType);
	}

	
	@RequestMapping(value = "/deleteClinicExpenseType/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteClinicExpenseType(@PathVariable Long id) {
		clinicExpenseService.deleteClinicExpenseType(id);
	}

	@RequestMapping(value ="/getAllExpenseTypesByCompanyId" , method = RequestMethod.GET)
	public @ResponseBody List<ClinicExpenseType> getAllExpenseTypesByCompanyId(@PathVariable Long companyId) {
		return clinicExpenseService.getAllExpenseTypesByCompanyId(companyId);
	}
	

	@RequestMapping(value = "/getAllExpenseTypesByCompanyUserName/{username:.+}", method = RequestMethod.GET)
	public @ResponseBody List<ClinicExpenseType> getAllExpenseTypesByCompanyUserName(@PathVariable String username) {
		return clinicExpenseService.getAllExpenseTypesByCompanyUserName(username);
	}
	
	/*
	 * @RequestMapping(value =
	 * "/getAllExpenseMonthWiseByLoggedInUserName/{username:.+}", method =
	 * RequestMethod.GET) public @ResponseBody List<MonthlyWiseExpenseDTO>
	 * getAllExpenseMonthWiseByLoggedInUserName(@PathVariable String username) {
	 * return
	 * clinicExpenseService.getAllExpenseMonthWiseByLoggedInUserName(username); }
	 */
	@RequestMapping(value = "/saveClinicExpenses", method = RequestMethod.POST)
	public @ResponseBody void saveClinicExpenses(@RequestBody MonthlyClinicExpense monthlyClinicExpense) {
		clinicExpenseService.saveClinicExpenses(monthlyClinicExpense);
	}
	
	@RequestMapping(value = "/getAllExpenseByCompanyWise/{username:.+}", method = RequestMethod.GET)
	public @ResponseBody List<MonthlyClinicExpense> getAllExpenseByCompanyWise(@PathVariable String username) {
		return clinicExpenseService.getAllExpenseByCompanyWise(username);
	}	
}
