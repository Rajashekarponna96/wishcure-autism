package com.openspace.HospitalMgnt.SubscriptionMaster;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.SubScriptionMaster;

@Repository
public interface SubscriptionMasterRepository extends PagingAndSortingRepository<SubScriptionMaster, Long> {
	SubScriptionMaster findBySubscriptionName(String name);
	List<SubScriptionMaster> findById(Long id);
}
