function roleService(MODULE_CONFIG, $http) {
	this.addRoleType = function(role) {
	return 	$http.post(MODULE_CONFIG.ADD_ROLETYPE(),role);
	};
	this.getAllRoles=function(){
		return $http.get(MODULE_CONFIG.GETALLROLES());
	};
	this.updateRoleType=function(role){
		return $http.put(MODULE_CONFIG.UPDATEROLE(),role);
	};
	this.deleteRoleType=function(id){
		return $http.delete(MODULE_CONFIG.DELETEROLE(id));
	};
	this.getAllRolesBasedOnStatus=function(){
		return $http.get(MODULE_CONFIG.GETALLROLESBASEDONSTATUS());
	};
	this.assignFeatureToRole=function(role){
		return $http.post(MODULE_CONFIG.ASSIGFEATURETOROLE(),role);
	};
	this.updateRoleFeature=function(roleFeature){
		return $http.put(MODULE_CONFIG.UPDATEROLEFEATURE(),roleFeature);
	}
	
	
	
}

angular.module('HealthApplication').service('roleService', roleService);