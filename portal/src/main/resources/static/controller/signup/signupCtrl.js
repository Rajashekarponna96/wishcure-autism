function signupCtrl($scope, $state, signupService, $rootScope, countryService,
		stripeService, packageMasterService, subscriptionMasterService,
		packagePricingService, $http, companyTypeService, $stateParams,
		PATIENT_MODULE_CONFIG, successMessageHandler) {
	$scope.userDto = {};
	$scope.userObj = {};
	$scope.user = {};
	$scope.packagePricing1 = {};
	$scope.companyType = {};
	$scope.hidetag = false;
	$scope.nextTag = false;
	$scope.userPackage = {};
	$scope.userCompanyTypeObj = {};
	$scope.activeColor1 = {};
	$scope.activeColor2 = {};
	$scope.activeColor3 = {};
	$scope.gotoPrevious = false;
	$scope.packagePricing = {};
	$scope.packageNameMaster = {};
	$scope.stripePackage = {};
	
	$scope.achBankInfo = {
		"routing_number" : undefined,
		"account_number" : undefined,
		"account_holder_name" : undefined,
		"account_holder_type" : undefined,
		"country" : "us",
		"currency" : "usd",
		"token" : undefined,
		"customerEmail":undefined
	}
	$scope.stripePayment = {
		"stripePackage" : {}
	};
	$scope.userDto = {
		"stripePayment" : {},
		"transaction" : {}
	};
	$scope.tokenDto = {
		"token" : undefined
	};
	if ($stateParams.signupUser != undefined) {
		$scope.userObj = $stateParams.signupUser;
	}
	;

	if ($stateParams.userPackagePricing != undefined) {
		$scope.packagePricing1 = $stateParams.userPackagePricing;
	}
	;

	if ($stateParams.userCompanyType != undefined) {
		$scope.companyType = $stateParams.userCompanyType;
	}
	;
	if ($stateParams.stripePlan != undefined) {
		$scope.stripePlan = $stateParams.stripePlan;
	}
	;
	if ($stateParams.userObject != undefined) {
		$scope.user = $stateParams.userObject;
	}
	;
	if ($stateParams.UserCompanyTypeObject != undefined) {
		$scope.userCompanyTypeObj = $stateParams.UserCompanyTypeObject;
	}
	;

	if ($stateParams.userCompany != undefined) {
		$scope.companyType = $stateParams.userCompany;
	}
	;

	if ($stateParams.flag != undefined) {
		$scope.gotoPrevious = $stateParams.flag;
	}
	;
	if ($stateParams.pckgMaster != undefined) {
		$scope.packagePricing.packageNameMaster = $stateParams.pckgMaster;
	}
	;
	if ($stateParams.pckgPrice != undefined) {
		$scope.packagePrice = $stateParams.pckgPrice;
	}
	;
	if ($stateParams.pkname != undefined) {
		$scope.packageName = $stateParams.pkname;
	}
	;

	$scope.getAddressFromGoogle=function(){
		var address1 = document.getElementById('street_number').value;
		var address2 = document.getElementById('route').value;
		var city = document.getElementById('locality').value;
		var state = document.getElementById('administrative_area_level_1').value;
		var country = document.getElementById('country').value;
		var zipcode = document.getElementById('postal_code').value;
		$scope.user.address1=address1;
		$scope.user.address2=address2;
	/*	$scope.user.address3=$scope.user.address1+" "+$scope.user.address2;
		alert("data in address1"+$scope.user.address1);
		alert("data in address2"+$scope.user.address2);*/
		$scope.user.city=city;
		$scope.user.state=state;
		$scope.user.country=country;
		$scope.user.zipcode=zipcode;
	};
	
	
	$scope.doSignUp = function() {
		
		//google address binding
		$scope.userDto = $scope.user;
		if ($scope.user.organizationName != undefined) {
			$scope.userDto.companyName = $scope.user.organizationName;
		}

		
		/*$scope.userDto.stripePackage = $scope.user.stripePackage;
		$scope.stripePayment.stripePackage = $scope.user.stripePackage;
		$scope.userDto.stripePayment = $scope.stripePayment;
		$scope.userDto.transaction = $scope.transaction;*/
		$scope.userDto.companyType = $scope.companyType;
		signupService.doSignUp($scope.userDto)
				.then(
						function(response) {
							//$scope.chargeCard(token);
							$scope.nextTag = true;
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 5000
							};
							toastr
									.success('',
											successMessageHandler.SIGNUP_SUCCESS);
							$rootScope.loginMessage = "Thank you Successfully Registered!!";
							 $state.go('login');
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
							$scope.message = $scope.message1.substring(1,$scope.message1.length - 1);
							toastr.error($scope.message, 'Error');

						});
	};

	$scope.$on('loader_show', function(event, args) {
		$rootScope.loader = true;
	});
	$scope.$on('loader_hide', function(event, args) {
		$rootScope.loader = false;
	});

	$scope.getAllCountries = function() {
		countryService.getAllCountries().then(function(response) {
			$scope.countriesList = response.data;
		}, function(error) {

		})
	};
	$scope.getCountrysBasedOnIsdCode = function() {
		countryService.getCountrysBasedOnIsdCode().then(function(response) {
			$scope.countriesList = response.data;
			//$scope.getAllStatesByCountryId($scope.countriesList[0].id);
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
	$scope.gotoPayments = function() {
		$state.go('payment');
	};

	// payments
	$scope.getAllPackageMAstersList = function() {
		packageMasterService.getAllPackageMAstersList().then(
				function(response) {
					$scope.packageMastersLists = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};
	$scope.getAllSubscriptionMastersList = function() {
		subscriptionMasterService.getAllSubscriptionMastersList().then(
				function(response) {
					$scope.subscriptionMasterLists = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.addPackagePricing = function(packagePricing) {
		packagePricingService.addPackagePricing(packagePricing).then(
				function(response) {
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());

				});
	};
	$scope.gotoLogin = function() {
		$state.go('login');
	};

	$scope.getPackagePriceMatserInfo = function(subscriptionName, packagename) {
		packagePricingService.getPackagePriceMatserInfo(subscriptionName,
				packagename).then(function(response) {
			$scope.packagePricing1 = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());

		});

	};

	$scope.getAllCompanies = function() {
		companyTypeService.getAllCompanies().then(function(response) {
			$scope.companyTypesList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.gotoPay = function(user, packagePricing1, companyType) {
		$state.go('blank', {
			signupUser : user,
			PackagePrice : packagePricing1,
			cmpType : companyType
		});
	};

	/* payment integration starts */

	$scope.chargeCard = function(token) {
		var PaymentDto = {
			"source" : token,
			"currency" : "usd",
			"description" : $scope.user.email,
			"email" : $scope.user.email,
			"stripeProductId" : $scope.user.stripePackage.packageId,
			"stripePlanId" : $scope.stripePlan.planId
		};
		return $http.post(PATIENT_MODULE_CONFIG.CHARGE_FOR_TRANSACTION(),
				PaymentDto).then(function(response) {
			$scope.card = response.data;
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 5000
			};
			$state.go('login');
			toastr.success('', successMessageHandler.SIGNUP_PAYMENT_SUCCESS);
			$scope.transaction = {
				"transactionName" : $scope.card.object,
				"description" : $scope.card.outcome.sellerMessage,
				"paymentInvoiceNumber" : $scope.card.invoice,
				"paymentTransactionNumber" : $scope.card.balance_transaction,
				"status" : $scope.card.status,
				"payby" : $scope.card.source.name,
				"chargeTransactionId" : $scope.card.id
			};
			$scope.stripePayment = {
				"amount" : $scope.card.amount / 100,
				"gatewayStatus" : $scope.card.status,
				"trasaction" : $scope.transaction
			};
			// $scope.doSignUp();
		},function(error){
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-right",
					timeOut : 2000
				};
				$scope.message1 = JSON.stringify(error.data.message
						.trim());
				$scope.message = $scope.message1.substring(1,
						$scope.message1.length - 1);
				toastr.error($scope.message, 'Error');
			
		})
	};
	// create customer for stripe account
	$scope.createCustomer = function(token) {
		$scope.customerDto = {
			"description" : "customer creation for stripe",
			"email" : $scope.user.email,
			"token" : token
		}
		return $http.post(PATIENT_MODULE_CONFIG.CREATE_CUSTOMER(),
				$scope.customerDto).then(function(response) {
			console.log("success");
		}, function(error) {

		})
	};

	var handler = StripeCheckout
			.configure({
				key : 'pk_test_7W4ilnHOU35278UonFn8GovO',
				image : 'https://stripe.com/img/documentation/checkout/marketplace.png',
				locale : 'auto',
				token : function(token) {
					console.log("token  " + JSON.stringify(token));
					$scope.doSignUp(token.id);
					// $scope.chargeCard(token.id);
					// $scope.createCustomer(token.id);
					// $scope.doSignUp(token.id);
				}
			});

	$scope.clickOnPurchase = function() {
		handler.open({
			name : 'Stripe.com',
			description : '2 widgets',
			zipCode : true,
			amount : 1 * 100
		});
	};
	/*
	 * var stripe = require("stripe")( "pk_test_7W4ilnHOU35278UonFn8GovO" );
	 */

	$scope.getStripeProduct = function() {
		return $http.get(PATIENT_MODULE_CONFIG.GET_STRIP_PRODUCTS()).then(
				function(response) {
					console.log("success");
				}, function(error) {

				});
	};
	// Close Checkout on page navigation:
	window.addEventListener('popstate', function() {
		handler.close();
	});

	/* payments end */

	$scope.gotoConfirmation = function() {
		$state.go('signup.cofirmation');
	};
	$scope.gotoPrimaryRegistration = function() {
		$state.go('signup.primaryRegistration');
	};
	$scope.gotopackageSubscription = function() {
		$state.go('signup.packageSubscription');
	};
	$scope.gotoNextPackageSubscription = function(user, companyType) {
		$state.go('signup.packageSubscription', {
			userObject : user,
			UserCompanyTypeObject : companyType,
			stripePlan : $scope.stripePlan
		});
	};
	$scope.gotoNextConfirmation = function(user, packagePricing1,
			userCompanyTypeObj) {
		$state.go('signup.cofirmation', {
			userObject : user,
			userPackagePricing : packagePricing1,
			userCompanyType : userCompanyTypeObj,
			stripePlan : $scope.stripePlan
		});
	};
	$scope.gotoPreviousPackageSubscription = function(user, packagePricing1,
			packageNameMaster) {
		$state.go('signup.packageSubscription', {
			userObject : user,
			userPackagePricing : packagePricing1,
			UserCompanyTypeObject : $scope.companyType,
			pckgMaster : packageNameMaster,
			stripePlan : $scope.stripePlan

		});

	};
	$scope.gotoPreviousPrimaryRegistration = function(user, userCompanyTypeObj,
			packagePricing1, packageNameMaster) {
		$scope.gotoPrevious = true;
		$state.go('signup.primaryRegistration', {
			userObject : user,
			userCompany : userCompanyTypeObj,
			flag : $scope.gotoPrevious,
			pckgPrice : packagePricing1,
			pkname : packageNameMaster,
			stripePlan : $scope.stripePlan

		});
		$scope.getCountrysBasedOnIsdCode();
	};

	$scope.gotoNext2PackageSubscription = function(user, companyType,
			packagePrice, packageName) {
		$state.go('signup.packageSubscription', {
			userObject : user,
			userPackagePricing : packagePrice,
			UserCompanyTypeObject : companyType,
			pckgMaster : packageName,
			stripePlan : $scope.stripePlan
		});
	};

	$scope.activeColor1 = {
		"background-color" : "DodgerBlue ",
		"color" : "white"
	};
	$scope.getAllStripePackages = function() {
		stripeService.getAllStripePackages().then(function(response) {
			$scope.stripePackagesList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllStripePlansByProductId = function(productId) {
		stripeService.getAllStripePlanByProductId(productId).then(
				function(response) {
					$scope.stripePlan = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.createAccount = function(token) {
		$scope.tokenDto = {
			"token" : token
		};
		stripeService.createAccount($scope.tokenDto).then(function(response) {

		}, function(error) {

		})
	};

	var stripe = Stripe('pk_test_7W4ilnHOU35278UonFn8GovO');

	// Stripe Response Handler
	$scope.stripeCallback = function(code, result) {
		if (result.error) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-right",
				timeOut : 2000
			};
			toastr.error("Enter Valid Card Details!", 'Error');
		} else {
			$scope.doSignUp(result.id);
		}
	};

	$scope.doSignupWithAchBank=function(achBankInfo){
		$scope.userDto = $scope.user;
		if ($scope.user.organizationName != undefined) {
			$scope.userDto.companyName = $scope.user.organizationName;
		}

		$scope.userDto.stripePackage = $scope.user.stripePackage;
		$scope.stripePayment.stripePackage = $scope.user.stripePackage;
		$scope.userDto.stripePayment = $scope.stripePayment;
		$scope.userDto.transaction = $scope.transaction;
		$scope.userDto.companyType = $scope.companyType;
		achBankInfo.customerEmail=$scope.user.email;
		achBankInfo.stripeProductId= $scope.user.stripePackage.packageId;
		achBankInfo.stripePlanId= $scope.stripePlan.planId;
		signupService
				.doSignUp($scope.userDto)
				.then(
						function(response) {
							$scope.createAchBankAccount(achBankInfo);
							$scope.nextTag = true;
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 5000
							};
							toastr
									.success('',
											successMessageHandler.SIGNUP_SUCCESS);
							$rootScope.loginMessage = "Thank you Successfully Registered!!";
							// $state.go('login');
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
							$scope.message = $scope.message1.substring(1,
									$scope.message1.length - 1);
							toastr.error($scope.message, 'Error');

						});
	
	}
	
	$scope.CheckAch = function(achBankInfo) {
		var stripe = Stripe('pk_test_7W4ilnHOU35278UonFn8GovO');
		stripe.createToken('bank_account', achBankInfo).then(
				function(result) {
					if (result.error) {

						toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-right",
							timeOut : 2000
						};
						// toastr.error("Enter Valid Bank Account Details!",
						// 'Error');
						toastr.error(result.error.message, 'Error');
					} else {
						achBankInfo.token = result.token.id;
						/*alert("result.token is success "
								+ JSON.stringify(achBankInfo.token));*/
						// handle result.error here
						$scope.doSignupWithAchBank(achBankInfo);
						//$scope.createAchBankAccount(achBankInfo);
					}
				});
	};

	$scope.createAchBankAccount = function(achBankDetails) {
		stripeService.createAchBankAccount(achBankDetails).then(function(response){
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 5000
			};
			$state.go('login');
			toastr.success('', successMessageHandler.paymentSuccessMessage);
		},function(error){
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-right",
					timeOut : 2000
				};
				$scope.message1 = JSON.stringify(error.data.message
						.trim());
				$scope.message = $scope.message1.substring(1,
						$scope.message1.length - 1);
				toastr.error($scope.message, 'Error');
			
		})
	}
	
	$scope.emptyDataAtACH=function(){
		if($scope.payments=='true'){
			$scope.achBankInfo= undefined;
		}
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
}
angular.module("HealthApplication").controller("signupCtrl", signupCtrl);
