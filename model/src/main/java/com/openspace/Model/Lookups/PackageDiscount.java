package com.openspace.Model.Lookups;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PackageDiscount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String packageDiscountName;

	private String discountPercentage;

	private boolean active;

	private LocalDate validFrom;

	private LocalDate validTo;

	private LocalDateTime createdDate;

	private LocalDateTime modifiedDate;

	private PackagePriceMaster packagePriceMaster;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPackageDiscountName() {
		return packageDiscountName;
	}

	public void setPackageDiscountName(String packageDiscountName) {
		this.packageDiscountName = packageDiscountName;
	}

	public String getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDate getValidTo() {
		return validTo;
	}

	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@OneToOne
	public PackagePriceMaster getPackagePriceMaster() {
		return packagePriceMaster;
	}

	public void setPackagePriceMaster(PackagePriceMaster packagePriceMaster) {
		this.packagePriceMaster = packagePriceMaster;
	}

}
