function regionalCenterZoneService(PATIENT_MODULE_CONFIG, $http) {
	this.addRCZ = function(rcz) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_REGIONAL_CENTER_ZONE(),rcz);
	};
	this.getAllRCZ=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GETALL_REGIONAL_CENTER_ZONE());
	};
	this.getAllRCZByStatus=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GETALL_REGIONAL_CENTER_ZONE_BY_STATUS());
	};
	this.updateRCZ=function(rcz){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_REGIONAL_CENTER_ZONE(),rcz);
	};
	this.deleteRCZ=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_REGIONAL_CENTER_ZONE(id));
	};
	
}

angular.module('HealthApplication').service('regionalCenterZoneService', regionalCenterZoneService);