package com.openspace.HospitalMgnt.State;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.State;
import com.openspace.Model.PatientMgnt.Repositories.StateRepository;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateRepository stateRepository;

	@Override
	public List<State> getAllStates() {
		return (List<State>) stateRepository.findAll();
	}

	@Override
	public List<State> getAllStatesByCountryId(Long companyId) {
		return stateRepository.findByCountry_Id(companyId);
	}

	@Override
	public State getOneStatesById(Long stateId) {
		// TODO Auto-generated method stub
		return stateRepository.findOne(stateId);
	}

}
