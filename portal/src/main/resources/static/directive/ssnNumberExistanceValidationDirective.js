
// 718 directive for SSN existence check 

angular.module("HealthApplication").directive('ssnAvailable', function($timeout, $q,$http,MODULE_CONFIG) {
return {
 restrict: 'AE',
 require: 'ngModel',
 link: function(scope, elm, attr, model) { 
   model.$asyncValidators.ssnExists = function(modelValue, viewValue) {
 	 // calling WS to check whether user exists or not
 	 var isSSNNumberExists=false;
     $http.get(MODULE_CONFIG.IS_SSN_EXISTS(modelValue)).
 	 then(function(response) {
			isSSNNumberExists=response.data;
      })
     var defer = $q.defer();
     $timeout(function(){
       model.$setValidity('ssnExists', !isSSNNumberExists); 
       defer.resolve;
     }, 1000);
     return defer.promise;
   };
 }
} 
});