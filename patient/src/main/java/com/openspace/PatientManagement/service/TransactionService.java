package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.Stripe.Transaction;

public interface TransactionService {

	void add(Transaction transaction);

	List<Transaction> getAll();

	Page<Transaction> findAllWithPagination(int page, int size);

	void updateTransaction(Transaction transaction);

	void deleteTransaction(Long id);

}
