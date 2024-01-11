package com.openspace.PatientManagement.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.PatientGoal;
import com.openspace.Model.Template.GoalTemplateLookup;
import com.openspace.PatientManagement.dto.PatientGoalDTo;
import com.openspace.PatientManagement.dto.PatientGoalDtoObject;
import com.openspace.PatientManagement.dto.PatientGoalsReportDto;
import com.openspace.PatientManagement.service.PatientGoalService;

@RestController
public class PatientGoalController {

	@Autowired
	private PatientGoalService patientGoalService;

	@RequestMapping(value = "/savePatientGoals/{patientId}", method = RequestMethod.POST)
	public @ResponseBody void savePatientGoals(@RequestBody PatientGoalDtoObject patientgoalDto,
			@PathVariable("patientId") Long patientId) {
		patientGoalService.savePatientGoals(patientgoalDto, patientId);
	}

	@RequestMapping(value = "/getAllPatientGoals", method = RequestMethod.POST)
	public @ResponseBody List<PatientGoal> getPatientGoalsByPatientId(@RequestBody PatientGoalDTo patientGoalDTo) {
		return patientGoalService.getPatientGoalsByPatientId(patientGoalDTo);
	}

	@RequestMapping(value = "/updatePatientGoals", method = RequestMethod.PUT)
	public @ResponseBody void updatePatientGoals(@RequestBody PatientGoal patientGoal) {
		patientGoalService.updatePatientGoals(patientGoal);
	}

	@RequestMapping(value = "/deletePatientGoals/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deletePatientGoals(@PathVariable Long id) {
		patientGoalService.deletePatientGoals(id);
	}

	@RequestMapping(value = "/getAllPatientGoals/{patientId}", method = RequestMethod.GET)
	public @ResponseBody List<PatientGoal> getPatientGoalsByPatientId(@PathVariable("patientId") Long patientId) {
		return patientGoalService.getAllPatientGoalsByPatientId(patientId);
	}
	
	@RequestMapping(value = "/getAllPatientGoalsCreatedDates/{patientId}", method = RequestMethod.GET)
	public @ResponseBody Set<String> getAllPatientGoalsCreatedDates(@PathVariable("patientId") Long patientId) {
		return patientGoalService.getAllPatientGoalsCreatedDates(patientId);
	}
	
	@RequestMapping(value = "/getAllPatientGoalsInPresentWeek/{patientId}", method = RequestMethod.GET)
	public @ResponseBody PatientGoalsReportDto getPatientGoalsInPresentWk(@PathVariable("patientId") Long patientId) {
		return patientGoalService.getPatientGoalsInPresentWeek(patientId);
	}
	
	@RequestMapping(value = "/assignGoalsToPatient/", method = RequestMethod.POST)
	public @ResponseBody void assignGoalsToPatient(@RequestBody Patient patient,@RequestBody List<GoalTemplateLookup> goalTemplatelookups) {
		 patientGoalService.assignGoalsToPatient(patient,goalTemplatelookups);
	}
	
	@RequestMapping(value = "/deletePatientGoalById/{patientId}/{goalId}", method = RequestMethod.DELETE)
	public @ResponseBody void deletePatientGoalById(@PathVariable("patientId") Long patientId,@PathVariable("goalId") Long goalId) {
		patientGoalService.deletePatientGoalById(patientId,goalId);
	}
	
	@RequestMapping(value = "/getAllPatientGoalsByPatient/{patientId}", method = RequestMethod.GET)
	public @ResponseBody List<PatientGoal> findAllPatientGoalsByPatient(@PathVariable("patientId") Long patientId) {
		return patientGoalService.findAllPatientGoalsByPatient(patientId);
	}
	
	

}
