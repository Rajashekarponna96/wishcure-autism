package com.openspace.HospitalMgnt.SubscriptionMaster;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.SubScriptionMaster;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class SubscriptionMasterServiceImpl implements SubscriptionMasterService {

	@Autowired
	private SubscriptionMasterRepository subscriptionMasterRepository;

	@Override
	public void saveSubscriptionMaster(SubScriptionMaster subscriptionMaster) {
		// TODO Auto-generated method stub
		SubScriptionMaster dbSubscriptionMaster = subscriptionMasterRepository
				.findBySubscriptionName(subscriptionMaster.getSubscriptionName());
		if (dbSubscriptionMaster != null) {
			throw new RuntimeException(ErrorMessageHandler.subscriptionMasterAlreadyExists);
		}
		subscriptionMaster.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		subscriptionMaster.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
		subscriptionMaster.setStatus(true);
		subscriptionMasterRepository.save(subscriptionMaster);
	}

	@Override
	public Page<SubScriptionMaster> getAllSubscriptionMasters(int page,int size) {
		// TODO Auto-generated method stub
		return (Page<SubScriptionMaster>) subscriptionMasterRepository.findAll(new PageRequest(page, size,Sort.Direction.DESC,"id"));
	}
	@Override
	public List<SubScriptionMaster> getAllSubscriptionMasters() {
		// TODO Auto-generated method stub
		return (List<SubScriptionMaster>) subscriptionMasterRepository.findAll();
	}

	@Override
	public void updateSubscriptionMaster(SubScriptionMaster subscriptionMaster) {
		// TODO Auto-generated method stub
		SubScriptionMaster dbSubscriptionMaster = subscriptionMasterRepository.findOne(subscriptionMaster.getId());
		if (dbSubscriptionMaster == null) {
			throw new RuntimeException(ErrorMessageHandler.subscriptionMasterDoesNotExists);
		}
		SubScriptionMaster dbSubscriptionMaster2 = subscriptionMasterRepository
				.findBySubscriptionName(subscriptionMaster.getSubscriptionName());
		if (dbSubscriptionMaster2 == null) {
			dbSubscriptionMaster.setSubscriptionName(subscriptionMaster.getSubscriptionName());
			dbSubscriptionMaster.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
			dbSubscriptionMaster.setSubscriptionDescription(subscriptionMaster.getSubscriptionDescription());
			dbSubscriptionMaster.setStatus(subscriptionMaster.getStatus());
		} else if (dbSubscriptionMaster2.getId().equals(subscriptionMaster.getId())) {
			dbSubscriptionMaster.setSubscriptionName(subscriptionMaster.getSubscriptionName());
			dbSubscriptionMaster.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
			dbSubscriptionMaster.setSubscriptionDescription(subscriptionMaster.getSubscriptionDescription());
			dbSubscriptionMaster.setStatus(subscriptionMaster.getStatus());
		} else {
			throw new RuntimeException(ErrorMessageHandler.subscriptionMasterAlreadyExists);
		}
		dbSubscriptionMaster.setSubscriptionName(subscriptionMaster.getSubscriptionName());
		dbSubscriptionMaster.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
		dbSubscriptionMaster.setSubscriptionDescription(subscriptionMaster.getSubscriptionDescription());
		dbSubscriptionMaster.setStatus(subscriptionMaster.getStatus());
		subscriptionMasterRepository.save(dbSubscriptionMaster);
	}

	@Override
	public void deleteSubscriptionMaster(Long id) {
		// TODO Auto-generated method stub
		SubScriptionMaster dbSubscriptionMaster = subscriptionMasterRepository.findOne(id);
		if (dbSubscriptionMaster == null) {
			throw new RuntimeException(ErrorMessageHandler.subscriptionMasterDoesNotExists);
		}
		subscriptionMasterRepository.delete(dbSubscriptionMaster);
	}

}
