package com.openspace.PatientManagement.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Dto.ChargeDto1;
import com.openspace.Model.Dto.InvoiceStipeDto;
import com.openspace.Model.PatientMgnt.Repositories.StripePlanRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.Payment.CustomerDto;
import com.openspace.Model.Payment.Payment;
import com.openspace.Model.Payment.PaymentDto;
import com.openspace.Model.Stripe.StripeBank;
import com.openspace.Model.Stripe.StripePackage;
import com.openspace.Model.Stripe.StripePayment;
import com.openspace.Model.Stripe.StripePlan;
import com.openspace.Model.Stripe.Transaction;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.PatientManagement.dto.TokenDto;
import com.openspace.PatientManagement.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Account;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceLineItem;
import com.stripe.model.Plan;
import com.stripe.model.Product;
import com.stripe.model.Subscription;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

	private StripeClient stripeClient;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private StripePlanRepository stripePlanRepository;

	
	@Autowired
	PaymentController(StripeClient stripeClient) {
		this.stripeClient = stripeClient;
		Stripe.apiKey = "sk_test_KnRgkZ9quhBpKBTaNQ0eu2GX";
	}

	@RequestMapping(value = "/charge", method = RequestMethod.POST)
	public void chargeCard(@RequestBody PaymentDto paymentDto) throws Exception {

		UserAccount dbUserAccount = userAccountRepository.findByUsername(paymentDto.getEmail());
		String token = paymentDto.getSource();
		Customer customer = null;
		try {
			Map<String, Object> customerParams = new HashMap<>();
			customerParams.put("source", token);
			customerParams.put("email", paymentDto.getEmail());
			customer = Customer.create(customerParams);
		} catch (Exception e) {
			throw new RuntimeException("Error" + e.getMessage());
		}

		if (customer != null) {
			Map<String, Object> chargeParams = new HashMap<String, Object>();
			StripePackage dbStripePackage = dbUserAccount.getStripePackage();
			if (dbStripePackage != null) {
				StripePlan dbStripePlan = stripePlanRepository.findByProductId(dbStripePackage.getPackageId());
				if (dbStripePlan != null) {
					if (dbStripePlan.getTrialPeriodDays() == null) {

						Map<String, Object> planParam = new HashMap<>();
						planParam.put("plan", paymentDto.getStripePlanId());
						Map<String, Object> items = new HashMap<>();
						items.put("0", planParam);
						Map<String, Object> params = new HashMap<>();
						params.put("customer", customer.getId());
						params.put("items", items);
						Subscription subscription = Subscription.create(params);
						//subscription.getSubscriptionItems()

						if (dbUserAccount != null) {
							dbUserAccount.setSubScriptionId(subscription.getId());
							// dbStripePackage.setStripePayments(stripePayments);
						}
					} else {
						List<StripePayment> stripePayments = new ArrayList<StripePayment>();

						chargeParams.put("amount", 100); // amount in cents for
															// testing the valid
															// card or not?
						chargeParams.put("currency", "usd");
						chargeParams.put("description", paymentDto.getDescription());
						chargeParams.put("receipt_email", paymentDto.getEmail());
						chargeParams.put("customer", customer.getId());
						Charge charge = Charge.create(chargeParams);

						Transaction trx = new Transaction();
						trx.setTransactionName(charge.getObject());
						trx.setDescription(charge.getOutcome().getSellerMessage());
						trx.setPaymentInvoiceNumber(charge.getInvoice());
						trx.setPaymentTransactionNumber(charge.getBalanceTransaction());
						trx.setStatus(charge.getStatus());
						trx.setChargeTransactionId(charge.getId());
						trx.setTransactionDate(LocalDate.now());
						trx.setPaymentInvoiceNumber(charge.getInvoice());

						StripePayment stripePayment = new StripePayment();
						stripePayment.setAmount(charge.getAmount());
						stripePayment.setGatewayStatus(charge.getStatus());
						stripePayment.setPaidDate(LocalDate.now());
						stripePayment.setTrasaction(trx);
						stripePayment.setStripePackage(dbStripePackage);
						stripePayment.setUserAccount(dbUserAccount);
						stripePayments.add(stripePayment);

						if (dbUserAccount != null) {
							// dbUserAccount.setSubScriptionId(subscription.getId());
							dbStripePackage.setStripePayments(stripePayments);
						}
					}

				}
			}

			if (dbUserAccount != null) {
				dbUserAccount.setCustomerId(customer.getId());
				dbUserAccount.setStripePackage(dbStripePackage);
				// dbUserAccount.setPaymentDone(true);
			}
		}
		userAccountRepository.save(dbUserAccount);
	}

	@RequestMapping(value = "/crateCustomer/token", method = RequestMethod.POST)
	public void createCustomer(@RequestBody CustomerDto customerDto) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException, APIException {
		Map<String, Object> customerParams = new HashMap<String, Object>();
		customerParams.put("description", customerDto.getDescription());
		customerParams.put("source", customerDto.getToken());
		customerParams.put("email", customerDto.getEmail());
		Customer.create(customerParams);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addPayment(@RequestBody Payment payment) {
		paymentService.addPayment(payment);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Payment> getAllPayments() {
		return paymentService.getAllPayments();
	}

	@RequestMapping(value = "/paginationdata", method = RequestMethod.GET)
	public Page<Payment> getAllPaymentsWithPagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "size", required = true) int size) {
		return paymentService.getAllPaymentsWithPagination(page, size);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void updatePayment(@RequestBody Payment payment) {
		paymentService.updatePayment(payment);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletePayment(@PathVariable(value = "id") Long id) {
		paymentService.deletePayment(id);
	}

	@RequestMapping(value = "/getAllProducts", method = RequestMethod.GET)
	public List<Product> getAllProducts() throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		Map<String, Object> productParams = new HashMap<String, Object>();
		productParams.put("limit", "3");
		return Product.list(productParams).getData();
	}

	/*
	 * @RequestMapping(value = "/getAllPlans", method = RequestMethod.GET)
	 * public PlanCollection getAllPlans() throws AuthenticationException,
	 * InvalidRequestException, APIConnectionException, CardException,
	 * APIException { Map<String, Object> planParams = new HashMap<String,
	 * Object>(); planParams.put("limit", "3"); return Plan.list(planParams); }
	 */

	@RequestMapping(value = "/getAllPlans", method = RequestMethod.GET)
	public String getAllPlansJSON() {
		try {
			Map<String, Object> planParams = new HashMap<>();
			planParams.put("limit", "20");
			return Plan.list(planParams).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/getAllPaymentsStripe", method = RequestMethod.GET)
	public static  ChargeDto1 getAllPaymentsStripe(Transaction transaction) {
		try {
			/*Map<String, Object> chargeParams = new HashMap<String, Object>();
			chargeParams.put("limit", "2000");
			return Charge.list(chargeParams).toJson();*/
		//Charge charge=Charge.retrieve("ch_1CgtfeDteEPjg4BEu9GS7Mp4");	
		Charge charge=Charge.retrieve(transaction.getChargeTransactionId());
		ChargeDto1 chargeDto1=new ChargeDto1();
			chargeDto1.setId(charge.getId());
			Float value=(float) (charge.getAmount()/100.0);
			chargeDto1.setAmount(value);
			chargeDto1.setCurrency(charge.getCurrency());
			chargeDto1.setCreated(charge.getCreated());
			chargeDto1.setCustomer(charge.getCustomer());
			chargeDto1.setReceiptEmail(charge.getReceiptEmail());
			chargeDto1.setReceiptNumber(charge.getReceiptNumber());
			chargeDto1.setDescription(charge.getDescription());
			chargeDto1.setStatus(charge.getStatus());
		return chargeDto1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	@RequestMapping(value = "/getinvoicee1", method = RequestMethod.GET)
	public static  String getAllinvoicee1(Transaction transaction) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		
		Map<String, Object> planParams = new HashMap<>();
		planParams.put("customer", "cus_Cy2KGgyqDozPQr");
	//	planParams.put("customer", "cus_D0OJL1pSwRi4sm");
		return Invoice.list(planParams).toString(); 
		//cus_CeYF33Gd8dime3
		//return Invoice.retrieve("in_1CTPjuDteEPjg4BEtKKFUYFj").toJson();
		
		//return Invoice.retrieve("in_1ClFxhDteEPjg4BEct7skitb").toJson();
	}
	@RequestMapping(value = "/getinvoicee", method = RequestMethod.GET)
	public static  List<InvoiceStipeDto> getAllinvoicee(UserAccount userAccount) {
		try{
		List<InvoiceStipeDto> invoiceStipeDtos=new ArrayList<>();
		Map<String, Object> planParams = new HashMap<>();
		//planParams.put("customer", "cus_D0OJL1pSwRi4sm");
	planParams.put("customer", userAccount.getCustomerId());
		List<Invoice> invoices= Invoice.list(planParams).getData(); 
		InvoiceStipeDto invoiceStipeDto=null;
		for(Invoice invoice:invoices){
			 invoiceStipeDto=new InvoiceStipeDto();
			invoiceStipeDto.setId(invoice.getId());
			invoiceStipeDto.setCustomer(invoice.getCustomer());
			Float total=(float) (invoice.getTotal()/100.0);
			invoiceStipeDto.setTotal(total);
			Float subTotal=(float) (invoice.getSubtotal()/100.0);
			invoiceStipeDto.setSubtotal(subTotal);
			Float value=(float) (invoice.getAmountDue()/100.0);
			invoiceStipeDto.setAmountDue(value);
			invoiceStipeDto.setCurrency(invoice.getCurrency().toUpperCase());
			invoiceStipeDto.setPaid(invoice.getPaid());
			invoiceStipeDto.setNumber(invoice.getNumber());
			
			 long batch_date = invoice.getDate(); 
			 Date dt = new Date (batch_date * 1000);
			 SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
			  System.out.println(sfd.format(dt));
			invoiceStipeDto.setDate(sfd.format(dt));
			invoiceStipeDto.setCreated(invoice.getCreated());
			invoice.getLines().getData();
			for(InvoiceLineItem invoiceLineItem:invoice.getLines().getData()){
				invoiceStipeDto.setDescription(invoiceLineItem.getDescription());
				
				String s1=invoiceLineItem.getDescription();  
				String[] words=s1.split("\\s");//splits the string based on whitespace 
				invoiceStipeDto.setDesc(words[3]+" (per "+words[1]+")");
				invoiceStipeDto.setUnitPrice(words[7]);
				long batch_date1 = invoiceLineItem.getPeriod().getStart(); 
				 Date dt1 = new Date (batch_date1 * 1000);
				invoiceStipeDto.setStart(sfd.format(dt1));	
				long batch_date2 = invoiceLineItem.getPeriod().getEnd(); 
				 Date dt2 = new Date (batch_date2 * 1000);
				invoiceStipeDto.setEnd(sfd.format(dt2));
				
				invoiceStipeDto.setQuantity(invoiceLineItem.getQuantity());
			}
			
			/*invoiceStipeDto.set
			invoiceStipeDto.set
			invoiceStipeDto.set*/
			
			
			invoiceStipeDtos.add(invoiceStipeDto);
		}
		
		return invoiceStipeDtos;
		}catch (Exception e) {
				e.printStackTrace();
				return null;
			}

	}
	@RequestMapping(value = "/getAllPaymentsStripe3", method = RequestMethod.GET)
	public  String getAllPaymentsStripe3(Transaction transaction) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException, IOException {
		return Charge.retrieve("ch_1CgtfeDteEPjg4BEu9GS7Mp4").toJson();

	}
	
	/*@RequestMapping(value = "/getAllPaymentsStripe1", method = RequestMethod.GET)
	public static String getAllPaymentsStripe1(Transaction transaction) {

          String string=getAllPaymentsStripe(transaction);
          return string;
	}*/
	
	/*@RequestMapping(value = "/getAllPaymentsStripe2", method = RequestMethod.GET)
	public static List<String> getAllPaymentsStripe2(Transaction transaction) {
		List<String> strings=new ArrayList<>();
		 
          String string=getAllPaymentsStripe(transaction);
          for(int i=1;i<=2;i++){
        	  strings.add(string); 
          }
          return strings;
	}*/
	
	/*@RequestMapping(value = "/getAllUSerPaymentsStripe/{username:.+}", method = RequestMethod.GET)
	public List<String> getAllPaymentsStripe(@PathVariable("adminUserName")  String username) {
		UserAccount userAccount=userAccountRepository.findByUsername(username);
		if(userAccount==null){
			throw new RuntimeException("User Doesn't Exist!");
		}
		List<Transaction> transactionList=new ArrayList<>();
		List<String> stringList=new ArrayList<>();
		List<StripePayment> stripePayments=stripePaymentRepository.findByUserAccount_Id(userAccount.getId());
		for(StripePayment stripePayment:stripePayments){
			transactionList.add(stripePayment.getTrasaction());
		}
		for(Transaction transaction:transactionList){
			String string=getAllPaymentsStripe(transaction);
			stringList.add(string);
		}
		return stringList;
	}*/

	@RequestMapping(value = "/updatePlan", method = RequestMethod.GET)
	public String updatePlan() throws AuthenticationException, InvalidRequestException, APIConnectionException,
			CardException, APIException {

		Plan p = Plan.retrieve("plan_D0LoAuEGrpOdmS");
		Map<String, Object> updateParams = new HashMap<String, Object>();
		updateParams.put("tiers_mode", "volume");
		return p.update(updateParams).toJson();

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void createPlan() throws AuthenticationException, InvalidRequestException, APIConnectionException,
			CardException, APIException {

		Map<String, Object> tier1 = new HashMap<>();
		tier1.put("amount", 7999);
		tier1.put("up_to", 10);

		Map<String, Object> tier2 = new HashMap<>();
		tier2.put("amount", 6999);
		tier2.put("up_to", 20);

		Map<String, Object> tier3 = new HashMap<>();
		tier3.put("amount", 5999);
		tier3.put("up_to", "inf");

		Map<String, Object> tiersParamas = new HashMap<>();
		tiersParamas.put("0", tier1);
		tiersParamas.put("1", tier2);
		tiersParamas.put("2", tier3);
		Map<String, Object> params = new HashMap<>();
		params.put("currency", "usd");
		params.put("interval", "month");
		params.put("product", "prod_D0MPmtBhPd2pJN");
		params.put("nickname", "100GB");
		params.put("billing_scheme", "tiered");
		params.put("tiers", tiersParamas);
		params.put("tiers_mode", "volume");
		params.put("trial_period_days", 7);

		Plan.create(params);

	}

	@RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	public void createProduct() throws AuthenticationException, InvalidRequestException, APIConnectionException,
			CardException, APIException {
		Map<String, Object> productParams = new HashMap<String, Object>();
		productParams.put("name", "Monthly membership base fee");
		productParams.put("type", "service");

		Product.create(productParams);
	}

	@RequestMapping(value = "/create-account", method = RequestMethod.POST)
	public void createAccount(@RequestBody TokenDto tokenDto) throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
		String token = tokenDto.getToken();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("country", "US");
		params.put("type", "custom");
		params.put("account_token", token);
		Account acct = Account.create(params);
	}

	@RequestMapping(value = "/create_Ach_Account", method = RequestMethod.POST)
	public void createACHBankAccount(@RequestBody StripeBank achBankDetails) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException, APIException {
		// Create a Customer
		UserAccount dbUserAccount = userAccountRepository.findByUsername(achBankDetails.getCustomerEmail());
		Customer customer = null;
		try {
			Map<String, Object> customerParams = new HashMap<String, Object>();
			customerParams.put("source", achBankDetails.getToken());
			customerParams.put("description", achBankDetails.getCustomerEmail());
			customer = Customer.create(customerParams);
		} catch (Exception e) {
			throw new RuntimeException("" + e.getMessage());
		}

		if (customer != null) {
			Map<String, Object> chargeParams = new HashMap<String, Object>();
			StripePackage dbStripePackage = dbUserAccount.getStripePackage();
			if (dbStripePackage != null) {
				StripePlan dbStripePlan = stripePlanRepository.findByProductId(dbStripePackage.getPackageId());
				if (dbStripePlan != null) {
					if (dbStripePlan.getTrialPeriodDays() == null) {

						Map<String, Object> planParam = new HashMap<>();
						planParam.put("plan", achBankDetails.getStripePlanId());
						Map<String, Object> items = new HashMap<>();
						items.put("0", planParam);
						Map<String, Object> params = new HashMap<>();
						params.put("customer", customer.getId());
						params.put("items", items);
						Subscription subscription = Subscription.create(params);

						if (dbUserAccount != null) {
							dbUserAccount.setSubScriptionId(subscription.getId());
							// dbStripePackage.setStripePayments(stripePayments);
						}
					}

				}
			}
			if (dbUserAccount != null) {
				dbUserAccount.setCustomerId(customer.getId());
				dbUserAccount.setStripePackage(dbStripePackage);
			}
		}
		userAccountRepository.save(dbUserAccount);

	}

}
