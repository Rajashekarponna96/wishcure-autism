package com.openspace.Model.Util;

import java.io.Serializable;
import java.util.ResourceBundle;

public interface ErrorMessageHandler extends Serializable {
	
	ResourceBundle errorBundle = ResourceBundle.getBundle("error");
	
	public final String productOwner = errorBundle.getString("PRODUCT_OWNER");
	
	public final String superAdmin = errorBundle.getString("SUPER_ADMIN");
	
	public final String superAdminDoesNotExists = errorBundle.getString("SUPER_ADMIN_DOES_NOT_EXISTS");
	
	public final String departmentNotFound = errorBundle.getString("DEPARTMENT_NOT_FOUND");
	
	public final String userDoesNotExists = errorBundle.getString("USER_DOES_NOT_EXISTS");
	
	public final String pleaseEnterAnotherEmailId = errorBundle.getString("PLEASE_ENTER_ANOTHER_EMAILID");
	
	public final String pleaseSelectPaymentModeAndSessions = errorBundle.getString("PLEASE_SELECT_PAYMENTMODE_AND_NOOFSESSIONS");
	
	public final String individualNotExists = errorBundle.getString("INDIVIDUAL_NOT_EXISTS");
	
	public final String regionalCenterNotFound = errorBundle.getString("REGIONAL_CENTER_NOT_FOUND");
	
	public final String companyDoesNotExists = errorBundle.getString("COMPANY_DOES_NOT_EXISTS");
	
	public final String companyAlreadyExists = errorBundle.getString("COMPANY_ALREADY_EXISTS");
	
	public final String patientDoesNotExists = errorBundle.getString("PATIENT_DOES_NOT_EXISTS");
	
	public final String departmentExists = errorBundle.getString("DEPARTMENT_EXISTS");	
	
	public final String categoryTypeAlreadyExists = errorBundle.getString("CATEGORY_TYPE_ALREADY_EXISTS");
	
	public final String categoryTypeDoesNotExists = errorBundle.getString("CATEGORY_TYPE_DOES_NOT_EXISTS");
	
	public final String currencyAlreadyExists = errorBundle.getString("CURRENCY_ALREADY_EXISTS");
	
	public final String expenseTypeAlreadyExists = errorBundle.getString("EXPENSE_TYPE_ALREADY_EXISTS");
	
	public final String currencyDoesNotExists = errorBundle.getString("CURRENCY_DOES_NOT_EXISTS");
	
	public final String documentLookupALreadyExists = errorBundle.getString("DOCUMENTTYPE_LOOKUP_ALREADY_EXISTS");
	
	public final String documentLookupDoesNotExists = errorBundle.getString("DOCUMENTTYPE_LOOKUP_DOES_NOT_EXISTS");
	
	public final String folderExistsForThisPatient = errorBundle.getString("FOLDER_ALREADY_EXISTS_FOR_THIS_PATIENT");
	
	public final String folderDoesNotExists = errorBundle.getString("FOLDER_DOES_NOT_EXISTS");
	
	public final String insurancLookupAlreadyExists = errorBundle.getString("INSURANCELOOKUP_ALREADY_EXISTS");
	
	public final String insurancLookupDoesNotExists = errorBundle.getString("INSURANCELOOKUP_DOES_NOT_EXISTS");
	
	public final String insuranceNameAlreadyExists = errorBundle.getString("INSURANCE_NAME_ALREADY_EXISTS");
	
	public final String insuranceNameDoesNotExists = errorBundle.getString("INSURANCE_NAME_DOES_NOT_EXISTS");
	
	public final String paymentHasNotBeenMadeYet = errorBundle.getString("PAYMENT_HAS_NOT_BEEN_MADE_YET");
	
	public final String paymentDetailsALreadyExists = errorBundle.getString("PAYMENT_DETAILS_ALREADY_EXISTS");
	
	public final String sorrypaymentDetailsDoesNotExists = errorBundle.getString("SORRY_PAYMENT_DETAILS_DOES_NOT_EXISTS");
	
