function therapistService(MODULE_CONFIG, $http) {
	this.addTherapist = function(therapist) {
	return 	$http.post(MODULE_CONFIG.ADDTHERAPIST(),therapist);
	};
	this.getAllTherapists=function(adminUsername){
		return $http.get(MODULE_CONFIG.GETALLTHERAPISTS(adminUsername));
	};
	this.getAllTherpistByDepartment=function(id){
		
		return $http.get(MODULE_CONFIG.GETALLTHERAPISTSBYDEPARTMENT(id));
	};
	this.updateTherapist=function(therapist){
		return $http.put(MODULE_CONFIG.UPDATETHERAPIST(),therapist);
	};
	this.deleteTherapist=function(id){
		return $http.delete(MODULE_CONFIG.DELETETHERAPIST(id));
	};
	this.getTherapistByUsername=function(adminUsername){
		return $http.get(MODULE_CONFIG.GETTHERAPISTBYUSERNAME(adminUsername));
	};
	this.getAllActiveTherapists=function(adminUsername){
		return $http.get(MODULE_CONFIG.GETALLACTIVETHERAPISTS(adminUsername));
	};
	this.getAllInactiveTherapists=function(adminUsername){
		return $http.get(MODULE_CONFIG.GETALLINACTIVETHERAPISTS(adminUsername));
	};
	this.getAlltherapistsByAdminUsername=function(adminUsername){
		return $http.get(MODULE_CONFIG.GET_ALL_THERAPISTS_BY_ADMINUSERNAME(adminUsername));
	};
}
angular.module('HealthApplication').service('therapistService', therapistService);