function motorScaleService($http,PATIENT_MODULE_CONFIG) {

	this.addMotorScale = function(motorScale) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_MOTOR_SCALE(),
				motorScale);
	}
	this.getMotorScales=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_MOTOR_SCALES());
	}
	this.updateMotorScale=function(motorScale){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_MOTOR_SCALE(),
				motorScale);
	}
	this.deleteMotorScale=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_MOTOR_SCALE(id));
	}

	this.assignMotorScaleToCluster=function(assignedScales,clusterId){
		return $http.post(PATIENT_MODULE_CONFIG.ASSIGN_MOTOR_SCALE_TO_MOTORCLUSTER(clusterId),assignedScales);
	}
	this.assignMotorScaleTemplateToPatient=function(motorScaleTemplateDto){
		return $http.post(PATIENT_MODULE_CONFIG.ASSING_MOTOR_TEMPLATE_TO_PATIENT_MOTOR_SCALES(),motorScaleTemplateDto)
	}
	
}
angular.module('HealthApplication')
		.service("motorScaleService", motorScaleService);