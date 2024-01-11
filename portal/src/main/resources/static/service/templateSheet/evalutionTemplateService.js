function templateService(MODULE_CONFIG, $http) {
	this.addtemplate = function(template) {
	return 	$http.post(MODULE_CONFIG.ADDTEMPLATE(),template);
	};
	this.getAllTemplates=function(){
		return $http.get(MODULE_CONFIG.GETALLTEMPLATES());
	};
	
	this.deleteTemplate=function(id){
		return $http.delete(MODULE_CONFIG.DELETETEMPLATE(id));
	};
	this.addanswer = function(anser) {
		return 	$http.post(MODULE_CONFIG.ADDANSWER(),anser);
		};
}

angular.module('HealthApplication').service('templateService', templateService);