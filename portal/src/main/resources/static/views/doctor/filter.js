var app = angular.module('myApp', []);

app.controller('MyCtrl', [ '$scope', '$filter', function($scope, $filter) {
	$scope.currentPage = 0;
	$scope.pageSize = 10;
	$scope.data = [];
	$scope.q = '';

	$scope.getData = function() {
		return $filter('filter')($scope.data, $scope.q)
	}

	$scope.numberOfPages = function() {
		return Math.ceil($scope.getData().length / $scope.pageSize);
	}

	for (var i = 0; i < 65; i++) {
		$scope.data.push("Item " + i);
	}

	$scope.$watch('q', function(newValue, oldValue) {
		if (oldValue != newValue) {
			$scope.currentPage = 0;
		}
	}, true);
} ]);

app.filter('startFrom', function() {
	return function(input, start) {
		start = +start;
		return input.slice(start);
	}
});
