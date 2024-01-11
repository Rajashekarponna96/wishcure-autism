package com.openspace.HospitalMgnt.State;

import java.util.List;

import com.openspace.Model.Lookups.State;

public interface StateService {

	List<State> getAllStates();

	List<State> getAllStatesByCountryId(Long companyId);

	State getOneStatesById(Long stateId);

}
