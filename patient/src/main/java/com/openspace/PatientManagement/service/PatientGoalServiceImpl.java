package com.openspace.PatientManagement.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.PatientGoal;
import com.openspace.Model.PatientMgnt.Repositories.PatientGoalRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.Template.GoalTemplateLookup;
import com.openspace.Model.Util.ErrorMessageHandler;
import com.openspace.PatientManagement.dto.PatientGoalDTo;
import com.openspace.PatientManagement.dto.PatientGoalDtoObject;
import com.openspace.PatientManagement.dto.PatientGoalsReportDto;

@Service
public class PatientGoalServiceImpl implements PatientGoalService {

	@Autowired
	PatientGoalRepository patientGoalRepository;

	@Autowired
	private PatientGoalService patientGoalService;

	@Autowired
	PatientRepository patientRepository;

	@Override
	public void savePatientGoals(PatientGoalDtoObject patientgoalDto, Long patientId) {
		// TODO Auto-generated method stub
		List<PatientGoal> dbPatientGoals = patientGoalRepository.findByPatient_IdAndDate(patientId, LocalDate.now());
		if (dbPatientGoals.size() == 0) {
			List<PatientGoal> patientGoals = new ArrayList<PatientGoal>();
			for (GoalTemplateLookup goalTemplateLookup : patientgoalDto.getGolaTemplatelookups()) {
				PatientGoal patientGoal = new PatientGoal();
				patientGoal.setName(goalTemplateLookup.getGoalTemplateLookupName());
				// patientGoal.setAnswer(AnswerStatus.NO);
				patientGoal.setDate(LocalDate.now());
				patientGoal.setStatus(true);
				patientGoal.setGoalTemplateLookup(goalTemplateLookup);
				Patient dbPatient = patientRepository.findOne(patientId);
				if (dbPatient != null) {
					patientGoal.setPatient(dbPatient);
				} else {
				}
				patientGoalRepository.save(patientGoal);
				patientGoals.add(patientGoal);
			}

		} else if (patientgoalDto.getGolaTemplatelookups() != null) {
			for (GoalTemplateLookup goalTemplateLookup : patientgoalDto.getGolaTemplatelookups()) {
				List<PatientGoal> dbPatientGoals1 = patientGoalRepository
						.findByPatient_IdAndGoalTemplateLookup_IdAndDate(patientId, goalTemplateLookup.getId(),
								LocalDate.now());
				if (dbPatientGoals.size() > 0) {
					// dbPatientGoal.setAnswer(goalTemplateLookup.getAnswer());
					Patient dbPatient = patientRepository.findOne(patientId);
					if (dbPatient != null) {
						// dbPatientGoal.setPatient(dbPatient);
					}
					// patientGoalRepository.save(dbPatientGoal);
				}
			}

		} else {
			for (PatientGoal patientGoal : patientgoalDto.getPatientGoals()) {
				PatientGoal dbPatientGoal = patientGoalRepository.findByPatient_IdAndIdAndDate(patientId,
						patientGoal.getId(), LocalDate.now());
				if (dbPatientGoal != null) {
					// dbPatientGoal.setAnswer(AnswerStatus.NO);
					Patient dbPatient = patientRepository.findOne(patientId);
					dbPatientGoal.setComment(patientGoal.getComment());
					dbPatientGoal.setPercentile(patientGoal.getPercentile());
					dbPatientGoal.setNoValue(patientGoal.getNoValue());
					dbPatientGoal.setYesValue(patientGoal.getYesValue());
					System.out.println("data is::"+patientgoalDto.getEvaluator());
					System.out.println("data is::"+patientgoalDto.getServiceCoordinator());
					dbPatientGoal.setEvaluator(patientgoalDto.getEvaluator());
					dbPatientGoal.setServiceCoordinator(patientgoalDto.getServiceCoordinator());
					if (dbPatient != null) {
						dbPatientGoal.setPatient(dbPatient);
					}
					patientGoalRepository.save(dbPatientGoal);
				}
			}

		}
	}

