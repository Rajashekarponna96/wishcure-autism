package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.State;

@Repository
public interface StateRepository extends PagingAndSortingRepository<State, Long> {
	List<State> findByCountry_Id(long id);
	State findByName(String name);

}
