package com.openspace.Model.ScalesRepositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Scales.MotorScale;

@Repository
public interface MotorScaleRepository  extends PagingAndSortingRepository<MotorScale, Long>   {

	MotorScale findByItemDescripation(String itemDescripation);

	List<MotorScale> findByMotorCluster_Id(Long id);

}
