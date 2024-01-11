package com.openspace.PatientManagement.Clusters;

import java.util.List;

import com.openspace.Model.Clusters.MentalClusters;

public interface MentalClustersService {

	void saveMentalCluster(MentalClusters mentalClusters);

	List<MentalClusters> getAllMentalCluster();

	void updateMentalCluster(MentalClusters mentalClusters);

	void deleteMentalCluster(Long id);

}