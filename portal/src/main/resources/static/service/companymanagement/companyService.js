function companyService(MODULE_CONFIG, PATIENT_MODULE_CONFIG, $http) {
	this.addCompany = function() {
		return $http.post(MODULE_CONFIG.ADD_COMPANY());
	};
	this.getAllCountries = function() {
		return $http.get(MODULE_CONFIG.GETALLCOUNTRIES());
	};
	this.getAllStatesByCountry = function(id) {
		return $http.get(MODULE_CONFIG.GETALLSTATESBYCOUNTRYID(id));
	};
	this.getAllCitiesByState = function(id) {
		return $http.get(MODULE_CONFIG.GETALLCITIESBYSTATEID(id));
	};
	this.getCompanyUser = function(username) {
		return $http.get(MODULE_CONFIG.GET_COMPANY_USER(username));
	};
	this.updateCompanyUser = function(user) {
		return $http.put(MODULE_CONFIG.UPDATE_COMPANY_USER(), user);
	};

	this.uploadImage = function(xlsfile) {
		var fd = new FormData();
		fd.append('file', xlsfile);
		return $http({
			method : 'POST',
			url : PATIENT_MODULE_CONFIG.UPLOADIMAGE(),
			data : fd,
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).success(function(data, status, headers, config) {
			return data;
		}).error(function(data, status, headers, config) {
			return data;

		});
	};
	this.getImagePath = function(profilePicPath) {
		return $http.get(PATIENT_MODULE_CONFIG.VIEW_IMAGE(profilePicPath));
	};
	
	this.getImagePathData = function(username) {
		return $http.get(PATIENT_MODULE_CONFIG.VIEW_BYTE_IMAGE(username));
	};
	
	this.getAllComaniesPage=function(page, size){
		return $http.get(MODULE_CONFIG.GET_ALL_COMANIES_PAGE(page, size));
	};
	this.getStripeInvoice= function(username) {
		return $http.get(MODULE_CONFIG.GET_STRIPE_INVOICE(username));
	};

}

angular.module('HealthApplication').service('companyService', companyService);