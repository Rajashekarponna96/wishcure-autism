function permissionCtrl($scope, $state, roleService, permissionService,
		$rootScope, $stateParams) {
	$scope.permission = {
		isApproved : undefined,
		canView : undefined,
		canDelete : undefined,
		canUpdate : undefined,
		canAdd : undefined,
		category : {},
		role : {}

	};

	$scope.addPermission = function(permission) {
		permissionService.addPermission(permission).then(function(response) {

		}, function(error) {

		})
	};
	$scope.updatePermission = function(permission) {
		permissionService.updatePermission(permission).then(function(response) {

		}, function(error) {

		})
	};

	$scope.getAllPermissions = function() {
		permissionService.getAllPermissions().then(function(response) {
			$scope.permissionsList = response.data;
		}, function(error) {

		})
	};

	$scope.getAllRolesBasedOnStatus = function() {
		roleService.getAllRolesBasedOnStatus().then(function(response) {
			$scope.rolesList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

}
angular.module("HealthApplication")
		.controller("permissionCtrl", permissionCtrl);