	public final String regionalCenterLookupAlreadyExists = errorBundle.getString("REGIONAL_CENTER_LOOKUP_ALREADY_EXISTS");
	
	public final String regionalCenterDoesNotExists = errorBundle.getString("REGIONAL_CENTER_DOES_NOT_EXISTS");
	
	public final String regionalCenterExists = errorBundle.getString("REGIONAL_CENTER_EXISTS");
	
	public final String schoolNameAlreadyExists = errorBundle.getString("SCHOOL_NAME_ALREADY_EXISTS");
	
	public final String schoolDoesNotExists = errorBundle.getString("SCHOOL_DOES_NOT_EXISTS");
	
	public final String speechTempleteCategoryAlreadyExists = errorBundle.getString("SPEECH_TEMPLATE_CATEGORY_ALREADY_EXISTS");
	
	public final String speechTempleteCategoryDoesNotExists = errorBundle.getString("SPEECH_TEMPLATE_CATEGORY_DOES_NOT_ALREADY_EXISTS");
	
	public final String speechTemplateQuestionAlreadyExists = errorBundle.getString("SPEECH_TEMPLATE_QUESTION_ALREADY_EXISTS");
	
	public final String speechTemplateQuestionDoesNotExists = errorBundle.getString("SPEECH_TEMPLATE_QUESTION_DOESN'T_EXISTS");
	
	public final String roleDoesNotExists = errorBundle.getString("ROLE_DOES_NOT_EXISTS");
	
	public final String taxAlreadyExists = errorBundle.getString("TAX_ALREADY_EXISTS");
	
	public final String taxDoesNotExists = errorBundle.getString("TAX_DOES_NOT_EXISTS");
	
	public final String transactionDetailsDoesNotExists = errorBundle.getString("TRANSACTION_DETAILS_DOES_NOT_EXISTS");
	
	public final String categoryOrModuleAlreadyExists = errorBundle.getString("CATEGORY_OR_MODULE_ALREADY_EXISTS");
	
	public final String categoryOrModuleDoesNotExists = errorBundle.getString("CATEGORY_OR_MODULE_DOES_NOT_EXISTS");
	
	public final String clientTypeAlreadyExists = errorBundle.getString("CLIENT_TYPE_ALREADY_EXISTS");
	
	public final String clientTypeDoesNotExists = errorBundle.getString("CLIENT_TYPE_DOES_NOT_EXISTS");
	
	public final String enterAValidUsername = errorBundle.getString("ENTER_A_VALID_USERNAME");
	
	public final String passwordMustMatch = errorBundle.getString("PASSWORD_MUST_MATCH");
	
	public final String passwordMustBeDifferent = errorBundle.getString("PASSWORD_MUST_BE_DIFFERENT");
	
	public final String enterYourCurrentPassword = errorBundle.getString("ENTER_YOUR_CORRECT_OLD_PASSWORD");
	
	public final String pleaseEnterYourRegisteredMailId = errorBundle.getString("PLEASE_ENTER_YOUR_REGISTERED_MAIL_ID");
	
	public final String inactiveUserCanNotBeLoggedIn = errorBundle.getString("INACTIVE_USER_CANNOT_BE_LOGGED_IN");
	
	public final String inactiveCompanyUserCanNotBeLoggedIn = errorBundle.getString("INACTIVE_COMPANY_USER_CANNOT_BE_LOGGED_IN");
	
	public final String invalidPassword = errorBundle.getString("INVALID_PASSWORD");
	
	public final String invalidOtp = errorBundle.getString("INVALID_OTP");
	
	public final String permissionTypeAlreadyExists = errorBundle.getString("PERMISSION_TYPE_ALREADY_EXISTS");
	
	public final String permissionTypeDoesNotExists = errorBundle.getString("PERMISSION_TYPE_DOES_NOT_EXISTS");
	
	public final String adminTherapistDoesNotExists = errorBundle.getString("ADMIN_THERAPIST_DOES_NOT_EXISTS");
	
