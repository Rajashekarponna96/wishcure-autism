package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.DoctorManagement.Currency;

public interface CurrencyService {

	void addCurrency(Currency currency);

	List<Currency> getAllCurrencys();

	void updateCurrency(Currency currency);

	void deleteCurrency(Long id);

	List<Currency> getAllCurrencysByAdmin(String username);

	Page<Currency> getAllCurrencysByAdminPage(String username, int page, int size);
}
