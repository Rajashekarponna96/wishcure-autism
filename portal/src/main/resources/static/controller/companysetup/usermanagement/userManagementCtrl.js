//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function userManagementCtrl($scope, $state, userManagementSerrvice,
		scheduleService, patientService, siteService, therapistService,
		roleService, departmentService, appointmentService, countryService,
		$rootScope, $stateParams, companyService, PATIENT_MODULE_CONFIG,
		successMessageHandler) {
	$scope.page = 0;
	$scope.size = 5;
	$scope.addUser = {};
	$scope.address1 = {};
	// $scope.flash = false;
	$scope.flag = false;
	$scope.flag1 = false;

	if ($stateParams.userObj != undefined) {
		$scope.user = $stateParams.userObj;
		$scope.user.directoryPath = "false";
	}

	$rootScope.personsCompany = {
		"startDate" : undefined,
		"endDate" : undefined,
		"status" : undefined,
		"user" : undefined
	}

	$scope.getImagePathData = function(ProfilePicimagePath) {
		return PATIENT_MODULE_CONFIG.GET_IMAGE_PATH_DATA(ProfilePicimagePath);
	};
	$scope.getImagePathData1 = function(name) {
		return PATIENT_MODULE_CONFIG.GET_USER_IMAGEPATHDATA(name);
	}
	$scope.sort = function(keyname) {
		$scope.sortKey = keyname; // set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; // if true make it false and vice
		// versa
	}

	$scope.fileChanged = function(files) {
		if (files != null) {
			var file = files[0];
			$scope.filepath = files[0];
			companyService.uploadImage($scope.filepath).then(
					function(response) {
						$scope.addUser.signaturePath = response.data.imagePath;
						$scope.imageUrl = $scope.addUser.signaturePath;
						$scope.viewImagePathData($scope.imageUrl);
						// $scope.getImagePathData($scope.user.signaturePath);
					}, function(eror) {
					});
		}
	};

	$scope.loadSignature = function() {
		if ($scope.signatureForTherapist != undefined) {
			$scope.viewImagePathData($scope.signatureForTherapist);
		} else {
			$scope.getImagePathDataByUser();
		}
	};

	$scope.getImagePathDataByUser = function() {
		userManagementSerrvice.getImagePathDataByUser($scope.user.email).then(
				function(response) {
					$scope.imageurl = response.data.location;

				}, function(error) {
				});
	};

	$scope.viewImagePathData = function(signatureForTherapist) {
		return PATIENT_MODULE_CONFIG
				.VIEW_USER_IMAGEPATHDATA(signatureForTherapist);
	};

	$scope.updateFileChanged = function(files) {
		if (files != null) {
			var file = files[0];
			$scope.filepath = files[0];
			companyService
					.uploadImage($scope.filepath)
					.then(
							function(response) {
								$scope.user.signaturePath = response.data.imagePath;
								$scope.user.directoryPath = "true";
								$scope.signatureForTherapist = $scope.user.signaturePath;
								$scope
										.viewImagePathData($scope.signatureForTherapist);
								toastr.options = {
									closeButton : true,
									progressBar : true,
									showMethod : 'slideDown',
									positionClass : "toast-top-right",
									timeOut : 2000
								};
								toastr
										.success(
												'',
												successMessageHandler.USERMANAGEMENT_SIGN_UPLOAD_SUCCESS);
							},
							function(error) {
								toastr.options = {
									closeButton : true,
									progressBar : true,
									showMethod : 'slideDown',
									positionClass : "toast-top-right",
									timeOut : 2000
								};
								$scope.message1 = JSON
										.stringify(error.data.message.trim());
								$scope.message = $scope.message1.substring(0,
										$scope.message1.lenth);
								toastr.error($scope.message, 'Error');
							});
		}
	};

	$scope.addUserFunction = function() {
		if ($scope.addUser != undefined && $scope.addUser.role != undefined) {
			if ($scope.addUser.role.roleName == 'Therapist') {
				var address1 = document.getElementById('street_number').value;
				var address2 = document.getElementById('route').value;
				var city = document.getElementById('locality').value;
				var state = document
						.getElementById('administrative_area_level_1').value;
				var country = document.getElementById('country').value;
				var zipcode = document.getElementById('postal_code').value;
				$scope.addUser.address1 = address1;
				$scope.addUser.address2 = address2;
				$scope.addUser.city = city;
				$scope.addUser.state = state;
				$scope.addUser.country = country;
				$scope.addUser.zipcode = zipcode;
				
			}
		}
		$scope.addUser.adminUserName = $rootScope.loggedUsername;
		userManagementSerrvice.addUser($scope.addUser).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 3000
			};
			toastr.success('', successMessageHandler.SIGNUP_SUCCESS);
			$state.go('main.usermanagement_list');
		}, function(error) {
		});
	};

	$scope.addTherapist = function(id) {
		$scope.addUser.oldTherapistId = id;
		$scope.addUser.adminUserName = $rootScope.loggedUsername;
		userManagementSerrvice
				.addTherapist($scope.addUser)
				.then(
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 3000
							};
							toastr
									.success(
											'',
											successMessageHandler.SEND_EMAIL_NOTIFICATION_TO_THE_THERAPIST);

							$state.go('main.usermanagement_list');
						}, function(error) {
						});
	};
	$scope.getAllusers = function() {
		$scope.adminUserName = $rootScope.loggedUsername;
		userManagementSerrvice.getAllusers($scope.adminUserName).then(
				function(response) {
					$scope.userList = response.data;

				}, function(error) {
				});
	};
	// //////

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
	if ($stateParams.registeredCompanies != undefined) {
		$scope.registeredCompanies = $stateParams.registeredCompanies;
	}
	if ($stateParams.activeAdminsObject != undefined) {
		$scope.activeAdminsObject = $stateParams.activeAdminsObject;
	}
	if ($stateParams.inActiveAdminsObject != undefined) {
		$scope.inActiveAdminsObject = $stateParams.inActiveAdminsObject;
	}
	if ($stateParams.activeUsersOfSiteObject != undefined) {
		$scope.activeUsersOfSiteObject = $stateParams.activeUsersOfSiteObject;
	}
	if ($stateParams.inActiveUsersOfSiteObject != undefined) {
		$scope.inActiveUsersOfSiteObject = $stateParams.inActiveUsersOfSiteObject;
	}
	if ($stateParams.activeTherapistsObject != undefined) {
		$scope.activeTherapistsObject = $stateParams.activeTherapistsObject;
	}
	if ($stateParams.inActiveTherapistsObject != undefined) {
		$scope.inActiveTherapistsObject = $stateParams.inActiveTherapistsObject;
	}
	if ($stateParams.registeredEnterpriseCompanies != undefined) {
		$scope.registeredEnterpriseCompanies = $stateParams.registeredEnterpriseCompanies;
	}

	if ($stateParams.registeredIndividualCompanies != undefined) {
		$scope.registeredIndividualCompanies = $stateParams.registeredIndividualCompanies;
	}
	if ($scope.activeAdminsObject == true) {
		$scope.getAllusersPage = function() {
			$scope.adminUserName = $rootScope.loggedUsername;
			userManagementSerrvice.getAllusersPageBySuperAdminAndAdmin(
					$scope.adminUserName, "Super Admin", true, $scope.page,
					$scope.size).then(function(response) {
				$scope.userLists = response.data.content;
				$scope.totalElements = response.data.totalElements;
				$scope.totalPages = response.data.totalPages - 1;
				$scope.noOfPgaes(response.data.totalPages);
			}, function(error) {
			});
		};
	} else if ($scope.inActiveAdminsObject == true) {
		$scope.getAllusersPage = function() {
			$scope.adminUserName = $rootScope.loggedUsername;
			userManagementSerrvice.getAllusersPageBySuperAdminAndAdmin(
					$scope.adminUserName, "Super Admin", false, $scope.page,
					$scope.size).then(function(response) {
				$scope.userLists = response.data.content;
				$scope.totalElements = response.data.totalElements;
				$scope.totalPages = response.data.totalPages - 1;
				$scope.noOfPgaes(response.data.totalPages);
			}, function(error) {
			});
		};
	} else if ($scope.activeTherapistsObject == true) {
		$scope.getAllusersPage = function() {
			$scope.adminUserName = $rootScope.loggedUsername;
			userManagementSerrvice.getAllusersPageByTherapist(
					$scope.adminUserName, "Therapist", true, $scope.page,
					$scope.size).then(function(response) {
				$scope.userLists = response.data.content;
				$scope.totalElements = response.data.totalElements;
				$scope.totalPages = response.data.totalPages - 1;
				$scope.noOfPgaes(response.data.totalPages);
			}, function(error) {
			});
		};
	} else if ($scope.inActiveTherapistsObject == true) {
		$scope.getAllusersPage = function() {
			$scope.adminUserName = $rootScope.loggedUsername;
			userManagementSerrvice.getAllusersPageByTherapist(
					$scope.adminUserName, "Therapist", false, $scope.page,
					$scope.size).then(function(response) {
				$scope.userLists = response.data.content;
				$scope.totalElements = response.data.totalElements;
				$scope.totalPages = response.data.totalPages - 1;
				$scope.noOfPgaes(response.data.totalPages);
			}, function(error) {
			});
		};
	}else if ($scope.activeUsersOfSiteObject == true) {
		$scope.getAllusersPage = function() {
			$scope.adminUserName = $rootScope.loggedUsername;
			userManagementSerrvice.getAllActiveUsersPageBySiteAdmin(
					$scope.adminUserName, true, $scope.page,
					$scope.size).then(function(response) {
				$scope.userLists = response.data.content;
				$scope.totalElements = response.data.totalElements;
				$scope.totalPages = response.data.totalPages - 1;
				$scope.noOfPgaes(response.data.totalPages);
			}, function(error) {
			});
		};
	}else if ($scope.inActiveUsersOfSiteObject == true) {
		$scope.getAllusersPage = function() {
			$scope.adminUserName = $rootScope.loggedUsername;
			userManagementSerrvice.getAllActiveUsersPageBySiteAdmin(
					$scope.adminUserName, false, $scope.page,
					$scope.size).then(function(response) {
				$scope.userLists = response.data.content;
				$scope.totalElements = response.data.totalElements;
				$scope.totalPages = response.data.totalPages - 1;
				$scope.noOfPgaes(response.data.totalPages);
			}, function(error) {
			});
		};
	}
	else {
		$scope.getAllusersPage = function() {
			$scope.adminUserName = $rootScope.loggedUsername;
			userManagementSerrvice.getAllusersPageByLoginUser($scope.adminUserName,
					$scope.page, $scope.size).then(function(response) {
				$scope.userLists = response.data.content;
				$scope.totalElements = response.data.totalElements;
				$scope.totalPages = response.data.totalPages - 1;
				$scope.noOfPgaes(response.data.totalPages);
			}, function(error) {
			});
		};
	}

	$scope.getAllusersPageSearch = function(search) {
		if (search == " " || search == undefined || search == "") {
			search = null;
		}
		$scope.adminUserName = $rootScope.loggedUsername;
		userManagementSerrvice.getAllusersPageSearch($scope.adminUserName,
				search, $scope.page, $scope.size).then(function(response) {
			$scope.userLists = response.data.content;
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages - 1;
			$scope.noOfPgaes(response.data.totalPages);
		}, function(error) {
		});
	};

	// ////////////

	$scope.getAllRegistrations = function() {
		$scope.adminUserName = $rootScope.loggedUsername;
		userManagementSerrvice.getAllRegistrations($scope.adminUserName).then(
				function(response) {
					$scope.userList = response.data;

				}, function(error) {
				});
	};
	$scope.getTherapistByUsername = function() {
		therapistService.getTherapistByUsername($scope.user.email).then(
				function(response) {
					$scope.user = response.data;
					$scope.getAllStatesByCountryId($scope.user.countryName.id);
					$scope.getAllCitiesByStateId($scope.user.stateName.id);
				}, function(error) {

				})
	};
	$scope.getAllSitesByCompanyUserName = function() {
		// $scope.companyAdminUsername= $rootScope.loggedUsername;
		siteService.getAllSitesByCompanyUserName($rootScope.loggedUsername)
				.then(function(response) {
					$scope.siteList = response.data;
					// $scope.getAllStatesByCountryId($scope.site.address.country.id);
					// $scope.getAllCitiesByStateId($scope.site.address.state.id);
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.getAllSitesByCompanyUserNameAndStatus = function() {
		siteService.getAllSitesByCompanyUserNameAndStatus(
				$rootScope.loggedUsername).then(function(response) {
			$scope.siteList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getAllRoles = function() {
		roleService.getAllRoles().then(function(response) {
			$scope.allrolesList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getAllRolesBasedOnStatus = function() {
		roleService.getAllRolesBasedOnStatus().then(function(response) {
			$scope.rolesList = response.data;
			angular.forEach($scope.rolesList, function(role) {
				if (role.roleName == $rootScope.loggedUserRole) {
					var index = $scope.rolesList.indexOf(role);
					$scope.rolesList.splice(index, 1);
				}
			})

		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.removeIt = function(i) {
		$scope.rolesList.splice(1, 1)
	};
	$scope.updateUser = function(user) {
		$scope.user = user;
		if ($scope.addUser != undefined && $scope.addUser.role != undefined) {
			if ($scope.addUser.role.roleName == 'Therapist') {
				var address1 = document.getElementById('street_number').value;
				var address2 = document.getElementById('route').value;
				var city = document.getElementById('locality').value;
				var state = document
						.getElementById('administrative_area_level_1').value;
				var country = document.getElementById('country').value;
				var zipcode = document.getElementById('postal_code').value;

				$scope.address1.address1 = address1;
				$scope.address1.address2 = address2;
				$scope.address1.city = city;
				$scope.address1.state = state;
				$scope.address1.country = country;
				$scope.address1.zipcode = zipcode;
				$scope.user.address1 = $scope.address1;
			}
		}
		// userManagementSerrvice.updateUser(user).then(function(response) {
		userManagementSerrvice
				.updateUser($rootScope.loggedUsername, $scope.user)
				.then(
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 3000
							};
							toastr
									.success(
											'',
											successMessageHandler.USERMANAGEMENT_UPDATE_USER_SUCCESS);
							$state.go('main.usermanagement_list');
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
	$scope.deleteUser = function(id) {

		// userManagementSerrvice.deleteUser(id).then(function(response) {
		userManagementSerrvice
				.deleteUser($rootScope.loggedUsername, id)
				.then(
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 3000
							};
							toastr
									.success(
											'',
											successMessageHandler.USERMANAGEMENT_DELETE_USER_SUCCESS);
							$state.go('main.usermanagement_list');
							$scope.getAllusersPage();
						},
						function(error) {
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
						});
	};
	$scope.getAllCountries = function() {
		countryService.getAllCountries().then(function(response) {
			$scope.countriesList = response.data;
			$scope.getAllStatesByCountryId($scope.user.address.country.id);
			$scope.getAllCitiesByStateId($scope.user.address.state.id);
		}, function(error) {

		})
	};
	$scope.getOwnerObject = function(user) {
		$scope.ownerObject = user;
		return $scope.ownerObject;
	};

	$scope.getCountrysBasedOnIsdCode = function() {
		countryService.getCountrysBasedOnIsdCode().then(function(response) {
			$scope.countriesList = response.data;
			/*
			 * $scope.getAllStatesByCountryId($scope.user.address.country.id);
			 * $scope.getAllCitiesByStateId($scope.user.address.state.id);
			 */
		}, function(error) {

		})
	};
	$scope.getCountrysByIsdCode = function() {
		countryService.getCountrysBasedOnIsdCode().then(function(response) {
			$scope.countriesList = response.data;
		}, function(error) {

		})
	};
	$scope.getAllStatesByCountryId = function(id) {
		countryService.getAllStatesByCountryId(id).then(function(response) {
			$scope.statesList = response.data;
		}, function(error) {

		})
	};

	var arr = new Array();
	$scope.getAllCitiesByStateId = function(id) {
		countryService.getAllCitiesByStateId(id).then(function(response) {
			$scope.citiesList = response.data;
			angular.forEach($scope.citiesList, function(city) {
				arr.push({
					name : city.name
				});
			});
			$scope.cities = arr;
			return $scope.cities;
		}, function(error) {

		})
	};
	$scope.getAllDepartments = function() {
		departmentService.getAlldepartments().then(function(response) {
			$scope.departmentsList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllDepartmentsByCmpany = function() {
		departmentService
				.getAllDepartmentsByCmpany($rootScope.loggedUsername)
				.then(
						function(response) {
							$scope.departmentsListByCmpany = response.data;
							if ($scope.departmentsListByCmpany.length < 1) {
								toastr.options = {
									closeButton : true,
									progressBar : true,
									showMethod : 'slideDown',
									positionClass : "toast-top-full-width",
									timeOut : 2000
								};
								$scope.message = successMessageHandler.DEPARTMENT_DEPT_NOT_CONFIGURED;
								toastr.error($scope.message, '');
							}
						},
						function(error) {
							$scope.message = JSON.stringify(error.data.message
									.trim());
						});
	};
	$scope.getAllDepartmentsList = function() {
		departmentService.getAlldepartmentsList($rootScope.loggedUsername,$scope.page, $scope.size).then(
				function(response) {
					$scope.departmentsLists = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages - 1;
					$scope.noOfPgaes(response.data.totalPages);
				}, function(error) {
					$scope.message1 = JSON.stringify(error.data.message.trim());
					$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
				});
	};

	/*
	 * $scope.deptMsg= function() {
	 * departmentService.getAllDepartmentsByCmpany($rootScope.loggedUsername).then(function(response) {
	 * $scope.departmentsListByCmpany = response.data;
	 * if($scope.departmentsListByCmpany.length<1){ toastr.options = {
	 * closeButton: true, progressBar: true, showMethod: 'slideDown',
	 * positionClass : "toast-top-full-width", timeOut: 2000 }; $scope.message =
	 * "Departments are not configured! Please configure the Departments
	 * First!!" toastr.error($scope.message, ''); }else{ } }); };
	 */
	// //////
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

		if ($rootScope.compFlag == false) {
			// alert("compFlag Falllllssseeeee")
			$scope.registeredEnterpriseCompaniesForAdmin($rootScope.user);
		}
		if ($rootScope.compFlag == true) {
			// alert("compFlag trueeeeeee")
			$scope.getRegisteredCompaniesFilter($rootScope.personsCompany);
		}
		$scope.getAllusersPage();
	});

	// //////
	$scope.gotoList = function() {

		$state.go('main.usermanagement_list');
	};
	$scope.gotoAdd = function() {
		$state.go('main.add_usermanagement');
	};
	$scope.gotoback = function() {
		$state.go('main.usermanagement_list');
	};
	$scope.gotoupdate = function(user) {
		$state.go('main.updateUserMangement', {
			userObj : user
		});
	};

	/*
	 * //get all registered companies $scope.getAllRegisteredCompanies =
	 * function() { userManagementSerrvice.getAllRegisteredCompanies().then(
	 * function(response) { $scope.registeredCompanies = response.data; },
	 * function(error) { }) };
	 */
	// get all registered companies
	$scope.getAllRegisteredCompanies = function(registrationType) {
		if (registrationType == undefined) {
			registrationType = 'All';
		}
		userManagementSerrvice.getAllRegisteredCompanies(registrationType)
				.then(function(response) {
					$scope.registeredCompanies = response.data;
					$rootScope.user = registrationType;
					// alert("All::"+$scope.registeredCompanies.length)
				}, function(error) {

				})
	};
	/*
	 * if($scope.registeredIndividualCompanies==true){
	 * registrationType='Individual'; alert("Not Call")
	 * userManagementSerrvice.getAllRegisteredCompanies(registrationType).then(
	 * function(response) { $scope.registeredCompanies = response.data;
	 * $rootScope.user=registrationType;
	 * alert("IndI::"+$scope.registeredCompanies.length) }, function(error) { }) };
	 */
	// if($scope.registeredEnterpriseCompanies==true){
	$scope.registeredEnterpriseCompaniesForAdmin = function(registrationType) {
		// registrationType='Super Admin';
		$rootScope.compFlag = false;
		$rootScope.user = registrationType;
		// alert("Call")
		userManagementSerrvice.getAllRegisteredCompaniesPage(registrationType,
				$scope.page, $scope.size).then(function(response) {
			$scope.registeredCompanies = response.data.content;
			/*
			 * if($scope.registeredCompanies.length<1){ alert("true its Zero")
			 * $scope.flash=true; }
			 */
			// alert(JSON.stringify($scope.registeredCompanies ));
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages - 1;
			$scope.noOfPgaes(response.data.totalPages);

			// alert("Super::"+$scope.registeredCompanies.length)
		}, function(error) {

		})
		// }
	};
	$scope.user = $stateParams.userObj;

	$scope.type = $stateParams.type;
	$scope.gotoAppointmentViewPage = function(user) {
		$rootScope.userId = user.id;
		$state.go('main.therapistAppointmentsList', {
			userObj : user
		});
	}
	$scope.findAllTherapistAppointments = function() {
		userManagementSerrvice.findAllTherapistAppointments($rootScope.userId,
				$scope.page, $scope.size).then(function(response) {
			$scope.therapistAppointments = response.data.content;
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages - 1;
			$scope.noOfPgaes(response.data.totalPages);
		}, function(error) {

		})
	};
	$scope.searchSchedules = function(appointment) {
		$rootScope.appointment = appointment;
		/*
		 * alert("appointmentStartedDate::"+appointmentStartedDate);
		 * alert("appointmentEndedDate::"+appointmentEndedDate);
		 */
		// var appointmentStartedDate=null;
		angular
				.forEach(
						appointment.subAppointments,
						function(subAppointment) {
							if (subAppointment.status == 'AWAITING') {
								// alert("flag2:"+$scope.flag)
								if ($scope.flag == false) {
									// alert("Enter")
									$scope.appointmentStartingDate = subAppointment.appointmentStartedDate
											.slice(6, 10)
											+ "-"
											+ subAppointment.appointmentStartedDate
													.slice(3, 5)
											+ "-"
											+ subAppointment.appointmentStartedDate
													.slice(0, 2)
									// alert("inside for
									// Loop:"+$scope.appointmentStartingDate)
									$scope.flag = true;
								}
							}
						})
		// alert($scope.appointmentStartedDate);
		$scope.searchScheduleDtoObj = {
			// $scope.stime=
			"localDate" : $scope.appointmentStartingDate,
			"enddate" : $scope.appointmentStartingDate,
			/*
			 * "fromTime" : appointment.appointmentStartTime, "toTime" :
			 * appointment.appointmentEndTime,
			 */
			"fromTime" : "00:00",
			"toTime" : "23:30",
			"username" : $rootScope.loggedUsername,
			// "department":appointment.department
			"department" : appointment.doctor.department,
			"accurateFromTime" : appointment.appointmentStartTime,
			"accurateToTime" : appointment.appointmentEndTime,
			"accurateEndDate" : appointment.appointmentEndedDate
		}
		// alert($scope.searchScheduleDtoObj.localDate);
		scheduleService.searchScheduleDto1($scope.searchScheduleDtoObj).then(
				function(response) {
					$scope.schedulesDtos = response.data;
					$rootScope.flaf = true;
					$scope.flag = false;
					/*
					 * $state.go('main.searchSchedule', { scheduleListByDate :
					 * $scope.schedulesDtos, stDate : appointmentStartedDate,
					 * enddate : appointment.appointmentEndedDate });
					 */
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
					/*
					 * $scope.appointment.appointmentStartedDate=undefined;
					 * $scope.appointment.appointmentEnddate=undefined;
					 */
					// alert("error occured")
					$scope.flag = false;
					$scope.flag1 = true;
				})
	};
	if ($stateParams.scheduleListByDate != undefined) {
		$scope.scheduleListByDate = $stateParams.scheduleListByDate;
	}
	;

	$scope.gotoAddTherapist = function(id) {
		$rootScope.existId = id;
		$state.go('main.add_userTherapist');
	}
	$scope.getUsersBasedOnCompany = function(userName, registeredCompanies) {
		$state.go('main.internalDashboard', {
			userName : userName,
			registeredCompanies : registeredCompanies
		});

	};
	$scope.getUsersBasedOnCompany1 = function(userName, registeredCompanies) {
		$state.go('main.registeredCompaniesOverView', {
			userName : userName,
			registeredCompanies : registeredCompanies
		});

	};
	// scheduleDtoForCalendar
	$scope.updateAppointmentWithAssignedTherapist = function() {
		scheduleDtoForCalendar = $rootScope.scheduleDtoForCalendar;
		// alert("a:"+JSON.stringify(scheduleDtoForCalendar))
		// alert("b:"+JSON.stringify($rootScope.appointment))
		// scheduleDtoForCalendar.appointment=$rootScope.appointment;
		scheduleDtoForCalendar.appointmentId = $rootScope.appointment.id;
		// alert(JSON.stringify(scheduleDtoForCalendar))
		appointmentService
				.updateAppointmentWithAssignedTherapist(scheduleDtoForCalendar)
				.then(
						function(response) {
							// $scope.schedulesDtos = response.data;
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
											successMessageHandler.USERMANAGEMENT_ASSIGN_PATIENT_SUCCESS);
							$scope.findAllTherapistAppointments();
						},
						function(error) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 5000
							};
							$scope.message = JSON.stringify(error.data.message
									.trim());
							toastr.error($scope.message, 'Error');
							// $scope.flag=false;
						})
	};

	$scope.radioClick = function(schedule) {
		// alert(JSON.stringify(schedule))
		$rootScope.scheduleDtoForCalendar = schedule;
	}

	$scope.gotoOverView = function() {
		$state.go('main.registeredCompaniesOverView')
	}
	$scope.getRegisteredCompaniesForStripeInvoice = function() {
		$scope.word = "All";
		userManagementSerrvice.getRegisteredCompaniesForStripeInvoice(
				$scope.word).then(function(response) {
			$scope.companiesList = response.data;

		}, function(error) {
		});
	}
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
					/*
					 * if($scope.registeredCompanies.length<1){ alert("true its
					 * Zero in getRegisteredCompaniesFilter") $scope.flash=true;
					 * alert($scope.flash) }
					 */
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

	$scope.reset = function(person) {
		person.startDate = undefined;
		person.endDate = undefined;
		person.status = undefined;
	}

	$scope.getStripePayments = function() {

		// patientService.getStripePayments($rootScope.username).then(
		patientService.getStripePayments($rootScope.username).then(
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
					$state.go('main.listofpayments')
				});
	}

	$scope.gotoInvoicePage = function(username) {
		/*
		 * if(pa.invoice!=null){ $rootScope.invoiceId=pa.invoice; }
		 */
		$rootScope.username = username;
		$state.go('main.stripeInvoice', {
		// userObject : pa,
		});
	};
	$scope.invoiceStripe = $stateParams.invoiceObject;
	$scope.gotoInvoice = function(invoice) {
		/*
		 * if(pa.invoice!=null){ $rootScope.invoiceId=pa.invoice; }
		 */fdepartmentsListByCmpany

		$state.go('main.stripeSingleInvoice', {
			invoiceObject : invoice,
		});
	};

	$scope.gotoAdminsList = function() {
		$state.go('main.listofpayments')
	}

	$scope.gotoInvoiceList = function() {
		$state.go('main.stripeInvoice')
	};

	$scope.role={};
	$scope.getDepartmentsByRole = function(role) {
		$scope.role=role
		if ($scope.role != undefined && $scope.role.departments != undefined) {
			if ($scope.role.departments.length > 0) {
				$scope.getAllDepartmentsByCmpanyAndRole($scope.role.id);
			}
		}

	};
	/*$scope.getAllDepartmentsByCmpanyAndRole = function(roleId) {
		departmentService.getAllDepartmentsByCmpanyAndRole(
				$rootScope.loggedUsername, roleId).then(function(response) {
			$scope.departmentsListByCmpanyAndRole = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};*/

}
angular.module("HealthApplication").controller("userManagementCtrl",
		userManagementCtrl);