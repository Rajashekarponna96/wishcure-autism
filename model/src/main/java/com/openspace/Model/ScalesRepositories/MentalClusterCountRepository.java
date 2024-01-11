package com.openspace.Model.ScalesRepositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.Scales.MentalClusterCount;

public interface MentalClusterCountRepository  extends PagingAndSortingRepository<MentalClusterCount, Long> {

	List<MentalClusterCount> findByMentalResult_Id(Long id);

}
