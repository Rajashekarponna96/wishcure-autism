//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function schoolCtrl($scope, $state, schoolService, $rootScope, $stateParams,
		countryService , successMessageHandler) {

	$scope.page = 0;
	$scope.size = 5;
	$scope.school = {}
	$scope.address = {}

	$scope.updateSchoolObj = $stateParams.schoolObj;
	$scope.addSchool = function(school,address) {
		var address1 = document.getElementById('street_number').value;
		var address2 = document.getElementById('route').value;
		var city = document.getElementById('locality').value;
		var state = document.getElementById('administrative_area_level_1').value;
		var country = document.getElementById('country').value;
		var zipcode = document.getElementById('postal_code').value;
		school.adminUserName = $rootScope.loggedUsername;
		$scope.address.address1=address1;
		$scope.address.address2=address2;
		$scope.address.city=city;
		$scope.address.state=state;
		$scope.address.country=country;
		$scope.address.zipcode=zipcode;
		school.address =$scope.address;
		if(school.status==undefined){
			school.status=true;
		}
	//alert("school::"+JSON.stringify(school.status));
		//alert("$scope.school:"+JSON.stringify($scope.school));
		schoolService.addSchool(school).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.SCHOOLS_ADD_SUCCESS);
			$state.go('main.schoolList');
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

	$scope.getAllSchoolsByCompanyUserNamePage = function() {
		schoolService.getAllSchoolsByCompanyUserNamePage($rootScope.loggedUsername,$scope.page, $scope.size)
				.then(function(response) {
					$scope.schoolLists = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages-1;
					$scope.noOfPgaes(response.data.totalPages);
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	
	$scope.getAllSchoolsByCompany = function() {
		schoolService.getAllSchoolsByCompany($rootScope.loggedUsername)
				.then(function(response) {
					$scope.schoolsLists = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	
	
	
	
	/*$scope.deleteSchool = function(id) {
		schoolService.deleteSchool(id).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', ' Data Deleted Successfully');
			$scope.getAllSchoolsByCompanyUserNamePage();
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
	};*/
	
	$scope.deleteSchool=function(id){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  schoolService.deleteSchool(id).then(function(response) {
						 swal("Poof! It has been deleted!", {
						      icon: "success",
						    });
						 $scope.getAllSchoolsByCompanyUserNamePage();
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
						 swal($scope.message , {
						      icon: "info",
						    });
					});
			    swal("Proof!It has been deleted!", {
			      icon: "success",
			    });
			   
			  } else {
			    swal("It is safe!");
			  }
			});
	};
	
	$scope.updateSchool = function(school) {
		/*alert("mobileNumber:"+school.mobileNumber);
		alert(school.email);
		alert(mob);*/
		school.adminUserName = $rootScope.loggedUsername;
		schoolService.updateSchool(school).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.SCHOOLS_UPDATE_SUCCESS);
			$state.go('main.schoolList');
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
	
	/*$scope.getAllschoolsByCompanyUserName = function() {
		schoolService.getAllschoolsByCompanyUserName($rootScope.loggedUsername)
				.then(function(response) {
					$scope.schoolList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	
	
	
	$scope.getAllschoolsByCompanyUserNameAndStatus = function() {
		schoolService.getAllschoolsByCompanyUserNameAndStatus($rootScope.loggedUsername)
				.then(function(response) {
					$scope.schoolList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.getCountrysBasedOnIsdCode = function() {
		countryService.getCountrysBasedOnIsdCode().then(function(response) {
			$scope.countriesList = response.data;
		}, function(error) {

		})
	};

	$scope.getCountrysBasedOnIsdCodeInUpdate = function() {
		countryService.getCountrysBasedOnIsdCode().then(function(response) {
			$scope.countriesList = response.data;
			$scope.getAllStatesByCountryId($scope.updateschoolObj.address.country.id);
			$scope.getAllCitiesByStateId($scope.updateschoolObj.address.state.id);
		}, function(error) {

		})
	};

	$scope.getAllCountries = function() {
		countryService.getAllCountries().then(function(response) {
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

	$scope.getAllCitiesByStateId = function(id) {
		countryService.getAllCitiesByStateId(id).then(function(response) {
			$scope.citiesList = response.data;
		}, function(error) {

		})
	};

	$scope.getAllschools = function() {
		schoolService.getAllschools().then(function(response) {
			$scope.schoolList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	

	*/
	
	
	// Pagination logic

	$scope.noOfPgaes=function(totalpages){
		
		$scope.pageList=[];
		for(i=0;i<totalpages;i++){
			$scope.pageList.push(i);
		}
		
	   };
	
	   $scope.pageChanged=function(page){
		$scope.page=page;
	   };
	   
	   $scope.sizeChanged=function(size){
			$scope.size=size;
		};
		$scope.firstPage = function() {
			$scope.page = 0;
		};
		
		$scope.lastPage = function() {
			$scope.page = $scope.totalPages;
		};

		$scope.previousPage = function() {
			if ($scope.page >0) {
				$scope.page = $scope.page - 1;
			}
		};
		$scope.nextPage = function() {
			if ($scope.page < $scope.totalPages) {
				$scope.page = $scope.page + 1;
			}
		};
		$scope.$watchGroup(['size','page'],
				function(oldSize, oldPage) {
					$scope.getAllSchoolsByCompanyUserNamePage();
				});
		
		
	$scope.gotoList = function() {
		$state.go('main.schoolList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addSchool');
	};
	$scope.gotoback = function() {
		$state.go('main.schoolList');
	};
	$scope.gotoupdate = function(school) {
		$state.go('main.updateSchool', {
			schoolObj : school
		});
	};
}
angular.module("HealthApplication").controller("schoolCtrl", schoolCtrl);
