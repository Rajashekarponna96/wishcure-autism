package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.openspace.Model.PatientMgnt.Repositories.TransactionRepository;
import com.openspace.Model.Stripe.Transaction;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public void add(Transaction transaction) {
		transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> getAll() {
		return (List<Transaction>) transactionRepository.findAll();
	}

	@Override
	public Page<Transaction> findAllWithPagination(int page, int size) {
		return transactionRepository.findAll(new PageRequest(page, size));
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		Transaction dbTransaction = transactionRepository.findOne(transaction.getId());
		if (dbTransaction == null) {
			throw new RuntimeException(ErrorMessageHandler.transactionDetailsDoesNotExists);
		}
		dbTransaction.setDescription(transaction.getDescription());
		dbTransaction.setPayby(transaction.getPayby());
		dbTransaction.setPaymentInvoiceNumber(transaction.getPaymentInvoiceNumber());
		dbTransaction.setPaymentTransactionNumber(transaction.getPaymentTransactionNumber());
		dbTransaction.setTransactionDate(transaction.getTransactionDate());
		dbTransaction.setTransactionName(transaction.getTransactionName());
		dbTransaction.setTransactionStatus(transaction.getTransactionStatus());
		transactionRepository.save(dbTransaction);
	}

	@Override
	public void deleteTransaction(Long id) {
		Transaction dbTransaction = transactionRepository.findOne(id);
		if (dbTransaction == null) {
			throw new RuntimeException(ErrorMessageHandler.transactionDetailsDoesNotExists);
		}
		transactionRepository.delete(dbTransaction);
	}

}
