package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.Country;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {
	List<Country> findByIsdcode(String isdcode);
	Country findById(Long id);
	Country findByName(String name);
}
