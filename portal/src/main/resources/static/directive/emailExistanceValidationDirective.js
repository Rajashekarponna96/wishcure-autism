
// 718 directive for email existence check 

angular.module("HealthApplication").directive('emailAvailable', function($timeout, $q,$http,MODULE_CONFIG) {
return {
 restrict: 'AE',
 require: 'ngModel',
 link: function(scope, elm, attr, model) { 
   model.$asyncValidators.emailExists = function(modelValue, viewValue) {
 	 // calling WS to check whether user exists or not
 	 var isEmailIdExists=false;
	    $http.get(MODULE_CONFIG.IS_EMAIL_EXISTS(modelValue)).
      then(function(response) {
			isEmailIdExists=response.data;
      })
     var defer = $q.defer();
     $timeout(function(){
       model.$setValidity('emailExists', !isEmailIdExists); 
       defer.resolve;
     }, 1000);
     return defer.promise;
   };
 }
} 
});