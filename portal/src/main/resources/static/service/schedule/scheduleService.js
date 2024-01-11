function scheduleService(MODULE_CONFIG, $http) {
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
	this.getAllSchedulesByDoctorUsernamePage=function(doctorUsername,page,size){
		return $http.get(MODULE_CONFIG.GETALLSCHEDULESBYDOCTORUSERNAMEPAGE(doctorUsername,page,size));
	};
	this.getAllSchedulesByDoctorUsername=function(doctorUsername){
		return $http.get(MODULE_CONFIG.GETALLSCHEDULESBYDOCTORUSERNAME(doctorUsername));
	};
	this.getAllSchedulesByRole=function(username){
		return $http.get(MODULE_CONFIG.GET_ALL_SCHEDULES_BY_USER_ROLE(username));
	};
	this.getAllSchedulesByTodayDate = function(scheduleDateDto) {
		return 	$http.post(MODULE_CONFIG.GETALLSCHEDULESBYTODAYDATE(),scheduleDateDto);
	};
	this.searchScheduleDto = function(searchScheduleDto) {
		return 	$http.post(MODULE_CONFIG.SEARCH_SCHEDULES_BY_TODAYDATE(),searchScheduleDto);
	};
	this.searchScheduleDto1 = function(searchScheduleDto) {
		return 	$http.post(MODULE_CONFIG.SEARCH_SCHEDULES_BY_TODAYDATE1(),searchScheduleDto);
	};
	this.findScheduledHours=function(scheduledhourDto){
		return 	$http.post(MODULE_CONFIG.GET_SCHEDULED_HOURS(),scheduledhourDto);	
	};
		
	
	
	
	
}

angular.module('HealthApplication').service('scheduleService', scheduleService);





