function countryService(MODULE_CONFIG, $http) {
	this.getAllCountries = function() {
		return $http.get(MODULE_CONFIG.GETALLCOUNTRIES());
	};
	this.getAllStatesByCountryId = function(id) {
		return $http.get(MODULE_CONFIG.GETALLSTATESBYCOUNTRYID(id));
	};
	this.getAllCitiesByStateId=function(id){
		return $http.get(MODULE_CONFIG.GETALLCITIESBYSTATEID(id));
	};
	this.getCountrysBasedOnIsdCode = function() {
		return $http.get(MODULE_CONFIG.GETCOUNTRYBASEDONISDCODE());
	};
	this.getStateByPatientId=function(stateId){
		return $http.get(MODULE_CONFIG.GET_ONE_STATE_BY_ID(stateId));
	};
	
	
	
}

angular.module("HealthApplication").service("countryService", countryService);
