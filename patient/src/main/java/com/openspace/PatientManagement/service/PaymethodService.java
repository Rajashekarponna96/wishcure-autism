package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.DoctorManagement.Paymethod;

public interface PaymethodService {

	void addPaymethod(Paymethod paymethod);

	List<Paymethod> getAllPaymethods();

	void updatePaymethod(Paymethod paymethod);

	void deletePaymethod(Long id);

	List<Paymethod> getAllPaymethodsByAdmin(String username);

	Page<Paymethod> getAllPaymethodsByAdminPage(String username, int page, int size);
}
