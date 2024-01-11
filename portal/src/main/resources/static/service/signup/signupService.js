function signupService(MODULE_CONFIG, $http) {
   this.doSignUp = function(user) {
    return $http.post(MODULE_CONFIG.DOSIGNUP(), user);
  };
}

angular.module("HealthApplication").service("signupService", signupService);
