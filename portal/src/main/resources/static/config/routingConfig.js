var myApp = angular.module('HealthApplication', ['ui.router','mwl.calendar','ngStorage','ngMaterial','ngAnimate','long2know',
    'ngAria','ui.bootstrap', 'colorpicker.module','ngSanitize','chart.js','xeditable',
    'ngMessages','mdPickers','checklist-model','ui.bootstrap.datetimepicker','angucomplete-alt','ngAutocomplete','dx','angularPayments']);  
myApp.config(function($stateProvider, $urlRouterProvider,$httpProvider) {
	$urlRouterProvider.otherwise('/login');
	$stateProvider.state('login', {
		url : '/login',
		templateUrl : 'views/account/login/login.html',
		controller : 'loginCtrl'
	}).state('healthapp', {
		url : '/healthapp',
		templateUrl : 'views/account/login/HealthApp.html',
		controller : 'loginCtrl'
	}).state('signup', {
		url : '/signup',
		abstract:true,
		templateUrl : 'views/account/signup/signup.html',
		controller : 'signupCtrl'
	}).state('signup.primaryRegistration', {
		url : '/primaryRegistration',
		templateUrl : 'views/account/signup/primaryRegistration.html',
		controller : 'signupCtrl',
		params:{
			userObject:null,
			pckgPrice:null,
			userCompany:null,
			flag:null,
			pkname:null,
			stripePlan:null
		}
	}).state('signup.packageSubscription', {
		url : '/packageSubscription',
		templateUrl : 'views/account/signup/packageSubscription.html',
		controller : 'signupCtrl',
		params:{
			userObject:null,
			UserCompanyTypeObject:null,
			pckgMaster:null,
			userPackagePricing:null,
			stripePlan:null
		}
	}).state('signup.cofirmation', {
		url : '/cofirmation',
		templateUrl : 'views/account/signup/confirmation.html',
		controller : 'signupCtrl',
		params:{
			userObject : null,
			userPackagePricing : null,
			userCompanyType:null,
			stripePlan:null
		}
	}).state('signup.blank', {
		url : '/blank',
		templateUrl : 'views/account/signup/blank.html',
		controller : 'signupCtrl'
	}).state('payment', {
		url : '/payment',
		templateUrl : 'views/account/signup/payment.html',
		controller : 'packagePricingCtrl'
	}).state('forgotpassword', {
		url : '/forgotpassword',
		templateUrl : 'views/account/forgotpassword/forgot_password.html',
		controller : 'forgotpasswordCtrl'
	}).state('otp', {
		url : '/otp',
		templateUrl : 'views/otp/otp.html',
		controller : 'otpCtrl',
		params:{
			UserObject:null
		}
	}).state('main', {
		url : '/main',
		templateUrl : 'views/main.html',
		abstract:true,
		controller : 'loginCtrl'
	}).state('main.dashboard', {
		url : '/dashboard',
		templateUrl : 'views/dashboard/dashboard.html',
		controller : 'loginCtrl'
	}).state('main.registeredCompaniesDashboard', {
		url : '/registeredCompaniesDashboard',
		templateUrl : 'views/dashboard/registeredCompaniesDashboard.html',
		controller : 'loginCtrl'
	}).state('main.changepassword', {
		url : '/changepassword', 
		templateUrl : 'views/companysetup/companymanagement/change_password.html',
		controller : 'changepasswordCtrl'
	}).state('main.add_patient', {
		url : '/add_patient',
		abstract:true,
		templateUrl : 'views/patient/add_patient.html',
		controller : 'PatientCtrl'
	}).state('main.add_patient.add_patient_details', { 
		url : '/add_patient_details',
		templateUrl : 'views/patient/addPatient/registrationDetails.html',
		controller : 'PatientCtrl',
		params:{
			patient:null,
			payment:null,
			documentTypelookup:null,
			documentsList1:null,
			regionalZone:null
		}
	}).state('main.add_patient.add_child_details', {
		url : '/add_child_details',
		templateUrl : 'views/patient/addPatient/childDetails.html',
		controller : 'PatientCtrl',
		params:{
			patient:null,
			payment:null,
			documentTypelookup:null,
			documentsList1:null,
			regionalZone:null
		}
	}).state('main.add_patient.add_parent_details', {
		url : '/add_child_details',
		templateUrl : 'views/patient/addPatient/parentDetails.html',
		controller : 'PatientCtrl',
		params:{
			patient:null,
			payment:null,
			documentTypelookup:null,
			documentsList1:null,
			regionalZone:null
		}
	}).state('main.add_patient.add_patient_communicationAddress', {
		url : '/add_patient_communicationAddress',
		templateUrl : 'views/patient/addPatient/communicationAddress.html',
		controller : 'PatientCtrl',
		params:{
			patient:null,
			payment:null,
			documentTypelookup:null,
			documentsList1:null,
			regionalZone:null
		}
	}).state('main.add_patient.add_patient_uploadDocuments', {
		url : '/add_patient_uploadDocuments',
		templateUrl : 'views/patient/addPatient/uploadDocuments.html',
		controller : 'PatientCtrl',
		params:{
			patient:null,
			payment:null,
			documentTypelookup:null,
			documentsList1:null,
			regionalZone:null
		}
	}).state('main.add_patient.add_patient_clientType', {
		url : '/add_patient_clientType',
		templateUrl : 'views/patient/addPatient/clientType.html',
		controller : 'PatientCtrl',
		params:{
			patient:null,
			payment:null,
			documentTypelookup:null,
			documentsList1:null,
			regionalZone:null
		}
	}).state('main.add_patient.add_patient_payments', {
		url : '/add_patient_payments',
		templateUrl : 'views/patient/addPatient/payments.html',
		controller : 'PatientCtrl',
		params:{
			patient:null,
			payment:null,
			documentTypelookup:null,
			documentsList1:null,
			regionalZone:null
		}
	}).state('main.add_patient.add_patient_summary', {
		url : '/add_patient_summary',
		templateUrl : 'views/patient/addPatient/summary.html',
		controller : 'PatientCtrl',
		params:{
			patient:null,
			payment:null,
			documentTypelookup:null,
			documentsList1:null,
			regionalZone:null
		}
	}).state('main.patient_list', {
		url : '/patient_list',
		templateUrl : 'views/patient/patient_list.html',
		controller : 'PatientCtrl',
		params:{
			inActivePatientsObject:null,
			todayPatientsObject:null
		}
	}).state('main.patientslist', {
		url : '/patientslist',
		templateUrl : 'views/patient/patient_acclist.html',
		controller : 'PatientCtrl'
	}).state('main.searchPatient', {
		url : '/searchPatient',
		templateUrl : 'views/add_patient_payment/searchPatient.html',
		controller : 'PatientCtrl'
	}).state('main.searchPatients', {
		url : '/searchPatients',
		templateUrl : 'views/patient/searchPatients.html',
		controller : 'PatientCtrl'
	}).state('main.searchByAppointment', {
		url : '/searchByAppointment',
		templateUrl : 'views/reports/searchPatientsByAppointmentType.html',
		controller : 'PatientCtrl'
	}).state('main.searchByClientType', {
		url : '/searchByClientType',
		templateUrl : 'views/reports/searchPatientsByClientType.html',
		controller : 'PatientCtrl'
	}).state('main.searchByDepartmentType', {
		url : '/searchByDepartmentType',
		templateUrl : 'views/reports/searchPatientsByDepartmentType.html',
		controller : 'PatientCtrl'
	}).state('main.invoice', {
		url : '/invoice',
		templateUrl : 'views/reports/invoice.html',
		controller : 'PatientCtrl'
	}).state('main.invoices', {
		url : '/invoices',
		templateUrl : 'views/reports/invoices.html',
		controller : 'PatientCtrl'
	}).state('main.typography', {
		url : '/typography',
		templateUrl : 'views/otp/typography.html',
		controller : 'PatientCtrl'
	}).state('main.savePayment', {
		url : '/savePayment',
		templateUrl : 'views/add_patient_payment/savePayment.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	}).state('main.paymentList', {
		url : '/paymentList',
		templateUrl : 'views/add_patient_payment/paymentList.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	}).state('main.paymentsList', {
		url : '/paymentsList',
		templateUrl : 'views/add_patient_payment/paymentsList.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	}).state('main.patientviewtabs', {
		url : '/patient_view',
		templateUrl : 'views/patient_viewmore.html',
		abstract:true,
		controller : 'PatientCtrl'
	}).state('main.patientviewtabs.patient_documents', {
		url : '/patient_documents',
		templateUrl : 'views/patient/patient_documents.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	}).state('main.patientviewtabs.patient_note', {
		url : '/patient_note',
		templateUrl : 'views/patient/patient_note.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	}).state('main.patientDetails', {
		url : '/patientDetails',
		templateUrl : 'views/patient/patientDetails.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	}).state('main.patientviewtabs.patient_evaluation_sheet', {
		url : '/patient_evaluation_sheet',
		templateUrl : 'views/patient/patient_evaluation_sheet.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null,
			patientObj12:null,
			dataEval:null,
			progress:null			
		}
	}).state('main.patientviewtabs.patient_evaluation_sheet.mentalscalenavigation', {
		url : '/mentalscalenavigation',
		templateUrl : 'views/assessments/mentalscalenavigation.html',
		controller : 'assessmentCtrl'
	})
	
	.state('main.patientviewtabs.patient_evaluation_sheet.mentalscalenavigation.mentalQuestions', {
		url : '/mentalQuestions',
		templateUrl : 'views/assessments/mentalTemplate.html',
		controller : 'assessmentCtrl',
			params: {
				patient: null
				
			}
	})
	.state('main.patientviewtabs.patient_evaluation_sheet.mentalscalenavigation.motorQuestions', {
		url : '/motorQuestions',
		templateUrl : 'views/assessments/motorTemplate.html',
		controller : 'assessmentCtrl'		
	})
	.state('main.patientviewtabs.patient_evaluation_sheet.mchatQuestions', {
		url : '/mchatQuestions',
		templateUrl : 'views/assessments/mchatTemplate.html',
		controller : 'assessmentCtrl',
		params:{
			patient: null
		}
	}).state('main.patientviewtabs.patient_evaluation_sheet.vsmsTemplate', {
		url : '/vsmsTemplate',
		templateUrl : 'views/assessments/vsmsTemplate.html',
		controller : 'vsmsQuestionCtrl',
			params: {
				patient: null
				
			}
	})
	.state('main.patientviewtabs.patient_evaluation_sheet.caretakerMilestones', {
		url : '/caretakerMilestonesQuestions',
		templateUrl : 'views/assessments/careTakerMilestonesTemplate.html',
		controller : 'caretakerMilestonesCtrl'		
	})
	.state('main.patientviewtabs.patient_evaluation_sheet.childObservation', {
		url : '/childObservationQuestions',
		templateUrl : 'views/assessments/childObservationTemplate.html',
		controller : 'childObservationQuestionLookupCtrl'		
	})
	.state('main.patientviewtabs.patient_evaluation_sheet.screeningTest', {
		url : '/screeningTestQuestions',
		templateUrl : 'views/assessments/screeningTestTemplate.html',
		controller : 'screeningTestCtrl'		
	})
    .state('main.patientviewtabs.patient_evaluation_sheet.csbsTemplate', {
		url : '/csbsTemplateQuestions',
		templateUrl : 'views/assessments/csbsTemplate.html',
		controller : 'assessmentCtrl'		
	})
		
	.state('main.patientviewtabs.patient_evaluation_sheet.evalutionTemplate', {
		url : '/evalutionSheetTemplate',
		templateUrl : 'views/assessments/evalutionTemplate.html',
		controller : 'assessmentCtrl'		
	})
	.state('main.patientviewtabs.patient_evaluation_sheet.behaviourmanagementprogramme', {
		url : '/behaviourmanagementprogramme',
		templateUrl : 'views/assessments/behaviourmanagementprogramme.html',
		controller : 'PatientCtrl'		
	})
	.state('main.patientviewtabs.patient_evaluation_sheet.physiotheraphyevaluation', {
		url : '/physiotheraphyevaluation',
		templateUrl : 'views/assessments/physiotheraphyevaluation.html',
		controller : 'assessmentCtrl'		
	})
	.state('main.patientviewtabs.patient_evaluation_sheet.physiotheraphyevaluation.physiotheraphynewevaluation', {
		url : '/physiotheraphynewevaluation',
		templateUrl : 'views/assessments/physiotheraphynewevaluation.html',
		controller : 'assessmentCtrl',
params:{
	category:null
}
	}).state('main.patientviewtabs.patient_evaluation_sheet.speechevaluation', {
		url : '/speechevaluation',
		templateUrl : 'views/assessments/speechevaluation.html',
		controller : 'PatientCtrl'
	}).state('main.patientviewtabs.patient_evaluation_sheet.evaluationreport', {
		url : '/evaluationreport',
		templateUrl : 'views/assessments/evaluationreport.html',
		controller : 'PatientCtrl'
	}).state('main.patientviewtabs.patient_evaluation_sheet.evaluationEmptypage', {
		url : '/patientEvaluation',
		templateUrl : 'views/assessments/evaluationEmptypage.html',
		controller : 'PatientCtrl'
	}).state('main.patientviewtabs.patient_evaluation_sheet.patient_progress_notes', {
		url : '/patient_progress_notes',
		templateUrl : 'views/patient/patient_progress_notes.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null,
			patientObj12:null
		}
	}).state('main.patientviewtabs.patient_evaluation_sheet.nichqparentquestionlookup', {
		url : '/nichqparentTemplate',
		templateUrl : 'views/assessments/nichqparentTemplate.html',
		controller : 'assessmentCtrl',
		params:{
			patient:null
		}
	})
	.state('main.patientviewtabs.patient_evaluation_sheet.nichqteacherquestionlookuplist', {
		url : '/nichqteacherTemplate',
		templateUrl : 'views/assessments/nichqteacherTemplate.html',
		controller : 'assessmentCtrl',
		params:{
			patient:null
		}
	})
	.state('main.patientviewtabs.patient_evaluation_sheet.nichqparentTemplateResult', {
		url : '/nichqParentTemplateResult',
		templateUrl : 'views/autismTemplateResult/nichqParentTemplateResult.html',
		controller : 'assessmentCtrl',
		params:{
			patient:null
		}
	})
	.state('main.patientviewtabs.patient_evaluation_sheet.issaBehaviourview', {
		url : '/issaBehaviourview',
		templateUrl : 'views/assessments/issaBehaviourview.html',
		controller : 'assessmentCtrl',
			params:{
				patient: null
			}
	})
	.state('main.patientviewtabs.patient_evaluation_sheet.issaBehaviourview.isaaTemplate', {
		url : '/isaaTemplate',
		templateUrl : 'views/assessments/isaaBehaviouralTemplate.html',
		controller : 'assessmentCtrl',
		params:{
			patient: null,
			category:null
		}
	})
	
	.state('main.patientviewtabs.patient_evaluation_sheet.isaaBehaviouralTemplateResult', {
		url : '/isaaBehaviouralTemplateResult',
		templateUrl : 'views/autismTemplateResult/isaaBehaviouralTemplateResult.html',
		controller : 'assessmentCtrl',
			params:{
				patient: null
			}
	})
		.state('main.patientviewtabs.patient_evaluation_sheet.nichqTeacherTemplateResult', {
		url : '/nichqTeacherTemplateResult',
		templateUrl : 'views/autismTemplateResult/nichqTeacherTemplateResult.html',
		controller : 'assessmentCtrl',
			params:{
				patient: null
			}
	}).state('main.patientviewtabs.patient_goals_sheet', {
		url : '/patient_goals_sheet',
		templateUrl : 'views/patient/patient_goals_sheet.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	
	}).state('main.patientviewtabs.patient_exit_note', {
		url : '/patient_exit_note',
		templateUrl : 'views/patient/patient_exit_note.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null,
			patientObj12:null
		}
	}).state('main.updatePatient', {
		url : '/updatePatient',
		templateUrl : 'views/patient/updatePatient.html',
		controller : 'PatientCtrl',
		abstract:true,
		params:{
			patientObject:null
		}
	}).state('main.updatePatient.updatePatientDetails', {
		url : '/updatePatientDetails',
		templateUrl : 'views/patient/updatePatient/updatePatientDetails.html',
		controller : 'PatientCtrl',
		controllerAs: 'vm',
		params:{
			patientObject:null
		}
	}).state('main.updatePatient.updatePatientRegistrationDetails', {
		url : '/updatePatientRegistrationDetails',
		templateUrl : 'views/patient/updatePatient/updateRegistration.html',
		controller : 'PatientCtrl',
		controllerAs: 'vm',
		params:{
			patientObject:null,
			payment :null,
			PatientDocuments:null
		}
	}).state('main.updatePatient.updateChildDetails', {
		url : '/updateChildDetails',
		templateUrl : 'views/patient/updatePatient/updateChildDetails.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null,
			payment :null,
			PatientDocuments:null
		}
	}).state('main.updatePatient.updateParentDetails', {
		url : '/updateParentDetails',
		templateUrl : 'views/patient/updatePatient/updateParentDetails.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null,
			payment :null,
			PatientDocuments:null
		}
	}).state('main.updatePatient.updateCommunicationAddress', {
		url : '/updateCommunicationAddress',
		templateUrl : 'views/patient/updatePatient/updateCommunicationAddress.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null,
			payment :null,
			PatientDocuments:null
		}
	}).state('main.updatePatient.uploadDocuments', {
		url : '/uploadDocuments',
		templateUrl : 'views/patient/updatePatient/uploadDocuments.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null,
			payment :null,
			PatientDocuments:null
		}
	}).state('main.updatePatient.updateClientType', {
		url : '/updateClientType',
		templateUrl : 'views/patient/updatePatient/updateClientType.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null,
			payment :null,
			PatientDocuments:null
		}
	}).state('main.updatePatient.updatePayments', {
		url : '/updatePayments',
		templateUrl : 'views/patient/updatePatient/updatePayments.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null,
			payment :null,
			PatientDocuments:null
		}
	}).state('main.updatePatient.updateSummary', {
		url : '/updateSummary',
		templateUrl : 'views/patient/updatePatient/updateSummary.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null,
			payment :null,
			PatientDocuments:null
		}
	})
	.state('main.department_list', {
		url : '/department_list',
		templateUrl : 'views/companysetup/departmentmanagement/department_list.html',
		controller : 'loginCtrl'
	}).state('main.add_department', {
		url : '/add_department',
		templateUrl : 'views/companysetup/departmentmanagement/add_department.html',
		controller : 'loginCtrl'
	})
	.state('main.update_companymanagement', {
		url : '/update_companymanagement',
		templateUrl : 'views/companysetup/companymanagement/company_management.html',
		controller : 'companyCtrl'
	}).state('main.subscripton', {
		url : '/subscripton',
		templateUrl : 'views/companysetup/subscription/pricing.html',
		controller : 'loginCtrl'
	}).state('main.approveAppoinment', {
		url : '/approveAppoinment',
		templateUrl : 'views/appointment/approveAppoinment.html',
		controller : 'appointmentCtrl'
	}).state('main.appointment', {
		url : '/appointment',
		templateUrl : 'views/appointment/appointment.html',
		controller : 'appointmentCtrl'
			
	}).state('main.add_appointment', {
		url : '/add_appointment',
		templateUrl : 'views/appointment/add_appointment.html',
		controller : 'appointmentCtrl',
		params:{
			selectScheduleObj:null,
			stDate:null,
			enddate:null,
			startTime:null
		}
	
	}).state('main.appointment_list', {
		url : '/appointment_list',
		templateUrl : 'views/appointment/appointment_list.html',
		controller : 'appointmentCtrl'
	}).state('main.subAppointmentList', {
		url : '/subAppointmentList',
		templateUrl : 'views/appointment/subAppointmentList.html',
		controller : 'appointmentCtrl'
	}).state('main.appointment_listReports', {
		url : '/appointment_listReports',
		templateUrl : 'views/appointment/appointment_listReports.html',
		controller : 'appointmentCtrl'
	}).state('main.updateappointment', {
		url : '/updateappointment',
		templateUrl : 'views/appointment/update_appointment.html',
		controller : 'appointmentCtrl',
		params:{
			appointmentObj:null
		}
	}).state('main.editappointment', {
		url : '/editappointment',
		templateUrl : 'views/appointment/edit_Appointment.html',
		controller : 'appointmentCtrl',
		params:{
			appointmentObj:null,
			editAppointmentObj:null
		}
	}).state('main.add_appointmentForCalender', {
		url : '/add_appointmentForCalender',
		templateUrl : 'views/appointment/add_appointmentForCalender.html',
		controller : 'appointmentCtrl',
		params:{
			calenderScheduleObj:null
		}
			
	}).state('main.add_appoinment', {
		url : '/add_appoinment',
		templateUrl : 'views/appointment/add_appointment2.html',
		controller : 'appointmentCtrl',
		params:{
			selectScheduleObj:null,
			stDate:null,
			enddate:null
		}
			
	}).state('main.therapistAppointmentsList', {
		url : '/therapistAppointmentsList',
		templateUrl : 'views/appointment/therapistAppointmentsList.html',
		controller : 'userManagementCtrl',
		params:{
			userObj:null
		}
	})
	.state('main.calender', {
		url : '/calender',
		templateUrl : 'views/calender/calender.html',
		controller : 'calenderCtrl'
	})
	.state('main.companyname', {
		url : '/companyname',
		templateUrl : 'views/masters/companynames/Company_Names.html',
		controller : 'loginCtrl'
	}).state('main.companytype', {
		url : '/companytype',
		templateUrl : 'views/masters/companytypes/Client_TypesMaster.html',
		controller : 'loginCtrl'
	}).state('main.subscription', {
		url : '/subscription',
		templateUrl : 'views/masters/subscriptionmaster/Subscription_Master.html',
		controller : 'loginCtrl'
	}).state('main.addclienttype', {
		url : '/addclienttype',
		templateUrl : 'views/masters/clientTypes/addClientTypesMaster.html',
		controller : 'clientTypeCtrl'
	}).state('main.updateclienttype', {
		url : '/updateclienttype',
		templateUrl : 'views/masters/clientTypes/updateclienttype.html',
		controller : 'clientTypeCtrl',
		params:{
			clientTypeObj:null
		}
	}).state('main.clienttypelist', {
		url : '/clienttypelist',
		templateUrl : 'views/masters/clientTypes/clienttypelist.html',
		controller : 'clientTypeCtrl'
	}).state('main.addRole', {
		url : '/addRole',
		templateUrl : 'views/masters/role/addRole.html',
		controller : 'roleCtrl'
	}).state('main.updateRole', {
		url : '/updateRole',
		templateUrl : 'views/masters/role/updateRole.html',
		controller : 'roleCtrl',
		params:{
			roleObj:null
		}
	}).state('main.roleist', {
		url : '/roleist',
		templateUrl : 'views/masters/role/roleist.html',
		controller : 'roleCtrl'
	}).state('main.addRoleFeature', {
		url : '/addRoleFeature',
		templateUrl : 'views/masters/role/addRoleFeature.html',
		controller : 'roleCtrl'
	}).state('main.roleFeature', {
		url : '/roleFeature',
		templateUrl : 'views/masters/role/roleFeatures.html',
		controller : 'roleCtrl'
	}).state('main.viewRoleFeature', {
		url : '/viewRoleFeature',
		templateUrl : 'views/masters/role/viewRoleFeature.html',
		controller : 'roleCtrl',
		params:{
			viewRoleObj:null
		}
	}).state('main.updateRoleFeature', {
		url : '/updateRoleFeature',
		templateUrl : 'views/masters/role/updateRoleFeature.html',
		controller : 'roleCtrl',
		params:{
			updateRoleFeatureObj:null
		}
	}).state('main.addCompanyType', {
		url : '/addCompanyType',
		templateUrl : 'views/masters/companytypes/addCompanyType.html',
		controller : 'companyTypeCtrl'
	}).state('main.updateCompanyType', {
		url : '/updateCompanyType',
		templateUrl : 'views/masters/companytypes/updateCompanyType.html',
		controller : 'companyTypeCtrl',
		params:{
			companyTypeObj:null
		}
	}).state('main.companyTypesList', {
		url : '/companyTypesList',
		templateUrl : 'views/masters/companytypes/companyTypesList.html',
		controller : 'companyTypeCtrl'
			
	}).state('main.addSubscriptionMaster', {
		url : '/addSubscriptionMaster',
		templateUrl : 'views/masters/subscriptionmaster/addSubscriptionMaster.html',
		controller : 'subscriptionMasterCtrl'
	}).state('main.updateSubscriptionMaster', {
		url : '/updateSubscriptionMaster',
		templateUrl : 'views/masters/subscriptionmaster/updateSubscriptionMaster.html',
		controller : 'subscriptionMasterCtrl',
		params:{
			subscriptionObj:null
		}
	}).state('main.subscriptionMasterList', {
		url : '/subscriptionMasterList',
		templateUrl : 'views/masters/subscriptionmaster/subscriptionMasterList.html',
		controller : 'subscriptionMasterCtrl'
	
	}).state('main.addCategory', {
		url : '/addCategory',
		templateUrl : 'views/masters/category/addCategory.html',
		controller : 'categoryCtrl'
	}).state('main.updateCategory', {
		url : '/updateCategory',
		templateUrl : 'views/masters/category/updateCategory.html',
		controller : 'categoryCtrl',
		params:{
			categoryObj:null
		}
	}).state('main.categoryList', {
		url : '/categoryList',
		templateUrl : 'views/masters/category/categoryList.html',
		controller : 'categoryCtrl'
	}).state('main.addPackageSubscription', {
		url : '/addPackageSubscription',
		templateUrl : 'views/packages/packagesubscription/addPackageSubscription.html',
		controller : 'packageSubscriptionCtrl'
	}).state('main.updatePackageSubscription', {
		url : '/updatePackageSubscription',
		templateUrl : 'views/packages/packagesubscription/updatePackageSubscription.html',
		controller : 'packageSubscriptionCtrl',
		params:{
			packageSubscriptionObj:null
		}
	}).state('main.packageSubscriptionList', {
		url : '/packageSubscriptionList',
		templateUrl : 'views/packages/packagesubscription/packageSubscriptionList.html',
		controller : 'packageSubscriptionCtrl'
	}).state('main.addDepartment', {
		url : '/addDepartment',
		templateUrl : 'views/companysetup/departmentmanagement/addDepartment.html',
		controller : 'departmentCtrl'
	}).state('main.updateDepartment', {
		url : '/updateDepartment',
		templateUrl : 'views/companysetup/departmentmanagement/updateDepartment.html',
		controller : 'departmentCtrl',
		params:{
			departmentObj:null
		}
	}).state('main.departmentlist', {
		url : '/departmentlist',
		templateUrl : 'views/companysetup/departmentmanagement/departmentlist.html',
		controller : 'departmentCtrl'
	}).state('main.addPackageNameMaster', {
		url : '/addPackageNameMaster',
		templateUrl : 'views/packages/packageNameMaster/addPackageNameMaster.html',
		controller : 'packageMasterCtrl'
	}).state('main.updatepackageNameMaster', {
		url : '/updatepackageNameMaster',
		templateUrl : 'views/packages/packageNameMaster/updatepackageNameMaster.html',
		controller : 'packageMasterCtrl',
		params:{
			packageMasterObj:null
		}
	}).state('main.packagePricingList', {
		url : '/packagePricingList',
		templateUrl : 'views/packages/packagepricing/packagePricingList.html',
		controller : 'packagePricingCtrl'
	}).state('main.addPackagePricing', {
		url : '/addPackagePricing',
		templateUrl : 'views/packages/packagepricing/addPackagePricing.html',
		controller : 'packagePricingCtrl'
	}).state('main.updatePackagePricing', {
		url : '/updatepackagePricing',
		templateUrl : 'views/packages/packagepricing/updatePackagePricing.html',
		controller : 'packagePricingCtrl',
		params:{
			packagePricingObj:null
		}
	}).state('main.packageNameMasterlist', {
		url : '/packageNameMasterlist',
		templateUrl : 'views/packages/packageNameMaster/packageNameMasterList.html',
		controller : 'packageMasterCtrl'
	}).state('main.addPackageDiscount', {
		url : '/addPackageDiscount',
		templateUrl : 'views/packages/packageDiscount/addPackageDiscount.html',
		controller : 'packageDiscountCtrl'
	}).state('main.updatePackageDiscount', {
		url : '/updatePackageDiscount',
		templateUrl : 'views/packages/packageDiscount/updatePackageDiscount.html',
		controller : 'packageDiscountCtrl',
		params:{
			packageDiscountObj:null
		}
	}).state('main.packageDiscountlist', {
		url : '/packageDiscountlist',
		templateUrl : 'views/packages/packageDiscount/packageDiscountlist.html',
		controller : 'packageDiscountCtrl'
	}).state('main.add_usermanagement', {
		url : '/add_usermanagement',
		templateUrl : 'views/companysetup/usermanagement/add_usermanagement.html',
		controller : 'userManagementCtrl'
	}).state('main.add_userTherapist', {
		url : '/add_userTherapist',
		templateUrl : 'views/companysetup/usermanagement/add_userTherapist.html',
		controller : 'userManagementCtrl',
			params:{
				existingTherapistId:null
			}
	}).state('main.updateUserMangement', {
		url : '/updateUserMangement',
		templateUrl : 'views/companysetup/usermanagement/updateUserMangement.html',
		controller : 'userManagementCtrl',
		params:{
			userObj:null
		}
	}).state('main.usermanagement_list', {
		url : '/usermanagement_list',
		templateUrl : 'views/companysetup/usermanagement/usermanagement_list.html',
		controller : 'userManagementCtrl',
		params:{
				activeAdminsObject:null,
				inActiveAdminsObject:null,
				activeTherapistsObject:null,
				inActiveTherapistsObject:null,
				activeUsersOfSiteObject:null,
				inActiveUsersOfSiteObject:null,
				activeTherapistsOfSiteObject:null,
				inActiveTherapistsOfSiteObject:null,
				siteObj:null
		}
	}).state('main.evaluation', {
		url : '/evaluation',
		templateUrl : 'views/template/evaluation.html',
		controller : 'evalutionCtrl'
	}).state('main.addSite', {
		url : '/addSite',
		templateUrl : 'views/companysetup/sitemanagement/addSite.html',
		controller : 'siteCtrl'
	}).state('main.updateSite', {
		url : '/updateSite',
		templateUrl : 'views/companysetup/sitemanagement/updateSite.html',
		controller : 'siteCtrl',
		params:{
			siteObj:null
		}
	}).state('main.siteList', {
		url : '/siteList',
		templateUrl : 'views/companysetup/sitemanagement/siteList.html',
		controller : 'siteCtrl',
		params:{
			activeSitesObject:null,
			inActiveSitesObject:null
			
	}

	}).state('main.siteCompaniesOverView', {
		url : '/siteCompaniesOverView',
		templateUrl : 'views/dashboard/siteCompaniesOverView.html',
		controller : 'siteCtrl',
		params:{
			siteObj:null
		}
	/*}).state('main.siteCompaniesOverView', {
		url : '/siteCompaniesOverView',
		templateUrl : 'views/dashboard/siteCompaniesOverView.html',
		controller : 'siteCtrl',
		params:{
			adminUserName:null,
			site:null
		}*/
	}).state('main.addEvalutionCategory', {
		url : '/addEvalutionCategory',
		templateUrl : 'views/template/evalutioncategory/addEvalutionCategory.html',
		controller : 'evalutionCategoryCtrl'
	}).state('main.updateEvalutionCategory', {
		url : '/updateEvalutionCategory',
		templateUrl : 'views/template/evalutioncategory/updateEvalutionCategory.html',
		controller : 'evalutionCategoryCtrl',
		params:{
			evalutionCategoryObj:null
		}
	}).state('main.evalutionCategoryList', {
		url : '/evalutionCategoryList',
		templateUrl : 'views/template/evalutioncategory/evalutionCategoryList.html',
		controller : 'evalutionCategoryCtrl'
			
	}).state('main.addEvalutionQuestion', {
		url : '/addEvalutionQuestion',
		templateUrl : 'views/template/evalutionquestion/addEvalutionQuestion.html',
		controller : 'evalutionQuestionCtrl'
	}).state('main.updateEvalutionQuestion', {
		url : '/updateEvalutionQuestion',
		templateUrl : 'views/template/evalutionquestion/updateEvalutionQuestion.html',
		controller : 'evalutionQuestionCtrl',
		params:{
			evalutionQuestionObj:null
		}
	}).state('main.evalutionQuestionList', {
		url : '/evalutionQuestionList',
		templateUrl : 'views/template/evalutionquestion/evalutionQuestionList.html',
		controller : 'evalutionCtrl'

	}).state('main.add_therapist', {
		url : '/add_therapist',
		templateUrl : 'views/therapist/add_therapist.html',
		controller : 'therapistCtrl'
	}).state('main.updateTherapist', {
		url : '/updateTherapist',
		templateUrl : 'views/therapist/updateTherapist.html',
		controller : 'therapistCtrl',
		params:{
			therapistObj:null
		}
	}).state('main.therapist_list', {
		url : '/therapist_list',
		templateUrl : 'views/therapist/therapist_list.html',
		controller : 'therapistCtrl'
			

	}).state('main.addExpense', {
		url : '/addExpense',
		templateUrl : 'views/clinicExpenses/addExpense.html',
		controller : 'clinicExpenseTypeCtrl'
	}).state('main.updateExpense', {
		url : '/updateExpense',
		templateUrl : 'views/clinicExpenses/updateExpense.html',
		controller : 'clinicExpenseTypeCtrl',
		params:{
			clinicExpenseTypeCtrlObj:null
		}
	}).state('main.expenseList', {
		url : '/expenseList',
		templateUrl : 'views/clinicExpenses/expenseList.html',
		controller : 'clinicExpenseTypeCtrl'
	
	}).state('main.addMonthlyClinicExpenses', {
		url : '/addMonthlyClinicExpenses',
		templateUrl : 'views/clinicExpenses/MonthlyClinicExpenses/addMonthlyClinicExpenses.html',
		controller : 'clinicExpenseTypeCtrl'
	}).state('main.monthlyClinicExpenseList', {
		url : '/monthlyClinicExpenseList',
		templateUrl : 'views/clinicExpenses/MonthlyClinicExpenses/monthlyClinicExpenseList.html',
		controller : 'clinicExpenseTypeCtrl'
	}).state('main.monthlyExpenseViewMore', {
		url : '/monthlyExpenseViewMore',
		templateUrl : 'views/clinicExpenses/MonthlyClinicExpenses/monthlyExpenseViewMore.html',
		controller : 'clinicExpenseTypeCtrl',
			params:{
			monthlyExpenseObj:null
		}
	}).state('main.updateMonthlyClinicExpense', {
		url : '/updateMonthlyClinicExpense',
		templateUrl : 'views/clinicExpenses/MonthlyClinicExpenses/`	updateMonthlyClinicExpenses.html',
		controller : 'clinicExpenseTypeCtrl',
			params:{
			monthlyExpenseObj:null
		}
	
	}).state('main.addHolidays', {
		url : '/addHolidays',
		templateUrl : 'views/holidays/add_holiday.html',
		controller : 'holidaysCtrl'
	}).state('main.updateHolidays', {
		url : '/updateHolidays',
		templateUrl : 'views/holidays/updateHolidays.html',
		controller : 'holidaysCtrl',
		params:{
			holidaysObj:null
		}
	}).state('main.holidaysList', {
		url : '/holidaysList',
		templateUrl : 'views/holidays/holidaysList.html',
		controller : 'holidaysCtrl'
	}).state('main.addTherapistHolidays', {
		url : '/addTherapistHolidays',
		templateUrl : 'views/holidays/therapistHolidays.html',
		controller : 'therapistHolidaysCtrl'
	}).state('main.holidaysListAdmin', {
		url : '/holidaysListAdmin',
		templateUrl : 'views/holidays/therapistHolidaysList.html',
		controller : 'therapistHolidaysCtrl'
	}).state('main.addSchedule', {
		url : '/addSchedule',
		templateUrl : 'views/schedule/addSchedule.html',
		controller : 'scheduleCtrl'
	}).state('main.updateSchedule', {
		url : '/updateSchedule',
		templateUrl : 'views/schedule/updateSchedule.html',
		controller : 'scheduleCtrl',
		params:{
			scheduleObj:null
		}
	}).state('main.scheduleList', {
		url : '/scheduleList',
		templateUrl : 'views/schedule/scheduleList.html',
		controller : 'scheduleCtrl'
	}).state('main.addTherapistSchedule', {
		url : '/addTherapistSchedule',
		templateUrl : 'views/schedule/therapistSchedeule.html',
		controller : 'therapistScheduleCtrl'
	}).state('main.scheduleListAdmin', {
		url : '/scheduleListAdmin',
		templateUrl : 'views/schedule/therapistScheduleList.html',
		controller : 'therapistScheduleCtrl'
	}).state('main.addtemplate', {
		url : '/addtemplate',
		templateUrl : 'views/templateSheet/evalutionTemplate.html',
		controller : 'templateCtrl'
	}).state('main.templateList', {
		url : '/templateList',
		templateUrl : 'views/templateSheet/evalutionTemplate.html',
		controller : 'templateCtrl'
	}).state('main.evalutionTemplate', {
		url : '/evalutionTemplate',
		templateUrl : 'views/templateSheet/evalutionTemplate.html',
		controller : 'evalutionCtrl'
	}).state('main.goalsTemplate', {
		url : '/goalsTemplate',
		templateUrl : 'views/templateSheet/goalsTemplate.html',
		controller : 'goalTemplateCtrl',
		params:{
			Patient:null
		}
	}).state('main.evaluationTemplateCategories', {
		url : '/evaluationTemplateCategories',
		templateUrl : 'views/templateSheet/evaluationTemplateCategories.html',
		controller : 'PatientCtrl',
		params:{
			Patient:null
		}
	}).state('main.dynamicTemplate', {
		url : '/dynamicTemplate',
		templateUrl : '/views/template/dynamicTemplate/dynamicTemplate.html',
		controller : 'userManagementCtrl'
	}).state('main.updateGoalTemplate', {
		url : '/updateGoalTemplate',
		templateUrl : 'views/templateSheet/updateGoalTemplate.html',
		controller : 'goalTemplateCtrl',
		params:{
			goalObj:null
		}
	}).state('main.calender2', {
		url : '/calender2',
		templateUrl : 'views/calender/calender2.html',
		controller : 'calendarCtrl'
	}).state('main.model', {
		url : '/model',
		templateUrl : 'views/calender/modalContent.html',
		controller : 'calendarCtrl'
	}).state('main.registeredCompanies', {
		url : '/registeredCompanies',
		templateUrl : 'views/companysetup/usermanagement/registeredCompanies.html',
		controller : 'loginCtrl',
		params:{
			registeredEnterpriseCompanies:null,
			registeredIndividualCompanies:null,
			registeredCompanies:null,
			type:null
		}
	})/*.state('main.registeredCompaniesOverView', {
		url : '/registeredCompaniesOverView',
		templateUrl : 'views/dashboard/registeredCompaniesOverView.html',
		controller : 'userManagementCtrl',
		params:{
			registeredEnterpriseCompanies:null,
			registeredIndividualCompanies:null,
			registeredCompanies:null
		}
	})*/.state('main.ParentMilestones', {
		url : '/ParentMilestones',
		templateUrl : 'views/parent/ParentMilestones.html',
		controller : 'userManagementCtrl'
	})/*.state('main.ChildObservation', {
		url : '/ChildObservation',
		templateUrl : 'views/parent/ChildObservation.html',
		controller : 'userManagementCtrl'
	})*//*.state('main.ScreeningTest', {
		url : '/ScreeningTest',
		templateUrl : 'views/parent/ScreeningTest.html',
		controller : 'userManagementCtrl'
	})*/.state('main.ParentDashboard', {
		url : '/ParentDashboard',
		templateUrl : 'views/parent/dashbordParent.html/ParentDashboard.html',
		controller : 'loginCtrl'
	}).state('main.searchSchedule', {
		url : '/searchSchedule',
		templateUrl : 'views/schedule/schedulesByDate.html',
		controller : 'appointmentCtrl',
		params:{
			scheduleListByDate:null,
			stDate:null,
			enddate:null
		}
	}).state('main.payments', {
		url : '/payments',
		templateUrl : 'views/payments/payments.html',
		controller : 'PatientCtrl'
	}).state('main.mypayments', {
		url : '/mypayments',
		templateUrl : 'views/payments/mypayments.html',
		controller : 'loginCtrl'
	}).state('main.renewalpayments', {
		url : '/renewalpayments',
		templateUrl : 'views/payments/renewalpayments.html',
		controller : 'packagePricingCtrl'
	}).state('main.listofpayments', {
		url : '/listofpayments',
		templateUrl : 'views/payments/listofpayments.html',
		controller : 'userManagementCtrl'
	}).state('main.speechTheraphyTemplates', {
		url : '/speechTheraphyTemplates',
		templateUrl : 'views/template/speechTheraphyTemplates.html',
		controller : 'speechTheraphyCtrl',
		params:{
			patient:null,
			evalutionflag:null,
			exitflag:null
		}
	}).state('main.addSpeechTheraphyCategoryTemplate', {
		url : '/addSpeechTheraphyCategoryTemplate',
		templateUrl : 'views/speechTheraphyTemplateCategory/addSpeechTheraphyTemplateCategory.html',
		controller : 'speechTheraphyCategoryCtrl'
	}).state('main.updateSpeechTheraphyCategoryTemplate', {
		url : '/updateSpeechTheraphyCategoryTemplate',
		templateUrl : 'views/speechTheraphyTemplateCategory/updateSpeechTheraphyCategoryTemplate.html',
		controller : 'speechTheraphyCategoryCtrl',
		params:{
			stCategory:null
		}
	}).state('main.speechTheraphyCategoryTemplateList', {
		url : '/speechTheraphyCategoryTemplateList',
		templateUrl : 'views/speechTheraphyTemplateCategory/speechTheraphyCategoryTemplateList.html',
		controller : 'speechTheraphyCategoryCtrl'
	}).state('main.addDocumentTypeLookup', {
		url : '/addDocumentTypeLookup',
		templateUrl : 'views/documentTypeLookup/addDocumentTypeLookup.html',
		controller : 'documentTypeLookupCtrl'
	}).state('main.updateDocumentTypeLookup', {
		url : '/updateDocumentTypeLookup',
		templateUrl : 'views/documentTypeLookup/updateDocumentTypeLookup.html',
		controller : 'documentTypeLookupCtrl',
		params:{
			documentObj:null
		}
	}).state('main.documentTypeLookupList', {
		url : '/documentTypeLookupList',
		templateUrl : 'views/documentTypeLookup/documentTypeLookupList.html',
		controller : 'documentTypeLookupCtrl'
	}).state('main.addSpeechTheraphyQuestionTemplate', {
		url : '/addSpeechTheraphyQuestionTemplate',
		templateUrl : 'views/speechTheraphyTemplateQustion/addQustion.html',
		controller : 'speechTheraphyQuestionCtrl'
	}).state('main.speechTheraphyQuestionTemplateList', {
		url : '/list',
		templateUrl : 'views/speechTheraphyTemplateQustion/questionList.html',
		controller : 'speechTheraphyQuestionCtrl'
	}).state('main.updateSpeechTheraphyQuestionTemplate', {
		url : '/update',
		templateUrl : 'views/speechTheraphyTemplateQustion/updateQuestion.html',
		controller : 'speechTheraphyQuestionCtrl',
		params:{
			question:null
		}
	}).state('main.invoiceTemplate', {
		url : '/invoiceTemplate',
		templateUrl : 'views/reports/invoiceTemplate.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	}).state('main.invoiceViewForCompany', {
		url : '/invoiceViewForCompany',
		templateUrl : 'views/reports/invoiceViewForCompany.html',
		controller : 'companyCtrl'
		/*params:{
			invoiceObject:null
		}*/
	}).state('main.editInvoice', {
		url : '/editInvoice',
		templateUrl : 'views/reports/editInvoice.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null,
			dtoObjct:null
		}
	}).state('main.viewInvoice', {
		url : '/viewInvoice',
		templateUrl : 'views/reports/viewInvoice.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	}).state('main.updateInvoice', {
		url : '/updateInvoice',
		templateUrl : 'views/reports/updateInvoice.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	}).state('main.new', {
		url : '/new',
		templateUrl : 'views/reports/NewFile.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null,
			dtoObjct:null
		}
	}).state('main.addRegionalCenter', {
		url : '/addRegionalCenter',
		templateUrl : 'views/clientTypes/addRegionalCenter.html',
		controller : 'PatientCtrl'
	}).state('main.regionalCenterList', {
		url : '/regionalCenterList',
		templateUrl : 'views/clientTypes/regionalCenterList.html',
		controller : 'PatientCtrl'
	}).state('main.updateRegionalCenter', {
		url : '/updateRegionalCenter',
		templateUrl : 'views/clientTypes/updateRegionalCenter.html',
		controller : 'PatientCtrl',
		params:{
			regionObject:null
		}
	}).state('main.addTax', {
		url : '/addTax',
		templateUrl : 'views/finance/tax/addTax.html',
		controller : 'taxCtrl'
	}).state('main.taxList', {
		url : '/taxList',
		templateUrl : 'views/finance/tax/taxList.html',
		controller : 'taxCtrl'
	}).state('main.updateTax', {
		url : '/updateTax',
		templateUrl : 'views/finance/tax/updateTax.html',
		controller : 'taxCtrl',
		params:{
			taxObj:null
		}
	}).state('main.addCurrency', {
		url : '/addCurrency',
		templateUrl : 'views/finance/currency/addCurrency.html',
		controller : 'currencyCtrl'
	}).state('main.currencyList', {
		url : '/currencyList',
		templateUrl : 'views/finance/currency/currencyList.html',
		controller : 'currencyCtrl'
	}).state('main.updateCurrency', {
		url : '/updateCurrency',
		templateUrl : 'views/finance/currency/updateCurrency.html',
		controller : 'currencyCtrl',
		params:{
			currencyObj:null
		}
	}).state('main.addPaymethod', {
		url : '/addPaymethod',
		templateUrl : 'views/finance/paymethod/addPaymethod.html',
		controller : 'paymethodCtrl'
	}).state('main.paymethodList', {
		url : '/paymethodList',
		templateUrl : 'views/finance/paymethod/paymethodList.html',
		controller : 'paymethodCtrl'
	}).state('main.updatePaymethod', {
		url : '/updatePaymethod',
		templateUrl : 'views/finance/paymethod/updatePaymethod.html',
		controller : 'paymethodCtrl',
		params:{
			paymethodObj:null
		}
	}).state('main.addInsurance', {
		url : '/addInsurance',
		templateUrl : 'views/clientTypes/addInsurance.html',
		controller : 'insuranceCtrl'
	}).state('main.insuranceList', {
		url : '/insuranceList',
		templateUrl : 'views/clientTypes/insuranceList.html',
		controller : 'insuranceCtrl'
	}).state('main.updateInsurance', {
		url : '/updateInsurance',
		templateUrl : 'views/clientTypes/updateInsurance.html',
		controller : 'insuranceCtrl',
		params:{
			insuranceObj:null
		}
	}).state('main.internalDashboard', {
		url : '/internalDashboard',
		templateUrl : 'views/dashboard/internalDashboard.html',
		controller : 'loginCtrl',
		params:{
			userName:null,
			registeredCompanies:null
		}
	}).state('main.registeredCompaniesOverView', {
		url : '/registeredCompaniesOverView',
		templateUrl : 'views/dashboard/registeredCompaniesOverView.html',
		controller : 'loginCtrl',
		params:{
			userName:null,
			registeredCompanies:null
		}
	}).state('main.stripeInvoice', {
		url : '/stripeInvoice',
		templateUrl : 'views/stripe/stripeInvoices.html',
		controller : 'loginCtrl',
		params:{
			userObject:null,
			email:null,
			userName:null,
			active:null,
			activeAdminsAndSuperAdmin:null,
			inActiveAdminsAndSuperAdmin:null
		}

	}).state('main.stripeSingleInvoice', {
		url : '/stripeSingleInvoice',
		templateUrl : 'views/stripe/stripeSingleInvoice.html',
		controller : 'loginCtrl',
		params:{
			invoiceObject:null
		}

	}).state('main.internalDashboardLogs', {
		url : '/internalDashboardLogs',
		templateUrl : 'views/dashboard/internalDashboardLogs.html',
		controller : 'loginCtrl',
		params:{
			userName:null,
			active:null,
			activeAdminsAndSuperAdmin:null,
			inActiveAdminsAndSuperAdmin:null
		}
	}).state('main.internalDashboardLogsInactive', {
		url : '/internalDashboardLogsInactive',
		templateUrl : 'views/dashboard/internalDashboardLogsInactive.html',
		controller : 'loginCtrl',
		params:{
			userName:null,
			active:null,
			activeAdminsAndSuperAdmin:null,
			inActiveAdminsAndSuperAdmin:null
		}
	}).state('main.internalDashboardLogsAll', {
		url : '/internalDashboardLogsAll',
		templateUrl : 'views/dashboard/internalDashboardLogsAll.html',
		controller : 'loginCtrl',
		params:{
			userName:null,
			active:null,
			activeAdminsAndSuperAdmin:null,
			inActiveAdminsAndSuperAdmin:null
		}
	}).state('main.addRegionalCenterZone', {
		url : '/addRegionalCenterZone',
		templateUrl : 'views/masters/regionalCenter/addRegionalCenterZone.html',
		controller : 'regionalCenterZoneCtrl'
	}).state('main.regionalCenterZoneList', {
		url : '/regionalCenterZoneList',
		templateUrl : 'views/masters/regionalCenter/regionalCenterZoneList.html',
		controller : 'regionalCenterZoneCtrl'
	}).state('main.updateRegionalCenterZone', {
		url : '/updateRegionalCenterZone',
		templateUrl : 'views/masters/regionalCenter/updateRegionalCenterZone.html',
		controller : 'regionalCenterZoneCtrl',
		params:{
			obj:null
		}
	}).state('main.addRegionalCenterLookup', {
		url : '/addRegionalCenterLookup',
		templateUrl : 'views/masters/regionalCenter/addRegionalCenterLookup.html',
		controller : 'regionalCenterLookupCtrl'
	}).state('main.regionalCenterLookupList', {
		url : '/regionalCenterLookupList',
		templateUrl : 'views/masters/regionalCenter/regionalCenterLookupList.html',
		controller : 'regionalCenterLookupCtrl'
	}).state('main.updateRegionalCenterLookup', {
		url : '/updateRegionalCenterLookup',
		templateUrl : 'views/masters/regionalCenter/updateRegionalCenterLookup.html',
		controller : 'regionalCenterLookupCtrl',
		params:{
			obj:null
		}
	}).state('main.insuranceLookupList', {
		url : '/insuranceLookupList',
		templateUrl : 'views/insuranceLookup/InsuranceLookupList.html',
		controller : 'insuranceLookupCtrl'
	}).state('main.addInsuranceLookup', {
		url : '/addInsuranceLookup',
		templateUrl : 'views/insuranceLookup/addInsuranceLookup.html',
		controller : 'insuranceLookupCtrl'
	}).state('main.updateInsuranceLookup', {
		url : '/updateInsuranceLookup',
		templateUrl : 'views/insuranceLookup/updateInsuranceLookup.html',
		controller : 'insuranceLookupCtrl',
		params:{
			insLookup:null
		}
	}).state('main.addCategoryType', {
		url : '/addCategoryType',
		templateUrl : 'views/masters/categorytype/addCategoryType.html',
		controller : 'categoryTypeCtrl'
	}).state('main.updateCategoryType', {
		url : '/updateCategoryType',
		templateUrl : 'views/masters/categorytype/updateCategoryType.html',
		controller : 'categoryTypeCtrl',
		params:{
			categoryTypeObj:null
		}
	}).state('main.categoryTypeList', {
		url : '/categoryTypeList',
		templateUrl : 'views/masters/categorytype/categoryTypeList.html',
		controller : 'categoryTypeCtrl'
	}).state('main.addSchool', {
		url : '/addSchool',
		templateUrl : 'views/school/addSchool.html',
		controller : 'schoolCtrl'
	}).state('main.updateSchool', {
		url : '/updateSchool',
		templateUrl : 'views/school/updateSchool.html',
		controller : 'schoolCtrl',
		params:{
			schoolObj:null
		}
	}).state('main.schoolList', {
		url : '/schoolList',
		templateUrl : 'views/school/schoolList.html',
		controller : 'schoolCtrl'

	}).state('main.help', {
		url : '/help',
		templateUrl : 'views/account/login/help.html',
		controller : 'accordionCtrl'
	}).state('main.CaretakerCategoryLookup', {
		url : '/CaretakerCategoryLookup',
		templateUrl : 'views/parentTemplate/careTaker/careTakerCategoryLookup/addCaretakerCategoryLookup.html',
		controller : 'careTakerCategoryLookupCtrl'
	}).state('main.updateCareTakerCategory', {
		url : '/updateCareTakerCategory',
		templateUrl : 'views/parentTemplate/careTaker/careTakerCategoryLookup/updateCareTakerCategory.html',
		controller : 'careTakerCategoryLookupCtrl',
		params:{
			categoryObj:null
		}
	}).state('main.CareTakerQuestionLookup', {
		url : '/CareTakerQuestionLookup',
		templateUrl : 'views/parentTemplate/careTaker/careTakerQuestionLookup/addCareTakerQuestionLookup.html',
		controller : 'careTakerQuestionLookupCtrl'
	}).state('main.updateCareTakerQuestionLookup', {
		url : '/updateCareTakerQuestionLookup',
		templateUrl : 'views/parentTemplate/careTaker/careTakerQuestionLookup/updateCareTakerQuestionLookup.html',
		controller : 'careTakerQuestionLookupCtrl',
		params:{
			questionObj:null,
			answerObj:null
		}
	}).state('main.careTakerQuestionLookup_list', {
		url : '/careTakerQuestionLookup_list',
		templateUrl : 'views/parentTemplate/careTaker/careTakerQuestionLookup/careTakerQuestionLookup_list.html',
		controller : 'careTakerQuestionLookupCtrl'
	}).state('main.careTakerMilestones', {
		url : '/careTakerMilestones',
		templateUrl : 'views/parent/careTakerMilestones.html',
		controller : 'caretakerMilestonesCtrl'
	}).state('main.careTakerMilestones2', {
		url : '/careTakerMilestones2',
		templateUrl : 'views/parent/careTakerMilestones2.html',
		controller : 'caretakerMilestonesCtrl'
	}).state('main.addChildObservationCategoryLookup', {
		url : '/addChildObservationCategoryLookup',
		templateUrl : 'views/parentTemplate/childObservation/childObservationCategory/addChildObservationCategoryLookup.html',
		controller : 'childObservationCategoryLookupCtrl'
	}).state('main.childObservationCategoryLookuplist', {
		url : '/childObservationCategoryLookuplist',
		templateUrl : 'views/parentTemplate/childObservation/childObservationCategory/ChildObservationCategoryLookuplist.html',
		controller : 'childObservationCategoryLookupCtrl'
	}).state('main.updateChildObservationCategoryLookup', {
		url : '/updateChildObservationCategoryLookup',
		templateUrl : 'views/parentTemplate/childObservation/childObservationCategory/updateChildObservationCategoryLookup.html',
		controller : 'childObservationCategoryLookupCtrl',
		params:{
			childObservationCategoryLookupObject:null
		}
	}).state('main.addChildObservationQuestionLookup', {
		url : '/addChildObservationQuestionLookup',
		templateUrl : 'views/parentTemplate/childObservation/childObservationQuestion/addChildObservationQuestionLookup.html',
		controller : 'childObservationQuestionLookupCtrl'
	}).state('main.childObservationQuestionLookuplist', {
		url : '/childObservationQuestionLookuplist',
		templateUrl : 'views/parentTemplate/childObservation/childObservationQuestion/ChildObservationQuestionLookuplist.html',
		controller : 'childObservationQuestionLookupCtrl'
	}).state('main.updateChildObservationQuestionLookup', {
		url : '/updateChildObservationQuestionLookup',
		templateUrl : 'views/parentTemplate/childObservation/childObservationQuestion/updateChildObservationQuestionLookup.html',
		controller : 'childObservationQuestionLookupCtrl',
		params:{
			childObservationQuestionLookupObject:null,
			answerObject:null
		}
	}).state('main.ChildObservation', {
		url : '/ChildObservation',
		templateUrl : 'views/parent/ChildObservation.html',
		controller : 'childObservationQuestionLookupCtrl',
			params:{
				naniObj:null
			}	
	})/*.state('main.ChildObsevation', {
		url : '/ChildObsevation',
		templateUrl : 'views/parent/ChildObservation2.html',
		controller : 'childObservationQuestionLookupCtrl'
	})*/.state('main.addScreeningTest', {
		url : '/addScreeningTest',
		templateUrl : 'views/parentTemplate/screeningTest/addScreeningTest.html',
		controller : 'screeningTestLookupCtrl'
	}).state('main.updateScreeningTest', {
		url : '/updateScreeningTest',
		templateUrl : 'views/parentTemplate/screeningTest/updateScreeningTest.html',
		controller : 'screeningTestLookupCtrl',
		params:{
			screeningTestObj:null
		}
	}).state('main.addScreeningTestQuestion', {
		url : '/addScreeningTestQuestion',
		templateUrl : 'views/parentTemplate/screeningTest/addScreeningTestQuestion.html',
		controller : 'screeningTestQuestionCtrl'
	}).state('main.updateScreeningTestQuestion', {
		url : '/updateScreeningTestQuestion',
		templateUrl : 'views/parentTemplate/screeningTest/updateScreeningTestQuestion.html',
		controller : 'screeningTestQuestionCtrl',
		params:{
			screeningTestQuestionObj:null,
			screeningAnswer:null
		}
	}).state('main.ScreeningTest', {
		url : '/ScreeningTest',
		templateUrl : 'views/parent/ScreeningTest.html',
		controller : 'screeningTestCtrl'
	}).state('main.addScreeningTestQuestionList', {
		url : '/addScreeningTestQuestionList',
		templateUrl : 'views/parentTemplate/screeningTest/screeningTestQuestionList.html',
		controller : 'screeningTestQuestionCtrl'
	}).state('main.addCsbsCategory', {
		url : '/addCsbsCategory',
		templateUrl : 'views/parentTemplate/csbs/addCsbs.html',
		controller : 'csbsLookupCtrl'
	}).state('main.updateCsbsCategory', {
		url : '/updateCsbsCategory',
		templateUrl : 'views/parentTemplate/csbs/updateCsbs.html',
		controller : 'csbsLookupCtrl',
		params:{
			csbsObj:null
		}
	}).state('main.addCsbsQuestion', {
		url : '/addCsbsQuestion',
		templateUrl : 'views/parentTemplate/csbs/addCsbsQuestion.html',
		controller : 'csbsQuestionCtrl'
	}).state('main.updateCsbsQuestion', {
		url : '/updateCsbsQuestion',
		templateUrl : 'views/parentTemplate/csbs/updateCsbsQuestion.html',
		controller : 'csbsQuestionCtrl',
		params:{
			csbsQuestionObj:null,
			screeningAnswer:null
		}
	}).state('main.listCsbsQuestion', {
		url : '/listCsbsQuestion',
		templateUrl : 'views/parentTemplate/csbs/csbsQuestionList.html',
		controller : 'csbsQuestionCtrl'
	}).state('main.addCsbsTemplate', {
		url : '/addCsbsTemplate',
		templateUrl : 'views/parent/csbsTemplate.html',
		controller : 'csbsQuestionCtrl'
	}).state('main.updateMchartLookup', {
		url : '/updateMchartLookup',
		templateUrl : 'views/parentTemplate/Mchart/updateMchartLookup.html',
		controller : 'mchartLookupCtrl',
		params:{
			mchartLookupObj:null
		}
	}).state('main.listMchartLookup', {
		url : '/listMchartLookup',
		templateUrl : 'views/parentTemplate/Mchart/mchartLookupList.html',
		controller : 'mchartLookupCtrl'
	}).state('main.mchart', {
		url : '/mchart',
		templateUrl : 'views/parent/mchart.html',
		controller : 'mchartCtrl',
		params:{
			mchartObj:null
		}
	}).state('main.addNichqParentCategoryLookup',{
		url :'/addNichqParentCategoryLookup',
		templateUrl: 'views/NICHQParent/addNichqParentCategoryLookup.html',
		controller : 'NichqCategoryLookupCtrl'
	}).state('main.updateNichqCategoryLookup',{
		url :'/updateNichqCategoryLookup',
		templateUrl: 'views/NICHQParent/updateNichqCategoryLookup.html',
		controller : 'NichqCategoryLookupCtrl',
		params : {
			nichqParentCategoryLookupObject: null
		}
	}).state('main.addNichqParentQuestionLookup', {
		url : '/addNichqParentQuestionLookup',
		templateUrl : 'views/NICHQParent/addNichqParentQuestionLookup.html',
		controller : 'NichqQuestionLookupCtrl'
	}).state('main.updateNichqQuestionLookup', {
		url : '/updateNichqQuestionLookup',
		templateUrl : 'views/NICHQParent/updateNichqQuestionLookup.html',
		controller : 'NichqQuestionLookupCtrl',
		params:{
			updateNichqParentQuestionLookupObj: null
		}
	}).state('main.nichqParentQuestionLookupList', {
		url : '/nichqParentQuestionLookupList',
		templateUrl : 'views/NICHQParent/nichqParentQuestionLookupList.html',
		controller : 'NichqQuestionLookupCtrl'
	}).state('main.addNichqParentCategory', {
		url : '/addNichqParentCategory',
		templateUrl : 'views/parentTemplate/nichq/addNichqParentCategory.html',
		controller : 'nichqParentGeneralCtrl'
	}).state('main.updateNichqParentCategory', {
		url : '/updateNichqParentCategory',
		templateUrl : 'views/parentTemplate/nichq/updateNichqParentCategory.html',
		controller : 'nichqParentGeneralCtrl',
		params:{
			nichqObj:null
		}
	}).state('main.addNichqTemplate', {
		url : '/addNichqTemplate',
		templateUrl : 'views/NICHQParent/nichqTemplate.html',
		controller : 'nichqTemplateCtrl'
	}).state('main.MentalCluster', {
		url : '/MentalCluster',
		templateUrl : 'views/Cluster/MentalCluster.html',
		controller : 'MentalClusterCtrl'
	}).state('main.UpdateMentalCluster', {
		url : '/UpdateMentalCluster',
		templateUrl : 'views/Cluster/updateMentalCluster.html',
		controller : 'MentalClusterCtrl',
		params:{
			mentalClusterObj:null
		}
	}).state('main.MentalScale', {
		url : '/MentalScale',
		templateUrl : 'views/Scale/MentalScale.html',
		controller : 'MentalScaleCtrl'
	}).state('main.list_MentalScale', {
		url : '/list_MentalScale',
		templateUrl : 'views/Scale/list_MentalScale.html',
		controller : 'MentalScaleCtrl'
	}).state('main.updateMentalScale', {
		url : '/updateMentalScale',
		templateUrl : 'views/Scale/updateMentalScale.html',
		controller : 'MentalScaleCtrl',
		params:{
			mentalScaleObj:null
		}
	}).state('main.VSMSQuestion', {
		url : '/VSMSQuestion',
		templateUrl : 'views/vsmsQuestion/VSMSQuestion.html',
		controller : 'vsmsQuestionCtrl'
	}).state('main.vsmsQuestionList', {
		url : '/vsmsQuestionList',
		templateUrl : 'views/vsmsQuestion/vsmsQuestionList.html',
		controller : 'vsmsQuestionCtrl'
	}).state('main.updateVSMSQuestion', {
		url : '/updateVSMSQuestion',
		templateUrl : 'views/Scale/updateVSMSQuestion.html',
		controller : 'vsmsQuestionCtrl',
		params:{
			vsmsScaleObj:null
		}
	}).state('main.ClusterAndScale', {
		url : '/ClusterAndScale',
		templateUrl : 'views/Scale/ClusterAndScale.html',
		controller : 'MentalScaleCtrl'
	}).state('main.addMotorCluster', {
		url : '/addMotorCluster',
		templateUrl : 'views/motorCluster/addMotorCluster.html',
		controller : 'motorClusterCtrl'
	}).state('main.motorClusterList', {
		url : '/motorClusterList',
		templateUrl : 'views/motorCluster/motorClusterList.html',
		controller : 'motorClusterCtrl'
	}).state('main.updateMotorCluster', {
		url : '/updateMotorCluster',
		templateUrl : 'views/motorCluster/updateMotorCluster.html',
		controller : 'motorClusterCtrl',
		params:{
			motocluster:null
		}
	}).state('main.addVsmsCluster', {
		url : '/addVsmsCluster',
		templateUrl : 'views/vsmsCluster/addVsmsCluster.html',
		controller : 'vsmsClusterCtrl'
	}).state('main.vsmsClusterList', {
		url : '/vsmsClusterList',
		templateUrl : 'views/vsmsCluster/vsmsClusterList.html',
		controller : 'vsmsClusterCtrl'
	}).state('main.updateVsmsCluster', {
		url : '/updateVsmsCluster',
		templateUrl : 'views/vsmsCluster/updateVsmsCluster.html',
		controller : 'vsmsClusterCtrl',
		params:{
			motocluster:null
		}
	}).state('main.addMotorScale', {
		url : '/addMotorScale',
		templateUrl : 'views/motorScale/addMotorScale.html',
		controller : 'motorScaleCtrl'
	}).state('main.motorScaleList', {
		url : '/motorScaleList',
		templateUrl : 'views/motorScale/motorScaleList.html',
		controller : 'motorScaleCtrl'
	}).state('main.updateMotorScale', {
		url : '/updateMotorScale',
		templateUrl : 'views/motorScale/updateMotorScale.html',
		controller : 'motorScaleCtrl',
		params:{
			motoScale:null
		}
	}).state('main.assignMotorScalesToCluster', {
		url : '/assignMotorScalesToCluster',
		templateUrl : 'views/motorScale/assignMotorScalestoCluster.html',
		controller : 'motorScaleCtrl'
	}).state('main.mental_Motor_Template', {
		url : '/mental_Motor_Template',
		templateUrl : 'views/templateSheet/mental-motor.html',
		controller : 'mentalMotorCtrl',
		params:{
			Patient:null
		}
	}).state('main.mentalReportGraph', {
		url : '/mentalReportGraph',
		templateUrl : 'views/patient/mentalReportGraph.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	}).state('main.vsmsReportGraph', {
		url : '/vsmsReportGraph',
		templateUrl : 'views/patient/vsmsReportGraph.html',
		controller : 'vsmsQuestionCtrl',
		params:{
			patientObject:null
		}
	}).state('main.addTeacherInfoCategoryLookup', {
		url : '/addTeacherInfoCategoryLookup',
		templateUrl : "views/autisumTemplate/teacherInfo/addTeacherInfoCategoryLookup.html",
		controller : 'nichqTeacherCategoryLoockupCtrl'
	}).state('main.addTeacherQuestionLookup', {
		url : '/addTeacherQuestionLookup',
		templateUrl : "views/autisumTemplate/teacherInfo/addTeacherQuestionLookup.html",
		controller : 'nichqTeacherQuestionLookupCtrl'
	}).state('main.teacherQuestionLookupList', {
		url : '/teacherQuestionLookupList',
		templateUrl : "views/autisumTemplate/teacherInfo/teacherQuestionLookupList.html",
		controller : 'nichqTeacherQuestionLookupCtrl'
	}).state('main.updateTeacherQuestionLookup', {
		url : '/updateTeacherQuestionLookup',
		templateUrl : "views/autisumTemplate/teacherInfo/updateTeacherQuestionLookup.html",
		controller : 'nichqTeacherQuestionLookupCtrl',
		params:{
			updateTeacher:null
		}
	}).state('main.ParentInfo', {
		url : '/ParentInfo',
		templateUrl : 'views/autisumTemplate/ParentInfo.html',
		controller : 'PatientCtrl'
	}).state('main.M-Chat', {
		url : '/M-Chat',
		templateUrl : 'views/autisumTemplate/M-Chat.html',
		controller : 'PatientCtrl'
	}).state('main.behavioural', {
		url : '/behavioural',
		templateUrl : 'views/autisumTemplate/behavioural.html',
		controller : 'loginCtrl'
	}).state('main.add_consulting', {
		url : '/add_consulting',
		templateUrl : 'views/consulting/add_consulting.html',
		controller : 'loginCtrl'
	}).state('main.M-ChatReportTemplate', {
		url : '/M-ChatReportTemplate',
		templateUrl : 'views/autisumTemplate/M-ChatReportTemplate.html',
		controller : 'PatientCtrl',
        params:{
        	mchatPatient:null
		}
	}).state('main.addIsaaBehaviourCategoryLookUP', {  // here state for add IsaabehaviourCategorylookup
		url : '/addIsaaBehaviourCategoryLookUP',
		templateUrl : 'views/isaaBehaviourLookup/addIsaaBehaviourCategoryLookUP.html',
		controller : 'isaaBehaviourlookUPCtrl'
	}).state('main.updateIsaaBehaviourLookUp', { // here state for update IsaabehaviourCategorylookup
		url : '/updateIsaaBehaviourLookUp',
		templateUrl : 'views/isaaBehaviourLookup/updateIsaaBehaviourCategoryLookUp.html',
		controller : 'isaaBehaviourlookUPCtrl',
		params:{
			updateCategoryLookUp1:null
		}
	}).state('main.addIsaaBehaviourQuestionLookup', {
		url : '/addIsaaBehaviourQuestionLookup',
		templateUrl : 'views/isaaBehaviourLookup/addIsaaBehaviourQuestionLookup.html',
		controller : 'isaaBehaviourQuestionLookupCtrl'
	}).state('main.updateIsaaBehaviourQuestionLookup', {
		url : '/updateIsaaBehaviourQuestionLookup',
		templateUrl : 'views/isaaBehaviourLookup/updateIsaaBehaviourQuestionLookup.html',
		controller : 'isaaBehaviourQuestionLookupCtrl',
		params:{
			updateisaaBehaviour : null
		}
	}).state('main.isaaBehaviourQuestionLookupList', {
		url : '/isaaBehaviourQuestionLookupList',
		templateUrl : 'views/isaaBehaviourLookup/isaaBehaviourQuestionLookupList.html',
		controller : 'isaaBehaviourQuestionLookupCtrl'
	}).state('main.addSpeechEvalutionTemplate', {
		url : '/addSpeechEvalutionTemplate',
		templateUrl : 'views/speechEvalutionTemplate/addSpeechEvalutionTemplate.html',
		controller : 'SpeechTherapyEvalutionCtrl'
	}).state('main.physioTherapyEvalutionTemplate', {
		url : '/physioTherapyEvalutionTemplate',
		templateUrl : 'views/physioTherapyEvalutionTemplate/physioTherapyEvalutionTemplate.html',
		controller : 'SpeechTherapyEvalutionCtrl'
	}).state('main.addPsychologyTemplate', {
		url : '/addPsychologyTemplate',
		templateUrl : 'views/psychologyBMPTemplate/addPsychologyTemplate.html',
		controller : 'SpeechTherapyEvalutionCtrl'
	}).state('main.addGenericTemplate', {
		url : '/addGenericTemplate',
		templateUrl : 'views/genericTemplate/addGenericTemplate.html',
		controller : 'SpeechTherapyEvalutionCtrl'
	}).state('main.genericCategoryTemplate', {
		url : '/genericCategoryTemplate',
		templateUrl : 'views/genericTemplate/genericCategoryTemplate.html',
		controller : 'SpeechTherapyEvalutionCtrl'
	}).state('main.genericQuestionTemplate', {
		url : '/genericQuestionTemplate',
		templateUrl : 'views/genericTemplate/genericQuestionTemplate.html',
		controller : 'SpeechTherapyEvalutionCtrl'
	}).state('main.updateTeacherCategoryLookup', {
		url : '/updateTeacherCategoryLookup',
		templateUrl : 'views/autisumTemplate/teacherInfo/updateTeacherCategoryLookup.html',
		controller : 'nichqTeacherCategoryLoockupCtrl',
		params:{
			updatednichqTeacherCategoryLookup:null
		}
	}).state('main.mchatReport', {
		url : '/mchatReport',
		templateUrl : 'views/patient/reports/mchatreport.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	}).state('main.departmentOfPhysiptherapy', {
		url : '/departmentOfPhysiptherapy',
		templateUrl : 'views/autisumTemplate/physiotherapy/departmentOfPhysiptherapy.html',
		controller : 'PatientCtrl'
	}).state('main.speechEvaluate', {
		url : '/speechEvaluate',
		templateUrl : 'views/autisumTemplate/speechevaluate/speechEvaluate.html',
		controller : 'SpeechTherapyEvalutionCtrl'
	}).state('main.goalCategory', {
		url : '/goalCategory',
		templateUrl : 'views/autisumTemplate/goal/goalCategory.html',
		controller : 'goalTemplateCtrl'
	}).state('main.nichqParentReport', {
		url : '/nichqParentReport',
		templateUrl : 'views/patient/reports/nichqParentReport.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	}).state('main.isaaBeahaviourReport', {
		url : '/isaaBeahaviourReport',
		templateUrl : 'views/patient/reports/isaaBehaviourReport.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	}).state('main.nichqTeacherReport', {
		url : '/nichqTeacherReport',
		templateUrl : 'views/patient/reports/nichqTeacherReport.html',
		controller : 'PatientCtrl',
		params:{
			patientObject:null
		}
	})
	.state('main.separationprocess', {
		url : '/separationprocess',
		templateUrl : 'views/patient/separationprocess.html',
		controller : 'PatientCtrl',
	}).state('main.addSubCategory', {
		url : '/addSubCategory',
		templateUrl : 'views/masters/subcategory/addSubCategory.html',
		controller : 'subCategoryCtrl'
	}).state('main.subCategoryList', {
		url : '/subCategoryList',
		templateUrl : 'views/masters/subcategory/subCategoryList.html',
		controller : 'subCategoryCtrl'
	}).state('main.updateSubCategory', {
		url : '/updateSubCategory',
		templateUrl : 'views/masters/subcategory/updateSubCategory.html',
		controller : 'subCategoryCtrl',
		params:{
			subcategoryObj:null
		}
	}).state('main.addPatientRegType', {
		url : '/addPatientRegType',
		templateUrl : 'views/masters/patientregtype/addPatientRegType.html',
		controller : 'patientRegTypeCtrl'
	}).state('main.patientRegTypeList', {
		url : '/patientRegTypeList',
		templateUrl : 'views/masters/patientregtype/patientRegTypeList.html',
		controller : 'patientRegTypeCtrl'
	}).state('main.updatePatientRegType', {
		url : '/updatePatientRegType',
		templateUrl : 'views/masters/patientregtype/updatePatientRegType.html',
		controller : 'patientRegTypeCtrl',
		params:{
			patientRegTypeObj:null
		}
	});
	
	
	
	
}).run(function($rootScope, $localStorage, $location) {
	$rootScope.$on("$locationChangeStart", function(event, next, current) {
		if ($localStorage.user == undefined) {
			$location.path('/login');
		}
		if ($rootScope.loggedUsername == undefined || $rootScope.loggedUserRole == undefined) {
			$location.path('/login');
		}
	});
});
angular.module('HealthApplication').config(['$compileProvider', function($compileProvider) { $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|fil‌​e|ftp|blob):|data:im‌​age\//); } ]); 
