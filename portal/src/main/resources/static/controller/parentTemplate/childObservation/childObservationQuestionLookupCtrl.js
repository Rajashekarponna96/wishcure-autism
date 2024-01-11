function childObservationQuestionLookupCtrl($scope, $state, childObservationQuestionLookupService,childObservationCategoryLookupService, $rootScope,$stateParams) {
	
	$scope.object={}
	$scope.questionName="";
	//$scope.cid;
	
	        $scope.childObservationQuestionLookup=$stateParams.childObservationQuestionLookupObject;
	        $scope.answer=$stateParams.answerObject;
	  //add Quest Lookups      
	$scope.addChildObservationQuestionLookup = function(questionName) {
		//alert("$scope.goal object::"+JSON.stringify(goal))
		//alert("$scope.goal questionName::"+JSON.stringify(goal.questionName))
		$scope.ObservationQuestionLookupObj={
				"name":questionName,
				//"answer":$scope.ans,
				"childObservationAnswerLookups":$scope.answers,
				"childObservationCategoryLookup":$scope.object
		}
		//alert("$scope.questionName::"+JSON.stringify(goal.questionName))
		//alert("$scope.object::"+JSON.stringify($scope.object))
		childObservationQuestionLookupService.addChildObservationQuestionLookup($scope.ObservationQuestionLookupObj).then(function(response) {
                 //
			$scope.getGeneralQuestionsByPersonAndCatId($scope.object);
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
	//get Questions From Quest Lookup category---id
	$scope.getQuestionsByCategoryId= function(category) {
		childObservationQuestionLookupService.getQuestionsByCategoryId(category.id).then(function(response) {
			$scope.childQuestionsListByCategoryId = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
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
	//General Questions From Lookup Don't touch
	$scope.saveGeneralQuestion= function(lookupList) {
	//	alert("56 Line:"+JSON.stringify(lookupList));
		$scope.userName=$rootScope.loggedUsername;
		//alert("userName::"+JSON.stringify($scope.userName));
		childObservationQuestionLookupService.saveGeneralQuestion(lookupList,$scope.userName).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Added Successfully');
			//$state.go('main.ChildObservation');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
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
	//get Questions From General Quest 
	$scope.getGeneralQuestionsByPersonAndCatId= function(category) {
		$scope.object=category;
		$scope.userName=$rootScope.loggedUsername;
		childObservationQuestionLookupService.getGeneralQuestionsByPersonAndCatId($scope.userName,$scope.object.id).then(function(response) {
			$scope.childObservationQuestionsListByPerosnIdAndCatId = response.data;
			if ($scope.childObservationQuestionsListByPerosnIdAndCatId.length < 1) {
				$scope.getQuestionsByCategoryId(category);
			}
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	//Update General Questions
	$scope.updateGeneralQuestion= function(lookupList) {
		$scope.userName=$rootScope.loggedUsername;
		childObservationQuestionLookupService.updateGeneralQuestion(lookupList,$scope.userName).then(function(response) {
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
	//getAll General Questions
	$scope.getAllChildObservationQuestions= function() {
		childObservationQuestionLookupService.getAllChildObservationQuestions().then(function(response) {
			$scope.childObservationQuestionsList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	//getAll Category Lookups Ids
	$scope.getAllChildObservationCategoryLookupsIds= function() {
		childObservationCategoryLookupService.getAllChildObservationCategoryLookupsIds().then(function(response) {
			$scope.childObservationCategoryLookupsIdsList = response.data;
			//ng-init call is done here
//$scope.getGeneralQuestionsByPersonAndCatId($scope.childObservationCategoryLookupsIdsList[0]);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	//Get Category Lookups
	$scope.getAllChildObservationCategoryLookups= function() {
		childObservationCategoryLookupService.getAllChildObservationCategoryLookups().then(function(response) {
			$scope.childObservationCategoryLookupsList = response.data;
			$scope.getGeneralQuestionsByPersonAndCatId($scope.childObservationCategoryLookupsList[0]);
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
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
	//Question
	$scope.getAllChildObservationQuestionLookups = function() {
		childObservationQuestionLookupService.getAllChildObservationQuestionLookups().then(function(response) {
			$scope.childObservationQuestionLookupsList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
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
	//DashBoard
	$scope.getAllAnswersDashBoard = function() {
	//	alert("dashBoard")
		$scope.userName=$rootScope.loggedUsername;
		childObservationQuestionLookupService.getAllAnswersDashBoard($scope.userName).then(function(response) {
			$scope.childObservationDashBoardsList = response.data;
			//alert("resp:"+JSON.stringify($scope.childObservationDashBoardsList));
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
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
	//update QuestionLookup
	$scope.updateChildObservationQuestionLookup = function( childObservationQuestionLookup) {
		childObservationQuestionLookupService.updateChildObservationQuestionLookup( childObservationQuestionLookup).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Updated Successfully');
                $state.go('main.childObservationQuestionLookuplist');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
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
	//ADD CATEGORY LOOKUP
	$scope.addChildObservationCategoryLookup = function(childObservationCategoryLookup) {
		childObservationCategoryLookupService.addChildObservationCategoryLookup(childObservationCategoryLookup).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Added Successfully');
			$state.go('main.ChildObservation');
			$scope.childObservationCategoryLookup={};
			$scope.getAllChildObservationCategoryLookups();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	//DELETE QuestionLookup
	$scope.deleteChildObservationQuestionLookup = function(id) {
		childObservationQuestionLookupService.deleteChildObservationQuestionLookup(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Deleted Successfully');
                $scope.getAllChildObservationQuestionLookups();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
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
	
	$scope.gotoList = function() {
		$state.go('main.childObservationQuestionLookuplist');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addChildObservationQuestionLookup');
	};
	$scope.gotoback = function() {
		$state.go('main.ChildObsevation');
	};
	$scope.gotobak = function() {
		$state.go('main.ChildObservation');
	};
	$scope.gotoupdate = function(childObservationQuestionLookup,Answer) {
		$state.go('main.updateChildObservationQuestionLookup',{
			childObservationQuestionLookupObject: childObservationQuestionLookup,
			answerObject: Answer
		});
	};
	var counter = 0;
	$scope.answers = [ {
		name : ''
	} ];

	$scope.newItem = function() {
		counter++;
		$scope.answers.push({
			name : ''
		});
	}
	$scope.deleteItem = function() {
		counter--;
		$scope.answers.pop({
			shortAnswer : ''
		});

	}
	$scope.show = function(answers) {
		alert(answers);

	}
}
angular.module("HealthApplication")
		.controller("childObservationQuestionLookupCtrl", childObservationQuestionLookupCtrl);