package com.openspace.PatientManagement.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.openspace.Model.DoctorManagement.AppointmentInvoice;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.EvalutionSheet;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.DoctorManagement.SubAppointment;
import com.openspace.Model.MentalReferenceProfile.PatientMentalScale;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.SubAppointmentRepository;
import com.openspace.Model.Scales.MentalScales;
import com.openspace.PatientManagement.Scales.MentalScalesService;
import com.openspace.PatientManagement.dto.InvoiceDto;
import com.openspace.PatientManagement.dto.InvoiceItemsDto;
import com.openspace.PatientManagement.dto.ProgressReportDto;
import com.openspace.PatientManagement.dto.ProgressSheetDateDto;
import com.openspace.PatientManagement.service.EvalutionSheetService;
import com.openspace.PatientManagement.service.PatientService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping(value = "/report")
public class ReportController {

	@Autowired
	private EvalutionSheetService evalutionSheetService;
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private SubAppointmentRepository subAppointmentRepository;
	
	@Autowired
	private MentalScalesService mentalScalesService;

	@RequestMapping(value = "/progress", method = RequestMethod.POST)
	public @ResponseBody ModelAndView generateProgressSheet(@RequestBody ProgressSheetDateDto progressSheetDateDto,
			ModelAndView modelAndView) {
		List<ProgressReportDto> list = new ArrayList<ProgressReportDto>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
		LocalDate localDate = LocalDate.parse(progressSheetDateDto.getProgressDate(), formatter);
		String actualDate=localDate.format(formatter);
		System.out.println("actual Date"+actualDate);
		String beforeDate = null;
		Set<String> dates=	evalutionSheetService.getProgressSheetDatesByPatientId(progressSheetDateDto.getPatientId());
		for(String date:dates){
			if(date.equals(actualDate)){
			
			}
		}
		System.out.println("BeforeDate"+beforeDate);
		
		EvalutionSheet evalutionSheet = evalutionSheetService
				.getProgressSheetByDateAndStatus(progressSheetDateDto);
		List<EvalutionSheet> evalutionSheetList = new ArrayList<EvalutionSheet>();
		evalutionSheetList.add(evalutionSheet);
		// list.addAll(createProgressReportDto(evalutionSheet));
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("patientSpeechTemplates", evalutionSheet.getPatientSpeechTherapyTemplateList());

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(evalutionSheetList, true);
		/*if (evalutionSheet.getPatient().getFirstName() != null) {
		parameterMap.put("name",
				evalutionSheet.getPatient().getFirstName() + " . " + evalutionSheet.getPatient().getLastName());
		}
		if (evalutionSheet.getPatient().getUcl() != null) {
		parameterMap.put("uci", evalutionSheet.getPatient().getUcl());
		}
		if (evalutionSheet.getPatient().getAge() != null) {
		parameterMap.put("age", evalutionSheet.getPatient().getAge().toString());
		}
		if (evalutionSheet.getPatient().getDateOfTest() != null) {
		parameterMap.put("dateOfTest", evalutionSheet.getPatient().getDateOfTest());
		}
		if ( evalutionSheet.getPatient().getSsn() != null) {
		parameterMap.put("ssn", evalutionSheet.getPatient().getSsn());
		}
		if (evalutionSheet.getPatient().getGaurdian() != null) {
		parameterMap.put("parent", evalutionSheet.getPatient().getGaurdian());
		}*/
		parameterMap.put("dataSource", JRdataSource);
		/*if (evalutionSheet.getHistory() != null) {
		parameterMap.put("history", evalutionSheet.getHistory());
		}
		if (evalutionSheet.getBackgroundInformation()!= null) {
		parameterMap.put("backgroundInformation", evalutionSheet.getBackgroundInformation());
		}
		if (evalutionSheet.getObservation() != null) {
		parameterMap.put("observation", evalutionSheet.getObservation());
		}
		if (evalutionSheet.getTestAdministered() != null) {
		parameterMap.put("testAdministered", evalutionSheet.getTestAdministered());
		}
		if (evalutionSheet.getOralMotor() != null) {
		parameterMap.put("oralMotor", evalutionSheet.getOralMotor());
		}
		if (evalutionSheet.getPragmaticSkills() != null) {
		parameterMap.put("pragmaticSkills", evalutionSheet.getPragmaticSkills());
		}
		
		if (evalutionSheet.getSummary() != null) {
		parameterMap.put("summary", evalutionSheet.getSummary());
		}
		if (evalutionSheet.getRecommandations() != null) {
		parameterMap.put("recommandations", evalutionSheet.getRecommandations());
		}
		if (evalutionSheet.getParentalTips() != null) {
		parameterMap.put("parentalTips", evalutionSheet.getParentalTips());
		}*/
		/*// parameterMap.put("auditotyCategoryName",evalutionSheet.getEvalutionSheetResult().getAuditotyCategoryName());
		parameterMap.put("auditotyStandardScore",
				evalutionSheet.getEvalutionSheetResult().getAuditotyStandardScore().toString());
		parameterMap.put("auditotyAge", evalutionSheet.getEvalutionSheetResult().getAuditotyAge().toString());
		parameterMap.put("auditotyDelay", evalutionSheet.getEvalutionSheetResult().getAuditotyDelay().toString());
		parameterMap.put("auditotyPercentile",
				evalutionSheet.getEvalutionSheetResult().getAuditotyPercentile().toString());
		// parameterMap.put("expressiveCategoryName",evalutionSheet.getEvalutionSheetResult().getExpressiveCategoryName());
		parameterMap.put("expressiveStandardScore",
				evalutionSheet.getEvalutionSheetResult().getExpressiveStandardScore().toString());
		parameterMap.put("expressiveAge", evalutionSheet.getEvalutionSheetResult().getExpressiveAge().toString());
		parameterMap.put("expressiveDelay", evalutionSheet.getEvalutionSheetResult().getExpressiveDelay().toString());
		parameterMap.put("expressivePercentile",
				evalutionSheet.getEvalutionSheetResult().getExpressivePercentile().toString());*/
		parameterMap.put("dataSource", JRdataSource);
		modelAndView = new ModelAndView("generateProgressSheet", parameterMap);

		return modelAndView;

	}

