package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.Lookups.RegionalCenterLookup;

public interface RegionalCenterLookupRepository extends PagingAndSortingRepository<RegionalCenterLookup, Long> {

	RegionalCenterLookup findByName(String name);

	List<RegionalCenterLookup> findByStatus(boolean b);

}
