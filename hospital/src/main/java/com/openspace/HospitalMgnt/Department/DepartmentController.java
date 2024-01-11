package com.openspace.HospitalMgnt.Department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.DoctorManagement.PatientRegistrationType;
import com.openspace.Model.Lookups.Department;

@RestController
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value = RestURIConstants.SAVE_DEPARTMENT, method = RequestMethod.POST)
	public @ResponseBody void saveDepartment(@RequestBody Department department) {
		departmentService.saveDepartment(department);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_DEPARTMENTS, method = RequestMethod.GET)
	public @ResponseBody Page<Department> getAllDepartments(@PathVariable String username,@RequestParam("page")int page,@RequestParam("size")int size) {
		System.out.println("inside dept con");
		return departmentService.getAllDepartments(username,page,size);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_DEPARTMENTS_SEARCH, method = RequestMethod.GET)
	public @ResponseBody Page<Department> getAllDepartmentsSearch(@PathVariable String username,@PathVariable String search,@RequestParam("page")int page,@RequestParam("size")int size) {
		return departmentService.getAllDepartmentsSearch(username,search,page,size);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_DEPARTMENTS_LIST, method = RequestMethod.GET)
	public @ResponseBody List<Department> getAllDepartments() {
		return departmentService.getAllDepartments();
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_DEPARTMENTS_LIST_BY_COMPANY, method = RequestMethod.GET)
	public @ResponseBody List<Department> getAllDepartmentsList(@PathVariable String username) {
		return departmentService.getAllDepartmentsList(username);
	}
	
	@RequestMapping(value = RestURIConstants.UPDATE_DEPARTMENT, method = RequestMethod.PUT)
	public @ResponseBody void updateDepartment(@RequestBody Department department) {
		departmentService.updateDepartment(department);
	}

	@RequestMapping(value = RestURIConstants.DELETE_DEPARTMENT, method = RequestMethod.DELETE)
	public @ResponseBody void deleteDepartment(@PathVariable Long id) {
		departmentService.deleteDepartment(id);
	}

	/*@RequestMapping(value="/pagination",method=RequestMethod.GET)
	public Page<Department> findAll(@RequestParam("page")int page,@RequestParam("size")int size){
		return departmentService.findAll(page,size);
	}*/
	
	@RequestMapping(value = RestURIConstants.GET_ALL_DEPARTMENTS_BY_ROLE, method = RequestMethod.GET)
	public @ResponseBody List<Department> getAllDepartmentsByRole(@PathVariable("roleId") Long roleId) {
		return departmentService.getAllDepartmentsByRole(roleId);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_DEPARTMENTS_LIST_BY_COMPANY_AND_ROLE, method = RequestMethod.GET)
	public @ResponseBody List<Department> getAllDepartmentsListByCompanyAndRole(@PathVariable String username,@PathVariable Long roleid) {
		return departmentService.getAllDepartmentsListByCompanyAndRole(username,roleid);
	}
	
	/*
	 * @RequestMapping(value =
	 * RestURIConstants.GET_ALL_DEPARTMENTS_LIST_BY_REGISTARATION_TYPE, method =
	 * RequestMethod.GET) public @ResponseBody List<Department>
	 * getAllDepartmentsListByRegistartionType(@PathVariable Long id) { return
	 * departmentService.getAllDepartmentsListByRegistartionType(id); }
	 */
	
	//718 getDepartmentsByRegistartionTypeAndProductOwnerAndCompany
	@RequestMapping(value = RestURIConstants.GET_ALL_DEPARTMENTS_LIST_BY_REGISTARATION_TYPE, method = RequestMethod.GET)
	public @ResponseBody List<Department> getDepartmentsByRegistartionTypeAndProductOwnerAndCompany(@PathVariable Long regTypeId,@PathVariable String userName) {
		return departmentService.getDepartmentsByRegistartionTypeAndProductOwnerAndCompany(regTypeId,userName);
	}
	
	
}
