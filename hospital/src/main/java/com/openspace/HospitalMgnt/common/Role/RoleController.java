package com.openspace.HospitalMgnt.common.Role;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.PatientMgnt.Repositories.RoleRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.Role;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	

	@RequestMapping(value = RestURIConstants.SAVE_ROLE, method = RequestMethod.POST)
	public @ResponseBody void addRole(@RequestBody Role role) {
		roleService.addRole(role);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_ROLES, method = RequestMethod.GET)
	public @ResponseBody List<Role> getAllRoles() {
		return roleService.getAllRoles();
	}

	@RequestMapping(value = RestURIConstants.UPDATE_ROLE, method = RequestMethod.PUT)
	public @ResponseBody void updateRole(@RequestBody Role role) {
		roleService.updateRole(role);
	}

	@RequestMapping(value = RestURIConstants.DELETE_ROLE, method = RequestMethod.DELETE)
	public @ResponseBody void deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_ROLES_BASEDON_STATUS, method = RequestMethod.GET)
	public @ResponseBody List<Role> getAllRolesBasedonActive() {
		return roleService.getAllRolesBasedonActive();
	}

	@RequestMapping(value = RestURIConstants.ASSIGN_FEATURETO_ROLE, method = RequestMethod.POST)
	public @ResponseBody void addFeatureToRole(@RequestBody Role role) {
		roleService.addFeatureToRole(role);
	}
	
	@RequestMapping(value = RestURIConstants.UPDATE_ROLE_FEATURE, method = RequestMethod.PUT)
	public @ResponseBody void updateRoleFeatures(@RequestBody Role role) {
		roleService.updateRoleFeatures(role);
	}

	
}
