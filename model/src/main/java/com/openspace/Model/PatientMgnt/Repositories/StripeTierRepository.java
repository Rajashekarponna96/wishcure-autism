package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Stripe.StripeTier;

@Repository
public interface StripeTierRepository extends PagingAndSortingRepository<StripeTier, Long> {
	
	List<StripeTier> findByStripePlan_Id(Long id);

}
