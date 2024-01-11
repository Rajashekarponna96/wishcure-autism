function nichqTeacherCategoryLoockupCtrl($scope, $state, $stateParams,
		nichqteachercategorylookupservice,successMessageHandler) {

	$scope.nichqteachercategorylookup = {};
	if ($stateParams.updatednichqTeacherCategoryLookup != null) {
		$scope.nichqteachercategorylookup1 = $stateParams.updatednichqTeacherCategoryLookup;
	}

	$scope.addNichqteachercategorylookup = function() {
		nichqteachercategorylookupservice.addNichqteachercategorylookup($scope.nichqteachercategorylookup).then(
				function(response) {
					toastr.options = {
		                    closeButton: true,
		                    progressBar: true,
		                    showMethod: 'slideDown',
		                    positionClass : "toast-top-full-width",
		                    timeOut: 2000
		                };
		                toastr.success('',successMessageHandler.NICHQ_TEACHER_CATEGORY_ADDED);
		                $scope.nichqteachercategorylookup = {};
						$scope.getNichqteachercategorylookup();
				
					
					
				}, function(error){
					$scope.message = JSON.stringify(error.data.message.trim());
				});
					
				
	}
	$scope.getNichqteachercategorylookup = function() {
		nichqteachercategorylookupservice.getNichqteachercategorylookup().then(function(response) {
			
			$scope.nichqteachercategorylookupList = response.data;
		}, function(error) {
		})
	};
	$scope.deleteNichqteachercategorylookup = function(nichqteachercategorylookupid) {
		nichqteachercategorylookupservice.deleteNichqteachercategorylookup(nichqteachercategorylookupid).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.NICHQ_TEACHER_CATEGORY_DELETED);
                $scope.getNichqteachercategorylookup();		
			
			
		}, function(error){
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	}
	$scope.updateNichqteachercategorylookup = function(nichqteachercategorylookup1) {
		nichqteachercategorylookupservice.updateNichqteachercategorylookup(nichqteachercategorylookup1).then(function(response) {
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',successMessageHandler.NICHQ_TEACHER_CATEGORY_UPDATED);
                $state.go('main.addTeacherInfoCategoryLookup');
		
			
			
		}, function(error){
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	}
	
	$scope.gotoupdate = function(nichqTeacherCategoryLookup) {
		$state.go('main.updateTeacherCategoryLookup', {
			updatednichqTeacherCategoryLookup : nichqTeacherCategoryLookup
		});
	};
	$scope.gotoAdd = function() {
		$state.go('main.addTeacherInfoCategoryLookup');
	}
	$scope.gotoback = function() {
		$state.go('main.addTeacherInfoCategoryLookup');
	}
}

angular.module("HealthApplication").controller("nichqTeacherCategoryLoockupCtrl",
		nichqTeacherCategoryLoockupCtrl);