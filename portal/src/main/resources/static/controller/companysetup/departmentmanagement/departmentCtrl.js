//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function departmentCtrl($scope, $state, departmentService, $rootScope,successMessageHandler,roleService,
		$stateParams) {

	$scope.page = 0;
	$scope.size = 5;

	$scope.department = $stateParams.departmentObj;
	$scope.patientRegistrationTypes=[];

	$scope.sort = function(keyname) {
		$scope.sortKey = keyname; // set the sortKey to the param passed
		$scope.reverse = !$scope.reverse; // if true make it false and vice
											// versa
	}
	
	$scope.getAllRolesBasedOnStatus = function() {
		roleService.getAllRolesBasedOnStatus().then(function(response) {
			
			/*To remove specific elements
			 * var objOpts = response.data;
			
			var removeObjectProperties = function(obj, props) {

			    for(var i = 0; i < props.length; i++) {
			        if(obj.hasOwnProperty(props[i])) {
			            delete obj[props[i]];
			        }
			    }
			   

			};
			removeObjectProperties(objOpts, ["0", "1", "6"]);*/
	
				 
			$scope.rolesList = response.data;

		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.addDepartment = function(department) {
		department.adminUserName=$rootScope.loggedUsername;
		departmentService.addDepartment(department).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.DEPARTMENT_ADD_SUCCESS);
			$state.go('main.departmentlist');
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
			toastr.error($scope.message, 'Error');
			
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

	$scope.getAllDepartments = function() {
		departmentService.getAlldepartments().then(function(response) {
			$scope.departmentsList = response.data;
		}, function(error) {
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
		});
	};
	$scope.getAllDepartmentsByCmpany = function() {
		departmentService.getAllDepartmentsByCmpany($rootScope.loggedUsername).then(function(response) {
			$scope.departmentsListByCmpany = response.data;
		}, function(error) {
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
		});
	};
	
	$scope.getAllDepartmentsListSearch = function(search) {
		departmentService.getAlldepartmentsListSearch($rootScope.loggedUsername,search,$scope.page, $scope.size).then(
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

	$scope.updateDepartment = function(department) {
		departmentService.updateDepartment(department).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.DEPARTMENT_UPDATE_SUCCESS);
			$state.go('main.departmentlist');
		}, function(error) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
			toastr.error($scope.message, 'Error');
			
		});
	};
	/*$scope.deleteDepartment = function(id) {
		departmentService.deleteDepartment(id).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.DEPARTMENT_DELETE_SUCCESS);
			$scope.getAllDepartmentsList();
		}, function(error) {
			
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-right",
                    timeOut: 2000
                };
			$scope.message1 = JSON.stringify(error.data.message.trim());
			$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
			toastr.error($scope.message, 'Error');
		});
	};*/
	
	$scope.getAllPatientRegistrationTypesList = function() {
		departmentService.getAllPatientRegistrationTypesList().then(
				function(response) {
					$scope.patientRegistrationTypes = response.data;
					
				}, function(error) {
					$scope.message1 = JSON.stringify(error.data.message.trim());
					$scope.message=$scope.message1.substring(1,$scope.message1.length-1);
				});
	};
	
	$scope.deleteDepartment=function(id){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  departmentService.deleteDepartment(id).then(function(response) {
						 swal("Ouch! It has been deleted!", {
						      icon: "success",
						    });
						 $scope.getAllDepartmentsList();
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
						 swal($scope.message , {
						      icon: "info",
						    });
					});
			    swal("Ouch! It has been deleted!", {
			      icon: "success",
			    });
			   
			  } else {
			    swal("It is safe!");
			  }
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
		$scope.getAllDepartmentsList();
	});

	$scope.gotoList = function() {
		$state.go('main.departmentlist');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addDepartment');
	};
	$scope.gotoback = function() {
		$state.go('main.departmentlist');
	};
	$scope.gotoupdate = function(client) {
		$state.go('main.updateDepartment', {
			departmentObj : client
		});
	};
}
angular.module("HealthApplication")
		.controller("departmentCtrl", departmentCtrl);
