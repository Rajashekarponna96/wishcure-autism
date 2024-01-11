package com.openspace.HospitalMgnt.Therapist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.Lookups.Department;
@RestController
public class TherapistController {
	@Autowired
	private TherapistService therapistService;

	@RequestMapping(value = RestURIConstants.SAVE_THERAPIST, method = RequestMethod.POST)
	public @ResponseBody void saveTherapist(@RequestBody TherapistDto therapistDto) {
		therapistService.saveTherapist(therapistDto);;
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_THERAPISTS, method = RequestMethod.GET)
	public @ResponseBody List<Doctor> getAllTherapists(@PathVariable String adminUsername) {
		return therapistService.getAllTherapists(adminUsername);
	}

	@RequestMapping(value = RestURIConstants.UPDATE_THERAPIST, method = RequestMethod.PUT)
	public @ResponseBody void updateTherapist(@RequestBody TherapistDto therapistDto) {
		therapistService.updateTherapist(therapistDto);;
	}

	@RequestMapping(value = RestURIConstants.DELETE_THERAPIST, method = RequestMethod.DELETE)
	public @ResponseBody void deleteTherapist(@PathVariable Long id) {
		therapistService.deleteTherapist(id);
	}
	@RequestMapping(value = RestURIConstants.GET_THERAPIST_BY_USERNAME, method = RequestMethod.GET)
	public @ResponseBody TherapistDto getTherapistsByUsername(@PathVariable String adminUsername) {
		return therapistService.getTherapistsByUsername(adminUsername);
	}
	@RequestMapping(value = RestURIConstants.GET_ALL_ACTIVE_THERAPISTS, method = RequestMethod.GET)
	public @ResponseBody int getAllActiveTherapists(@PathVariable String adminUsername) {
		return therapistService.getAllActiveTherapists(adminUsername);
	}
	@RequestMapping(value = RestURIConstants.GET_ALL_INACTIVE_THERAPISTS, method = RequestMethod.GET)
	public @ResponseBody int getAllInActiveTherapists(@PathVariable String adminUsername) {
		return therapistService.getAllInActiveTherapists(adminUsername);
	}
	@RequestMapping(value = RestURIConstants.GET_ALL_THERAPISTS_BY_DEPARTMENT, method = RequestMethod.GET)
	public @ResponseBody List<Doctor> getAllTherapistsByDepartment(@PathVariable Long id) {
		return therapistService.getAllTherapistsByDepartment(id);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_THERAPISTS_BY_ADMINUSERNAME, method = RequestMethod.GET)
	public @ResponseBody List<Doctor> getAlltherapistsByAdminUsername(@PathVariable String adminUsername) {
		return therapistService.getAlltherapistsByAdminUsername(adminUsername);
	}

}
