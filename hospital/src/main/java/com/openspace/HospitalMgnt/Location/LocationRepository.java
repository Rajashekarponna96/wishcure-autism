package com.openspace.HospitalMgnt.Location;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.Location;

@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {
	List<Location> findByCity_Id(Long cityId);
}
