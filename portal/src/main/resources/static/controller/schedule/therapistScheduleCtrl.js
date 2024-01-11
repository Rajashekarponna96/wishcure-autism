//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function therapistScheduleCtrl($scope, $state, therapistScheduleService,therapistService, $rootScope,$stateParams, successMessageHandler) {
	
	$scope.scheduleObj={
	}
 $scope.schedule=$stateParams.scheduleObj;
	
			$scope.days = ['SUNDAY','MONDAY' ,'TUESDAY' ,'WEDNESDAY' ,'THURSDAY' ,'FRIDAY', 'SATURDAY'];
		    $scope.searchTerm;
		    $scope.clearSearchTerm = function() {
		      $scope.searchTerm = '';
		    };
		  
		    $scope.arrayToString = function(string){
		        return string.join(", ");
		    };
		  
		/*	$scope.addScheduleObj = function() {
				
				$scope.scheduleObj.doctorUsername= $rootScope.loggedUsername;
				
				therapistScheduleService.addSchedule($scope.scheduleObj).then(function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('','Schedule Saved Successfully');
		                $scope.scheduleObj=undefined;
		                $scope.getAllTherapistsSchedulesByAdminUsername();
		                $state.go('main.scheduleListAdmin');
				}, function(error) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',

		                    positionClass : "toast-top-right",

		                    timeOut: 2000
		                };
					$scope.message = JSON.stringify(error.data.message.trim());
					toastr.error($scope.message, 'Error');
		                
					
				});
			};
*/		
		    