	public List<ProgressReportDto> createProgressReportDto(EvalutionSheet evalutionSheet) {
		List<ProgressReportDto> progressreportDtoList = new ArrayList<ProgressReportDto>();

		/*for (PatientQuestionCategory patientQuestionCategory : evalutionSheet.getPatientQuestionCategories()) {

			for (PatientQuestion patientQuestion : patientQuestionCategory.getPatientQuestions()) {

				for (PatientAnswer patientAnswer : patientQuestion.getPatientAnswers()) {
					ProgressReportDto progressReportDto = new ProgressReportDto();
					progressReportDto.setQuestioncategory(
							patientQuestion.getQuestionName() + "(" + patientQuestionCategory.getName() + ")");
					if (patientAnswer.getSelectedAnswer() != null) {
						boolean selectedAnswer = patientAnswer.getSelectedAnswer();
						String selectedAnswer1 = selectedAnswer ? "yes" : "No";
						progressReportDto.setAnswer(patientAnswer.getShortAnswer() + ":   " + selectedAnswer1);

						progressreportDtoList.add(progressReportDto);
					}
				}

			}

		}*/
		return progressreportDtoList;

	}

	/*@RequestMapping(value = "/evaluation/{patientId}", method = RequestMethod.GET)
	public @ResponseBody ModelAndView generateEvaluationSheet(@PathVariable("patientId") Long patientId,
			ModelAndView modelAndView) {
		//List<ProgressReportDto> list = new ArrayList<ProgressReportDto>();
		EvalutionSheet evalutionSheet = evalutionSheetService
				.getEvaluationSheetByPatientIdStatusInEvaluationReport(patientId);
		// list.addAll(createProgressReportDto(evalutionSheet));
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<EvalutionSheet> evalutionSheetList = new ArrayList<EvalutionSheet>();
		evalutionSheetList.add(evalutionSheet);
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(evalutionSheetList,true);
		if (evalutionSheet.getPatient().getFirstName() != null) {
		parameterMap.put("name",
				evalutionSheet.getPatient().getFirstName() + " . " + evalutionSheet.getPatient().getLastName());
		}
		if (evalutionSheet.getPatient().getUcl() != null) {
		parameterMap.put("uci", evalutionSheet.getPatient().getUcl());
		}
		if (evalutionSheet.getPatient().getAge() != null) {
		parameterMap.put("age", evalutionSheet.getPatient().getAge().toString());
		}
		if (evalutionSheet.getPatient().getDateOfTest() != null) {
		parameterMap.put("dateOfTest", evalutionSheet.getPatient().getDateOfTest());
		}
		if (evalutionSheet.getPatient().getSsn() != null) {
		parameterMap.put("ssn", evalutionSheet.getPatient().getSsn());
		}
		if ( evalutionSheet.getPatient().getGaurdian() != null) {
		parameterMap.put("parent", evalutionSheet.getPatient().getGaurdian());
		}
		
		parameterMap.put("dataSource", JRdataSource);
		
		if (evalutionSheet.getHistory() != null) {
		parameterMap.put("history", evalutionSheet.getHistory());
		}
		if (evalutionSheet.getBackgroundInformation() != null) {
		parameterMap.put("backgroundInformation", evalutionSheet.getBackgroundInformation());
		}
		if (evalutionSheet.getObservation() != null) {
		parameterMap.put("observation", evalutionSheet.getObservation());
		}
		if (evalutionSheet.getTestAdministered() != null) {
		parameterMap.put("testAdministered", evalutionSheet.getTestAdministered());
		}
		if (evalutionSheet.getOralMotor() != null) {
		parameterMap.put("oralMotor", evalutionSheet.getOralMotor());
		}
		if (evalutionSheet.getPragmaticSkills() != null) {
		parameterMap.put("pragmaticSkills", evalutionSheet.getPragmaticSkills());
		}
		if (evalutionSheet.getSummary() != null) {
		parameterMap.put("summary", evalutionSheet.getSummary());
		}
		if ( evalutionSheet.getRecommandations() != null) {
		parameterMap.put("recommandations", evalutionSheet.getRecommandations());
		}
		if (evalutionSheet.getParentalTips() != null) {
		parameterMap.put("parentalTips", evalutionSheet.getParentalTips());
		}
		
		modelAndView = new ModelAndView("generateEvaluationReport", parameterMap);

		return modelAndView;

	}*/

