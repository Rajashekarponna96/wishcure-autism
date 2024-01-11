package com.openspace.HospitalMgnt.common;

public class RestURIConstants {

	public static final String USER_REGISTRATION = "/userRegistration";
	public static final String ISEMAILEXISTS = "/isEmailExists/{email:.+}";
	public static final String ISCOMPANYEXISTS = "/isCompanyExists/{company}";
	public static final String ISMOBILENUMBEREXISTS = "/ismobileNumberExists/{mobileNumber}";
	public static final String ISNPINUMBEREXISTS = "/isNpiNumberExists/{npi}";
	public static final String ISUCINUMBEREXISTS = "/isUCINumberExists/{uci}";
	public static final String ISSSNNUMBEREXISTS = "/isSSNNumberExists/{ssn}";
	public static final String REGISTEREDCOMPANIES = "/registeredCompanies/{registrationType}";
	public static final String REGISTEREDCOMPANIES_STRIPE = "/registeredCompaniesStripe/{registrationType}";
	public static final String REGISTEREDCOMPANIES_PAGE = "/registeredCompaniesPage/{registrationType}";
	public static final String GET_REGISTERED_COMPANIES_FILTER= "/getRegisteredCompaniesFilter";
	
	public static final String USER_LOGIN = "/userLogin";
	public static final String VALIDATE_OTP= "/validateOTP";
	public static final String RESEND_OTP= "/resndOTP/{adminUserName:.+}";
	public static final String OTPEXPIRETIME= "/otpExpireTime/{adminUserName:.+}";
	
	public static final String USER_FORGOTPASSWORD = "/userForgotPassword/{email:.+}";
	public static final String CHANGEPASSWORD = "/changePassword";

	public static final String GET_ALL_COUNTRIES = "/getAllCountries";
	public static final String UPDATE_COUNTRY = "/updateCountry";
	public static final String GET_COUNTRY = "/getCountry/{id}";
	public static final String GET_COUNTRY_BY_ISDCODE = "/getCountrys";
	public static final String GET_PATIENTS_BY_DATE = "/getPatientsByDate";

	
	
	public static final String GET_ALL_STATES = "/getAllStates";
	public static final String GET_ALL_STATES_BY_COUNTRYID = "/getAllStatesBy/{companyId}";
	public static final String GET_ONE_STATES_BY_ID = "/getStateBy/{stateId}";
	

	public static final String GET_ALL_CITIES = "/getAllCities";
	public static final String GET_ALL_CITIES_BY_STATEID = "/getAllCitiesBy/{stateId}";

	public static final String GET_ALL_LOCATIONS = "/getAllLocations";
	public static final String GET_ALL_LOCATIONS_BY_CITYID = "/getAllLocationsBy/{cityId}";

	public static final String SAVE_COMPANY = "/saveCompany";
	public static final String GET_ALL_COMPANIES = "/getAllComanies";
	public static final String GET_ALL_COMPANIES_PAGE = "/getAllComaniesPage";
	public static final String UPDATE_COMPANY = "/updateCompany";
	public static final String DELETE_COMPANY = "/deleteCompany/{id}";
	public static final String GET_COMPANY_USER = "/getCompanyUser/{username:.+}";
	public static final String UPDATE_COMPANY_USER = "/updateCompanyUser";
	public static final String GET_STRIPE_INVOICE = "/getStripeInvoice/{username:.+}";
	
	public static final String SAVE_COMPANY_TYPE = "/saveCompanyType";
	public static final String GET_ALL_COMPANY_TYPES = "/getAllCompanyTypes";
	public static final String GET_ALL_COMPANY_TYPES_LIST = "/getAllCompanyTypesList";
	public static final String UPDATE_COMPANY_TYPE = "/updateCompanyType";
	public static final String DELETE_COMPANY_TYPE = "/deleteCompanyType/{id}";


