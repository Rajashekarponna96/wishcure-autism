package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Stripe.StripePayment;

@Repository
public interface StripePaymentRepository extends PagingAndSortingRepository<StripePayment, Long> {

	List<StripePayment> findByUserAccount_Id(Long id);
}
