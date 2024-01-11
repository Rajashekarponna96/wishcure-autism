package com.openspace.Model.ScalesRepositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.Scales.MentalScales;

public interface MentalScalesRepositories extends PagingAndSortingRepository<MentalScales, Long> {

	MentalScales findByItemDescripation(String itemDescripation);

	List<MentalScales> findByMentalClusters_Id(Long id);


}
