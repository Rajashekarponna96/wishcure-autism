function MentalClusterService(MODULE_CONFIG,PATIENT_MODULE_CONFIG,$http) {
	
	
	this.addMentalCluster = function(cluster) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADD_MENTAL_CLUSTER(),cluster);
	};
	this.getAllMentalCluster=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_MENTAL_CLUSTER());
	};
	this.updateMentalCluster=function(cluster){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_MENTAL_CLUSTER(),cluster);
	};
	this.deletementalCluster=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_MENTAL_CLUSTER(id));
	};
}

angular.module('HealthApplication').service('MentalClusterService', MentalClusterService);