package com.openspace.PatientManagement.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.DoctorManagement.EvalutionSheet;
import com.openspace.Model.Dto.EvalutionSheetDto;
import com.openspace.PatientManagement.IASSBehaviour.ISAABehaviorCategoryService;
import com.openspace.PatientManagement.Mchart.MchartService;
import com.openspace.PatientManagement.NICHQParent.NichqParentQuestionService;
import com.openspace.PatientManagement.NICHQTeacher.PatientNichqTeacherQuestionService;
import com.openspace.PatientManagement.Scales.VSMSQuestionsService;
import com.openspace.PatientManagement.dto.EvalutionDateMapDTO;
import com.openspace.PatientManagement.dto.EvalutionSheetDateDto;
import com.openspace.PatientManagement.dto.ProgressSheetDateDto;
import com.openspace.PatientManagement.service.BehaviourlManagementService;
import com.openspace.PatientManagement.service.EvalutionSheetService;
import com.openspace.PatientManagement.service.SpeechEvaluationService;

@RestController
public class EvalutionSheetController {

	@Autowired
	private EvalutionSheetService evalutionSheetService;
	
	@Autowired
	private SpeechEvaluationService speechEvaluationService;
	
	@Autowired
	private NichqParentQuestionService nichqParentQuestionService;
	
	@Autowired
	private BehaviourlManagementService behaviourlManagementService;
	
	@Autowired
	private PatientNichqTeacherQuestionService patientNichqTeacherQuestionService;
	
	@Autowired
	private MchartService mchartService;
	
	@Autowired
	private ISAABehaviorCategoryService iSAABehaviorCategoryService;
	
	@Autowired
	private	VSMSQuestionsService vsmsQuestionsService;
	
	

	@RequestMapping(value = "/saveEvalutionSheet", method = RequestMethod.POST)
	public @ResponseBody void saveEvalutionSheet(@RequestBody EvalutionSheetDto evalutionSheetDto) {
		evalutionSheetService.saveEvalutionSheet(evalutionSheetDto);
	}
	@RequestMapping(value = "/savePatientEvalutionSheet", method = RequestMethod.POST)
	public @ResponseBody void savePatientEvalutionSheet(@RequestBody EvalutionSheetDto evalutionSheetDto) {
		System.out.println("controller");
		evalutionSheetService.savePatientEvalutionSheet(evalutionSheetDto);
	}
	@RequestMapping(value = "/saveProgressNote", method = RequestMethod.POST)
	public @ResponseBody void saveProgressNote(@RequestBody EvalutionSheet evalutionSheet) {
		evalutionSheetService.saveProgressNote(evalutionSheet);
	}
	@RequestMapping(value = "/saveExitNote", method = RequestMethod.POST)
	public @ResponseBody void saveExitNote(@RequestBody EvalutionSheet evalutionSheet) {
		evalutionSheetService.saveExitNote(evalutionSheet);
	}

	@RequestMapping(value = "/getAllEvalutionSheet", method = RequestMethod.GET)
	public @ResponseBody List<EvalutionSheet> getAllSheets() {
		return evalutionSheetService.getAllSheets();
	}

	@RequestMapping(value = "/updateEvalutionSheet", method = RequestMethod.PUT)
	public @ResponseBody void updateEvalutionSheet(@RequestBody EvalutionSheet evalutionSheet) {
		evalutionSheetService.updateEvalutionSheet(evalutionSheet);
	}

	@RequestMapping(value = "/deleteEvalutionSheet/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteEvalutionSheet(@PathVariable Long id) {
		evalutionSheetService.deleteEvalutionSheet(id);
	}

	@RequestMapping(value = "/getEvalutionSheetBy/{patientId}/status/{status}", method = RequestMethod.GET)
	public @ResponseBody EvalutionSheet getEvaluationSheetByPatientIdStatus(@PathVariable("patientId") Long patientId,
			@PathVariable("status") String status) {
		return evalutionSheetService.getEvaluationSheetByPatientIdStatus(patientId, status);
	}

	@RequestMapping(value = "/getProgressSheetBy/{patientId}", method = RequestMethod.GET)
	public @ResponseBody EvalutionSheet getEvaluationSheetByPatientIdStatusInProgress(
			@PathVariable("patientId") Long patientId) {
		return evalutionSheetService.getEvaluationSheetByPatientIdStatusInProgress(patientId);
	}
	
	@RequestMapping(value = "/getExitSheetBy/{patientId}", method = RequestMethod.GET)
	public @ResponseBody EvalutionSheet getEvaluationSheetByPatientIdStatusInExit(
			@PathVariable("patientId") Long patientId) {
		return evalutionSheetService.getEvaluationSheetByPatientIdStatusInExit(patientId);
	}
	
	
	@RequestMapping(value = "/getProgressSheetReportBy/{patientId}", method = RequestMethod.GET)
	public @ResponseBody EvalutionSheet getEvaluationSheetByPatientIdStatusInProgressReport(
			@PathVariable("patientId") Long patientId) {
		return evalutionSheetService.getEvaluationSheetByPatientIdStatusInProgressReport(patientId);
	}
	
	@RequestMapping(value = "/getProgressSheetDatesBy/{patientId}", method = RequestMethod.GET)
	public @ResponseBody Set<String> getProgressSheetDatesByPatientId(
			@PathVariable("patientId") Long patientId) {
		return evalutionSheetService.getProgressSheetDatesByPatientId(patientId);
	}
	
