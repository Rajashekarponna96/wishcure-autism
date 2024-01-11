package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.UserManagement.Role;
import com.openspace.Model.UserManagement.RoleStatus;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
	Role findByRoleName(String name);
	List<Role> findByRoleStatus(RoleStatus roleStatus);
}
