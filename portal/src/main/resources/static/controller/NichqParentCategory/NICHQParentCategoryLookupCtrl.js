function nichqparentCtrl($scope,$state,$stateParams,nichqservice){
	$scope.nichqparent={};
	$scope.nichqparentlist= [];
	if ($stateParams.nichqobject != undefined) {
		$scope.nichqparent1= $stateParams.nichqobject;
	}
	
$scope.addNichqparent= function() {
	nichqservice.addNichqParent($scope.nichqparent).then(
			function(response) {
				$scope.nichqparent={};
				$scope.getNichqparent();
			}, function(error){
				
			})
		};
$scope.getNichqparent=function(){
	nichqservice.getNichqparent().then(
			function(response){
				$scope.nichqparentlist = response.data;
			}, function(error){
			})
};
$scope.deleteNichqparent= function(nichqparentid) {
	nichqservice.deleteNichqparent(nichqparentid).then(
			function(response) {
				$scope.getNichqparent();	
			}, function(error){
				
			})
		};
		$scope.updateNichqparent= function(nichqobject) {
			nichqservice.updateNichqparent(nichqobject).then(
					function(response) {
						$state.go('main.NICHQParentCategoryLookup');
						
					}, function(error){
						
					})
				};		
		
				$scope.gotoadd = function(nichqparent) {
					$state.go('main.NICHQParentCategoryLookup',{
						nichqobject:nichqparent});
				};	
				
				
				$scope.gotoupdate = function(nichqparent) {
			$state.go('main.updatenichqparent',{
				nichqobject:nichqparent});
		};
}
angular.module("HealthApplication").controller("nichqCtrl",
		 nichqparentCtrl);