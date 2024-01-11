function NichqParentService($http,PATIENT_MODULE_CONFIG) {
	this.addNichqParent= function(Nichqparent){
		return $http.post(PATIENT_MODULE_CONFIG.ADD_NICHQ_PARENT(),
				Nichqparent);
	}
	this.getNichqparent=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_NICHQ_PARENT());
	}
	this.deleteNichqparent=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_NICHQ_PARENT(id));
	}
	this.updateNichqparent=function(nichqobject){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_NICHQ_PARENT(),nichqobject);
	}
}
angular.module("HealthApplication").service("nichqservice",
		NichqParentService);