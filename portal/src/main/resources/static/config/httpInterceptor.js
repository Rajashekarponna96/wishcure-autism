myApp.config(function($httpProvider) {
	$httpProvider.interceptors.push(function($q, $injector,
			$rootScope) {
		var numLoadings = 0;

		return {
			'request' : function(request) {
				numLoadings++;
				$rootScope.$broadcast("loader_show");
				return request;
			},

			'response' : function(response) {
				if ((--numLoadings) === 0) {
					// Hide loader
					$rootScope.$broadcast("loader_hide");
				}
				return response;
			},
			// This is the responseError interceptor
			'responseError' : function(rejection) {
				if (!(--numLoadings)) {
					// Hide loader
					$rootScope.$broadcast("loader_hide");
				}
				
				return $q.reject(rejection);
			}
		};
	});
});