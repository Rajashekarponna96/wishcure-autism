
// 718 directive for npi existence check 

angular.module("HealthApplication").directive('npiAvailable', function($timeout, $q,$http,MODULE_CONFIG) {
return {
 restrict: 'AE',
 require: 'ngModel',
 link: function(scope, elm, attr, model) { 
   model.$asyncValidators.npiExists = function(modelValue, viewValue) {
 	 // calling WS to check whether user exists or not
 	 var isNpiNumberExists=false;
	$http.get(MODULE_CONFIG.IS_NPI_EXISTS(modelValue)).
 	 then(function(response) {
			isNpiNumberExists=response.data;
      })
     var defer = $q.defer();
     $timeout(function(){
       model.$setValidity('npiExists', !isNpiNumberExists); 
       defer.resolve;
     }, 1000);
     return defer.promise;
   };
 }
} 
});