function patientRegTypeService(PATIENT_MODULE_CONFIG, $http){
		
		this.addPatientRegtype = function(patientRegtype) {
		return 	$http.post(PATIENT_MODULE_CONFIG.ADD_PATIENT_REGISTRATIONTYPE(), patientRegtype);
		};
		this.getAllPatientRegTypes=function(){
			return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_PATIENT_REEGISTRATION_TYPES());
		};
		this.deleteRegistrationType=function(id){
			return $http.delete(PATIENT_MODULE_CONFIG.DELETE_PATIENT_REGISTRATION_TYPE(id));
		};
		this.updatePatientRegType=function(pRegType){
			return $http.put(PATIENT_MODULE_CONFIG.UPDATE_PATIENT_REGISTRATION_TYPE(), pRegType);
		};
		/* this.getAllCategoryTypeByStatus=function(){
			//alert("called service")
			return $http.get(PATIENT_MODULE_CONFIG.GETALLCATEGORYTYPEBYSTATUS());
		};*/
}

angular.module('HealthApplication').service('patientRegTypeService', patientRegTypeService);