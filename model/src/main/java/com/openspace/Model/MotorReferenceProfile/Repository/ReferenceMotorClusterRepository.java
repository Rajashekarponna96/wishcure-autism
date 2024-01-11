package com.openspace.Model.MotorReferenceProfile.Repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.MotorReferenceProfile.ReferenceMotorCluster;

@Repository
public interface ReferenceMotorClusterRepository extends PagingAndSortingRepository<ReferenceMotorCluster, Long> {

	List<ReferenceMotorCluster> findByMotorReferenceProfile_IdAndMotorClusterId(Long id, Long clusterId);

}
