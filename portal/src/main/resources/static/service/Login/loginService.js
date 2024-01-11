function loginService(MODULE_CONFIG,PATIENT_MODULE_CONFIG, $http) {
	this.doLogin = function(user) {
	return 	$http.post(MODULE_CONFIG.LOGIN(), user);
	};
	this.getActiveDoctorsChartDataPerPresentYear=function(adminUserName,year){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ACTIVE_DOCTORS_PER_PRESENT_YEAR(adminUserName,year));
	};
	this.getActiveUsersChartDataPerPresentYear=function(adminUserName,year){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ACTIVE_USERS_PER_PRESENT_YEAR(adminUserName,year));
	};
	this.getTotalPatientsRegisteredChartDataPerPresentYear=function(adminUserName,year){
		return $http.get(PATIENT_MODULE_CONFIG.GET_TOTAL_PATIENTS_PER_PRESENT_YEAR(adminUserName,year));
	};
	this.getTodayPatientsData=function(adminUserName){
		return $http.get(PATIENT_MODULE_CONFIG.GET_TODAY_PATIENTS_DATA(adminUserName));
	};
	this.getAllactiveAdmins=function(adminUserName,loggedUserRole){
		return $http.get(MODULE_CONFIG.GET_ALL_ACTIVE_ADMINS(adminUserName,loggedUserRole));
	};
	this.getAllInactiveAdmins=function(adminUserName){
		return $http.get(MODULE_CONFIG.GET_ALL_INACTIVE_ADMINS(adminUserName));
	};
	this.getAllactiveSiteAdmins=function(adminUserName){
		return $http.get(MODULE_CONFIG.GET_ALL_ACTIVE_SITEADMIN(adminUserName));
	};
	this.getAllInactiveSiteAdmins=function(adminUserName){
		return $http.get(MODULE_CONFIG.GET_ALL_INACTIVE_SITEADMIN(adminUserName));
	};
	this.getAllAppointmentsCountByRole=function(adminUserName){
		return $http.get(MODULE_CONFIG.GET_APPOINTMENTS_COUNT_BY_ROLE(adminUserName));
	};
	this.getImageFromS3=function(adminUserName){
		return $http.get(PATIENT_MODULE_CONFIG.GET_USER_IMAGEPATHDATA_S3(adminUserName));
	};
	this.getAllActivepersonsForInternalDashboard=function(adminUserName){
		return $http.get(MODULE_CONFIG.GET_ALL_ACTIVE_PERSONS_FOR_INTERNAL_DASHBOARD(adminUserName));
	};
	
	this.getAllInactivepersonsForInternalDashboard=function(adminUserName){
		return $http.get(MODULE_CONFIG.GET_ALL_INACTIVE_PERSONS_FOR_INTERNAL_DASHBOARD(adminUserName));
	};
}

angular.module('HealthApplication').service('loginService', loginService);