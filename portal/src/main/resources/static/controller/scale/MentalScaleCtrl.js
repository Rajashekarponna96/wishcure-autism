function MentalScaleCtrl($scope, $state,MentalScaleService,MentalClusterService, $rootScope,$stateParams) {

	$scope.evalution = $stateParams.mentalScaleObj;
	
	$scope.mentalScales={
		"mentalScaleCheckList":[]
	}
	
	$scope.addMentalScale = function() {
		$scope.mentalScaleDto = {
			"mentalScales" : $scope.evalutions
		}
		MentalScaleService.addMentalScale($scope.mentalScaleDto).then(function(response) {
			$state.go('main.list_MentalScale');
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', ' Data Added Successfully');
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
	
	// for select mental cluter
	$scope.getAllMentalCluster = function() {
		MentalClusterService.getAllMentalCluster().then(function(response) {
			$scope.mentalClusterList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.getAllMentalScale = function() {
		MentalScaleService.getAllMentalScale().then(function(response) {
					$scope.mentalScaleList = response.data;
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
	
	
	$scope.updateMentalScale = function(evalution) {
		MentalScaleService.updateMentalScale(evalution).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Updated Successfully');
			$state.go('main.list_MentalScale');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.updateMentalClusterScale = function(mentalCluster,mentalScale) {
		$scope.mentalScaleDto={
				"mentalScales":$scope.mentalScales.mentalScaleCheckList,
				"mentalClusters":mentalCluster
		}
		alert(JSON.stringify($scope.mentalScaleDto));
		MentalScaleService.updateMentalClusterScale($scope.mentalScaleDto).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Updated Successfully');
			$state.go('main.list_MentalScale');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	
	
	$scope.deletementalScale = function(id) {
		MentalScaleService.deletementalScale(id).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',' Data Deleted Successfully');
			$scope.getAllMentalScale();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	
	$scope.gotoList = function() {
		$state.go('main.list_MentalScale');
	};
	$scope.gotoAdd = function() {
		$state.go('main.MentalScale');
	};
	$scope.gotoback = function() {
		$state.go('main.MentalScale');
	};
	$scope.gotoupdate = function(mentalScale) {
		$state.go('main.updateMentalScale', {
			mentalScaleObj : mentalScale,
		});
	};
	var counter = 0;
	$scope.evalutions = [ {
		itemDescripation : '',
		percent50:'',
		percent3:'',
		percent97:'',
		percent97Rank:''
	} ];

	$scope.newItem = function() {
		counter++;
		$scope.evalutions.push({
			itemDescripation : '',
			percent50:'',
			percent3:'',
			percent97:'',
			percent97Rank:''
		});
	}
	$scope.deleteItem = function() {
		counter--;
		$scope.evalutions.pop({
			itemDescripation : '',
			percent50:'',
			percent3:'',
			percent97:'',
			percent97Rank:''
		});

	}}
angular.module("HealthApplication")
		.controller("MentalScaleCtrl", MentalScaleCtrl);
