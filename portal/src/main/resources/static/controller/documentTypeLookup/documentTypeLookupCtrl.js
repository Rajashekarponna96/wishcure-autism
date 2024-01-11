function documentTypeLookupCtrl($scope, $state, $stateParams, documentTypeLookupService) {
	$scope.page = 0;
	$scope.size = 5;
	$scope.document = $stateParams.documentObj;
	$scope.addDocumentType = function(document) {
		documentTypeLookupService.addDocumentType(document).then(function(response) {
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 3000
				};
				toastr.success('', "User registered successfully!");
			$state.go('main.documentTypeLookupList');
		}, function(error) {
		});
	};
	
	$scope.getAllDocumentTypes = function() {
		documentTypeLookupService.getAllDocumentTypes($scope.page,$scope.size).then(
				function(response) {
					$scope.documentTypeList = response.data.content;
					$scope.totalElements = response.data.totalElements;
					$scope.totalPages = response.data.totalPages-1;
					$scope.noOfPgaes(response.data.totalPages);
				}, function(error) {
				});
	};
	
	$scope.updateDocumentType = function(document) {
		documentTypeLookupService.updateDocumentType(document).then(function(response) {
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 3000
				};
				toastr.success('', "User updated successfully!");
			$state.go('main.documentTypeLookupList');
		}, function(error) {
		});
	};
	
	$scope.deleteDocumentType = function(id) {
		documentTypeLookupService.deleteDocumentType(id).then(function(response) {
			toastr.options = {
					closeButton : true,
					progressBar : true,
					showMethod : 'slideDown',
					positionClass : "toast-top-full-width",
					timeOut : 3000
				};
				toastr.success('', successMessageHandler.USERMANAGEMENT_DELETE_USER_SUCCESS);
			$state.go('main.documentTypeLookupList');
			$scope.getAllDocumentTypes();
		}, function(error) {
		});
	};
	
	$scope.deletealert=function(id){
		swal({
			  title: "Are you sure?",
			  text: "Once deleted, you will not be able to recover this!",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  documentTypeLookupService.deleteDocumentType(id).then(function(response) {
						 swal("Poof! Your Document Type has been deleted!", {
						      icon: "success",
						    });
						 $scope.getAllDocumentTypes();
					}, function(error) {
						$scope.message = JSON.stringify(error.data.message.trim());
						 swal($scope.message , {
						      icon: "info",
						    });
					});
			    swal("Poof! Your Document Type has been deleted!", {
			      icon: "success",
			    });
			   
			  } else {
			    swal("Your Document Type is safe!");
			  }
			});
	};
	
	$scope.gotoList = function() {

		$state.go('main.documentTypeLookupList');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addDocumentTypeLookup');
	};
	$scope.gotoback = function() {
		$state.go('main.documentTypeLookupList');
	};
	$scope.gotoupdate = function(document) {
		$state.go('main.updateDocumentTypeLookup', {
			documentObj : document
		});
	};
// //////
	// Pagination logic

	$scope.noOfPgaes=function(totalpages){
		
		$scope.pageList=[];
		for(i=0;i<totalpages;i++){
			$scope.pageList.push(i);
		}
		
	   };
	
	   $scope.pageChanged=function(page){
		$scope.page=page;
	   };
	   
	   $scope.sizeChanged=function(size){
			$scope.size=size;
		};
		$scope.firstPage = function() {
			$scope.page = 0;
		};
		
		$scope.lastPage = function() {
			$scope.page = $scope.totalPages;
		};

		$scope.previousPage = function() {
			if ($scope.page >0) {
				$scope.page = $scope.page - 1;
			}
		};
		$scope.nextPage = function() {
			if ($scope.page < $scope.totalPages) {
				$scope.page = $scope.page + 1;
			}
		};
		$scope.$watchGroup(['size','page'],
				function(oldSize, oldPage) {
			$scope.getAllDocumentTypes();
				});
}
angular.module("HealthApplication").controller("documentTypeLookupCtrl",
		documentTypeLookupCtrl);