	public final String personDoesNotExists = errorBundle.getString("PERSON_DOES_NOT_EXISTS");
	
	public final String pleaseAssignAppointmentsToAnotherTherapistBeforeInactivating = errorBundle.getString("PLEASE_ASSIGN_APPOINTMENTS_TO_ANOTHER_THERAPIST_BEFORE_INACTIVATING_THIS_THERAPIST");
	
	public final String siteAdminDoesNotExists = errorBundle.getString("SITE_ADMIN_DOES_NOT_EXISTS");
	
	public final String pleaseAssignAppointmentsToAnotherTherapistBeforeDeleting = errorBundle.getString("PLEASE_ASSIGN_APPOINTMENTS_TO_ANOTHER_THERAPIST_BEFORE_DELETING_THIS_THERAPIST");
	
	public final String userAlreadyExists = errorBundle.getString("USER_ALREADY_EXISTS");
	
	public final String yourCompanyIsInactivated = errorBundle.getString("YOUR_COMPANY_IS_INACTIVATED");
	
	public final String pleaseSelectAnImage = errorBundle.getString("PLEASE_SELECT_AN_IMAGE");
	
	public final String invalidUsername = errorBundle.getString("INVALID_USERNAME");
	
	public final String userAlreadyRegistered = errorBundle.getString("USER_ALREADY_REGISTERED");
	
	public final String pleaseSelectStartDate = errorBundle.getString("PLEASE_SELECT_START_DATE");
	
	public final String pleaseSelectEndDate = errorBundle.getString("PLEASE_SELECT_END_DATE");
	
	public final String roleAlreadyExists = errorBundle.getString("ROLE_ALREADY_EXISTS");
	
	public final String featureAssignedAlreadyToRole = errorBundle.getString("FEATURES_ASSIGNED_ALREADY_TO_ROLE");
	
	public final String companyNameAlreadyExists = errorBundle.getString("COMAPNY_NAME_ALREADY_EXISTS");
	
	public final String companyTypeAlreadyExists = errorBundle.getString("COMPANY_TYPE_ALREADY_EXISTS");
	
	public final String companyTypeDoesNotExists = errorBundle.getString("COMPANY_TYPE_DOES_NOT_EXISTS");
	
	public final String evalauationCategoryAlreadyExists = errorBundle.getString("EVALUATION_CATEGORY_ALREADY_EXISTS");
	
	public final String evalauationCategoryDoesNotExists = errorBundle.getString("EVALUATION_CATEGORY_DOES_NOT_EXISTS");
	
	public final String evaluationQuestionAlreadyExists = errorBundle.getString("EVALUATION_QUESTION_ALREADY_EXISTS");
	
	public final String evaluationQuestionDoesNotExists = errorBundle.getString("EVALUATION_QUESTION_DOES_NOT_EXISTS");
	
	public final String packageDiscountAlreadyExists = errorBundle.getString("PACKAGE_DISCOUNT_ALREADY_EXISTS");
	
	public final String packageDiscountDoesNotExists = errorBundle.getString("PACKAGE_DISCOUNT_DOES_NOT_EXISTS_TO_UPDATE");
	
	public final String packageDiscountNotAvailableToDelete = errorBundle.getString("PACKAGE_DISCOUNT_NOT_AVAILABLE_TO_DELETE");
	
	public final String packageNameAlreadyExists = errorBundle.getString("PACKAGE_NAME_ALREADY_EXISTS");
	
	public final String packageNameDoesNotExists = errorBundle.getString("PACKAGE_NAME_DOES_NOT_EXISTS");
	
	public final String combinationOfPackagenameAndSubscriptionnameAlreadyExists = errorBundle.getString("COMBINATION_OF_PACKAGENAME_AND_SUBSCRIPTIONNAME_ALREADY_EXISTS");
	
	public final String packagePriceMasternameDoesNotExists = errorBundle.getString("PACKAGE_PRICE_MASTERNAME_DOES_NOT_EXISTS");
	
