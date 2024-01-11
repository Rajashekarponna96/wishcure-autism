package com.openspace.Model.Clusters.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.Clusters.MentalClusters;
import com.openspace.Model.Clusters.VSMSCluster;

public interface VSMSClustersRepositories  extends PagingAndSortingRepository<VSMSCluster, Long>{

	VSMSCluster findByName(String name);


}
