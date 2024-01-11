// 718 Dumps Controller start
angular
		.module("app")
		.controller(
				"changePasswordController",
				function($scope, $http, $filter, $uibModal, $rootScope,$location) {
					
					$scope.gotoChangePassword=function(){
				          $.post("http://localhost:8080/healthapp/updatePassword", { "username":$rootScope.loggedUserName,
				        	  "oldpassword": $scope.oldpassword,"newpassword":$scope.newpassword,"confirmpassword":$scope.confirmpassword}, 
				          
							       function(returnedData){
							        // $rootScope.loggedUsername=returnedData;
							         
							         $scope.$apply(function() { $location.path('/app/dashboard/dashboard');  });
							         alert("Your Password has been changed Succesfully!!")
							        
							}).fail(function(error){
							      alert("Invalid Password!!")
							});
					 
				         
				        /* $("#label_email,#label_password").text("");
				         if ($.trim($("#txt_email").val()) == "") {
				             $("#label_email").text("Please enter email");
				         }
				         if ($.trim($("#txt_password").val()) == "") {
				             $("#label_password").text("Please enter password");
				         }*/
				                
				            
						
					}
					
					
				});
