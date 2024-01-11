package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.Address1;
import com.openspace.Model.Lookups.RegionalCenterLookup;
import com.openspace.Model.PatientMgnt.Repositories.RegionalCenterLookupRepository;
import com.openspace.Model.Util.ErrorMessageHandler;
@Service
public class RegionalCenterLookupServiceImpl implements RegionalCenterLookupService{
	
	@Autowired
	private RegionalCenterLookupRepository regionalCenterLookupRepository;

	@Override
	public void saveRegionalCenterLookup(RegionalCenterLookup regionalCenterLookup) {
		// TODO Auto-generated method stub
		RegionalCenterLookup dbRegionalCenterLookup = regionalCenterLookupRepository
				.findByName(regionalCenterLookup.getName());
		if (dbRegionalCenterLookup != null) {
			throw new RuntimeException(ErrorMessageHandler.regionalCenterLookupAlreadyExists);
		}
		regionalCenterLookupRepository.save(regionalCenterLookup);
	}

	@Override
	public List<RegionalCenterLookup> getRegionalCenterLookup() {
		// TODO Auto-generated method stub
		return (List<RegionalCenterLookup>) regionalCenterLookupRepository.findAll();
	}
	
	@Override
	public List<RegionalCenterLookup> getRegionalCenterLookupByStatus() {
		// TODO Auto-generated method stub
		return (List<RegionalCenterLookup>) regionalCenterLookupRepository.findByStatus(true);
	}

	@Override
	public void updateRegionalCenterLookup(RegionalCenterLookup regionalCenterLookup) {
		// TODO Auto-generated method stub
		RegionalCenterLookup dbRegionalCenterLookup = regionalCenterLookupRepository.findOne(regionalCenterLookup.getId());
		if (dbRegionalCenterLookup == null) {
			throw new RuntimeException(ErrorMessageHandler.regionalCenterDoesNotExists);
		}
		RegionalCenterLookup dbRegionalCenterLookup1 = regionalCenterLookupRepository.findByName(regionalCenterLookup.getName());
		Address1 dbAddress = dbRegionalCenterLookup.getReginoalCenterAddress();
		if (dbRegionalCenterLookup1 == null) {
			dbRegionalCenterLookup.setName(regionalCenterLookup.getName());
			dbRegionalCenterLookup.setEmail(regionalCenterLookup.getEmail());
			dbRegionalCenterLookup.setMobileNumber(regionalCenterLookup.getMobileNumber());
			dbRegionalCenterLookup.setFaxNo(regionalCenterLookup.getFaxNo());
			dbRegionalCenterLookup.setStatus(regionalCenterLookup.getStatus());
		} else if (dbRegionalCenterLookup1.getId().equals(dbRegionalCenterLookup.getId())) {
			dbRegionalCenterLookup.setName(regionalCenterLookup.getName());
			dbRegionalCenterLookup.setEmail(regionalCenterLookup.getEmail());
			dbRegionalCenterLookup.setMobileNumber(regionalCenterLookup.getMobileNumber());
			dbRegionalCenterLookup.setFaxNo(regionalCenterLookup.getFaxNo());
			dbRegionalCenterLookup.setStatus(regionalCenterLookup.getStatus());
		} else {
			throw new RuntimeException(ErrorMessageHandler.regionalCenterExists);
		}
		dbAddress.setAddress1(regionalCenterLookup.getReginoalCenterAddress().getAddress1());
		dbAddress.setAddress2(regionalCenterLookup.getReginoalCenterAddress().getAddress2());
		dbAddress.setCity(regionalCenterLookup.getReginoalCenterAddress().getCity());
		dbAddress.setState(regionalCenterLookup.getReginoalCenterAddress().getState());
		dbAddress.setCountry(regionalCenterLookup.getReginoalCenterAddress().getCountry());
		dbAddress.setZipcode(regionalCenterLookup.getReginoalCenterAddress().getZipcode());
		dbRegionalCenterLookup.setName(regionalCenterLookup.getName());
		dbRegionalCenterLookup.setEmail(regionalCenterLookup.getEmail());
		dbRegionalCenterLookup.setMobileNumber(regionalCenterLookup.getMobileNumber());
		dbRegionalCenterLookup.setReginoalCenterAddress(dbAddress);
		dbRegionalCenterLookup.setFaxNo(regionalCenterLookup.getFaxNo());
		dbRegionalCenterLookup.setStatus(regionalCenterLookup.getStatus());
		dbRegionalCenterLookup.setRegionalCenterZoneLookup(regionalCenterLookup.getRegionalCenterZoneLookup());
		regionalCenterLookupRepository.save(dbRegionalCenterLookup);
	}

	@Override
	public void deleteRegionalCenterLookup(Long id) {
		// TODO Auto-generated method stub
		RegionalCenterLookup dbRegionalCenterLookup = regionalCenterLookupRepository.findOne(id);
		if (dbRegionalCenterLookup == null) {
			throw new RuntimeException(ErrorMessageHandler.regionalCenterDoesNotExists);
		}
		try{
			regionalCenterLookupRepository.delete(dbRegionalCenterLookup);
		}catch (Exception e) {
			throw new RuntimeException("Can't Delete, It's Already In Use....!!");
		}
		
	}

}