	@RequestMapping(value = "/getEvalutionSheetDatesBy/{patientId}", method = RequestMethod.GET)
	public @ResponseBody List<EvalutionDateMapDTO> getEvalutionSheetDatesByPatientId(
			@PathVariable("patientId") Long patientId) {
		List<EvalutionDateMapDTO> dateList = new ArrayList<>();
		
		EvalutionDateMapDTO evalutionDateMapDTO = new EvalutionDateMapDTO();
		Set<String> evaluationSheetDates = evalutionSheetService.getEvalutionSheetDatesByPatientId(patientId);
		if(!evaluationSheetDates.isEmpty() && evaluationSheetDates != null) {
		evalutionDateMapDTO.setDate(evaluationSheetDates.iterator().next());
		evalutionDateMapDTO.setEvalutionSheetType("EvalutionReport");
		dateList.add(evalutionDateMapDTO);
		}
		EvalutionDateMapDTO evalutionDateMapDTO1 = new EvalutionDateMapDTO();
		Set<String> speechEvaluationDates = speechEvaluationService.getEvalutionSheetDatesByPatientId(patientId);
		if (!speechEvaluationDates.isEmpty()  && speechEvaluationDates != null) {
			evalutionDateMapDTO1.setDate(speechEvaluationDates.iterator().next());
			evalutionDateMapDTO1.setEvalutionSheetType("SpeechEvalution");
			dateList.add(evalutionDateMapDTO1);
		}
		 EvalutionDateMapDTO evalutionDateMapDTO2 = new EvalutionDateMapDTO();
		 Set<String> nichqEvaluationDates= nichqParentQuestionService.getEvalutionSheetDatesByPatientId(patientId);
		 if(!nichqEvaluationDates.isEmpty()  && nichqEvaluationDates != null) {
		 evalutionDateMapDTO2.setDate(nichqEvaluationDates.iterator().next());
		 evalutionDateMapDTO2.setEvalutionSheetType("NichqParentQuestion");
		  dateList.add(evalutionDateMapDTO2);
		  
		 }
		 
		
		 EvalutionDateMapDTO evalutionDateMapDTO3 = new EvalutionDateMapDTO();
		 Set<String> nichqTeacherEvaluationDates= patientNichqTeacherQuestionService.getEvalutionSheetDatesByPatientId(patientId);
		 if(!nichqTeacherEvaluationDates.isEmpty()  && nichqTeacherEvaluationDates != null) {
			 evalutionDateMapDTO3.setDate(nichqTeacherEvaluationDates.iterator().next());
			 evalutionDateMapDTO3.setEvalutionSheetType("PatientNichqTeacherQuestion");
		  dateList.add(evalutionDateMapDTO3);
		  
		 }
		 
		 
		 
		  EvalutionDateMapDTO evalutionDateMapDTO4 = new EvalutionDateMapDTO();
		  
		  Set<String> behaviourMangementProgrammeDates = behaviourlManagementService.getEvalutionSheetDatesByPatientId(patientId); 
		  if(!behaviourMangementProgrammeDates.isEmpty() && behaviourMangementProgrammeDates != null) {
			evalutionDateMapDTO4.setDate(behaviourMangementProgrammeDates.iterator().next());
			evalutionDateMapDTO4.setEvalutionSheetType("BehaviourMangementProgramme");
			dateList.add(evalutionDateMapDTO4);
		  }
		  
		  EvalutionDateMapDTO evalutionDateMapDTO5 = new EvalutionDateMapDTO();
		  
		  Set<String> mchartDates = mchartService.getEvalutionSheetDatesByPatientId(patientId); 
		  if(!mchartDates.isEmpty() && mchartDates != null) {
			  evalutionDateMapDTO5.setDate(mchartDates.iterator().next());
			  evalutionDateMapDTO5.setEvalutionSheetType("Mchart");
			dateList.add(evalutionDateMapDTO5);
		  }
		  
		  EvalutionDateMapDTO evalutionDateMapDTO6 = new EvalutionDateMapDTO();
		  
		  Set<String> isaaBehaviouralDates = iSAABehaviorCategoryService.getEvalutionSheetDatesByPatientId(patientId); 
		  if(!isaaBehaviouralDates.isEmpty() && isaaBehaviouralDates != null) {
			  evalutionDateMapDTO6.setDate(isaaBehaviouralDates.iterator().next());
			  evalutionDateMapDTO6.setEvalutionSheetType("Isaa Behavioural");
			dateList.add(evalutionDateMapDTO6);
		  }
		    
	  
	  EvalutionDateMapDTO evalutionDateMapDTO7 = new EvalutionDateMapDTO();
	  
	  Set<String> vsmsDates = vsmsQuestionsService.getEvalutionSheetDatesByPatientId(patientId); 
	  if(!vsmsDates.isEmpty() && vsmsDates != null) {
		  evalutionDateMapDTO7.setDate(vsmsDates.iterator().next());
		  evalutionDateMapDTO7.setEvalutionSheetType("VSMS");
		dateList.add(evalutionDateMapDTO7);
	  }
	  
	  
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
	    Collections.sort(dateList, (s1, s2) -> LocalDate.parse(s1.getDate() , formatter).
	            compareTo(LocalDate.parse(s2.getDate(), formatter)));
		  
		return dateList;
		
	}
	
	@RequestMapping(value = "/getProgressSheetByDate", method = RequestMethod.POST)
	public @ResponseBody EvalutionSheet getProgressSheetByDateAndStatus(
			@RequestBody ProgressSheetDateDto progressSheetDateDto) {
		return evalutionSheetService.getProgressSheetByDateAndStatus(progressSheetDateDto);
	}
	
	@RequestMapping(value = "/getEvalutionSheetDate", method = RequestMethod.POST)
	public @ResponseBody EvalutionSheet getProgressSheetByDateAndStatus(
			@RequestBody EvalutionSheetDateDto evalutionSheetDateDto) {
		return evalutionSheetService.getEvalutionSheetByDateAndStatus(evalutionSheetDateDto);
	}
	
	
	
}