	public static final String SAVE_SITE = "/saveSite";
	public static final String GET_ALL_SITES = "/getAllSites";
	public static final String UPDATE_SITE = "/updateSite";
	public static final String DELETE_SITE = "/deleteSite/{id}";
	public static final String GET_ALL_SITES_BY_COMPANYID = "/getAllSitesByCompany/{companyId}";
	public static final String GET_ALL_SITES_BY_COMPANYUSERNAME = "/getAllSitesByCmpanyUserName/{name:.+}";
	public static final String GET_ALL_SITES_BY_COMPANYUSERNAME_AND_STATUS = "/getAllSitesByCmpanyUserNameAndStatus/{name:.+}";
	public static final String GET_ALL_SITES_BY_COMPANYUSERNAME_PAGE = "/getAllSitesByCmpanyUserNamePage/{name:.+}";
	public static final String GET_ALL_SITES_PAGE_BY_SUPERADMIN_COMPANY = "/getAllSitesByCompanyIdAndStatus/{adminUsername:.+}/{roleName}/{active}";
	public static final String GET_ALL_ACTIVE_SITES_BY_COMPANY_USERNAME = "/getAllActiveSitesByCompanyUserName/{adminUsername:.+}";
	public static final String GET_ALL_INACTIVE_SITES_BY_COMPANY_USERNAME = "/getAllInActiveSitesByCompanyUserName/{adminUsername:.+}";
	
	public static final String SAVE_DEPARTMENT = "/saveDepartmet";
	public static final String GET_ALL_DEPARTMENTS = "/getAllDepartmets/{username:.+}";
	public static final String GET_ALL_DEPARTMENTS_SEARCH = "/getAllDepartmetsSearch/{username:.+}/{search}";
	public static final String GET_ALL_DEPARTMENTS_LIST = "/getAllDepartmetsList";
	public static final String GET_ALL_DEPARTMENTS_LIST_BY_COMPANY= "/getAllDepartmetsListByCompany/{username:.+}";
	public static final String UPDATE_DEPARTMENT = "/updateDepartmet";
	public static final String DELETE_DEPARTMENT = "/deleteDepartmet/{id}";
	public static final String GET_ALL_DEPARTMENTS_BY_SITE = "/getAllDepartmets/{siteId}";
	public static final String GET_ALL_DEPARTMENTS_BY_ROLE = "/getAllDepartmetsByRole/{roleId}";
	public static final String GET_ALL_DEPARTMENTS_LIST_BY_COMPANY_AND_ROLE= "/getAllDepartmetsListByCompanyAndRole/{username:.+}/{roleid}";
	
	
	public static final String SAVE_ROLE = "/saveRole";
	public static final String GET_ALL_ROLES = "/getAllRoles";
	public static final String UPDATE_ROLE = "/updateRole";
	public static final String DELETE_ROLE = "/deleteRole/{id}";
	public static final String GET_ALL_ROLES_BASEDON_STATUS = "/getAllRolesByStatus";
	public static final String ASSIGN_FEATURETO_ROLE = "/assigFeaturesToRole";
	public static final String UPDATE_ROLE_FEATURE = "/updateRoleFeatures";
	
	public static final String SAVE_PERMISSION = "/savePermission";
	public static final String GET_ALL_PERMISSIONS = "/getAllPermissions";
	public static final String UPDATE_PERMISSION = "/updatePermission";
	public static final String DELETE_PERMISSION = "/deletePermission/{id}";
	public static final String GET_ALL_PERMISSIONS_BY_ROLE = "/getAllPermissionsByRole/{roleId}";
	
