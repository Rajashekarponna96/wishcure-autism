function PatientModuleConstants() {

	var protocol = "http";

	var host = "localhost";
	var port = 6002;

	/*
	 * var host = "ec2-54-215-243-120.us-west-1.compute.amazonaws.com"; var port =
	 * 8087;
	 */
//	var host = "204.93.196.231";
//	var port = 9002;
	/*
	 * var host = "103.255.146.157"; var port = 6002;
	 * 
	 */

	var url = protocol + "://" + host + ":" + port + "/patientapp";
	return {
		PROTOCOL : protocol,
		HOST : host,
		PORT : port,
		URL : url,
		ADD_PATIENT : function() {
			return url + "/patient/";
		},
		FIND_ALL_PATIENT : function(page, size) {
			return url + "/patient/pagination?page=" + page + "&size=" + size;
		},
		UPDATE_PATIENT : function() {
			return url + "/patient/updatePatient1";
		},
		UPLOADIMAGE : function() {
			return url + "/upload/fileUploadInLocalDirectory";
		},
		UPLOAD_DOCUMENT : function() {
			return url + "/upload/document/";
		},
		VIEW_IMAGE : function(profilePicPath) {
			return url + "/upload/" + profilePicPath;
		},
		GET_PATIENT_DOCUMENT : function(documentName) {
			return url + "/upload/" + documentName;
		},
		GET_ALL_PATIENT_DOCUMENTS : function(patientId) {
			return url + "/patient/allDocumentsbyPatient/" + patientId;
		},
		GET_ALL_PATIENT_DOCUMENTSBY_STATUS : function(patientId, status) {
			return url + "/patient/allDocumentsbyPatientAndStatus/" + patientId
					+ "/" + status;
		},
		GET_NEW_PATIENT_COUNT : function(adminUserName) {
			return url + "/patient/findPatientsByToDayDate/" + adminUserName;
		},
		FIND_ALL_PATIENTS_BY_ROLE : function(adminUsername, search, page, size) {
			return url + "/patient/pagination/" + adminUsername + "/" + search
					+ "?page=" + page + "&size=" + size;
		},
		GET_ALL_PATIENTS : function(adminUserName) {
			return url + "/patient/getAllpatients/" + adminUserName;
		},
		GET_ALL_PATIENTS_BY_COMPANY_AND_DEPTID : function(adminUserName,deptId) {
			return url + "/patient/getAllpatientsByCompanyAndDeptId/" + adminUserName + "/" + deptId;
		},
		ADDEVALUTIONSHEET : function() {
			return url + "/saveEvalutionSheet";
		},
		ADD_PATIENT_EVALUTION_SHEET : function() {
			return url + "/saveEvalutionSheet";
		},
		ADD_PATIENT_PROGRESS_SHEET : function() {
			return url + "/savePatientEvalutionSheet";
		},
		ADD_PATIENT_PROGRESS_NOTE : function() {
			return url + "/saveProgressNote";
		},
		FIND_EVALUTIONSHEET_BY_PATIENTID_AND_STATUS : function(patientid,
				status) {
			return url + "/getEvalutionSheetBy/" + patientid + "/status/"
					+ status;
		},
		GET_EVALUTION_SHEET_BY_PATIENTID_STATUS_INPROGRESSSHEET : function(
				patientid) {
			return url + "/getProgressSheetBy/" + patientid;
		},
		GET_EVALUTION_SHEET_BY_PATIENTID_STATUS_INEXITSHEET : function(
				patientid) {
			return url + "/getExitSheetBy/" + patientid;
		},
		SAVE_PATIENT_GOALS : function(patientid) {
			return url + "/savePatientGoals/" + patientid;
		},
		GET_PATIENT_GOALS_BY_PATIENTID_AND_DATE : function() {
			return url + "/getAllPatientGoals";
		},
		GET_ALL_CREATEDDATES_OF_PATIENT_GOALS_BY_PATIENTID : function(patientid) {
			return url + "/getAllPatientGoalsCreatedDates/" + patientid;
		},
		VIEW_BYTE_IMAGE : function(username) {
			return url + "/upload/viewimage/" + username;
		},
		GET_TOTAL_PATIENTS_REGISTERD : function(adminUserName) {
			return url + "/patient/totalPatientsRegistered/" + adminUserName;
		},
		GET_ALL_INACTIVE_PATIENTS : function(adminUserName) {
			return url + "/patient/getAllInactivePatients/" + adminUserName;
		},
		GENERATE_PROGRESS_REPORT : function() {
			return url + "/report/progress";
		},
		GENERATE_EVALUATION_REPORT : function(id) {
			return url + "/report/evaluation/" + id;
		},
		GENERATE_EXIT_NOTE_REPORT : function(id) {
			return url + "/report/exitNote/" + id;
		},
		PATIENT_GOALS_INFORMATION : function(id) {
			return url + "/getAllPatientGoalsInPresentWeek/" + id;
		},
		GET_ACTIVE_DOCTORS_PER_PRESENT_YEAR : function(adminUserName, year) {
			return url + "/dashboard/ActiveDoctorsPerPresentYear/"
					+ adminUserName + "/" + year;
		},
		GET_ACTIVE_USERS_PER_PRESENT_YEAR : function(adminUserName, year) {
			return url + "/dashboard/ActiveUsersPerPresentYear/"
					+ adminUserName + "/" + year;
		},
		GET_TOTAL_PATIENTS_PER_PRESENT_YEAR : function(adminUserName, year) {
			return url + "/dashboard/TotalPatientsRegisteredPerPresentYear/"
					+ adminUserName + "/" + year;
		},
		GET_TODAY_PATIENTS_DATA : function(adminUserName) {
			return url + "/dashboard/TodayPatients/" + adminUserName;
		},
		FIND_DOCTOR_BY_PATIENTID : function(patientId) {
			return url + "/patient/getDoctorByPatientId/" + patientId;
		},
		DELETE_DOCUMENT_BY_ID : function(documentId) {
			return url + "/patient/deleteDocumentByPatient/" + documentId;
		},
		DISPLAY_PDF : function(documentId) {
			return url + "/upload/pdfS3/" + documentId;
		},
		GET_IMAGE_PATH_DATA : function(ProfilePicimagePath) {
			return url + "/upload/image/" + ProfilePicimagePath;
		},
		GET_USER_IMAGEPATHDATA : function(loggedUsername) {
			return url + "/upload/image/" + loggedUsername;
		},
		GET_ALL_PATIENTS_BY_ROLE : function(username) {
			return url + "/patient/getAllByRole/" + username;
		},
		DELETE_PATIENT_GOALS_BY_ID : function(patientId, goalId) {
			return url + "/deletePatientGoalById/" + patientId + "/" + goalId;
		},
		ADD_PAYMENT : function() {
			return url + "/patient/addPayment/";
		},
		GETCYCLES : function(id) {
			return url + "/patient/getCycles/" + id;
		},
		GETSESSIONS : function(id) {
			return url + "/patient/getSessions/" + id;
		},
		SEARCHPATIENT : function() {
			return url + "/patient/searchPatient/";
		},
		GETPAYMENTSBYROLE : function(adminUserName, page, size) {
			return url + "/patient/paymentsPagination/" + adminUserName
					+ "?page=" + page + "&size=" + size;
		},
		GETPAYMENTSBYPATIENT : function(userName) {
			return url + "/patient/getPayment/" + userName;
		},
		GET_ALL_PATIENT_GOALS_BY_PATIENT_ID : function(patientId) {
			return url + "/getAllPatientGoalsByPatient/" + patientId;
		},
		CHARGE_FOR_TRANSACTION : function() {
			return url + "/payment/charge";
		},
		INVOICE : function() {
			return url + "/patient/invoice";
		},
		CREATE_CUSTOMER : function() {
			return url + "/payment/crateCustomer/token";
		},
		GET_SPEECHTHERAPHY_TEMPLATE_BASEDON_STATUS : function(status) {
			return url + "/speechTheraphyTemplate/findoneByStatus/" + status;
		},
		GETALL_CREATED_DATES_OF_PROGRESSNOTE : function(patientid) {
			return url + "/getProgressSheetDatesBy/" + patientid;
		},
		GETALL_CREATED_DATES_OF_EVALUTION_SHEET : function(patientid) {
			return url + "/getEvalutionSheetDatesBy/" + patientid;
		},
		FIND_PROGRESS_SHEET_BY_PATIENT_IDAND_CREATEDDATE : function() {
			return url + "/getProgressSheetByDate";
		},
		
		FIND_EVALUTION_SHEET_BY_PATIENT_IDAND_CREATEDDATE : function() {
			return url + "/getEvalutionSheetDate";
		},
		
		GENERATE_INVOICE : function() {
			return url + "/report/generateInvoice";
		},
		ASSIGN_SPEECHTHERAPHY_TEMPLATE_TO_PATIENT : function(patientId,
				evalutionStatus) {
			return url + "/speechTheraphyTemplate/assignTemplate/" + patientId
					+ "/evalutionStatus/" + evalutionStatus;
		},
		FIND_ALL_PATIENT_SPEECHTEMPLATES_BY_PATIENT_IN_EVALUTION : function(
				patientId, status) {
			return url + "/speechTheraphyTemplate/findallBy/" + patientId
					+ "/evalution/" + status;
		},
		FIND_ALL_PATIENT_SPEECHTEMPLATES_BY_PATIENT_IN_PROGRESS : function(
				patientId, tabdate) {
			return url + "/speechTheraphyTemplate/findallBy/" + patientId + "/"
					+ tabdate + "/progress";
		},
		CREATEFOLDER : function() {
			return url + "/patient/saveFolder";
		},
		CALAMOUNT : function() {
			return url + "/patient/calculateAmount";
		},
		ADD_SPEECHTHERAPHY_CATEGORY : function() {
			return url + "/speechTemplateCategory/";
		},
		GET_ALL_SPEECHTHERAPHY_CATEGORYS : function() {
			return url + "/speechTemplateCategory/all";
		},
		UPDATE_SPEECHTHERAPHY_CATEGORY : function() {
			return url + "/speechTemplateCategory/update";
		},
		DELETE_SPEECHTHERAPHY_CATEGORY : function(id) {
			return url + "/speechTemplateCategory/delete/" + id;
		},
		ADD_SPEECHTHERAPHY_QUESTION : function() {
			return url + "/speechTemplateQuestion/add";
		},
		GET_ALL_SPEECHTHERAPHY_QUESTION : function() {
			return url + "/speechTemplateQuestion/all";
		},
		UPDATE_SPEECHTHERAPHY_QUESTION : function() {
			return url + "/speechTemplateQuestion/update";
		},
		DELETE_SPEECHTHERAPHY_QUESTION : function(Id) {
			return url + "/speechTemplateQuestion/delete/" + Id;
		},
		ADD_DOCUMENT_TYPE : function() {
			return url + "/documentTypeLookup/add";
		},
		GETALL_DOCUMENT_TYPES : function(page, size) {
			return url + "/documentTypeLookup/all?page=" + page + "&size="
					+ size;
		},
		UPDATE_DOCUMENT_TYPE : function() {
			return url + "/documentTypeLookup/update";
		},
		DELETE_DOCUMENT_TYPE : function(Id) {
			return url + "/documentTypeLookup/delete/" + Id;
		},
		FIND_ALL_PATIENT_FOLDERS : function(patientId) {
			return url + "/folder/all/" + patientId;
		},
		FIND_ALL_DOCUMENTLOOKUP_FOLDERS : function() {
			return url + "/documentTypeLookup/findAll";
		},
		CRAETE_FOLDER_FOR_PATIENT : function() {
			return url + "/folder/";
		},
		GET_ALL_DOCUMENTSBY_PATIENTANDFOLDER_WISE : function(patientId,
				folderid) {
			return url + "/patient/getAllDocumentsBy/" + patientId + "/folder/"
					+ folderid;
		},
		SAVE_INVOICE : function() {
			return url + "/patient/saveInvoice/";
		},
		GET_INVOICE : function() {
			return url + "/patient/getInvoice/";
		},
		DELETE_ITEM : function(id) {
			return url + "/patient/deleteItem/" + id;
		},
		GET_PAID_AMOUNT : function() {
			return url + "/patient/paidAmount/";
		},
		ADD_REGIONALCENTER : function() {
			return url + "/regionalCenter/save";
		},
		GET_ALL_REGIONALCENTERS_BY_ADMIN_PAGE : function(email, page, size) {
			return url + "/regionalCenter/getAll/" + email + "?page=" + page
					+ "&size=" + size;
		},
		UPDATE_REGIONALCENTER : function() {
			return url + "/regionalCenter/update/";
		},
		DELETE_REGIONALCENTER : function(id) {
			return url + "/regionalCenter/delete/" + id;
		},
		ADD_TAX : function() {
			return url + "/addTax/";
		},
		GETALLTAXSLIST : function(email, page, size) {
			return url + "/getAllTaxsByAdminPage/" + email + "?page=" + page
					+ "&size=" + size;
		},
		UPDATETAX : function() {
			return url + "/updateTax/";
		},
		DELETETAX : function(id) {
			return url + "/deleteTax/" + id;
		},
		ADD_CURRENCY : function() {
			return url + "/addCurrency/";
		},
		GET_ALL_CURRENCY_LIST : function(email, page, size) {
			return url + "/getAllCurrencysByAdminPage/" + email + "?page="
					+ page + "&size=" + size;
		},
		GET_ALL_CURRENCYS : function(email) {
			return url + "/getAllCurrencysByAdmin/" + email;
		},
		UPDATE_CURRENCY : function() {
			return url + "/updateCurrency/";
		},
		DELETE_CURRENCY : function(id) {
			return url + "/deleteCurrency/" + id;
		},
		ADD_CLINIC_EXPENSE_TYPE : function() {
			return url + "/saveClinicExpenseType";
		},
		
		GET_ALL_EXPENSE_TYPE_LIST_BY_COMPANY_USER_NAME : function(email){
			return url + "/getAllExpenseTypesByCompanyUserName/" + email;
		},
		
		ADD_MONTHLY_CLINIC_EXPENSE: function() {
			return url + "/saveMonthlyClinicExpense";
		},
		
		GET_ALL_EXPENSE_MONTH_WISE_BY_LOGGEDIN_USER_NAME : function(email){
			return url + "/getAllExpenseMonthWiseByLoggedInUserName/" + email;
		},
		ADD_CLINIC_EXPENSE : function() {
			return url + "/saveClinicExpenses";
		},
		GET_ALL_EXPENSES_BY_COMPANY_WISE : function(email){
			return url + "/getAllExpenseByCompanyWise/" + email;
		},
		
		
		ADD_PAYMETHOD : function() {
			return url + "/addPaymethod/";
		},
		GET_ALL_PAYMETHODS_LIST : function(email, page, size) {
			return url + "/getAllPaymethodsByAdminPage/" + email + "?page="
					+ page + "&size=" + size;
		},
		GETALLPAYMETHODS : function(email) {
			return url + "/getAllPaymethodsByAdmin/" + email;
		},
		UPDATE_PAYMETHOD : function() {
			return url + "/updatePaymethod/";
		},
		DELETE_PAYMETHOD : function(id) {
			return url + "/deletePaymethod/" + id;
		},
		ADD_INSURANCE : function() {
			return url + "/insurance/";
		},
		GET_ALL_INSURANCE_LIST : function(email, page, size) {
			return url + "/insurance/getAll/" + email + "?page=" + page
					+ "&size=" + size;
		},
		UPDATE_INSURANCE : function() {
			return url + "/insurance/update";
		},
		DELETE_INSURANCE : function(id) {
			return url + "/insurance/delete/" + id;
		},
		GETALLITEMS : function() {
			return url + "/patient/getAllItems/";
		},
		GET_TOTALAMOUNT : function() {
			return url + "/patient/getTotalAmount/";
		},
		FIND_ALL_PATIENTS_BY_ROLE_LIST_OF_TODAY_DATES : function(adminUsername,
				page, size) {
			return url + "/patient/findPatientsByToDayDateList/"
					+ adminUsername + "?page=" + page + "&size=" + size;
		},
		FIND_ALL_PATIENTS_BY_ROLE_INACTIVE : function(adminUsername, page, size) {
			return url + "/patient/paginationInActive/" + adminUsername
					+ "?page=" + page + "&size=" + size;
		},
		DELETE_PATIENTSPEECHTHERAPHY_TEMPLATE_BY_PATIENT_AND_STATUS : function(
				templateId, patientId, evalutionStatus) {
			return url + "/speechTheraphyTemplate/deleteBy/" + patientId + "/"
					+ evalutionStatus + "/" + templateId;
		},
		DOWNLOAD_INVOICE : function() {
			return url + "/report/downloadInvoice";
		},
		GET_STRIP_PRODUCTS : function() {
			return url + "/payment/allStripProducts";
		},
		GET_SINGLE_PAYMENT : function(id) {
			return url + "/patient/getSinglepayment/" + id;
		},
		UPDATE_PAYMENT : function() {
			return url + "/patient/updatePayment/";
		},
		PATIENT_SEARCH_BY_ROLE : function(adminUsername, search, page, size) {
			return url + "/patient/getAllpatientsSearch/" + adminUsername + "/"
					+ search + "?page=" + page + "&size=" + size;
		},
		ADD_DOCUMENT : function() {
			return url + "/patient/addDocument";
		},
		GET_ALL_STRIPE_PACKAGES : function() {
			return url + "/stripePackage/all";
		},
		GET_STRIPE_PLAN_BY_PRODUCT_ID : function(productId) {
			return url + "/stripePlan/byProcudtId/" + productId;
		},
		GET_INVOICE_ITEMS : function() {
			return url + "/patient/getInvoiceItems";
		},
		UPDATE_FOLDER_NAME : function() {
			return url + "/patient/updateFolderName";
		},
		GET_REGIONAL_CENTERS : function(id, loggedUsername) {
			return url + "/patient/getRegionalCenters/" + id + "/"
					+ loggedUsername;
		},
		GET_SCHOOL_CLIENT_TYPES : function() {
			return url + "/patient/getschoolClientTypes";
		},
		GET_PRIVATE_CLIENT_TYPES : function() {
			return url + "/patient/getPrivateClienTypes";
		},
		GET_INSURENCE_TYPES : function() {
			return url + "/patient/getInsurenceClientTypes";
		},
		GET_REGIONALCENTER_ZONES : function() {
			return url + "/patient/getRegionalCenterZones";
		},
		CREATE_ACCOUNT_IN_STRIPE : function() {
			return url + "/payment/create-account"
		},
		CREATE_ACH_BANKACCOUNT_DETAILS : function() {
			return url + "/payment/create_Ach_Account";
		},
		VIEW_USER_IMAGEPATHDATA : function(imageFile) {
			return url + "/upload/imageview/" + imageFile;
		},
		GET_FILEPATH_FROM_S3 : function(documentId) {
			return url + "/patient/getfileFromS3/" + documentId;
		},
		GET_STRIPE_PAYMENTS : function(username) {
			return url + "/patient/getAllUSerPaymentsStripe/" + username;
		},
		GET_USER_IMAGEPATHDATA_S3 : function(username) {
			return url + "/upload/imageS3/" + username;
		},
		GET_USER_IMAGEPATHDATA_THERAPIST_S3 : function(username) {
			return url + "/upload/imageFroTherapistS3/" + username;
		},
		ADD_REGIONAL_CENTER_ZONE : function() {
			return url + "/regionalCenterZoneLookup/save";
		},
		GETALL_REGIONAL_CENTER_ZONE : function() {
			return url + "/regionalCenterZoneLookup/getAll";
		},
		GETALL_REGIONAL_CENTER_ZONE_BY_STATUS : function() {
			return url + "/regionalCenterZoneLookup/getAllByStatus"
		},
		UPDATE_REGIONAL_CENTER_ZONE : function() {
			return url + "/regionalCenterZoneLookup/update";
		},
		DELETE_REGIONAL_CENTER_ZONE : function(id) {
			return url + "/regionalCenterZoneLookup/delete/" + id;
		},
		ADD_REGIONAL_CENTER_LOOKUP : function() {
			return url + "/regionalCenterLookup/save";
		},
		GETALL_REGIONAL_CENTER_LOOKUP : function() {
			return url + "/regionalCenterLookup/getAll";
		},
		UPDATE_REGIONAL_CENTER_LOOKUP : function() {
			return url + "/regionalCenterLookup/update";
		},
		DELETE_REGIONAL_CENTER_LOOKUP : function(id) {
			return url + "/regionalCenterLookup/delete/" + id;
		},
		FIND_ALL_PATIENTS_BY_ROLE_FOR_BILLINGS_PAGE : function(page, size) {
			return url + "/patient/findAllPatientsByRoleForBillingsPage/"
					+ "?page=" + page + "&size=" + size;
		},
		ADD_INSURANCELOOKUP : function() {
			return url + "/insuranceLookup/";
		},
		ALL_INSURANCE_LOOKUPS : function() {
			return url + "/insuranceLookup/all";
		},
		UPDATE_INSURANCE_LOOKUP : function() {
			return url + "/insuranceLookup/update";
		},
		DELETE_INSURANCELOOKUP : function(insuranceLookupid) {
			return url + "/insuranceLookup/delete/" + insuranceLookupid;
		},
		GET_ALL_INSURANCES_WITHOUT_PAGINATION : function(email) {
			return url + "/insurance/getAllwithoutPagination/" + email;
		},
		ADD_SCHOOL : function() {
			return url + "/school/addSchool/";
		},
		GET_ALL_SCHOOLS_BY_COMPANY_USERNAME_PAGE : function(username, page,
				size) {
			return url + "/school/getall/" + username + "/" + "?page=" + page
					+ "&size=" + size;
		},
		GET_ALL_SCHOOLS_BY_COMPANY_USERNAME : function(username) {
			return url + "/school/getallwithoutPagenation/" + username;
		},
		DELETE_SCHOOL : function(id) {
			return url + "/school/delete/" + id;
		},
		GET_ALL_SCHOOLS_BY_COMPANY : function(username) {
			return url + "/school/getAllSchoolsByCompany/" + username;
		},
		UPDATE_SCHOOL : function() {
			return url + "/school/update";
		},
		// CATEGORYTYPE
		ADDCATEGORYTYPE : function() {
			return url + "/CategoryType/add";
		},
		GETALLCATEGORYTYPES : function() {
			return url + "/CategoryType/all";
		},
		UPDATECATEGORYTYPE : function() {
			return url + "/CategoryType/update";
		},
		DELETECATEGORYTYPE : function(id) {
			return url + "/CategoryType/delete/" + id;
		},
		GETALLCATEGORYTYPEBYSTATUS : function() {
			return url + "/CategoryType/allByStatus";
		},
		GET_ALL_INSURANCES_BY_COMPANY : function(username) {
			return url + "/insurance/getAllInsurancesByCompany/" + username;
		},
		ADD_CARETAKER_CATEGORYLOOKUP : function() {
			return url + "/saveCareTakerCategoryLookup";
		},
		GETALL_CATEGORYLOOKUPS : function() {
			return url + "/getCareTakerCategoryLookups";
		},
		UPDATE_CATEGORYLOOKUP : function() {
			return url + "/updateCareTakerCategoryLookup";
		},
		DELETE_CATEGORYLOOKUP : function(id) {
			return url + "/deleteCareTakerCategoryLookup/" + id;
		},
		ADD_CARETAKER_QUESTIONLOOKUP : function() {
			return url + "/saveCareTakerQuestionLookup";
		},
		GETALL_CARETAKER_QUESTIONLOOKUPS : function() {
			return url + "/getCareTakerQuestionLookups";
		},
		UPDATE_CARETAKER_QUESTIONLOOKUP : function() {
			return url + "/updateCareTakerQuestionLookup";
		},
		DELETE_CARETAKER_QUESTIONLOOKUP : function(id) {
			return url + "/deleteCareTakerQuestionLookup/" + id;
		},
		SAVE_CARETAKER_MILESTONES : function(adminUserName) {
			return url + "/saveCaretakerMilestones/" + adminUserName;
		},
		CARETAKER_MILESTONES_DASHBOARD : function(adminUserName) {
			return url + "/getCaretakerAnswersCountForDashboard/"
					+ adminUserName;
		},
		GETALL_CARETAKER_MILESTONES : function(id, adminUserName) {
			return url + "/getCaretakerMilestones/" + id + "/" + adminUserName;
		},
		UPDATE_CARETAKER_MILESTONES : function(adminUserName) {
			return url + "/updateCaretakerMilestones/" + adminUserName;
		},
		GET_QUESTIONS_BASED_ON_CATEGORYSTATUS : function(id) {
			return url + "/getCareTakerQuestionsByCategoryStatus/" + id;
		},
		ADDCHILDOBSERVATIONCATEGORYLOOKUP : function() {
			return url + "/saveChildObservationCategoryLookup";
		},
		GETALLCHILDOBSERVATIONCATEGORYLOOKUPS : function() {
			return url + "/getAllChildObservationCategoryLookups";
		},
		UPDATECHILDOBSERVATIONCATEGORYLOOKUP : function() {
			return url + "/updateChildObservationCategoryLookup";
		},
		DELETECHILDOBSERVATIONCATEGORYLOOKUP : function(id) {
			return url + "/deleteChildObservationCategoryLookup/" + id;
		},
		GETALLCHILDOBSERVATIONCATEGORYLOOKUPSIDS : function() {
			return url + "/getAllChildObservationCategoryLookupsIds";
		},
		ADDCHILDOBSERVATIONQUESTIONLOOKUP : function() {
			return url + "/saveChildObservationQuestionLookup";
		},
		GETALLCHILDOBSERVATIONQUESTIONLOOKUPS : function() {
			return url + "/getAllChildObservationQuestionLookups";
		},
		DELETECHILDOBSERVATIONQUESTIONLOOKUP : function(id) {
			return url + "/deleteChildObservationQuestionLookup/" + id;
		},
		UPDATECHILDOBSERVATIONQUESTIONLOOKUP : function() {
			return url + "/updateChildObservationQuestionLookup";
		},
		ADDCHILDOBSERVATIONQUESTION : function(userName) {
			return url + "/saveChildObservationQuestion/" + userName;
		},
		GETALLQUESTIONSBYCATEGORYIDANDPESONID : function(id, userName) {
			return url
					+ "/getChildObservationQuestionsByPatientUsernameAndCategoryId/"
					+ userName + "/" + id;
		},
		GETALLCHILDOBSERVATIONQUESTIONS : function() {
			return url + "/getAllChildObservationQuestions";
		},
		UPDATECHILDOBSERVATIONQUESTION : function(userName) {
			return url + "/updateChildObservationQuestion/" + userName;
		},
		GETALLCHILDOBSERVATIONCATEGORYLOOKUPSIDS : function() {
			return url + "/getAllChildObservationCategoryLookupsIds";
		},
		CHILDDASHBOARD : function(userName) {
			return url + "/getAnswersCountForDashboard/" + userName;
		},
		GETALLQUESTIONSBYCATEGORYID : function(id) {
			return url
					+ "/getAllChildObservationQuestionLookupsByCategoryStatus/"
					+ id;
		},
		SAVE_SCREENING_TEST_LOOKUP : function() {
			return url + "/saveScreeningTestLookup";
		},
		GET_ALL_SCREENING_TEST_LOOKUP : function() {
			return url + "/getAllScreeningTestLookup";
		},
		UPDATE_SCREENING_TEST_LOOKUP : function() {
			return url + "/updateScreeningTestLookup";
		},
		DELETE_SCREENING_TEST_LOOKUP_BY_ID : function(id) {
			return url + "/deleteScreeningTestLookup/" + id;
		},
		SAVE_SCREENING_TEST_QUESTION_LOOKUP : function() {
			return url + "/saveScreeningTestQuestionLookup";
		},
		GET_ALL_SCREENING_TEST_QUESTION_LOOKUP : function() {
			return url + "/getAllScreeningTestQuestionLookup";
		},
		DELETE_SCREENING_TEST_QUESTION_LOOKUP_BY_ID : function(id) {
			return url + "/deleteScreeningTestQuestionLookup/" + id;
		},
		UPDATE_SCREENING_TEST_QUESTION_LOOKUP : function() {
			return url + "/updateScreeningTestQuestionLookup";
		},
		SAVE_SCREENING_TEST_CATEGORY : function(adminUserName) {
			return url + "/saveScreeningTestCategoryQuestions/" + adminUserName;
		},
		GET_ALL_SCREENING_TEST_BY_CATEGORY_STATUS : function() {
			return url + "/getAllScreeningTestCategoryQuestions";
		},
		GET_ALL_SCREENING_TEST_SOCIAL_GRAPH : function(adminUserName) {
			return url + "/getAllScreeningTestCategoryForSocialStack/"
					+ adminUserName;
		},
		GET_ALL_SCREENING_TEST_IMPAIRMENT_GRAPH : function(adminUserName) {
			return url + "/getAllScreeningTestCategoryForImparimentStackGraph/"
					+ adminUserName;
		},
		GET_ALL_SCREENING_TEST_BEHAVIOUR_GRAPH : function(adminUserName) {
			return url + "/getAllScreeningTestCategoryForBehaviourStackGraph/"
					+ adminUserName;
		},
		GET_CREATED_DATES_BY_SCREENING_TEST : function(adminUserName,
				categoryStatus) {
			return url + "/getAllScreeningTestGoalsCreatedDates/"
					+ adminUserName + "/" + categoryStatus;
		},
		GET_ALL_SCREENING_TEST_LOOKUP_BY_CATEGORY_STATUS : function(
				categorystatus) {
			return url + "/getAllScreeningTestLookupByCategoryStatus/"
					+ categorystatus;
		},
		ADD_CSBS_CATEGORY : function() {
			return url + "/savecsbsLookup";
		},
		GET_ALL_CSBS_CATEGORY : function() {
			return url + "/getAllcsbsLookup";
		},
		GET_ALL_CSBS_CATEGORY_BY_ID : function(id) {
			return url + "/getAllcsbsLookupByCategoryStatus/" + id;
		},
		UPDATE_CSBS_CATEGORY : function() {
			return url + "/updatecsbsLookup";
		},
		DELETE_CSBS_CATEGORY : function(id) {
			return url + "/deletecsbsLookup/" + id;
		},
		ADD_CSBS_QUESTION : function() {
			return url + "/savecsbsQuestionLookup";
		},
		GET_ALL_CSBS_QUESTIONS : function() {
			return url + "/getAllcsbsQuestionLookup";
		},
		UPDATE_CSBS_QUESTIONS : function() {
			return url + "/updatecsbsQuestionLookup";
		},
		DELETE_CSBS_QUESTION : function(id) {
			return url + "/deletecsbsQuestionLookup/" + id;
		},
		ADD_CSBS_CATEGORY_TEMPLATE : function(adminUserName) {
			return url + "/savecsbsCategoryQuestions/" + adminUserName;
		},
		GET_ALL_CSBS_DATA : function(categoryId) {
			return url + "/getAllcsbsCategoryQuestions/" + categoryId ;
		},
		GET_ALL_CSBS_QUESTIONS_BY_CATEGORY_ID : function(categoryId) {
			return url + "/getAllcsbsQuestionLookupByCategoryId/" + categoryId ;
		}
		,
		ADD_MCHART_LOOKUP : function() {
			return url + "/saveMchartLookup";
		},
		GET_ALL_MCHART_LOOKUP : function() {
			return url + "/getAllMchartLookup";
		},
		UPDATE_MCHART_LOOKUP : function() {
			return url + "/updateMchartLookup";
		},
		DELETE_MCHART_LOOKUP : function(id) {
			return url + "/deleteMchartLookup/" + id;
		},
		ADD_MCHART : function() {
			return url + "/saveMchart";
		},
		GET_ALL_MCHART_BY_ID : function(id) {
			return url + "/getAllMchart/" + id;
		},
		GET_ALL_PATIENT_BEHAVIOURAL_PROGRAMME_BY_ID : function(patientid) {
			return url + "/behaviourlManagement/getAllPatientBehaviouralManagement/" + patientid;
		},
		GET_ALL_PATIENT_SPEECH_EVALUTION_BY_ID : function(patientid) {
			return url + "/speechEvaluation/getAllPatientSpeechEvaluationByPatientId/" + patientid;
		},
		GET_ALL_MCHART_COUNT_BY_PATIENT_ID : function(id) {
			return url + "/getMchartCountByPatientId/" + id;
		},
		
		ADD_NICHQ_CATEGORY_LOOKUP : function() {
			return url + "/saveNICHQParentCategoryLookup";
		},
		GET_ALL_NICHQ_CATEGORY_LOOKUP_QUESTIONS : function() {
			return url + "/getAllNICHQParentCategoryLookup";
		},
		UPDATE_NICHQ_CATEGORY_LOOKUP : function() {
			return url + "/updateNICHQParentCategoryLookup";
		},
		DELETE_NICHQ_CATEGORY_LOOKUP : function(id) {
			return url + "/deleteNICHQParentCategoryLookup/" + id;
		},
		GET_ALL_NICHQ_CATEGORY_BY_ID : function(id) {
			return url + "/getAllNichqParentCategoryLookupByStatus/" + id;
		},
		ADD_NICHQ_PARENT_QUESTION_LOOKUP : function() {
			return url + "/saveNICHQPQLookup";
		},
		GET_ALL_NICHQ_PARENT_QUESTION_LOOKUPS : function() {
			return url + "/getAllnichqParentQuestionLookup";
		},
		UPDATE_NICHQ_PARENT_QUESTION_LOOKUP : function() {
			return url + "/updateNICHQParentQuestionLookup";
		},
		DELETE_NICHQ_PARENT_QUESTION_LOOKUP : function(id) {
			return url + "/deleteNICHQParentQuestionLookup/" + id;
		},
		ADD_NICHQ_CATEGORY_TEMPLATE : function(adminUserName) {
			return url + "/saveNichqCategoryQuestions/" + adminUserName;
		},
		GET_ALL_NICHQ_DATA : function(adminUserName, id) {
			return url + "/getAllNichqCategoryQuestions/" + adminUserName + "/"
					+ id;
		},
		GET_ALL_PATIENTMENTALSCALES_BYPATIENT_ID : function(patientId) {
			return url + "/getAllPatientMentalScales/" + patientId;
		},
		UPDATE_PATIENT_MENTALSCALES : function(patientId) {
			return url + "/updatePatientMentalScales/" + patientId;
		},
		GET_PATIENT_MENTALSCALES_AGE_CLUSTERS : function(patientId) {
			return url + "/getAllPatientMentalClusterCountResult/" + patientId;
		},
		GET_PATIENT_MENTALSCALES_AGE_CLUSTERS_FOR_GRAPH : function(patientId) {
			return url + "/getAllPatientMentalClusterCountForGraph/"
					+ patientId;
		},
		GET_ALL_PATIENT_MOTOR_SCALES_BYPATIENT_ID : function(patientId) {
			return url + "/motorScale/getAllPatientMotorScalesByPatientId/"
					+ patientId;
		},
		GET_ALL_MOTOR_SCALES_COUNT : function(patientId) {
			return url + "/motorScale/getAllPatientMotorClusterCountResult/"
					+ patientId;
		},
		GET_ALL_MOTOR_SCALES_COUNT_FOR_GRAPH : function(patientId) {
			return url + "/motorScale/getAllPatientMotorClusterCountForGraph/"
					+ patientId;
		},
		UPDATE_PATIENT_MOTOR_SCALES : function(patientId) {
			return url + "/motorScale/updatePatientMotorScales/" + patientId;
		},
		ADD_MENTAL_CLUSTER : function() {
			return url + "/saveMentalCluster";
		},
		GET_ALL_MENTAL_CLUSTER : function() {
			return url + "/getAllMentalCluster";
		},
		UPDATE_MENTAL_CLUSTER : function() {
			return url + "/updateMentalCluster";
		},
		DELETE_MENTAL_CLUSTER : function(id) {
			return url + "/deleteMentalCluster/" + id;
		},
		ADD_MENTAL_SCALE : function() {
			return url + "/saveMentalScales";
		},
		GET_ALL_MENTAL_SCALE : function() {
			return url + "/getAllMentalScales";
		},
		UPDATE_MENTAL_SCALE : function() {
			return url + "/updateMentalScales";
		},
		DELETE_MENTAL_SCALE : function(id) {
			return url + "/deleteMentalScales/" + id;
		},
		UPDATE_MENTAL_SCALE_WITH_CLUSTER : function() {
			return url + "/updateMentalScalesWithCluster";
		},
		ASSIGN_MENTAL_SCALES_TO_PATIENT : function() {
			return url + "/assignmentalScalesToPatient";
		},
		ASSING_MOTOR_TEMPLATE_TO_PATIENT_MOTOR_SCALES : function() {
			return url + "/motorScale/assignMotorScaleTemplateToPatient";
		},
		ADD_MOTOR_CLUSTER : function() {
			return url + "/motorCluster/";
		},
		GET_MOTOR_CLUSTERS : function() {
			return url + "/motorCluster/all";
		},
		UPDATE_MOTOR_CLUSTER : function() {
			return url + "/motorCluster/update";
		},
		DELETE_MOTOR_CLUSTER : function(id) {
			return url + "/motorCluster/" + id;
		},
		ADD_MOTOR_SCALE : function() {
			return url + "/motorScale/";
		},
		GET_MOTOR_SCALES : function() {
			return url + "/motorScale/all";
		},
		UPDATE_MOTOR_SCALE : function() {
			return url + "/motorScale/update";
		},
		DELETE_MOTOR_SCALE : function(id) {
			return url + "/motorScale/" + id;
		},
		ASSIGN_MOTOR_SCALE_TO_MOTORCLUSTER : function(clusterId) {
			return url + "/motorScale/assignedToMotorCluster/" + clusterId;
		},
		ADD_VSMS_CLUSTER : function() {
			return url + "/saveVSMSCluster";
		},
		GET_VSMS_CLUSTERS : function() {
			return url + "/getAllVSMSCluster";
		},
		UPDATE_VSMS_CLUSTER : function() {
			return url + "/updateVSMSCluster";
		},
		DELETE_VSMS_CLUSTER : function(id) {
			return url + "/deleteVSMSCluster/" + id;
		},
		ADD_VSMS_QUESTION : function() {
			return url + "/saveVSMSQuestions";
		},
		GET_VSMS_QUESTIONS : function() {
			return url + "/getAllVSMSQuestions";
		},
		UPDATE_VSMS_QUESTION : function() {
			return url + "/updateVSMSQuestions";
		},
		DELETE_VSMS_QUESTION : function(id) {
			return url + "/deleteVSMSQuestions/" + id;
		},
		ASSIGN_VSMS_QUESTIONS_TO_PATIENT : function() {
			return url + "/assignVSMSQuestionsToPatient" ;
		},
		GET_ALL_PATIENT_VSMS_QUESTIONS_BYPATIENT_ID : function(patientId) {
			return url + "/getAllPatientVSMSQuestions/" + patientId;
		},
		VSMS_REPORT_DATA : function(patientId) {
			return url + "/getVSMReportData/" + patientId;
		},
		
		ADD_MCHATTEMPLATE_TO_PATIENT : function() {
			return url + "/patient/patientMchatAssmnt";
		},
		UPDATE_PATIENT_MCHART_QUESTIONS :function(patientId) {
			return url + "/patient/updatePatientMchatAssmnt/" + patientId;
		},
		GET_PATIENT_OBJECT : function() {
			return url + "/patient/getPatient";
		},
		ADD_CATEGORYLOOKUP : function() {
			return url + "/saveISAABehaviorLookup";
		},
		ALL_CATEGORY_LOOKUPS : function() {
			return url + "/getAllISAABehaviorLookup";
		},
		DELETE_CATEGORYLOOKUP : function(id) {
			return url + "/deleteISAABehaviorLookup/" + id;
		},
		UPDATE_CATEGORY_LOOKUP : function() {
			return url + "/updateISAABehaviorLookup";
		},
		ADD_CATEGORY_QUESTIONLOOKUP : function() {
			return url + "/saveISAABehaviorQuestionLookup";
		},
		ALL_ISAAB_QUESTION_LOOKUPS : function() {
			return url + "/getAllISAABehaviorQuestionLookup";
		},
		UPDATE_QUESTION_LOOKUP : function() {
			return url + "/updateISAABehaviorQuestionLookup";
		},
		DELETE_CATEGORY_QUESTION_LOOKUP : function(id) {
			return url + "/deleteISAABehaviorQuestionLookup/" + id;
		},

		ADD_NICHQ_PARENT : function() {
			return url + "/saveNICHQParentCategoryLookup";
		},
		GET_NICHQ_PARENT : function() {
			return url + "/getAllNICHQParentCategoryLookup";
		},
		DELETE_NICHQ_PARENT : function(id) {
			return url + "/deleteNICHQParentCategoryLookup/" + id;
		},
		UPDATE_NICHQ_PARENT : function() {
			return url + "/updateNICHQParentCategoryLookup";
		},
		ADD_NICHQ_TEACHER_CATEGORY_LOOKUP : function() {
			return url + "/nichqTeacherCategorylookup/";
		},
		GET_NICHQ_TEACHER_CATEGORY_LOOKUP : function() {
			return url + "/nichqTeacherCategorylookup/all";
		},
		DELETE_NICHQ_TEACHER_CATEGORY_LOOKUP : function(id) {
			return url + "/nichqTeacherCategorylookup/delete/" + id;
		},
		UPDATE_NICHQ_TEACHER_CATEGORY_LOOKUP : function() {
			return url + "/nichqTeacherCategorylookup/update";
		},
		ADD_NICHQ_TEACHER_QUESTION_LOOKUP : function() {
			return url + "/nichqTeacherQuestionLookup/";
		},
		GET_NICHQ_TEACHER_QUESTION_LOOKUP : function() {
			return url + "/nichqTeacherQuestionLookup/all";
		},
		DELETE_NICHQ_TEACHER_QUESTION_LOOKUP : function(id) {
			return url + "/nichqTeacherQuestionLookup/delete/" + id;
		},
		UPDATE_NICHQ_TEACHER_QUESTION_LOOKUP : function() {
			return url + "/nichqTeacherQuestionLookup/update";
		},
		ADD_NICHQ_TEACHER_TEMPLATE_TO_THE_PARENT : function() {
			return url + "/patientNichqTeacherCategory/";
		},
		GET_NICHQ_TEACHER_TEMPLATE_TO_THE_PARENT : function(id) {
			return url + "/patientNichqTeacherCategory/allByPatientId/" + id;
		},
		ADD_NICHQ_PARENT_TEMPLATE : function() {
			return url + "/nichqParentCategory/";
		},
		GET_NICHQ_PARENT_TEMPLATE_TO_THE_PARENT : function(id) {
			return url + "/nichqParentCategory/allBy/" + id;
		},
		ADD_ISAABEHAVIOUR_TEMPLATE : function() {
			return url + "/saveISAABehaviorCategoryQuestions";
		},
		GET_ALL_ISAABEHAVIOUR_TEMPLATES_BY_PATIENTID : function(patientId) {
			return url + "/getAllISAABehaviorCategorysByPatientId/" + patientId
					+ "/";
		},
		GET_NICHQ_TEACHER_RESULTS : function(id) {
			return url + "/patientNichqTeacherCategory/nichqTeacherResult/"
					+ id;
		},
		GET_NICHQ_PARENT_RESULTS : function(id) {
			return url + "/nichqParentCategory/parentResult/" + id;
		},
		GET_ISAA_BEHAVIOUR_REPORT_BY_PATIENT_ID : function(patientid) {
			return url + "/isaaBehaviourResult/" + patientid + "/";
		},
		DELETE_TEACHER_RESULT_BY_PATIENT_ID : function(id) {
			return url
					+ "/patientNichqTeacherCategory/deletenichqTeacherReport/"
					+ id;
		},
		DELETE_PARENT_RESULT_BY_PATIENT_ID : function(id) {
			return url + "/nichqParentCategory/deleteParentResult/" + id;
		},
		DELETE_ISAA_BEHAVIOUR_BY_PATIENT_ID : function(id) {
			return url + "/deleteisaaBehaviourResult/" + id;
		},
		DELETE_MCHART_RESULT_BY_PATIENT_ID : function(id) {
			return url + "/deleteMchartsByPatientId/" + id;
		},
		GET_ALL_NICHQ_PARENT_CATEGORY_LOOKUPS : function() {
			return url + "/getAllNICHQParentCategoryLookup";
		},
		GET_ALL_NICHQ_PARENT_QUESTION_LOOKUPS_BY_CATEGORY : function(categoryId) {
			return url + "/getAllnichqParentQuestionLookupsByCategoryId/"
					+ categoryId;
		},
		ASSIGN_NICHQ_PARENT_TEMPLATE_TO_PATIENT : function() {
			return url + "/nichqParentCategory/";
		},
		GET_ALL_NICHQ_PARENT_CATEGORIES_BY_PATIENT : function(patientId) {
			return url + "/nichqParentCategory/allBy/" + patientId;
		},
		GET_ALL_NICHQ_PARENT_TEMPLATE_RESULT_BY_PATIENT : function(patientId) {
			return url + "/nichqParentCategory/parentResult/" + patientId;
		},
		GET_ALL_NICHQ_PARENT_QUESTIONS_BY_PATIENT_AND_CATEGORY : function(
				patientId, categoryId) {
			return url + "/nichqParentQuestion/byPatient/" + patientId
					+ "/categoryId/" + categoryId;
		},
		GET_NICHQ_TEACHER_QUESTIONS_BY_PATIENT_AND_CATEGORY : function(
				patientId, categoryId) {
			return url + "/patientNichqTeacherQuestion/patient/" + patientId
					+ "/category/" + categoryId;
		},
		GET_ALL_NICHQ_TEACHER_QUESTION_LOOKUPS_BY_CATEGORY : function(
				categoryId) {
			return url + "/nichqTeacherQuestionLookup/allByCategoryId/"
					+ categoryId;
		},
		ASSIGN_NICHQ_TEACHER_TEMPLATE_TO_PATIENT : function() {
			return url + "/patientNichqTeacherCategory/";
		},
		GET_ALL_NICHQ_PARENTRESULT_BY_PATIENT : function(patientId) {
			return url + "/nichqParentQuestion/reportbyPatient/" + patientId;
		},
		ALL_ISAABEHAVIORAL_CATEGORY_LOOKUPS : function() {
			return url + "/getAllISAABehaviorLookup";
		},
		ALL_ISAAB_QUESTION_LOOKUPS_BY_CATEGORYLOOKUP : function(categoryId) {
			return url + "/getAllISAABehaviorQuestionLookupsByCategory/"
					+ categoryId;
		},
		ALL_ISAAB_QUESTIONS_BY_PATIENT_AND_CATEGORYLOOKUP : function(patientId,
				categoryId) {
			return url + "/isaaBehaviouralQuestion/allByPatient/" + patientId
					+ "/categorylookup/" + categoryId;
		},
		ASSIGN_ISAABEHAVIORAL_TEMPLATE_TO_PATIENT : function() {
			return url + "/saveISAABehaviorCategoryQuestions";
		},
		GET_ISAA_BEHAVIOUR_RESULT_BY_PATIENT : function(patientId) {
			return url + "/isaaBehaviouralQuestion/reportbyPatient/"
					+ patientId;
		},
		GET_NICHQ_TEACHER_TEMPLATE_RESULT_BY_PATIENT : function(patientId) {
			return url + "/patientNichqTeacherCategory/nichqTeacherResult/"
					+ patientId;
		},
		GET_NICHQ_TEACHER_TEMPLATE_REPORT_BY_PATIENT : function(patientId) {
			return url + "/patientNichqTeacherCategory/nichqTeacherReport/"
					+ patientId;
		},
		GET_ALL_MCHART_BY_PATIENT_ID: function(patientid) {
			return url + "/getAllMchart/" + patientid;
		},
		GET_ALL_PATIENT_REEGISTRATION_TYPES: function() {
			return url + "/patientregistrationtype/all";
		},
		ADD_PATIENT_REGISTRATIONTYPE: function() {
			return url + "/patientregistrationtype/add";
		},
		DELETE_PATIENT_REGISTRATION_TYPE: function(id) {
			return url + "/patientregistrationtype/delete/" + id;
		},
		UPDATE_PATIENT_REGISTRATION_TYPE : function() {
			return url + "/patientregistrationtype/update";
		},
		GETALLCATEGORIESBYREGISTRATIONTYPE : function(id) {
			return url + "/CategoryType/getAllCatByRegistrationtype/" + id;
		},
		GET_PATIENT_BY_SSN : function(ssn) {
			return url + "/patient/getPaientBySSN/" + ssn;
		},
		GET_PATIENT_BY_ID : function(id) {
			return url + "/patient/getPaientById/" + id;
		},
		GET_PATIENT_BY_REGNO : function(regNo) {
			return url + "/patient/getPaientByREGNO/" + regNo;
		},
		GET_CAT_BY_REGTYPE : function(pID) {
			alert("p id -->");
			return url + "/patient/getCatByPaient/" + pID;
		},
		
		SAVE_PATIENT_SPEECH_EVALUTION_SHEET :function(patientid){
			return url + "/speechEvaluation/" + patientid ;
		},
		
		SAVE_PATIENT_BEHAVIOURAL_MANAGEMENT_SHEET :function(patientid){
			return url + "/behaviourlManagement/" + patientid ;
		}

	};

};
angular.module("HealthApplication").constant("PATIENT_MODULE_CONFIG",
		PatientModuleConstants());










































