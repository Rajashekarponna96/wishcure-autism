package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Tax;
import com.openspace.Model.PatientMgnt.Repositories.TaxRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class TaxServiceImpl implements TaxService {

	@Autowired
	private TaxRepository taxRepository;
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Override
	public void addTax(Tax tax) {
		UserAccount dbUserAccount=userAccountRepository.findByUsername(tax.getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		tax.setCompany(dbUserAccount.getCompany());
		tax.setUserAccount(dbUserAccount);
		
		Tax dbTax=taxRepository.findByName(tax.getName());
		if(dbTax!=null){
			throw new RuntimeException(ErrorMessageHandler.taxAlreadyExists);
		}
		taxRepository.save(tax);
	}

	@Override
	public List<Tax> getAllTaxs() {
		// TODO Auto-generated method stub
		return (List<Tax>) taxRepository.findAll();
	}

	@Override
	public void updateTax(Tax tax) {
		// TODO Auto-generated method stub
		Tax dbTax=taxRepository.findOne(tax.getId());
		if(dbTax==null){
			throw new RuntimeException(ErrorMessageHandler.taxDoesNotExists);
		}
		Tax dbTax2=taxRepository.findByName(tax.getName());
		if(dbTax2==null){
			
		}else if(dbTax2.getId().equals(tax.getId())){
			
		}else{
			throw new RuntimeException(ErrorMessageHandler.taxAlreadyExists);
		}
		dbTax=tax;
		taxRepository.save(dbTax);
	}

	@Override
	public void deleteTax(Long id) {
		// TODO Auto-generated method stub
		Tax dbTax=taxRepository.findOne(id);
		if(dbTax==null){
			throw new RuntimeException(ErrorMessageHandler.taxDoesNotExists);
		}
		taxRepository.delete(dbTax);
	}

	@Override
	public List<Tax> getAllTaxsByAdmin(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Tax> getAllTaxsByAdminPage(String username, int page, int size) {
		// TODO Auto-generated method stub
		UserAccount dbUserAccount=userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Page<Tax> dbTaxs=taxRepository.findByCompany_Id(dbUserAccount.getCompany().getId(),
				new PageRequest(page, size, Sort.Direction.DESC, "id"));
		return dbTaxs;
	}

}
