angular.module("HealthApplication").directive('companyAvailable', function($timeout, $q,$http,MODULE_CONFIG) {
return {
 restrict: 'AE',
 require: 'ngModel',
 link: function(scope, elm, attr, model) { 
   model.$asyncValidators.companyExists = function(modelValue, viewValue) {
 	 var iscompanyExists=false;
	    $http.get(MODULE_CONFIG.IS_COMPANY_EXISTS(modelValue)).then(function(response) {
			iscompanyExists=response.data;
      })
     var defer = $q.defer();
     $timeout(function(){
       model.$setValidity('companyExists', !iscompanyExists); 
       defer.resolve;
     }, 1000);
     return defer.promise;
   };
 }
} 
});