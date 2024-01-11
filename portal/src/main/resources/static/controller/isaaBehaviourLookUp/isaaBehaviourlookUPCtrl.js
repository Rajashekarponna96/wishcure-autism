function isaaBehaviourlookUPCtrl($scope, $state,
		$rootScope, $stateParams, successMessageHandler,isaaBehaviourLookUpService) {
	if ($stateParams.updateCategoryLookUp1 != undefined){
		$scope.categoryLookup=$stateParams.updateCategoryLookUp1;
	}
	$scope.gotoupdateIsaaBehaviour = function(categoryLookup) {
		$state.go('main.updateIsaaBehaviourLookUp',{
			updateCategoryLookUp1:categoryLookup
		});
		
	};
	$scope.gotoaddIsaaBehaviour = function() {
		$state.go('main.addIsaaBehaviourCategoryLookUP');
	};
	
	
	$scope.addCategoryLookup = function(categoryLookup) {
		isaaBehaviourLookUpService.addCategoryLookup(categoryLookup)
				.then(
						function(response) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 2000
							};
							toastr.success('',successMessageHandler.ISAA_BEHAVIOUR_ADDED);
							$scope.getAllCategorylookup();

							$scope.categoryLookup=	categoryLookup;
							$scope.categoryLookup={};
						},
						function(error) {
							toastr.options = {
								closeButton : true,
								progressBar : true,
								showMethod : 'slideDown',
								positionClass : "toast-top-right",
								timeOut : 2000
							};
							$scope.message = JSON.stringify(error.data.message.trim());
							toastr.error($scope.message, 'Error');
						})
	};


	$scope.getAllCategorylookup = function() {
		isaaBehaviourLookUpService.getAllCategorylookup().then(
				function(response) {
					$scope.CategoriesList = response.data;
				}, function(error) {

				})
	};
	
	$scope.updateCategoryLookup = function(categoryLookup) {
		isaaBehaviourLookUpService.updateCategoryLookup(categoryLookup).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.ISAA_BEHAVIOUR_UPDATED );
		                $state.go('main.addIsaaBehaviourCategoryLookUP');
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
				})
	};
	
	$scope.deleteCategoryLookup = function(categoryLookupId) {
		isaaBehaviourLookUpService.deleteCategoryLookup(categoryLookupId).then(
				function(response) {
					$scope.getAllCategorylookup();
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-right",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.ISAA_BEHAVIOUR_DELETED);
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
				})
	};
	$scope.gotoAdd = function() {
		$state.go('main.addIsaaBehaviourCategoryLookUP');
	}
	$scope.gotoback = function() {
		$state.go('main.addIsaaBehaviourCategoryLookUP');
	}

}
angular.module("HealthApplication").controller("isaaBehaviourlookUPCtrl",isaaBehaviourlookUPCtrl);
