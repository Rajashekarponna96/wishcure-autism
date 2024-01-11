//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function siteCtrl($scope, $state, siteService, $rootScope, $stateParams,
		countryService, successMessageHandler) {

	$scope.page = 0;
	$scope.size = 5;
	$scope.site = {}

	$scope.updateSiteObj = $stateParams.siteObj;
	$scope.addSite = function(site) {
		var address1 = document.getElementById('street_number').value;
		var address2 = document.getElementById('route').value;
		var city = document.getElementById('locality').value;
		var state = document.getElementById('administrative_area_level_1').value;
		var country = document.getElementById('country').value;
		var zipcode = document.getElementById('postal_code').value;
		$scope.site.address1=address1;
		$scope.site.address2=address2;
		$scope.site.city=city;
		$scope.site.state=state;
		$scope.site.country=country;
		$scope.site.zipcode=zipcode;
		$scope.site.companyAdminUsername = $rootScope.loggedUsername;
		siteService.addSite(site).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.SITEMANAGEMENT_ADD_SUCCESS);
			$state.go('main.siteList');
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

	$scope.getAllSitesByCompanyUserName = function() {
		siteService.getAllSitesByCompanyUserName($rootScope.loggedUsername)
				.then(function(response) {
					$scope.siteList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	
	
	$scope.getAllSitesByCompanyUserNamePage = function() {
		siteService.getAllSitesByCompanyUserNamePage($rootScope.loggedUsername,$scope.page, $scope.size)
				.then(function(response) {
					$scope.siteLists = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages-1;
					$scope.noOfPgaes(response.data.totalPages);
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	
	$scope.getAllSitesByCompanyUserNameAndStatus = function() {
		siteService.getAllSitesByCompanyUserNameAndStatus($rootScope.loggedUsername)
				.then(function(response) {
					$scope.siteList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	//718
	if ($stateParams.activeSitesObject != undefined) {
		$scope.activeSitesObject = $stateParams.activeSitesObject;
	}
	if ($stateParams.inActiveSitesObject != undefined) {
		$scope.inActiveSitesObject = $stateParams.inActiveSitesObject;
	}
	if ($scope.activeSitesObject == true) {
		$scope.getAllSitesPageBySuperAdminAndCompany = function() {
			
			$scope.adminUserName = $rootScope.loggedUsername;
			siteService.getAllsitesPageBySuperAdminAndCompany(
					$scope.adminUserName, "Super Admin", true, $scope.page,
					$scope.size).then(function(response) {
				$scope.siteLists = response.data;
				$scope.totalElements = response.data.totalElements;
				$scope.totalPages = response.data.totalPages - 1;
				$scope.noOfPgaes(response.data.totalPages);
			}, function(error) {
			
			});
			
			
			
		};
	} else if ($scope.inActiveSitesObject == true) {
		$scope.getAllSitesPageBySuperAdminAndCompany = function() {
			$scope.adminUserName = $rootScope.loggedUsername;
			siteService.getAllsitesPageBySuperAdminAndCompany(
					$scope.adminUserName, "Super Admin", false, $scope.page,
					$scope.size).then(function(response) {
				$scope.siteLists = response.data;
				$scope.totalElements = response.data.totalElements;
				$scope.totalPages = response.data.totalPages - 1;
				$scope.noOfPgaes(response.data.totalPages);
			}, function(error) {
			});
		};
	} 
	//718 ending

	$scope.getCountrysBasedOnIsdCode = function() {
		countryService.getCountrysBasedOnIsdCode().then(function(response) {
			$scope.countriesList = response.data;
		}, function(error) {

		})
	};

	$scope.getCountrysBasedOnIsdCodeInUpdate = function() {
		countryService.getCountrysBasedOnIsdCode().then(function(response) {
			$scope.countriesList = response.data;
			$scope.getAllStatesByCountryId($scope.updateSiteObj.address.country.id);
			$scope.getAllCitiesByStateId($scope.updateSiteObj.address.state.id);
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

	$scope.getAllSites = function() {
		siteService.getAllSites().then(function(response) {
			$scope.siteList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.updateSite = function(site) {
		siteService.updateSite(site).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.SITEMANAGEMENT_UPDATE_SUCCESS);
			$state.go('main.siteList');
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

	$scope.deleteSite = function(id) {
		siteService.deleteSite(id).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.SITEMANAGEMENT_DELETE_SUCCESS);
			$scope.getAllSitesPageBySuperAdminAndCompany();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
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
					//$scope.getAllSitesPageBySuperAdminAndCompany();
				});
		
		
	$scope.gotoList = function() {
		$state.go('main.siteList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addSite');
	};
	$scope.gotoback = function() {
		$state.go('main.siteList');
	};
	$scope.gotoupdate = function(site) {
		$state.go('main.updateSite', {
			siteObj : site
		});
	};
	
	/*//718
	$scope.getSitesBasedOnCompany1 = function(userName, sites) {
		//	alert(userName.email);
			$rootScope.userEmail=userName.email;
			$state.go('main.siteCompaniesOverView', {
				userName : userName,
				site : site
			});
		};*/
	
	$scope.getSitesBasedOnCompany1 = function(site) {
		$state.go('main.siteCompaniesOverView', {
			siteObj : site
		});
	};
	
	//Related to SiteCompaniesOverview DashBoard
	
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
	$scope.gotousers = function(){
		$state.go('main.usermanagement_list')
	};
	$scope.gotopatientListactive = function(){
		$scope.activePatients=true;
		$state.go('main.patient_list',{
			activePatientsObject : $scope.activePatients
			});
	};
	$scope.gotopatientListInactive = function(){
		$scope.inActivePatients=true;
		$state.go('main.patient_list',{
			inActivePatientsObject : $scope.inActivePatients
			});
	};
	$scope.gotopatientList = function(){
		$state.go('main.patient_list')
	};
	$scope.gototTodaypatientList = function(){
		$scope.todayPatients=true;
		$state.go('main.patient_list',{
			todayPatientsObject : $scope.todayPatients
			});
	};
	$scope.appointmentslists = function() {
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
	
}

angular.module("HealthApplication").controller("siteCtrl", siteCtrl);
