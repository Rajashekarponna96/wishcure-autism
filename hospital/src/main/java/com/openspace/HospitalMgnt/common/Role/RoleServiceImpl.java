package com.openspace.HospitalMgnt.common.Role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.PatientMgnt.Repositories.PermissionRepository;
import com.openspace.Model.PatientMgnt.Repositories.RoleRepository;
import com.openspace.Model.UserManagement.Permission;
import com.openspace.Model.UserManagement.Role;
import com.openspace.Model.UserManagement.RoleStatus;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public void addRole(Role role) {
		Role dbRole = roleRepository.findByRoleName(role.getRoleName());
		if (dbRole != null) {
			throw new RuntimeException(ErrorMessageHandler.roleAlreadyExists);
		}
		// role.setStatus(true);
		role.setRoleStatus(RoleStatus.ACTIVE);
		roleRepository.save(role);
	}

	@Override
	public List<Role> getAllRoles() {
		return (List<Role>) roleRepository.findAll();
	}

	@Override
	public void updateRole(Role role) {
		Role dbRole = roleRepository.findOne(role.getId());
		if (dbRole == null) {
			throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
		}
		Role dbRoleByName = roleRepository.findByRoleName(role.getRoleName());
		if (dbRoleByName == null) {
			dbRole.setDisplayName(role.getDisplayName());
			dbRole.setRoleName(role.getRoleName());
			dbRole.setRoleDescription(role.getRoleDescription());
			dbRole.setRoleStatus(role.getRoleStatus());
		} else if (dbRoleByName.getId().equals(role.getId())) {
			dbRole.setDisplayName(role.getDisplayName());
			dbRole.setRoleName(role.getRoleName());
			dbRole.setRoleDescription(role.getRoleDescription());
			dbRole.setRoleStatus(role.getRoleStatus());
		} else {
			throw new RuntimeException(ErrorMessageHandler.roleAlreadyExists);
		}

		dbRole.setDisplayName(role.getDisplayName());
		dbRole.setRoleName(role.getRoleName());
		dbRole.setRoleDescription(role.getRoleDescription());
		dbRole.setRoleStatus(role.getRoleStatus());
		roleRepository.save(dbRole);
	}

	@Override
	public void deleteRole(Long id) {
		Role dbRole = roleRepository.findOne(id);
		if (dbRole == null) {
			throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
		}
		roleRepository.delete(dbRole);
	}

	@Override
	public List<Role> getAllRolesBasedonActive() {
		
		List<Role> roles = roleRepository.findByRoleStatus(RoleStatus.ACTIVE);
		
		System.out.println("Before Removed From List:");

		for(Role r : roles)
		{
			System.out.println("role before removal"+r.getRoleName());
		}
		
		List<Role> removeRoles = new ArrayList<>();
		removeRoles.add(roleRepository.findOne(Long.valueOf(2)));
		removeRoles.add(roleRepository.findOne(Long.valueOf(5)));
		removeRoles.add(roleRepository.findOne(Long.valueOf(15)));
		
		roles.removeAll(removeRoles);
		
		for(Role r : roles)
		{
			System.out.println("role after removal"+r.getRoleName());
		}
		return roles;
	}

	@Override
	public void addFeatureToRole(Role role) {
		Role dbRole = roleRepository.findOne(role.getId());
		if (dbRole == null) {
			throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
		}


		if (dbRole.getPermissions().size() > 0) {
			throw new RuntimeException(ErrorMessageHandler.featureAssignedAlreadyToRole + " " + dbRole.getRoleName());
		}
		if (role.getPermissions() != null) {
			for (Permission permission : role.getPermissions()) {
				permission.setRole(dbRole);
			}
		}
		permissionRepository.save(role.getPermissions());
		roleRepository.save(dbRole);
	}

	@Override
	public void updateRoleFeatures(Role role) {
		Role dbRole = roleRepository.findOne(role.getId());
		if (dbRole == null) {
			throw new RuntimeException(ErrorMessageHandler.roleDoesNotExists);
		}
		if (role.getPermissions() != null) {
			for (Permission permission : role.getPermissions()) {
				permission.setRole(dbRole);
			}
			dbRole.setPermissions(role.getPermissions());
		}
		// permissionRepository.save(role.getPermissions());
		roleRepository.save(dbRole);
	}

}
