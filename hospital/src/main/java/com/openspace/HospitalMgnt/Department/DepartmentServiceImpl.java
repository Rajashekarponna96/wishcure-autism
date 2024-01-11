package com.openspace.HospitalMgnt.Department;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.PatientRegistrationType;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.Lookups.Department;
import com.openspace.Model.PatientMgnt.Repositories.CompanyRepository;
import com.openspace.Model.PatientMgnt.Repositories.DepartmentRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRegistrationTypeRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.PatientMgnt.Repositories.RoleRepository;
import com.openspace.Model.PatientMgnt.Repositories.TherapistRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.Role;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private TherapistRepository therapistRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PatientRegistrationTypeRepository patientRegistrationTypeRepository;

	@Override
	public void saveDepartment(Department department) {

		UserAccount dbUserAccount = userAccountRepository.findByUsername(department.getAdminUserName());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		if (dbUserAccount.getRole().getRoleName().equals("Product Owner")) {
			department.setProductOwnerStatus(true);
		}
		department.setCompany(dbUserAccount.getCompany());
		department.setSite(dbUserAccount.getSite());
		department.setUserAccount(dbUserAccount);
		if (department.getRole() != null) {
			Role dbRole = roleRepository.findOne(department.getRole().getId());
			if (dbRole != null) {
				department.setRole(dbRole);
			}
		}

		if (department.getPatientRegistrationType() != null) {
			PatientRegistrationType dbPatientRegistrationType = patientRegistrationTypeRepository
					.findOne(department.getPatientRegistrationType().getId());
			if (dbPatientRegistrationType != null) {
				department.setPatientRegistrationType(dbPatientRegistrationType);
			}
		}
		Department dbDepartment = null;

		if (!dbUserAccount.getRole().getRoleName().equals("Individual")) {
			dbDepartment = departmentRepository.findByCompany_IdAndDepartmentName(dbUserAccount.getCompany().getId(),
					department.getDepartmentName());
		} else if (dbUserAccount.getRole().getRoleName().equals("Product Owner")) {

			dbDepartment = departmentRepository.findByDepartmentNameAndUserAccount_Id(department.getDepartmentName(),
					dbUserAccount.getId());
		} else {
			dbDepartment = departmentRepository.findByDepartmentNameAndUserAccount_UniqueId(
					department.getDepartmentName(), dbUserAccount.getUniqueId());
		}

		if (dbDepartment != null) {
			throw new RuntimeException(ErrorMessageHandler.departmentExists);
		}
		department.setStatus(true);
		department.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		department.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		departmentRepository.save(department);
	}

	public List<Department> getAllDepartmentsByProductOwner() {
		List<UserAccount> dbUserAccounts = userAccountRepository.findByRole_RoleName("Product Owner");
		UserAccount account = null;
		for (UserAccount userAccount : dbUserAccounts) {
			account = userAccount;
		}
		List<Department> departments = departmentRepository.findByUserAccount_IdAndProductOwnerStatus(account.getId(),
				true);
		return departments;
	}

	/*
	 * @Override public Page<Department> getAllDepartments(int page, int size) {
	 * return (Page<Department>) departmentRepository.findAll(new PageRequest(page,
	 * size, Sort.Direction.DESC, "id"));
	 * 
	 * }
	 */
	@Override
	public Page<Department> getAllDepartments(String username, int page, int size) {
		/*
		 * UserAccount dbUserAccount=userAccountRepository.findByUsername(username); if
		 * (dbUserAccount == null) { throw new RuntimeException(
		 * "User  Does not Exist"); } Page<Department>
		 * dbDepartments=departmentRepository.findByCompany_Id(dbUserAccount.
		 * getCompany( ).getId(), new PageRequest(page, size, Sort.Direction.DESC,
		 * "id")); //return (Page<Department>) departmentRepository.findAll(new
		 * PageRequest(page, size, Sort.Direction.DESC, "id")); return dbDepartments;
		 */
		/*
		 * List<Department> dbDepartments =
		 * getAllDepartmentsListActiveAndInactive(username);
		 * 
		 * Long l= new Long(3);//first way
		 * 
		 * List<Department> dbDepartmentByProdOwner =
		 * departmentRepository.findByRole_Id(l);
		 * 
		 * dbDepartments.addAll(dbDepartmentByProdOwner);
		 * 
		 * 
		 * LinkedHashSet<Department> hashSet = new LinkedHashSet<>(dbDepartments);
		 * 
		 * ArrayList<Department> listWithoutDuplicates = new ArrayList<>(hashSet);
		 * 
		 * int start = new PageRequest(page, size).getOffset(); int end = (start + new
		 * PageRequest(page, size).getPageSize()) > listWithoutDuplicates.size() ?
		 * listWithoutDuplicates.size() : (start + new PageRequest(page,
		 * size).getPageSize());
		 * 
		 * return new PageImpl<Department>(listWithoutDuplicates.subList(start, end),
		 * new PageRequest(page, size, Sort.Direction.DESC, "id"),
		 * listWithoutDuplicates.size());
		 */

		List<Department> dbDepartments = getAllDepartmentsListActiveAndInactive(username);
		int start = new PageRequest(page, size).getOffset();
		int end = (start + new PageRequest(page, size).getPageSize()) > dbDepartments.size() ? dbDepartments.size()
				: (start + new PageRequest(page, size).getPageSize());

		return new PageImpl<Department>(dbDepartments.subList(start, end),
				new PageRequest(page, size, Sort.Direction.DESC, "id"), dbDepartments.size());

	}

	@Override
	public List<Department> getAllDepartmentsList(String username) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<Department> dbDepartments = null;
		dbDepartments = departmentRepository.findByCompany_IdAndStatus(dbUserAccount.getCompany().getId(), true);
		// return (Page<Department>) departmentRepository.findAll(new
		// PageRequest(page,
		// size, Sort.Direction.DESC, "id"));
		if (!dbUserAccount.getRole().getRoleName().equals("Product Owner")) {
			List<Department> pownerDepartments = getAllDepartmentsByProductOwner();
			// for(Department companyDepartment:dbDepartments){
			for (Department pownerDepartment : pownerDepartments) {
				Department companyDepartment = departmentRepository.findByCompany_IdAndDepartmentName(
						dbUserAccount.getCompany().getId(), pownerDepartment.getDepartmentName());
				if (companyDepartment == null) {
					Department newDepartment = new Department();
					newDepartment.setDepartmentName(pownerDepartment.getDepartmentName());
					newDepartment.setDescriptionName(pownerDepartment.getDescriptionName());
					newDepartment.setAdminUserName(username);
					newDepartment.setCompany(dbUserAccount.getCompany());
					newDepartment.setUserAccount(dbUserAccount);
					newDepartment.setStatus(true);
					newDepartment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
					newDepartment.setModifiedDate(new Timestamp(System.currentTimeMillis()));
					newDepartment.setProductOwnerhasThis(true);
					departmentRepository.save(newDepartment);
					dbDepartments = departmentRepository.findByCompany_IdAndStatus(dbUserAccount.getCompany().getId(),
							true);
				}

				/*
				 * if(pownerDepartment.getDepartmentName().equals( companyDepartment.
				 * getDepartmentName())){
				 * 
				 * }
				 */
			}
			// dbDepartments.addAll(departments);
			// }
		} else {
			dbDepartments = departmentRepository.findByUserAccount_IdAndProductOwnerStatus(dbUserAccount.getId(), true);
		}
		return dbDepartments;
	}

	/*
	 * public List<Department> getAllDepartmentsListActiveAndInactive(String
	 * username) { UserAccount dbUserAccount =
	 * userAccountRepository.findByUsername(username); if (dbUserAccount == null) {
	 * throw new RuntimeException(ErrorMessageHandler.userDoesNotExists); }
	 * List<Department> dbDepartments = null; dbDepartments =
	 * departmentRepository.findByCompany_Id(dbUserAccount.getCompany().getId()); //
	 * return (Page<Department>) departmentRepository.findAll(new //
	 * PageRequest(page, // size, Sort.Direction.DESC, "id")); if
	 * (!dbUserAccount.getRole().getRoleName().equals("Product Owner")) {
	 * List<Department> pownerDepartments = getAllDepartmentsByProductOwner(); //
	 * for(Department companyDepartment:dbDepartments){ for (Department
	 * pownerDepartment : pownerDepartments) { Department companyDepartment =
	 * departmentRepository.findByCompany_IdAndDepartmentName(
	 * dbUserAccount.getCompany().getId(), pownerDepartment.getDepartmentName()); if
	 * (companyDepartment == null) { Department newDepartment = new Department();
	 * newDepartment.setDepartmentName(pownerDepartment.getDepartmentName());
	 * newDepartment.setDescriptionName(pownerDepartment.getDescriptionName());
	 * newDepartment.setAdminUserName(username);
	 * newDepartment.setCompany(dbUserAccount.getCompany());
	 * newDepartment.setUserAccount(dbUserAccount); newDepartment.setStatus(true);
	 * newDepartment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
	 * newDepartment.setModifiedDate(new Timestamp(System.currentTimeMillis()));
	 * newDepartment.setProductOwnerhasThis(true);
	 * departmentRepository.save(newDepartment); dbDepartments =
	 * departmentRepository.findByCompany_Id(dbUserAccount.getCompany().getId()); }
	 * 
	 * 
	 * if(pownerDepartment.getDepartmentName().equals( companyDepartment.
	 * getDepartmentName())){
	 * 
	 * }
	 * 
	 * } // dbDepartments.addAll(departments); // } } else { dbDepartments =
	 * departmentRepository.findByUserAccount_IdAndProductOwnerStatus(dbUserAccount.
	 * getId(), true); } return dbDepartments; }
	 */
	
	// new 718
	public List<Department> getAllDepartmentsListActiveAndInactive(String username) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<Department> dbDepartments = null;
		if (dbUserAccount.getRole().getRoleName().equals("Super Admin")) {
			dbDepartments = departmentRepository.findByCompany_IdAndUserAccount_Id(dbUserAccount.getCompany().getId(), dbUserAccount.getId());
		}else if (dbUserAccount.getRole().getRoleName().equals("Facility Manager")) {
			dbDepartments = departmentRepository.findByCompany_IdAndUserAccount_Id(dbUserAccount.getCompany().getId(), dbUserAccount.getId());
			List<UserAccount> superAdminUsers = userAccountRepository.findByCompany_IdAndRole_RoleNameAndActive(dbUserAccount.getCompany().getId(),"Super Admin",true);
			if (!superAdminUsers.isEmpty()) {
				List<Department> superAdminDepts = departmentRepository.findByCompany_IdAndUserAccount_Id(dbUserAccount.getCompany().getId(), superAdminUsers.get(0).getId());
				dbDepartments.addAll(superAdminDepts);
			}
		}
		return dbDepartments;
	}

	@Override
	public Page<Department> getAllDepartmentsSearch(String username, String search, int page, int size) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Company dbCompany = companyRepository.findOne(dbUserAccount.getCompany().getId());
		if (dbCompany == null) {
			throw new RuntimeException(ErrorMessageHandler.companyDoesNotExists);
		}
		if (search != null) {
			List<Department> departmentList = new ArrayList<Department>();
			departmentList = departmentRepository.findByDepartmentSearch(search, dbCompany.getId());
			return new PageImpl<Department>(departmentList, new PageRequest(page, size, Sort.Direction.DESC, "id"),
					departmentList.size());
		} else {
			return (Page<Department>) departmentRepository
					.findAll(new PageRequest(page, size, Sort.Direction.DESC, "id"));
		}
	}

	@Override
	public List<Department> getAllDepartments() {
		return (List<Department>) departmentRepository.findAll();
	}

	@Override
	public void updateDepartment(Department department) {
		Department dbDepartment = departmentRepository.findOne(department.getId());
		if (dbDepartment == null) {
			throw new RuntimeException(ErrorMessageHandler.departmentNotFound);
		}
		if (department.getRole() != null) {
			Role dbRole = roleRepository.findOne(department.getRole().getId());
			if (dbRole != null) {
				dbDepartment.setRole(dbRole);
			}
		}
		if (department.getPatientRegistrationType() != null) {
			PatientRegistrationType dbPatientRegistrationType = patientRegistrationTypeRepository
					.findOne(department.getPatientRegistrationType().getId());
			if (dbPatientRegistrationType != null) {
				dbDepartment.setPatientRegistrationType(dbPatientRegistrationType);
			}
		}

		Department dbDepartment2 = departmentRepository
				.findByCompany_IdAndDepartmentName(department.getCompany().getId(), department.getDepartmentName());
		if (dbDepartment2 == null) {
			dbDepartment.setDepartmentName(department.getDepartmentName());
			dbDepartment.setDescriptionName(department.getDescriptionName());
			dbDepartment.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			dbDepartment.setStatus(department.isStatus());
		} else if (dbDepartment2.getId().equals(department.getId())) {
			dbDepartment.setDepartmentName(department.getDepartmentName());
			dbDepartment.setDescriptionName(department.getDescriptionName());
			dbDepartment.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			dbDepartment.setStatus(department.isStatus());
		} else {
			throw new RuntimeException(ErrorMessageHandler.departmentExists);
		}
		dbDepartment.setDepartmentName(department.getDepartmentName());
		dbDepartment.setDescriptionName(department.getDescriptionName());
		dbDepartment.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		dbDepartment.setStatus(department.isStatus());
		departmentRepository.save(dbDepartment);
	}

	@Override
	public void deleteDepartment(Long id) {
		Department dbDepartment = departmentRepository.findOne(id);
		if (dbDepartment == null) {
			throw new RuntimeException(ErrorMessageHandler.departmentNotFound);
		}

		List<Doctor> doctors = therapistRepository.findByDepartment_Id(id);
		if (doctors.size() > 0) {
			throw new RuntimeException(ErrorMessageHandler.departmentAssignedToTherapistCannotBeDeleted);
		}

		/*
		 * List<Patient> patients = patientRepository.findByDepartment_Id(id); if
		 * (patients.size() > 0) { throw new RuntimeException(ErrorMessageHandler.
		 * departmentAssignedToPatientCannotBeDeleted); }
		 */

		try {
			departmentRepository.delete(dbDepartment);
		} catch (Exception exception) {
			throw new RuntimeException(ErrorMessageHandler.userAlreadyInUseCannotBeDeleted);
		}

	}
	/*
	 * public Page<Department> findAll(int page, int size) { return
	 * departmentRepository.findAll(new PageRequest(page, size)); }
	 */

	@Override
	public List<Department> getAllDepartmentsByRole(Long roleId) {
		// TODO Auto-generated method stub
		return departmentRepository.findByRole_Id(roleId);
	}

	@Override
	public List<Department> getAllDepartmentsListByCompanyAndRole(String username, Long roleid) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(username);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<Department> dbDepartments = null;
		dbDepartments = departmentRepository.findByCompany_IdAndStatusAndRole_Id(dbUserAccount.getCompany().getId(),
				true, roleid);
		// return (Page<Department>) departmentRepository.findAll(new
		// PageRequest(page,
		// size, Sort.Direction.DESC, "id"));
		if (!dbUserAccount.getRole().getRoleName().equals("Product Owner")) {
			List<Department> pownerDepartments = getAllDepartmentsByProductOwner();
			// for(Department companyDepartment:dbDepartments){
			for (Department pownerDepartment : pownerDepartments) {
				if (pownerDepartment.getRole() != null) {
					dbDepartments.add(pownerDepartment);
				}
				/*
				 * Department companyDepartment =
				 * departmentRepository.findByCompany_IdAndDepartmentName(
				 * dbUserAccount.getCompany().getId(), pownerDepartment.getDepartmentName()); if
				 * (companyDepartment == null) { Department newDepartment = new Department();
				 * newDepartment.setDepartmentName(pownerDepartment.getDepartmentName());
				 * newDepartment.setDescriptionName(pownerDepartment.getDescriptionName());
				 * newDepartment.setAdminUserName(username);
				 * newDepartment.setCompany(dbUserAccount.getCompany());
				 * newDepartment.setUserAccount(dbUserAccount); newDepartment.setStatus(true);
				 * newDepartment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
				 * newDepartment.setModifiedDate(new Timestamp(System.currentTimeMillis()));
				 * newDepartment.setProductOwnerhasThis(true);
				 * departmentRepository.save(newDepartment); dbDepartments =
				 * departmentRepository
				 * .findByCompany_IdAndStatusAndRole_Id(dbUserAccount.getCompany().getId(),
				 * true, roleid); }
				 */

				/*
				 * if(pownerDepartment.getDepartmentName().equals( companyDepartment.
				 * getDepartmentName())){
				 * 
				 * }
				 */
			}
			// dbDepartments.addAll(departments);
			// }
		} else {
			dbDepartments = departmentRepository.findByUserAccount_IdAndProductOwnerStatus(dbUserAccount.getId(), true);
		}
		return dbDepartments;
	}

	/*
	 * @Override public List<Department>
	 * getAllDepartmentsListByRegistartionType(Long id) { // TODO Auto-generated
	 * method stub return departmentRepository.findByPatientRegistrationType_Id(id);
	 * 
	 * 
	 * 
	 * }
	 */

	@Override
	public List<Department> getDepartmentsByRegistartionTypeAndProductOwnerAndCompany(Long regTypeId, String userName) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(userName);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}

		List<Department> dbDepartments = null;

		if (!dbUserAccount.getRole().getRoleName().equals("Product Owner")) {

			dbDepartments = departmentRepository.findByCompany_IdAndStatusAndPatientRegistrationType_Id(
					dbUserAccount.getCompany().getId(), true, regTypeId);

			List<UserAccount> dbUserAccounts = userAccountRepository.findByRole_RoleName("Product Owner");
			UserAccount prodOwnerAccount = null;
			for (UserAccount userAccount : dbUserAccounts) {
				prodOwnerAccount = userAccount;
			}

			List<Department> productOwnerDepartments = departmentRepository
					.findByUserAccount_IdAndProductOwnerStatusAndPatientRegistrationType_Id(prodOwnerAccount.getId(),
							true, regTypeId);
			dbDepartments.addAll(productOwnerDepartments);

		} else {
			dbDepartments = departmentRepository.findByPatientRegistrationType_Id(regTypeId);
		}
		return dbDepartments;

	}

	@Override
	public List<Department> getAllDepartmentsListByRegistartionType(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
