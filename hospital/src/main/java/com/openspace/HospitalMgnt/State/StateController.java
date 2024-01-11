package com.openspace.HospitalMgnt.State;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.Lookups.State;

@RestController
public class StateController {

	@Autowired
	private StateService stateService;

	@RequestMapping(value = RestURIConstants.GET_ALL_STATES, method = RequestMethod.GET)
	public @ResponseBody List<State> getAllStates() {
		return stateService.getAllStates();
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_STATES_BY_COUNTRYID, method = RequestMethod.GET)
	public @ResponseBody List<State> getAllStatesByCompany(@PathVariable Long companyId) {
		return stateService.getAllStatesByCountryId(companyId);

	}
	
	@RequestMapping(value = RestURIConstants.GET_ONE_STATES_BY_ID, method = RequestMethod.GET)
	public @ResponseBody State getOneState(@PathVariable Long stateId) {
		return stateService.getOneStatesById(stateId);

	}
}
