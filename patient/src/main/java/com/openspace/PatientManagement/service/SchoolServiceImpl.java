package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.DoctorManagement.School;
import com.openspace.Model.Lookups.Address1;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.SchoolRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class SchoolServiceImpl implements SchoolService{
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public void addSchool(School school) {
		// TODO Auto-generated method stub
		/*UserAccount dbUserAccount = userAccountRepository.findByUsername(school.getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		
		school.setCompany(dbUserAccount.getCompany());
		school.setUserAccount(dbUserAccount);		
		
		School dbSchool = schoolRepository.findBySchoolName(school.getSchoolName());
		if(dbSchool!=null){
			throw new RuntimeException(ErrorMessageHandler.schoolNameAlreadyExists);
		}
		schoolRepository.save(school);*/
		
		/*if(1>0){
			throw new RuntimeException("User  Does not Exist");
		}*/
		UserAccount dbUserAccount=userAccountRepository.findByUsername(school.getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException("User  Does not Exist");
		}
		if(dbUserAccount.getRole().getRoleName().equals("Product Owner")){
			school.setProductOwnerStatus(true);
		}
		if(dbUserAccount.getCompany() != null){
			school.setCompany(dbUserAccount.getCompany());
		}
		school.setUserAccount(dbUserAccount);
		if(school.getAddress() != null){
			Address1 address1=school.getAddress();
			school.setAddress(address1);
		}
		
		School dbSchool = null;
		
		if(!dbUserAccount.getRole().getRoleName().equals("Individual")){
			dbSchool=schoolRepository.findBySchoolNameAndCompany_Id(school.getSchoolName(),dbUserAccount.getCompany().getId());
			}
		else if(dbUserAccount.getRole().getRoleName().equals("Product Owner")){
			
			dbSchool=schoolRepository.findBySchoolNameAndUserAccount_Id(school.getSchoolName(),dbUserAccount.getId());
		}else{
			dbSchool=schoolRepository.findBySchoolNameAndUserAccount_UniqueId(school.getSchoolName(),dbUserAccount.getUniqueId());
			}
		
		if (dbSchool != null) {
			throw new RuntimeException("School Name Already Exist!");
		}
		/*school.setStatus(true);
		school.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		school.setModifiedDate(new Timestamp(System.currentTimeMillis()));*/
		if(school.getEmail()!=null){
			try{
				School dbSchool2=schoolRepository.findByEmail(school.getEmail());
				Person dbPerson=personRepository.findByEmail(school.getEmail());
				if (dbPerson != null||dbSchool2 != null) {
					throw new RuntimeException("Email Already Exist!");
				}	
			}catch(Exception exception){
				throw new RuntimeException("Email Already Exist!");
			}
			/*School dbSchool2=schoolRepository.findByEmail(school.getEmail());
			Person dbPerson=personRepository.findByEmail(school.getEmail());
			if (dbPerson != null||dbSchool2 != null) {
				throw new RuntimeException("Email Already Exist!");
			}*/
		}
		if(school.getMobileNumber()!=null){
			try{
				School dbSchool3=schoolRepository.findByMobileNumber(school.getMobileNumber());
				Person dbPerson1=personRepository.findByMobileNumber(school.getMobileNumber());
				if (dbPerson1 != null||dbSchool3 != null) {
					throw new RuntimeException("Moblie Already Exist!");
				}
			}catch(Exception exception){
				throw new RuntimeException("Moblie Already Exist!");
			}
			/*School dbSchool3=schoolRepository.findByMobileNumber(school.getMobileNumber());
			Person dbPerson1=personRepository.findByMobileNumber(school.getMobileNumber());
			if (dbPerson1 != null||dbSchool3 != null) {
				throw new RuntimeException("Moblie Already Exist!");
			}*/
		}
		schoolRepository.save(school);
	}

	@Override
	public List<School> getAllSchools() {
		// TODO Auto-generated method stub
		 return (List<School>) schoolRepository.findAll();
	}

	@Override
	public void updateSchool(School school) {
		UserAccount dbUserAccount=userAccountRepository.findByUsername(school.getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		School dbSchool = schoolRepository.findOne(school.getId());
		if(dbSchool == null) {
			throw new RuntimeException("School doesn't Exist!");
		}
		School dbSchool2 =null;
		if(dbUserAccount.getCompany()!=null){
		dbSchool2 = schoolRepository.findBySchoolNameAndCompany_Id(school.getSchoolName(),dbUserAccount.getCompany().getId());
		}else{
			dbSchool2 = schoolRepository.findBySchoolNameAndUserAccount_Id(school.getSchoolName(),dbUserAccount.getId());
		}
		if(dbSchool2!=null&&!dbSchool2.getId().equals(school.getId())){
			throw new RuntimeException("School Name Already Exist!");
		}
		
		
		if(school.getEmail()!=null){
			if(dbSchool.getEmail()!=null){
			if(!dbSchool.getEmail().equals(school.getEmail())){
				System.out.println(dbSchool.getEmail());
				System.out.println(school.getEmail());
			try{
				School dbSchool3=schoolRepository.findByEmail(school.getEmail());
				System.out.println(dbSchool3);
				Person dbPerson=personRepository.findByEmail(school.getEmail());
				if (dbSchool3 != null&&!dbSchool3.getId().equals(school.getId())) {
					throw new RuntimeException("Email  Already Exist!");
				}
				if (dbPerson != null) {
					throw new RuntimeException("Email Already Exist!");
				}
			}catch(Exception exception){
				throw new RuntimeException("Email Already Exist!");
			}
			}
			}
		/*School dbSchool3=schoolRepository.findByEmail(school.getEmail());
		Person dbPerson=personRepository.findByEmail(school.getEmail());
		if (dbSchool3 != null&&!dbSchool3.getId().equals(school.getId())) {
			throw new RuntimeException("Email  Already Exist!");
		}
		if (dbPerson != null) {
			throw new RuntimeException("Email Already Exist!");
		}*/
		}
		if(school.getMobileNumber()!=null){
			if(dbSchool.getMobileNumber()!=null){
			if(!dbSchool.getMobileNumber().equals(school.getMobileNumber())){
			try{
				School dbSchool4=schoolRepository.findByMobileNumber(school.getMobileNumber());
				if (dbSchool4 != null&&!dbSchool4.getId().equals(school.getId())) {
					throw new RuntimeException("Moblie Already Exist!");
				}
				Person dbPerson1=personRepository.findByMobileNumber(school.getMobileNumber());
				if (dbPerson1!= null) {
					throw new RuntimeException("Moblie Already Exist!");
				}
			}catch(Exception exception){
				throw new RuntimeException("Moblie Already Exist!");
			}
			}
		}
			/*School dbSchool4=schoolRepository.findByMobileNumber(school.getMobileNumber());
			if (dbSchool4 != null&&!dbSchool4.getId().equals(school.getId())) {
				throw new RuntimeException("Moblie Already Exist!");
			}
			Person dbPerson1=personRepository.findByMobileNumber(school.getMobileNumber());
			if (dbPerson1!= null) {
				throw new RuntimeException("Moblie Already Exist!");
			}*/
		}
		
		//dbSchool = school;
		dbSchool.setSchoolName(school.getSchoolName());
		dbSchool.setIdentityId(school.getIdentityId());
		dbSchool.setEmail(school.getEmail());
		dbSchool.setMobileNumber(school.getMobileNumber());
		dbSchool.setAddress(school.getAddress());
		dbSchool.setFax(school.getFax());
		dbSchool.setStatus(school.isStatus());
		schoolRepository.save(dbSchool);
		if(dbSchool.isProductOwnerStatus()==true){
			List<School> schools=schoolRepository.findBySchoolId(dbSchool.getId());
			for(School school2:schools){
				school2.setSchoolName(school.getSchoolName());
				school2.setIdentityId(school.getIdentityId());
				school2.setEmail(school.getEmail());
				school2.setMobileNumber(school.getMobileNumber());
				school2.setAddress(school.getAddress());
				school2.setFax(school.getFax());
				school2.setStatus(school.isStatus());
				schoolRepository.save(school2);
			}
		}
	}

	
	@Override
	public void deleteSchool(Long id) {
		System.out.println(id);
		School dbSchool = schoolRepository.findOne(id);
		if(dbSchool == null) {
			throw new RuntimeException(ErrorMessageHandler.schoolDoesNotExists);
		}
		List<Patient> patients=patientRepository.findBySchool_Id(id);
		if(patients.size()>0){
			throw new RuntimeException("School Assigned To Patient! Can't Delete");
		}
		try {
			schoolRepository.delete(dbSchool);
		} catch (Exception exception) {
			throw new RuntimeException("User Can't Delete! It's Already In Use");
		}
		
	}

	/*@Override
	public Page<School> getAllSchoolsByAdminPage(String username, int page, int size) {
		// TODO Auto-generated method stub
		UserAccount dbUserAccount = userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Page<School> dbSchools=schoolRepository.findByCompany_Id(dbUserAccount.getCompany().getId(),
				new PageRequest(page, size, Sort.Direction.DESC, "id"));
		return dbSchools;		
	}	*/
	
	
	public List<School> getAllSchoolsByProductOwner(){
		   List<UserAccount> dbUserAccounts=userAccountRepository.findByRole_RoleName("Product Owner");
		   UserAccount account=null;
		   for(UserAccount userAccount:dbUserAccounts){
			   account=userAccount;
		   }
		  List<School> schools=schoolRepository.findByUserAccount_IdAndProductOwnerStatus(account.getId(),true);
		  return schools;
	   }
	
	
	@Override
	public List<School> getAllSchoolsByAdmin(String username) {
		UserAccount dbUserAccount=userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException("User  Does not Exist");
		}
		List<School> schools=null;
		schools=schoolRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
		if(!dbUserAccount.getRole().getRoleName().equals("Product Owner")){
			List<School> pownerSchools=getAllSchoolsByProductOwner();
			for(School pownerSchool:pownerSchools){
				School companySchool=schoolRepository.findBySchoolNameAndCompany_Id(pownerSchool.getSchoolName(), dbUserAccount.getCompany().getId());
				if(companySchool==null){
					School newSchool=new School();
					newSchool.setSchoolName(pownerSchool.getSchoolName());
					newSchool.setIdentityId(pownerSchool.getIdentityId());
					newSchool.setAdminUserName(username);
					newSchool.setCompany(dbUserAccount.getCompany());
					newSchool.setUserAccount(dbUserAccount);
					newSchool.setFax(pownerSchool.getFax());
					newSchool.setEmail(pownerSchool.getEmail());
					newSchool.setMobileNumber(pownerSchool.getMobileNumber());
					newSchool.setProductOwnerhasThis(true);
					newSchool.setAddress(pownerSchool.getAddress());
					newSchool.setStatus(pownerSchool.isStatus());
					newSchool.setSchoolId(pownerSchool.getId());
					schoolRepository.save(newSchool);
					schools=schoolRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
				}
				
			}
		}else{
			schools=schoolRepository.findByUserAccount_IdAndProductOwnerStatus(dbUserAccount.getId(),true);
		}
		return schools;
	}
	@Override
	public Page<School> getAllSchoolsByAdminPage(String username,int page, int size) {
		/*UserAccount dbUserAccount=userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException("User  Does not Exist");
		}
		Page<Department> dbDepartments=departmentRepository.findByCompany_Id(dbUserAccount.getCompany().getId(),
				new PageRequest(page, size, Sort.Direction.DESC, "id"));
		//return (Page<Department>) departmentRepository.findAll(new PageRequest(page, size, Sort.Direction.DESC, "id"));
		return dbDepartments;*/
		
		
		List<School> dbSchools = getAllSchoolsByAdmin(username);
		int start = new PageRequest(page, size).getOffset();
		int end = (start + new PageRequest(page, size).getPageSize()) > dbSchools.size() ? dbSchools.size()
				: (start + new PageRequest(page, size).getPageSize());

		return new PageImpl<School>(dbSchools.subList(start, end),
				new PageRequest(page, size, Sort.Direction.DESC, "id"), dbSchools.size());
	}
	
	@Override
	public List<School> getAllSchoolsByCompany(String username) {
		UserAccount dbUserAccount=userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException("User  Does not Exist");
		}
		List<School> schools=null;
		if(dbUserAccount.getCompany()!=null){
		schools=schoolRepository.findByCompany_IdAndStatus(dbUserAccount.getCompany().getId(),true);
		}else{
			schools=schoolRepository.findByUserAccount_IdAndStatus(dbUserAccount.getId(),true);
		}
		return schools;
	}
}
