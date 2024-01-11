function vsmsClusterService($http, PATIENT_MODULE_CONFIG) {
	
	this.addVSMSCluster = function(vsmsCluster) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_VSMS_CLUSTER(),
				vsmsCluster);
	}
	
	this.getVSMSClusters=function(patientId){
		return $http.get(PATIENT_MODULE_CONFIG.GET_VSMS_CLUSTERS(patientId));
	}
	
	this.updateVSMSCluster=function(vsmsCluster){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_VSMS_CLUSTER(),
				vsmsCluster);
	}
	
	this.deleteVSMSCluster=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_VSMS_CLUSTER(id));
	}

}
angular.module('HealthApplication').service("vsmsClusterService",
		vsmsClusterService);