function packageMasterService(MODULE_CONFIG, $http) {
	
	this.addPackageMaster = function(packageMaster) {
	return 	$http.post(MODULE_CONFIG.ADD_PACKAGEMASTER(),packageMaster);
	};
	this.getAllPackageMAsters=function(page, size){
		return $http.get(MODULE_CONFIG.GETALLPACKAGEMASTER(page, size));
	};
	this.updatepackageNameMaster=function(packageMaster){
		return $http.put(MODULE_CONFIG.UPDATEPACKAGEMASTER(),packageMaster);
	};
	this.deletepackageNameMaster=function(id){
		return $http.delete(MODULE_CONFIG.DELETEPACKAGEMASTER(id));
	};
	this.getAllPackageMAstersList=function(){
		return $http.get(MODULE_CONFIG.GETALLPACKAGEMASTERSLIST());
	};
	
}
angular.module('HealthApplication').service('packageMasterService', packageMasterService);