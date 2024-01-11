package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Insurance;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.DoctorManagement.School;
import com.openspace.Model.Lookups.Address1;
import com.openspace.Model.Lookups.InsuranceLookup;
import com.openspace.Model.PatientMgnt.Repositories.InsuranceLookupRepository;
import com.openspace.Model.PatientMgnt.Repositories.InsuranceRepository;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	private InsuranceRepository insuranceRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private InsuranceLookupRepository insuranceLookupRepository;

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public void addInsurance(Insurance insurance) {
		// TODO Auto-generated method stub
		UserAccount dbUserAccount = userAccountRepository.findByUsername(insurance.getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		insurance.setCompany(dbUserAccount.getCompany());
		insurance.setUserAccount(dbUserAccount);
		insurance.setStatus(true);
		insurance.setFromLookup(false);
		Insurance dbInsurance = insuranceRepository.findByInsuranceCompanyName(insurance.getInsuranceCompanyName());
		if (dbInsurance != null) {
			throw new RuntimeException(ErrorMessageHandler.insuranceNameAlreadyExists);
		}
		if(insurance.getEmailId()!=null){
			try{
				Insurance dbInsurance2=insuranceRepository.findByEmailId(insurance.getEmailId());
				Person dbPerson=personRepository.findByEmail(insurance.getEmailId());
				if (dbPerson != null||dbInsurance2 != null) {
					throw new RuntimeException("Email Already Exist!");
				}	
			}catch(Exception exception){
				throw new RuntimeException("Email Already Exist!");
			}
		}
		if(insurance.getMobileNumber()!=null){
			try{
				Insurance dbInsurance3=insuranceRepository.findByMobileNumber(insurance.getMobileNumber());
				Person dbPerson1=personRepository.findByMobileNumber(insurance.getMobileNumber());
				if (dbPerson1 != null||dbInsurance3 != null) {
					throw new RuntimeException("Moblie Already Exist!");
				}
			}catch(Exception exception){
				throw new RuntimeException("Moblie Already Exist!");
			}
		}
		insuranceRepository.save(insurance);
	}

	@Override
	public List<Insurance> getAllInsurances() {
		// TODO Auto-generated method stub
		return (List<Insurance>) insuranceRepository.findAll();
	}

	@Override
	public List<Insurance> getAllInsurancesByAdmin(String username) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<InsuranceLookup> insuranceLookups = (List<InsuranceLookup>) insuranceLookupRepository.findAll();
		if (insuranceLookups != null && insuranceLookups.size() > 0) {
			for (InsuranceLookup insuranceLookup : insuranceLookups) {
				Insurance dbInsurance = insuranceRepository
						.findByInsuranceCompanyName(insuranceLookup.getInsuranceCompanyName());
				if (dbInsurance == null) {
					Insurance insurance2 = new Insurance();
					insurance2.setInsuranceCompanyName(insuranceLookup.getInsuranceCompanyName());
					insurance2.setCompany(dbUserAccount.getCompany());
					insurance2.setUserAccount(dbUserAccount);
					insurance2.setAdminUserName(dbUserAccount.getUsername());
					if (insuranceLookup.getEmailId() != null) {
						insurance2.setEmailId(insuranceLookup.getEmailId());
					}
					if (insuranceLookup.getFax() != null) {
						insurance2.setFax(insuranceLookup.getFax());
					}
					if (insuranceLookup.getIdNumber() != null) {
						insurance2.setIdNumber(insuranceLookup.getIdNumber());
					}
					Address1 address1 = new Address1();
					if (insuranceLookup.getAddress1() != null) {
						address1.setAddress1(insuranceLookup.getAddress1().getAddress1());
						address1.setAddress2(insuranceLookup.getAddress1().getAddress2());
						address1.setCity(insuranceLookup.getAddress1().getCity());
						address1.setState(insuranceLookup.getAddress1().getState());
						address1.setLocation(insuranceLookup.getAddress1().getLocation());
						address1.setCountry(insuranceLookup.getAddress1().getCountry());
						address1.setZipcode(insuranceLookup.getAddress1().getZipcode());
					}
					if (insuranceLookup.getMobileNumber() != null) {
						insurance2.setMobileNumber(insuranceLookup.getMobileNumber());
					}
					if (insuranceLookup.getStatus() != null) {
						insurance2.setStatus(insuranceLookup.getStatus());
					}
					if (insuranceLookup.getFax() != null) {
						insurance2.setFax(insuranceLookup.getFax());
					}
					insurance2.setFromLookup(true);
					insurance2.setAddress1(address1);
					insurance2.setInsuranceLookupId(insuranceLookup.getId());
					insuranceRepository.save(insurance2);
				}
			}
		}
		List<Insurance> dbInsurances = insuranceRepository.findByCompany_IdAndStatus(dbUserAccount.getCompany().getId(),true);
		return dbInsurances;
	}

	@Override
	public void updateInsurance(Insurance insurance) {
		// TODO Auto-generated method stub
		Insurance dbInsurance = insuranceRepository.findOne(insurance.getId());
		if (dbInsurance == null) {
			throw new RuntimeException(ErrorMessageHandler.insuranceNameDoesNotExists);
		}
		Insurance dbInsurance2 = insuranceRepository.findByInsuranceCompanyName(insurance.getInsuranceCompanyName());
		if (dbInsurance2 != null && !dbInsurance2.getId().equals(insurance.getId())) {
			throw new RuntimeException(ErrorMessageHandler.insuranceNameAlreadyExists);
		}
		if(insurance.getEmailId()!=null&& dbInsurance.getEmailId() != null){
			if(!dbInsurance.getEmailId().equals(insurance.getEmailId())&&dbInsurance.getEmailId()!=null){
			try{
				
				Insurance dbSchool3=insuranceRepository.findByEmailId(insurance.getEmailId());
				Person dbPerson=personRepository.findByEmail(insurance.getEmailId());
				if (dbSchool3 != null&&!dbSchool3.getId().equals(insurance.getId())) {
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
		
		if(insurance.getMobileNumber()!=null && dbInsurance.getMobileNumber() != null){
			System.out.println(insurance.getMobileNumber());
			System.out.println(dbInsurance.getMobileNumber());
			if(!dbInsurance.getMobileNumber().equals(insurance.getMobileNumber())){
			try{
				Insurance dbSchool4=insuranceRepository.findByMobileNumber(insurance.getMobileNumber());
				if (dbSchool4 != null&&!dbSchool4.getId().equals(insurance.getId())) {
					throw new RuntimeException("Moblie Already Exist!");
				}
				Person dbPerson1=personRepository.findByMobileNumber(insurance.getMobileNumber());
				if (dbPerson1!= null) {
					throw new RuntimeException("Moblie Already Exist!");
				}
			}catch(Exception exception){
				throw new RuntimeException("Moblie Already Exist!");
			}
			}
		}
		dbInsurance = insurance;
		insuranceRepository.save(dbInsurance);
		
		/*if(dbSchool.isProductOwnerStatus()==true){
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
		}*/
	}

	@Override
	public void deleteInsurance(Long id) {
		// TODO Auto-generated method stub
		Insurance dbInsurance = insuranceRepository.findOne(id);
		if (dbInsurance == null) {
			throw new RuntimeException(ErrorMessageHandler.insuranceNameDoesNotExists);
		}

		try {
			insuranceRepository.delete(dbInsurance);
		} catch (Exception exception) {
			throw new RuntimeException("User Can't Delete! It's Already In Use");
		}
	}

	@Override
	public Page<Insurance> getAllInsurancesByAdminPage(String username, int page, int size) {
		// TODO Auto-generated method stub
		UserAccount dbUserAccount = userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}

		List<InsuranceLookup> insuranceLookups = (List<InsuranceLookup>) insuranceLookupRepository.findAll();
		if (insuranceLookups != null && insuranceLookups.size() > 0) {
			for (InsuranceLookup insuranceLookup : insuranceLookups) {
				Insurance dbInsurance = insuranceRepository
						.findByInsuranceCompanyNameAndUserAccount_Id(insuranceLookup.getInsuranceCompanyName(),dbUserAccount.getId());
				if (dbInsurance == null) {
					Insurance insurance2 = new Insurance();
					insurance2.setInsuranceCompanyName(insuranceLookup.getInsuranceCompanyName());
					insurance2.setCompany(dbUserAccount.getCompany());
					insurance2.setUserAccount(dbUserAccount);
					insurance2.setAdminUserName(dbUserAccount.getUsername());
					if (insuranceLookup.getEmailId() != null) {
						insurance2.setEmailId(insuranceLookup.getEmailId());
					}
					if (insuranceLookup.getFax() != null) {
						insurance2.setFax(insuranceLookup.getFax());
					}
					if (insuranceLookup.getIdNumber() != null) {
						insurance2.setIdNumber(insuranceLookup.getIdNumber());
					}
					Address1 address1 = new Address1();
					if (insuranceLookup.getAddress1() != null) {
						address1.setAddress1(insuranceLookup.getAddress1().getAddress1());
						address1.setAddress2(insuranceLookup.getAddress1().getAddress2());
						address1.setCity(insuranceLookup.getAddress1().getCity());
						address1.setState(insuranceLookup.getAddress1().getState());
						address1.setLocation(insuranceLookup.getAddress1().getLocation());
						address1.setCountry(insuranceLookup.getAddress1().getCountry());
						address1.setZipcode(insuranceLookup.getAddress1().getZipcode());
					}
					if (insuranceLookup.getMobileNumber() != null) {
						insurance2.setMobileNumber(insuranceLookup.getMobileNumber());
					}
					if (insuranceLookup.getFax() != null) {
						insurance2.setFax(insuranceLookup.getFax());
					}
					if (insuranceLookup.getStatus() != null) {
						insurance2.setStatus(insuranceLookup.getStatus());
					}
					insurance2.setAddress1(address1);
					insurance2.setFromLookup(true);
					insuranceRepository.save(insurance2);
				}
			}
		}
		Page<Insurance> dbInsurances = insuranceRepository.findByuserAccount_Id(dbUserAccount.getId(),
				new PageRequest(page, size, Sort.Direction.DESC, "id"));
		return dbInsurances;
	}
	
	@Override
	public List<Insurance> getAllInsurancesByCompany(String username) {
		UserAccount dbUserAccount=userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException("User  Does not Exist");
		}
		List<Insurance> insurances=null;
		if(dbUserAccount.getCompany()!=null){
		insurances=insuranceRepository.findByCompany_IdAndStatus(dbUserAccount.getCompany().getId(),true);
		}else{
			insurances=insuranceRepository.findByUserAccount_IdAndStatus(dbUserAccount.getId(),true);
		}
		return insurances;
	}

}
