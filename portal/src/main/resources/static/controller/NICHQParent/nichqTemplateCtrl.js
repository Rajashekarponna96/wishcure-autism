function nichqTemplateCtrl($scope, $state, $rootScope, NichqQuestionLookupService,
		NichqCategoryLookupService, $rootScope, nichqTemplateService, $log,successMessageHandler) {

	// parent MileStone arrays
	$scope.userName = $rootScope.loggedUsername;
	$scope.firstName = $rootScope.loggedFirstname;
	$scope.lastName = $rootScope.loggedLastname;
	$scope.today = new Date();
	$scope.ParentGetcsbsDataDto = {};
	$scope.datesList = [];

	// Screening test arrays
	$scope.questionCategoryDtoListSocial = [];
	$scope.questionCategoryDtoListImpairments = [];
	$scope.questionCategoryDtoListBehaviour = [];

	$scope.ParentQuestionCategoryDtoListSocial = [];
	$scope.ParentQuestionCategoryDtoListImpairments = [];
	$scope.ParentQuestionCategoryDtoListBehaviour = [];

	// Screening Test get function start

	$scope.getAllNichqCategoryLookupQuestions = function() {
		NichqCategoryLookupService.getAllNichqCategoryLookupQuestions().then(
				function(response) {
					$scope.screeninhTestList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};

	$scope.getAllQuestionCategoriesSocial = function(id) {
		NichqCategoryLookupService.getAllNichqByCategoryStatus(id).then(
				function(response) {
					$scope.flagSocial = true;
					$scope.questionCategoryDtoListSocial = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	}
	// screening test get functions from ParentQuestionCategory table

	$scope.getAllParentQuestionCategoriesSocial = function(tabdate) {
		nichqTemplateService.getAllParentQuestionCategorysNichq(
				$scope.userName, tabdate).then(function(response) {
			$scope.flagSocial = false;
			$scope.ParentQuestionCategoryDtoListSocial = response.data;
			if ($scope.ParentQuestionCategoryDtoListSocial.length < 1) {
				$scope.getAllQuestionCategoriesSocial(tabdate);
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

	$scope.addSocialData = function() {
		$scope.nichqGoalObjDto = {
			"nichqParentCategoryLookupDto" : $scope.questionCategoryDtoListSocial,
			"nichqParentCategory" : $scope.ParentQuestionCategoryDtoListSocial,
			"id" : $rootScope.Category.id
		}
		nichqTemplateService
				.addParentNichqData($scope.userName, $scope.nichqGoalObjDto)
				.then(
						function(response) {
							$scope
									.getAllParentQuestionCategoriesSocial($rootScope.Category.id);
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-full-width",
								timeOut : 2000
							};
							toastr.success('', successMessageHandler.NICHQ_TEMPLATE_ADDED);
						},
						function(error) {
							$scope.message = JSON.stringify(error.data.message
									.trim());
						});
	};
	// csbs save functionallity Ends

	// Add new Goal

	var counter = 0;
	$scope.evalutions = [ {
		name : ''
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
	$scope.Category = {};
	$scope.gotoCategory = function(Category) {
		$rootScope.Category = Category;
	}

	$scope.addNichqQuestion = function(obj) {
		// alert("outside"+JSON.stringify($rootScope.Category));
		// alert("outside"+JSON.stringify(obj));
		// alert("Answer name"+$scope.evalutions);
		$scope.nichqQuestionLookup = {
			"name" : obj.questionName,
			"nichqParentAnswerLookup" : $scope.evalutions,
			"nICHQParentCategoryLookup" : $rootScope.Category
		}
		NichqQuestionLookupService.addNichqParentQuestionLookup($scope.nichqQuestionLookup).then(function(response) {
			$scope.getAllQuestionCategoriesSocial($rootScope.Category.id);
			obj.questionName = undefined;
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.NICHQ_PARENT_QUESTION_LOOKUP_ADDED);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
			obj.questionName = undefined;
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.error($scope.message, 'Error');
		});
	};

	$scope.gotoNICHQ = function() {
		$state.go('main.addNichqParentCategoryLookup');
	};
}
angular.module("HealthApplication").controller("nichqTemplateCtrl",
		nichqTemplateCtrl);