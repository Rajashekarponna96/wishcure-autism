package com.openspace.HospitalMgnt.common.Person;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Person;

@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
    @RequestMapping(value = RestURIConstants.SAVE_PERSON, method = RequestMethod.POST)
	public @ResponseBody void addPerson(@RequestBody Person person) {
		personService.addPerson(person);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_PERSONS, method = RequestMethod.GET)
	public @ResponseBody List<Person> getAllPersons(@PathVariable String adminUsername) {
		return personService.getAllPersons(adminUsername);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_PERSONS_PAGE, method = RequestMethod.GET)
	public @ResponseBody Page<Person> getAllPersons(@PathVariable String adminUsername,@RequestParam("page")int page,@RequestParam("size")int size) {
		return personService.getAllPersons(adminUsername,page,size);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_PERSONS_PAGE_BY_LOGIN_USER, method = RequestMethod.GET)
	public @ResponseBody Page<Person> getAllPersonsByLoginUser(@PathVariable String adminUsername,@RequestParam("page")int page,@RequestParam("size")int size) {
		return personService.getAllPersonsByLoginUser(adminUsername,page,size);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_PERSONS_PAGE_SEARCH, method = RequestMethod.GET)
	public @ResponseBody Page<Person> getAllPersonsSearch(@PathVariable String adminUsername,@PathVariable String search,@RequestParam("page")int page,@RequestParam("size")int size) {
		return personService.getAllPersonsSearch(adminUsername,search,page,size);
	}
	
	@RequestMapping(value = RestURIConstants.UPDATE_PERSON, method = RequestMethod.PUT)
	public @ResponseBody void updatePerson(@PathVariable String adminUserName,@RequestBody Doctor doctor)throws FileNotFoundException,IOException {
		personService.updatePerson(doctor,adminUserName);
	}

	@RequestMapping(value = RestURIConstants.DELETE_PERSON, method = RequestMethod.DELETE)
	public @ResponseBody void deletePerson(@PathVariable Long id,@PathVariable String adminUserName) {
		personService.deletePerson(id,adminUserName);
	}
	@RequestMapping(value = RestURIConstants.SAVE_USER, method = RequestMethod.POST)
	public @ResponseBody void addPerson_user(@RequestBody PersonDTO person) throws FileNotFoundException,IOException{
		personService.addPerson_user(person);
	}
	@RequestMapping(value = RestURIConstants.GET_ALL_REGISTRATIONS, method = RequestMethod.GET)
	public @ResponseBody int getAllRegistrations(@PathVariable String adminUsername) {
		return personService.getAllRegistrations(adminUsername);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_ACTIVE_ADMINS, method = RequestMethod.GET)
	public @ResponseBody int getAllActiveAdmins(@PathVariable String adminUsername) {
		return personService.getAllActiveAdmins(adminUsername);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_INACTIVE_ADMINS, method = RequestMethod.GET)
	public @ResponseBody int getAllInActiveAdmins(@PathVariable String adminUsername) {
		return personService.getAllInActiveAdmins(adminUsername);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_ACTIVE_SITEADMIN, method = RequestMethod.GET)
	public @ResponseBody int getAllActiveSiteAdmins(@PathVariable String adminUsername) {
		return personService.getAllActiveSiteAdmins(adminUsername);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_INACTIVE_SITEADMIN, method = RequestMethod.GET)
	public @ResponseBody int getAllInActiveSiteAdmins(@PathVariable String adminUsername) {
		return personService.getAllInActiveSiteAdmins(adminUsername);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_PERSONS_PAGE_BY_SUPERADMIN_ADMIN, method = RequestMethod.GET)
	public @ResponseBody Page<Person> getAllPersonsBySuperAdminAndAdmin(@PathVariable String adminUsername,@PathVariable String roleName,@PathVariable Boolean active,@RequestParam("page")int page,@RequestParam("size")int size) {
		return personService.getAllPersonsBySuperAdminAndAdmin(adminUsername,roleName,active,page,size);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_PERSONS_PAGE_BY_THERAPIST, method = RequestMethod.GET)
	public @ResponseBody Page<Person> getAllPersonsByTherapists(@PathVariable String adminUsername,@PathVariable String roleName,@PathVariable Boolean active,@RequestParam("page")int page,@RequestParam("size")int size) {
		return personService.getAllPersonsByTherapists(adminUsername,roleName,active,page,size);
	}
	@RequestMapping(value = RestURIConstants.GET_ALL_ACTIVE_USERS_BY_LOGIN_USERS, method = RequestMethod.GET)
	public @ResponseBody Page<Person> getAllPersonsBySuperAdminAndAdmin(@PathVariable String adminUsername,@PathVariable Boolean active,@RequestParam("page")int page,@RequestParam("size")int size) {
		return personService.getAllActiveUsersByLoginUser(adminUsername,active,page,size);
	}
	
	@RequestMapping(value=RestURIConstants.GETONEPERSON,method=RequestMethod.GET)
	public @ResponseBody Person getPersonById(@PathVariable("id") Long id){
		return personService.getOnePerson(id);
		
	}
	
	@RequestMapping(value = RestURIConstants.ADD_USER_THERAPIST, method = RequestMethod.POST)
	public @ResponseBody void addUserTherapist(@RequestBody PersonDTO person) throws FileNotFoundException,IOException{
		personService.addUserTherapist(person);
	}
	
	@RequestMapping(value =RestURIConstants.GET_ALL_USERS_BY_SUPERADMIN_AND_ADMIN, method = RequestMethod.GET)
	public @ResponseBody List<Person> getAllusersBySuperAdminAndAdmin(@PathVariable String adminUserName,@PathVariable String roleName,@PathVariable Boolean active) {
		return personService.getAllusersBySuperAdminAndAdmin(adminUserName,roleName,active);
	}
	
	@RequestMapping(value = RestURIConstants.ACTIVE, method = RequestMethod.POST)
	public @ResponseBody void activeUser(@RequestBody Person person){
		personService.activeUser(person);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_ACTIVE_PERSONS_PAGE_BY_REG_TYPE, method = RequestMethod.GET)
	public @ResponseBody Page<Person> getAllActivePersonsByRegType(@PathVariable String adminUsername,@RequestParam("page")int page,@RequestParam("size")int size) {
		return personService.getAllActivePersonsByRegType(adminUsername,page,size);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_INACTIVE_PERSONS_PAGE_BY_REG_TYPE, method = RequestMethod.GET)
	public @ResponseBody Page<Person> getAllInActivePersonsByRegType(@PathVariable String adminUsername,@RequestParam("page")int page,@RequestParam("size")int size) {
		return personService.getAllInActivePersonsByRegType(adminUsername,page,size);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_INACTIVE_PERSONS_COUNT_BY_COMPANY, method = RequestMethod.GET)
	public @ResponseBody int getInactivePersonsCount(@PathVariable String adminUsername) {
		return personService.getInactivePersonsCount(adminUsername);
	}
	@RequestMapping(value = RestURIConstants.GET_ALL_ACTIVE_PERSONS_COUNT_BY_COMPANY, method = RequestMethod.GET)
	public @ResponseBody int getActivePersonsCount(@PathVariable String adminUsername) {
		return personService.getActivePersonsCount(adminUsername);
	}
	@RequestMapping(value = RestURIConstants.GET_ALL_ACTIVE_PERSONS_COUNT_BY_COMPANY_AND_SITE, method = RequestMethod.GET)
	public @ResponseBody int getActivePersonsCountByCompanyAndSite(@PathVariable String adminUsername) {
		return personService.getActivePersonsCountByCompanyAndSite(adminUsername);
	}
}
