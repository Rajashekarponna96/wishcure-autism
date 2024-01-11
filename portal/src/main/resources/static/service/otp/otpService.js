function otpService(MODULE_CONFIG, $http) {
	
	this.validateOtp = function(otp) {
	return 	$http.post(MODULE_CONFIG.VALIDATE_OTP(),otp);
	};
	
	this.resendOTP = function(email) {
		return 	$http.post(MODULE_CONFIG.RESEND_OTP(email));
	};
	
	this.otpExpireTime = function(email) {
		return 	$http.post(MODULE_CONFIG.OTP_EXPIRE_TIME(email));
	};	
}

angular.module('HealthApplication').service('otpService', otpService);