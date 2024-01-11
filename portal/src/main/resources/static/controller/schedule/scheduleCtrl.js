//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function scheduleCtrl($scope, $state, scheduleService, $rootScope,$stateParams, successMessageHandler) {
	$scope.page = 0;
	$scope.size = 5;
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
		    
		    $scope.getAllSchedulesByDoctorUsernamePage = function() {
				scheduleService.getAllSchedulesByDoctorUsernamePage($rootScope.loggedUsername,$scope.page,$scope.size).then(function(response) {
					$scope.scheduleLists = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages - 1;
					$scope.noOfPgaes(response.data.totalPages);
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
			};
			
			$scope.getAllSchedulesByDoctorUsername = function() {
				scheduleService.getAllSchedulesByDoctorUsername($rootScope.loggedUsername).then(function(response) {
					$scope.scheduleList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
			};
		  
			$scope.addScheduleObj = function() {
				
				/*if($scope.fromTime.getHours()<10||$scope.toTime.getHours()<10){
				var fromhour="0"+$scope.fromTime.getHours()
				var tohour="0"+$scope.toTime.getHours()
				}*/
				if($scope.scheduleObj.availableDays==""||$scope.scheduleObj.availableDays==undefined){
					toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-right",
							timeOut : 5000
						};
						toastr.error("Please Select Available Days!", 'Error');
				}
				else{
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
		                toastr.success('',successMessageHandler.PATIENT_SCHEDULE_SAVED_SUCCESS);
		               //$scope.scheduleObj=undefined;
		                $scope.getAllSchedulesByDoctorUsernamePage();
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
			}};
			
			$scope.getAllSchedules = function() {
				scheduleService.getAllSchedules().then(function(response) {
					$scope.scheduleList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
			};
			
			$scope.getAllSchedulesByRole = function() {
				scheduleService.getAllSchedulesByRole($rootScope.loggedUsername).then(function(response) {
					$scope.scheduleList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
			};
			
			
			$scope.updateSchedule = function(schedule) {
				$scope.schedule.doctorUsername= $rootScope.loggedUsername;
				scheduleService.updateSchedule($scope.schedule).then(function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-full-width",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.PATIENT_SCHEDULE_UPDATE_SUCCESS);
					$state.go('main.addSchedule');
					
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
			};
			$scope.deleteSchedule = function(id) {
				scheduleService.deleteSchedule(id).then(function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-full-width",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.PATIENT_SCHEDULE_DELETE_SUCCESS);
					$scope.getAllSchedulesByDoctorUsernamePage();
				}, function(error) {
					toastr.options={
							closeButton:true,
							progressBar:true,
							showMethod:'slideDown',
							positionClass:"toast-top-full-width",
							timeOut:2000
					};
					$scope.message = JSON.stringify(error.data.message.trim());
					toastr.error($scope.message, 'Error');
				});
			};
			$scope.gotoList = function() {
				$state.go('main.scheduleList');
			};
			$scope.gotoAdd = function() {
				$state.go('main.addSchedule');
			};
			$scope.gotoback = function() {
				//$state.go('main.addSchedule');
				$scope.fromTime=undefined;
				  $scope.toTime=undefined;
				  $scope.scheduleObj.availableDays=undefined;
			};
			$scope.gotoupdate = function(schedule) {
				$state.go('main.updateSchedule',{
				scheduleObj:	schedule
				});
			};
			
			// Pagination logic

			$scope.noOfPgaes = function(totalpages) {

				$scope.pageList = [];
				for (i = 0; i < totalpages; i++) {
					$scope.pageList.push(i);
				}

			};

			$scope.pageChanged = function(page) {
				$scope.page = page;
			};

			$scope.sizeChanged = function(size) {
				$scope.size = size;
			};
			$scope.firstPage = function() {
				$scope.page = 0;
			};

			$scope.lastPage = function() {
				$scope.page = $scope.totalPages;
			};

			$scope.previousPage = function() {
				if ($scope.page > 0) {
					$scope.page = $scope.page - 1;
				}
			};

			$scope.nextPage = function() {
				if ($scope.page < $scope.totalPages) {
					$scope.page = $scope.page + 1;
				}
			};

			$scope.$watchGroup([ 'size', 'page' ], function(oldSize, oldPage) {
				$scope.getAllSchedulesByDoctorUsernamePage();
			});
			
			// ///////////////////////////////////////////////////////////
}
angular.module("HealthApplication")
		.controller("scheduleCtrl", scheduleCtrl);
