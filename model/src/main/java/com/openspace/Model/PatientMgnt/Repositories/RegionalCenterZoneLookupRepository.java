package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.RegionalCenterZoneLookup;

@Repository
public interface RegionalCenterZoneLookupRepository extends PagingAndSortingRepository<RegionalCenterZoneLookup, Long> {

	RegionalCenterZoneLookup findByRegionalCenterZoneName(String regionalCenterZoneName);

	List<RegionalCenterZoneLookup> findByStatus(boolean b);
	
	

}