	public final String questionAlreadyExists = errorBundle.getString("QUESTION_ALREADY_EXISTS");
	
	public final String questionDoesNotExists = errorBundle.getString("QUESTION_DOES_NOT_EXISTS");
	
	public final String questionCategoryDoesNotExists = errorBundle.getString("QUESTION_CATEGORY_DOES_NOT_EXISTS");
	
	public final String questionCategoryAlreadyExists = errorBundle.getString("QUESTION_CATEGORY_ALREADY_EXISTS");
	
	public final String patientNotFound = errorBundle.getString("PATIENT_NOT_FOUND");
	
	public final String doctorDoesNotExists = errorBundle.getString("DOCTOR_DOES_NOT_EXISTS");
	
	public final String appointedSchedulesCannotBeDeleted = errorBundle.getString("APPOINTED_SCHEDULES_CANNOT_BE_DELETED");
	
	public final String scheduleCannotBeCreatedDuringTheseHours = errorBundle.getString("SCHEDULE_CANNOT_BE_CREATED_DURING_THESE_HOURS");
	
	public final String endtimeAndStartTimeShouldNotBeEqual = errorBundle.getString("END_TIME_AND_START_TIME_SHOULD_NOT_BE_EQUAL");
	
	public final String endtimeShouldBeGreaterthanStarttime = errorBundle.getString("END_TIME_SHOULD_BE_GREATER_THAN_START_TIME");
	
	public final String pleaseSelectStartDateAndTime = errorBundle.getString("PLEASE_SELECT_START_DATE_AND_TIME");
	
	public final String adminDoesNotExists = errorBundle.getString("ADMIN_DOES_NOT_EXISTS_");
	
	public final String pleaseSelectFromtime = errorBundle.getString("PLEASE_SELECT_FROMTIME");
	
	public final String pleaseSelectTotime = errorBundle.getString("PLEASE_SELECT_TOTIME");
	
	public final String pleaseSelectEndtime = errorBundle.getString("PLEASE_SELECT_END_DATE");
	
	public final String endtimeShouldNotEqualToFromtime = errorBundle.getString("ENDTIME_SHOULD_NOT_EQUAL_TO_FROMTIME");
	
	public final String endtimeShouldBeGreaterthanFromtime = errorBundle.getString("ENDTIME_SHOULD_BE_GREATER_THAN_FROMTIME");
	
	public final String pleaseUpdateTheStatusOfPatientAppointments = errorBundle.getString("PLEASE_UPDATE_THE_STATUS_OF_PATIENTS_PREVIOUS_APPOINTMENTS_WITH_COMPLETE_CANCEL");
	
	public final String startdateShouldBeLessthanEnddate = errorBundle.getString("STARTDATE_SHOULD_BE_LESSTHAN_ENDDATE");
	
	public final String therapistDoesNotExists = errorBundle.getString("THERAPIST_DOES_NOT_EXISTS");
	
	public final String schedulesAreNotAvailableOnThisTimings = errorBundle.getString("SCHEDULES_ARE_NOT_AVAILABLE_ON_THIS_TIMINGS");
	
	public final String noTherapistAvailableDuringTheseHours = errorBundle.getString("NO_THERAPIST_AVAILABLE_DURING_THESE_HOURS");
	
	public final String yourSelectedDateShouldBeGreaterthanPresentSystemDate = errorBundle.getString("YOUR_SELECTED_DATE_SHOULD_BE_GREATER_THAN_PRESENT_SYSTEM_DATE");
	
	public final String yourSelectedTimeShouldBeGreaterthanPresentSystemTime = errorBundle.getString("YOUR_SELECTED_TIME_SHOULD_BE_GREATER_THAN_PRESENT_SYSTEM_TIME");
	
	public final String pleaseStarttimeAndEndtime = errorBundle.getString("PLEASE_STARTTIME_AND_ENDTIME");
	
	public final String scheduleDoesNotExists = errorBundle.getString("SCHEDULE_DOES_NOT_EXISTS");
	
