function clinicExpenseTypeService(PATIENT_MODULE_CONFIG, $http) {
	this.addClinicExpenseType = function(clinicExpenseType) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_CLINIC_EXPENSE_TYPE(),
				clinicExpenseType);
	};
	this.getAllExpenseTypeListByCompanyUserName = function(email) {
		return $http.get(PATIENT_MODULE_CONFIG
				.GET_ALL_EXPENSE_TYPE_LIST_BY_COMPANY_USER_NAME(email));
	};
	this.addMonthlyClinicExpense = function(monthlyClinicExpense) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_MONTHLY_CLINIC_EXPENSE(),
				monthlyClinicExpense);
	};
	
	this.getAllExpenseMonthWiseByLoggedInUserName = function(email) {
		return $http.get(PATIENT_MODULE_CONFIG
				.GET_ALL_EXPENSE_MONTH_WISE_BY_LOGGEDIN_USER_NAME(email));
	};
	this.addClinicExpenses = function(monthlyExpense) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_CLINIC_EXPENSE(),
				monthlyExpense);
	};
	
	this.getAllExpensesCompnywise = function(email) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_EXPENSES_BY_COMPANY_WISE(email));
	};
	/*
	 * this.getAllMchartsByPatientId=function(id){ return
	 * $http.get(PATIENT_MODULE_CONFIG.GET_ALL_MCHART_BY_ID(id)); };
	 * this.getMchartCountResult=function(id){ return
	 * $http.get(PATIENT_MODULE_CONFIG.GET_ALL_MCHART_COUNT_BY_PATIENT_ID(id)); };
	 * 
	 * getAllMchartQuestionsByPatient=function(patientId){ return
	 * $http.get(PATIENT_MODULE_CONFIG.GET_ALL_MCHART_BY_PATIENT_ID(id)); };
	 */
}

angular.module('HealthApplication').service('clinicExpenseTypeService',
		clinicExpenseTypeService);