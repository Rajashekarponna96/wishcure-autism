package com.openspace.PatientManagement.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.openspace.Model.PatientMgnt.Repositories.PaymentRepository;
import com.openspace.Model.Payment.Payment;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public void addPayment(Payment payment) {
		paymentRepository.save(payment);
	}

	@Override
	public List<Payment> getAllPayments() {
		return (List<Payment>) paymentRepository.findAll();
	}

	@Override
	public Page<Payment> getAllPaymentsWithPagination(int page, int size) {
		// TODO Auto-generated method stub
		return paymentRepository.findAll(new PageRequest(page, size));
	}

	@Override
	public void updatePayment(Payment payment) {
		Payment dbPayment = paymentRepository.findOne(payment.getId());
		if (dbPayment == null) {
			throw new RuntimeException(ErrorMessageHandler.paymentHasNotBeenMadeYet);
		}
		dbPayment.setAmount(payment.getAmount());
		dbPayment.setGatewayStatus(payment.getGatewayStatus());
		dbPayment.setModifiedDate(LocalDate.now());
		dbPayment.setPaidDate(payment.getPaidDate());
		//dbPayment.setTrasaction(payment.getTrasaction());
		paymentRepository.save(dbPayment);
	}

	@Override
	public void deletePayment(Long id) {
		Payment dbPayment = paymentRepository.findOne(id);
		if(dbPayment == null){
			throw new RuntimeException(ErrorMessageHandler.paymentHasNotBeenMadeYet);
		}
		paymentRepository.delete(dbPayment);
	}

}
