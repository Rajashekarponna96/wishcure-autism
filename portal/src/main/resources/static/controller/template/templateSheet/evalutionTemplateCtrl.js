function templateCtrl($scope, $state, templateService, evalutionCategorService,
		$rootScope, $stateParams) {

	$scope.answer = {
		"question" : null,
		"shortAnswer" : null
	};
	$scope.template = $stateParams.templateObj;
	$scope.addtemplate = function(template) {
		templateService.addtemplate(template).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', ' Data Added Successfully');
			$state.go('main.templateList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.addanswerObj = function() {
		alert("JSON.stringify  "+JSON.stringify($scope.evalutionQuestion));
		$scope.answer = {
			"shortAnswer" : $scope.addShortAnswer,
			"question" : $scope.evalutionQuestion
		};
		alert(JSON.stringify($scope.answer));
		templateService.addanswer($scope.answer).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', ' Data Added Successfully');
			$state.go('main.templateList');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllTemplates = function() {
		templateService.getAllTemplates().then(function(response) {
			$scope.templateList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getanswers = function(questionid) {
		alert(questionid);
		evalutionCategorService.getAnswers(questionid).then(function(response) {
			$scope.answersList = response.data;
		}, function(error) {

		})

	};
	/*$scope.getAllEvalutionCategories = function() {
		evalutionCategorService.getAllEvalutionCategories().then(
				function(response) {
					$scope.evalutionCategoryList = response.data;
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
				});
	};*/
	$scope.getAllQuestionCategories = function() {
		evalutionCategorService.getAllEvalutionCategories().then(
			function(response) {
				$scope.questionCategoryList = response.data;
			}, function(error) {
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		
	};

	$scope.deleteTemplate = function(id) {
		templateService.deleteTemplate(id).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', ' Data Deleted Successfully');
			$scope.getAllTemplates();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {
		$state.go('main.templateList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addtemplate');
	};
	$scope.gotoback = function() {
		$state.go('main. templateList');
	};

	var counter = 0;
	$scope.elements = [ {
		id : counter,
		value : ''
	} ];

	$scope.newItem = function() {
		counter++;
		$scope.elements.push({
			id : counter,
			value : ''
		});
	}
	$scope.deleteItem = function() {
		counter--;
		$scope.elements.pop({
			id : counter,
			value : ''
		});

	}
	$scope.show = function(elements) {
		alert(JSON.stringify(elements));

	}

}
angular.module("HealthApplication").controller("templateCtrl", templateCtrl);
