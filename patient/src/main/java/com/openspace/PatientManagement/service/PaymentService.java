package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.Payment.Payment;

public interface PaymentService {

	void addPayment(Payment payment);

	List<Payment> getAllPayments();

	Page<Payment> getAllPaymentsWithPagination(int page, int size);

	void updatePayment(Payment payment);

	void deletePayment(Long id);

}