	public static final String SAVE_PERSON = "/savePerson";
	public static final String SAVE_USER = "/saveUser";
	public static final String ADD_USER_THERAPIST = "/addUserTherapist";
	public static final String GET_ALL_PERSONS = "/getAllPersons/{adminUsername:.+}";
	public static final String GET_ALL_PERSONS_PAGE = "/getAllPersonsPage/{adminUsername:.+}";
	public static final String GET_ALL_PERSONS_PAGE_BY_LOGIN_USER = "/getAllPersonsByLoginUser/{adminUsername:.+}";
	public static final String GET_ALL_ACTIVE_USERS_BY_LOGIN_USERS = "/getAllActiveUsersByLoginUser/{adminUsername:.+}/{active}";
	public static final String GET_ALL_PERSONS_PAGE_SEARCH = "/getAllPersonsPageSearch/{adminUsername:.+}/{search}";
	public static final String GET_ALL_PERSONS_PAGE_BY_SUPERADMIN_ADMIN = "/getAllPersonsBySuperAdminAndAdmin/{adminUsername:.+}/{roleName}/{active}";
	public static final String GET_ALL_PERSONS_PAGE_BY_THERAPIST = "/getAllPersonsByTherapists/{adminUsername:.+}/{roleName}/{active}";
	public static final String UPDATE_PERSON = "/updatePerson/{adminUserName:.+}";
	public static final String DELETE_PERSON = "/deletePerson/{id}/{adminUserName:.+}";
	public static final String GET_ALL_REGISTRATIONS = "/getAllRegistrations/{adminUsername:.+}";
	public static final String GET_ALL_ACTIVE_ADMINS = "/getAllActiveAdmins/{adminUsername:.+}";
	public static final String GET_ALL_INACTIVE_ADMINS = "/getAllInActiveAdmins/{adminUsername:.+}";
	public static final String GET_ALL_ACTIVE_SITEADMIN = "/getAllActiveSiteAdmins/{adminUsername:.+}";
	public static final String GET_ALL_INACTIVE_SITEADMIN = "/getAllInActiveSiteAdmins/{adminUsername:.+}";
	public static final String GETONEPERSON = "/person/{id}";
	public static final String GET_ALL_USERS_BY_SUPERADMIN_AND_ADMIN = "/getAllusersBySuperAdminAndAdmin/{adminUserName:.+}/{roleName}/{active}";
	public static final String ACTIVE="/activeUser";
	public static final String GET_ALL_ACTIVE_PERSONS_PAGE_BY_REG_TYPE= "/getAllActiveAndInactivePersonsByRegType/{adminUsername:.+}";
	public static final String GET_ALL_INACTIVE_PERSONS_PAGE_BY_REG_TYPE= "/getAllInActivePersonsByRegType/{adminUsername:.+}";
	public static final String GET_ALL_INACTIVE_PERSONS_COUNT_BY_COMPANY= "/getInactivePersonsCount/{adminUsername:.+}";
	public static final String GET_ALL_ACTIVE_PERSONS_COUNT_BY_COMPANY= "/getActivePersonsCount/{adminUsername:.+}";
	public static final String GET_ALL_ACTIVE_PERSONS_COUNT_BY_COMPANY_AND_SITE= "/getActivePersonsCountByCompanyAndSite/{adminUsername:.+}";
	
	public static final String SAVE_PACKAGE_MASTER = "/savePackageMaster";
	public static final String GET_ALL_PACKAGE_MASTERS = "/getAllPackageMastersPage";
	public static final String GET_ALL_PACKAGE_MASTERS_LIST = "/getAllPackageMastersList";
	public static final String UPDATE_PACKAGE_MASTER= "/updatePackageMaster";
	public static final String DELETE_PACKAGE_MASTER = "/deletePackageMaster/{id}";
	
