package com.openspace.Model.UserManagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;
import com.openspace.Model.DateConverters.LocalDateTimeConverter;
import com.openspace.Model.DateConverters.LocalDateTimeDeserializer;
import com.openspace.Model.DateConverters.LocalDateTimeSerializer;
import com.openspace.Model.Lookups.Company;
import com.openspace.Model.Lookups.CompanyType;
import com.openspace.Model.Lookups.Site;
import com.openspace.Model.Stripe.StripePackage;
import com.openspace.Model.Stripe.StripePayment;

@Entity
public class UserAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String password;

	private boolean isFirtLogin;

	private Role role;

	private boolean isActive;

	private boolean isOtpEntered;

	private String token;

	private String salt;

	private String resetToken;

	private String loginIp;

	private Company company;

	private boolean isIpExist;

	private LocalDateTime resetTokenExpires;

	private Integer graceCount;

	private UserType userType;

	private Site site;

	private String uniqueId;

	private CompanyType companyType;

	private StripePackage stripePackage;

	private String customerId;

	private LocalDate registeredDate;

	private boolean registeredUser;

	private String subScriptionId;

	private List<StripePayment> stripePayments;
	
	private boolean paymentDone;
	
	public UserAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isFirtLogin() {
		return isFirtLogin;
	}

	public void setFirtLogin(boolean isFirtLogin) {
		this.isFirtLogin = isFirtLogin;
	}

	@OneToOne(cascade = CascadeType.MERGE)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	@Convert(converter = LocalDateTimeConverter.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	public LocalDateTime getResetTokenExpires() {
		return resetTokenExpires;
	}

	public void setResetTokenExpires(LocalDateTime resetTokenExpires) {
		this.resetTokenExpires = resetTokenExpires;
	}

	public Integer getGraceCount() {
		return graceCount;
	}

	public void setGraceCount(Integer graceCount) {
		this.graceCount = graceCount;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public boolean isIpExist() {
		return isIpExist;
	}

	public void setIpExist(boolean isIpExist) {
		this.isIpExist = isIpExist;
	}

	public boolean isOtpEntered() {
		return isOtpEntered;
	}

	public void setOtpEntered(boolean isOtpEntered) {
		this.isOtpEntered = isOtpEntered;
	}

	@Enumerated(EnumType.STRING)
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@JsonIgnore
	@OneToOne(cascade = CascadeType.MERGE)
	public CompanyType getCompanyType() {
		return companyType;
	}

	@JsonProperty
	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	//@JsonIgnore
	@ManyToOne
	public StripePackage getStripePackage() {
		return stripePackage;
	}

	//@JsonProperty
	public void setStripePackage(StripePackage stripePackage) {
		this.stripePackage = stripePackage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(LocalDate registeredDate) {
		this.registeredDate = registeredDate;
	}

	public boolean isRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(boolean registeredUser) {
		this.registeredUser = registeredUser;
	}

	public String getSubScriptionId() {
		return subScriptionId;
	}

	public void setSubScriptionId(String subScriptionId) {
		this.subScriptionId = subScriptionId;
	}

	@JsonIgnore
	@OneToMany
	public List<StripePayment> getStripePayments() {
		return stripePayments;
	}

	@JsonProperty
	public void setStripePayments(List<StripePayment> stripePayments) {
		this.stripePayments = stripePayments;
	}

	public boolean isPaymentDone() {
		return paymentDone;
	}

	public void setPaymentDone(boolean paymentDone) {
		this.paymentDone = paymentDone;
	}

}
