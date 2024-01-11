package com.openspace.HospitalMgnt.common.Permission;

import java.util.List;

import com.openspace.Model.UserManagement.Permission;

public interface PermissionService {

	void addPermission(Permission permission);

	List<Permission> getAllPermissions();

	void updatePermission(Permission permission);

	void deletePermission(Long id);

	List<Permission> getAllPaermissionsByRoleId(Long roleId);

}
