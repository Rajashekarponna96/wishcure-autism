function loginCtrl($scope, $state, loginService, therapistService,$stateParams,$http,
		userManagementSerrvice, patientService, signupService, categoryService,siteService,
		$rootScope, companyService, $rootScope, $localStorage,PATIENT_MODULE_CONFIG, successMessageHandler) {
	// $scope.getAllCategories();
	$scope.page = 0;
	$scope.size = 5;
	$scope.flash=false;
	$scope.flash1=false;
	$scope.flash2=false;
	$scope.getAllCategories = function() {
		categoryService.getAllCategories().then(function(response) {
			$scope.categoriesList = response.data;
			// $localStorage.user.role.permissions = response.data;
		}, function(error) {

		})
	};
	
	
	if ($stateParams.userName != undefined) {
		$scope.userName = $stateParams.userName;
	};
	if ($stateParams.registeredCompanies != undefined) {
		$scope.registeredCompanies = $stateParams.registeredCompanies;
	};
	
	if ($stateParams.activeAdminsAndSuperAdmin != undefined) {
		$scope.activeAdminsAndSuperAdmin = $stateParams.activeAdminsAndSuperAdmin;
	};
	if ($stateParams.inActiveAdminsAndSuperAdmin != undefined) {
		$scope.inActiveAdminsAndSuperAdmin = $stateParams.inActiveAdminsAndSuperAdmin;
	};
	
	$scope.doLogin = function(user) {
		user.loginIp=$scope.userIpAddress;
		delete $localStorage.user;
		if (user == undefined) {
                $scope.message = "Please Enter Valid Credentials!";
		} else {
			var tokenUsername= btoa(user.username);
			var tokenPassword=btoa(user.password);
			loginService
					.doLogin(user)
					.then(
							function(response) {
								$localStorage.user = response.data;
								$localStorage.$save();
								// alert($localStorage.user);
								$scope.userObj = response.data;

								$rootScope.loggedUsername = $localStorage.user.userName;
								$rootScope.loggedUserRole = $localStorage.user.role.roleName;
								$rootScope.loggedFirstname = $localStorage.user.firstname;
								$rootScope.loggedLastname = $localStorage.user.lastname;
								$rootScope.ProfilePicimagePath = $localStorage.user.imagePath;
								//console.log("User details "+JSON.stringfy($scope.userObj));
							   // alert("Token "+$scope.userObj.token);
								// alert($rootScope.loggedUserRole);

								
								/*
								 * if (response.data.role.roleName =='Super
								 * Admin') { $state.go('main.dashboard'); } if
								 * (response.data.role.roleName =='Product
								 * Owner') { $state.go('main.addCompanyType'); }
								 */
								if ($scope.userObj.ipExist == false) {
									$state.go('otp', {
										UserObject : $scope.userObj
									})
									alert("Token "+$scope.userObj.token);
									toastr.options = {
										closeButton : true,
										progressBar : true,
										showMethod : 'slideDown',
										positionClass : "toast-top-full-width",
										timeOut : 2000
									};
									toastr 
											.success('',
													successMessageHandler.LOGIN_OTP_SUCCESS);

								}
								
								if ($scope.userObj.ipExist == true) {
									if($scope.userObj.ipaddress != $scope.userIpAddress){
										$state.go('otp', {
											UserObject : $scope.userObj
										})
										toastr.options = {
											closeButton : true,
											progressBar : true,
											showMethod : 'slideDown',
											positionClass : "toast-top-full-width",
											timeOut : 2000
										};
										
										toastr.success('',successMessageHandler.LOGIN_OTP_SUCCESS);
										
									}
									
									if ($rootScope.loggedUserRole == "Therapist") {
										$state.go('main.dashboard');
									} else if($rootScope.loggedUserRole == "Parent1"){
										$state.go('main.ParentDashboard');
									} else if($rootScope.loggedUserRole == "Facility Manager"){
										$state.go('main.dashboard');
									}else{
										$state.go('main.dashboard');
									}

									toastr.options = {
										closeButton : true,
										progressBar : true,
										showMethod : 'slideDown',
										positionClass : "toast-top-full-width",
										timeOut : 2000
									};
									toastr.success('', successMessageHandler.LOGIN_SUCCESS);

								}

							},
							function(error) {
								$scope.message1 =JSON.stringify(error.data.message.trim());
								$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
							});
		}
	};
	$scope.getImagePathData = function() {
		return PATIENT_MODULE_CONFIG.GET_USER_IMAGEPATHDATA_S3($rootScope.loggedUsername);
	};
	
	$scope.getImageS3 = function() {
		$scope.adminUserName = $rootScope.loggedUsername;
		loginService.getImageFromS3($scope.adminUserName).then(
				function(response) {
					$scope.getImage = response.data;
					$scope.getImageUrl = $scope.getImage.location;
				}, function(error) {
				})
	};
	
	/*$scope.getImagePathData = function() {
		$scope.adminUserName = $rootScope.loggedUsername;
		companyService.getImagePathData($scope.adminUserName).then(
				function(response) {
					response.data;
				}, function(error) {
				})
	};*/

	$scope.arrayBufferToBase64 = function(buffer) {
		var binary = '';
		var bytes = new Uint8Array(buffer);
		var len = bytes.byteLength;
		for (var i = 0; i < len; i++) {
			binary += String.fromCharCode(bytes[i]);
		}
		return window.btoa(binary);
	}

	$scope.hasFeature = function(name) {
		$scope.userObject = $localStorage.user;
		var found = undefined;
		angular.forEach($scope.userObject.role.permissions, function(feature) {
			if (feature.category.categoryName == name) {
				found = feature;
			}
		});
		return found;
	};
//Start-ParentDashboard-lineChart
	$scope.labels2 = ["January", "February", "March", "April", "May", "June", "July"];
	  $scope.series2 = ['Series A', 'Series B'];
	  $scope.data2 = [
	    [65, 59, 80, 81, 56, 55, 40],
	    [28, 48, 40, 19, 86, 27, 90]
	  ];
	  $scope.onClick = function (points, evt) {
	  };
	  $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }, { yAxisID: 'y-axis-2' }];
	  $scope.options = {
	    scales: {
	      yAxes: [
	        {
	          id: 'y-axis-1',
	          type: 'linear',
	          display: true,
	          position: 'left'
	        },
	        {
	          id: 'y-axis-2',
	          type: 'linear',
	          display: true,
	          position: 'right'
	        }
	      ]
	    }
	  };
	  
