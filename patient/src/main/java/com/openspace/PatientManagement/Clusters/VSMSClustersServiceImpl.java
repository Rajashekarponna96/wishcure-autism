package com.openspace.PatientManagement.Clusters;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Clusters.MentalClusters;
import com.openspace.Model.Clusters.VSMSCluster;
import com.openspace.Model.Clusters.Repositories.MentalClustersRepositories;
import com.openspace.Model.Clusters.Repositories.VSMSClustersRepositories;
import com.openspace.Model.Lookups.Department;

@Service
public class VSMSClustersServiceImpl implements VSMSClustersService {
	@Autowired
	private VSMSClustersRepositories vsmsClustersRepositories;

	@Override
	public void saveVSMSCluster(VSMSCluster vsmsClusters) {
		
		VSMSCluster dbVSMSClusters = vsmsClustersRepositories.findByName(vsmsClusters.getName());
		if(dbVSMSClusters !=null){
			throw new RuntimeException("VSMS cluster already exists!!");
		}
		else{
			VSMSCluster vsmsClusters1 =new VSMSCluster();
			vsmsClusters1.setName(vsmsClusters.getName());
			vsmsClusters1.setDescription(vsmsClusters.getDescription());
			vsmsClustersRepositories.save(vsmsClusters1);
		}
	}

	@Override
	public List<VSMSCluster> getAllVSMSCluster() {
		return (List<VSMSCluster>)vsmsClustersRepositories.findAll();
	}

	@Override
	public void updateVSMSCluster(VSMSCluster vsmsClusters) {
		
		VSMSCluster dbVSMSClusters = vsmsClustersRepositories.findOne(vsmsClusters.getId());
		if(dbVSMSClusters == null){
			throw new RuntimeException("Mental cluster Not exists!!");
		}
		VSMSCluster dbVSMSClusters2 = vsmsClustersRepositories.findByName(vsmsClusters.getName());
		
		if (dbVSMSClusters2 == null) {
			dbVSMSClusters.setName(vsmsClusters.getName());
			dbVSMSClusters.setDescription(vsmsClusters.getDescription());
		} else if (dbVSMSClusters2.getId().equals(dbVSMSClusters.getId())) {
			dbVSMSClusters.setName(vsmsClusters.getName());
			dbVSMSClusters.setDescription(vsmsClusters.getDescription());
		} else {
			throw new RuntimeException("VSMS Cluster Already Exist!");
		}
		dbVSMSClusters.setName(vsmsClusters.getName());
		dbVSMSClusters.setDescription(vsmsClusters.getDescription());
		vsmsClustersRepositories.save(dbVSMSClusters);
	}

	@Override
	public void deleteVSMSCluster(Long id) {
		VSMSCluster dbVSMSClusters = vsmsClustersRepositories.findOne(id);
		if(dbVSMSClusters == null){
			throw new RuntimeException("Mental cluster Not exists!!");
		}
		else{
			vsmsClustersRepositories.delete(id);
		}
	}
	
}