	public static final String SAVE_PACKAGE_PRICE_MASTER = "/savePackagePriceMaster";
	public static final String GET_ALL_PACKAGE_PRICE_MASTERS = "/getAllPackagePriceMasters";
	public static final String UPDATE_PACKAGE_PRICE_MASTER= "/updatePackagePriceMaster";
	public static final String DELETE_PACKAGE_PRICE_MASTER = "/deletePackagePriceMaster/{id}";
	public static final String GET_PACKAGE_PRICE_MASTER = "/getPackagePriceMaster/{id}";
	public static final String GET_PACKAGE_PRICE_MASTER_INFO = "/getPackagePriceMasterInfo/{subscriptionName}/{packageName}";
	
	
	public static final String SAVE_PACKAGE_DISCOUNT = "/savePackageDiscount";
	public static final String GET_ALL_PACKAGE_DISCOOUNTS = "/getAllPackageDiscounts";
	public static final String UPDATE_PACKAGE_DISCOUNT= "/updatePackageDiscount";
	public static final String DELETE_PACKAGE_DISCOUNT = "/deletePackageDiscount/{id}";
	
	public static final String SAVE_PACKAGE_SUBSCRIPTION = "/savePackageSubscription";
	public static final String GET_ALL_PACKAGE_SUBSCRIPTIONS = "/getAllPackageSubscriptions";
	public static final String UPDATE_PACKAGE_SUBSCRIPTION= "/updatePackageSubscription";
	public static final String DELETE_PACKAGE_SUBSCRIPTION= "/deletePackageSubscription/{id}";

	public static final String SAVE_CLIENT_TYPE= "/saveClientType";
	public static final String GET_ALL_CLIENT_TYPES = "/getAllClientTypes";
	public static final String GET_ALL_CLIENT_TYPES_LIST = "/getAllClientTypesList";
	public static final String UPDATE_CLIENT_TYPE= "/updateClientType";
	public static final String DELETE_CLIENT_TYPE= "/deleteClientType/{id}";
	
	public static final String SAVE_SUBSCRIPTION_MASTER= "/saveSubscriptionMaster";
	public static final String GET_ALL_SUBSCRIPTION_MASTERS = "/getAllSubscriptionMasters";
	public static final String GET_ALL_SUBSCRIPTION_MASTERS_LIST = "/getAllSubscriptionMastersList";
	public static final String UPDATE_SUBSCRIPTION_MASTER= "/updateSubscriptionMaster";
	public static final String DELETE_SUBSCRIPTION_MASTER= "/deleteSubscriptionMaster/{id}";
	
	public static final String SAVE_CATEGORY= "/saveCategory";
	public static final String GET_ALL_CATEGORYS = "/getAllCategorys";
	public static final String UPDATE_CATEGORY= "/updateCategory";
	public static final String DELETE_CATEGORY= "/deleteCategory/{id}";
	
	public static final String SAVE_SUB_CATEGORY= "/saveSubCategory";
	public static final String GET_ALL_SUB_CATEGORYS= "/getAllSubCategorys";
	public static final String UPDATE_SUB_CATEGORY= "/subCategory/update";
	public static final String DELETE_SUB_CATEGORY= "/deleteSubCategory/{id}";
	public static final String GET_ALL_SUB_CATEGORYS_BY_CATEGORY_TYPE = "/getAllSubCategorysByCategoryType/{id}";
	
	
	
	
	
	public static final String SAVE_EVALUTION_CATEGORY= "/saveEvalutionCategory";
	public static final String GET_ALL_EVALUTION_CATEGORYS = "/getAllEvalutionCategorys";
	public static final String UPDATE_EVALUTION_CATEGORY= "/updateEvalutionCategory";
	public static final String DELETE_EVALUTION_CATEGORY= "/deleteEvalutionCategory/{id}";
	

	public static final String SAVE_EVALUTION_QUESTION= "/saveEvalutionQuestion";
	public static final String GET_ALL_EVALUTION_QUESTIONS = "/getAllEvalutionQuestions";
	public static final String UPDATE_EVALUTION_QUESTION= "/updateEvalutionQuestion";
	public static final String DELETE_EVALUTION_QUESTION= "/deleteEvalutionQuestion/{id}";
	public static final String GET_ALL_EVALUTION_QUESTIONS_BY_CATEGORYID= "/getAllEvalutionQuestionsByCategory/{categoryId}";
	
