//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function otpCtrl($scope, $state, otpService, $stateParams, successMessageHandler) {
	$scope.user = $stateParams.UserObject;
	$scope.otpDto = {
	}
	$scope.validateOtp = function() {
		$scope.otpDto.email=$scope.user.userName;
		otpService.validateOtp($scope.otpDto).then(function(response) {/*
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 2000
				};
				toastr.success('', successMessageHandler.OTP_LOGIN_SUCCESS);
				$state.go('main.dashboard');

		*/
			$state.go('main.dashboard');
			}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 5000
                };
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.error($scope.message, 'Warning');
		});
	};
	
	$scope.resndOTP=function(){
		
		toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 4000
			};
			toastr.success('',successMessageHandler.OTP_LOADING_SUCCESS);
		otpService.resendOTP($scope.user.userName).then(function(response) {
			$scope.timerForOtpExpires();
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 2000
				};
				toastr.success('',successMessageHandler.LOGIN_OTP_SUCCESS);
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 5000
                };
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.error($scope.message, 'Warning');
		});
	};
	
	
	$scope.timerForOtpExpires = function() {
		otpService.otpExpireTime($scope.user.userName).then(
				function(response) {
					$scope.expTime = response.data;
					clearInterval($scope.x);
					// Set the date we're counting down to
					var dateTimeExp=$scope.expTime.resetTokenExpires.month+" "+$scope.expTime.resetTokenExpires.dayOfMonth+", "+$scope.expTime.resetTokenExpires.year+" "+$scope.expTime.resetTokenExpires.hour+":"+$scope.expTime.resetTokenExpires.minute+":"+$scope.expTime.resetTokenExpires.second;
					var countDownDate = new Date(dateTimeExp);
					// Update the count down every 1 second
					$scope.x = setInterval(function() {
					    // Get todays date and time
					    var now = new Date();
						if(now.getTimezoneOffset()<=0){
							//-
							now.setMinutes(now.getMinutes() + now.getTimezoneOffset());
						}else{
							//+
							now.setMinutes(now.getMinutes() + now.getTimezoneOffset());
						}
					    // Find the distance between now and the count down date
					    var distance = countDownDate - now;
					    
					    // Time calculations for days, hours, minutes and seconds
					    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
					    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
					    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
					    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
					    
					    // Output the result in an element with id="demo"
					   // document.getElementById("demo").innerHTML ="Expires in "+ minutes + "m " + seconds + "s ";
					    
					    // If the count down is over, write some text 
					    if (distance < 0) {
					        clearInterval($scope.x);
					        document.getElementById("demo").innerHTML = "EXPIRED";
					    }
					}, 1000);
				
					
				}, function(error) {
				});
	};
}
angular.module("HealthApplication").controller("otpCtrl", otpCtrl);
