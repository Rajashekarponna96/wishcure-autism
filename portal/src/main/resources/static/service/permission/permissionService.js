function permissionService(MODULE_CONFIG, $http) {
	this.addPermission = function(permission) {
	return $http.post(MODULE_CONFIG.ADD_PERMISSION(),permission);
	};
	this.getAllPermissions=function(){
		return $http.get(MODULE_CONFIG.GETALLPERMISSIONS());
	};
	this.updatePermission=function(permission){
		return $http.put(MODULE_CONFIG.UPDATE_PERMISSION(),permission);
	};
	this.deleteRoleType=function(id){
		return $http.delete(MODULE_CONFIG.DELETEROLE(id));
	};
	this.getAllRolesBasedOnStatus=function(){
		return $http.get(MODULE_CONFIG.GETALLROLESBASEDONSTATUS());
	};
	this.getAllPermissionsByRoleId=function(id){
		return $http.get(MODULE_CONFIG.GETALLPERMISSIONSBY(id));
	};

	
}

angular.module('HealthApplication').service('permissionService', permissionService);