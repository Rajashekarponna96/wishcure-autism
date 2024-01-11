package com.openspace.HospitalMgnt.SubscriptionMaster;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.Lookups.SubScriptionMaster;

@RestController
public class SubscriptionMasterController {
	@Autowired
	private SubscriptionMasterService subscriptionMasterService;
	
	@RequestMapping(value=RestURIConstants.SAVE_SUBSCRIPTION_MASTER)
	public @ResponseBody void saveSubscriptionMaster(@RequestBody SubScriptionMaster subscriptionMaster){
		subscriptionMasterService.saveSubscriptionMaster(subscriptionMaster);;
	}
	

	@RequestMapping(value = RestURIConstants.GET_ALL_SUBSCRIPTION_MASTERS, method = RequestMethod.GET)
	public @ResponseBody Page<SubScriptionMaster> getAllSubscriptionMasters(@RequestParam("page")int page,@RequestParam("size")int size) {
		return subscriptionMasterService.getAllSubscriptionMasters(page,size);
	}
	@RequestMapping(value = RestURIConstants.GET_ALL_SUBSCRIPTION_MASTERS_LIST, method = RequestMethod.GET)
	public @ResponseBody List<SubScriptionMaster> getAllSubscriptionMasters() {
		return subscriptionMasterService.getAllSubscriptionMasters();
	}

	@RequestMapping(value = RestURIConstants.UPDATE_SUBSCRIPTION_MASTER, method = RequestMethod.PUT)
	public @ResponseBody void updateSubscriptionMaster(@RequestBody SubScriptionMaster subscriptionMaster) {
		subscriptionMasterService.updateSubscriptionMaster(subscriptionMaster);;
	}

	@RequestMapping(value = RestURIConstants.DELETE_SUBSCRIPTION_MASTER, method = RequestMethod.DELETE)
	public @ResponseBody void deleteSubscriptionMaster(@PathVariable Long id) {
		subscriptionMasterService.deleteSubscriptionMaster(id);
	}

}