//End-ParentDashboard-lineChart
	  
	  
//Start-ParentDashboard-barChart
		 $scope.labels1 = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
		  $scope.series1 = ['Series A', 'Series B'];

		  $scope.data1 = [
		    [65, 59, 80, 81, 56, 55, 40],
		    [28, 48, 40, 19, 86, 27, 90]
		  ];
//End-ParentDashboard-barChart
		  
		  
	  $scope.$on('loader_show', function(event, args) {
			$rootScope.loader = true;
		});
		$scope.$on('loader_hide', function(event, args) {
			$rootScope.loader = false;
		});
		
		
//Start-Dashboard-lineChart
	$scope.getActiveDoctorsChartDataPerPresentYear = function() {
		$scope.adminUserName = $rootScope.loggedUsername;
		var currentTime = new Date();
		// returns the year (four digits)
		var year = currentTime.getFullYear();
		loginService.getActiveDoctorsChartDataPerPresentYear($scope.adminUserName,year).then(
				function(response) {
					$scope.labels = response.data.labels;
					$scope.series = response.data.series;
					$scope.data = response.data.data;
					
				}, function(error) {
				})
	};
	$scope.getActiveUsersChartDataPerPresentYear = function() {
		$scope.adminUserName = $rootScope.loggedUsername;
		var currentTime = new Date();
		// returns the year (four digits)
		var year = currentTime.getFullYear();
		loginService.getActiveUsersChartDataPerPresentYear($scope.adminUserName,year).then(
				function(response) {
					$scope.labels5 = response.data.labels;
					$scope.series5 = response.data.series;
					$scope.data5 = response.data.data;
					
				}, function(error) {
				})
	};
	
	$scope.getTotalPatientsRegisteredChartDataPerPresentYear = function() {
		$scope.adminUserName = $rootScope.loggedUsername;
		var currentTime = new Date();
		// returns the year (four digits)
		var year = currentTime.getFullYear();
		loginService.getTotalPatientsRegisteredChartDataPerPresentYear($scope.adminUserName,year).then(
				function(response) {
					$scope.labels6 = response.data.labels;
					$scope.series6 = response.data.series;
					$scope.data6 = response.data.data;
					
				}, function(error) {
				})
	};
	
	  $scope.onClick = function (points, evt) {
	  };
	  $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }];
	  $scope.options = {
	    scales: {
	      yAxes: [
	        {
	          id: 'y-axis-1',
	          type: 'linear',
	          display: true,
	          position: 'left'
	        }
	      ]
	    }
	  };
//Start-Dashboard-lineChart
	 
	//End-Dashboard-barChart 
	  $scope.getTodayPatientsData = function() {
			$scope.adminUserName = $rootScope.loggedUsername;
			loginService.getTodayPatientsData($scope.adminUserName).then(
					function(response) {
						$scope.labels3 = response.data.labels;
						 $scope.series3= response.data.series;
						 $scope.data3 = response.data.data;
						
					}, function(error) {
					})
		};
