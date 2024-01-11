function userManagementSerrvice(MODULE_CONFIG,PATIENT_MODULE_CONFIG, $http) {
	
	this.addUser= function(user) {
	return 	$http.post(MODULE_CONFIG.ADD_USER(),user);
	};
	
	this.addTherapist= function(user) {
		return 	$http.post(MODULE_CONFIG.ADD_USER_THERAPIST(),user);
		};
	this.getAllusers=function(adminUserName){
		return $http.get(MODULE_CONFIG.GETALLUSERS(adminUserName));
	};
	this.getAllusersPage=function(adminUserName,page,size){
		return $http.get(MODULE_CONFIG.GETALLUSERSPAGE(adminUserName,page,size));
	};
	this.getAllusersPageByLoginUser=function(adminUserName,page,size){
		return $http.get(MODULE_CONFIG.GET_ALL_PERSONS_PAGE_BY_LOGIN_USER(adminUserName,page,size));
	};
	
	this.getAllusersPageSearch=function(adminUserName,search,page,size){
		return $http.get(MODULE_CONFIG.GETALLUSERSPAGESEARCH(adminUserName,search,page,size));
	};
	this.updateUser=function(adminUserName,user){
		return $http.put(MODULE_CONFIG.UPDATEUSER(adminUserName),user);
	};
	this.deleteUser=function(adminUserName,id){
		return $http.delete(MODULE_CONFIG.DELETEUSER(adminUserName,id));
	};
	this.getAllRegistrations=function(adminUserName){
		return $http.get(MODULE_CONFIG.GETALLREGISTRATIONS(adminUserName));
	};
	
	this.getAllRegisteredCompanies=function(registrationType){
		return $http.get(MODULE_CONFIG.GET_ALL_REGISTERED_COMPANIES(registrationType));
	};
	
	this.getAllRegisteredCompaniesPage=function(registrationType,page,size){
		/*alert("Service hit")
		alert("Service page:"+page)
		alert("Service size:"+size)
		alert("Service registrationType:"+registrationType)*/
		return $http.get(MODULE_CONFIG.GET_ALL_REGISTERED_COMPANIES_PAGE(registrationType,page,size));
	};
	
	this.getAllusersPageBySuperAdminAndAdmin=function(adminUserName,roleName,active,page,size){
		return $http.get(MODULE_CONFIG.GETALLUSERSPAGEBYSUPERADMINANDADMIN(adminUserName,roleName,active,page,size));
	};
	this.getAllActiveUsersPageBySiteAdmin=function(adminUserName,active,page,size){
		return $http.get(MODULE_CONFIG.GETALLACTIVEUSERSPAGEBYSITEADMIN(adminUserName,active,page,size));
	};
	this.getAllusersPageByTherapist=function(adminUserName,roleName,active,page,size){
		return $http.get(MODULE_CONFIG.GETALLUSERSPAGEBYTHERAPIST(adminUserName,roleName,active,page,size));
	};
	this.findAllTherapistAppointments=function(id,page,size){
		return $http.get(MODULE_CONFIG.FIND_ALL_THERAPIST_APPOINTMENTS(id,page,size));
	};
	this.getAllusersBySuperAdminAndAdmin=function(adminUserName,roleName,active){
		return $http.get(MODULE_CONFIG.GET_ALL_USERS_BY_SUPERADMIN_AND_ADMIN(adminUserName,roleName,active));
	};
	this.active= function(person) {
	return 	$http.post(MODULE_CONFIG.ACTIVE(),person);
	};
		
	this.getRegisteredCompaniesFilter= function(person,page,size) {
	return 	$http.post(MODULE_CONFIG.GET_REGISTERED_COMPANIES_FILTER(page,size),person);
	};
	
	this.getRegisteredCompaniesForStripeInvoice= function(word) {
	return 	$http.get(MODULE_CONFIG.GET_REGISTERED_STRIPE(word));
	};
	
	this.getImagePathDataByUser = function(email) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_USER_IMAGEPATHDATA_THERAPIST_S3(email));
	};
	this.getAllActiveAndInactivePersonsByRegType=function(adminUserName,page,size){
		return $http.get(MODULE_CONFIG.GET_ALL_ACTIVE_AND_INACTIVE_PERSONS_BY_REGTYPE(adminUserName,page,size));
	};
	this.getAllInactivePersonsByRegType=function(adminUserName,page,size){
		return $http.get(MODULE_CONFIG.GET_ALL__INACTIVE_PERSONS_BY_REGTYPE(adminUserName,page,size));
	};
	
	
	
	
}
angular.module('HealthApplication').service('userManagementSerrvice', userManagementSerrvice);