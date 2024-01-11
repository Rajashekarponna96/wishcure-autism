package com.openspace.PatientManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.DoctorManagement.School;
import com.openspace.PatientManagement.service.SchoolService;

@RestController
@RequestMapping(value="/school")
public class SchoolController {
	
	@Autowired
	private SchoolService schoolService;
	
	@RequestMapping(value="/addSchool",method=RequestMethod.POST)   
	public void addSchool(@RequestBody School school){   //save
		schoolService.addSchool(school);
	}
	
	@RequestMapping(value="/get", method=RequestMethod.GET) 
	public List<School> getAllSchools(){								//fetch all
		return schoolService.getAllSchools();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void updateSchool(@RequestBody School school){   //update
		schoolService.updateSchool(school);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public void deleteSchool(@PathVariable Long id) {											//delete
		schoolService.deleteSchool(id);
	}
	
	@RequestMapping(value="/getall/{username:.+}", method=RequestMethod.GET) 	//getall schools by adminpage
	public Page<School> getAllSchoolsByAdminPage(@PathVariable String username, @RequestParam("page") int page, @RequestParam("size")int size) {
		return schoolService.getAllSchoolsByAdminPage(username, page, size);
	}
	
	@RequestMapping(value="/getallwithoutPagenation/{username:.+}", method=RequestMethod.GET)
	public List<School> getAllSchoolsByAdmin(@PathVariable String username) {
		return schoolService.getAllSchoolsByAdmin(username);
	}
	
	@RequestMapping(value="/getAllSchoolsByCompany/{username:.+}", method=RequestMethod.GET)
	public List<School> getAllSchoolsByCompany(@PathVariable String username) {
		return schoolService.getAllSchoolsByCompany(username);
	}

}
