package com.openspace.Model.ScalesRepositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.Scales.MotorClusterCount;

public interface MotorClusterCountRepository  extends PagingAndSortingRepository<MotorClusterCount, Long> {

	List<MotorClusterCount> findByMotorResult_Id(Long id);
}
