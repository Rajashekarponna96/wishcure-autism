package com.openspace.HospitalMgnt.SubscriptionMaster;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.Lookups.SubScriptionMaster;

public interface SubscriptionMasterService {
	void saveSubscriptionMaster(SubScriptionMaster subscriptionMaster);

	Page<SubScriptionMaster> getAllSubscriptionMasters(int page,int size);

	void updateSubscriptionMaster(SubScriptionMaster subscriptionMaster);

	void deleteSubscriptionMaster(Long id);

	List<SubScriptionMaster> getAllSubscriptionMasters();

}
