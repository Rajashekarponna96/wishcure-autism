package com.openspace.HospitalMgnt.common.Permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.UserManagement.Permission;

@RestController
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@RequestMapping(value = RestURIConstants.SAVE_PERMISSION, method = RequestMethod.POST)
	public @ResponseBody void addPermission(@RequestBody Permission permission) {
		permissionService.addPermission(permission);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_PERMISSIONS, method = RequestMethod.GET)
	public @ResponseBody List<Permission> getAllPermissions() {
		return permissionService.getAllPermissions();
	}

	@RequestMapping(value = RestURIConstants.UPDATE_PERMISSION, method = RequestMethod.PUT)
	public @ResponseBody void updatePermission(@RequestBody Permission permission) {
		permissionService.updatePermission(permission);
	}

	@RequestMapping(value = RestURIConstants.DELETE_PERMISSION, method = RequestMethod.DELETE)
	public @ResponseBody void deletePermission(@PathVariable Long id) {
		permissionService.deletePermission(id);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_PERMISSIONS_BY_ROLE, method = RequestMethod.GET)
	public @ResponseBody List<Permission> getAllPaermissionsByRoleId(@PathVariable Long roleId) {
		return permissionService.getAllPaermissionsByRoleId(roleId);
	}

}
