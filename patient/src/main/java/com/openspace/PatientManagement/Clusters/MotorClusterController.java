package com.openspace.PatientManagement.Clusters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Clusters.MotorCluster;

@RestController
@RequestMapping(value = "/motorCluster")
public class MotorClusterController {

	@Autowired
	private MotorClusterService motorClusterService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addMotorCluster(@RequestBody MotorCluster motorCluster) {
		motorClusterService.saveMotorCluster(motorCluster);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<MotorCluster> findAll() {
	return	motorClusterService.getAllMotorClusters();
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updateMotorCluster(@RequestBody MotorCluster motorCluster) {
		motorClusterService.updateMotorCluster(motorCluster);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteMotorCluster(@PathVariable("id") Long id) {
		motorClusterService.deleteMotorCluster(id);
	}
	
}
