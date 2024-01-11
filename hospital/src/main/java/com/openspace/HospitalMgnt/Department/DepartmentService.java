package com.openspace.HospitalMgnt.Department;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.DoctorManagement.PatientRegistrationType;
import com.openspace.Model.Lookups.Department;

public interface DepartmentService {

	void saveDepartment(Department department);

	//Page<Department> getAllDepartments(int page, int size);

	void updateDepartment(Department department);

	void deleteDepartment(Long id);

	//Page<Department> findAll(int page, int size);

	List<Department> getAllDepartments();

	Page<Department> getAllDepartmentsSearch(String username, String search2, int page, int size);

	Page<Department> getAllDepartments(String username, int page, int size);

	List<Department> getAllDepartmentsList(String username);

	List<Department> getAllDepartmentsByRole(Long roleId);

	List<Department> getAllDepartmentsListByCompanyAndRole(String username, Long roleid);
	
	List<Department> getAllDepartmentsListByRegistartionType(Long id);

	List<Department> getDepartmentsByRegistartionTypeAndProductOwnerAndCompany(Long id, String userName);


}
