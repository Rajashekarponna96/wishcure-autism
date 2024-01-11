function NichqCategoryLookupCtrl($scope, $stateParams, $state, NichqCategoryLookupService,successMessageHandler){
	
	if ($stateParams.nichqParentCategoryLookupObject != null) {
		$scope.nichqparentcategorylookup1 = $stateParams.nichqParentCategoryLookupObject;
	}

	
	$scope.addNichqCategoryLookup = function(nichq){
		NichqCategoryLookupService.addNichqCategoryLookup(nichq).then(function(response){
			toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    positionClass : "toast-top-full-width",
                    timeOut: 2000
                };
                toastr.success('',	successMessageHandler.NICHQ_CATEGORY_ADDED);
                $scope.nichq={};
                $scope.getAllNichqCategoryQuestions();
		}, function(error){
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	}
		$scope.getAllNichqCategoryQuestions = function(){
			NichqCategoryLookupService.getAllNichqCategoryLookupQuestions().then(function(response){
				$scope.nichqParentCategoryQuestionList = response.data;
			}, function(error){
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		}
		
		
		
		
		
		$scope.deleteNichqCategoryLookup  = function(id){
			NichqCategoryLookupService.deleteNichqCategoryLookup(id).then(function(response){
				toastr.options = {
	                    closeButton: true,
	                    progressBar: true,
	                    showMethod: 'slideDown',
	                    positionClass : "toast-top-full-width",
	                    timeOut: 2000
	                };
	                toastr.success('',successMessageHandler.NICHQ_CATEGORY_DELETED);
				$scope.getAllNichqCategoryQuestions();
			}, function(error){
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		}
		
		
		$scope.updateNichqparentcategorylookup = function(nichqparentcategory) {
			NichqCategoryLookupService.updateNichqCategoryLookup(nichqparentcategory).then(function(response) {
				toastr.options = {
	                    closeButton: true,
	                    progressBar: true,
	                    showMethod: 'slideDown',
	                    positionClass : "toast-top-full-width",
	                    timeOut: 2000
	                };
	                toastr.success('', successMessageHandler.NICHQ_CATEGORY_UPDATED);
	                $state.go('main.addNichqParentCategoryLookup');
			
				
				
			}, function(error){
				$scope.message = JSON.stringify(error.data.message.trim());
			});
		}
	
	
	$scope.gotoAdd = function() {
		$state.go('main.addNichqParentCategoryLookup');
	};
	$scope.gotoback = function() {
		$state.go('main.addNichqParentCategoryLookup');
	};
	
	
	$scope.gotoupdate = function(updateNichq) {
		$state.go('main.updateNichqCategoryLookup',{
			nichqParentCategoryLookupObject: updateNichq
		});
	};
	/*$scope.gotoupdate = function(updateNichq) {
		alert("gotoupdate called");
		$state.go('main.updateNichqCategoryLookup',{
			nichqParentCategoryLookupObject : updateNichq
		});
	};*/
}

angular.module('HealthApplication').controller('NichqCategoryLookupCtrl', NichqCategoryLookupCtrl);