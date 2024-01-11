package com.openspace.PatientManagement.Clusters;

import java.util.List;

import com.openspace.Model.Clusters.MentalClusters;
import com.openspace.Model.Clusters.VSMSCluster;

public interface VSMSClustersService {

	void saveVSMSCluster(VSMSCluster vsmsClusters);

	List<VSMSCluster> getAllVSMSCluster();

	void updateVSMSCluster(VSMSCluster vsmsClusters);

	void deleteVSMSCluster(Long id);

	

}