	public static final String SAVE_THERAPIST = "/saveTherapist";
	public static final String GET_ALL_THERAPISTS = "/getAllTherapists/{adminUsername:.+}";
	public static final String UPDATE_THERAPIST = "/updateTherapist";
	public static final String DELETE_THERAPIST = "/deleteTherapist/{id}";
	public static final String GET_THERAPIST_BY_USERNAME = "/getTherapistByUsername/{adminUsername:.+}";
	public static final String GET_ALL_ACTIVE_THERAPISTS = "/getAllActiveTherapists/{adminUsername:.+}";
	public static final String GET_ALL_INACTIVE_THERAPISTS = "/getAllInActiveTherapists/{adminUsername:.+}";
	public static final String GET_ALL_THERAPISTS_BY_DEPARTMENT = "/getAllTherapistsByDepartment/{id}";
	public static final String GET_ALL_THERAPISTS_BY_ADMINUSERNAME= "/getAlltherapistsByAdminUsername/{adminUsername:.+}";
	
	public static final String SAVE_APPOINTMENT = "/saveAppointment";
	public static final String UPDATE_APPOINTMENT = "/updateAppointment";
	public static final String DELETE_APPOINTMENT = "/deleteAppointmentById/{id}";
	public static final String GET_APPOINTS_COUNT_BY_ROLE = "/getAllAppointmentsCountByRole/{adminUserName:.+}";
	public static final String GET_APPOINTS_BY_ROLE = "/getAppointmentssByRole/{adminUserName:.+}";
	public static final String GET_APPOINTS_BY_ROLE_PAGE = "/getAppointmentssByRoleByPagination/{adminUserName:.+}";
	public static final String GET_APPOINTSS_BY_ROLE_PAGE = "/getAppointmentssByAdminByPagination/{adminUserName:.+}";
	public static final String GET_APPOINTMENT_LIST_BY_BETWEEN_DATES = "/getAppointmentListBetweenDates";
	public static final String GET_SUB_APPOINTS_BY_ROLE = "/getAllSubAppointmentssByRole/{adminUserName:.+}";
	public static final String GET_SUB_APPOINTS_BY_ROLE_FOR_CALENDARVIEW = "/getAllSubAppointmentssByRoleforCalendarView/{adminUserName:.+}";
	public static final String GET_TODAY_SUB_APPOINTS = "/getTodaySubAppointments/{adminUserName:.+}";
	public static final String COMPLETE_SUBAPPOINTMENTS = "/completeSubappointments";
	public static final String UPDATE_SUBAPPOINTMENT_STATUS = "/updateSubappointmentStatus/{id}/{status}";
	public static final String GET_SUBAPPOINTMENTS_BY_INVOICE = "/getSubAppointmentsByInvoice";
	public static final String GET_SUBAPPOINTMENTS_BY_INVOICE_PAGE = "/getSubAppointmentsByInvoicePage";
	public static final String GET_ALL_SUBAPPOINTMENTS_BY_INVOICE= "/getAllSubAppointmentsByInvoice/{adminUserName:.+}";
	public static final String GET_ALL_SUBAPPOINTMENTS_BY_INVOICE_PAGE= "/getAllSubAppointmentsByInvoicePage/{adminUserName:.+}";
	public static final String GET_ALL_SUBAPPOINTMENTS_BY_APPOINTMENT= "/getAllSubAppointmentsByAppointment/{id}";
	public static final String GET_SUBAPPOINTMENTS_BY_INVOICENO= "/getSubAppointmentsByInvoiceNo";
	public static final String FIND_ALL_THERAPIST_APPOINTMENTS= "/findAllTherapistAppointments/{id}";
	public static final String UPDATE_APPOINTMENT_WITH_ASSIGNEDTHERAPIST= "/updateAppointmentWithAssignedTherapist/";
	
	
	public static final String SAVE_SCHEDULE = "/saveSchedule";
	public static final String GET_ALL_SCHEDULES = "/getAllSchedules";
	public static final String UPDATE_SCHEDULE = "/updateSchedule";
	public static final String DELETE_SCHEDULE = "/deleteSchedule/{id}";
	public static final String GET_ALL_SCHEDULES_BY_DOCTOR_USERNAME_PAGE = "/getAllSchedulesByDoctorUsernameByPagination/{doctorUsername:.+}";
	public static final String GET_ALL_SCHEDULES_BY_DOCTOR_USERNAME = "/getAllSchedulesByDoctorUsername/{doctorUsername:.+}";
	public static final String GET_ALL_SCHEDULES_BY_ROLE = "/getAllSchedulesByRole/{adminUsername:.+}";
	public static final String GET_ALL_SCHEDULES_BY_ADMIN_USERNAME_PAGE = "/getAllSchedulesByAdminUsernamePage/{adminUsername:.+}";
	public static final String FIND_DATE = "/findDate";
	public static final String TODAY_DATE = "/getschedulesBytoday";
	public static final String GET_SCHEDULES_BY_AVAILABLEDAYS = "/getschedulesAvailableDays/{availableDay}/{id}";
	public static final String GET_SCHEDULES_BY_DAY = "/getschedulesByDay";
	public static final String SEARCH_SCHEDULES_BY_DAY = "/searchSchedulesByDay";
	public static final String SEARCH_SCHEDULES_BY_DAY1 = "/searchSchedulesByDay1";
	public static final String GET_SCHEDULED_HOURS = "/getScheduledHours";
	
	
	public static final String SAVE_HOLIDAYS= "/saveHolidays";
	public static final String GET_ALL_HOLIDAYS = "/getAllHolidays";
	public static final String UPDATE_HOLIDAYS= "/updateHolidays";
	public static final String DELETE_HOLIDAYS= "/deleteHolidays/{id}";
	public static final String GET_ALL_HOLIDAYS_BY_DOCTOR_USERNAME = "/getAllHolidaysByDoctorUsername/{doctorUsername:.+}";
	public static final String GET_ALL_HOLIDAYS_BY_DOCTOR_USERNAME_PAGE = "/getAllHolidaysByDoctorUsernameByPagination/{doctorUsername:.+}";
	public static final String GET_ALL_HOLIDAYS_BY_ADMIN_USERNAME = "/getAllHolidaysByAdminUsername/{adminUsername:.+}";
	public static final String GET_ALL_HOLIDAYS_BY_ADMIN_USERNAME_PAGE ="/getAllHolidaysByAdminUsernamePage/{adminUsername:.+}";
	public static final String GET_ALL_HOLIDAYS_BY_ROLE ="/getAllHolidaysByRole/{adminUsername:.+}";
	
	
	public static final String SAVE_QUESTION_CATEGORY= "/saveQuestionCategory";
	public static final String GET_ALL_QUESTION_CATEGORYS = "/getAllQuestionCategorys";
	public static final String GET_ALL_QUESTION_CATEGORYS_BY_PATIENT_ID = "/getAllCategorysByPatientId/{patientId}";
	public static final String GET_ALL_LIST_QUESTION_CATEGORYS = "/getAllListQuestionCategorys";
	public static final String UPDATE_QUESTION_CATEGORY= "/updateQuestionCategory";
	public static final String DELETE_QUESTION_CATEGORY= "/deleteQuestionCategory/{id}";
	public static final String FIND_ALL_QUESTION_CATEGORYS = "/findAllQuestionCategorys";
	
	
	
