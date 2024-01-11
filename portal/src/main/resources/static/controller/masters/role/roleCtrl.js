function roleCtrl($scope, $state, roleService, permissionService,
		categoryService, $rootScope, $stateParams,successMessageHandler) {

	$scope.viewRoleObj = $stateParams.viewRoleObj;

	$scope.roleType = $stateParams.roleObj;

	$scope.updateRoleFeature = $stateParams.updateRoleFeatureObj;

	$scope.addRoleType = function(role) {
		roleService.addRoleType(role).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.ROLE_ADD_DATA_SUCCESS);
			$state.go('main.roleist');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getAllRolesBasedOnStatus = function() {
		roleService.getAllRolesBasedOnStatus().then(function(response) {
			$scope.rolesList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getAllRoles = function() {
		roleService.getAllRoles().then(function(response) {
			$scope.allrolesList = response.data;
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};

	$scope.getAllPermissions = function() {
		categoryService.getAllCategories().then(function(response) {
			$scope.categoriesList = response.data;
			$scope.role.permissions = response.data;
		}, function(error) {

		})
	};

	$scope.updateRoleType = function(role) {
		roleService.updateRoleType(role).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.ROLE_UPDATE_DATA_SUCCESS);
			$state.go('main.roleist');
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.deleteRoleType = function(id) {
		roleService.deleteRoleType(id).then(function(response) {
			toastr.options = {
				closeButton : true,
				progressBar : true,
				showMethod : 'slideDown',
				positionClass : "toast-top-full-width",
				timeOut : 2000
			};
			toastr.success('', successMessageHandler.ROLE_DELETE_DATA_SUCCESS);
			$scope.getAllRolesBasedOnStatus();
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		});
	};
	$scope.gotoList = function() {
		$state.go('main.roleist');
	};
	$scope.gotoAdd = function() {
		$state.go('main.addRole');
	};
	$scope.gotoback = function() {
		$state.go('main.roleist');
	};
	$scope.gotoupdate = function(role) {
		$state.go('main.updateRole', {
			roleObj : role
		});
	};

	$scope.gotoRoleFeature = function() {
		$state.go('main.roleFeature');
	};

	$scope.gotoUpdateRoleFeature = function(role) {
		$state.go('main.updateRoleFeature', {
			updateRoleFeatureObj : role
		});
	};

	$scope.gotoviewRoleFeature = function(role) {
		$state.go('main.viewRoleFeature', {
			viewRoleObj : role
		});
	};
	$scope.gotoAddRoleFeature = function() {
		$state.go('main.addRoleFeature');
	};

	$scope.gotoRoleFeatureList = function() {
		$state.go('main.roleFeature');
	};

	// permission ctrl logic

	$scope.permissionsList = [];

	$scope.getAllCategories = function() {
		categoryService.getAllCategories().then(function(response) {
			$scope.categoryList = response.data;
			angular.forEach($scope.categoryList, function(category) {
				$scope.permissionsList.push({
					"category" : category,
					"isApproved" : false,
					"canView" : false,
					"canDelete" : false,
					"canUpdate" : false,
					"canAdd" : false
				});

				// $scope.role.permissions.push($scope.permissionsList);
			})
		}, function(error) {
			$scope.message = JSON.stringify(error.data.message.trim());
		})
	};

	$scope.assignFeatureToRole = function() {
		$scope.saveRole = {
			"id" : $scope.role.id,
			"permissions" : $scope.permissionsList,
			"roleName" : $scope.role.roleName,
			"roleDescription" : $scope.role.roleDescription,
			"displayName" : $scope.role.displayName,
			"roleStatus" : $scope.role.roleStatus,
			"status" : $scope.role.status
		};
		roleService.assignFeatureToRole($scope.saveRole).then(
				function(response) {
					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						positionClass : "toast-top-full-width",
						timeOut : 2000
					};
					toastr.success('', successMessageHandler.ROLE_ASSIGN_FEATURES_SUCCESS);
					$state.go('main.roleFeature');

				}, function(error) {

					toastr.options = {
						closeButton : true,
						progressBar : true,
						showMethod : 'slideDown',
						timeOut : 2000
					};
					toastr.error('Admin','Features Already Assigned to Role');

				})
	};

	$scope.updateRoleFeatureMethod = function() {
		$scope.updateRoleFeatureObject = {
			"id" : $scope.updateRoleFeature.id,
			"roleName" : $scope.updateRoleFeature.roleName,
			"roleDescription" : $scope.updateRoleFeature.roleDescription,
			"displayName" : $scope.updateRoleFeature.displayName,
			"roleStatus" : $scope.updateRoleFeature.roleStatus,
			"status" : $scope.updateRoleFeature.status,
			"permissions" : $scope.updatePermissionsList

		}

		roleService.updateRoleFeature($scope.updateRoleFeatureObject).then(
				function(response) {
					$state.go('main.roleFeature');
				}, function(error) {

				})
	};

	$scope.getAllPermissions = function() {
		permissionService.getAllPermissions().then(function(response) {
			$scope.permissionsList = response.data;
		}, function(error) {

		})
	};

	$scope.getAllPermissionsByRoleId = function() {
		permissionService.getAllPermissionsByRoleId($scope.viewRoleObj.id)
				.then(function(response) {
					$scope.permissionList = response.data;
				}, function(error) {

				})
	};
	$scope.getAllPermissionsByRoleIdInUpdateRole = function() {
		permissionService
				.getAllPermissionsByRoleId($scope.updateRoleFeature.id).then(
						function(response) {
							$scope.updatePermissionsList = response.data;
						}, function(error) {

						})
	};

}
angular.module("HealthApplication").controller("roleCtrl", roleCtrl);
