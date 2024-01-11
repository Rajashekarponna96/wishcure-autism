package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Stripe.Transaction;

@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction,Long>{

}