$scope.addScheduleObj = function() {
				
				/*if($scope.fromTime.getHours()<10||$scope.toTime.getHours()<10){
				var fromhour="0"+$scope.fromTime.getHours()
				var tohour="0"+$scope.toTime.getHours()
				}*/
				
				
				if($scope.fromTime.getHours()<10 && $scope.fromTime.getMinutes()<10){
					$scope.scheduleObj.fromTime="0"+$scope.fromTime.getHours()+":0"+$scope.fromTime.getMinutes()+":"+"00";
					}
				
				if($scope.fromTime.getHours()<10&& $scope.fromTime.getMinutes()>=10){
					$scope.scheduleObj.fromTime="0"+$scope.fromTime.getHours()+":"+$scope.fromTime.getMinutes()+":"+"00";
					}
				
				if($scope.fromTime.getHours()>=10 && $scope.fromTime.getMinutes()<10){
					$scope.scheduleObj.fromTime=$scope.fromTime.getHours()+":0"+$scope.fromTime.getMinutes()+":"+"00";
					}
				
				if($scope.fromTime.getHours()>=10 && $scope.fromTime.getMinutes()>=10){
					$scope.scheduleObj.fromTime=$scope.fromTime.getHours()+":"+$scope.fromTime.getMinutes()+":"+"00";
					}
				
				/////////////
				if($scope.toTime.getHours()<10&& $scope.toTime.getMinutes()<10){
					$scope.scheduleObj.toTime="0"+$scope.toTime.getHours()+":0"+$scope.toTime.getMinutes()+":"+"00";
					}
				
				if($scope.toTime.getHours()<10&& $scope.toTime.getMinutes()>=10){
					$scope.scheduleObj.toTime="0"+$scope.toTime.getHours()+":"+$scope.toTime.getMinutes()+":"+"00";
					}
				
				if($scope.toTime.getHours()>=10 && $scope.toTime.getMinutes()<10){
					$scope.scheduleObj.toTime=$scope.toTime.getHours()+":0"+$scope.toTime.getMinutes()+":"+"00";
					}
				
				if($scope.toTime.getHours()>=10 && $scope.toTime.getMinutes()>=10){
					$scope.scheduleObj.toTime=$scope.toTime.getHours()+":"+$scope.toTime.getMinutes()+":"+"00";
					}
				/////////////
				
				
				
				
				
				/*if($scope.fromTime.getMinutes()<10||$scope.toTime.getMinutes()<10){
					var fromminutes="0"+$scope.fromTime.getMinutes()
					var tominutes="0"+$scope.toTime.getMinutes
				}
				
				$scope.scheduleObj.fromTime=fromhour+":"+fromminutes+":"+"00";
				$scope.scheduleObj.toTime=tohour+":"+tominutes+":"+"00";
				else{
			$scope.scheduleObj.fromTime=$scope.fromTime.getHours()+":"+$scope.fromTime.getMinutes()+":"+"00";
			$scope.scheduleObj.toTime=$scope.toTime.getHours()+":"+$scope.toTime.getMinutes()+":"+"00";
				}	
				
				*/
				
				
				/*$scope.scheduleObj.fromTime=fromhour+":"+fromminutes+":"+"00";
				$scope.scheduleObj.toTime=tohour+":"+tominutes+":"+"00";*/
				
				
				$scope.scheduleObj.doctorUsername= $rootScope.loggedUsername;
			//	alert(JSON.stringify($scope.scheduleObj));
				scheduleService.addSchedule($scope.scheduleObj).then(function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
					  $scope.fromTime=undefined;
					  $scope.toTime=undefined;
					  $scope.scheduleObj.availableDays=undefined;
		                toastr.success('',successMessageHandler.THERAPIST_SCHEDULE_SAVE_SUCCESS);
		               //$scope.scheduleObj=undefined;
		                //$scope.getAllSchedulesByDoctorUsername();
		                $state.go('main.scheduleListAdmin');
				}, function(error) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
					$scope.message = JSON.stringify(error.data.message.trim());
					toastr.error($scope.message, 'Error');
		                
					
				});
			};
			
			
			
			$scope.addScheduleByAdmin = function() {
				
				if($scope.fromTime.getHours()<10 && $scope.fromTime.getMinutes()<10){
					$scope.scheduleObj.fromTime="0"+$scope.fromTime.getHours()+":0"+$scope.fromTime.getMinutes()+":"+"00";
					}
				
				if($scope.fromTime.getHours()<10&& $scope.fromTime.getMinutes()>=10){
					$scope.scheduleObj.fromTime="0"+$scope.fromTime.getHours()+":"+$scope.fromTime.getMinutes()+":"+"00";
					}
				
				if($scope.fromTime.getHours()>=10 && $scope.fromTime.getMinutes()<10){
					$scope.scheduleObj.fromTime=$scope.fromTime.getHours()+":0"+$scope.fromTime.getMinutes()+":"+"00";
					}
				
				if($scope.fromTime.getHours()>=10 && $scope.fromTime.getMinutes()>=10){
					$scope.scheduleObj.fromTime=$scope.fromTime.getHours()+":"+$scope.fromTime.getMinutes()+":"+"00";
					}
				
				/////////////
				if($scope.toTime.getHours()<10&& $scope.toTime.getMinutes()<10){
					$scope.scheduleObj.toTime="0"+$scope.toTime.getHours()+":0"+$scope.toTime.getMinutes()+":"+"00";
					}
				
				if($scope.toTime.getHours()<10&& $scope.toTime.getMinutes()>=10){
					$scope.scheduleObj.toTime="0"+$scope.toTime.getHours()+":"+$scope.toTime.getMinutes()+":"+"00";
					}
				
				if($scope.toTime.getHours()>=10 && $scope.toTime.getMinutes()<10){
					$scope.scheduleObj.toTime=$scope.toTime.getHours()+":0"+$scope.toTime.getMinutes()+":"+"00";
					}
				
				if($scope.toTime.getHours()>=10 && $scope.toTime.getMinutes()>=10){
					$scope.scheduleObj.toTime=$scope.toTime.getHours()+":"+$scope.toTime.getMinutes()+":"+"00";
					}
				/////////////
				
				$scope.scheduleObj.doctorUsername= $scope.therapist.email;
				therapistScheduleService.addSchedule($scope.scheduleObj).then(function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
					$scope.fromTime=undefined;
					  $scope.toTime=undefined;
					  $scope.scheduleObj.availableDays=undefined;
		                toastr.success('',successMessageHandler.THERAPIST_SCHEDULE_SAVE_SUCCESS);
		               /* $scope.scheduleObj=undefined;
		                $scope.getAllSchedulesByDoctorUsername();*/
		                $state.go('main.scheduleListAdmin');
				}, function(error) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',

		                    positionClass : "toast-top-right",

		                    timeOut: 2000
		                };
					$scope.message = JSON.stringify(error.data.message.trim());
					toastr.error($scope.message, 'Error');
		                
					
				});
			};
			
			
			$scope.getAllTherapists= function() {
				$scope.adminUsername=$rootScope.loggedUsername;
				therapistService.getAllTherapists($scope.adminUsername).then(function(response) {
					$scope.therapistList = response.data;
				
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
			};
			
		/*	$scope.getAllSchedulesByDoctorUsername = function() {
				therapistScheduleService.getAllSchedulesByDoctorUsername($rootScope.loggedUsername).then(function(response) {
					$scope.scheduleList = response.data;
					
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
			};
			*/
			
			$scope.getAllTherapistsSchedulesByRole = function() {
				therapistScheduleService.getAllTherapistsSchedulesByRole($rootScope.loggedUsername).then(function(response) {
					$scope.scheduleListAdmin = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
			};
			
			
			
		/*	$scope.getAllSchedules = function() {
				therapistScheduleService.getAllSchedules().then(function(response) {
					$scope.scheduleList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
			};*/
			
			/*$scope.updateSchedule = function(schedule) {
				$scope.schedule.doctorUsername= $rootScope.loggedUsername;
				therapistScheduleService.updateSchedule($scope.schedule).then(function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-full-width",
		                    timeOut: 2000
		                };
		                toastr.success('',' Data updated Successfully');
					$state.go('main.addSchedule');
					
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
			};*/
			$scope.deleteSchedule = function(id) {
				therapistScheduleService.deleteSchedule(id).then(function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-full-width",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.THERAPIST_SCHEDULE_DELETE_SUCCESS);
					$scope.getAllTherapistsSchedulesByRole();
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
			};
			$scope.gotoList = function() {
				$state.go('main.scheduleListAdmin');
			};
			$scope.gotoAdd = function() {
				$state.go('main.addTherapistSchedule');
			};
			$scope.gotoback = function() {
				$state.go('main.addTherapistSchedule');
			};
			$scope.gotoupdate = function(schedule) {
				$state.go('main.updateSchedule',{
				scheduleObj:	schedule
				});
			};
}
angular.module("HealthApplication")
		.controller("therapistScheduleCtrl", therapistScheduleCtrl);
