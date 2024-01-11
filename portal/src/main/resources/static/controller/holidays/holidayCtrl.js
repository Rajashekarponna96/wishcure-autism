//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function holidaysCtrl($scope, $state, holidaysService, $rootScope,$stateParams, successMessageHandler) {
	$scope.page = 0;
	$scope.size = 5;
	$scope.holidays={};
	$scope.color="red";
	$scope. holidays=$stateParams. holidaysObj;
	$scope.addHolidays = function( holidays) {
		
		var n=$scope.holidays.fromTime;
		
		if(n.getMonth()>8&&n.getDate()>9){
			var appdate=n.getFullYear()+"-"+(n.getMonth()+1)+"-"+n.getDate();
		}else if(n.getMonth()<9&&n.getDate()<10){
			var appdate=n.getFullYear()+"-0"+(n.getMonth()+1)+"-0"+n.getDate();
		}
		else if(n.getMonth()<9&&n.getDate()>9){
			var appdate=n.getFullYear()+"-0"+(n.getMonth()+1)+"-"+n.getDate();
		}else{
		var appdate=n.getFullYear()+"-"+(n.getMonth()+1)+"-0"+n.getDate();
		}
		
		if(n.getHours()>9&&n.getMinutes()>9){
			var apptime=n.getHours()+":"+n.getMinutes()+":00";
			}else if(n.getHours()<10&&n.getMinutes()<10){
				var apptime="0"+n.getHours()+":0"+n.getMinutes()+":00";
			}else if(n.getHours()<10&&n.getMinutes()>9){
				var apptime="0"+n.getHours()+":"+n.getMinutes()+":00";
			}else{
				var apptime=n.getHours()+":0"+n.getMinutes()+":00";
			}
		alert("fromTime:"+$scope.holidays.fromTime)
		alert($scope.holidays.fromTime.getTimezoneOffset())
		//For EC2
		if($scope.holidays.fromTime.getTimezoneOffset()<=0){
			$scope.holidays.fromTime=$scope.holidays.fromTime.setMinutes($scope.holidays.fromTime.getMinutes() - $scope.holidays.fromTime.getTimezoneOffset());
			$scope.holidays.toTime=$scope.holidays.toTime.setMinutes($scope.holidays.toTime.getMinutes() - $scope.holidays.toTime.getTimezoneOffset());
		}else{
			$scope.holidays.fromTime=$scope.holidays.fromTime.setMinutes($scope.holidays.fromTime.getMinutes() - $scope.holidays.fromTime.getTimezoneOffset());
			$scope.holidays.toTime=$scope.holidays.toTime.setMinutes($scope.holidays.toTime.getMinutes() - $scope.holidays.toTime.getTimezoneOffset());
		}
		
		var d=new Date();
		
		if(d.getMonth()>8&&d.getDate()>9){
			var date=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		}else if(d.getMonth()<9&&d.getDate()<10){
			var date=d.getFullYear()+"-0"+(d.getMonth()+1)+"-0"+d.getDate();
		}
		else if(d.getMonth()<9&&d.getDate()>9){
			var date=d.getFullYear()+"-0"+(d.getMonth()+1)+"-"+d.getDate();
		}else{
		var date=d.getFullYear()+"-"+(d.getMonth()+1)+"-0"+d.getDate();
		}
		
		if(d.getHours()>9&&d.getMinutes()>9){
		var time=d.getHours()+":"+d.getMinutes()+":00";
		}else if(d.getHours()<10&&d.getMinutes()<10){
			var time="0"+d.getHours()+":0"+d.getMinutes()+":00";
		}else if(d.getHours()<10&&d.getMinutes()>9){
			var time="0"+d.getHours()+":"+d.getMinutes()+":00";
		}else{
			var time=d.getHours()+":0"+d.getMinutes()+":00";
		}
		
		if(appdate==date){
			if(apptime<time){

				toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};toastr
					.error(
							'',
							'Your Selected Time Should be Greater Than The Present System Time');	
					 $scope.holidays=undefined;
			}else{
				$scope.holidays.doctorUsername= $rootScope.loggedUsername;
				holidaysService.addHolidays($scope.holidays).then(function(response) {
					
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-full-width",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.HOLIDAY_ADD_SUCCESS);
		                $scope.holidays=undefined;
		                $scope.getAllHolidaysByDoctorUsernamePage();
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
		                
					
					//$scope.message = JSON.stringify(error.data.message.trim());
				});
				}
		}else{
		$scope.holidays.doctorUsername= $rootScope.loggedUsername;
		holidaysService.addHolidays($scope.holidays).then(function(response) {
			
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.HOLIDAY_ADD_SUCCESS);
                $scope.holidays=undefined;
                $scope.getAllHolidaysByDoctorUsernamePage();
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
                
			
			//$scope.message = JSON.stringify(error.data.message.trim());
		});
		}
	};
	//$scope.holidays.color="red";
	$scope.changeDate=function(Date){
		var currentDate= Date; //Extracting the date value in dd/mm/yyyy format from the mentioned text box
		var newDate = currentDate.split('-'); //Splitting the extracted date value using the delimiter /, which is the seperator used in the date value			
		currentDate = newDate[1] + "-" + newDate[0] + "-" + newDate[2];//Constructing a new date value (string) using the splitted values.
		return currentDate;
	}
	$scope.getAllHolidaysByDoctorUsername = function() {
		//$scope.companyAdminUsername= $rootScope.loggedUsername;
		holidaysService.getAllHolidaysByDoctorUsername($rootScope.loggedUsername).then(function(response) {
			$scope.holidaysList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllHolidaysByDoctorUsernamePage = function() {
		//$scope.companyAdminUsername= $rootScope.loggedUsername;
		holidaysService.getAllHolidaysByDoctorUsernamePage($rootScope.loggedUsername,$scope.page, $scope.size).then(function(response) {
			$scope.holidaysLists = response.data.content;
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages - 1;
			$scope.noOfPgaes(response.data.totalPages);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
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
		$scope.getAllHolidaysByDoctorUsernamePage();
	});
	
	
	
	// ///////////////////////////////////////////////////////////
	

	
	$scope.getAllHolidays = function() {
		holidaysService.getAllHolidays().then(function(response) {
			$scope.holidaysList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.updateHolidays = function( holidays) {
		holidaysService.updateHolidays( holidays).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.HOLIDAY_UPDATE_SUCCESS);
			$state.go('main.addHolidays');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deleteHolidays = function(id) {
		holidaysService.deleteHolidays(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.HOLIDAY_DELETE_SUCCESS);
			$scope.getAllHolidaysByDoctorUsernamePage();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {
		$state.go('main.addHolidays');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addHolidays');
	};
	$scope.gotoback = function(holidays) {
		//$state.go('main.addHolidays');
		holidays.fromTime=undefined;
		holidays.toTime=undefined;
		holidays.reason=undefined;
	};
	$scope.gotoupdate = function( holidays) {
		$state.go('main.updateHolidays',{
			 holidaysObj:	 holidays
		});
	};
	
	$scope.getAllHolidaysByRole=function(){
		holidaysService.getAllHolidaysByRole($rootScope.loggedUsername).then(function(response) {
			$scope.holiddaysList=response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
}
angular.module("HealthApplication")
		.controller("holidaysCtrl", holidaysCtrl);











/*//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function holidaysCtrl($scope, $state, holidaysService, $rootScope, $stateParams) {

	$scope.holidays = $stateParams.holidaysObj;
	$scope.holidays = {};
	$scope.addHolidays = function() {
		$scope.holidays.doctorUsername = $rootScope.loggedUsername;
		holidaysService.addHolidays($scope.holidays).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', ' Data Added Successfully');
			$state.go('main.holidaysList');
		}, function(error) {
			// $scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllHolidays = function() {
		holidaysService.getAllHolidays().then(function(response) {
			$scope.holidaysList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.updateHolidays = function(holidays) {
		holidaysService.updateHolidays(holidays).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', ' Data Updated Successfully');
			$state.go('main.holidaysList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deleteHolidays = function(id) {
		holidaysService.deleteHolidays(id).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', ' Data Deleted Successfully');
			$scope.getAllHolidays();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {
		$state.go('main.holidaysList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.holidays');
	};
	$scope.gotoback = function() {
		$state.go('main.holidaysList');
	};
	$scope.gotoupdate = function(holidays) {
		$state.go('main.updateHolidays', {
			holidaysObj : holidays
		});
	};
}
angular.module("HealthApplication").controller("holidaysCtrl", holidaysCtrl);
*/


