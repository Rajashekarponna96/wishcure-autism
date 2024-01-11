function screeningTestCtrl($scope, $state, $rootScope, screeningTestLookupservice,screeningTestQuestionLookupService,$rootScope,
		screeningTestService, $log) {

	// parent MileStone arrays
	$scope.userName = $rootScope.loggedUsername;
	$scope.firstName = $rootScope.loggedFirstname;
	$scope.lastName = $rootScope.loggedLastname;
	$scope.today = new Date();
	$scope.ParentGetScreeningTestDataDto = {};
	$scope.datesList = [];

	// Screening test arrays
	$scope.questionCategoryDtoListSocial = [];
	$scope.questionCategoryDtoListImpairments = [];
	$scope.questionCategoryDtoListBehaviour = [];

	$scope.ParentQuestionCategoryDtoListSocial = [];
	$scope.ParentQuestionCategoryDtoListImpairments = [];
	$scope.ParentQuestionCategoryDtoListBehaviour = [];

	// Screening Test get function start
	
	$scope.getAllScreeningTest = function() {
		screeningTestLookupservice.getAllScreeningTest().then(function(response) {
			$scope.screeninhTestList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getAllQuestionCategoriesSocial = function(id) {
		screeningTestLookupservice.getAllScreeningTestByCategoryStatus(id).then(
				function(response) {
					$scope.flagSocial = true;
					$scope.questionCategoryDtoListSocial = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	}
	// screening test get functions from ParentQuestionCategory table
	
	$scope.getAllParentQuestionCategoriesSocial = function(tabdate) {
		$scope.GetScreeningTestDataDto = {
			"username" : $scope.userName,
			"id" :tabdate.id,
			"date" : tabdate.date
		}
		screeningTestService.getAllParentQuestionCategorysScreeningTest($scope.GetScreeningTestDataDto).then(function(response) {
			$scope.flagSocial = false;
			$scope.ParentQuestionCategoryDtoListSocial = response.data;
			if ($scope.ParentQuestionCategoryDtoListSocial.length < 1) {
				$scope.getAllQuestionCategoriesSocial(tabdate.id);
			}
			if (tabdate.date == $scope.todaytabdate) {
				$scope.addFlag = true;
			} else {
				$scope.addFlag = false;
			}
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	}
	// Screening Test save functionallity Starts

	$scope.addSocialData = function(tabdate) {
		$scope.ScreeningTestGoalObjectDto = {
			"screeningTestCategoryLookupDto" : $scope.questionCategoryDtoListSocial,
			"screeningTestCategory" : $scope.ParentQuestionCategoryDtoListSocial,
			"id" : tabdate.id,
			"date" : tabdate.date
		}
		screeningTestService.addParentScreeningTestData($scope.userName,$scope.ScreeningTestGoalObjectDto).then(function(response) {
			$scope.getAllParentQuestionCategoriesSocial(tabdate);
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', 'Social Interaction Data Added Successfully');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	// ScreeningTest save functionallity Ends

	// code for screening test save data by date
	$scope.today = new Date();

	var tabs = [
			{
				title : 'Zero',
				content : 'Woah...that is a really long title!'
			},

			{
				title : 'Twenty',
				content : "If you're still reading this, you should just go check out the API docs for tabs!"
			} ], selected = null, previous = null;
	$scope.tabs = tabs;
	$scope.selectedIndex = 0;
	$scope.$watch('selectedIndex', function(current, old) {
		previous = selected;
		selected = tabs[current];
		if (old + 1 && (old != current))
			$log.debug('Goodbye !');
		if (current + 1)
			$log.debug('Hello !');
	});

	if ($scope.today.getDate() < 10) {
		$scope.date = "0" + $scope.today.getDate();
	} else {
		$scope.date = $scope.today.getDate();
	}
	if ($scope.today.getMonth() < 10) {
		$scope.monthnumbaer = $scope.today.getMonth() + 1;
		$scope.month = "0" + $scope.monthnumbaer;
	} else {
		$scope.month = $scope.today.getMonth() + 1;
	}
	$scope.todaytabdate = $scope.month + " " + $scope.date + " "
			+ $scope.today.getFullYear();

	console.log(" $scope.todaytabdate" + $scope.todaytabdate);

	$scope.addFlag = false;
	$scope.addTab = function(tabid) {
		$scope.tabdateoBJ={
				"date":$scope.todaytabdate,
		        "id":tabid
		}
		$scope.lastdateObject=$scope.datesList[$scope.datesList.length - 1]
		if ($scope.lastdateObject.date != $scope.todaytabdate) {
			$scope.datesList.push($scope.tabdateoBJ);
			$scope.addFlag = true;
		} else {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-right",
				timeOut : 2000
			};
			toastr.error('', 'Already Screening Test Goal  Added!!');
		}

	};
	$scope.getAllCratedDatesOfPatientGoals = function(id) {
		$scope.tabdateoBJ={
				"date":$scope.todaytabdate,
		        "id":id
		}
		screeningTestService.getAllCreatedDatesByScreeningTest($scope.userName,id).then(function(response) {
			$scope.datesList = response.data;
			if ($scope.datesList != undefined) {
				if ($scope.datesList.length < 1) {
					$scope.datesList.push($scope.tabdateoBJ);
					$scope.addFlag = true;
				}
			}
			$scope.getAllParentQuestionCategoriesSocial($scope.datesList[0]);
		})
	};
	$scope.gotoAddGoal= function(){
		$state.go('main.addScreeningTestQuestion')
	};
	$scope.gotoback = function() {
		$state.go('main.addScreeningTest');
	};
	
	//Add new Goal
	
	var counter = 0;
	$scope.evalutions = [ {
		name : 'yes/No'
	} ];

	$scope.newItem = function() {
		counter++;
		$scope.evalutions.push({
			name : ''
		});
	}
	$scope.deleteItem = function() {
		counter--;
		$scope.evalutions.pop({
			name : ''
		});

	}
	$scope.Category={};
	$scope.gotoCategory=function(Category){
		$rootScope.Category=Category;
	}

	$scope.addScreeningTestQuestion = function(obj) {
		//alert("outside"+JSON.stringify($rootScope.Category));
		//alert("outside"+JSON.stringify(obj));
		//alert("Answer name"+$scope.evalutions);
		$scope.ScreeningTestQuestionLookup={
				"name":obj.questionName,
				"screeningTestAnswerLookups":$scope.evalutions,
				"screeningTestCategoryLookup":$rootScope.Category	
		}
		screeningTestQuestionLookupService.addScreeningTestQuestion($scope.ScreeningTestQuestionLookup).then(function(response) {
			$scope.getAllQuestionCategoriesSocial($rootScope.Category.id);
			obj.questionName=undefined;
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Added Successfully');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
			obj.questionName=undefined;
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.error($scope.message,'Error');
		});
	};

}
angular.module("HealthApplication").controller("screeningTestCtrl",
		screeningTestCtrl);