package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.Company;
import com.openspace.Model.UserManagement.UserAccount;

@Repository
public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {

	UserAccount findByUsername(String username);
	UserAccount findByCompany(Company company);
	UserAccount findByloginIp(String ip);
	List<UserAccount> findByCompany_Id(Long id);
	List<UserAccount> findBySite_Id(Long id);
	Page<UserAccount> findByCompany_Id(Long id,Pageable pageable);
	List<UserAccount> findByCompany_IdAndActive(Long id,boolean status);
	List<UserAccount> findByRole_RoleName(String name);
	List<UserAccount> findByCompany_IdAndRole_IdAndActive(Long id, Long id2, boolean b);
	List<UserAccount> findByCompany_IdAndRole_RoleNameAndActive(Long companyId, String roleName, boolean active);
	List<UserAccount> findByRegisteredUser(boolean registeredUser);
	UserAccount findByUsernameAndPaymentDone(String username,boolean paymentDone);
	
	List<UserAccount> findByUniqueId(String uniqueId);
	List<UserAccount> findAll();
	List<UserAccount> findBySite_IdAndRole_IdAndActive(Long siteId, Long roleId, boolean active);
	List<UserAccount> findBySite_IdAndActive(Long id, boolean b);
}

 
