function patientService(PATIENT_MODULE_CONFIG, MODULE_CONFIG, $http) {
	this.addPatient = function(patient) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_PATIENT(), patient);
	};
	this.findAllPatient = function(page, size) {
		return $http.get(PATIENT_MODULE_CONFIG.FIND_ALL_PATIENT(page, size));
	};
	
	this.findAllPatientSearchByRole = function(adminUserName,search,page, size) {
		return $http.get(PATIENT_MODULE_CONFIG.PATIENT_SEARCH_BY_ROLE(adminUserName,search,page, size));
	};
	
	this.getAllPatients = function(adminUserName) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_PATIENTS(adminUserName));
	};
	this.getAllPatientsByCompanyAndDeptId = function(adminUserName,deptId) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_PATIENTS_BY_COMPANY_AND_DEPTID(adminUserName,deptId));
	};
	this.getAllDocuments = function(patientId,) {
		return $http.get(PATIENT_MODULE_CONFIG
				.GET_ALL_PATIENT_DOCUMENTS(patientId));
	};
	this.getAllDocumentsByStatus = function(patientId,status) {
		return $http.get(PATIENT_MODULE_CONFIG
				.GET_ALL_PATIENT_DOCUMENTSBY_STATUS(patientId,status));
	};
	this.getPatientDocument = function(path) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_PATIENT_DOCUMENT(path));
	};
	this.updatePatient = function(patientObject) {
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_PATIENT(),patientObject);
	};
	this.totalPatientsRegistered = function(adminUserName) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_TOTAL_PATIENTS_REGISTERD(adminUserName));
	};
	this.inActivePatients = function(adminUserName) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_INACTIVE_PATIENTS(adminUserName));
	};
	this.patientGoalsInformation = function(id) {
		return $http.get(PATIENT_MODULE_CONFIG.PATIENT_GOALS_INFORMATION(id));
	};
	this.generateProgressReport = function(progressSheetDateDto) {
		return $http.post(PATIENT_MODULE_CONFIG.GENERATE_PROGRESS_REPORT(),progressSheetDateDto,{
            responseType : 'arraybuffer'
        });
	};
	this.findAllpatientsByPaginationByRoleByInactive = function(adminUsername, page, size) {
		return $http.get(PATIENT_MODULE_CONFIG.FIND_ALL_PATIENTS_BY_ROLE_INACTIVE(
				adminUsername, page, size));
	};
	this.findAllpatientsByPaginationByRoleAndTodayDate = function(adminUsername, page, size) {
		return $http.get(PATIENT_MODULE_CONFIG.FIND_ALL_PATIENTS_BY_ROLE_LIST_OF_TODAY_DATES(
				adminUsername, page, size));
	};
	
	
	this.generateEvaluationReport = function(id,doctorObj) {
		return $http.get(PATIENT_MODULE_CONFIG.GENERATE_EVALUATION_REPORT(id),{
            responseType : 'arraybuffer'

        });
	};
	
	this.generateExitNoteReport = function(id) {
		return $http.get(PATIENT_MODULE_CONFIG.GENERATE_EXIT_NOTE_REPORT(id),{
            responseType : 'arraybuffer'

        });
	};


	this.uploadDocument = function(xlsfile) {
		var fd = new FormData();
		fd.append('file', xlsfile);
		return $http({
			method : 'POST',
			url : PATIENT_MODULE_CONFIG.UPLOAD_DOCUMENT(),
			data : fd,
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).success(function(data, status, headers, config) {
			return data;
		}).error(function(data, status, headers, config) {
			return data;

		});
	};

	this.findAllpatientsByPaginationByRole = function(adminUsername,search, page, size) {
		return $http.get(PATIENT_MODULE_CONFIG.FIND_ALL_PATIENTS_BY_ROLE(
				adminUsername,search, page, size));
	};
	this.getNewPatientsCount = function(adminUserName) {
		return $http.get(PATIENT_MODULE_CONFIG.GET_NEW_PATIENT_COUNT(adminUserName));
	};
	
	this.findDoctorByPatientId=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.FIND_DOCTOR_BY_PATIENTID(id));
	};

	this.deleteDocumnet=function(documentId){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_DOCUMENT_BY_ID(documentId));
	};
	
	this.getAllPatientsByRole=function(username){
		return  $http.get(PATIENT_MODULE_CONFIG.GET_ALL_PATIENTS_BY_ROLE(username));
	};
	
	this.assignGoalstoPatient=function(listOfSelectedGoals,patientId){
		return  $http.post(MODULE_CONFIG.ASSIGN_GOALS_TO_PATIENT(patientId),listOfSelectedGoals);
	};
	this.addPayment = function(paymentObject) {
		return $http.post(PATIENT_MODULE_CONFIG.ADD_PAYMENT(),paymentObject);
	};
	this.searchPatient = function(patient) {
		return $http.post(PATIENT_MODULE_CONFIG.SEARCHPATIENT(),patient);
	};
	this.getCycles = function(id) {
		return $http.get(PATIENT_MODULE_CONFIG.GETCYCLES(id));
	};
	this.getSessions = function(id) {
		return $http.get(PATIENT_MODULE_CONFIG.GETSESSIONS(id));
	};
	this.getPaymentsByRole=function(email,page,size){
		return $http.get(PATIENT_MODULE_CONFIG.GETPAYMENTSBYROLE(email,page,size));
	};
	this.getTotalPaymentsDashBoard=function(name){
		return $http.get(PATIENT_MODULE_CONFIG.GET_TOTAL_PAYMENTS_DASHBOARD(name));
	};
	
	this.getPatientAppointmentsDashBoards=function(name){
		return $http.get(PATIENT_MODULE_CONFIG.GET_PATIENT_APPOINTMENTS_DASHBOARDS(name));
	};
	this.getMonthlyPaymentsDashBoard=function(name){
		return $http.get(PATIENT_MODULE_CONFIG.GET_MONTHLY_PAYMENTS_DASHBOARD(name));
	};
	this.getPaymentsByPatient = function(adminUserName) {
		return $http.get(PATIENT_MODULE_CONFIG.GETPAYMENTSBYPATIENT(adminUserName));
	};
	
	this.generateInvoice = function() {
		return $http.get(PATIENT_MODULE_CONFIG.GENERATE_INVOICE(),{
            responseType : 'arraybuffer'

        });
	};
	this.createFolder=function(folder){
		return $http.post(PATIENT_MODULE_CONFIG.CREATEFOLDER(),folder)
	};
	
	this.calamount=function(calAmtObj){
		return $http.post(PATIENT_MODULE_CONFIG.CALAMOUNT(),calAmtObj)
	};
	
	this.getAllSpeechTheraphyTemplatesByPatientInEvalution=function(patientid,status){
		return $http.get(PATIENT_MODULE_CONFIG.FIND_ALL_PATIENT_SPEECHTEMPLATES_BY_PATIENT_IN_EVALUTION(patientid,status));
	};
	
	this.getAllSpeechTheraphyTemplatesByPatientInProgress=function(patientid,tabdate){
		return $http.get(PATIENT_MODULE_CONFIG.FIND_ALL_PATIENT_SPEECHTEMPLATES_BY_PATIENT_IN_PROGRESS(patientid,tabdate));
	};
	
	
	// patient folders
	
	this.getAllFoldersbyPatient=function(patientid){
		return $http.get(PATIENT_MODULE_CONFIG.FIND_ALL_PATIENT_FOLDERS(patientid));
	};
	
	this.getAllDocumentLookupFolders=function(){
		return $http.get(PATIENT_MODULE_CONFIG.FIND_ALL_DOCUMENTLOOKUP_FOLDERS());
	};
	
	this.createFolderForPatient=function(folder){
		return $http.post(PATIENT_MODULE_CONFIG.CRAETE_FOLDER_FOR_PATIENT(),folder);
	};

	this.getAllDocumentsByPatientAndFolderId=function(patientId,folderid){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_DOCUMENTSBY_PATIENTANDFOLDER_WISE(patientId,folderid));
	};
	
	this.saveInvoice = function(invoice) {
		return $http.post(PATIENT_MODULE_CONFIG.SAVE_INVOICE(),invoice);
	};
	this.getInvoice = function(invoice) {
		return $http.post(PATIENT_MODULE_CONFIG.GET_INVOICE(),invoice);
	};
	this.deleteItem=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_ITEM(id));
	}
	this.getPaidAmount=function(amtDto){
		return $http.post(PATIENT_MODULE_CONFIG.GET_PAID_AMOUNT(),amtDto);
	};
	this.addRegionalCenter=function(region){
		return $http.post(PATIENT_MODULE_CONFIG.ADD_REGIONALCENTER(),region);
	};
	this.getAllRegionalCentersByAdminPage=function(email,page,size){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_REGIONALCENTERS_BY_ADMIN_PAGE(email,page,size));
	};
	this.updateRegionalCenter=function(region){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_REGIONALCENTER(),region);
	};
	this.deleteRegionalCenter=function(id){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_REGIONALCENTER(id));
	};
	this.getAllItems=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GETALLITEMS());
	};
	this.getTotalAmount=function(invoiceDto){
		return $http.post(PATIENT_MODULE_CONFIG.GET_TOTALAMOUNT(),invoiceDto);
	};
	this.deletePatintSpeechTheraphyTemplateByPatintAndStausInEvalution=function(templateId,patientId,status){
		return $http.delete(PATIENT_MODULE_CONFIG.DELETE_PATIENTSPEECHTHERAPHY_TEMPLATE_BY_PATIENT_AND_STATUS(templateId,patientId,status));
	};
	this.downloadInvoice=function(invoiceDto){
		return $http.post(PATIENT_MODULE_CONFIG.DOWNLOAD_INVOICE(),invoiceDto,{
			 responseType : 'arraybuffer'
        });
	};
	this.getSinglepayment=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_SINGLE_PAYMENT(id));	
	};
	this.getInvoiceItems=function(invoiceDto){
		return $http.post(PATIENT_MODULE_CONFIG.GET_INVOICE_ITEMS(),invoiceDto);	
	};
	this.updatePayment=function(payment){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_PAYMENT(),payment);
	};
	this.addDocument=function(patientInfo){
		return $http.post(PATIENT_MODULE_CONFIG.ADD_DOCUMENT(),patientInfo)
	};
	this.updateFolderName=function(folder){
		return $http.post(PATIENT_MODULE_CONFIG.UPDATE_FOLDER_NAME(),folder)
	};
	this.getRegionalCenters=function(id,loggedUsername){
		return $http.get(PATIENT_MODULE_CONFIG.GET_REGIONAL_CENTERS(id,loggedUsername));	
	};
	this.getschoolClientTypes=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_SCHOOL_CLIENT_TYPES());	
	};
	this.getPrivateClienTypes=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_PRIVATE_CLIENT_TYPES());	
	};
	this.getInsurenceClientTypes=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_INSURENCE_TYPES());	
	};
	this.getRegionalCenterZones=function(){
		return $http.get(PATIENT_MODULE_CONFIG.GET_REGIONALCENTER_ZONES());	
	};
	this.getStripePayments=function(username){
		// alert(username)
		return 	$http.get(PATIENT_MODULE_CONFIG.GET_STRIPE_PAYMENTS(username));
	};
	// get access of s3 file
	this.findFilePathinS3Bucket=function(documentId){
		return $http.get(PATIENT_MODULE_CONFIG.GET_FILEPATH_FROM_S3(documentId));	
	};
	
	this.displayDocument = function(documentId) {
		return $http.get(PATIENT_MODULE_CONFIG.DISPLAY_PDF(documentId))
	};
	this.findAllPatientsByRoleForBillingsPage = function(billingsDto,page, size) {
		return $http.post(PATIENT_MODULE_CONFIG.FIND_ALL_PATIENTS_BY_ROLE_FOR_BILLINGS_PAGE(page, size),billingsDto)
	};
	this.getAllPatientMentalScalesByPatientId=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_PATIENTMENTALSCALES_BYPATIENT_ID(id));
	};
	
	this.updatePatientMentalScalesByPatientId=function(patientMental,id){
		return $http.put(PATIENT_MODULE_CONFIG.UPDATE_PATIENT_MENTALSCALES(id),patientMental);
	};
	
	this.getPatientMentalScalesAgeAndClusterCount=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_PATIENT_MENTALSCALES_AGE_CLUSTERS(id));
	};
	
	this.getPatientMentalScalesAgeAndClusterCountForGraph=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_PATIENT_MENTALSCALES_AGE_CLUSTERS_FOR_GRAPH(id));
	};
	this.getAllPatientMotorScalesByPatientId=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_PATIENT_MOTOR_SCALES_BYPATIENT_ID(id));
	};
	this.getAllMotorScalesCountBypatientId=function(patientId){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_MOTOR_SCALES_COUNT(patientId));
	};
	
	this.getAllMotorScalesCountBypatientIdForGraph=function(patientId){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_MOTOR_SCALES_COUNT_FOR_GRAPH(patientId));
	};
	
	this.updatePatientMotorScalesByPatientId=function(patientMotor,id){
		return $http.post(PATIENT_MODULE_CONFIG.UPDATE_PATIENT_MOTOR_SCALES(id),patientMotor);
	};
	
	this.addMchatTemplateToPatient=function(patient){
		return $http.post(PATIENT_MODULE_CONFIG.ADD_MCHATTEMPLATE_TO_PATIENT(),patient);
	};
	this.updateMchartTemplateToPatient=function(patientMchart,id){
		return $http.post(PATIENT_MODULE_CONFIG.UPDATE_PATIENT_MCHART_QUESTIONS(id),patientMchart);
	};
	this.getPatientObj=function(patientOb){
		return $http.post(PATIENT_MODULE_CONFIG.GET_PATIENT_OBJECT(),patientOb);
	};
	
	this.getAllIsaaBeaviourTemplatesByPatientId=function(patientId){
		return $http.get(PATIENT_MODULE_CONFIG.GET_ALL_ISAABEHAVIOUR_TEMPLATES_BY_PATIENTID(patientId));
	};
	this.searchPatientBySSN=function(ssn){
		return $http.get(PATIENT_MODULE_CONFIG.GET_PATIENT_BY_SSN(ssn));
	};
	
	this.searchPatientById=function(id){
		return $http.get(PATIENT_MODULE_CONFIG.GET_PATIENT_BY_ID(id));
	};
	this.searchPatientByRegNo=function(regNo){
		return $http.get(PATIENT_MODULE_CONFIG.GET_PATIENT_BY_REGNO(regNo));
	};
	this.getCategoryByRegistartiontype=function(pId){
		return $http.get(PATIENT_MODULE_CONFIG.GET_CAT_BY_REGTYPE(pId));
	}
}
angular.module('HealthApplication').service('patientService', patientService);