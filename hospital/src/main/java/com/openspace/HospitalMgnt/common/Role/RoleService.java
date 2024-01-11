package com.openspace.HospitalMgnt.common.Role;

import java.util.List;

import com.openspace.Model.UserManagement.Role;

public interface RoleService {

	void addRole(Role role);

	List<Role> getAllRoles();

	void updateRole(Role role);

	void deleteRole(Long id);

	List<Role> getAllRolesBasedonActive();

	void addFeatureToRole(Role role);

	void updateRoleFeatures(Role role);

}
