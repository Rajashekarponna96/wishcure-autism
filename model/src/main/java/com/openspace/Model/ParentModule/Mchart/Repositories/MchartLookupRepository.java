package com.openspace.Model.ParentModule.Mchart.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.Mchart.MchartLookup;
@Repository
public interface MchartLookupRepository extends PagingAndSortingRepository<MchartLookup, Long> {

	MchartLookup findByName(String name);

}
