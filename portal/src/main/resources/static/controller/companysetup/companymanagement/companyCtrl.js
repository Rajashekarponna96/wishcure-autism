//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function companyCtrl($scope, $state, companyService, countryService,loginService, successMessageHandler,
		$rootScope, PATIENT_MODULE_CONFIG) {
	
	$scope.page = 0;
	$scope.size = 5;
	//$scope.flagValue="1";
	
	$scope.addCompany = function(user) {
		companyService.addCompany(user).then(function(response) {
			$state.go('');
		}, function(error) {
		});
	};

	$scope.getCompanyUser = function() {
		companyService.getCompanyUser($rootScope.loggedUsername).then(
				function(response) {
					$scope.user = response.data;
				}, function(error) {

				})
	};
	
	$scope.getImagePathData = function() {
		return PATIENT_MODULE_CONFIG.GET_USER_IMAGEPATHDATA($rootScope.loggedUsername);
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
	
	$scope.loadUserImage=function(){
		if($rootScope.imageUrlForCompany != undefined){
			$scope.viewImagePathData($rootScope.imageUrlForCompany);
		}else{
			$scope.getImageS3();
		}
	}

	$scope.viewImagePathData = function(imageUrlForCompany) {
		$scope.flagValue="3";
		return PATIENT_MODULE_CONFIG.VIEW_USER_IMAGEPATHDATA(imageUrlForCompany);
	};
	/*$scope.getImagePathData = function(profilePicPath) {
		companyService.getImagePath(profilePicPath).then(function(response) {
			$scope.companyImagePath = response.data;
		})
	};*/
	$scope.fileChanged = function(files) {
		$rootScope.imageUrlForCompany=undefined;
		if (files != null) {
			
			var imagefile = document.querySelector('#file');
            if (imagefile.files && imagefile.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#temp_image')
                        .attr('src', e.target.result);
                };
                reader.readAsDataURL(imagefile.files[0]);
                this.imagefile = imagefile.files[0];
                $scope.flagValue="2";
            }else{
                console.log("Image not selected");
            }
			
			
			
			var file = files[0];
			$scope.filepath = files[0];
			companyService.uploadImage($scope.filepath).then(
					function(response) {
						$scope.user.profilePic = response.data.imagePath;
						$rootScope.imageUrlForCompany = $scope.user.profilePic;
						$scope.getImageS3();
						$scope.viewImagePathData($rootScope.imageUrlForCompany);
						toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-right",
							timeOut : 2000
						};
						toastr.success(' ', successMessageHandler.COMPANYMANAGEMENT_IMAGE_UPLOAD_SUCCESS);

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
						$scope.message = $scope.message1.substring(0,
								$scope.message1.lenth);
						toastr.error($scope.message, 'Error');
					});
		}
	};

	$scope.getAllCountries = function() {
		countryService.getAllCountries().then(function(response) {
			$scope.countriesList = response.data;
		}, function(error) {

		})
	};

	$scope.getCountrysBasedOnIsdCode = function() {
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

	$scope.getAllCitiesByStateId = function(id) {
		countryService.getAllCitiesByStateId(id).then(function(response) {
			$scope.citiesList = response.data;
		}, function(error) {

		})
	};

	/*$scope.getImagePathData1 = function() {
		return PATIENT_MODULE_CONFIG
				.GET_USER_IMAGEPATHDATA($rootScope.loggedUsername);
	};*/
	$scope.updateCompanyUser = function(user) {
		var address1 = document.getElementById('street_number').value;
		var address2 = document.getElementById('route').value;
		var city = document.getElementById('locality').value;
		var state = document.getElementById('administrative_area_level_1').value;
		var country = document.getElementById('country').value;
		var zipcode = document.getElementById('postal_code').value;
		$scope.user.address1=address1;
		$scope.user.address2=address2;
		$scope.user.city=city;
		$scope.user.state=state;
		$scope.user.country=country;
		$scope.user.zipcode=zipcode;
		companyService.updateCompanyUser(user).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.COMPANYMANAGEMENT_USER_PROFILE_UPLOAD_SUCCESS);
			$scope.message = undefined;
			$scope.getImagePathData();
		}, function(error) {
			
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-right",
					timeOut : 2000
				};
				$scope.message1 = JSON.stringify(error.data.message
						.trim());
				$scope.message = $scope.message1.substring(0,
						$scope.message1.lenth);
				toastr.error($scope.message, 'Error');
		})
	};

	$scope.gotoChangepassword = function() {
		$state.go('main.changepassword');
	};
	
	//
	$scope.getAllComaniesPage= function() {
		companyService.getAllComaniesPage($scope.page, $scope.size).then(function(response) {
			$scope.companiesLists = response.data.content;
			$scope.totalElements = response.data.totalElements;
			$scope.totalPages = response.data.totalPages-1;
			$scope.noOfPgaes(response.data.totalPages);
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
					$scope.getAllComaniesPage();
		});
		//
		 $scope.gotoInvoicePage = function(pa) {
			   $rootScope.invoiceId=pa.invoice;
				$state.go('main.invoiceViewForCompany')
				/*, {
					patientObject : pa,
				});*/
			};
			
			$scope.getAllCountries = function() {
				countryService.getAllCountries().then(function(response) {
					$scope.countriesList = response.data;
				}, function(error) {

				})
			};
			
			$scope.getStripeInvoice = function() {
				companyService.getStripeInvoice($rootScope.loggedUsername).then(
						function(response) {
							$scope.stripeInvoice = response.data;
						}, function(error) {

						})
			};
			$scope.invoiceList=[{itemName:"Service for the month of jan for the Basic Subscription",quantity:"10",unitCost:'$29.99',gaurdian:'$71.00',price:"$299.9"}/*,
				                  {itemName:"item2",quantity:"2",unitCost:'$57.00',gaurdian:'$56.80',price:"$112.80"},
				                  {itemName:"item3",quantity:"3",unitCost:'$645.00',gaurdian:'$321.20',price:"$1286.20"},
				                  {itemName:"item4",quantity:"4",unitCost:'$486.00',gaurdian:'$524.20',price:"$789.20"}*/];
			$scope.AdminsLists=[{date:" 19 JANUARY 2018",invoice:"INV-0001",companyName:"Team Work",email:"gowripathi@gmail.com",mobileNumber:"9848022335",month:"JANUARY"}]
}
angular.module("HealthApplication").controller("companyCtrl", companyCtrl);
