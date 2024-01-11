function appointmentCtrl($scope, $rootScope, $state, departmentService,
		scheduleService, $stateParams, patientService, therapistService,
		loginService, appointmentService,$http, $filter, $uibModal,$log) {
	
	$scope.page = 0;
	$scope.size = 5;
	$scope.appointment = {};
	$scope.appointments = {};
	$scope.values = [];
	$scope.flag = false;
	$scope.doctor = {};
	$scope.appointmentForCalendar={};
	$scope.endappointmentdate=undefined;
	$scope.hoursList=[];
	$scope.scheduledHourDto={};
	if ($stateParams.stDate != undefined && $stateParams.enddate != undefined) {
		$scope.startDate = $stateParams.stDate;
		$scope.appointment.appointmentEnddate = $stateParams.enddate;
		
		$scope.appointment.appointmentStartedDate = $scope.startDate;
		$scope.flag = true;
	}
	if($stateParams.startTime != undefined){
		$scope.appointmentStartTime = $stateParams.startTime+ ":00";
	}
	$scope.gotoPatientViewPage = function(patient) {
		$state.go('main.patientviewtabs.patient_documents', {
			patientObject : patient
		});
	};
	////COGNORE////
	/* $scope.open = function(size) {

	        var modalInstance = $uibModal.open({
	            animation: $scope.animationsEnabled,
	            templateUrl: 'CModalContent.html',
	            controller: 'appointmentCtrl',
	            size: size,
	            resolve: {
	                items: function() {
	                    return $scope.items;
	                }
	            }
	        });

	        modalInstance.result.then(function(selectedItem) {
	            $scope.selected = selectedItem;
	        }, function() {
	            $log.info('Modal dismissed at: ' + new Date());
	        });
	    };*/
	    ///2///
	  
	    ////COGNORE eND////
	/*if ($stateParams.selectScheduleObj != undefined) {
		$scope.selectedScheduleObj = $stateParams.selectScheduleObj;
		$scope.scheduleId = $scope.selectedScheduleObj.id;
		$scope.appointmentStartTime = $scope.selectedScheduleObj.fromTime
				+ ":00";
		$scope.appointmentEndTime = $scope.selectedScheduleObj.toTime + ":00";
		$scope.doctor = $scope.selectedScheduleObj.doctor;
		$scope.doctorname = $scope.selectedScheduleObj.doctorName;
		// $scope.doctorname=$scope.schedulefromCalendar.title;
		$scope.flag = true;
		$scope.scheduledHourDto.startTime=$scope.appointmentStartTime;
		$scope.scheduledHourDto.endtime=$scope.appointmentEndTime;
		$scope.scheduledHourDto.scheduleDtoForCalendar=$scope.selectedScheduleObj;
	}
	;*/
	if ($stateParams.selectScheduleObj != undefined) {
		$scope.selectedScheduleObj = $stateParams.selectScheduleObj;
		$scope.scheduleId = $scope.selectedScheduleObj.scheduleDtoForCalendar.id;
		
		if(parseInt($scope.appointmentStartTime)>8){
			$scope.appointmentEndTime = (parseInt($scope.appointmentStartTime)+1)+ ":"+$scope.appointmentStartTime.charAt(3)+"0"+":00";
		}else{
			$scope.appointmentEndTime = "0"+(parseInt($scope.appointmentStartTime)+1)+ ":"+$scope.appointmentStartTime.charAt(3)+"0"+":00";
		}
		
		$scope.doctor = $scope.selectedScheduleObj.scheduleDtoForCalendar.doctor;
		$scope.doctorname = $scope.selectedScheduleObj.scheduleDtoForCalendar.doctorName;
		// $scope.doctorname=$scope.schedulefromCalendar.title;
		$scope.flag = true;
		$scope.scheduledHourDto.startTime=$scope.appointmentStartTime;
		$scope.scheduledHourDto.endtime=$scope.appointmentEndTime;
		$scope.scheduledHourDto.scheduleDtoForCalendar=$scope.selectedScheduleObj;
	}
	;
	if($stateParams.calenderScheduleObj != undefined){
		//$scope.startDate = $stateParams.stDate;
		$scope.appointmentForCalendar=$stateParams.calenderScheduleObj;
		//$scope.appointmentForCalendar.startsAt=
		$scope.startTimer= $scope.appointmentForCalendar.startsAt.split("T");
		$scope.startDate=$scope.startTimer[0];
		$scope.startTime=$scope.startTimer[1];
		$scope.endTimer= $scope.appointmentForCalendar.endsAt.split("T");
		$scope.endDate=$scope.endTimer[0];
		$scope.endTime=$scope.endTimer[1];
		
		$scope.appointment.appointmentStartedDate = $scope.appointmentForCalendar.startsAt;
		$scope.appointmentStartTime=$scope.startTime;
		$scope.appointment.appointmentEnddate =  $scope.startDate;
		$scope.appointmentEndTime=$scope.endTime;
		$scope.doctor=$scope.appointmentForCalendar.doctor;
		$scope.doctorname=$scope.appointmentForCalendar.doctor.firstName;
		
	};
	

	if ($stateParams.scheduleListByDate != undefined) {
		$scope.scheduleListByDate = $stateParams.scheduleListByDate;
	}
	;

	$scope.arrayToString = function(string) {
		return string.join(", ");
	};
	$scope.weekDays = [ 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday',
			'Friday', 'Saturday' ];
	$scope.searchTerm;
	$scope.clearSearchTerm = function() {
		$scope.searchTerm = '';
	};

	if ($stateParams.appointmentObj != undefined) {
		$scope.updatedappointment = $stateParams.appointmentObj;
	}
	;
	$scope.makeCheckboxClick = function() {
		$scope.appointment.weekDays = $scope.appointment.appointmentStartedDate
				.getDay();
		$scope.appointment.weekDays = "ng-checked='appointment.weekDays'";
	};

	$scope.makeStringNull = function() {
		$scope.appointment.repeatOnWhichWeek = null;
		$scope.appointment.repeatOnWeek = null;
	};

	$scope.makeNumberNull = function() {
		$scope.appointment.repeatOnDay = null;
	};

	$scope.makeOccurrenceNull = function() {
		$scope.appointment.afterOccurrence = null;
	};

	$scope.makeEndNumberNull = function() {
		// $scope.appointment.appointmentEndedDate =new Date($scope.appointment.appointmentEndedDate.setDate($scope.appointment.appointmentStartedDate.getDate()
		// +($scope.appointment.afterOccurrence)*7));
		// $scope.appointment.appointmentEndedDate=$scope.appointment.appointmentEndedDate.setDate($scope.appointment.appointmentStartedDate.getDate()
		// +($scope.appointment.afterOccurrence)*7);

	};
	/*
	 * if($scope.appointment.afterOccurrence != undefined) {
	 * $scope.appointment.appointmentEndedDate =new
	 * Date($scope.appointment.appointmentEndedDate.setDate($scope.appointment.appointmentStartedDate.getDate()
	 * +($scope.appointment.afterOccurrence)*7)); }
	 */
	$scope.initmethod=function(){
		if($scope.appointment.appointmentStartedDate != undefined){
			$scope.appointment.appointmentStartedDate=new Date($scope.appointment.appointmentStartedDate);
			$scope.appointment.weekDays=$scope.appointment.appointmentStartedDate.getDay();
		}
	}

/*	$scope.myFunc = function() {
		if ($scope.appointmentStartTime.charAt(3) == 3) {
			$scope.appointmentEndTime = parseInt($scope.appointmentStartTime)
					+ parseInt($scope.appointment.duration);
			if ($scope.appointmentEndTime < 10) {
				if ($scope.appointment.duration.charAt(3) == 3) {
					$scope.appointmentEndTime = "0"
							+ (parseInt($scope.appointmentStartTime)
									+ parseInt($scope.appointment.duration) + 1)
							+ ":00" + ":00";
				} else {

					$scope.appointmentEndTime = "0"
							+ (parseInt($scope.appointmentStartTime) + parseInt($scope.appointment.duration))
							+ ":30" + ":00";
				}
			} else {
				if ($scope.appointment.duration.charAt(3) == 3) {
					$scope.appointmentEndTime = parseInt($scope.appointmentStartTime)
							+ parseInt($scope.appointment.duration)
							+ 1
							+ ":00"
							+ ":00";
				} else {
					$scope.appointmentEndTime = parseInt($scope.appointmentStartTime)
							+ parseInt($scope.appointment.duration)
							+ ":30"
							+ ":00";
				}

			}
		} else {
			$scope.appointmentEndTime = parseInt($scope.appointmentStartTime)
					+ parseInt($scope.appointment.duration);
			if ($scope.appointmentEndTime < 10) {
				if ($scope.appointment.duration.charAt(3) == 3) {
					$scope.appointmentEndTime = "0"
							+ (parseInt($scope.appointmentStartTime) + parseInt($scope.appointment.duration))
							+ ":30" + ":00";
				} else {
					$scope.appointmentEndTime = "0"
							+ (parseInt($scope.appointmentStartTime) + parseInt($scope.appointment.duration))
							+ ":00" + ":00";
				}
			} else {
				if ($scope.appointment.duration.charAt(3) == 3) {
					$scope.appointmentEndTime = parseInt($scope.appointmentStartTime)
							+ parseInt($scope.appointment.duration)
							+ ":30"
							+ ":00";
				} else {
					$scope.appointmentEndTime = parseInt($scope.appointmentStartTime)
							+ parseInt($scope.appointment.duration)
							+ ":00"
							+ ":00";
				}
			}
		}

		// ////////////coding for 30 minutes
		
		 * if($scope.appointment.duration.charAt(2)==5){
		 * $scope.appointment.appointmentEndTime
		 * =parseInt($scope.appointment.appointmentStartTime)
		 * +parseInt($scope.appointment.duration)+1+":00"+":00"; }
		 
		// /////////////
	};*/
	//converting dd/mm/yyyy formate to mm/dd/yyyy
	$scope.changeDate=function(appointmentStartedDate){
		var currentDate= appointmentStartedDate; //Extracting the date value in dd/mm/yyyy format from the mentioned text box
		var newDate = currentDate.split('-'); //Splitting the extracted date value using the delimiter /, which is the seperator used in the date value			
		currentDate = newDate[1] + "-" + newDate[0] + "-" + newDate[2];//Constructing a new date value (string) using the splitted values.
		return currentDate;
	}	
	//converting yyyy/mm/dd formate to mm/dd/yyyy
	$scope.changeDateformate=function(date){
		var currentDate= date; //Extracting the date value in yyyy/mm/dd format from the mentioned text box
		var newDate = currentDate.split('-'); //Splitting the extracted date value using the delimiter /, which is the seperator used in the date value			
		currentDate = newDate[1] + "-" + newDate[2] + "-" + newDate[0];//Constructing a new date value (string) using the splitted values.
		return currentDate;
	}	


	$scope.myFunction = function() {
		$scope.appointment.appointmentEndedDate =new Date($scope.appointment.appointmentStartedDate);
		if($scope.appointment.weeks !=undefined){
		$scope.appointment.appointmentEndedDate = new Date($scope.appointment.appointmentEndedDate.setDate($scope.appointment.appointmentEndedDate.getDate()+ (($scope.appointment.afterOccurrence - 1)*($scope.appointment.weeks)) * 7));
		}
		else{
			$scope.appointment.appointmentEndedDate = new Date($scope.appointment.appointmentEndedDate.setDate($scope.appointment.appointmentEndedDate.getDate()+ ($scope.appointment.afterOccurrence-1) * 7));
			
		}
	};

	/*$scope.myFunctionDuration = function() {
		$scope.appointment.duration = parseInt($scope.appointmentEndTime)
				- parseInt($scope.appointmentStartTime);
		if ($scope.appointment.duration < 10) {
			if ($scope.appointmentStartTime.charAt(3) != $scope.appointmentEndTime
					.charAt(3)) {
				if ($scope.appointmentStartTime.charAt(3) == 3) {
					$scope.appointment.duration = "0"
							+ (parseInt($scope.appointmentEndTime)
									- parseInt($scope.appointmentStartTime) - 1)
							+ ":30" + ":00";
				} else {
					$scope.appointment.duration = "0"
							+ (parseInt($scope.appointmentEndTime) - parseInt($scope.appointmentStartTime))
							+ ":30" + ":00";
				}
			} else {
				$scope.appointment.duration = "0"
						+ (parseInt($scope.appointmentEndTime) - parseInt($scope.appointmentStartTime))
						+ ":00" + ":00";
			}
		} else {
			if ($scope.appointmentStartTime.charAt(3) != $scope.appointmentEndTime
					.charAt(3)) {
				if ($scope.appointmentStartTime.charAt(3) == 3) {
					$scope.appointment.duration = parseInt($scope.appointmentEndTime)
							- parseInt($scope.appointmentStartTime)
							- 1
							+ ":30"
							+ ":00";
				} else {
					$scope.appointment.duration = parseInt($scope.appointmentEndTime)
							- parseInt($scope.appointmentStartTime)
							+ ":30"
							+ ":00";
				}
			} else {
				$scope.appointment.duration = parseInt($scope.appointmentEndTime)
						- parseInt($scope.appointmentStartTime) + ":00" + ":00";
			}
		}

		
		 * if($scope.appointment.appointmentStartTime.charAt(3)!=$scope.appointment.appointmentEndTime.charAt(3)){
		 * $scope.appointment.duration=parseInt($scope.appointment.appointmentEndTime)-parseInt($scope.appointment.appointmentStartTime)+0.5; }
		 * else{
		 * $scope.appointment.duration=parseInt($scope.appointment.appointmentEndTime)-parseInt($scope.appointment.appointmentStartTime)+":00"+":00";
		 *  }
		 
	};*/
/////////////////////////////////////////////////Add for Calendere////////////////////////////////////////////////////////////////////////////////
	$scope.addAppointmentCalender = function() {
		$scope.appointmentForCalendar.appointmentStartedDate = $scope.startDate;
		$scope.appointmentStartTime=$scope.startTime;
		
		$scope.appointmentForCalendar.appointmentEnddate =  $scope.startDate;
		$scope.appointmentEndTime=$scope.endTime;
		$scope.doctor=$scope.appointmentForCalendar.doctor;
		$scope.doctorname=$scope.appointmentForCalendar.doctor.firstName;
		
		//$scope.appointment.scheduleId = $scope.scheduleId;
		$scope.appointment.appointmentStartTime = $scope.appointmentStartTime;
		$scope.appointment.appointmentEndTime = $scope.appointmentEndTime;
		$scope.appointment.doctor = $scope.doctor;
		$scope.appointment.adminUserName = $rootScope.loggedUsername;
		if ($scope.appointment.occurance == 'NEVER') {
			
			$scope.appointment.appointmentEndedDate=$scope.appointment.appointmentStartedDate;
		}
console.log($scope.appointment.appointmentStartedDate);
console.log("new Date="+new Date());
		appointmentService
				.addAppointmentCalender($scope.appointment)
				.then(
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};
							toastr
									.success(
											'',
											'Appointment Saved Successfully and Your Appointmene Details are sent to Registered EmailId');
							$state.go('main.appointment_list');
							$rootScope.loginMessage = "Appointment Successfully Saved!!";
						},
						function(error) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 2000
							};
							$scope.message1 = JSON.stringify(error.data.message
									.trim());
							$scope.message = $scope.message1.substring(0,$scope.message1.length);
							toastr.error($scope.message, 'Error');
						});
	};
	$scope.gotoCalender = function() {
		$state.go('main.calender2');
	}
	$scope.gotoApp = function() {
		
		$state.go('main.add_appoinment');
		
	}
  $scope.goto = function() {
		
		$state.go('main.add_appointment');
		
	}
	//////////////////////////////////Add Completed For Calender//////////////////////////////////////////////////////
	$scope.addAppointment = function() {

		/*
		 * if($scope.appointment.occurance=='WEEKLY'){ var d = new Date(); var n =
		 * d.getDay() }
		 */
		$scope.appointment.scheduleId = $scope.scheduleId;
		//alert($scope.appointment.scheduleId);
		
		// $scope.appointment.appointmentStartedDate = $scope.startDate;
		$scope.appointment.appointmentStartTime = $scope.appointmentStartTime;
		$scope.appointment.appointmentEndTime = $scope.appointmentEndTime;
		$scope.appointment.doctor = $scope.doctor;
		$scope.appointment.adminUserName = $rootScope.loggedUsername;
		if ($scope.appointment.occurance == 'NEVER') {
			// $scope.appointment.appointmentEndedDate=$scope.appointment.appointmentStartedDate;
			// $scope.appointment.appointmentEndedDate =new
			// Date($scope.appointment.appointmentEndedDate.setDate($scope.appointment.appointmentStartedDate.getDate());
			//$scope.appointment.appointmentEndedDate = new Date();
			
			$scope.appointment.appointmentEndedDate=$scope.appointment.appointmentStartedDate;
			/*$scope.appointment.appointmentEndedDate = new Date(
					$scope.appointment.appointmentEndedDate
							.setDate($scope.appointment.appointmentStartedDate
									.getDate() + (0)));*/

		}
		/*
		 * if($scope.appointment.appointmentEndTime != undefined){
		 * $scope.appointment.duration=parseInt($scope.appointment.appointmentEndTime)-parseInt($scope.appointment.appointmentStartTime); }
		 */

		/*
		 * if($scope.appointment.appointmentStartedDate != undefined){
		 * $scope.appointment.weekDays=$scope.appointment.weekDays; }
		 */

		
		/*
		 * if($scope.appointment.duration!=undefined){ //var
		 * a=$scope.appointment.appointmentStartTime.charAt[1];
		 * 
		 * if($scope.appointment.appointmentStartTime.charAt(3)==3){
		 * $scope.appointment.appointmentEndTime
		 * =parseInt($scope.appointment.appointmentStartTime)
		 * +parseInt($scope.appointment.duration);
		 * if($scope.appointment.appointmentEndTime<10 ){
		 * $scope.appointment.appointmentEndTime
		 * ="0"+(parseInt($scope.appointment.appointmentStartTime)
		 * +parseInt($scope.appointment.duration))+":30"+":00"; }else{
		 * $scope.appointment.appointmentEndTime
		 * =parseInt($scope.appointment.appointmentStartTime)
		 * +parseInt($scope.appointment.duration)+":30"+":00"; } } else{
		 * $scope.appointment.appointmentEndTime
		 * =parseInt($scope.appointment.appointmentStartTime)
		 * +parseInt($scope.appointment.duration);
		 * if($scope.appointment.appointmentEndTime<10 ){
		 * $scope.appointment.appointmentEndTime
		 * ="0"+(parseInt($scope.appointment.appointmentStartTime)
		 * +parseInt($scope.appointment.duration))+":00"+":00"; }else{
		 * $scope.appointment.appointmentEndTime
		 * =parseInt($scope.appointment.appointmentStartTime)
		 * +parseInt($scope.appointment.duration)+":00"+":00"; } } }
		 */

		appointmentService
				.addAppointment($scope.appointment)
				.then(
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};
							toastr
									.success(
											'',
											'Appointment Saved Successfully and Your Appointmene Details are sent to Registered EmailId');
							$state.go('main.appointment_list');
							$rootScope.loginMessage = "Appointment Successfully Saved!!";
						},
						function(error) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 2000
							};
							$scope.message = JSON.stringify(error.data.message
									.trim());
							toastr.error($scope.message, 'Error');
						});
	};

	$scope.getAppointmentssByRole = function() {
		$scope.adminUserName = $rootScope.loggedUsername
		appointmentService.getAppointmentssByRole($scope.adminUserName).then(
				function(response) {
					$scope.appointmentsList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	if($rootScope.loggedUserRole!='Super Admin'){
	$scope.getAppointmentssByRoleByPagination = function() {
		$scope.adminUserName = $rootScope.loggedUsername
		appointmentService.getAppointmentssByRoleByPagination($scope.adminUserName ,$scope.page, $scope.size).then(
				function(response) {
					$scope.appointmentsList = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages - 1;
					$scope.noOfPgaes(response.data.totalPages);
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	}
	if($rootScope.loggedUserRole=='Super Admin'){
	$scope.getAppointmentssByAdminByPagination = function() {
		$scope.adminUserName = $rootScope.loggedUsername
		appointmentService.getAppointmentssByAdminByPagination($scope.adminUserName ,$scope.page, $scope.size).then(
				function(response) {
					$scope.appointmentsAdminList = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages - 1;
					$scope.noOfPgaes(response.data.totalPages);
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	}
	$scope.getListBetweenDates = function() {
		$scope.appointments = {
			"adminUserName" : $rootScope.loggedUsername,
			"appointmentStartedDate" : $scope.StartedDate,
			"appointmentEndedDate" : $scope.EndedDate
		};
		// $scope.appointments.adminUserName = $rootScope.loggedUsername
		appointmentService.getListBetweenDates($scope.appointments,$scope.page, $scope.size).then(
				function(response) {
					/*alert($rootScope.loggedUserRole);
					alert($rootScope.loggedUserRole=='Super Admin');*/
					if($rootScope.loggedUserRole=='Super Admin'){
					$scope.appointmentsAdminList = response.data.content;
					}else{
						$scope.appointmentsList = response.data.content;	
					}
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages - 1;
					$scope.noOfPgaes(response.data.totalPages);
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.getAllTherpistByDepartment = function(id) {

		therapistService.getAllTherpistByDepartment(id).then(
				function(response) {
					$scope.therapistList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.getAllDepartments = function() {
		departmentService.getAlldepartments().then(function(response) {
			$scope.departmentsList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});

	};

	$scope.getAllPatients = function() {
		$scope.adminUserName = $rootScope.loggedUsername;
		patientService.getAllPatients($scope.adminUserName).then(
				function(response) {
					$scope.patientList = response.data;
				}, function(error) {
				});
	};

	$scope.getAllTherapists = function() {
		$scope.adminUsername = $rootScope.loggedUsername;
		therapistService.getAllTherapists($scope.adminUsername).then(
				function(response) {
					$scope.therapistList = response.data;

				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.updateAppointment = function(appointment) {
		appointmentService.updateAppointment(appointment).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', ' Data Update Successfully');
					$state.go('main.appointment_list');
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	$scope.deleteAppointmentMethod = function(id) {
		appointmentService.dropAppointment(id).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', ' Data Deleted Successfully');
			$scope.getAppointmentssByRoleByPagination();
		}, function(error) {
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 2000
				};
			$scope.message = JSON.stringify(error.data.message.trim());
			toastr.error($scope.message, ' Error');
		});
	};
	$scope.gotoList = function() {
		$state.go('main.appointment_list');
	};
	$scope.gotoAdd = function() {
		$state.go('main.add_appointment');
	};
	$scope.gotoback = function() {
		$state.go('main.appointment_list');
	};
	$scope.gotoupdate = function(appointment) {
		$state.go('main.editappointment', {
			appointmentObj : appointment
		});
	};
	//////////////////fOR cALENDER
	$scope.searchSchedulesCalender = function(startDate, endDate) {
				$scope.searchScheduleDtoObj = {
					"localDate" : $scope.appointment.appointmentStartedDate,
					"enddate" : $scope.appointment.appointmentEnddate,
					"fromTime" : $scope.appointmentStartTime,
					"toTime" : $scope.appointmentEndTime,
					"username" : $rootScope.loggedUsername
				}
				scheduleService.searchScheduleDto($scope.searchScheduleDtoObj).then(
						function(response) {
							$scope.schedulesDtos = response.data;
							$state.go('main.searchSchedule', {
								scheduleListByDate : $scope.schedulesDtos,
								stDate : startDate,
								enddate : endDate
							});
						}, function(error) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 5000
							};
							$scope.message = JSON.stringify(error.data.message.trim());
							toastr.error($scope.message, 'Error');
							$scope.appointment.appointmentStartedDate=undefined;
							$scope.appointment.appointmentEnddate=undefined;
						})
	};
	
	/////////CALENDER OVER////////////////////
	
	
	
	
	
	$scope.searchSchedules = function(startDate, endDate) {
		if($scope.appointment.appointmentStartedDate.getTimezoneOffset()<=0){
			$scope.appointment.appointmentStartedDate.setMinutes($scope.appointment.appointmentStartedDate.getMinutes() - $scope.appointment.appointmentStartedDate.getTimezoneOffset());
			$scope.appointment.appointmentEnddate.setMinutes($scope.appointment.appointmentEnddate.getMinutes() - $scope.appointment.appointmentEnddate.getTimezoneOffset());
		}else{
			$scope.appointment.appointmentStartedDate.setMinutes($scope.appointment.appointmentStartedDate.getMinutes() + $scope.appointment.appointmentStartedDate.getTimezoneOffset());
			$scope.appointment.appointmentEnddate.setMinutes($scope.appointment.appointmentEnddate.getMinutes() + $scope.appointment.appointmentEnddate.getTimezoneOffset());
		}
		///////////////
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
		
		var n=$scope.appointment.appointmentStartedDate;
		//////////
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
		////////////////
		
			if(appdate==date){
		if($scope.appointmentStartTime<time){
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
				$scope.appointment.appointmentStartedDate=undefined;
				$scope.appointment.appointmentEnddate=undefined;
				
		}else{
		/////////////////////
		
		$scope.searchScheduleDtoObj = {
			"localDate" : $scope.appointment.appointmentStartedDate,
			"enddate" : $scope.appointment.appointmentEnddate,
			"fromTime" : $scope.appointmentStartTime,
			"toTime" : $scope.appointmentEndTime,
			"username" : $rootScope.loggedUsername
		}
		scheduleService.searchScheduleDto($scope.searchScheduleDtoObj).then(
				function(response) {
					$scope.schedulesDtos = response.data;
					$state.go('main.searchSchedule', {
						scheduleListByDate : $scope.schedulesDtos,
						stDate : startDate,
						enddate : endDate
					});
				}, function(error) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 5000
					};
					$scope.message = JSON.stringify(error.data.message.trim());
					toastr.error($scope.message, 'Error');
					$scope.appointment.appointmentStartedDate=undefined;
					$scope.appointment.appointmentEnddate=undefined;
				})}}
			else{
				$scope.searchScheduleDtoObj = {
					"localDate" : $scope.appointment.appointmentStartedDate,
					"enddate" : $scope.appointment.appointmentEnddate,
					"fromTime" : $scope.appointmentStartTime,
					"toTime" : $scope.appointmentEndTime,
					"username" : $rootScope.loggedUsername
				}
				scheduleService.searchScheduleDto($scope.searchScheduleDtoObj).then(
						function(response) {
							$scope.schedulesDtos = response.data;
							$state.go('main.searchSchedule', {
								scheduleListByDate : $scope.schedulesDtos,
								stDate : startDate,
								enddate : endDate
							});
						}, function(error) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 5000
							};
							$scope.message = JSON.stringify(error.data.message.trim());
							toastr.error($scope.message, 'Error');
							$scope.appointment.appointmentStartedDate=undefined;
							$scope.appointment.appointmentEnddate=undefined;
						})}
	};
	$scope.selectSchedule = function(schedule, startDate,endDate) {
		$state.go('main.add_appointment', {
			selectScheduleObj : schedule,
			stDate : startDate,
			enddate : endDate
		})
	};
	$scope.selectScheduleSub = function(scheduledHourDto,startDate,endDate,fromTime) {
		var stringfromTime=fromTime.split("-");//02:00-03:00//02:00,03:00
		var splittedfromTime=stringfromTime[0];//02:00
		$state.go('main.add_appointment', {
			selectScheduleObj : scheduledHourDto,
			stDate : startDate,
			enddate : endDate,
			startTime:splittedfromTime
		})
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
		if($rootScope.loggedUserRole=='Super Admin'){
			$scope.getAppointmentssByAdminByPagination();	
		}
		
		if($rootScope.loggedUserRole!='Super Admin'){
			$scope.getAppointmentssByRoleByPagination();	
		}
		$scope.getListBetweenDates();
		
		
	});
	///////////BackUp Don't Delete////////
	$scope.findScheduledHours123=function(){
		$scope.scheduledHourDto.startTime=$scope.appointmentStartTime;
		$scope.scheduledHourDto.appointmentStartedDate=$scope.appointment.appointmentStartedDate;
		//alert($scope.scheduledHourDto.startDate);
		console.log($scope.scheduledHourDto.startDate);
		$scope.scheduledHourDto.endtime=$scope.appointmentEndTime;
		scheduleService.findScheduledHours($scope.scheduledHourDto).then(function(response){
			$scope.hoursList=response.data;
		},function(error){
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 2000
				};
				$scope.message = JSON.stringify(error.data.message
						.trim());
				toastr.error($scope.message, 'Error');
		})
	};
///////////////////////////////////////////////////	
	
	
	
	$scope.findScheduledHours=function(schedule){
		$scope.scheduledHourDto.startTime=schedule.fromTime;
		$scope.scheduledHourDto.appointmentStartedDate=schedule.scheduledDate;
		//alert($scope.scheduledHourDto.startDate);
		//console.log($scope.scheduledHourDto.startDate);
		$scope.scheduledHourDto.endtime=schedule.toTime;
		$scope.scheduledHourDto.scheduleDtoForCalendar=schedule;
		scheduleService.findScheduledHours($scope.scheduledHourDto).then(function(response){
			$scope.hoursList=response.data;
		},function(error){
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 2000
				};
				$scope.message = JSON.stringify(error.data.message
						.trim());
				toastr.error($scope.message, 'Error');
		})
	};
	
	// ///////////////////////////////////////////////////////////
	$scope.deletealert=function(id){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this Appointment!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
					appointmentService.dropAppointment(id).then(function(response) {
						 swal("Poof! Your Appointment has been deleted!", {
						      icon: "success",
						    });
						$scope.getAppointmentssByRoleByPagination();
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
						 swal($scope.message , {
						      icon: "info",
						    });
						
					});
				
			
			    swal("Proof! Your Appointment has been deleted!", {
			      icon: "success",
			    });
			   
			  } else {
			    swal("Your Appointment is safe!");
			  }
			});
	};
	$scope.getTodaySubAppointments = function(){
		$scope.userName=$rootScope.loggedUsername
		appointmentService.getTodaySubAppointments($scope.userName).then(
				function(response){
					$scope.TodaySubAppointmentsList=response.data;
				},function(error){
					$scope.message=JSON.stringify(error.data.message.trim());
				});
	};
	
	$scope.updateStatus = function(id,status){
		appointmentService.updateSubappointmentStatus(id,status).then(
				
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};
							/*toastr
									.success(
											'',
											'Appointment Saved Successfully and Your Appointmene Details are sent to Registered EmailId');*/
							$state.go('main.approveAppoinment');
							//$rootScope.loginMessage = "Appointment Successfully Saved!!";
						},
						function(error) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 2000
							};
							$scope.message = JSON.stringify(error.data.message
									.trim());
							toastr.error($scope.message, 'Error');
						});
	};
	
	$scope.updateStatus1 = function(appointmentDate,id,status){
		 var date1 = new Date(); 
		  mnth = ("0" +(date1.getMonth() + 1)).slice(-2), day = ("0" + date1.getDate()).slice(-2);
		  var todayDate =[day,mnth,date1.getFullYear() ].join("-");
		  
		  var d1 = Date.parse(appointmentDate);
		  var d2 = Date.parse(todayDate);
		if(d1>d2&&status=='COMPLETE'){
			toastr
			.error(
					'',
					'Future Appointment Still Not Completed ');
		}else{
		appointmentService.updateSubappointmentStatus(id,status).then(
				
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};
							toastr
									.success(
											'',
											'Appointment Saved Successfully and Your Appointmene Details are sent to Registered EmailId');
							$state.go('main.editappointment');
							//$rootScope.loginMessage = "Appointment Successfully Saved!!";
						},
						function(error) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 2000
							};
							$scope.message = JSON.stringify(error.data.message
									.trim());
							toastr.error($scope.message, 'Error');
						});
		}
	};
	
	/*$scope.goTOEdit= function(editAppointment){
		$state.go('main.editappointment',{
			editAppointmentObj:editAppointment
		});
		
	};*/
	$scope.gotoInvoicePage = function(pa) {
		   $rootScope.invoiceId=pa.invoiceNo;
			$state.go('main.invoiceTemplate', {
				patientObject : pa,
			});
		};
		$scope.resetFilter=function(StartedDate,EndedDate){
			$scope.StartedDate=undefined;
			$scope.EndedDate=undefined;
		}
}
angular.module('HealthApplication').controller('appointmentCtrl',
		appointmentCtrl);