package com.openspace.HospitalMgnt.Template.GoalTemplateLookup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.GoalLookup;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.PatientGoal;
import com.openspace.Model.PatientMgnt.Repositories.GoalTemplateLookupRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientGoalRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.Template.GoalTemplateLookup;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class GoalTemplateLookupServiceImpl implements GoalTemplateLookupService {
	@Autowired
	private GoalTemplateLookupRepository goalTemplateLookupRepository;
	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientGoalRepository patientGoalRepository;

	@Override
	public void saveGoalTemplateLookup(GoalTemplateLookup goalTemplateLookup) {
		// TODO Auto-generated method stub
		if (goalTemplateLookup.getGoalTemplateLookupName() == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaseEnterGoalName);
		}
		GoalTemplateLookup dbGoalTemplateLookup = goalTemplateLookupRepository
				.findByGoalTemplateLookupName(goalTemplateLookup.getGoalTemplateLookupName());
		if (dbGoalTemplateLookup != null) {
			throw new RuntimeException(ErrorMessageHandler.goalTempplateLookupAlreadyExists);
		} else {
			goalTemplateLookupRepository.save(goalTemplateLookup);
		}
	}

	@Override
	public List<GoalTemplateLookup> getAllGoalTemplateLookups() {
		// TODO Auto-generated method stub
		return (List<GoalTemplateLookup>) goalTemplateLookupRepository.findAll();
	}

	@Override
	public void updateGoalTemplateLookup(GoalTemplateLookup goalTemplateLookup) {
		// TODO Auto-generated method stub
		GoalTemplateLookup dbGoalTemplateLookup = goalTemplateLookupRepository.findOne(goalTemplateLookup.getId());
		if (dbGoalTemplateLookup == null) {
			throw new RuntimeException(ErrorMessageHandler.goalLookupDoesNotExists);
		}
		GoalTemplateLookup dbGoalTemplatename = goalTemplateLookupRepository
				.findByGoalTemplateLookupName(goalTemplateLookup.getGoalTemplateLookupName());
		if (dbGoalTemplatename == null) {
			dbGoalTemplateLookup.setGoalTemplateLookupName(goalTemplateLookup.getGoalTemplateLookupName());
			dbGoalTemplateLookup.setAnswer(true);
			dbGoalTemplateLookup.setLogoUrl(goalTemplateLookup.getLogoUrl());
			
			goalTemplateLookupRepository.save(dbGoalTemplateLookup);
		} else if (dbGoalTemplatename.getId().equals(goalTemplateLookup.getId())) {
			dbGoalTemplateLookup.setGoalTemplateLookupName(goalTemplateLookup.getGoalTemplateLookupName());
			dbGoalTemplateLookup.setAnswer(true);
			dbGoalTemplateLookup.setLogoUrl(goalTemplateLookup.getLogoUrl());
			goalTemplateLookupRepository.save(dbGoalTemplateLookup);

		} else {
			throw new RuntimeException(ErrorMessageHandler.goalLookupAlreadyExists);
		}
		dbGoalTemplateLookup.setGoalTemplateLookupName(goalTemplateLookup.getGoalTemplateLookupName());
		dbGoalTemplateLookup.setAnswer(true);
		dbGoalTemplateLookup.setLogoUrl(goalTemplateLookup.getLogoUrl());
		goalTemplateLookupRepository.save(dbGoalTemplateLookup);

	}

	@Override
	public void deleteGoalTemplateLookup(Long id) {
		// TODO Auto-generated method stub
		GoalTemplateLookup dbGoalTemplateLookup = goalTemplateLookupRepository.findOne(id);
		if (dbGoalTemplateLookup == null) {
			throw new RuntimeException(ErrorMessageHandler.goalLookupDoesNotExists);
		}
		goalTemplateLookupRepository.delete(dbGoalTemplateLookup);
	}

	@Override
	public GoalLookup getGoalTemplateLookup(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GoalTemplateLookup> getAllGoalTemplateLookupsByPatient(Long patientId) {
		// TODO Auto-generated method stub
		List<GoalTemplateLookup> TemplateList = new ArrayList<GoalTemplateLookup>();
		if (patientId != null) {
			Patient patient = patientRepository.findOne(patientId);
			if (patient == null) {
				throw new RuntimeException(ErrorMessageHandler.patientNotFound);
			}
			// String fullname=patient.getFirstName()+" "+patient.getLastName();
			TemplateList = (List<GoalTemplateLookup>) goalTemplateLookupRepository.findAll();

			/*
			 * for (GoalTemplateLookup goalTemplateLookup : goalTemplateList) {
			 * String
			 * name=String.format(goalTemplateLookup.getGoalTemplateLookupName()
			 * ,fullname); goalTemplateLookup.setGoalTemplateLookupName(name);
			 * TemplateList.add(goalTemplateLookup); }
			 */
		}
		return TemplateList;
	}

	@Override
	public List<GoalTemplateLookup> getAllGoalLookupsByStatus(String status) {
		return goalTemplateLookupRepository.findByStatus(status);
	}

	@Override
	public void assignGoalsToPatient(List<GoalTemplateLookup> goalLookups, Long patientId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException(ErrorMessageHandler.pleaeSelectAPatient);
		}
		List<PatientGoal> patientGoals = new ArrayList<PatientGoal>();
		if (goalLookups != null) {
			for (GoalTemplateLookup goalLookup : goalLookups) {
				List<PatientGoal> dbPatientGoals = patientGoalRepository.findByPatient_IdAndGoalTemplateLookup_IdAndDate(dbPatient.getId(),
						goalLookup.getId(), LocalDate.now());
				if (dbPatientGoals.size()==0) {
					PatientGoal PatientGoal = convertToPatientGoal(goalLookup, dbPatient);
					if (PatientGoal != null) {
						patientGoals.add(PatientGoal);
					}
					if(PatientGoal != null){
						patientGoalRepository.save(PatientGoal);	
					}
				}
			}
			//patientGoalRepository.save(patientGoals);
		}
	}

	public PatientGoal convertToPatientGoal(GoalTemplateLookup goalLookup, Patient patient) {
		String gender="";
		if(patient.getGender().name().equalsIgnoreCase("MALE")){
			gender="He";
		}else{
			gender="She";
		}
		String name=gender+" "+goalLookup.getGoalTemplateLookupName();
		System.out.println("Gendaer "+name);
		PatientGoal patientGoal = new PatientGoal();
		patientGoal.setDate(LocalDate.now());
		patientGoal.setName(name);
		patientGoal.setPatient(patient);
		patientGoal.setStatus(false);
		// patientGoal.setAnswer(false);
		patientGoal.setGoalTemplateLookup(goalLookup);
		return patientGoal;
	}

}
