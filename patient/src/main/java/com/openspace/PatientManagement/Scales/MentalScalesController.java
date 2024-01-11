package com.openspace.PatientManagement.Scales;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.MentalReferenceProfile.PatientMentalScale;
import com.openspace.Model.Scales.MentalScaleDto;
import com.openspace.Model.Scales.MentalScaleResultDto;
import com.openspace.Model.Scales.MentalScaleTemplateDto;
import com.openspace.Model.Scales.MentalScales;
import com.openspace.Model.Scales.MentalScalesCountGraphDto;

@RestController
public class MentalScalesController {
	@Autowired
	private MentalScalesService mentalScalesService;

	@RequestMapping(value = "/saveMentalScales", method = RequestMethod.POST)
	public @ResponseBody void saveMentalScales(@RequestBody MentalScaleDto mentalScaleDto) {
		mentalScalesService.saveMentalScales(mentalScaleDto);
	}

	@RequestMapping(value = "/getAllMentalScales", method = RequestMethod.GET)
	public @ResponseBody List<MentalScales> getAllMentalScales() {
		return mentalScalesService.getAllMentalScales();
	}

	@RequestMapping(value = "/updateMentalScales", method = RequestMethod.PUT)
	public @ResponseBody void updateMentalScales(@RequestBody MentalScales mentalScales) {
		mentalScalesService.updateMentalScales(mentalScales);
	}

	@RequestMapping(value = "/updateMentalScalesWithCluster", method = RequestMethod.PUT)
	public @ResponseBody void updateMentalScalesWithCluster(@RequestBody MentalScaleDto mentalScaleDto) {
		mentalScalesService.updateMentalScalesWithCluster(mentalScaleDto);
	}

	@RequestMapping(value = "/deleteMentalScales/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteMentalScales(@PathVariable Long id) {
		mentalScalesService.deleteMentalScales(id);
	}

	//from test save
	@RequestMapping(value = "/assignmentalScalesToPatient", method = RequestMethod.POST)
	public void assignMentalScaleTemplateToPatient(@RequestBody MentalScaleTemplateDto mentalScaleTemplateDto) {
		if (mentalScaleTemplateDto.getPatient() == null) {
			throw new RuntimeException("Patient not Exists!");
		}
		mentalScalesService.assignMentalScaleTemplateToPatient(mentalScaleTemplateDto);
	}

	/*//it get count of each cluster
	@RequestMapping(value = "/groupCount/{reulstId}/{patientId}", method = RequestMethod.GET)
	public List<MentalClusterCount> getMentalClusterCount(@PathVariable("reulstId") Long id,
			@PathVariable(value = "patientId") Long patientId) {
		return mentalScalesService.getMentalClusterCount(id,patientId);
	}*/
	
	//get all mental scales that save in patient mental scales table
	@RequestMapping(value = "/getAllPatientMentalScales/{patientId}", method = RequestMethod.GET)
	public @ResponseBody List<PatientMentalScale> getAllPatientMentalScalesByPatientId(@PathVariable Long patientId) {
		return mentalScalesService.getAllPatientMentalScalesByPatientId(patientId);
	}
	
	//update mental scales that save in patient mental scales table
	@RequestMapping(value = "/updatePatientMentalScales/{patientId}", method = RequestMethod.PUT)
	public @ResponseBody void updatePatientMentalScales(@RequestBody List<PatientMentalScale> patientMentalScale,@PathVariable Long patientId) {
		 mentalScalesService.updatePatientMentalScales(patientMentalScale,patientId);
	}
	
	//
	@RequestMapping(value = "/getMentaAgeCount/", method = RequestMethod.GET)
	public  void getMentaAgeOfChild() {
		 mentalScalesService.getMentaAgeOfChild();
	}
	
	@RequestMapping(value = "/getAllPatientMentalClusterCountResult/{patientId}", method = RequestMethod.GET)
	public @ResponseBody List<MentalScaleResultDto> getAllPatientMentalClusterCountResult(@PathVariable Long patientId) {
		return mentalScalesService.getAllPatientMentalClusterCountResult(patientId);
	}
	
	@RequestMapping(value = "/getAllPatientMentalClusterCountForGraph/{patientId}", method = RequestMethod.GET)
	public @ResponseBody List<MentalScalesCountGraphDto> getAllPatientMentalClusterCountForGraph(@PathVariable Long patientId) {
		return mentalScalesService.getAllPatientMentalClusterCountForGraph(patientId);
	}
	

}	
