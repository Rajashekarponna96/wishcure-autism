package com.openspace.PatientManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Stripe.Transaction;
import com.openspace.PatientManagement.service.TransactionService;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody Transaction transaction) {
		transactionService.add(transaction);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Transaction> getAll() {
		return transactionService.getAll();
	}

	@RequestMapping(value = "/pagination", method = RequestMethod.GET)
	public Page<Transaction> findAllWithPagination(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "size", required = true) int size) {
		return transactionService.findAllWithPagination(page,size);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void update(@RequestBody Transaction transaction) {
		transactionService.updateTransaction(transaction);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteTransaction(@PathVariable(value="id") Long id) {
		transactionService.deleteTransaction(id);
	}
	

}
