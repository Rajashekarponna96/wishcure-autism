package com.openspace.HospitalMgnt.Therapist;

import java.util.List;

import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.Lookups.Department;

public interface TherapistService {
	void saveTherapist(TherapistDto therapistDto);

	List<Doctor> getAllTherapists(String adminUsername);

	void updateTherapist(TherapistDto therapistDto);

	void deleteTherapist(Long id);
	
	TherapistDto getTherapistsByUsername(String adminUsername);
	int getAllActiveTherapists(String adminUsername);
	
	int getAllInActiveTherapists(String adminUsername);
	
	List<Doctor> getAllTherapistsByDepartment(Long id);
	
	List<Doctor> getAlltherapistsByAdminUsername(String email);

}