	@RequestMapping(value = "/exitNote/{patientId}", method = RequestMethod.GET)
	public @ResponseBody ModelAndView generateExitNote(@PathVariable("patientId") Long patientId,
			ModelAndView modelAndView) {
		List<ProgressReportDto> list = new ArrayList<ProgressReportDto>();
		EvalutionSheet evalutionSheet = evalutionSheetService
				.getEvaluationSheetByPatientIdStatusInExitNoteReport(patientId);
		// list.addAll(createProgressReportDto(evalutionSheet));
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<EvalutionSheet> evalutionSheetList=new ArrayList<EvalutionSheet>();
		evalutionSheetList.add(evalutionSheet);
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(evalutionSheetList, true);
		/*parameterMap.put("name",
				evalutionSheet.getPatient().getFirstName() + " . " + evalutionSheet.getPatient().getLastName());
		parameterMap.put("uci", evalutionSheet.getPatient().getUcl());
		parameterMap.put("age", evalutionSheet.getPatient().getAge().toString());
		parameterMap.put("dateOfTest", evalutionSheet.getPatient().getDateOfTest());
		parameterMap.put("ssn", evalutionSheet.getPatient().getSsn());
		parameterMap.put("parent", evalutionSheet.getPatient().getGaurdian());*/
		parameterMap.put("dataSource", JRdataSource);
		/*parameterMap.put("history", evalutionSheet.getHistory());
		parameterMap.put("backgroundInformation", evalutionSheet.getBackgroundInformation());
		parameterMap.put("observation", evalutionSheet.getObservation());
		parameterMap.put("testAdministered", evalutionSheet.getTestAdministered());
		parameterMap.put("oralMotor", evalutionSheet.getOralMotor());
		parameterMap.put("pragmaticSkills", evalutionSheet.getPragmaticSkills());
		parameterMap.put("summary", evalutionSheet.getSummary());
		parameterMap.put("recommandations", evalutionSheet.getRecommandations());
		parameterMap.put("parentalTips", evalutionSheet.getParentalTips());*/
		/*parameterMap.put("auditotyCategoryName", evalutionSheet.getEvalutionSheetResult().getAuditotyCategoryName());
		parameterMap.put("auditotyStandardScore",
				evalutionSheet.getEvalutionSheetResult().getAuditotyStandardScore().toString());
		parameterMap.put("auditotyAge", evalutionSheet.getEvalutionSheetResult().getAuditotyAge().toString());
		parameterMap.put("auditotyDelay", evalutionSheet.getEvalutionSheetResult().getAuditotyDelay().toString());
		parameterMap.put("auditotyPercentile",
				evalutionSheet.getEvalutionSheetResult().getAuditotyPercentile().toString());
		parameterMap.put("expressiveCategoryName",
				evalutionSheet.getEvalutionSheetResult().getExpressiveCategoryName());
		parameterMap.put("expressiveStandardScore",
				evalutionSheet.getEvalutionSheetResult().getExpressiveStandardScore().toString());
		parameterMap.put("expressiveAge", evalutionSheet.getEvalutionSheetResult().getExpressiveAge().toString());
		parameterMap.put("expressiveDelay", evalutionSheet.getEvalutionSheetResult().getExpressiveDelay().toString());
		parameterMap.put("expressivePercentile",
				evalutionSheet.getEvalutionSheetResult().getExpressivePercentile().toString());*/
		modelAndView = new ModelAndView("generateExitNoteReport", parameterMap);

		return modelAndView;

	}

