function packagePricingService(MODULE_CONFIG, $http) {
	this.addPackagePricing = function(packagePricing) {
	return 	$http.post(MODULE_CONFIG.ADDPACKAGEPRICING(),packagePricing);
	};
	this.getAllPackagePricings=function(page, size){
		return $http.get(MODULE_CONFIG.GETALLPACKAGEPRICINGS(page, size));
	};
	this.updatePackagePricing=function(packagePricing){
		return $http.put(MODULE_CONFIG.UPDATEPACKAGEPRICING(),packagePricing);
	};
	this.deletePackagePricing=function(id){
		return $http.delete(MODULE_CONFIG.DELETEPACKAGEPRICING(id));
	};
	this.getPackagePriceMaster=function(id){
		return $http.get(MODULE_CONFIG.GETPACKAGEPRICING(id));
	};
	this.getPackagePriceMatserInfo=function(subscriptionName, packagename){
		return $http.get(MODULE_CONFIG.GET_PACKAGEPRICEMASTER_INFO(subscriptionName, packagename));
	};
	
	
	
}

angular.module('HealthApplication').service('packagePricingService', packagePricingService);