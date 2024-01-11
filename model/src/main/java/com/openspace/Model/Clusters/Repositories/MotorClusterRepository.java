package com.openspace.Model.Clusters.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.Clusters.MotorCluster;

public interface MotorClusterRepository extends PagingAndSortingRepository<MotorCluster, Long>  {

	MotorCluster findByName(String name);

}