	public static final String SAVE_QUESTION= "/saveQuestion";
	public static final String GET_ALL_QUESTION = "/getAllQuestions";
	public static final String UPDATE_QUESTION= "/updateQuestion";
	public static final String DELETE_QUESTION= "/deleteQuestion/{id}";
	public static final String GET_QUESTION= "/getQuestion/{id}";
	
	public static final String SAVE_GOAL_CATEGORY_LOOKUP= "/saveGoalCategoryLookup";
	public static final String GET_ALL_GOAL_CATEGORY_LOOKUP = "/getAllGoalCategoryLookups";
	public static final String UPDATE_GOAL_CATEGORY_LOOKUP= "/updateGoalCategoryLookup";
	public static final String DELETE_GOAL_CATEGORY_LOOKUP= "/deleteGoalCategoryLookup/{id}";
	public static final String GET_GOAL_CATEGORY_LOOKUP= "/getGoalCategoryLookup/{id}";
	

	public static final String SAVE_GOAL_TEMPLATE_LOOKUP= "/saveGoalTemplateLookup";
	public static final String GET_ALL_GOAL_TEMPLATE_LOOKUP = "/getAllGoalTemplateLookups";
	public static final String DELETE_GOAL_TEMPLATE_LOOKUP= "/deleteGoalTemplateLookup/{id}";
	public static final String UPDATE_GOAL_TEMPLATE_LOOKUP= "/updateGoalTemplateLookup";
	public static final String GET_GOAL_TEMPLATE_LOOKUP= "/getGoalTemplateLookup/{id}";
	public static final String GET_ALL_GOAL_TEMPLATE_LOOKUP_BY_PATIENT_ID = "/getAllGoalTemplateLookupsByPatient/{patientId}";
	public static final String GET_GOAL_TEMPLATE_LOOKUPS_BY_STATUS = "/getAllGoalTemplateLookupsByStatus/{status}";
	public static final String ASSIGN_GOAL_LOOKUPS_TO_PATIENT = "/assignGoalsToPatientById/{patientId}";
	
	
	public static final String SAVE_GOAL_TEMPLATE= "/saveGoalTemplate";
	public static final String GET_ALL_GOAL_TEMPLATE = "/getAllGoalTemplates";
	public static final String UPDATE_GOAL_TEMPLATE= "/updateGoalTemplate";
	public static final String DELETE_GOAL_TEMPLATE= "/deleteGoalTemplate/{id}";
	
	
	
