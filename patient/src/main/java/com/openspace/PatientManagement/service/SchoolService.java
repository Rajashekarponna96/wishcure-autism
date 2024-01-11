package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.DoctorManagement.School;

public interface SchoolService {

	void addSchool(School school);

	List<School> getAllSchools();

	void updateSchool(School school);

	Page<School> getAllSchoolsByAdminPage(String username, int page, int size);

	List<School> getAllSchoolsByAdmin(String username);

	void deleteSchool(Long id);

	List<School> getAllSchoolsByCompany(String username);

}