	@RequestMapping(value = "/downloadInvoice", method = RequestMethod.POST)
	public @ResponseBody ModelAndView downloadInvoice(@RequestBody AppointmentInvoice dto, ModelAndView modelAndView) {
		AppointmentInvoice appointmentInvoice = patientService.downloadInvoice(dto.getInvoiceNumber());
		SubAppointment subAppointment = (SubAppointment) subAppointmentRepository.findByInvoiceNo(dto.getInvoiceNumber());
		if (subAppointment == null) {
			throw new RuntimeException("Subappointment doesn't exist based on this Invoice Number" + dto.getInvoiceNumber() + "");
		}

		Map<String, Object> parameterMap = new HashMap<String, Object>();
	
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(appointmentInvoice.getItems());
		parameterMap.put("dataSource", JRdataSource);
		
		if (subAppointment.getDoctor().getAddress1() != null) {
			System.out.println(subAppointment.getDoctor().getAddress1().getZipcode() + " "
					+ subAppointment.getDoctor().getAddress1().getCity() + " ,"
					+ subAppointment.getDoctor().getAddress1().getState() + " ,"
					+ subAppointment.getDoctor().getAddress1().getCountry() + " , P:"
					+ subAppointment.getDoctor().getMobileNumber());
			parameterMap.put("doctorAdderess",
					subAppointment.getDoctor().getAddress1().getZipcode() + " "
							+ subAppointment.getDoctor().getAddress1().getCity()+ " ,"
							+ subAppointment.getDoctor().getAddress1().getState()+ " ,"
							+ subAppointment.getDoctor().getAddress1().getCountry() + " , P:"
							+ subAppointment.getDoctor().getMobileNumber());
		}
		if (subAppointment.getPatient().getAddress() != null) {
			parameterMap.put("patientAdderess",
					subAppointment.getPatient().getAddress().getZipcode() + " "
							+ subAppointment.getPatient().getAddress().getCity() + " ,"
							+ subAppointment.getPatient().getAddress().getState() + " ,"
							+ subAppointment.getPatient().getAddress().getCountry()+ " , P:"
							+ subAppointment.getPatient().getMobileNumber());
		}
		if (subAppointment.getPatient().getFirstName() != null) {
			parameterMap.put("PatientName",
					subAppointment.getPatient().getFirstName() + " " + subAppointment.getPatient().getLastName());
		}
		if (subAppointment.getPatient().getEmail() != null) {
			parameterMap.put("patientEmail", subAppointment.getPatient().getEmail());
		}
		if(subAppointment.getPerson().getProfilePic()!=null){
			System.out.println(subAppointment.getPerson().getProfilePic());
			parameterMap.put("signaturePath", subAppointment.getPerson().getProfilePic());
		}if(subAppointment.getPerson().getUserAccount().getCompany().getCompanyName()!=null){
			parameterMap.put("companyname", subAppointment.getPerson().getUserAccount().getCompany().getCompanyName());
		}
		
		if (appointmentInvoice.getInvoiceNumber() != null) {
			parameterMap.put("invoiceNumber", appointmentInvoice.getInvoiceNumber());
		}
		if (appointmentInvoice.getTotalAmount() != null) {
			parameterMap.put("totalAmount", appointmentInvoice.getTotalAmount());
		}
		if (appointmentInvoice.getPaidAmount() != null) {
			parameterMap.put("paidAmount", appointmentInvoice.getPaidAmount());
		}
		if (appointmentInvoice.getDueAmount() != null) {
			parameterMap.put("dueAmount", appointmentInvoice.getDueAmount());
		}
		if (appointmentInvoice.getPaidDate() != null) {
			parameterMap.put("paidDate", appointmentInvoice.getPaidDate());
		}
		if (appointmentInvoice.getPaidStatus() != null) {
			parameterMap.put("paidStatus", appointmentInvoice.getPaidStatus());
		}
		if (appointmentInvoice.getCurrency() != null) {
			parameterMap.put("currency", appointmentInvoice.getCurrency());
		}

		modelAndView = new ModelAndView("generateInvoiceReport", parameterMap);
		
		
		return modelAndView;
	}

