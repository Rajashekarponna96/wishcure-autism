package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.InsuranceLookup;

@Repository
public interface InsuranceLookupRepository extends PagingAndSortingRepository<InsuranceLookup, Long> {

	InsuranceLookup findByInsuranceCompanyName(String name);
}
