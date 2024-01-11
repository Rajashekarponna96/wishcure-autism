package com.openspace.PatientManagement.Clusters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Clusters.MotorCluster;
import com.openspace.Model.Clusters.Repositories.MotorClusterRepository;

@Service
public class MotorClusterServiceImpl implements MotorClusterService {

	@Autowired
	private MotorClusterRepository motorClusterRepository;

	@Override
	public void saveMotorCluster(MotorCluster motorCluster) {
		MotorCluster dbMotorCluster = motorClusterRepository.findByName(motorCluster.getName());
		if (dbMotorCluster != null) {
			throw new RuntimeException("MotorCluster Already Exists with " + dbMotorCluster.getName());
		}
		motorClusterRepository.save(motorCluster);
	}

	@Override
	public List<MotorCluster> getAllMotorClusters() {
		return (List<MotorCluster>) motorClusterRepository.findAll();
	}

	@Override
	public void updateMotorCluster(MotorCluster motorCluster) {
		MotorCluster dbMotorCluster = motorClusterRepository.findOne(motorCluster.getId());
		if (dbMotorCluster == null) {
			throw new RuntimeException("MotorCluster Does not Exists!!!");
		}
		MotorCluster dbmotorCluster1 = motorClusterRepository.findByName(motorCluster.getName());
		if (dbmotorCluster1 == null) {
			dbMotorCluster.setName(motorCluster.getName());
			dbMotorCluster.setDescription(motorCluster.getDescription());
		} else if (dbmotorCluster1.getId().equals(dbMotorCluster.getId())) {
			dbMotorCluster.setName(motorCluster.getName());
			dbMotorCluster.setDescription(motorCluster.getDescription());
		} else {
			throw new RuntimeException("MotorCluster Already Exists with " + dbMotorCluster.getName());
		}

		if (motorCluster.getDescription() != null) {
			dbMotorCluster.setDescription(motorCluster.getDescription());
		}
		dbMotorCluster.setName(motorCluster.getName());
		motorClusterRepository.save(dbMotorCluster);
	}

	@Override
	public void deleteMotorCluster(Long id) {
		motorClusterRepository.delete(id);
	}

}
