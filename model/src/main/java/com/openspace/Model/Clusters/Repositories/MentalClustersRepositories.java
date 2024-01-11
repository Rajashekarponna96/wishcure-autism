package com.openspace.Model.Clusters.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.Clusters.MentalClusters;

public interface MentalClustersRepositories  extends PagingAndSortingRepository<MentalClusters, Long>{

	MentalClusters findByName(String name);


}
