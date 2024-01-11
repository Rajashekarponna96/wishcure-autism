
// 718 directive for uci existence check 

angular.module("HealthApplication").directive('uciAvailable', function($timeout, $q,$http,MODULE_CONFIG) {
return {
 restrict: 'AE',
 require: 'ngModel',
 link: function(scope, elm, attr, model) { 
   model.$asyncValidators.uciExists = function(modelValue, viewValue) {
 	 // calling WS to check whether user exists or not
 	 var isUCINumberExists=false;
 	  $http.get(MODULE_CONFIG.IS_UCI_EXISTS(modelValue)).
 	 then(function(response) {
			isUCINumberExists=response.data;
      })
     var defer = $q.defer();
     $timeout(function(){
       model.$setValidity('uciExists', !isUCINumberExists); 
       defer.resolve;
     }, 1000);
     return defer.promise;
   };
 }
} 
});