	public final String yourSelectedStarttimeShouldBeLessthanScheduledEndtime = errorBundle.getString("YOUR_SELECTED_STARTTIME_SHOULD_BE_LESSTHAN_SCHEDULED_ENDTIME");
	
	public final String yourSelectedEndtimeShouldBeLessthanScheduledEndtime = errorBundle.getString("YOUR_SELECTED_ENDTIME_SHOULD_BE_LESSTHAN_SCHEDULED_ENDTIME");
	
	public final String yourSelectedStarttimeShouldBeGreaterthanScheduledStartime = errorBundle.getString("YOUR_SELECTED_STARTTIME_SHOULD_BE_GREATERTHAN_SCHEDULED_STARTTIME");
	
	public final String starttimeShouldbeLessthanTheEndtime = errorBundle.getString("STARTTIME_SHOULD_BE_LESSTHAN_THE_ENDTIME");
	
	public final String starttimeAndEndtimeShouldNotBeEqual = errorBundle.getString("STARTTIME_AND_ENDTIME_SHOULD_NOT_BE_EQUAL");
	
	public final String siteNameAlreadyExists = errorBundle.getString("SITE_NAME_ALREADY_EXISTS");
	
	final String siteDoesNotExists = errorBundle.getString("SITE_DOES_NOT_EXISTS");
	
	public final String siteAlreadyExists = errorBundle.getString("SITE_ALREADY_EXISTS");
	
	public final String siteCanootBeCreatedBecauseCompanyDoesNotExists = errorBundle.getString("SITE_CANNOT_BE_CREATED_BECAUSE_COMPANY_DOES_NOT_EXISTS");
	
	public final String goalCategoryAlreadyExists = errorBundle.getString("GOAL_CATEGORY_ALREADY_EXISTS");
	
	public final String goalCategoryDoesNotExists = errorBundle.getString("GOAL_CATEGORY_DOES_NOT_EXISTS");
	
	public final String goalLookupAlreadyExists = errorBundle.getString("GOAL_LOOKUP_ALREADY_EXISTS");
	
	public final String goalLookupDoesNotExists = errorBundle.getString("GOAL_LOOKUP_DOES_NOT_EXISTS");
	
	public final String pleaseEnterGoalName = errorBundle.getString("PLEASE_ENTER_GOAL_NAME");
	
	public final String goalTempplateLookupAlreadyExists = errorBundle.getString("GOAL_TEMPLATE_LOOKUP_ALREADY_EXISTS");
	
	public final String pleaeSelectAPatient = errorBundle.getString("PLEASE_SELECT_A_PATIENT");
	
	public final String enttimeAndStarttimeShouldNotEqual = errorBundle.getString("ENDTIME_AND_START_TIME_SHOULD_NOT_EQUAL");
	
	public final String endtimeShouldBeGreaterThanStarttime = errorBundle.getString("END_TIME_SHOULD_BE_GREATER_THAN_START_TIME");
	
	public final String itsAHolidayOnThisDate = errorBundle.getString("ITS_A_HOLIDAY_ON_THIS_DATE");
	
	public final String pleaseSelectAValidAvailableStarttime = errorBundle.getString("PLEASE_SELECT_A_VALID_AVAILABLE_STARTTIME");
	
	public final String appointmentDoesNotExists = errorBundle.getString("APPOINTMENT_DOES_NOT_EXISTS");
	
	public final String subscriptionMasterAlreadyExists = errorBundle.getString("SUBSCRIPTION_MASTER_ALREADY_EXISTS");
	
	public final String subscriptionMasterDoesNotExists = errorBundle.getString("SUBSCRIPTION_MASTER_DOES_NOT_EXISTS");
	
	public final String patientDoesNotHaveAModeOfPayment = errorBundle.getString("PATIENT_DOES_NOT_HAVE_A_MODE_OF_PAYMENT");
	
	public final String holidayDoesNotExists = errorBundle.getString("HOLIDAY_DOES_NOT_EXISTS");
	
