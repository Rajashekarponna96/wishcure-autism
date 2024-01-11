/*angular
		.module('HealthApplication')
		.config(
				function($httpProvider) {
					
					$httpProvider.interceptors
							.push(function($q, $injector, $localStorage,
									$rootScope, $location) {

								return {
									'request' : function(request) {
										request.headers.Authorization = $localStorage.authorization;
										return request;
									},

									'response' : function(response) {
										return response;
									},
									// This is the responseError interceptor
									'responseError' : function(rejection) {
										return $q.reject(rejection);
									}
								};
							});
				});*/