	@Override
	public List<PatientGoal> getPatientGoalsByPatientId(PatientGoalDTo patientGoalDTo) {
		String date = patientGoalDTo.getDate();
		System.out.println("date " + date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
		LocalDate createddate = LocalDate.parse(date, formatter);
		List<PatientGoal> patientGoalsList = new ArrayList<PatientGoal>();
		Patient patient = patientRepository.findOne(patientGoalDTo.getPatientId());
		List<PatientGoal> patientGoals = patientGoalRepository.findByPatient_IdAndDate(patientGoalDTo.getPatientId(),
				createddate);
		if (patientGoals.size() > 0) {
			for (PatientGoal patientGoal : patientGoals) {
				patientGoal.setName(String.format(patientGoal.getName(),patient.getFirstName()));
				patientGoalsList.add(patientGoal);
			}
		} else {
			TreeSet<String> savedDates = (TreeSet<String>) getAllPatientGoalsCreatedDates(
					patientGoalDTo.getPatientId());
			if (savedDates.size() > 0) {
				LocalDate previousDate = LocalDate.parse(savedDates.last(), formatter);
				List<PatientGoal> patientGoals1 = patientGoalRepository
						.findByPatient_IdAndDate(patientGoalDTo.getPatientId(), previousDate);
				List<PatientGoal> saveGoalsToPatientGoalsByToday = new ArrayList<PatientGoal>();
				if (patientGoals1.size() > 0) {
					for (PatientGoal patientGoal : patientGoals1) {
						PatientGoal patientGoal2 = new PatientGoal();
						patientGoal2.setDate(LocalDate.now());
						patientGoal2
								.setName(String.format(patientGoal.getName(),  patient.getFirstName()));
						patientGoal2.setPatient(patient);
						patientGoal2.setStatus(true);
						patientGoal2.setNoValue(null);
						patientGoal2.setYesValue(null);
						patientGoal2.setComment(null);
						/*patientGoal
								.setName(String.format(patientGoal.getName(),  patient.getFirstName()));
						// patientGoal.setAnswer(false);
						patientGoal.setComment(null);
						patientGoal.setNoValue(null);
						patientGoal.setYesValue(null);
						patientGoal.setPercentile(null);*/
						patientGoalsList.add(patientGoal);
						saveGoalsToPatientGoalsByToday.add(patientGoal2);
					}
					patientGoalRepository.save(saveGoalsToPatientGoalsByToday);
				}

			}
		}
		return patientGoalsList;
	}

	@Override
	public void updatePatientGoals(PatientGoal patientGoal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePatientGoals(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PatientGoal> getAllPatientGoalsByPatientId(Long patientId) {
		return patientGoalRepository.findByPatient_Id(patientId);
	}

	@Override
	public Set<String> getAllPatientGoalsCreatedDates(Long patientId) {
		TreeSet<String> dates = new TreeSet<String>();
		List<PatientGoal> patientGoals = patientGoalRepository.findByPatient_Id(patientId);
		for (PatientGoal patientGoal : patientGoals) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			dates.add(patientGoal.getDate().format(formatter));
		}

		return dates;
	}

	@Override
	public List<PatientGoal> getPatientGoalsByPatientId(Long patientId, String createdDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGoalsByPatient_IdAndAnswerAndDate(Long patientId, boolean status, LocalDate todayDate) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient != null) {
		}
		List<PatientGoal> patientList = new ArrayList<PatientGoal>();
		patientList = patientGoalRepository.findByPatient_IdAndAnswerAndDate(dbPatient.getId(), status, todayDate);

		return patientList.size();
	}

	@Override
	public PatientGoalsReportDto getPatientGoalsInPresentWeek(Long patientId) {
		String[] patientGoalseries = { "PatientYesGoals", "PatientNoGoals" };
		List<String> patientGoalseriesList = new ArrayList<String>();
		for (int i = 0; i < patientGoalseries.length; i++) {
			patientGoalseriesList.add(patientGoalseries[i]);
		}
		List<String> patientGoallablesList = new ArrayList<String>();
		ArrayList<Integer> patientGoalcompleteList = new ArrayList<>();
		ArrayList<Integer> patientGoalIncompleteList = new ArrayList<>();
		for (int i = 1; i < 7; i++) {
			LocalDate now = LocalDate.now();
			TemporalField fieldUS = WeekFields.of(Locale.FRANCE).dayOfWeek();
			LocalDate presentDate = now.with(fieldUS, i);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
			String formattedString = presentDate.format(formatter);
			patientGoallablesList.add(formattedString);
			int patientgoalcomplete = patientGoalService.getGoalsByPatient_IdAndAnswerAndDate(patientId, true,
					presentDate);
			patientGoalcompleteList.add(patientgoalcomplete);

			int patientgoalincomplete = patientGoalService.getGoalsByPatient_IdAndAnswerAndDate(patientId, false,
					presentDate);
			patientGoalIncompleteList.add(patientgoalincomplete);
		}
		PatientGoalsReportDto report = new PatientGoalsReportDto();
		report.setSeries(patientGoalseriesList);
		report.setLabels(patientGoallablesList);
		List<ArrayList<Integer>> data = new ArrayList<>();
		data.add(patientGoalcompleteList);
		data.add(patientGoalIncompleteList);
		report.setData(data);
		return report;
	}

	@Override
	public void assignGoalsToPatient(Patient patient, List<GoalTemplateLookup> goalTemplatelookups) {
		Patient dbPatient = patientRepository.findOne(patient.getId());
		if (dbPatient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		List<PatientGoal> selectedPatientGoals = new ArrayList<PatientGoal>();
		List<PatientGoal> patientGoals = patientGoalRepository.findByPatient_IdAndDate(dbPatient.getId(),
				LocalDate.now());
		if (patientGoals == null) {
			for (GoalTemplateLookup goalTemplateLookup : goalTemplatelookups) {
				if (goalTemplateLookup.isSelected() == true) {
					PatientGoal patientGoal = convertGoalLookupToPatientGoal(goalTemplateLookup, dbPatient);
					selectedPatientGoals.add(patientGoal);
				}
			}
			patientGoalRepository.save(selectedPatientGoals);
		} else {

		}

	}

	public PatientGoal convertGoalLookupToPatientGoal(GoalTemplateLookup goalTemplateLookup, Patient patient) {
		System.out.println("Gender "+patient.getGender().name());  
		String name=patient.getGender().name()+"  "+goalTemplateLookup.getGoalTemplateLookupName();
		PatientGoal patientGoal = new PatientGoal();
		patientGoal.setName(name);
		// patientGoal.setAnswer(false);
		patientGoal.setPatient(patient);
		patientGoal.setDate(LocalDate.now());
		patientGoal.setStatus(true);
		patientGoal.setGoalTemplateLookup(goalTemplateLookup);
		return patientGoal;
	}

	@Override
	public void deletePatientGoalById(Long patientId, Long goalId) {

		List<PatientGoal> patientGoals = patientGoalRepository.findByPatient_IdAndDateAndId(patientId, LocalDate.now(),
				goalId);
		patientGoalRepository.delete(patientGoals);
	}

	@Override
	public List<PatientGoal> findAllPatientGoalsByPatient(Long patientId) {
		return patientGoalRepository.findByPatient_IdAndDate(patientId, LocalDate.now());
	}
}
