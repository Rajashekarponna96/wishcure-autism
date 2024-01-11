package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Currency;
import com.openspace.Model.Lookups.Department;
import com.openspace.Model.PatientMgnt.Repositories.CurrencyRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Override
	public void addCurrency(Currency currency) {
		UserAccount dbUserAccount=userAccountRepository.findByUsername(currency.getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		currency.setCompany(dbUserAccount.getCompany());
		currency.setUserAccount(dbUserAccount);
		/*Currency dbCurrency=null;
		if(!dbUserAccount.getRole().getRoleName().equals("Individual")){
		 dbCurrency=currencyRepository.findByNameAndCompany_Id(currency.getName(),dbUserAccount.getCompany().getId());
		}else{
			dbCurrency=currencyRepository.findByNameAndUserAccount_UniqueId(currency.getName(),dbUserAccount.getUniqueId());
		}*/
		/*
		 * Currency dbCurrency=currencyRepository.findByName(currency.getName());
		 * if(dbCurrency!=null){ throw new
		 * RuntimeException(ErrorMessageHandler.currencyAlreadyExists); }
		 */
		//718
		Currency dbCurrency = null;

		
		dbCurrency = currencyRepository.findByNameAndUserAccount_UniqueId(
				currency.getName(), dbUserAccount.getUniqueId());
		

		if (dbCurrency != null) {
			throw new RuntimeException(ErrorMessageHandler.currencyAlreadyExists);
		}
		
		
		
		currencyRepository.save(currency);
	}

	@Override
	public List<Currency> getAllCurrencys() {
		// TODO Auto-generated method stub
		return (List<Currency>) currencyRepository.findAll();
	}

	@Override
	public void updateCurrency(Currency currency) {
		// TODO Auto-generated method stub
		Currency dbCurrency=currencyRepository.findOne(currency.getId());
		if(dbCurrency==null){
			throw new RuntimeException(ErrorMessageHandler.currencyDoesNotExists);
		}
		Currency dbCurrency2=currencyRepository.findByName(currency.getName());
		if(dbCurrency2==null){
			
		}else if(dbCurrency2.getId().equals(currency.getId())){
			
		}else{
			throw new RuntimeException(ErrorMessageHandler.currencyAlreadyExists);
		}
		dbCurrency=currency;
		currencyRepository.save(dbCurrency);
	}

	@Override
	public void deleteCurrency(Long id) {
		// TODO Auto-generated method stub
		Currency dbCurrency=currencyRepository.findOne(id);
		if(dbCurrency==null){
			throw new RuntimeException(ErrorMessageHandler.currencyDoesNotExists);
		}
		try {
			currencyRepository.delete(dbCurrency);
		} catch (Exception exception) {
			throw new RuntimeException("Can't Delete! It's Already In Use");
		}
		
	}

	@Override
	public List<Currency> getAllCurrencysByAdmin(String username) {
		// TODO Auto-generated method stub
		UserAccount dbUserAccount=userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<Currency> dbCurrencys=currencyRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
		return dbCurrencys;
	}

/*	For Company Based Pagination
 * @Override
	public Page<Currency> getAllCurrencysByAdminPage(String username, int page, int size) {
		// TODO Auto-generated method stub
		UserAccount dbUserAccount=userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException("User  Does not Exist");
		}
		Page<Currency> dbCurrencys=currencyRepository.findByCompany_Id(dbUserAccount.getCompany().getId(),
				new PageRequest(page, size, Sort.Direction.DESC, "id"));
		return dbCurrencys;
	}
	*/
	// Pagination  For All
	@Override
	public Page<Currency> getAllCurrencysByAdminPage(String username, int page, int size) {
		// TODO Auto-generated method stub
		UserAccount dbUserAccount=userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		return currencyRepository.findAll(new PageRequest(page, size));
		 
		//return dbCurrencys;
	}

}
