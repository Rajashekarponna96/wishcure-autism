function clientTypeService(MODULE_CONFIG, $http) {
	this.addClientType = function(client) {
	return 	$http.post(MODULE_CONFIG.ADD_CLIENTTYPE(),client);
	};
	this.getAllClients=function(){
		return $http.get(MODULE_CONFIG.GETALLCLIENTS());
	};
	this.getAllClientsList=function(page,size){
		return $http.get(MODULE_CONFIG.GETALLCLIENTSLIST(page,size));
	};
	this.updateClientType=function(client){
		return $http.put(MODULE_CONFIG.UPDATECLIENT(),client);
	};
	this.deleteClientType=function(id){
		return $http.delete(MODULE_CONFIG.DELETECLIENTTYPE(id));
	};
	
	
	
}

angular.module('HealthApplication').service('clientTypeService', clientTypeService);