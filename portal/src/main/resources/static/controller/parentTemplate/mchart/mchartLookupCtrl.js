function mchartLookupCtrl($scope, $state, mchartLookupService, $rootScope,$stateParams, successMessageHandler) {
	
	$scope.mchart=$stateParams.mchartLookupObj;
	$scope.addMchart = function(mchart) {
		mchartLookupService.addMchart(mchart).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
			$scope.mchart={};
                toastr.success('',successMessageHandler.CATEGORY_ADD_DATA_SUCCESS);
                $scope.getAllMcharts();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllMcharts = function() {
		mchartLookupService.getAllMcharts().then(function(response) {
			$scope.mchartList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.updateMchart = function(mchart) {
		mchartLookupService.updateMchart(mchart).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Updated Successfully');
			$state.go('main.listMchartLookup');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deleteMchart = function(id) {
		mchartLookupService.deleteMchart(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.CATEGORY_DELETE_DATA_SUCCESS);
                $scope.getAllMcharts();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {
		$state.go('main.listMchartLookup');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addMchartLookup');
	};
	$scope.gotoback = function() {
		$state.go('main.listMchartLookup');
	};
	$scope.gotoupdate = function(mchart) {
		$state.go('main.updateMchartLookup',{
			mchartLookupObj:mchart
		});
	};
}
angular.module("HealthApplication")
		.controller("mchartLookupCtrl", mchartLookupCtrl);
