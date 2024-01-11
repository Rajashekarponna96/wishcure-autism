package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Insurance;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.DoctorManagement.RegionalCenter;
import com.openspace.Model.Lookups.Address1;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.Lookups.RegionalCenterLookup;
import com.openspace.Model.PatientMgnt.Repositories.CompanyRepository;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.RegionalCenterLookupRepository;
import com.openspace.Model.PatientMgnt.Repositories.RegionalCenterRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class RegionalCenterServiceImpl implements RegionalCenterService {

	@Autowired
	private RegionalCenterRepository regionalCenterRepository;

	@Autowired
	private RegionalCenterLookupRepository regionalCenterLookupRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public void addRegionalCenter(RegionalCenter regionalCenter) {

		UserAccount dbUserAccount = userAccountRepository.findByUsername(regionalCenter.getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Company dbCompany = companyRepository.findOne(dbUserAccount.getCompany().getId());
		if (dbCompany == null) {
			throw new RuntimeException(ErrorMessageHandler.companyDoesNotExists);
		}
		List<RegionalCenter> dbRegionalCenter = regionalCenterRepository.findByName(regionalCenter.getName());
		if (dbRegionalCenter != null && dbRegionalCenter.size() > 0) {
			throw new RuntimeException(ErrorMessageHandler.regionalCenterExists);
		}
		
		if(regionalCenter.getEmail()!=null){
			try{
				RegionalCenter dbRegionalCenter2=regionalCenterRepository.findByEmail(regionalCenter.getEmail());
				Person dbPerson=personRepository.findByEmail(regionalCenter.getEmail());
				if (dbPerson != null||dbRegionalCenter2 != null) {
					throw new RuntimeException("Email Already Exist!");
				}	
			}catch(Exception exception){
				throw new RuntimeException("Email Already Exist!");
			}
		}
		if(regionalCenter.getMobileNumber()!=null){
			try{
				RegionalCenter dbRegionalCenter3=regionalCenterRepository.findByMobileNumber(regionalCenter.getMobileNumber());
				Person dbPerson1=personRepository.findByMobileNumber(regionalCenter.getMobileNumber());
				if (dbPerson1 != null||dbRegionalCenter3 != null) {
					throw new RuntimeException("Moblie Already Exist!");
				}
			}catch(Exception exception){
				throw new RuntimeException("Moblie Already Exist!");
			}
		}
		RegionalCenter regionalCenters = new RegionalCenter();
		Address1 address = new Address1();
		address.setAddress1(regionalCenter.getReginoalCenterAddress().getAddress1());
		address.setAddress2(regionalCenter.getReginoalCenterAddress().getAddress2());
		address.setCity(regionalCenter.getReginoalCenterAddress().getCity());
		address.setState(regionalCenter.getReginoalCenterAddress().getState());
		address.setCountry(regionalCenter.getReginoalCenterAddress().getCountry());
		address.setZipcode(regionalCenter.getReginoalCenterAddress().getZipcode());

		regionalCenters.setName(regionalCenter.getName());
		regionalCenters.setEmail(regionalCenter.getEmail());
		regionalCenters.setMobileNumber(regionalCenter.getMobileNumber());
		regionalCenters.setReginoalCenterAddress(address);
		regionalCenters.setCompany(dbCompany);
		regionalCenters.setUserAccount(dbUserAccount);
		regionalCenters.setAdminUserName(regionalCenter.getAdminUserName());
		regionalCenters.setFaxNo(regionalCenter.getFaxNo());
		regionalCenters.setStatus(regionalCenter.getStatus());
		regionalCenters.setFromRcebLookup(false);
		regionalCenters.setRegionalCenterZoneLookup(regionalCenter.getRegionalCenterZoneLookup());
		regionalCenterRepository.save(regionalCenters);
	}

	@Override
	public Page<RegionalCenter> getAllRegionalCenters(String username, int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Company dbCompany=null;
		if(dbUserAccount.getCompany() != null){
			 dbCompany = companyRepository.findOne(dbUserAccount.getCompany().getId());
			if (dbCompany == null) {
				throw new RuntimeException(ErrorMessageHandler.companyDoesNotExists);
			}
		}
	
		List<RegionalCenterLookup> dbRegionalCenterLookup = (List<RegionalCenterLookup>) regionalCenterLookupRepository
				.findByStatus(true);
		for (RegionalCenterLookup regionalCenterLookup : dbRegionalCenterLookup) {
			List<RegionalCenter> dbRegionalCenter = regionalCenterRepository
					.findByNameAndUserAccount_Id(regionalCenterLookup.getName(), dbUserAccount.getId());

			if (dbRegionalCenter.size() < 1) {
				RegionalCenter regionalCenters = new RegionalCenter();
				Address1 address = new Address1();
				address.setAddress1(regionalCenterLookup.getReginoalCenterAddress().getAddress1());
				address.setAddress2(regionalCenterLookup.getReginoalCenterAddress().getAddress2());
				address.setCity(regionalCenterLookup.getReginoalCenterAddress().getCity());
				address.setState(regionalCenterLookup.getReginoalCenterAddress().getState());
				address.setCountry(regionalCenterLookup.getReginoalCenterAddress().getCountry());
				address.setZipcode(regionalCenterLookup.getReginoalCenterAddress().getZipcode());

				regionalCenters.setName(regionalCenterLookup.getName());
				regionalCenters.setEmail(regionalCenterLookup.getEmail());
				regionalCenters.setMobileNumber(regionalCenterLookup.getMobileNumber());
				regionalCenters.setReginoalCenterAddress(address);
				regionalCenters.setCompany(dbCompany);
				regionalCenters.setUserAccount(dbUserAccount);
				regionalCenters.setAdminUserName(dbUserAccount.getUsername());
				regionalCenters.setFaxNo(regionalCenterLookup.getFaxNo());
				regionalCenters.setStatus(regionalCenterLookup.getStatus());
				regionalCenters.setRegionalCenterZoneLookup(regionalCenterLookup.getRegionalCenterZoneLookup());
				regionalCenters.setFromRcebLookup(true);
				regionalCenterRepository.save(regionalCenters);
			}
		}
		Page<RegionalCenter> dbRegionalCenters = regionalCenterRepository.findByUserAccount_Id(
				dbUserAccount.getId(), new PageRequest(page, size, Sort.Direction.DESC, "id"));
		return dbRegionalCenters;
	}

	@Override
	public void updateRegionalCenter(RegionalCenter regionalCenter) {
		// TODO Auto-generated method stub
		UserAccount dbUserAccount = userAccountRepository.findByUsername(regionalCenter.getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Company dbCompany = companyRepository.findOne(regionalCenter.getCompany().getId());
		if (dbCompany == null) {
			throw new RuntimeException(ErrorMessageHandler.companyDoesNotExists);
		}
		RegionalCenter dbRegionalCenter = regionalCenterRepository.findOne(regionalCenter.getId());
		if (dbRegionalCenter == null) {
			throw new RuntimeException(ErrorMessageHandler.regionalCenterDoesNotExists);
		}
		List<RegionalCenter> dbRegionalCenter2 = regionalCenterRepository.findByName(regionalCenter.getName());

		if(regionalCenter.getEmail()!=null&& dbRegionalCenter.getEmail() != null){
			if(!dbRegionalCenter.getEmail().equals(regionalCenter.getEmail())&&dbRegionalCenter.getEmail()!=null){
			try{
				
				RegionalCenter dbRegionalCenter3=regionalCenterRepository.findByEmail(regionalCenter.getEmail());
				Person dbPerson=personRepository.findByEmail(regionalCenter.getEmail());
				if (dbRegionalCenter3 != null&&!dbRegionalCenter3.getId().equals(regionalCenter.getId())) {
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
		
		if(regionalCenter.getMobileNumber()!=null && dbRegionalCenter.getMobileNumber() != null){
			System.out.println(regionalCenter.getMobileNumber());
			System.out.println(dbRegionalCenter.getMobileNumber());
			if(!dbRegionalCenter.getMobileNumber().equals(regionalCenter.getMobileNumber())){
			try{
				RegionalCenter dbRegionalCenter4=regionalCenterRepository.findByMobileNumber(regionalCenter.getMobileNumber());
				if (dbRegionalCenter4 != null&&!dbRegionalCenter4.getId().equals(regionalCenter.getId())) {
					throw new RuntimeException("Moblie Already Exist!");
				}
				Person dbPerson1=personRepository.findByMobileNumber(regionalCenter.getMobileNumber());
				if (dbPerson1!= null) {
					throw new RuntimeException("Moblie Already Exist!");
				}
			}catch(Exception exception){
				throw new RuntimeException("Moblie Already Exist!");
			}
			}
		}
		for (RegionalCenter RegionalCenterlist : dbRegionalCenter2) {
			if (RegionalCenterlist != null && !RegionalCenterlist.getId().equals(regionalCenter.getId())) {
				throw new RuntimeException(ErrorMessageHandler.regionalCenterExists);
			}
		}
		Address1 dbaddress = dbRegionalCenter.getReginoalCenterAddress();
		dbaddress.setAddress1(regionalCenter.getReginoalCenterAddress().getAddress1());
		dbaddress.setAddress2(regionalCenter.getReginoalCenterAddress().getAddress2());
		dbaddress.setCity(regionalCenter.getReginoalCenterAddress().getCity());
		dbaddress.setState(regionalCenter.getReginoalCenterAddress().getState());
		dbaddress.setCountry(regionalCenter.getReginoalCenterAddress().getCountry());
		dbaddress.setZipcode(regionalCenter.getReginoalCenterAddress().getZipcode());

		dbRegionalCenter.setName(regionalCenter.getName());
		dbRegionalCenter.setEmail(regionalCenter.getEmail());
		dbRegionalCenter.setMobileNumber(regionalCenter.getMobileNumber());
		dbRegionalCenter.setReginoalCenterAddress(dbaddress);
		dbRegionalCenter.setCompany(dbCompany);
		dbRegionalCenter.setUserAccount(dbUserAccount);
		dbRegionalCenter.setAdminUserName(regionalCenter.getAdminUserName());
		dbRegionalCenter.setFaxNo(regionalCenter.getFaxNo());
		dbRegionalCenter.setStatus(regionalCenter.getStatus());
		dbRegionalCenter.setRegionalCenterZoneLookup(regionalCenter.getRegionalCenterZoneLookup());
		regionalCenterRepository.save(dbRegionalCenter);
	}

	@Override
	public void deleteRegionalCenter(Long id) {
		// TODO Auto-generated method stub
		RegionalCenter dbRegionalCenter = regionalCenterRepository.findOne(id);
		if (dbRegionalCenter == null) {
			throw new RuntimeException(ErrorMessageHandler.regionalCenterDoesNotExists);
		}
		regionalCenterRepository.delete(dbRegionalCenter);
	}

	@Override
	public Page<RegionalCenter> getAllRegionalCentersByAdminPage(String username, int page, int size) {
		// TODO Auto-generated method stub
		UserAccount dbUserAccount = userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		/*
		 * Page<RegionalCenter >
		 * dbRegionalCenters=regionalCenterRepository.findByCompany_Id(
		 * dbUserAccount.getCompany().getId(), new PageRequest(page, size,
		 * Sort.Direction.DESC, "id"));
		 */
		// return dbRegionalCenters;
		return null;
	}

}
