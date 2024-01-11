package com.openspace.PatientManagement.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.DoctorManagement.AppointmentInvoice;
import com.openspace.Model.DoctorManagement.AppointmentInvoiceTemplate;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Document;
import com.openspace.Model.DoctorManagement.DocumentType;
import com.openspace.Model.DoctorManagement.Insurance;
import com.openspace.Model.DoctorManagement.ItemsTemplate;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.PatientRegistrationType;
import com.openspace.Model.DoctorManagement.PrivateClient;
import com.openspace.Model.DoctorManagement.RegionalCenter;
import com.openspace.Model.DoctorManagement.School;
import com.openspace.Model.Dto.CalAmountDto;
import com.openspace.Model.Dto.CycleDto;
import com.openspace.Model.Dto.InvoiceStipeDto;
import com.openspace.Model.Dto.MchatAssessmentDto;
import com.openspace.Model.Dto.PaidAmountDto;
import com.openspace.Model.Dto.PatientBillingDto;
import com.openspace.Model.Dto.PatientInvoiceDto;
import com.openspace.Model.Dto.PatientOb;
import com.openspace.Model.Dto.PaymentDashBoardDto;
import com.openspace.Model.Dto.SearchPatientDto;
import com.openspace.Model.Dto.Sessions;
import com.openspace.Model.Dto.SubAppointmentDashboardDto;
import com.openspace.Model.Dto.TotalPaymentDashBoard;
import com.openspace.Model.Lookups.CategoryType;
import com.openspace.Model.Lookups.RegionalCenterZoneLookup;
import com.openspace.Model.MotorReferenceProfile.PatientMotorScale;
import com.openspace.Model.Parent.Folder;
import com.openspace.Model.ParentModule.Mchart.Mchart;
import com.openspace.Model.Payment.Payment;
import com.openspace.Model.Util.VerhoeffAlgorithm;
import com.openspace.PatientManagement.dto.InvoiceDto;
import com.openspace.PatientManagement.dto.PatientInfoDto;
import com.openspace.PatientManagement.s3.S3LocatioDto;
import com.openspace.PatientManagement.service.PatientService;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody Patient patient) throws FileNotFoundException, IOException {
		patient.validatePatient();
		patientService.add(patient);
	}

	/*
	 * // modified due to complex coding
	 * 
	 * @RequestMapping(value = "/add", method = RequestMethod.POST) public void
	 * addPatient(@RequestBody PatientInfoDto patientInfoDto) throws
	 * FileNotFoundException, IOException {
	 * patientService.addPatient(patientInfoDto); }
	 */

	@RequestMapping(value = "/pagination", method = RequestMethod.GET)
	public Page<Patient> findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
		return patientService.findAll(page, size);
	}

	@RequestMapping(value = "/findPatientsByToDayDate/{adminUserName:.+}", method = RequestMethod.GET)
	public int findPatientsByDate(@PathVariable("adminUserName") String adminUserName) {
		return patientService.findPatientsByDate(adminUserName);
	}

	@RequestMapping(value = "/totalPatientsRegistered/{adminUserName:.+}", method = RequestMethod.GET)
	public int totalPatientsRegistered(@PathVariable("adminUserName") String adminUserName) {
		return patientService.totalPatientsRegistered(adminUserName);
	}

	@RequestMapping(value = "/getAllInactivePatients/{adminUserName:.+}", method = RequestMethod.GET)
	public int getAllInactivePatients(@PathVariable("adminUserName") String adminUserName) {
		return patientService.getAllInactivePatients(adminUserName);
	}

	@RequestMapping(value = "/allDocumentsbyPatient/{patientId}", method = RequestMethod.GET)
	public List<Document> getDocumentsByPatient(@PathVariable("patientId") Long patientId) {
		return patientService.getDocumentsByPatient(patientId);

	}

	@RequestMapping(value = "/allDocumentsbyPatientAndStatus/{patientId}/{status}", method = RequestMethod.GET)
	public List<Document> allDocumentsbyPatientAndStatus(@PathVariable("patientId") Long patientId,
			@PathVariable("status") DocumentType status) {
		return patientService.allDocumentsbyPatientAndStatus(patientId, status);

	}

	@RequestMapping(value = "/pagination/{adminUsername:.+}/{search}", method = RequestMethod.GET)
	public Page<Patient> findAllPatientsByAdmin(@PathVariable("adminUsername") String adminUsername,
			@PathVariable("search") String search,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size) {
		return patientService.findAllPatientsByRole(adminUsername,search, page, size);
	}
	
	@RequestMapping(value = "/paginationInActive/{adminUsername:.+}", method = RequestMethod.GET)
	public Page<Patient> findAllPatientsByAdminInActive(@PathVariable("adminUsername") String adminUsername,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size) {
		return patientService.findAllPatientsByRoleInActive(adminUsername,page, size);
	}
	
	@RequestMapping(value = "/findPatientsByToDayDateList/{adminUserName:.+}", method = RequestMethod.GET)
	public Page<Patient> findPatientsByDateList(@PathVariable("adminUserName") String adminUserName,@RequestParam("page")int page,@RequestParam("size")int size) {
		return patientService.findPatientsByDateList(adminUserName,page,size);
	}

	@RequestMapping(value = "/getAllpatients/{adminUserName:.+}", method = RequestMethod.GET)
	public List<Patient> findAll(@PathVariable("adminUserName") String adminUserName) {
		return patientService.findAllPatients(adminUserName);
	}
	
	@RequestMapping(value = "/getAllpatientsByCompanyAndDeptId/{adminUserName:.+}/{deptId}", method = RequestMethod.GET)
	public List<Patient> findAllpatientsByCompanyAndDeptId(@PathVariable("adminUserName") String adminUserName,@PathVariable("deptId") int deptId) {
		return patientService.findAllpatientsByCompanyAndDeptId(adminUserName,deptId);
	}
	
	@RequestMapping(value = "/getAllpatientsSearch/{adminUserName:.+}/{search}", method = RequestMethod.GET)
	public Page<Patient> findAllSearch(@PathVariable("adminUserName") String adminUserName,@PathVariable("search") String search,@RequestParam("page")int page,@RequestParam("size")int size) {
		return patientService.findAllPatientsSearch(adminUserName,search,page,size);
	}
	/*
	 * @RequestMapping(value =
	 * "/getAppointedPatientsByTherapist/{adminUserName:.+}", method =
	 * RequestMethod.GET) public @ResponseBody List<Patient>
	 * getAppointmentssByTherapist(@PathVariable("adminUserName")String
	 * adminUserName) { return
	 * patientService.getAppointedPatientsByTherapist(adminUserName); }
	 */

	@RequestMapping(value = "/updatePatient1", method = RequestMethod.PUT)
	public @ResponseBody void updatePatient(@RequestBody PatientInfoDto patient)
			throws FileNotFoundException, IOException {
		patientService.updatePatient(patient);
	}

	@RequestMapping(value = "/getDoctorByPatientId/{patientId}", method = RequestMethod.GET)
	public Doctor findDoctoerByPatient(@PathVariable("patientId") Long patientId) {
		return patientService.findDoctoerByPatientId(patientId);
	}

	@RequestMapping(value = "/deleteDocumentByPatient/{documentId}", method = RequestMethod.DELETE)
	public List<Document> deleteDoucmentByPatient(@PathVariable("documentId") Long documentId) {
		return patientService.deleteDoucmentByPatient(documentId);
	}

	@RequestMapping(value = "/getAllByRole/{adminUsername:.+}", method = RequestMethod.GET)
	public List<Patient> findAllPatientsByRoleWithoutPagination(@PathVariable("adminUsername") String adminUsername) {
		return patientService.findAllPatientsByRoleWithoutPagination(adminUsername);
	}

	@RequestMapping(value = "/searchPatient", method = RequestMethod.POST)
	public Set<Patient> searchPatient(@RequestBody SearchPatientDto patient) {
		return patientService.searchPatient(patient);
	}

	@RequestMapping(value = "/getPayment/{id}", method = RequestMethod.GET)
	public List<Payment> getPaymentsByPatient(@PathVariable Long id) {
		return patientService.getPaymentsByPatient(id);

	}

	@RequestMapping(value = "/updatePayment", method = RequestMethod.PUT)
	public void updatePayment(@RequestBody Payment payment) {
		patientService.updatePayment(payment);
	}

	@RequestMapping(value = "/addPayment", method = RequestMethod.POST)
	public void addPayment(@RequestBody Payment payment) {
		patientService.addPayment(payment);
	}

	@RequestMapping(value = "/getCycles/{id}", method = RequestMethod.GET)
	public CycleDto getCycles(@PathVariable Long id) {
		return patientService.getCycles(id);
	}

	@RequestMapping
	public Page<Payment> getAllPayments(@RequestParam("page") int page, @RequestParam("size") int size) {
		return patientService.getAllPayments(page, size);
	}

	@RequestMapping(value = "/paymentsPagination/{adminUsername:.+}", method = RequestMethod.GET)
	public Page<Payment> getPaymentsByRole(@PathVariable("adminUsername") String adminUsername,
			@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size) {
		return patientService.getPaymentsByRole(adminUsername, page, size);
	}

	@RequestMapping(value = "/paymentDashboard/{userName:.+}", method = RequestMethod.GET)
	public List<PaymentDashBoardDto> paymentDashboard(@PathVariable String userName) {
		return patientService.paymentDashboard(userName);
	}

	@RequestMapping(value = "/totalPaymentDashBoard/{userName:.+}", method = RequestMethod.GET)
	public List<TotalPaymentDashBoard> totalPaymentDashBoard(@PathVariable String userName) {
		return patientService.totalPaymentDashBoard(userName);
	}

	@RequestMapping(value = "/appointmentDashboardForPatient/{userName:.+}", method = RequestMethod.GET)
	public List<SubAppointmentDashboardDto> appointmentDashboardForPatient(@PathVariable String userName) {
		return patientService.appointmentDashboardForPatient(userName);
	}

	@RequestMapping(value = "/searchPatientPage", method = RequestMethod.POST)
	public Page<Patient> searchPatientPage(@RequestBody SearchPatientDto dto, @RequestParam("page") int page,
			@RequestParam("size") int size) {
		return patientService.searchPatientPage(dto, page, size);
	}

	@RequestMapping(value = "/invoice", method = RequestMethod.GET)
	public Integer invoice() {
		return patientService.invoice();
	}

	@RequestMapping(value = "/getSessions/{id}", method = RequestMethod.GET)
	public Sessions getSessions(@PathVariable Long id) {
		return patientService.getSessions(id);
	}

	@RequestMapping(value = "/calculateAmount", method = RequestMethod.POST)
	public CalAmountDto calculateAmount(@RequestBody CalAmountDto amountDto) {
		return patientService.calculateAmount(amountDto);
	}

	@RequestMapping(value = "/getAllDocumentsBy/{patientid}/folder/{id}", method = RequestMethod.GET)
	public List<Document> getAllDocumentsByPatientId(@PathVariable("patientid") Long patientid,
			@PathVariable("id") Long folderId) {
		return patientService.getAllDocumentsByPatientIdAndFolderId(patientid, folderId);
	}

	@RequestMapping(value="/saveInvoice",method=RequestMethod.POST)
	public void saveInvoice(@RequestBody AppointmentInvoice appointmentInvoice){
		patientService.saveInvoice(appointmentInvoice);
	}
	
	@RequestMapping(value="/updateInvoice",method=RequestMethod.POST)
	public void updateInvoice(@RequestBody PatientInvoiceDto patientInvoiceDto){
		patientService.updateInvoice(patientInvoiceDto);
	}
	
	@RequestMapping(value="/getInvoice",method=RequestMethod.POST)
	public AppointmentInvoice getInvoice(@RequestBody InvoiceDto invoiceNo){
		return patientService.getInvoice(invoiceNo);
	}
	
	@RequestMapping(value="/getInvoiceItems",method=RequestMethod.POST)
	public int getInvoiceItems(@RequestBody AppointmentInvoice invoiceNo){
		return patientService.getInvoiceItems(invoiceNo);
	}
	
	@RequestMapping(value="/updateFolderName",method=RequestMethod.POST)
	public void updateFolderName(@RequestBody Folder folder){
		 patientService.updateFolderName(folder);
	}
	
	
	@RequestMapping(value="/deleteItem/{id}",method=RequestMethod.DELETE)
	public void deleteItem(@PathVariable Long id){
		patientService.deleteItem(id);
	}
	
	@RequestMapping(value="/paidAmount",method=RequestMethod.POST)
	public PaidAmountDto paidAmount(@RequestBody PaidAmountDto paidAmountDto){
		return patientService.paidAmount(paidAmountDto);
	}
	
	@RequestMapping(value="/getAllItems", method = RequestMethod.GET)
	public List<ItemsTemplate> getAllItems(){
		return patientService.getAllItems();
	}
	@RequestMapping(value="/getAllItems1", method = RequestMethod.GET)
	public  List<AppointmentInvoiceTemplate> getAllItems1(){
		return patientService.getAllItems1();
	}
	@RequestMapping(value="/getTotalAmount",method=RequestMethod.POST)
	private Double getTotalAmount(@RequestBody AppointmentInvoice invoiceNo){
		return patientService.getTotalAmount(invoiceNo);
	}
	@RequestMapping(value="/getSinglepayment/{id}", method = RequestMethod.GET)
	public Payment getpayment(@PathVariable Long id){
		return patientService.getpayment(id);
	}
	
	@RequestMapping(value="/addDocument",method=RequestMethod.POST)
	private void addDocument(@RequestBody PatientInfoDto patientInfo) throws FileNotFoundException, IOException{
		 patientService.addDocument(patientInfo);
	}
	
	@RequestMapping(value="/getRegionalCenters/{id}/{userName:.+}", method = RequestMethod.GET)
	public  List<RegionalCenter> getRegionalCenters(@PathVariable Long id,@PathVariable String userName){
		return patientService.getRegionalCenters(id,userName);
	}
	@RequestMapping(value="/getschoolClientTypes", method = RequestMethod.GET)
	public  List<School> getschoolClientTypes(){
		return patientService.getschoolClientTypes();
	}
	
	@RequestMapping(value="/getPrivateClienTypes", method = RequestMethod.GET)
	public  List<PrivateClient> getPrivateClienTypes(){
		return patientService.getPrivateClienTypes();
	}
	@RequestMapping(value="/getInsurenceClientTypes", method = RequestMethod.GET)
	public  List<Insurance> getInsurenceClientTypes(){
		return patientService.getInsurenceClientTypes();
	}
	
	@RequestMapping(value="/getRegionalCenterZones", method = RequestMethod.GET)
	public  List<RegionalCenterZoneLookup> getRegionalCenterZones(){
		return patientService.getRegionalCenterZones();
	}
	
	/*@RequestMapping(value = "/getAllUSerPaymentsStripe/{username:.+}", method = RequestMethod.GET)
	public List<ChargeDto1> getAllPaymentsStripe(@PathVariable("username")  String username) {
		return patientService.getAllPaymentsStripe(username);
	}*/
	@RequestMapping(value = "/getAllUSerPaymentsStripe/{username:.+}", method = RequestMethod.GET)
	public List<InvoiceStipeDto> getAllPaymentsStripe(@PathVariable("username")  String username) {
		return patientService.getStripePaymentsInvoice(username);
	}

	@RequestMapping(value = "/getfileFromS3/{documentId}", method = RequestMethod.GET)
	public S3LocatioDto getFilePathinS3(@PathVariable("documentId") Long documentId) {
		return patientService.getFilePathinS3(documentId);
	}
	
	@RequestMapping(value = "/downloadfileFromS3/{documentId}", method = RequestMethod.GET)
	public void downloadTheFilePathFromS3(@PathVariable("documentId") Long documentId) {
		 patientService.downloadTheFilePathFromS3(documentId);
	}
	@RequestMapping(value="/findAllPatientsByRoleForBillingsPage",method=RequestMethod.POST)
	private Page<Patient> findAllPatientsByRoleForBillingsPage(@RequestBody PatientBillingDto patientBillingDto, @RequestParam("page") int page,
			@RequestParam("size") int size){
		return patientService.findAllPatientsByRoleForBillingsPage(patientBillingDto,page,size);
	}
	
	@RequestMapping(value = "/patientMchatAssmnt", method = RequestMethod.POST)
	private void patientMchatAssessment(@RequestBody MchatAssessmentDto mchatAssessmentDto) {
		patientService.patientMchatAssessment(mchatAssessmentDto);
	}
	@RequestMapping(value = "/updatePatientMchatAssmnt/{patientId}", method = RequestMethod.POST)
	public void updatePatientMchatAssmnt(@RequestBody List<Mchart> mchart,
			@PathVariable(value = "patientId") Long patientId) {
		patientService.updatePatientMchatAssmnt(mchart, patientId);
	}

	@RequestMapping(value = "/getPatient", method = RequestMethod.POST)
	public Patient getPatient(@RequestBody PatientOb patient) {
		return  patientService.getPatientId(patient);
	}
	
	
	
	@RequestMapping(value = "/getPaientBySSN/{ssn}", method = RequestMethod.GET)
	public Patient findPatientBYSSN(@PathVariable("ssn") String ssn) {
		return patientService.findPatientBYSSN(ssn);
	}
	
	@RequestMapping(value = "/getPaientById/{id}", method = RequestMethod.GET)
	public Patient getPaientById(@PathVariable("id") Long id) {
		return patientService.findPatientById(id);
	}
	
	@RequestMapping(value = "/getPaientByREGNO/{regNo}", method = RequestMethod.GET)
	public Patient findPatientBYREGNO(@PathVariable("regNo") String regNo) {
		return patientService.findPatientBYREGNO(regNo);
	}
	
	@RequestMapping(value = "/getCatByPaient/{patientId}", method = RequestMethod.GET)
	public List<CategoryType> fetchCatPatientByRegType(@PathVariable("patientId") Patient patientId) {
		System.out.println("INsied method call"+patientId.getId());
		return patientService.fetchCatPatientByRegType(patientId).getPatientRegistrationType().getCategoryType();

	}
	
}