	public final String yourSelectedStartdateShouldBeLessthanEnddate = errorBundle.getString("YOUR_SELECTED_STARTDATE_SHOULD_BE_LESS_THAN_ENDDATE");
	
	public final String sorryAppointmentAlreadyScheduledOnThisDate = errorBundle.getString("SORRY_APPOINTMENT_ALREADY_SCHEDULED_ON_THIS_DATE");
	
	public final String pleaseSelectTitle = errorBundle.getString("PLEASE_SELECT_TITLE");
	
	public final String departmentAssignedToTherapistCannotBeDeleted = errorBundle.getString("DEPARTMENT_IS_ASSIGNED_TO_THERAPIST_CANNOT_BE_DELETED");
	
	public final String departmentAssignedToPatientCannotBeDeleted = errorBundle.getString("DEPARTMENT_IS_ASSIGNED_TO_PATIENT_CANNOT_BE_DELETED");
	
	public final String userAlreadyInUseCannotBeDeleted = errorBundle.getString("USER_ALREADY_IN_USE_CANNOT_BE_DELETED");
	
	public final String pleaseEnterFirstName = errorBundle.getString("PLEASE_ENTER_FIRSTNAME");
	
	public final String pleaseEnterLastName = errorBundle.getString("PLEASE_ENTER_LASTNAME");
	
	public final String pleaseEnterDob = errorBundle.getString("PLEASE_ENTER_DATE_OF_BIRTH");
	
	public final String pleaseEnterPhoneNumber = errorBundle.getString("PLEASE_ENTER_PHONE_NUMBER");
	
	public final String pleaseEnterCategorytype = errorBundle.getString("PLEASE_ENTER_CATEGORY_TYPE");
	
	public final String pleaseEnterDepartmenttype = errorBundle.getString("PLEASE_ENTER_DEPARTMENT_TYPE");
	
	public final String physiotherapyAlreadyEvaluated = errorBundle.getString("PHYSIOTHERAPY_ALREADY_EVALAUTED");
	
	public final String physiotherapyCategoryAlreadyExists = errorBundle.getString("PHYSIOTHERAPY_CATEGORY_ALREADY_EXISTS");
	
	public final String physiotherapyCategoryQuestionsAlreadyExists = errorBundle.getString("PHYSIOTHERAPY_CATEGORY_QUESTIONS_ALREADY_EXISTS");
	
	public final String physiotherapyCategoryDoesNotExists = errorBundle.getString("PHYSIOTHERAPY_CATEGORY_DOESNOT_EXISTS");
	
	public final String physiotherapyEvalutionDoesNotExists = errorBundle.getString("PHYSIOTHERAPY_EVALUTION_DOESNOT_EXISTS");
	
	public final String patientDoesNotEvaluated = errorBundle.getString("PATIENT_DOESNOT_EVALUTED");
	
	public final String subCategoryTypeDoesNotExists = errorBundle.getString("SUB_CATEGORY_TYPE_DOES_NOT_EXISTS");
	
	public final String subCategoryTypeAlreadyExists = errorBundle.getString("SUB_CATEGORY_TYPE_ALREADY_EXISTS");
	
	public final String pleaseEnterPatientRegistrationtype = errorBundle.getString("PLEASE_ENTER_PATIENT_REGISTRATION_TYPE");
	
	public final String patientRegTypeAlreadyExists = errorBundle.getString("PATIENT_REGISTRATION_TYPE_ALREADY_EXISTS");
	
	public final String patientRegTypeDoesNotExists = errorBundle.getString("PATIENT_REGISTRATION_TYPE_DOES_NOT_EXISTS");
	
	public final String pleaseSelectPatientRegistration = errorBundle.getString("PATIENT_REGISTRATION_TYPE_IS_MANDATOIRY");
	
	public final String pleaseSelectPatientCategory = errorBundle.getString("PATIENT_CATEGORY_TYPE_IS_MANDATOIRY");
}

