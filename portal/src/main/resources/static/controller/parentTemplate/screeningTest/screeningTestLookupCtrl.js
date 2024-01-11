
function screeningTestLookupCtrl($scope, $state, screeningTestLookupservice, $rootScope,$stateParams) {
	
	$scope. screeningTest=$stateParams. screeningTestObj;
	$scope.addScreeningTest = function(screeningTest) {
		screeningTestLookupservice.addScreeningTest( screeningTest).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Added Successfully');
                $scope.getAllScreeningTest();
			$state.go('main.ScreeningTest');
		}, function(error) {
			//$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllScreeningTest = function() {
		screeningTestLookupservice.getAllScreeningTest().then(function(response) {
			$scope.screeninhTestList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.UpdateScreeningTest = function( screeningTest) {
		screeningTestLookupservice.updateScreeningTest( screeningTest).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Updated Successfully');
                $state.go('main.addScreeningTest');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deleteScreeningTest = function(id) {
		screeningTestLookupservice.deleteScreeningTest(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Deleted Successfully');
                $scope.getAllScreeningTest();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {
		$state.go('main.addScreeningTest');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addScreeningTest');
	};
	$scope.gotoback = function() {
		$state.go('main.ScreeningTest');
	};
	$scope.gotoupdate = function( screeningTest) {
		$state.go('main.updateScreeningTest',{
			screeningTestObj:	 screeningTest
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
			shortAnswer : ''
		});

	}
	$scope.show = function(evalutions) {
		alert(evalutions);

	}
}
angular.module("HealthApplication")
		.controller("screeningTestLookupCtrl", screeningTestLookupCtrl);