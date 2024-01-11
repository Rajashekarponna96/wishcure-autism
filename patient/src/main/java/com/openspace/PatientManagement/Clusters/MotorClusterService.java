package com.openspace.PatientManagement.Clusters;

import java.util.List;

import com.openspace.Model.Clusters.MotorCluster;

public interface MotorClusterService {

	void saveMotorCluster(MotorCluster motorCluster);

	List<MotorCluster> getAllMotorClusters();

	void updateMotorCluster(MotorCluster motorCluster);

	void deleteMotorCluster(Long id);
	
	

}
