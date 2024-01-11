function forgotpasswordService(MODULE_CONFIG, $http) {
    this.doForgotPassword = function(email) {
      return $http.get(MODULE_CONFIG.FORGOTPASSWORD(email));
    };
  }
  
  angular.module("HealthApplication").service("forgotpasswordService", forgotpasswordService);
  