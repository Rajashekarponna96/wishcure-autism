package com.openspace.Model.DoctorManagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DateConverters.LocalTimeConverter;
import com.openspace.Model.DateConverters.LocalTimeDeSerilizer;
import com.openspace.Model.DateConverters.LocalTimeSerilizer;
import com.openspace.Model.Lookups.Address1;
import com.openspace.Model.Lookups.CategoryType;
import com.openspace.Model.Lookups.ClientType;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.Lookups.Department;
import com.openspace.Model.MentalReferenceProfile.MentalResult;
import com.openspace.Model.MotorReferenceProfile.MotorResult;
import com.openspace.Model.Parent.Folder;
import com.openspace.Model.Parent.Parent;
import com.openspace.Model.ParentModule.Mchart.Mchart;
import com.openspace.Model.ParentModule.csbs.CsbsCategory;
import com.openspace.Model.Payment.Payment;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Entity
public class Patient extends Person implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private String gaurdian;

	private String bloodGroup;

	@Column(unique=true)
	private String ssn;

	private String ucl;

	private UserAccount userAccount;

	private List<Department> departments;

	private String category;

	private Boolean modelData;

	private List<Appointment> appointments;

	// private List<Document> documents;

	private List<Folder> folders;

	private LocalTime dateOfTest;

	private EvalutionSheet evalutionSheet;

	private List<PatientGoal> patientGoals;

	private ClientType clientType;

	// private Address addressOld;

	private Address1 address;

	private Company company;

	private String adminUser;

	private String companyUniqueId;

	private boolean evalutionDone;

	private boolean appointmentCreated;

	private Parent parent;

	private boolean sms;

	private boolean communicatonEmail;

	private TherapyAddress therapyAddress;

	private RegionalCenter regionalCenter;

	private School school;

	private Insurance insurance;

	private PrivateClient privateClient;

	private List<Payment> payments;

	private String uniqueId;

	private List<PatientSpeechTherapyTemplate> patientSpeechTherapyTemplates;

	// private List<DocumentTypeLookup> documentTypeLookups;

	private String serviceCoordinatorName;

	private Long phoneNumber;

	private String emailPatient;

	private String patientInsuraceNo;

	private String groupNumber;

	private String planCode;

	private String contactName;

	private CategoryType categoryType;
	
	private List<MentalResult> mentalResults;

	private List<MotorResult> motorResults;

	private List<Mchart> mchatTemplatesQuestios;

	private List<CsbsCategory> csbsCategories;

	private String relationshipOfChildren;

	private String filledOutBy;

	private String regNumber;

	private LocalDate regDate;

	private String referredBy;

	private String reasonForReferral;

	private String relationshipToChild;
	 
	private String motherTongue;
	
	private String patientNote;
	
	public String getSiblings() {
		return siblings;
	}

	public void setSiblings(String siblings) {
		this.siblings = siblings;
	}

	private String languagesKnown;
	
	private String siblings;

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	private String parentName;
	
	private String motherName;
	
	
	
	
	
	private PatientRegistrationType patientRegistrationType;

	public String getGaurdian() {
		return gaurdian;
	}

	public void setGaurdian(String gaurdian) {
		this.gaurdian = gaurdian;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	@OneToOne
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getUcl() {
		return ucl;
	}

	public void setUcl(String ucl) {
		this.ucl = ucl;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	public List<Appointment> getAppointments() {
		return appointments;
	}

	@JsonProperty
	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	@Convert(converter = LocalTimeConverter.class)
	@JsonDeserialize(using = LocalTimeDeSerilizer.class)
	@JsonSerialize(using = LocalTimeSerilizer.class)
	public LocalTime getDateOfTest() {
		return dateOfTest;
	}

	public void setDateOfTest(LocalTime dateOfTest) {
		this.dateOfTest = dateOfTest;
	}

	@JsonIgnore
	@OneToOne
	public EvalutionSheet getEvalutionSheet() {
		return evalutionSheet;
	}

	@JsonProperty
	public void setEvalutionSheet(EvalutionSheet evalutionSheet) {
		this.evalutionSheet = evalutionSheet;
	}

	@OneToOne
	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	/*
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient") public
	 * List<Document> getDocuments() { return documents; }
	 * 
	 * public void setDocuments(List<Document> documents) { this.documents =
	 * documents; }
	 */

	public String getPatientNote() {
		return patientNote;
	}

	public void setPatientNote(String patientNote) {
		this.patientNote = patientNote;
	}

	@Embedded
	public Address1 getAddress() {
		return address;
	}

	public void setAddress(Address1 address) {
		this.address = address;
	}

	@ManyToOne
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}

	public boolean isEvalutionDone() {
		return evalutionDone;
	}

	public void setEvalutionDone(boolean evalutionDone) {
		this.evalutionDone = evalutionDone;
	}

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	public List<PatientGoal> getPatientGoals() {
		return patientGoals;
	}

	public String getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}

	public String getLanguagesKnown() {
		return languagesKnown;
	}

	public void setLanguagesKnown(String languagesKnown) {
		this.languagesKnown = languagesKnown;
	}

	public void setPatientGoals(List<PatientGoal> patientGoals) {
		this.patientGoals = patientGoals;
	}

	public boolean isAppointmentCreated() {
		return appointmentCreated;
	}

	public void setAppointmentCreated(boolean appointmentCreated) {
		this.appointmentCreated = appointmentCreated;
	}

	@JsonIgnore
	@ManyToOne
	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public String getCompanyUniqueId() {
		return companyUniqueId;
	}

	public void setCompanyUniqueId(String companyUniqueId) {
		this.companyUniqueId = companyUniqueId;
	}

	public boolean isSms() {
		return sms;
	}

	public void setSms(boolean sms) {
		this.sms = sms;
	}

	public boolean isCommunicatonEmail() {
		return communicatonEmail;
	}

	public void setCommunicatonEmail(boolean communicatonEmail) {
		this.communicatonEmail = communicatonEmail;
	}

	@OneToOne
	public TherapyAddress getTherapyAddress() {
		return therapyAddress;
	}

	public void setTherapyAddress(TherapyAddress therapyAddress) {
		this.therapyAddress = therapyAddress;
	}

	@ManyToOne
	public RegionalCenter getRegionalCenter() {
		return regionalCenter;
	}

	public void setRegionalCenter(RegionalCenter regionalCenter) {
		this.regionalCenter = regionalCenter;
	}

	@OneToOne
	public PrivateClient getPrivateClient() {
		return privateClient;
	}

	public void setPrivateClient(PrivateClient privateClient) {
		this.privateClient = privateClient;
	}

	@OneToOne
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@OneToOne
	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	public List<Payment> getPayments() {
		return payments;
	}

	
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	@JsonIgnore
	@OneToMany
	public List<PatientSpeechTherapyTemplate> getPatientSpeechTherapyTemplates() {
		return patientSpeechTherapyTemplates;
	}

	@JsonProperty
	public void setPatientSpeechTherapyTemplates(List<PatientSpeechTherapyTemplate> patientSpeechTherapyTemplates) {
		this.patientSpeechTherapyTemplates = patientSpeechTherapyTemplates;
	}

	/*
	 * @ManyToOne public Department getDepartment() { return department; }
	 * 
	 * public void setDepartment(Department department) { this.department =
	 * department; }
	 */
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getModelData() {
		return modelData;
	}

	public void setModelData(Boolean modelData) {
		this.modelData = modelData;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	public List<Folder> getFolders() {
		return folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getServiceCoordinatorName() {
		return serviceCoordinatorName;
	}

	public void setServiceCoordinatorName(String serviceCoordinatorName) {
		this.serviceCoordinatorName = serviceCoordinatorName;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailPatient() {
		return emailPatient;
	}

	public void setEmailPatient(String emailPatient) {
		this.emailPatient = emailPatient;
	}

	public String getPatientInsuraceNo() {
		return patientInsuraceNo;
	}

	public void setPatientInsuraceNo(String patientInsuraceNo) {
		this.patientInsuraceNo = patientInsuraceNo;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "PATIENT_DEPARTMENT", joinColumns = { @JoinColumn(name = "PATIENT_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "DEPARTMENT_ID") })
	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnore
	public CategoryType getCategoryType() {
		return categoryType;
	}

	@JsonProperty
	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}
	
	
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	public List<MentalResult> getMentalResults() {
		return mentalResults;
	}

	public void setMentalResults(List<MentalResult> mentalResults) {
		this.mentalResults = mentalResults;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	public List<MotorResult> getMotorResults() {
		return motorResults;
	}

	public void setMotorResults(List<MotorResult> motorResults) {
		this.motorResults = motorResults;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	public List<Mchart> getMchatTemplatesQuestios() {
		return mchatTemplatesQuestios;
	}

	public void setMchatTemplatesQuestios(List<Mchart> mchatTemplatesQuestios) {
		this.mchatTemplatesQuestios = mchatTemplatesQuestios;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
	public List<CsbsCategory> getCsbsCategories() {
		return csbsCategories;
	}

	public void setCsbsCategories(List<CsbsCategory> csbsCategories) {
		this.csbsCategories = csbsCategories;
	}

	public String getRelationshipOfChildren() {
		return relationshipOfChildren;
	}

	public void setRelationshipOfChildren(String relationshipOfChildren) {
		this.relationshipOfChildren = relationshipOfChildren;
	}

	public String getFilledOutBy() {
		return filledOutBy;
	}

	public void setFilledOutBy(String filledOutBy) {
		this.filledOutBy = filledOutBy;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public String getReasonForReferral() {
		return reasonForReferral;
	}

	public void setReasonForReferral(String reasonForReferral) {
		this.reasonForReferral = reasonForReferral;
	}

	public String getRelationshipToChild() {
		return relationshipToChild;
	}

	public void setRelationshipToChild(String relationshipToChild) {
		this.relationshipToChild = relationshipToChild;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/*
	 * @OneToMany(cascade=CascadeType.MERGE) public List<DocumentTypeLookup>
	 * getDocumentTypeLookups() { return documentTypeLookups; }
	 * 
	 * public void setDocumentTypeLookups(List<DocumentTypeLookup>
	 * documentTypeLookups) { this.documentTypeLookups = documentTypeLookups; }
	 */
	@OneToOne(cascade = CascadeType.MERGE)
	public PatientRegistrationType getPatientRegistrationType() {
		return patientRegistrationType;
	}

	public void setPatientRegistrationType(PatientRegistrationType patientRegistrationType) {
		this.patientRegistrationType = patientRegistrationType;
	}

	public void validatePatient(){
		
		if(this.getFirstName()==null){
			throw new RuntimeException(ErrorMessageHandler.pleaseEnterFirstName);
		}
		
		
		  if(this.getPatientRegistrationType()==null){ throw new
		  RuntimeException(ErrorMessageHandler.pleaseSelectPatientRegistration); 
		  }
		 
		  if(this.getPatientRegistrationType().getCategoryType()==null){ throw new
			  RuntimeException(ErrorMessageHandler.pleaseSelectPatientCategory); 
			  }
		
		if(this.getDateOfBirth()==null){
			throw new RuntimeException(ErrorMessageHandler.pleaseEnterDob);
		}
		
		if(this.getMobileNumber()==null){
			throw new RuntimeException(ErrorMessageHandler.pleaseEnterPhoneNumber);
		}
		
		if(this.getDepartments()==null){
			throw new RuntimeException(ErrorMessageHandler.pleaseEnterDepartmenttype);
		}
		
		if(this.getPatientRegistrationType()==null){
			throw new RuntimeException(ErrorMessageHandler.pleaseEnterPatientRegistrationtype);
		}
		
	}
}
