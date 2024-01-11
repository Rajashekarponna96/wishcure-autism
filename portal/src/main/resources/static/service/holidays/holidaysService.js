function holidaysService(MODULE_CONFIG, $http) {
	this.addHolidays = function(holidays) {
	return 	$http.post(MODULE_CONFIG.ADDHOLIDAYS(),holidays);
	};
	this.getAllHolidays=function(){
		return $http.get(MODULE_CONFIG.GETALLHOLIDAYS());
	};
	this.updateHolidays=function(holidays){
		return $http.put(MODULE_CONFIG.UPDATEHOLIDAYS(),holidays);
	};
	this.deleteHolidays=function(id){
		return $http.delete(MODULE_CONFIG.DELETEHOLIDAYS(id));
	};
	this.getAllHolidaysByDoctorUsername=function(doctorUsername){
		return $http.get(MODULE_CONFIG.GETALLHOLIDAYSBYDOCTORUSERNAME(doctorUsername));
	};
	this.getAllHolidaysByDoctorUsernamePage=function(doctorUsername,page,size){
		return $http.get(MODULE_CONFIG.GETALLHOLIDAYSBYDOCTORUSERNAMEPAGE(doctorUsername,page,size));
	};
	this.getAllHolidaysByRole=function(username){
		return $http.get(MODULE_CONFIG.GETALLHOLIDAYSBYROLE(username));
	};
	
	
	
	
	
}

angular.module('HealthApplication').service('holidaysService', holidaysService);