	public static final String SAVE_EVALUTION_TEMPLATE= "/saveEvalutionTemplate";
	public static final String GET_ALL_EVALUTION_TEMPLATE = "/getAllEvalutionTemplate";
	public static final String UPDATE_EVALUTION_TEMPLATE= "/updateEvalutionTemplate";
	public static final String DELETE_EVALUTION_TEMPLATE= "/deleteEvalutionTemplate/{id}";
	
	
	public static final String SAVE_EVALUTION_SHEET= "/saveEvalutionSheet";
	public static final String GET_ALL_EVALUTION_SHEET = "/getAllEvalutionSheet";
	public static final String UPDATE_EVALUTION_SHEET= "/updateEvalutionSheet";
	public static final String DELETE_EVALUTION_SHEET= "/deleteEvalutionSheet/{id}";
	public static final String GET_EVALUTION_SHEET_BY_PATIENTID_STATUS= "/getEvalutionSheetBy/{patientId}/status/{status}";	
	public static final String GET_EVALUTION_SHEET_BY_PATIENTID_STATUS_INPROGRESSSHEET= "/getProgressSheetBy/{patientId}";	
	
	

	public static final String SAVE_ANSWER= "/saveAnswer";
	public static final String GET_ALL_ANSWERS = "/getAllAnswer";
	public static final String UPDATE_ANSWER= "/updateAnswer";
	public static final String DELETE_ANSWER= "/deleteAnswer/{id}";  
	public static final String GET_ALL_ANSWERS_BYQUESTION_ID= "/getAllAnswersByQuestionId/{questionId}";
	public static final String SAVE_PHYSIO_EVAL_CATEGORY= "/saveCategory";
	
	public static final String GET_ALL_DEPARTMENTS_LIST_BY_REGISTARATION_TYPE = "/getAllDeptByRegistrationtype/{regTypeId}/{userName:.+}";
	
	
	
	
	
	
	
	
	
	
	

}
