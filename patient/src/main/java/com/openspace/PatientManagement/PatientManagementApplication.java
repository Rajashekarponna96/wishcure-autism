
package com.openspace.PatientManagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.PatientMgnt.Repositories.PersonRepository;
import com.openspace.Model.PatientMgnt.Repositories.StripePlanRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.Stripe.StripePackage;
import com.openspace.Model.Stripe.StripePlan;
import com.openspace.Model.UserManagement.UserAccount;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Subscription;

@SpringBootApplication
@EnableWebMvc
@EntityScan(basePackages = { "com.openspace.*" })
@ComponentScan(basePackages = { "com.openspace.*" })
@EnableAutoConfiguration
@Configuration
@EnableScheduling
public class PatientManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientManagementApplication.class, args);
	}

	@Bean
	JasperReportsPdfView generateProgressSheet() {
		JasperReportsPdfView pdfView = new JasperReportsPdfView();
		pdfView.setUrl("classpath:patientprogressnote1.jrxml");
		pdfView.setReportDataKey("dataSource");
		return pdfView;
	}

	@Bean
	JasperReportsPdfView generateEvaluationReport() {
		JasperReportsPdfView pdfView = new JasperReportsPdfView();
		pdfView.setUrl("classpath:patientEvaluationReport.jrxml");
		pdfView.setReportDataKey("dataSource");
		return pdfView;
	}

	@Bean
	JasperReportsPdfView generateEvaluationReport1() {
		JasperReportsPdfView pdfView = new JasperReportsPdfView();
		pdfView.setUrl("classpath:patientEvaluationReport1.jrxml");
		pdfView.setReportDataKey("dataSource");
		return pdfView;
	}

	@Bean
	JasperReportsPdfView generateExitNoteReport() {
		JasperReportsPdfView pdfView = new JasperReportsPdfView();
		pdfView.setUrl("classpath:patientExitNoteReport1.jrxml");
		pdfView.setReportDataKey("dataSource");
		return pdfView;
	}

	@Bean
	JasperReportsPdfView generateInvoiceReport() {
		JasperReportsPdfView pdfView = new JasperReportsPdfView();
		pdfView.setUrl("classpath:invoiceReport.jrxml");
		pdfView.setReportDataKey("dataSource");
		return pdfView;
	}

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private StripePlanRepository stripePlanRepository;

	@Autowired
	private PersonRepository personRepository;

	@Scheduled(cron = "0 1 12 * * ?")
	public int subScribeTheRegisterUsers() throws Exception {
		System.out.println("schedular starts  ***********************&&&&");
		List<UserAccount> dbUserAccounts = userAccountRepository.findByRegisteredUser(true);
		for (UserAccount user : dbUserAccounts) {
			int i = 0;
			UserAccount dbUserAccount = userAccountRepository.findByUsername(user.getUsername());
			if (dbUserAccount == null) {
				throw new RuntimeException("User Does not Exist");
			}
			if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
				i = 1;
			} else {
				List<UserAccount> dbUseraccountList = new ArrayList<UserAccount>();
				if (dbUserAccount.getCompany() != null) {
					dbUseraccountList = userAccountRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
				}
				List<Person> persons = new ArrayList<Person>();
				for (UserAccount userAccount : dbUseraccountList) {
					Person person = personRepository.findByUserAccount_Id(userAccount.getId());
					persons.add(person);
				}
				i = persons.size();
			}

			StripePackage dbStripePackage = user.getStripePackage();
			if (dbStripePackage != null && dbStripePackage.getPackageId() != null) {
				StripePlan dbStripePlan = stripePlanRepository.findByProductId(dbStripePackage.getPackageId());
				if (dbStripePlan != null && dbStripePlan.getTrialPeriodDays() != null) {
					LocalDate trialPeriodEndDate = user.getRegisteredDate().plusDays(dbStripePlan.getTrialPeriodDays());
					if (trialPeriodEndDate.equals(LocalDate.now())) {
						Map<String, Object> planParam = new HashMap<>();
						planParam.put("plan", dbStripePlan.getPlanId());
						planParam.put("quantity", i);
						// planParam.put("tiers_mode", "volume");
						Map<String, Object> items = new HashMap<>();
						items.put("0", planParam);
						Map<String, Object> params = new HashMap<>();
						params.put("customer", user.getCustomerId());
						params.put("items", items);
						params.put("quantity", i);
						Subscription sub = Subscription.create(params);
						user.setSubScriptionId(sub.getId());
						userAccountRepository.save(user);
					}
				}
			}
		}
		return 0;
	}

   @Scheduled(cron = "0 1 12 * * ?")
	public int upDateSubscriptionToCustomer() throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		List<UserAccount> dbUserAccounts = userAccountRepository.findByRegisteredUser(true);
		for (UserAccount user : dbUserAccounts) {

			int i = 0;
			UserAccount dbUserAccount = userAccountRepository.findByUsername(user.getUsername());
			if (dbUserAccount == null) {
				throw new RuntimeException("User Does not Exist");
			}
			if (dbUserAccount.getRole().getRoleName().equals("Individual")) {
				i = 1;
			} else {
				List<UserAccount> dbUseraccountList = new ArrayList<UserAccount>();
				if (dbUserAccount.getCompany() != null) {
					dbUseraccountList = userAccountRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
				}
				List<Person> persons = new ArrayList<Person>();
				for (UserAccount userAccount : dbUseraccountList) {
					Person person = personRepository.findByUserAccount_Id(userAccount.getId());
					persons.add(person);
				}
				i = persons.size();
			}

			StripePackage dbStripePackage = dbUserAccount.getStripePackage();
			if (dbStripePackage != null && user.getSubScriptionId() != null) {
				StripePlan dbStripePlan = stripePlanRepository.findByProductId(dbStripePackage.getPackageId());
				Map<String, Object> planParam = new HashMap<>();
				planParam.put("plan", dbStripePlan.getPlanId());
				Map<String, Object> items = new HashMap<>();
				items.put("0", planParam);
				Subscription sub = Subscription.retrieve(user.getSubScriptionId());
				Map<String, Object> updateParams = new HashMap<String, Object>();
				updateParams.put("customer", dbUserAccount.getCustomerId());
				updateParams.put("items", items);
				updateParams.put("quantity", i);
				sub.update(updateParams);
			}

			/*Subscription sub = Subscription.retrieve(user.getSubScriptionId());
			Map<String, Object> updateParams = new HashMap<String, Object>();
			updateParams.put("quantity", i);
			sub.update(updateParams);*/

		}
		return 0;
	}

}
