package com.openspace.HospitalMgnt.common.Registration;

import com.openspace.Model.Lookups.CompanyType;
import com.openspace.Model.Stripe.StripePackage;
import com.openspace.Model.Stripe.StripePayment;
import com.openspace.Model.Stripe.Transaction;
import com.openspace.Model.UserManagement.Role;

public class RegistrationDto {

	private Long id;

	private String firstName;

	private String lastName;

	private String companyName;

	private String email;

	private String password;

	private String repeatPassword;

	private Long  moiblenumber;

	private String address1;

	private String address2;

	private String country;

	private String state;

	private String city;

	private String location;

	private String zipcode;

	private Role role;

	private String profilePic;

	//private PackagePriceMaster packagePriceMaster;
	
	private StripePackage stripePackage;
	
	private CompanyType companyType;
	
	private Transaction transaction;
	
	//private Payment payment;
	
	private StripePayment stripePayment;
	
	private String organizationName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public Long getMoiblenumber() {
		return moiblenumber;
	}

	public void setMoiblenumber(Long moiblenumber) {
		this.moiblenumber = moiblenumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public StripePackage getStripePackage() {
		return stripePackage;
	}

	public void setStripePackage(StripePackage stripePackage) {
		this.stripePackage = stripePackage;
	}

	public StripePayment getStripePayment() {
		return stripePayment;
	}

	public void setStripePayment(StripePayment stripePayment) {
		this.stripePayment = stripePayment;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	
}
