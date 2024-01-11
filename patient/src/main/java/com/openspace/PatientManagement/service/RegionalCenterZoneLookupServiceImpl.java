package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.RegionalCenterZoneLookup;
import com.openspace.Model.PatientMgnt.Repositories.RegionalCenterZoneLookupRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class RegionalCenterZoneLookupServiceImpl implements RegionalCenterZoneLookupService {

	@Autowired
	private RegionalCenterZoneLookupRepository regionalCenterZoneLookupRepository;

	@Override
	public void saveRegionalCenterZoneLookup(RegionalCenterZoneLookup regionalCenterZoneLookup) {
		// TODO Auto-generated method stub
		RegionalCenterZoneLookup dbRegionalCenterZoneLookup = regionalCenterZoneLookupRepository
				.findByRegionalCenterZoneName(regionalCenterZoneLookup.getRegionalCenterZoneName());
		if (dbRegionalCenterZoneLookup != null) {
			throw new RuntimeException(ErrorMessageHandler.regionalCenterLookupAlreadyExists);
		}
		regionalCenterZoneLookupRepository.save(regionalCenterZoneLookup);
	}

	@Override
	public List<RegionalCenterZoneLookup> getRegionalCenterZoneLookup() {
		// TODO Auto-generated method stub
		return (List<RegionalCenterZoneLookup>) regionalCenterZoneLookupRepository.findAll();
	}
	
	@Override
	public List<RegionalCenterZoneLookup> getRegionalCenterZoneLookupStatus() {
		// TODO Auto-generated method stub
		return (List<RegionalCenterZoneLookup>) regionalCenterZoneLookupRepository.findByStatus(true);
	}
	
	

	@Override
	public void updateRegionalCenterZoneLookup(RegionalCenterZoneLookup regionalCenterZoneLookup) {
		// TODO Auto-generated method stub
		RegionalCenterZoneLookup dbRegionalCenterZoneLookup = regionalCenterZoneLookupRepository
				.findOne(regionalCenterZoneLookup.getId());
		if (dbRegionalCenterZoneLookup == null) {
			throw new RuntimeException(ErrorMessageHandler.regionalCenterNotFound);
		}
		RegionalCenterZoneLookup dbRegionalCenterZoneLookup1 = regionalCenterZoneLookupRepository
				.findByRegionalCenterZoneName(regionalCenterZoneLookup.getRegionalCenterZoneName());
		if (dbRegionalCenterZoneLookup1 == null) {
			dbRegionalCenterZoneLookup.setRegionalCenterZoneName(regionalCenterZoneLookup.getRegionalCenterZoneName());
			dbRegionalCenterZoneLookup.setStatus(regionalCenterZoneLookup.getStatus());
		} else if (dbRegionalCenterZoneLookup1.getId().equals(dbRegionalCenterZoneLookup.getId())) {
			dbRegionalCenterZoneLookup.setRegionalCenterZoneName(regionalCenterZoneLookup.getRegionalCenterZoneName());
			dbRegionalCenterZoneLookup.setStatus(regionalCenterZoneLookup.getStatus());
		} else {
			throw new RuntimeException(ErrorMessageHandler.regionalCenterLookupAlreadyExists);
		}
		dbRegionalCenterZoneLookup.setRegionalCenterZoneName(regionalCenterZoneLookup.getRegionalCenterZoneName());
		dbRegionalCenterZoneLookup.setStatus(regionalCenterZoneLookup.getStatus());
		regionalCenterZoneLookupRepository.save(dbRegionalCenterZoneLookup);
	}

	@Override
	public void deleteRegionalCenterZoneLookup(Long id) {
		// TODO Auto-generated method stub
		RegionalCenterZoneLookup dbRegionalCenterZoneLookup = regionalCenterZoneLookupRepository.findOne(id);
		if (dbRegionalCenterZoneLookup == null) {
			throw new RuntimeException(ErrorMessageHandler.regionalCenterNotFound);
		}
		try{
			regionalCenterZoneLookupRepository.delete(dbRegionalCenterZoneLookup);
		}catch (Exception e) {
			throw new RuntimeException("Can't Delete, It's Already In Use....!!");
		}
		
	}
}
