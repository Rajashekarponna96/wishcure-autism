package com.openspace.PatientManagement.dashboard;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Appointment;
import com.openspace.Model.DoctorManagement.Doctor;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.PatientMgnt.Repositories.AppointmentRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.TherapistRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.UserAccount;

@Service
public class DashboardServiceImpl implements DashboardService {
	@Autowired
	private TherapistRepository therapistRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DashboardService reportService;

	@Override
	public int findActiveDoctorBydates(String adminUserName, LocalDate startDatelocaldate, LocalDate endDatelocaldate) {
		int i=0;
		UserAccount dbUser = userAccountRepository.findByUsername(adminUserName);
		if (dbUser == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person dbPerson = personRepository.findByUserAccount_Id(dbUser.getId());
		if (dbPerson == null) {
			throw new RuntimeException("User Does not Exist");
		}
		if(dbUser.getCompany()==null){
			return i;
		}
		List<UserAccount> dbUserList = null;
		if (dbPerson.getUserAccount().getCompany() != null) {
			dbUserList = userAccountRepository.findByCompany_IdAndActive(dbPerson.getUserAccount().getCompany().getId(),
					true);
		}
		List<Person> personList = new ArrayList<Person>();

		for (UserAccount useraccount : dbUserList) {
			Person person = personRepository.findByUserAccount_Id(useraccount.getId());
			personList.add(person);
		}
		List<Doctor> doctorList = new ArrayList<Doctor>();

		for (Person person : personList) {
			doctorList = therapistRepository.findByCompany_IdAndActiveAndCreatedDateBetween(
					dbUser.getCompany().getId(), true, startDatelocaldate, endDatelocaldate);
		}
		i=doctorList.size();
		return i;
	}

	@Override
	public int findRegistrationsBydates(String adminUserName, LocalDate startDatelocaldate,
			LocalDate endDatelocaldate) {
		int i=0;
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		if(dbUserAccount==null){
			return i;
		}
		List<Person> persons = personRepository.findByUserAccount_Company_IdAndCreatedDateBetween(
				dbUserAccount.getCompany().getId(), startDatelocaldate, endDatelocaldate);
		i=persons.size();
		return i;
	}

	public int totalPatientsRegistered(String adminUserName, LocalDate startDatelocaldate, LocalDate endDatelocaldate) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		List<Appointment> appointments = appointmentRepository
				.findByDoctor_IdAndAppointmentStartedDateGreaterThanEqualAndAppointmentEndedDateLessThanEqual(
						person.getId(), startDatelocaldate, endDatelocaldate);
		List<Patient> patientsList = new ArrayList<Patient>();

		if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
			for (Appointment appointment : appointments) {
				Patient patient = patientRepository.findByIdAndActive(appointment.getPatient().getId(), true);
				patientsList.add(patient);
			}
		} else {
			if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
				patientsList = patientRepository.findByAdminUserAndActiveAndCreatedDateBetween(adminUserName, true,
						startDatelocaldate, endDatelocaldate);
			} else {
				patientsList = patientRepository.findByAdminUserAndActiveAndCreatedDateBetween(adminUserName, true,
						startDatelocaldate, endDatelocaldate);
			}
		}
		return patientsList.size();

	}

	@Override
	public DashboardDto getTotalPatientsRegisteredChartDataPerPresentYear(String adminUserName, int year) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Does not Exist");
		}
		DashboardDto report = new DashboardDto();
		
		if(dbUserAccount.getRole().getRoleName().equals("Therapist")){
			String[] series = { "TotalPatientsEngaged" };
			String[] lables = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
					"October", "November", "December" };
			List<String> seriesList = new ArrayList<String>();
			for (int i = 0; i < series.length; i++) {
				seriesList.add(series[i]);
			}
			List<String> lablesList = new ArrayList<String>();
			for (int i = 0; i < lables.length; i++) {
				lablesList.add(lables[i]);
			}
			LocalDate localDate = LocalDate.now();
			int lastdayMonth = localDate.lengthOfMonth();
			ArrayList<Integer> totalPatientsRegisteredList = new ArrayList<>();
			for (int j = 1; j <= 12; j++) {
				LocalDate startDatelocaldate = LocalDate.of(year, j, 1);
				LocalDate endDatelocaldate = LocalDate.of(year, j, startDatelocaldate.lengthOfMonth());
				int totalPatientsRegistered = reportService.totalPatientsRegistered(adminUserName, startDatelocaldate,
						endDatelocaldate);
				totalPatientsRegisteredList.add(totalPatientsRegistered);
			}
			report.setSeries(seriesList);
			report.setLabels(lablesList);
			List<ArrayList<Integer>> data = new ArrayList<>();
			data.add(totalPatientsRegisteredList);
			report.setData(data);
		
		}else{
		String[] series = { "TotalPatientsRegistered" };
		String[] lables = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		List<String> seriesList = new ArrayList<String>();
		for (int i = 0; i < series.length; i++) {
			seriesList.add(series[i]);
		}
		List<String> lablesList = new ArrayList<String>();
		for (int i = 0; i < lables.length; i++) {
			lablesList.add(lables[i]);
		}
		LocalDate localDate = LocalDate.now();
		int lastdayMonth = localDate.lengthOfMonth();
		ArrayList<Integer> totalPatientsRegisteredList = new ArrayList<>();
		for (int j = 1; j <= 12; j++) {
			LocalDate startDatelocaldate = LocalDate.of(year, j, 1);
			LocalDate endDatelocaldate = LocalDate.of(year, j, startDatelocaldate.lengthOfMonth());
			int totalPatientsRegistered = reportService.totalPatientsRegistered(adminUserName, startDatelocaldate,
					endDatelocaldate);
			totalPatientsRegisteredList.add(totalPatientsRegistered);
		}
		report.setSeries(seriesList);
		report.setLabels(lablesList);
		List<ArrayList<Integer>> data = new ArrayList<>();
		data.add(totalPatientsRegisteredList);
		report.setData(data);
		}
		return report;
	}

	@Override
	public int findTodayPatientsByPresentDate(String adminUserName, LocalDate presentDate) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		List<Patient> patientsList = new ArrayList<Patient>();
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Does not Exist");
		}
		if (dbUserAccount.getRole().getRoleName().equals("Therapist")) {
			List<Appointment> appointments = appointmentRepository
					.findByDoctor_IdAndAppointmentStartedDate(person.getId(), presentDate);
			for (Appointment appointment : appointments) {
				Patient patient = patientRepository.findByIdAndActive(appointment.getPatient().getId(), true);
				patientsList.add(patient);
			}
		} else {
			if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
				patientsList = patientRepository.findByAdminUserAndActiveAndCreatedDate(dbUserAccount.getUsername(),
						true, presentDate);
			} else {
				patientsList = patientRepository.findByAdminUserAndCompany_IdAndActiveAndCreatedDate(adminUserName,
						dbUserAccount.getCompany().getId(), true, presentDate);
			}
		}
		return patientsList.size();
	}

	@Override
	public DashboardDto findTodayPatientsChartDataByPresentWeek(String adminUserName) {
		// TODO Auto-generated method stub
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Does not Exist");
		}
		String[] todayPatientsseries = { "TodayPatients" };
		String[] todayPatientslabels = { "Monday", "Tuesday", "Wensday", "Thursday", "Friday", "Saturday", "Sunday" };
		List<String> todayPatientseriesList = new ArrayList<String>();
		for (int i = 0; i < todayPatientsseries.length; i++) {
			todayPatientseriesList.add(todayPatientsseries[i]);
		}
		List<String> todayPatientlablesList = new ArrayList<String>();
		for (int i = 0; i < todayPatientslabels.length; i++) {
			todayPatientlablesList.add(todayPatientslabels[i]);
		}
		ArrayList<Integer> todayPatientsList = new ArrayList<>();
		for (int i = 1; i < todayPatientslabels.length; i++) {
			LocalDate now = LocalDate.now();
			TemporalField fieldUS = WeekFields.of(Locale.FRANCE).dayOfWeek();
			LocalDate presentDate = now.with(fieldUS, i);
			int todayPatients = reportService.findTodayPatientsByPresentDate(adminUserName, presentDate);
			todayPatientsList.add(todayPatients);
		}
		DashboardDto report = new DashboardDto();
		report.setSeries(todayPatientseriesList);
		report.setLabels(todayPatientlablesList);
		List<ArrayList<Integer>> data = new ArrayList<>();
		data.add(todayPatientsList);

		report.setData(data);
		return report;
	}

	@Override
	public DashboardDto getActiveDoctorsChartDataPerPresentYear(String adminUserName, int year) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		if (person == null) {
			throw new RuntimeException("Person Does not Exist");
		}
		String[] series = { "Active Doctors" };
		String[] lables = { "January", "February", "March", "April", "May", "June", "July", "Auguest", "Septmber",
				"Octomber", "November", "December" };
		List<String> seriesList = new ArrayList<String>();

		for (int i = 0; i < series.length; i++) {
			seriesList.add(series[i]);
		}
		List<String> lablesList = new ArrayList<String>();
		for (int i = 0; i < lables.length; i++) {
			lablesList.add(lables[i]);
		}
		DashboardDto report = new DashboardDto();
		LocalDate localDate = LocalDate.now();
		int lastdayMonth = localDate.lengthOfMonth();
		ArrayList<Integer> activeDocoters = new ArrayList<>();
		for (int j = 1; j <= 12; j++) {
			LocalDate startDatelocaldate = LocalDate.of(year, j, 1);
			LocalDate endDatelocaldate = LocalDate.of(year, j, startDatelocaldate.lengthOfMonth());
			int activeDocoter = reportService.findActiveDoctorBydates(dbUserAccount.getUsername(), startDatelocaldate,
					endDatelocaldate);
			activeDocoters.add(activeDocoter);
		}
		report.setSeries(seriesList);
		report.setLabels(lablesList);
		List<ArrayList<Integer>> data = new ArrayList<>();
		data.add(activeDocoters);
		report.setData(data);
		return report;
	}

	@Override
	public DashboardDto getActiveUsersChartDataPerPresentYear(String adminUserName, int year) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(adminUserName);
		if (dbUserAccount == null) {
			throw new RuntimeException("User Does not Exist");
		}
		Person person = personRepository.findByUserAccount_Id(dbUserAccount.getId());
		DashboardDto report = new DashboardDto();

		String[] series = { "Active Users" };
		String[] lables = { "January", "February", "March", "April", "May", "June", "July", "Auguest", "Septmber",
				"Octomber", "November", "December" };
		List<String> seriesList = new ArrayList<String>();

		for (int i = 0; i < series.length; i++) {
			seriesList.add(series[i]);
		}
		List<String> lablesList = new ArrayList<String>();
		for (int i = 0; i < lables.length; i++) {
			lablesList.add(lables[i]);
		}
		LocalDate localDate = LocalDate.now();
		int lastdayMonth = localDate.lengthOfMonth();
		ArrayList<Integer> registrations = new ArrayList<>();
		for (int j = 1; j <= 12; j++) {
			LocalDate startDatelocaldate = LocalDate.of(year, j, 1);
			LocalDate endDatelocaldate = LocalDate.of(year, j, startDatelocaldate.lengthOfMonth());
			int registration = reportService.findRegistrationsBydates(dbUserAccount.getUsername(), startDatelocaldate,
					endDatelocaldate);
			registrations.add(registration);
		}
		report.setSeries(seriesList);
		report.setLabels(lablesList);
		List<ArrayList<Integer>> data = new ArrayList<>();
		data.add(registrations);
		report.setData(data);
		return report;
	}

}
