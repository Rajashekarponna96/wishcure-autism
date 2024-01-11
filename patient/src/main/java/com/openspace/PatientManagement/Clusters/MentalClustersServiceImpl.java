package com.openspace.PatientManagement.Clusters;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Clusters.MentalClusters;
import com.openspace.Model.Clusters.Repositories.MentalClustersRepositories;
import com.openspace.Model.Lookups.Department;

@Service
public class MentalClustersServiceImpl implements MentalClustersService {
	@Autowired
	private MentalClustersRepositories mentalClustersRepositories;

	@Override
	public void saveMentalCluster(MentalClusters mentalClusters) {
		
		MentalClusters dbMentalClusters = mentalClustersRepositories.findByName(mentalClusters.getName());
		if(dbMentalClusters !=null){
			throw new RuntimeException("Mental cluster already exists!!");
		}
		else{
			MentalClusters mentalClusters1 =new MentalClusters();
			mentalClusters1.setName(mentalClusters.getName());
			mentalClusters1.setDescripation(mentalClusters.getDescripation());
			mentalClustersRepositories.save(mentalClusters1);
		}
	}

	@Override
	public List<MentalClusters> getAllMentalCluster() {
		return (List<MentalClusters>)mentalClustersRepositories.findAll();
	}

	@Override
	public void updateMentalCluster(MentalClusters mentalClusters) {
		
		MentalClusters dbMentalClusters = mentalClustersRepositories.findOne(mentalClusters.getId());
		if(dbMentalClusters == null){
			throw new RuntimeException("Mental cluster Not exists!!");
		}
		MentalClusters dbMentalClusters2 = mentalClustersRepositories.findByName(mentalClusters.getName());
		
		if (dbMentalClusters2 == null) {
			dbMentalClusters.setName(mentalClusters.getName());
			dbMentalClusters.setDescripation(mentalClusters.getDescripation());
		} else if (dbMentalClusters2.getId().equals(dbMentalClusters.getId())) {
			dbMentalClusters.setName(mentalClusters.getName());
			dbMentalClusters.setDescripation(mentalClusters.getDescripation());
		} else {
			throw new RuntimeException("Department Already Exist!");
		}
		dbMentalClusters.setName(mentalClusters.getName());
		dbMentalClusters.setDescripation(mentalClusters.getDescripation());
		mentalClustersRepositories.save(dbMentalClusters);
	}

	@Override
	public void deleteMentalCluster(Long id) {
		MentalClusters dbMentalClusters = mentalClustersRepositories.findOne(id);
		if(dbMentalClusters == null){
			throw new RuntimeException("Mental cluster Not exists!!");
		}
		else{
			mentalClustersRepositories.delete(id);
		}
	}
	
}
