package com.openspace.Model.ParentModule.Mchart.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.ParentModule.Mchart.Mchart;
@Repository
public interface MchartRepository extends PagingAndSortingRepository<Mchart, Long>{

	List<Mchart> findByPatient_Id(Long id);


	Mchart findByPatient_IdAndMchartLookupIdAndSelectedAnswer(Long id, Object obj, boolean b);


}