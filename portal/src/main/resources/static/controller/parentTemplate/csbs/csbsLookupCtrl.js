function csbsLookupCtrl($scope, $state, csbsLookupservice, $rootScope,$stateParams) {
	
	$scope. csbs=$stateParams. csbsObj;
	$scope.addcsbs = function(csbs) {
		csbsLookupservice.addcsbs( csbs).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Added Successfully');
			$state.go('main.addCsbsTemplate');
		}, function(error) {
			//$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllcsbs = function() {
		csbsLookupservice.getAllcsbs().then(function(response) {
			$scope.csbsList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.Updatecsbs = function( csbs) {
		csbsLookupservice.updatecsbs( csbs).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Updated Successfully');
                $state.go('main.addCsbsCategory');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deletecsbs = function(id) {
		csbsLookupservice.deletecsbs(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Deleted Successfully');
                $scope.getAllcsbs();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {
		$state.go('main.addCsbsCategory');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addCsbsTemplate');
	};
	$scope.gotoback = function() {
		$state.go('main.addCsbsTemplate');
	};
	$scope.gotoupdate = function( csbs) {
		$state.go('main.updateCsbsCategory',{
			csbsObj:csbs
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
		.controller("csbsLookupCtrl", csbsLookupCtrl);