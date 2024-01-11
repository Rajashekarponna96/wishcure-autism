package com.openspace.HospitalMgnt.common.Permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.PatientMgnt.Repositories.PermissionRepository;
import com.openspace.Model.UserManagement.Permission;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public void addPermission(Permission permission) {
		Permission dbPermission = permissionRepository
				.findByCategory_CategoryName(permission.getCategory().getCategoryName());
		if (dbPermission != null) {
			throw new RuntimeException(ErrorMessageHandler.permissionTypeAlreadyExists);
		}
		permissionRepository.save(permission);
	}

	@Override
	public List<Permission> getAllPermissions() {
		return (List<Permission>) permissionRepository.findAll();
	}

	@Override
	public void updatePermission(Permission permission) {
		Permission dbPermission = permissionRepository.findOne(permission.getId());
		if (dbPermission == null) {
			throw new RuntimeException(ErrorMessageHandler.permissionTypeDoesNotExists);
		}
		Permission dbPermission2 = permissionRepository
				.findByCategory_CategoryName(permission.getCategory().getCategoryName());
		if (dbPermission2 == null) {
			throw new RuntimeException(ErrorMessageHandler.permissionTypeAlreadyExists);
		}
		dbPermission.setApproved(permission.isApproved());
		dbPermission.setCanAdd(permission.isCanAdd());
		dbPermission.setCanDelete(permission.isCanDelete());
		dbPermission.setCanUpdate(permission.isCanUpdate());
		dbPermission.setCanView(permission.isCanView());
		dbPermission.setCategory(permission.getCategory());
		permissionRepository.save(dbPermission);

	}

	@Override
	public void deletePermission(Long id) {
		Permission dbPermission = permissionRepository.findOne(id);
		if (dbPermission == null) {
			throw new RuntimeException(ErrorMessageHandler.permissionTypeDoesNotExists);
		}
		permissionRepository.delete(dbPermission);

	}

	@Override
	public List<Permission> getAllPaermissionsByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return permissionRepository.findByRole_Id(roleId);
	}

}
