package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Insurance;
import com.openspace.Model.DoctorManagement.School;
import com.openspace.Model.Lookups.InsuranceLookup;
import com.openspace.Model.PatientMgnt.Repositories.InsuranceLookupRepository;
import com.openspace.Model.PatientMgnt.Repositories.InsuranceRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class InsuranceLookupServiceImpl implements InsuranceLookupService {

	@Autowired
	private InsuranceLookupRepository insuranceLookupRepository;
	

	@Autowired
	private InsuranceRepository insuranceRepository;

	@Override
	public void add(InsuranceLookup insuranceLookup) {
		// TODO Auto-generated method stub
		InsuranceLookup dbInsuranceLookup = insuranceLookupRepository
				.findByInsuranceCompanyName(insuranceLookup.getInsuranceCompanyName());
		if (dbInsuranceLookup != null) {
			throw new RuntimeException(ErrorMessageHandler.insurancLookupAlreadyExists);
		}
		insuranceLookup.setStatus(true);
		insuranceLookupRepository.save(insuranceLookup);
	}

	@Override
	public List<InsuranceLookup> getAll() {
		// TODO Auto-generated method stub
		return (List<InsuranceLookup>) insuranceLookupRepository.findAll();
	}

	@Override
	public void deleteInsuranceLookup(Long insurancelookupId) {
		// TODO Auto-generated method stub
		insuranceLookupRepository.delete(insurancelookupId);
		;
	}

	@Override
	public void updateInsuranceLookup(InsuranceLookup insuranceLookup) {
		// TODO Auto-generated method stub
		InsuranceLookup dbInsuranceLookup = insuranceLookupRepository.findOne(insuranceLookup.getId());
		if (dbInsuranceLookup == null) {
			throw new RuntimeException(ErrorMessageHandler.insurancLookupDoesNotExists);
		}

		InsuranceLookup dbInsuranceLookup1 = insuranceLookupRepository
				.findByInsuranceCompanyName(insuranceLookup.getInsuranceCompanyName());
		if (dbInsuranceLookup1 == null) {
			dbInsuranceLookup.setAddress1(insuranceLookup.getAddress1());
			dbInsuranceLookup.setEmailId(insuranceLookup.getEmailId());
			dbInsuranceLookup.setFax(insuranceLookup.getFax());
			dbInsuranceLookup.setIdNumber(insuranceLookup.getIdNumber());
			dbInsuranceLookup.setInsuranceCompanyName(insuranceLookup.getInsuranceCompanyName());
			dbInsuranceLookup.setMobileNumber(insuranceLookup.getMobileNumber());
		} else if (dbInsuranceLookup1.getId().equals(dbInsuranceLookup.getId())) {
			dbInsuranceLookup.setAddress1(insuranceLookup.getAddress1());
			dbInsuranceLookup.setEmailId(insuranceLookup.getEmailId());
			dbInsuranceLookup.setFax(insuranceLookup.getFax());
			dbInsuranceLookup.setIdNumber(insuranceLookup.getIdNumber());
			dbInsuranceLookup.setInsuranceCompanyName(insuranceLookup.getInsuranceCompanyName());
			dbInsuranceLookup.setMobileNumber(insuranceLookup.getMobileNumber());
		} else {
			throw new RuntimeException(ErrorMessageHandler.insurancLookupAlreadyExists);
		}

		insuranceLookupRepository.save(dbInsuranceLookup);
			List<Insurance> insurances=insuranceRepository.findByInsuranceLookupId(dbInsuranceLookup.getId());
			for(Insurance insurance:insurances){
				insurance.setStatus(insuranceLookup.getStatus());
				insurance.setAddress1(insuranceLookup.getAddress1());
				insurance.setEmailId(insuranceLookup.getEmailId());
				insurance.setFax(insuranceLookup.getFax());
				insurance.setIdNumber(insuranceLookup.getIdNumber());
				insurance.setInsuranceCompanyName(insuranceLookup.getInsuranceCompanyName());
				insurance.setMobileNumber(insuranceLookup.getMobileNumber());
				insuranceRepository.save(insurance);
			
		}
	}

}