//End-Dashboard-barChart 
		
		$scope.getAllactiveAdmins = function() {
			$scope.adminUsername = $rootScope.loggedUsername;
			loginService.getAllactiveAdmins($scope.adminUsername).then(
					function(response) {
						$scope.activeAdminsList = response.data;
						$scope.numberOfActiveAdmins = $scope.activeAdminsList;
						//alert("305:"+$scope.numberOfActiveAdmins );
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		
		$scope.getAllInactiveAdmins = function() {
			$scope.adminUsername = $rootScope.loggedUsername;
			loginService.getAllInactiveAdmins($scope.adminUsername).then(
					function(response) {
						$scope.inActiveAdminsList = response.data;
						$scope.numberOfInActiveAdmins = $scope.inActiveAdminsList;
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		$scope.getAllactiveSiteAdmins = function() {
			$scope.adminUsername = $rootScope.loggedUsername;
			loginService.getAllactiveSiteAdmins($scope.adminUsername).then(
					function(response) {
						$scope.activeSiteAdminsList = response.data;
						$scope.numberOfActiveSiteAdmins = $scope.activeSiteAdminsList;
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		
		$scope.getAllInactiveSiteAdmins = function() {
			$scope.adminUsername = $rootScope.loggedUsername;
			loginService.getAllInactiveSiteAdmins($scope.adminUsername).then(
					function(response) {
						$scope.inActiveSiteAdminsList = response.data;
						$scope.numberOfInActiveSiteAdmins = $scope.inActiveSiteAdminsList;
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		
		$scope.getAllActiveTherapists = function() {
			$scope.adminUsername = $rootScope.loggedUsername;
			therapistService.getAllActiveTherapists($scope.adminUsername).then(
					function(response) {
						$scope.activeTherapistList = response.data;
						$scope.numberOfActiveTherapists = $scope.activeTherapistList;
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		
		$scope.getAllInactiveTherapists = function() {
			$scope.adminUsername = $rootScope.loggedUsername;
			therapistService.getAllInactiveTherapists($scope.adminUsername).then(
					function(response) {
						$scope.inActiveTherapistList = response.data;
						$scope.numberOfInaciveTherapists = $scope.inActiveTherapistList;
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		// 718 Site Count
		$scope.getAllActiveSites = function() {
			$scope.adminUsername = $rootScope.loggedUsername;
			siteService.getAllActiveSites($scope.adminUsername).then(
					function(response) {
						$scope.activeSitesList = response.data;
						$scope.numberOfActiveSites = $scope.activeSitesList;
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		
		$scope.getAllInactiveSites = function() {
			$scope.adminUsername = $rootScope.loggedUsername;
			siteService.getAllInactiveSites($scope.adminUsername).then(
					function(response) {
						$scope.inActiveSiteList = response.data;
						$scope.numberOfInActiveSites = $scope.inActiveSiteList;
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		//Site Count Ending
		
		
		$scope.getAllAppointmentsCountByRole = function() {
			$scope.adminUsername = $rootScope.loggedUsername;
			loginService.getAllAppointmentsCountByRole($scope.adminUsername).then(
					function(response) {
						$scope.noOfAppointmentsList = response.data;
						$scope.numberOfAppointments = $scope.noOfAppointmentsList;
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		

		$scope.getAllRegistrations = function() {
			$scope.adminUserName = $rootScope.loggedUsername;
			userManagementSerrvice.getAllRegistrations($scope.adminUserName).then(
					function(response) {
						$scope.userList = response.data;
						$scope.numberOfRegisteredUsers = $scope.userList;

					}, function(error) {
					});
		};
		

		$scope.totalPatientsRegistered = function() {
			$scope.adminUserName = $rootScope.loggedUsername;
			patientService.totalPatientsRegistered($scope.adminUserName).then(function(response) {
				$scope.totalPatientsRegistered = response.data;
			}, function(error) {})
		};
		$scope.inActivePatients = function() {
			$scope.adminUserName = $rootScope.loggedUsername;
			patientService.inActivePatients($scope.adminUserName).then(function(response) {
				$scope.inActivePatients = response.data;
			}, function(error) {})
		};
		
		$scope.getNewPatientsCount = function() {
			$scope.adminUserName = $rootScope.loggedUsername;
			patientService.getNewPatientsCount($scope.adminUserName).then(
					function(response) {
						$scope.count = response.data;
					}, function(error) {
					})
		};
		
		
		if($scope.userName!=null){
		
		$scope.getAllactiveAdminsForInternalDashboard = function() {
			loginService.getAllactiveAdmins($scope.userName.userAccount.username).then(
					function(response) {
						$scope.activeAdminsList = response.data;
						$scope.numberOfActiveAdmins = $scope.activeAdminsList;
						//alert("420:"+$scope.numberOfActiveAdmins );
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		
		$scope.getAllActivepersonsForInternalDashboard = function() {
			loginService.getAllActivepersonsForInternalDashboard($scope.userName.userAccount.username).then(
					function(response) {
						$scope.activePersonsList = response.data;
					//	$scope.numberOfActiveAdmins = $scope.activePersonsList;
					//	alert("420:"+$scope.numberOfActiveAdmins );
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		$scope.getAllInactivepersonsForInternalDashboard = function() {
			loginService.getAllInactivepersonsForInternalDashboard($scope.userName.userAccount.username).then(
					function(response) {
						$scope.inActivePersonsList = response.data;
					//	$scope.numberOfActiveAdmins = $scope.activeAdminsList;
						//alert("420:"+$scope.numberOfActiveAdmins );
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		
		$scope.getAllInactiveAdminsForInternalDashboard = function() {
			loginService.getAllInactiveAdmins($scope.userName.userAccount.username).then(
					function(response) {
						$scope.inActiveAdminsList = response.data;
						$scope.numberOfInActiveAdmins = $scope.inActiveAdminsList;
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		
		
		$scope.getAllActiveTherapistsForInternalDashboard = function() {
			therapistService.getAllActiveTherapists($scope.userName.userAccount.username).then(
					function(response) {
						$scope.activeTherapistList = response.data;
						$scope.numberOfActiveTherapists = $scope.activeTherapistList;
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		
		$scope.getAllInactiveTherapistsForInternalDashboard = function() {
			therapistService.getAllInactiveTherapists($scope.userName.userAccount.username).then(
					function(response) {
						$scope.inActiveTherapistList = response.data;
						$scope.numberOfInaciveTherapists = $scope.inActiveTherapistList;
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		
		
		$scope.getAllAppointmentsCountByRoleForInternalDashboard = function() {
			loginService.getAllAppointmentsCountByRole($scope.userName.userAccount.username).then(
					function(response) {
						$scope.noOfAppointmentsList = response.data;
						$scope.numberOfAppointments = $scope.noOfAppointmentsList;
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};
		

		$scope.getAllRegistrationsForInternalDashboard = function() {
			userManagementSerrvice.getAllRegistrations($scope.userName.userAccount.username).then(
					function(response) {
						$scope.userList = response.data;
						$scope.numberOfRegisteredUsers = $scope.userList;

					}, function(error) {
					});
		};
		

		$scope.totalPatientsRegisteredForInternalDashboard = function() {
			patientService.totalPatientsRegistered($scope.userName.userAccount.username).then(function(response) {
				$scope.totalPatientsRegistered = response.data;
			}, function(error) {})
		};
		$scope.inActivePatientsForInternalDashboard = function() {
			patientService.inActivePatients($scope.userName.userAccount.username).then(function(response) {
				$scope.inActivePatients = response.data;
			}, function(error) {})
		};
		
		$scope.getNewPatientsCountForInternalDashboard = function() {
			patientService.getNewPatientsCount($scope.userName.userAccount.username).then(
					function(response) {
						$scope.count = response.data;
					}, function(error) {
					})
		};
		
		
		}
		
		
		
		$scope.logout=function(){
			delete $localStorage.user;
			//$state.go('index.html');
		};

		$scope.help=function(){
			$state.go('main.help');
		};
		
		$scope.gotoLogin=function(){
			$state.go('login');
		};
		$scope.gotoRegistration=function(){
			$state.go('signup');
		};

		$scope.chartOptions = {
		        palette: "bright",
		        dataSource: [{
		            country: "Used GB",
		            medals: 500
		        }, {
		            country: "Unused GB",
		            medals: 100
		        }],
		        title: "Data Utilization(GB)",
		        legend: {
		            orientation: "horizontal",
		            itemTextPosition: "right",
		            horizontalAlignment: "right",
		            verticalAlignment: "bottom",
		            columnCount: 4
		        },
		        "export": {
		            enabled: true
		        },
		        series: [{
		            argumentField: "country",
		            valueField: "medals",
		            label: {
		                visible: true,
		                font: {
		                    size: 16
		                },
		                connector: {
		                    visible: true,
		                    width: 0.5
		                },
		                position: "columns",
		                customizeText: function(arg) {
		                    return arg.valueText +" GB" + " (" + arg.percentText + ")";
		                }
		            }
		        }]
		    };
		
		$scope.gotoRegistration=function(){
			$state.go('signup.blank');
		};
		
		//data related to dashboard ng-click 
		$scope.gotoActiveAdmins = function(){
			$scope.activeAdmins=true;
			$state.go('main.usermanagement_list',{
				activeAdminsObject : $scope.activeAdmins
				});
		};	
		$scope.gotoInActiveAdmins = function(){
			$scope.inActiveAdmins=true;
			$state.go('main.usermanagement_list',{
				inActiveAdminsObject : $scope.inActiveAdmins
				});
		};
		$scope.gotoActiveUsersOfSite == function(){
			$scope.activeUsersOfSite=true;
			$state.go('main.usermanagement_list',{
				activeUsersOfSiteObject : $scope.activeUsersOfSite,
				siteObj : site
				});
		};
		$scope.gotoInActiveUsersOfSite == function(){
			$scope.inActiveUsersOfSite=true;
			$state.go('main.usermanagement_list',{
				inActiveUsersOfSiteObject : $scope.inActiveUsersOfSite
				});
		};
		$scope.gotoactiveTherapists = function(){
			$scope.activeTherapists=true;
			$state.go('main.usermanagement_list',{
				activeTherapistsObject : $scope.activeTherapists
				});
		};
		$scope.gotoinActiveTherapists = function(){
			$scope.inActiveTherapists=true;
			$state.go('main.usermanagement_list',{
				inActiveTherapistsObject : $scope.inActiveTherapists
				});
		};
		$scope.gotoactiveTherapistsOfSite = function(){
			$scope.activeTherapists=true;
			$state.go('main.usermanagement_list',{
				activeTherapistsOfSiteObject : $scope.activeTherapistsOfSite
				});
		};
		$scope.gotoinActiveTherapistsOfSite = function(){
			$scope.inActiveTherapists=true;
			$state.go('main.usermanagement_list',{
				inActiveTherapistsOfSiteObject : $scope.inActiveTherapistsOfSite
				});
		};
		//718
		$scope.gotoActiveSiteList = function(){
			$scope.activeSites=true;
			$state.go('main.siteList',{
				activeSitesObject : $scope.activeSites
				});
		};
		$scope.gotoInActiveSiteList = function(){
			$scope.inActiveSites=true;
			$state.go('main.siteList',{
				inActiveSitesObject : $scope.inActiveSites
				});
		};
		$scope.gotousers = function(){
			$state.go('main.usermanagement_list')
		};
		$scope.gotoActiveTherapists = function(){
			$state.go('main.usermanagement_list')
		};
		$scope.gotoInActiveTherapists = function(){
			$state.go('main.usermanagement_list')
		};
		$scope.gotopatientList = function(){
			$state.go('main.patient_list')
		};
		$scope.gotopatientListactive = function(){
			$state.go('main.patient_list')
		};

		$scope.gotopatientListInactive = function(){
			$scope.inActivePatients=true;
			$state.go('main.patient_list',{
				inActivePatientsObject : $scope.inActivePatients
				});
		};
		$scope.gotoAppointments = function(){
			$state.go('main.appointment_list')
		};
		$scope.gototTodaypatientList = function(){
			$scope.todayPatients=true;
			$state.go('main.patient_list',{
				todayPatientsObject : $scope.todayPatients
				});
		};
		$scope.getAllEnterPrisesCompanies = function(registrationType) {
			if(registrationType==undefined){
				registrationType='Super Admin';
			}
			userManagementSerrvice.getAllRegisteredCompanies(registrationType).then(
					function(response) {
						$scope.registeredEnterpriseCompanies = response.data;
						$scope.enterpriseList=$scope.registeredEnterpriseCompanies.length;
					}, function(error) {

					})
		};
		$scope.getAllIndividualsCompanies = function(registrationType) {
			if(registrationType==undefined){
				registrationType='Individual';
			}
			userManagementSerrvice.getAllRegisteredCompanies(registrationType).then(
					function(response) {
						$scope.registeredIndividualCompanies = response.data;
						$scope.individualsList=$scope.registeredIndividualCompanies.length;
					}, function(error) {

					})
		};
		$scope.gotoRegisteredEnterPrisesCompanies = function(){
			$scope.registeredEnterpriseCompanies=true;
			$scope.type="Super Admin";
			$rootScope.type="Super Admin";
			//alert("in Login:"+$scope.type)
			$state.go('main.registeredCompanies',{
				registeredEnterpriseCompanies : $scope.registeredEnterpriseCompanies,
				type:$scope.type
				
			});
		};
		$scope.gotoRegisteredIndividualsCompanies = function(){
			$scope.registeredIndividualCompanies=true;
			$scope.type="Individual";
			$rootScope.type="Individual";
			//alert("in Login:"+$scope.type)
			$state.go('main.registeredCompanies',{
				registeredIndividualCompanies : $scope.registeredIndividualCompanies,
				type:$scope.type
			});
		};
		$scope.goToBack= function(){
			/*$state.go('main.registeredCompanies',{
				registeredCompanies : $scope.registeredCompanies
			});*/
			$state.go('main.registeredCompaniesDashboard');
		};
		$scope.gotoAdminsList= function(){
			///$state.go('main.registeredCompaniesOverView');
			$state.go('main.registeredCompaniesDashboard');
		};
		$scope.goToBackFromLogs= function(){
			$state.go('main.internalDashboard',{
				userName : $scope.userName
			});
		};
		
		$scope.gotoActiveAdminsAndSuperAdminMethod= function(userName){
			$scope.activeAdminsAndSuperAdmin=true;
			$state.go('main.internalDashboardLogs',{
				userName : userName,
				activeAdminsAndSuperAdmin:$scope.activeAdminsAndSuperAdmin
			});
		};
		
		
		$scope.gotoInactiveAdminsAndSuperAdminMethod= function(userName){
			$scope.inActiveAdminsAndSuperAdmin=true;
			$state.go('main.internalDashboardLogsInactive',{
				userName : userName,
				inActiveAdminsAndSuperAdmin:$scope.inActiveAdminsAndSuperAdmin
			});
		};
		
		$scope.gotoTotalUsers= function(userName){
			$state.go('main.internalDashboardLogsAll',{
				userName : userName,
				inActiveAdminsAndSuperAdmin:$scope.inActiveAdminsAndSuperAdmin
			});
		};
		
		
		$scope.goactiveTherapistsMethod= function(userName){
			$scope.inActiveTherapist=true;
			$state.go('main.internalDashboardLogs',{
				userName : userName,
				inActiveTherapist:$scope.inActiveTherapist
			});
		};
		
		$scope.goactiveTherapistsMethod= function(userName){
			$scope.activeTherapist=true;
			$state.go('main.internalDashboardLogs',{
				userName : userName,
				activeTherapist:$scope.activeTherapist
			});
		};
		
		if($scope.activeAdminsAndSuperAdmin!=null){
		$scope.gotoActiveAdminsAndSuperAdmin=function(){
			userManagementSerrvice.getAllusersBySuperAdminAndAdmin($scope.userName.email, "Super Admin", true
				).then(function(response) {
				$scope.userLists = response.data;
				$state.go('main.internalDashboardLogs');
			}, function(error) {
			});
			
		};
		};
		
		$scope.active =function(person){
			//alert(person.active)
			userManagementSerrvice.active(person).then(function(response) {
				toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 2000
				};
				if(person.active=='false'){
					toastr.error('', ' Company is going to be InActive...All the Users under the Company will be Stopped!!');
				}else{
					toastr.success('', ' Data Updated Successfully!!');
						
				}
				//$state.go('main.departmentlist');
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		
		}
		if($scope.inActiveAdminsAndSuperAdmin!=null){
		$scope.gotoInactiveAdminsAndSuperAdmin=function(){
			userManagementSerrvice.getAllusersBySuperAdminAndAdmin($scope.userName.email, "Super Admin", false
				).then(function(response) {
				$scope.userLists = response.data;
				$state.go('main.internalDashboardLogs');
			}, function(error) {
			});
			
		};
		};
		
		/*$scope.gotoInvoicePage = function(email) {
			alert(email)
			$state.go('main.stripeInvoice', {
				email : email
			});
		};*/
		$scope.appointmentslistes = function() {
			$state.go('main.appointment_listReports');
		};
		$scope.gotoInvoicePage1 = function(userName) {
			/*
			 * if(pa.invoice!=null){ $rootScope.invoiceId=pa.invoice; }
			 */
			//alert("userName:"+userName);
			$rootScope.username11 = userName;
			$state.go('main.stripeInvoice', {
				userName : userName
			// userObject : pa,
			});
		};
		$scope.gotoInvoicePage = function(userName) {
			/*
			 * if(pa.invoice!=null){ $rootScope.invoiceId=pa.invoice; }
			 */
			//alert("userName:"+userName);
			$rootScope.username11 = userName.email;
			$state.go('main.stripeInvoice', {
				userName : userName
			// userObject : pa,
			});
		};
		
		$scope.getStripePayments = function() {
//alert("username11:;"+$rootScope.username11)
			//patientService.getStripePayments($rootScope.username).then(
			patientService.getStripePayments($rootScope.username11).then(	
			function(response) {
						$scope.invoiceList = response.data;

					}, function(error) {
						toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-right",
							timeOut : 2000
						};
						$scope.message = JSON.stringify(error.data.message.trim());
						toastr.error($scope.message, 'Error');
						//$state.go('main.listofpayments')
					});
		};
		$scope.changeMobileNumber = function(mobileNumber) {
			if (mobileNumber == 0) {
				var number = "";
				return number;
			}
			var currentNumber = mobileNumber.toString();

			changedNumber = "(" + currentNumber.substring(0, 3) + ")"
					+ currentNumber.substring(3, 6) + "-"
					+ currentNumber.substring(6, 10);
			return changedNumber;
		}
		$scope.invoiceStripe = $stateParams.invoiceObject;
		$scope.gotoInvoice = function(invoice) {
			/*
			 * if(pa.invoice!=null){ $rootScope.invoiceId=pa.invoice; }
			 */
			$state.go('main.stripeSingleInvoice', {
				invoiceObject : invoice,
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
			// alert(size)
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
			//if($rootScope.loggedUsername=='gowripathi@gmail.com'){
			if($scope.flash==true){
				$scope.getAllActiveAndInactivePersonsByRegType();	
			}
			
			if($scope.flash1==true){
					$scope.getAllInactivePersonsByRegType();	
				}
			
			if($scope.flash2==true){
					$scope.getAllusersPage();	
				}
			if ($rootScope.compFlag == false) {
				/*alert("compFlag Falllllssseeeee")
				alert("compFlag Falllllssseeeee::"+$rootScope.user)*/
				$scope.registeredEnterpriseCompaniesForAdmin($rootScope.user);
			}
			if ($rootScope.compFlag == true) {
				//alert("compFlag trueeeeeee")
				$scope.getRegisteredCompaniesFilter($rootScope.personsCompany);
			}
			
		});

		// //////
		
		$scope.getAllActiveAndInactivePersonsByRegType = function() {
			$scope.flash=true;
		
			userManagementSerrvice.getAllActiveAndInactivePersonsByRegType($rootScope.userEmail,$scope.page,$scope.size)
					.then(function(response) {
						$scope.usersLists = response.data.content;
						$scope.totalElements = response.data.totalElements;
						$scope.totalPages = response.data.totalPages-1;
						$scope.noOfPgaes(response.data.totalPages);
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};	
		
		$scope.getAllInactivePersonsByRegType = function() {
			$scope.flash1=true;
		
			userManagementSerrvice.getAllInactivePersonsByRegType($rootScope.userEmail,$scope.page,$scope.size)
					.then(function(response) {
						$scope.usersListsInactive = response.data.content;
						$scope.totalElements = response.data.totalElements;
						$scope.totalPages = response.data.totalPages-1;
						$scope.noOfPgaes(response.data.totalPages);
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
					});
		};	
		
		
		$scope.getAllusersPage = function() {
			$scope.flash2=true;
			$scope.adminUserName = $rootScope.loggedUsername;
			userManagementSerrvice.getAllusersPage($rootScope.userEmail,
					$scope.page, $scope.size).then(function(response) {
				$scope.userLists = response.data.content;
				$scope.totalElements = response.data.totalElements;
				$scope.totalPages = response.data.totalPages - 1;
				$scope.noOfPgaes(response.data.totalPages);
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		};
		
		$scope.type = $stateParams.type;
		$scope.registeredEnterpriseCompaniesForAdmin = function(registrationType) {
			// registrationType='Super Admin';
			//alert("941:"+registrationType)
			$rootScope.compFlag = false;
			$rootScope.user = registrationType;
			// alert("Call")
			userManagementSerrvice.getAllRegisteredCompaniesPage(registrationType,
					$scope.page, $scope.size).then(function(response) {
				$scope.registeredCompanies = response.data.content;
				/*if($scope.registeredCompanies.length<1){
					alert("true its Zero")	
					$scope.flash=true;
				}*/
				// alert(JSON.stringify($scope.registeredCompanies ));
				$scope.totalElements = response.data.totalElements;
				$scope.totalPages = response.data.totalPages - 1;
				$scope.noOfPgaes(response.data.totalPages);

				// alert("Super::"+$scope.registeredCompanies.length)
			}, function(error) {

			})
			// }
		};
		
		/*$scope.user = $stateParams.userObj;

		$scope.type = $stateParams.type;
		$scope.gotoAppointmentViewPage = function(user) {
			$rootScope.userId = user.id;
			$state.go('main.therapistAppointmentsList', {
				userObj : user
			});
		}*/
		
		$scope.getRegisteredCompaniesFilter = function(person) {
		$rootScope.compFlag = true;
		// alert("Filter Call")
		$rootScope.personsCompany = {
			"startDate" : person.startDate,
			"endDate" : person.endDate,
			"status" : person.status,
			"user" : $rootScope.user
		}
		if (typeof $rootScope.personsCompany.startDate != 'string') {
			if (typeof $rootScope.personsCompany.endDate != 'string') {
				if ($rootScope.personsCompany.startDate != undefined
						&& $rootScope.personsCompany.endDate != undefined) {
					var date1 = $rootScope.personsCompany.startDate;
					mnth = ("0" + (date1.getMonth() + 1)).slice(-2),
							day = ("0" + date1.getDate()).slice(-2);
					var changepaiddate = [ day, mnth, date1.getFullYear() ]
							.join("-");
					$rootScope.personsCompany.startDate = changepaiddate;

					var date = $rootScope.personsCompany.endDate;
					mnth = ("0" + (date.getMonth() + 1)).slice(-2),
							day = ("0" + date.getDate()).slice(-2);
					var changedate = [ day, mnth, date.getFullYear() ]
							.join("-");
					$rootScope.personsCompany.endDate = changedate;
				}
			}
		}
		userManagementSerrvice.getRegisteredCompaniesFilter(
				$rootScope.personsCompany, $scope.page, $scope.size).then(
				function(response) {
					$scope.registeredCompanies = response.data.content;
					/*if($scope.registeredCompanies.length<1){
						alert("true its Zero in getRegisteredCompaniesFilter")	
						$scope.flash=true;
						alert($scope.flash)
					}*/
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages - 1;
					$scope.noOfPgaes(response.data.totalPages);
					person.startDate = undefined;
					person.endDate = undefined;
					person.status = undefined;
				}, function(error) {
					// alert("error")
					person.startDate = undefined;
					person.endDate = undefined;
					person.status = undefined;
				})
	};
	
	$scope.getUsersBasedOnCompany1 = function(userName, registeredCompanies) {
	//	alert(userName.email);
		$rootScope.userEmail=userName.email;
		$state.go('main.registeredCompaniesOverView', {
			userName : userName,
			registeredCompanies : registeredCompanies
		});
	};
	
	$scope.gotoDashboard= function() {
		//alert("last:"+JSON.stringify($scope.userName));
		$state.go('main.registeredCompaniesOverView', {
			userName : $scope.userName,
			registeredCompanies : $scope.registeredCompanies
		});
	};
	
	$scope.gotoDashboard1= function() {
		//alert("last:"+JSON.stringify($scope.userName));
		$state.go('main.dashboard');
	};
	$scope.gotoregisteredCompanies= function() {
		//alert("last:"+JSON.stringify($scope.userName));
		$state.go('main.registeredCompanies', {
			registeredEnterpriseCompanies : $scope.registeredEnterpriseCompanies,
			type:$rootScope.type
		});
	};
	
	
	
	/**
	 * Get the user IP throught the webkitRTCPeerConnection
	 * @param onNewIP {Function} listener function to expose the IP locally
	 * @return undefined
	 */
	function getUserIP(onNewIP) { //  onNewIp - your listener function for new IPs
	    //compatibility for firefox and chrome
	    var myPeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection;
	    var pc = new myPeerConnection({
	        iceServers: []
	    }),
	    noop = function() {},
	    localIPs = {},
	    ipRegex = /([0-9]{1,3}(\.[0-9]{1,3}){3}|[a-f0-9]{1,4}(:[a-f0-9]{1,4}){7})/g,
	    key;

	    function iterateIP(ip) {
	        if (!localIPs[ip]) onNewIP(ip);
	        localIPs[ip] = true;
	    }

	     //create a bogus data channel
	    pc.createDataChannel("");

	    // create offer and set local description
	    pc.createOffer(function(sdp) {
	        sdp.sdp.split('\n').forEach(function(line) {
	            if (line.indexOf('candidate') < 0) return;
	            line.match(ipRegex).forEach(iterateIP);
	        });
	        
	        pc.setLocalDescription(sdp, noop, noop);
	    }, noop); 

	    //listen for candidate events
	    pc.onicecandidate = function(ice) {
	        if (!ice || !ice.candidate || !ice.candidate.candidate || !ice.candidate.candidate.match(ipRegex)) return;
	        ice.candidate.candidate.match(ipRegex).forEach(iterateIP);
	    };
	}

	// Usage

	getUserIP(function(ip){
		$scope.userIpAddress = ip;
			document.getElementById("ip").innerHTML = 'Got your IP ! : '  + ip + " | verify in http://www.whatismypublicip.com/";
	});
	
}

angular.module("HealthApplication").controller("loginCtrl", loginCtrl);
