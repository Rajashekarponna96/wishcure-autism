function isaaBehaviourQuestionLookupCtrl($scope, $state, $stateParams,
		isaaBehaviourQuestionLookUpService, isaaBehaviourLookUpService,successMessageHandler) {

	if($stateParams.updateisaaBehaviour != undefined){
		$scope.isaabQuestion = $stateParams.updateisaaBehaviour;
		console.log(JSON.stringify($scope.isaabQuestion));
	}
	
	

$scope.addIsaaBehaviourQuestionLookup = function() {
	alert('controller');
		$scope.iSAABehaviorQuestionLookup = {
			"name" : $scope.name,
			"iSAABehaviorAnswerLookups" : $scope.evalutions,
			"iSAABehaviorCategoryLookup" : $scope.isaabehaviour
		}
		isaaBehaviourQuestionLookUpService.addIsaaBehaviourQuestionLookup($scope.iSAABehaviorQuestionLookup)
				.then(function(response) {
					$state.go('main.isaaBehaviourQuestionLookupList');
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', successMessageHandler.ISAA_BEHAVIOUR_QUESTION_LOOKUP_DATA_ADDED);
				}, function(error) {
					$scope.message = JSON.stringify(error.data.message.trim());
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
	$scope.getAllCategorylookup = function() {
		isaaBehaviourLookUpService.getAllCategorylookup().then(
				function(response) {
					$scope.CategoriesList = response.data;
				}, function(error) {

				})
	};
	$scope.getAllisaaBehaviourQuestionLookup = function() {
		isaaBehaviourQuestionLookUpService.getAllisaaBehaviourQuestionLookup().then(function(response) {
			$scope.isaaBehaviourQuestionlookupList = response.data;
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
	
	$scope.deleteIsaaBehaviourQuestionlookup = function(isaaBehaviopurQuestionlookupid) {
		isaaBehaviourQuestionLookUpService.deleteIsaaBehaviourQuestionlookup(isaaBehaviopurQuestionlookupid).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.ISAA_BEHAVIOUR_QUESTION_LOOKUP_DATA_DELETED);
			$scope.getAllisaaBehaviourQuestionLookup();
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
	

	$scope.updateIsaaBehaviourQuestionLookup = function( isaabQuestion) {
		alert('controller');
		isaaBehaviourQuestionLookUpService.updateIsaaBehaviourQuestionLookup(isaabQuestion).then(function(response) {
			alert('response');
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.ISAA_BEHAVIOUR_QUESTION_LOOKUP_DATA_UPDATED);
                $state.go('main.isaaBehaviourQuestionLookupList');
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
		$state.go('main.isaaBehaviourQuestionLookupList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addIsaaBehaviourQuestionLookup');
	};
	$scope.gotoback = function() {
		$state.go('main.isaaBehaviourQuestionLookupList');
	};
	$scope.gotoupdateIsaaBehaviourQuestion = function(isaabQuestion) {
		$state.go('main.updateIsaaBehaviourQuestionLookup',{
			updateisaaBehaviour : isaabQuestion
				
		});
	};
	
	
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
}

angular.module("HealthApplication").controller("isaaBehaviourQuestionLookupCtrl",isaaBehaviourQuestionLookupCtrl);