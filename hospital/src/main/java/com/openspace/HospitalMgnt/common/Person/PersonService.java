package com.openspace.HospitalMgnt.common.Person;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Person;

public interface PersonService {

	List<Person> getAllPersons(String adminUsername);
	
	int getAllActiveAdmins(String adminUsername);
	
	int getAllInActiveAdmins(String adminUsername);
	
	int getAllInActiveSiteAdmins(String adminUsername);
	
	int getAllActiveSiteAdmins(String adminUsername);
	
	int  getAllRegistrations(String adminUsername);
	
	Page<Person> getAllPersons(String adminUsername,int page,int size);

	void addPerson(Person person);

	void updatePerson(Doctor doctor,String adminUsername) throws FileNotFoundException,IOException;

	void deletePerson(Long id,String adminUsername);

	void addPerson_user(PersonDTO person) throws FileNotFoundException,IOException;

	Page<Person> getAllPersonsBySuperAdminAndAdmin(String adminUsername, String roleName, Boolean active, int page,
			int size);

	Page<Person> getAllPersonsByTherapists(String adminUsername, String roleName, Boolean active, int page, int size);

	Page<Person> getAllPersonsSearch(String adminUsername, String search, int page, int size);

	Person getOnePerson(Long id);

	void addUserTherapist(PersonDTO personDto) throws FileNotFoundException, IOException;

   List<Person> getAllusersBySuperAdminAndAdmin(String adminUsername, String roleName, Boolean active);
   
   void activeUser(Person person);

Page<Person> getAllActivePersonsByRegType(String adminUsername, int page, int size);

Page<Person> getAllInActivePersonsByRegType(String adminUsername, int page, int size);

int getInactivePersonsCount(String adminUsername);

int getActivePersonsCount(String adminUsername);

Page<Person> getAllPersonsByLoginUser(String adminUsername, int page, int size);

Page<Person> getAllActiveUsersByLoginUser(String adminUsername, Boolean active, int page, int size);

int getActivePersonsCountByCompanyAndSite(String adminUsername);
}
