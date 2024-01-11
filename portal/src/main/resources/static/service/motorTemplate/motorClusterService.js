function motorClusterService($http, PATIENT_MODULE_CONFIG) {
	this.addMotorCluster = function(motorCluster) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_MOTOR_CLUSTER(),
				motorCluster);
	}
	this.getMotorClusters=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_MOTOR_CLUSTERS());
	}
	this.updateMotorCluster=function(motorCluster){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_MOTOR_CLUSTER(),
				motorCluster);
	}
	this.deleteMotorCluster=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_MOTOR_CLUSTER(id));
	}

}
angular.module('HealthApplication').service("motorClusterService",
		motorClusterService);