package com.openspace.Model.PatientMgnt.Repositories;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.City;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Long> {
	List<City> findByState_Id(Long id);
	City findByName(String cityName);

}
