function packageDiscountService(MODULE_CONFIG, $http) {
	
	this.addPackageDiscount = function(packageDiscount) {
	return 	$http.post(MODULE_CONFIG.ADD_PACKAGEDISCOUNT(),packageDiscount);
	};
	this.getAllPackageDiscounts=function(){
		return $http.get(MODULE_CONFIG.GETALLPACKAGEDISCOUNT());
	};
	this.updatePackageDiscount=function(packageDiscount){
		return $http.put(MODULE_CONFIG.UPDATEPACKAGEDISCOUNT(),packageDiscount);
	};
	this.deletePackageDiscount=function(id){
		return $http.delete(MODULE_CONFIG.DELETEPACKAGEDISCOUNT(id));
	};
}
angular.module('HealthApplication').service('packageDiscountService', packageDiscountService);