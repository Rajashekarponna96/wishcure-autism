function nichqParentGeneralCtrl($scope, $state, nichqParentGeneralService, $rootScope,$stateParams,successMessageHandler) {
	
	$scope.nichq=$stateParams. nichqObj;
	
	$scope.addNichq = function(nihcq) {
		nichqParentGeneralService.addNichq(nihcq).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.NICHQ_PARENT_ADDED);
			$state.go('main.addNichqTemplate');
		}, function(error) {
			//$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.getAllNichqs = function() {
		nichqParentGeneralService.getAllNichqs().then(function(response) {
			$scope.nichqList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.updateNichq = function(nichq) {
		nichqParentGeneralService.updateNichq(nichq).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.NICHQ_PARENT_UPDATED);
                $state.go('main.addNichqCategory');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deleteNichq = function(id) {
		nichqParentGeneralService.deleteNichq(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.NICHQ_PARENT_DELETED);
                $scope.getAllNichqs();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {
		$state.go('main.addNichqCategory');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addNichqTemplate');
	};
	$scope.gotoback = function() {
		$state.go('main.addNichqTemplate');
	};
	$scope.gotoupdate = function( csbs) {
		$state.go('main.updateNichqCategory',{
			nichqObj : nichq
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
		.controller("nichqParentGeneralCtrl", nichqParentGeneralCtrl);