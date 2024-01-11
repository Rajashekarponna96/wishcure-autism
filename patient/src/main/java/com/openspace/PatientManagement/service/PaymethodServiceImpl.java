package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.AppointmentInvoice;
import com.openspace.Model.DoctorManagement.Paymethod;
import com.openspace.Model.PatientMgnt.Repositories.AppointmentInvoiceRpository;
import com.openspace.Model.PatientMgnt.Repositories.PaymentRepository;
import com.openspace.Model.PatientMgnt.Repositories.PaymethodRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.Payment.Payment;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class PaymethodServiceImpl implements PaymethodService {

	@Autowired
	private PaymethodRepository paymethodRepository;
	
	@Autowired
	private AppointmentInvoiceRpository appointmentInvoiceRpository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Override
	public void addPaymethod(Paymethod paymethod) {
		UserAccount dbUserAccount=userAccountRepository.findByUsername(paymethod.getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		if(dbUserAccount.getRole().getRoleName().equals("Product Owner")){
			paymethod.setProductOwnerStatus(true);
		}
		paymethod.setCompany(dbUserAccount.getCompany());
		paymethod.setUserAccount(dbUserAccount);
		
		Paymethod dbPaymethod=null;
		
		if(!dbUserAccount.getRole().getRoleName().equals("Individual")){
			dbPaymethod=paymethodRepository.findByNameAndCompany_Id(paymethod.getName(),dbUserAccount.getCompany().getId());
			}else if(dbUserAccount.getRole().getRoleName().equals("Product Owner")){
				
				dbPaymethod=paymethodRepository.findByNameAndUserAccount_Id(paymethod.getName(),dbUserAccount.getId());
			}else{
				dbPaymethod=paymethodRepository.findByNameAndUserAccount_UniqueId(paymethod.getName(),dbUserAccount.getUniqueId());
			}
		
		if(dbPaymethod!=null){
			throw new RuntimeException(ErrorMessageHandler.paymentDetailsALreadyExists);
		}
		paymethodRepository.save(paymethod);
	}

	@Override
	public List<Paymethod> getAllPaymethods() {
		// TODO Auto-generated method stub
		return (List<Paymethod>) paymethodRepository.findAll();
	}
	
	@Override
	public void updatePaymethod(Paymethod paymethod) {
		// TODO Auto-generated method stub
		UserAccount dbUserAccount=userAccountRepository.findByUsername(paymethod.getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Paymethod dbPaymethod=paymethodRepository.findOne(paymethod.getId());
		if(dbPaymethod==null){
			throw new RuntimeException(ErrorMessageHandler.sorrypaymentDetailsDoesNotExists);
		}
		try{
			Paymethod dbPaymethod2=null;
			if(dbUserAccount.getCompany()!=null){
			dbPaymethod2=paymethodRepository.findByNameAndCompany_Id(paymethod.getName(),dbUserAccount.getCompany().getId());
			}else{
				dbPaymethod2=paymethodRepository.findByNameAndUserAccount_Id(paymethod.getName(),dbUserAccount.getId());
			}
			if(dbPaymethod2==null){
				
			}else if(dbPaymethod2.getId().equals(paymethod.getId())){
				
			}else{
				throw new RuntimeException(ErrorMessageHandler.paymentDetailsALreadyExists);
			}
		}catch(Exception exception){
			throw new RuntimeException(ErrorMessageHandler.paymentDetailsALreadyExists);
		}
		
		dbPaymethod=paymethod;
		paymethodRepository.save(dbPaymethod);
	}

	@Override
	public void deletePaymethod(Long id) {
		// TODO Auto-generated method stub
		Paymethod dbPaymethod=paymethodRepository.findOne(id);
		if(dbPaymethod==null){
			throw new RuntimeException(ErrorMessageHandler.sorrypaymentDetailsDoesNotExists);
		}
		List<Payment> payments=paymentRepository.findByPaymentTypes_Id(id);
		if(payments.size()>0){
			throw new RuntimeException("Paymethod Assigned To Payment! Can't Delete");
		}
		List<AppointmentInvoice> appointmentInvoices=appointmentInvoiceRpository.findByPaymethod_Id(id);
		if(appointmentInvoices.size()>0){
			throw new RuntimeException("Paymethod Assigned To Invoice! Can't Delete");
		}
		try {
			paymethodRepository.delete(dbPaymethod);
		} catch (Exception exception) {
			throw new RuntimeException("User Can't Delete! It's Already In Use");
		}
		
	}

	/*@Override
	public List<Paymethod> getAllPaymethodsByAdmin(String username) {
		// TODO Auto-generated method stub
		UserAccount dbUserAccount=userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<Paymethod> dbPaymethods=paymethodRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
		return dbPaymethods;
	}*/
	
	
	
	public List<Paymethod> getAllPaymethodsByProductOwner(){
		   //List<UserAccount> dbUserAccounts=userAccountRepository.findByRole_RoleName("Product Owner");
		   Iterable<UserAccount> dbUserAccounts=userAccountRepository.findAll();
		   UserAccount account=null;
		   for(UserAccount userAccount:dbUserAccounts){
			   account=userAccount;
		   }
		  List<Paymethod> departments = null;
		if (account.getId() != null) {
			departments = paymethodRepository.findByUserAccount_IdAndProductOwnerStatus(account.getId(), true);
		}
		  return departments;
	   }
	
	@Override
	public List<Paymethod> getAllPaymethodsByAdmin(String username) {
		UserAccount dbUserAccount=userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException("User  Does not Exist");
		}
		List<Paymethod> paymethods=null;
		paymethods=paymethodRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
		//return (Page<Department>) departmentRepository.findAll(new PageRequest(page, size, Sort.Direction.DESC, "id"));
		if(!dbUserAccount.getRole().getRoleName().equals("Product Owner")){
			List<Paymethod> pownerpaymethods=getAllPaymethodsByProductOwner();
			//for(Department companyDepartment:dbDepartments){
			for(Paymethod pownerpaymethod:pownerpaymethods){
				Paymethod companypaymethod=null;
				if( dbUserAccount.getCompany()!=null){
					 companypaymethod=paymethodRepository.findByNameAndCompany_Id(pownerpaymethod.getName(), dbUserAccount.getCompany().getId());	
				}else{
					companypaymethod=paymethodRepository.findByNameAndUserAccount_Id(pownerpaymethod.getName(), dbUserAccount.getId());	
				}
				
				if(companypaymethod==null){
					Paymethod newpaymethod=new Paymethod();
					newpaymethod.setName(pownerpaymethod.getName());
					newpaymethod.setDescription(pownerpaymethod.getDescription());
					newpaymethod.setAdminUserName(username);
					newpaymethod.setCompany(dbUserAccount.getCompany());
					newpaymethod.setUserAccount(dbUserAccount);
					newpaymethod.setProductOwnerhasThis(true);
					paymethodRepository.save(newpaymethod);
					paymethods=paymethodRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
				}
				
				
				/*if(pownerDepartment.getDepartmentName().equals(companyDepartment.getDepartmentName())){
					
				}*/
			}
		//	dbDepartments.addAll(departments);
	//	}
		}else{
			paymethods=paymethodRepository.findByUserAccount_IdAndProductOwnerStatus(dbUserAccount.getId(),true);
			System.out.println("paymethods list -->"+paymethods.size());
		}
		return paymethods;
	}
	@Override
	public Page<Paymethod> getAllPaymethodsByAdminPage(String username, int page, int size) {
		/*
		// TODO Auto-generated method stub
		UserAccount dbUserAccount=userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Page<Paymethod> dbPaymethods=paymethodRepository.findByCompany_Id(dbUserAccount.getCompany().getId(),
				new PageRequest(page, size, Sort.Direction.DESC, "id"));
		return dbPaymethods;
	*/
		List<Paymethod> dbPaymethods = getAllPaymethodsByAdmin(username);
		int start = new PageRequest(page, size).getOffset();
		int end = (start + new PageRequest(page, size).getPageSize()) > dbPaymethods.size() ? dbPaymethods.size()
				: (start + new PageRequest(page, size).getPageSize());

		return new PageImpl<Paymethod>(dbPaymethods.subList(start, end),
				new PageRequest(page, size, Sort.Direction.DESC, "id"), dbPaymethods.size());
	
	}

}
