function documentTypeLookupService(PATIENT_MODULE_CONFIG, $http) {
	this.addDocumentType=function(document){
		return $http.post(PATIENT_MODULE_CONFIG.ADD_DOCUMENT_TYPE(),document);
	};
	this.getAllDocumentTypes=function(page,size){
		return $http.get(PATIENT_MODULE_CONFIG.GETALL_DOCUMENT_TYPES(page,size));
	};
	this.updateDocumentType=function(document){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_DOCUMENT_TYPE(),document);
	};
	this.deleteDocumentType=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_DOCUMENT_TYPE(id));
	};
}

angular.module('HealthApplication').service('documentTypeLookupService', documentTypeLookupService);