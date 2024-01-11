
function patientIsaaBehaviourCategoryService($http,PATIENT_MODULE_CONFIG){
	this.addIsaaBehaviourtemplate= function(ISAABehaviorObjectDto){
		return $http.post(PATIENT_MODULE_CONFIG.ADD_ISAABEHAVIOUR_TEMPLATE(),
				ISAABehaviorObjectDto);
	}
	/*this.getAllNichqTeacherByPatientId= function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_NICHQ_TEACHER_TEMPLATE_TO_THE_PARENT(id));
	}*/
	this.getIsaaBehaviourResultByPatientId=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ISAA_BEHAVIOUR_RESULT_BY_PATIENT_ID(id));
	};

}
angular.module("HealthApplication").service("patientIsaaBehaviourCategoryService",
		patientIsaaBehaviourCategoryService);