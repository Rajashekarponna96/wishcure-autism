//angular.module('HealthApplication').constant('MODULE_CONFIG', ModuleConstants());
function clinicExpenseTypeCtrl($scope, $state, clinicExpenseTypeService, paymethodService,companyService, currencyService, patientService, $rootScope,$stateParams, $http, successMessageHandler) {
	
	$scope.page = 0;
	$scope.size = 5;
	
	$scope.clinicExpenseType=$stateParams.clinicExpenseTypeCtrlObj;
	
	//$scope.monthlyExpense = {};
	$scope.monthlyExpenseList = [];
	$scope.expenseMonthWiseList = [];
	
	$scope.addClinicExpenseType = function(clinicExpenseType) {
		clinicExpenseType.adminUserName=$rootScope.loggedUsername;
		clinicExpenseTypeService.addClinicExpenseType(clinicExpenseType).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('', successMessageHandler.EXPENSE_TYPE_ADD_DATA_SUCCESS);
			$state.go('main.expenseList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.addMonthlyExpense = function(monthlyExpense){
		var monthlyExpense1 = {
			"date" : monthlyExpense.date,
			"clinicExpenseType" : monthlyExpense.clinicExpenseType,
			"amount" : monthlyExpense.amount,
			"adminUserName" : $rootScope.loggedUsername
		};
		$scope.monthlyExpenseList.push(monthlyExpense1);
	}
	$scope.addTotalMonthlyExpenses = function() {
		//monthlyExpenseList.adminUserName=$rootScope.loggedUsername;
		clinicExpenseTypeService.addMonthlyClinicExpense($scope.monthlyExpenseList).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('', successMessageHandler.MONTHLY_EXPENSE_ADD_DATA_SUCCESS);
                
                $state.go('main.monthlyClinicExpenseList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	}
	$scope.addClinicExpenses = function(monthlyExpense) {
		monthlyExpense.adminUserName=$rootScope.loggedUsername;
		var date1 = monthlyExpense.paidDate; 
		if(typeof date1 !== 'string') {
			 mnth = ("0" +(date1.getMonth() + 1)).slice(-2), day = ("0" + date1.getDate()).slice(-2);
			  var changepaiddate =[day,mnth,date1.getFullYear() ].join("-");
			  monthlyExpense.paidDate =changepaiddate; 
			}
		clinicExpenseTypeService.addClinicExpenses(monthlyExpense).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('', successMessageHandler.EXPENSE_TYPE_ADD_DATA_SUCCESS);
                $state.go('main.monthlyClinicExpenseList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
		
	$scope.getAllExpenseTypeListByCompanyUserName= function() {
		clinicExpenseTypeService.getAllExpenseTypeListByCompanyUserName($rootScope.loggedUsername).then(function(response) {
			$scope.expenseTypeList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllExpenseMonthWiseByLoggedInUserName= function() {
		clinicExpenseTypeService.getAllExpenseMonthWiseByLoggedInUserName($rootScope.loggedUsername).then(function(response) {
			$scope.expenseMonthWiseList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllExpensesCompnywise= function() {
		clinicExpenseTypeService.getAllExpensesCompnywise($rootScope.loggedUsername).then(function(response) {
			$scope.expensesInCompanyList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllCurrencys= function() {
		currencyService.getAllCurrencys($rootScope.loggedUsername).then(function(response) {
			$scope.currencysList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllPaymethods= function() {
		paymethodService.getAllPaymethods($rootScope.loggedUsername).then(function(response) {
			$scope.paymethodsList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getCompanyUser = function() {
		companyService.getCompanyUser($rootScope.loggedUsername).then(
				function(response) {
					$scope.user = response.data;
				}, function(error) {

				})
	};
	/*$scope.updateCurrency = function(currency) {
		currencyService.updateCurrency(currency).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.CURRENCY_UPDATE_DATA_SUCCESS);
			$state.go('main.currencyList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};*/
	
	$scope.deleteExpense=function(id){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  clinicExpenseTypeService.deleteExpense(id).then(function(response) {
						 swal("Poof! It has been deleted!", {
						      icon: "success",
						    });
						 $scope.gotoMonthlyClinicExpenseList();
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
					//$scope.getAllCurrencyList();
				});
	  
	$scope.gotoList = function() {
		$state.go('main.expenseList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addExpense');
	};
	$scope.gotoback = function() {
		$state.go('main.expenseList');
	};
	$scope.gotoupdate = function(expense) {
		$state.go('main.updateExpense',{
			expenceObj:	expense
		});
	};
	$scope.gotoMonthlyClinicExpenseList = function() {
		$state.go('main.monthlyClinicExpenseList');
	};
	$scope.gotoAddMonthlyClinicExpenses= function() {
		$state.go('main.addMonthlyClinicExpenses');
	};
	$scope.gotoOverView=function(monthlyExpense){
		$state.go('main.monthlyExpenseViewMore',{
			monthlyExpenseObj:	monthlyExpense
		});
	};
	
	$scope.gotoUpdateClinicExpense=function(monthlyExpense){
		$state.go('main.updateMonthlyClinicExpense',{
			monthlyExpenseObj:	monthlyExpense
		});
	};
	
	$scope.documents = [];
	$scope.documentChanged = function(files) {
		$scope.documents=[];
		if (files != null) {
			var file = files[0];
			$scope.filepath = files[0];
			patientService.uploadDocument($scope.filepath).then(
					function(response) {
						toastr.options = {
							closeButton : true,
							progressBar : true,
							showMethod : 'slideDown',
							positionClass : "toast-top-right",
							timeOut : 2000
						};
						toastr
								.success('',
										successMessageHandler.DOCUMENT_UPLOADED_SUCCESSFULLY);
						$scope.path = response.data.imagePath;
						$scope.document=response.data;
						$scope.document.folder=$scope.folder;
						$scope.documents.push($scope.document);
						files=undefined;
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
	
}
angular.module("HealthApplication")
		.controller("clinicExpenseTypeCtrl", clinicExpenseTypeCtrl);
