package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.DoctorManagement.Tax;

public interface TaxService {

	void addTax(Tax tax);

	List<Tax> getAllTaxs();

	void updateTax(Tax tax);

	void deleteTax(Long id);

	List<Tax> getAllTaxsByAdmin(String username);

	Page<Tax> getAllTaxsByAdminPage(String username, int page, int size);
}
