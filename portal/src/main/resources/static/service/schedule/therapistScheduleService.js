function therapistScheduleService(MODULE_CONFIG, $http) {
	this.addSchedule = function(schedule) {
	return 	$http.post(MODULE_CONFIG.ADDSCHEDULE(),schedule);
	};
	this.getAllSchedules=function(){
		return $http.get(MODULE_CONFIG.GETALLSCHEDULES());
	};
	this.updateSchedule=function(schedule){
		return $http.put(MODULE_CONFIG.UPDATESCHEDULE(),schedule);
	};
	this.deleteSchedule=function(id){
		return $http.delete(MODULE_CONFIG.DELETESCHEDULE(id));
	};
	this.getAllSchedulesByDoctorUsername=function(doctorUsername){
		return $http.get(MODULE_CONFIG.GETALLSCHEDULESBYDOCTORUSERNAME(doctorUsername));
	};
	this.getAllTherapistsSchedulesByRole=function(adminUsername){
		return $http.get(MODULE_CONFIG.GETALLTHERAPISTSSCHEDULESBYROLE(adminUsername));
	};
	this.getAllTherapists=function(adminUsername){
		return $http.get(MODULE_CONFIG.GETALLTHERAPISTS(adminUsername));
	};
}

angular.module('HealthApplication').service('therapistScheduleService', therapistScheduleService);