	@RequestMapping(value = "/generateInvoice", method = RequestMethod.GET)
	public @ResponseBody ModelAndView generateInvoice(ModelAndView modelAndView) {

		InvoiceDto invoice = new InvoiceDto();
		invoice.setAddress("1355 Market Street, Suite 900 San Francisco, CA 94103 P: 987766543");
		invoice.setFullName("john");
		invoice.setEmail("john@gmail.com ");
		invoice.setInvoiceId(0044777);
		invoice.setIssuedDate(LocalDate.now());
		invoice.setPaymentDueDate(LocalDate.now());

		InvoiceItemsDto itemList = new InvoiceItemsDto();
		itemList.setItemName("item1");
		itemList.setQuantity(3);
		itemList.setUnitprice(20.0);
		itemList.setTax(10.0);
		itemList.setTotalPrice((itemList.getQuantity() * itemList.getUnitprice()) + itemList.getTax());
		InvoiceItemsDto itemList1 = new InvoiceItemsDto();
		itemList1.setItemName("item2");
		itemList1.setQuantity(2);
		itemList1.setUnitprice(20.0);
		itemList1.setTax(10.0);
		itemList1.setTotalPrice((itemList1.getQuantity() * itemList1.getUnitprice()) + itemList1.getTax());

		List<InvoiceItemsDto> itemListDtos = new ArrayList<InvoiceItemsDto>();
		itemListDtos.add(itemList);
		itemListDtos.add(itemList1);
		invoice.setItemsList(itemListDtos);

		invoice.setDiscount(10.0);
		invoice.setVat(20.0);
		Double totalAmount = 0.0;
		Double grandTotal = 0.0;
		for (InvoiceItemsDto items : itemListDtos) {
			totalAmount = totalAmount + items.getTotalPrice();
		}
		System.out.println(totalAmount);
		invoice.setTotalAmount(totalAmount);
		grandTotal = totalAmount - invoice.getDiscount() + invoice.getVat();
		System.out.println(grandTotal);
		invoice.setGrandTotal(grandTotal);

		List<InvoiceDto> list = new ArrayList<InvoiceDto>();
		list.add(invoice);
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(itemListDtos);
		parameterMap.put("dataSource", JRdataSource);
		parameterMap.put("fullname", invoice.getFullName());
		parameterMap.put("address", invoice.getAddress());
		parameterMap.put("email", invoice.getEmail());
		parameterMap.put("invoiceId", invoice.getInvoiceId());
		parameterMap.put("issuedDate", invoice.getIssuedDate());
		parameterMap.put("paymentDueDate", invoice.getPaymentDueDate());
		parameterMap.put("totalAmount", invoice.getTotalAmount());
		parameterMap.put("discount", invoice.getDiscount());
		parameterMap.put("vat", invoice.getVat());
		parameterMap.put("grandTotal", invoice.getGrandTotal());
		modelAndView = new ModelAndView("generateInvoiceReport", parameterMap);
		return modelAndView;

	}
	
	@RequestMapping(value = "/evaluation/{patientId}", method = RequestMethod.GET)
	public @ResponseBody ModelAndView generateEvaluationSheet(@PathVariable("patientId") Long patientId, ModelAndView modelAndView) {
		//List<ProgressReportDto> list = new ArrayList<ProgressReportDto>();
		
		///718 somehow doctor object not coming from frontend
		Doctor doctor = patientService.findDoctoerByPatientId(patientId);
		List<PatientMentalScale> mentalScales = mentalScalesService.getAllPatientMentalScalesByPatientId(patientId);
		Person person=personRepository.findByUserAccount_Role_RoleNameAndUserAccount_Company_Id("Super Admin",doctor.getCompany().getId());
		EvalutionSheet evalutionSheet = evalutionSheetService
				.getEvaluationSheetByPatientIdStatusInEvaluationReport(patientId);
		// list.addAll(createProgressReportDto(evalutionSheet));
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		List<EvalutionSheet> evalutionSheetList = new ArrayList<EvalutionSheet>();
		evalutionSheetList.add(evalutionSheet);
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(evalutionSheetList,true);
		JRDataSource JRdataMentalScalesSource = new JRBeanCollectionDataSource(mentalScales,true);
		parameterMap.put("dataSource", JRdataSource);
		parameterMap.put("mentalScalesDataSource", JRdataMentalScalesSource);
		if(person.getProfilePic() != null){
		parameterMap.put("companyLogo", person.getProfilePic());
		}
		if(person.getProfilePic() !=null){
		parameterMap.put("doctorSign",doctor.getSignaturePath());
		}
		modelAndView = new ModelAndView("generateEvaluationReport1", parameterMap);
		return modelAndView;

	}

}
