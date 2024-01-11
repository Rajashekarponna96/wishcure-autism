package com.openspace.PatientManagement.Clusters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Clusters.MentalClusters;

@RestController
public class MentalClustersController {
	@Autowired
	private MentalClustersService mentalClustersService;

	@RequestMapping(value = "/saveMentalCluster", method = RequestMethod.POST)
	public @ResponseBody void saveMentalCluster(@RequestBody MentalClusters mentalClusters) {
		mentalClustersService.saveMentalCluster(mentalClusters);
	}

	@RequestMapping(value = "/getAllMentalCluster", method = RequestMethod.GET)
	public @ResponseBody List<MentalClusters> getAllMentalCluster() {
		return mentalClustersService.getAllMentalCluster();
	}

	@RequestMapping(value = "/updateMentalCluster", method = RequestMethod.PUT)
	public @ResponseBody void updateMentalCluster(@RequestBody MentalClusters mentalClusters) {
		mentalClustersService.updateMentalCluster(mentalClusters);
	}

	@RequestMapping(value = "/deleteMentalCluster/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteMentalCluster(@PathVariable Long id) {
		mentalClustersService.deleteMentalCluster(id);
	}
}
