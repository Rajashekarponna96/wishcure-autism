function appointmentService(MODULE_CONFIG, $http) {
	
	this.addAppointment = function(appointment) {
	return 	$http.post(MODULE_CONFIG.ADDAPPOINTMENT(),appointment);
	};
	this.getAllTherpistByDepartment=function(id){
		return $http.get(MODULE_CONFIG.GETALLTHERAPISTSBYDEPARTMENT(id));
	};
	this.getAppointmentssByRole=function(adminUserName){
		return $http.get(MODULE_CONFIG.GET_APPOINTS_BY_ROLE(adminUserName));
	};
	this.getAppointmentssByRoleByPagination=function(adminUserName, page, size){
		return $http.get(MODULE_CONFIG.GET_APPOINTS_BY_ROLE_PAGE(adminUserName, page, size));
	};
	this.updateAppointment = function(appointment) {
		return 	$http.put(MODULE_CONFIG.UPDATEAPPOINTMENT(),appointment);
    };
    this.dropAppointment=function(id){
		return $http.delete(MODULE_CONFIG.DELETEAPPOINTMENT(id));
	};
	this.getListBetweenDates = function(appointments,page,size) {
		return 	$http.post(MODULE_CONFIG.GETAPPOINTMENTLISTBYBETWEENDATES( page, size),appointments);
    };
    
    this.getSubAppointmentssByRole=function(adminUserName){
		return $http.get(MODULE_CONFIG.GET_ALL_SUB_APPOINTS_BY_ROLE(adminUserName));
	};
	this.getSubAppointmentssByRoleforCalendarView=function(adminUserName){
		return $http.get(MODULE_CONFIG.GET_ALL_SUB_APPOINTS_BY_ROLE_FOR_CALENDARVIEW(adminUserName));
	};
	this.getTodaySubAppointments=function(name){
		return $http.get(MODULE_CONFIG.GET_TODAY_SUBAPPOINTMENTS(name));
	};
	this.updateSubappointmentStatus=function(id,status){
		return $http.post(MODULE_CONFIG.UPDATESUBAPPOINTMENTSTATUS(id,status));
	};
	this.getSubAppointmentsByInvoice = function(appointment) {
		return 	$http.post(MODULE_CONFIG.GETSUBAPPOINTMENTS_BYINVOICE(),appointment);
	};
    
	this.getAppointmentssByAdminByPagination=function(adminUserName, page, size){
		return $http.get(MODULE_CONFIG.GET_APPOINTS_BY_ADMIN_PAGE(adminUserName, page, size));
	};
	
	this.getAllSubAppointmentsByAppointment=function(id){
		return $http.get(MODULE_CONFIG.GET_ALL_SUBAPPOINTMENTS_BY_APPOINTMENT(id));
	};
	
	this.getSubAppointmentsByInvoiceNo=function(itemDto){
		return $http.post(MODULE_CONFIG.GET_SUBAPPOINTMENTS_BY_INVOICENO(),itemDto);
	};
	this.updateAppointmentWithAssignedTherapist=function(scheduleDtoForCalendar){
		return $http.post(MODULE_CONFIG.UPDATE_APPOINTMENT_WITH_ASSIGNEDTHERAPIST(),scheduleDtoForCalendar);
	};
	this.getSubAppointmentsByInvoicePage=function(appointment,page, size) {
		return 	$http.post(MODULE_CONFIG.GETSUBAPPOINTMENTS_BYINVOICE_PAGE(page, size),appointment);
	};
}

angular.module('HealthApplication').service('appointmentService', appointmentService);