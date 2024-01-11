package com.openspace.Model.MentalReferenceProfile.Repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.MentalReferenceProfile.ReferenceMentalCluster;

@Repository
public interface ReferenceMentalClusterRepository extends PagingAndSortingRepository<ReferenceMentalCluster, Long> {
	
	List<ReferenceMentalCluster> findByMentalClusterId(Long clusterId);

	List<ReferenceMentalCluster> findByMentalReferenceProfile_Id(Long id);
	List<ReferenceMentalCluster> findByMentalReferenceProfile_IdAndMentalClusterId(Long id,Long clusterId);

}
