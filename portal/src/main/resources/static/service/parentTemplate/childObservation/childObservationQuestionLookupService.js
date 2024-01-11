function childObservationQuestionLookupService(PATIENT_MODULE_CONFIG, MODULE_CONFIG, $http) {
	this.addChildObservationQuestionLookup = function(ObservationQuestion) {
	return 	$http.post(PATIENT_MODULE_CONFIG.ADDCHILDOBSERVATIONQUESTIONLOOKUP(),ObservationQuestion);
	};
	this.getAllChildObservationQuestionLookups=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GETALLCHILDOBSERVATIONQUESTIONLOOKUPS());
	};
	this.updateChildObservationQuestionLookup=function(screeningTest){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATECHILDOBSERVATIONQUESTIONLOOKUP(),screeningTest);
	};
	this.deleteChildObservationQuestionLookup=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETECHILDOBSERVATIONQUESTIONLOOKUP(id));
	};
	this.getQuestionsByCategoryId=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GETALLQUESTIONSBYCATEGORYID(id));
	};
	this.saveGeneralQuestion=function(list,name){
		return $http.post(PATIENT_MODULE_CONFIG.ADDCHILDOBSERVATIONQUESTION(name),list);
	};
	this.getGeneralQuestionsByPersonAndCatId=function(name,id){
		//alert("name::"+JSON.stringify(name))//n@gmail.com
		//alert("id::"+JSON.stringify(id))//38
		return $http.get(PATIENT_MODULE_CONFIG.GETALLQUESTIONSBYCATEGORYIDANDPESONID(id,name));
	};
	this.getAllChildObservationQuestions=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GETALLCHILDOBSERVATIONQUESTIONS());
	};
	this.updateGeneralQuestion=function(list,name){
		/*alert("updateGeneralQuestion Service")
		alert("List::"+JSON.stringify(list))
		alert("name::"+JSON.stringify(name))*/
		return $http.put(PATIENT_MODULE_CONFIG.UPDATECHILDOBSERVATIONQUESTION(name),list);
	};
	this.getAllAnswersDashBoard=function(name){
		return $http.get(PATIENT_MODULE_CONFIG.CHILDDASHBOARD(name));
	};
}
angular.module('HealthApplication').service('childObservationQuestionLookupService', childObservationQuestionLookupService);

