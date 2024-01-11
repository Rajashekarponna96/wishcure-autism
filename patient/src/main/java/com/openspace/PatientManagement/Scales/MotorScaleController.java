package com.openspace.PatientManagement.Scales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.MotorReferenceProfile.PatientMotorScale;
import com.openspace.Model.Scales.MotorScale;
import com.openspace.Model.Scales.MotorScaleTemplateDto;

@RestController
@RequestMapping(value = "/motorScale")
public class MotorScaleController {

	@Autowired
	private MotorScaleService motorScaleService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addMotorScale(@RequestBody MotorScale motorScale) {
		motorScaleService.saveMotorScale(motorScale);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<MotorScale> findAll() {
		return motorScaleService.getAllMotorScales();
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updateMotorScale(@RequestBody MotorScale motorScale) {
		motorScaleService.updateMotorScale(motorScale);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteMotorScale(@PathVariable("id") Long id) {
		motorScaleService.deleteMotorScale(id);
	}

	@RequestMapping(value = "/allbyPagination", method = RequestMethod.GET)
	public Page<MotorScale> findAllPaginataionData(@RequestParam(value = "page", required = false) int page,
			@RequestParam(value = "size", required = false) int size) {
		return motorScaleService.getAllPaginatedMotorScales(page, size);
	}

	@RequestMapping(value = "/assignedToMotorCluster/{motorClusterId}", method = RequestMethod.POST)
	public void assignedToMotorCluster(@RequestBody List<MotorScale> motorScalesList,
			@PathVariable(value = "motorClusterId") Long motorClusterId) {
		motorScaleService.assignedToMotorCluster(motorScalesList, motorClusterId);
	}
	
	@RequestMapping(value = "/assignMotorScaleTemplateToPatient", method = RequestMethod.POST)
	public void assignMotorScaleTemplateToPatient(@RequestBody MotorScaleTemplateDto motorScaleTemplateDto) {
		//motorScaleTemplateDto.setMotorScales(motorScaleService.getAllMotorScales());
		motorScaleService.assignMotorScaleTemplateToPatient(motorScaleTemplateDto);
	}

	@RequestMapping(value = "/getAllPatientMotorScalesByPatientId/{patientId}", method = RequestMethod.GET)
	public List<PatientMotorScale> getAllPatientMotorScalesByPatientId(@PathVariable(value = "patientId") Long patientId) {
		return motorScaleService.getAllPatientMotorScalesByPatientId(patientId);
	}
	
	@RequestMapping(value = "/updatePatientMotorScales/{patientId}", method = RequestMethod.POST)
	public void updatePatientMotorScales(@RequestBody List<PatientMotorScale> patientMotorScaleList,
			@PathVariable(value = "patientId") Long patientId) {
		motorScaleService.updatePatientMotorScales(patientMotorScaleList, patientId);
	}
	
	@RequestMapping(value = "/getAllPatientMotorClusterCountResult/{patientId}", method = RequestMethod.GET)
	public List<MotorScaleResultDto> getAllPatientMotorClusterCountResult(@PathVariable(value = "patientId") Long patientId) {
		return motorScaleService.getAllPatientMotorClusterCountResult(patientId);
	}
	
	@RequestMapping(value = "/getAllPatientMotorClusterCountForGraph/{patientId}", method = RequestMethod.GET)
	public List<MotorScalesCountGraphDto> getAllPatientMotorClusterCountForGraph(@PathVariable(value = "patientId") Long patientId) {
		return motorScaleService.getAllPatientMotorClusterCountForGraph(patientId);
	}
}
