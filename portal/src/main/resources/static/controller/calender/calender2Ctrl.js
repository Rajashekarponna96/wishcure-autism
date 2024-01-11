angular.module('HealthApplication').controller(
		'calendarCtrl',
		function(moment, alert, calendarConfig, $scope,$state, $rootScope,appointmentService,patientService,
				scheduleService,holidaysService) {
   $scope.page=0;
   $scope.size=100;
   $scope.calendarDto={};
   $scope.TodayDateDto={};
   $scope.calendarDto={};
   $scope.listofSchedules=[];
			var vm = this;
			// These variables MUST be set as a minimum for the calendar
			// to work
			vm.calendarView = 'month';
			//vm.calendarTitle=
			vm.viewDate = new Date();
			var actions = [ 
			];
			vm.cellIsOpen = true;
			$scope.getAllSchedulesByDoctorUsername = function() {
				scheduleService.getAllSchedulesByDoctorUsername($rootScope.loggedUsername).then(function(response) {
					$scope.scheduleList = response.data;
					angular.forEach($scope.scheduleList, function(schedule) {
					vm.events.push({
						title : schedule.doctor.firstName,
						  type: 'warning',
						 startsAt: schedule.fromTime,
					     endsAt: schedule.toTime,
						color : calendarConfig.colorTypes.warning,
						draggable : false,
						resizable : true,
						actions : actions
					
					});
					})
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
			};
			$scope.YearData=function(){
			};
			$scope.MonthData=function(){
			};
			$scope.WeekData=function(){
			};
			$scope.DayData=function(calendarView,calendarTitle){
				$scope.calendarDto={
						sourceType:calendarView,
						sourceDate:calendarTitle
				}
				
			};
			
			$scope.getAllSchedulesByRole = function() {
				scheduleService.getAllSchedulesByRole($rootScope.loggedUsername).then(function(response) {
					$scope.scheduleList = response.data;
					
					angular.forEach($scope.scheduleList, function(schedule) {
						vm.events.push({
							title : schedule.doctor.firstName,
							  type: 'info',
							 startsAt: schedule.fromTime,
						     endsAt: schedule.toTime,
							color : calendarConfig.colorTypes.info,
							draggable : false,
							resizable : true,
							actions : actions
						
						});
					});
					
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
			};
			
						
			 $scope.ok = function() {
			        $scope.$modalInstance.close();
			    };

			    $scope.cancel = function() {
			        $scope.$modalInstance.dismiss('cancel');
			    };
			
			$scope.getAppointmentssByRole = function() {
				$scope.adminUserName = $rootScope.loggedUsername
				appointmentService.getAppointmentssByRole($scope.adminUserName)
						.then(function(response) {
							$scope.appointmentsList = response.data;
							angular.forEach($scope.appointmentsList, function(appintment) {
								var sDate=appintment.appointmentStartedDate;
								var eDate=appintment.appointmentEndedDate
								var startDate=sDate.split("-").reverse().join("-");
								var endDate=eDate.split("-").reverse().join("-");
								var str=appintment.doctor.firstName+"-"+appintment.patient.firstName;
								vm.events.push({
									id:appintment.id,
									 title : str,
									 type: 'success',
									 startsAt:new Date(startDate+"T"+appintment.appointmentStartTime+":00"),  
								     endsAt: new Date(endDate+"T"+appintment.appointmentEndTime+":00"),
									color : calendarConfig.colorTypes.success,
									draggable : false,
									resizable : true,
									actions : actions
								});
							})
							
						}, function(error) {
							$scope.message = JSON.stringify(error.data.message.trim());
						});
			};
			
			$scope.getSubAppointmentssByRoleforCalendarView = function() {
				$scope.adminUserName = $rootScope.loggedUsername;
				appointmentService.getSubAppointmentssByRoleforCalendarView($scope.adminUserName)
						.then(function(response) {
							$scope.appointmentsList = response.data;
							angular.forEach($scope.appointmentsList, function(appintment) {
								var str=appintment.doctor.firstName+"-"+appintment.patient.firstName;
								if($rootScope.loggedUserRole =="Therapist"){
									$scope.appointmenttitle="appointed";
								}else if( $rootScope.loggedUserRole =="Individual"){
									$scope.appointmenttitle=str;
								}else{
									$scope.appointmenttitle=str;
								}
								var sDate=appintment.appointmentStartedDate;
								var eDate=appintment.appointmentEndedDate
								var startDate=sDate.split("-").reverse().join("-");
								var endDate=eDate.split("-").reverse().join("-");
								vm.events.push({
									id:appintment.id,
									 title : $scope.appointmenttitle,
									 type: 'success',
									 startsAt:appintment.appointmentStartedDate+"T"+appintment.appointmentStartTime+":00",  
								     endsAt: appintment.appointmentEndedDate+"T"+appintment.appointmentEndTime+":00",
									color : calendarConfig.colorTypes.success,
									draggable : false,
									resizable : true,
									actions : actions
								});
							})
							
							// vm.events.patients=["pat1","pat2"];
							
						}, function(error) {
							$scope.message = JSON.stringify(error.data.message.trim());
						});
			};
			vm.updateAppointment = function() {
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
			

			$scope.findAllpatientsByPaginationByRole = function() {
				patientService.findAllpatientsByPaginationByRole($rootScope.loggedUsername ,$scope.page, $scope.size).then(
					function(response) {
						$scope.patientListByAdmin = response.data.content;
						$scope.totalElements = response.data.totalElements;
						$scope.totalPages = response.data.totalPages - 1;
						$scope.noOfPgaes(response.data.totalPages);
					});
			};
			
			// 718 get holidays of doctor
			
			$scope.getHolidaysByRole = function() {
				// $scope.companyAdminUsername= $rootScope.loggedUsername;
				holidaysService.getAllHolidaysByRole($rootScope.loggedUsername).then(function(response) {
					$scope.holidaysList = response.data;
					angular.forEach($scope.holidaysList, function(holiday) {
						
						var sDate=holiday.fromDate;
						var eDate=holiday.toDate;
						var startDate=sDate.split("-").reverse().join("-");
						var endDate=eDate.split("-").reverse().join("-");
						if($rootScope.loggedUserRole =="Therapist"){
							$scope.holidaytitle="Holiday";
						}else if( $rootScope.loggedUserRole =="Individual"){
							$scope.holidaytitle="Holiday";
						}else{
							$scope.holidaytitle="Holiday-"+holiday.doctor.firstName;
						}
						vm.events.push({
							 title : $scope.holidaytitle,
							 type: 'warning',
							 startsAt:startDate+"T"+holiday.fromLocalTime+":00",
							 endsAt:endDate+"T"+holiday.toLocalTime+":00",
							color : calendarConfig.colorTypes.warning,
							draggable : false,
							resizable : true,
							actions : actions
						});
					})
					
					
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
			
			$scope.Previous=function(){
			};
			
			$scope.Today=function(){
			};

			$scope.Next=function(){
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
				$scope.findAllpatientsByPaginationByRole();
			});
			vm.addEvent = function() {
				vm.events.push({
					title : $scope.event.title,
					
					appointmentStartDate : moment().startOf('day').toDate(),
					appointmentEndDate : moment().endOf('day').toDate(),
					color : calendarConfig.colorTypes.important,
					draggable : true,
					resizable : true
				});
			};

			
			vm.eventClicked = function(event) {
				//alert.show('Clicked', event);
				if(event.value=="Free"){
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
					
					
					$scope.calenderDate=event.startsAt.split("T");
					$scope.spittedDate=$scope.calenderDate[0];
					$scope.spittedtime=$scope.calenderDate[1];
					
					if($scope.spittedDate>=date){
						if($scope.spittedDate==date){
							if($scope.spittedtime>=time){
								$state.go('main.add_appointmentForCalender',{
									 calenderScheduleObj: event
								 });
							}else{
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
							}
						}
						if($scope.spittedDate>date){
					$state.go('main.add_appointmentForCalender',{
					 calenderScheduleObj: event
				 });
                    	}}
					else{
                		toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};toastr
							.error(
									'',
									'Your Selected Date Should be Greater Than The Present System Date');
					
                	}
                    /*if($scope.spittedDate==date){
                    	if($scope.spittedtime>=time){
					$state.go('main.add_appointmentForCalender',{
					 calenderScheduleObj: event
				 });}
                    	else{
                    		toastr.options = {
    								closeButton : true,
    								progressBar : true,
    								showMethod : 'slideDown',
    								positionClass : "toast-top-full-width",
    								timeOut : 2000
    							};toastr
								.error(
										'',
										'Please Choose Above Current Time');
						
                    	}
                    	}else{
                    		toastr.options = {
    								closeButton : true,
    								progressBar : true,
    								showMethod : 'slideDown',
    								positionClass : "toast-top-full-width",
    								timeOut : 2000
    							};toastr
								.error(
										'',
										'Please Choose Current Date');
						
                    	}*/
			};
			}
			vm.eventEdited = function(event) {
				// alert.show('Edited', event);
				$state.go('main.add_appointment');
				
			};

			vm.eventDeleted = function(event) {
				alert.show('Deleted', event);
			};

			vm.eventTimesChanged = function(event) {
				alert.show('Dropped or resized', event);
			};

			vm.toggle = function($event, field, event) {
				$event.preventDefault();
				$event.stopPropagation();
				event[field] = !event[field];
			};

			vm.timespanClicked = function(date, cell) {

				if (vm.calendarView === 'month') {
					if ((vm.cellIsOpen && moment(date).startOf('day').isSame(
							moment(vm.viewDate).startOf('day')))
							|| cell.events.length === 0 || !cell.inMonth) {
						vm.cellIsOpen = false;
					} else {
						vm.cellIsOpen = true;
						vm.viewDate = date;
					}
				} else if (vm.calendarView === 'year') {
					if ((vm.cellIsOpen && moment(date).startOf('month').isSame(
							moment(vm.viewDate).startOf('month')))
							|| cell.events.length === 0) {
						vm.cellIsOpen = false;
					} else {
						vm.cellIsOpen = true;
						vm.viewDate = date;
					}
				}
			};
			
			vm.clickonMonth=function(){
				var val= moment(vm.viewDate);
			};
			$scope.getAllSchedulesByTodayDate=function(){
					$scope.calendarDto={
							"sourceType":vm.calendarView,
							"sourceDate":vm.calendarTitle,
							"username":$rootScope.loggedUsername
					};
				//console.log(JSON.stringify($scope.calendarDto));
				scheduleService.getAllSchedulesByTodayDate($scope.calendarDto).then(function(response){
				$scope.listofSchedules=response.data;
				vm.events=[];
				angular.forEach($scope.listofSchedules, function(schedule) {
					$scope.scheduleName="Free";
					if($rootScope.loggedUserRole =="Therapist"){
						$scope.scheduletitle="Free";
					}else if($rootScope.loggedUserRole =="Individual"){
						$scope.scheduletitle="Free";
					}else{
						$scope.scheduletitle="Free-"+schedule.doctorName+"-"+schedule.doctor.department.departmentName;
					}
					 if(schedule.scheduleStatus=='AVAILABILITY'){
						var	color1 = calendarConfig.colorTypes.info
							}
						if(schedule.scheduleStatus=="APPOINTED"){
							var	color1= calendarConfig.colorTypes.danger
							//$scope.scheduletitle="busy-"+schedule.doctorName;
							}
					vm.events.push({
						doctor:schedule.doctor,
						 title : $scope.scheduletitle,
						 type: 'info',
						 value :$scope.scheduleName,
						startsAt:schedule.startDate,
						 endsAt:schedule.endDate,
						 color : calendarConfig.colorTypes.info,
						draggable : false,
						resizable : true,
						actions : actions
					});
					
				})
				
				$scope.getSubAppointmentssByRoleforCalendarView();
				$scope.getHolidaysByRole();
				
				},function(error){
					
				})
			};
			
			//for indvidual
			
			

		
		    calendarConfig.templates.calendarMonthCell = 'groupedMonthEvents.html';

		    $scope.$on('$destroy', function() {
		      calendarConfig.templates.calendarMonthCell = 'mwl/calendarMonthCell.html';
		    });


		    vm.viewDate = moment().startOf('month').toDate();

		    vm.groupEvents = function(cell) {
		      cell.groups = {};
		      cell.events.forEach(function(event) {
		        cell.groups[event.type] = cell.groups[event.type] || [];
		        cell.groups[event.type].push(event);
		      });
		    };

		});