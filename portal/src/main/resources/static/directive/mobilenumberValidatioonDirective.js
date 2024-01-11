// 718 directive for email existence check 
angular.module("HealthApplication").directive('mobileAvailable', function($timeout, $q,$http,MODULE_CONFIG) {
return {
 restrict: 'AE',
 require: 'ngModel',
 link: function(scope, elm, attr, model) { 
   model.$asyncValidators.mobileNumberExists = function(modelValue, viewValue) {
 	 // calling WS to check whether user exists or not
 	 var isMobileNumberExists=false;
 	$http.get(MODULE_CONFIG.IS_MOBILE_NUMBER_EXISTS(modelValue)).
 	 then(function(response) {
			isMobileNumberExists=response.data;
      })
     var defer = $q.defer();
     $timeout(function(){
       model.$setValidity('mobileNumberExists', !isMobileNumberExists); 
       defer.resolve;
     }, 1000);
     return defer.promise;
   };
 }
} 
});