package com.openspace.PatientManagement.Mchart;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.ParentModule.Mchart.Mchart;
import com.openspace.Model.ParentModule.Mchart.MchartObjectDto;
import com.openspace.Model.ParentModule.Mchart.MchartResultDto;

@RestController
public class MchartController {
	@Autowired
	private MchartService mchartService;

	@RequestMapping(value = "/saveMchart", method = RequestMethod.POST)
	public @ResponseBody void saveMchart(@RequestBody MchartObjectDto mchartObject) {
		mchartObject.setDate(LocalDate.now());
		mchartService.saveMchart(mchartObject);
	}

	@RequestMapping(value = "/getAllMchart/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Mchart> getAllMchartsByPatientId(@PathVariable Long id) {
		return mchartService.getAllMchartsByPatientId(id);
	}

	@RequestMapping(value = "/getMchartCountByPatientId/{id}", method = RequestMethod.GET)
	public @ResponseBody MchartResultDto getMchartCountByPatientId(@PathVariable Long id) {
		return mchartService.getMchartCountByPatientId(id);
	}

	@RequestMapping(value = "/deleteMchart/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteMchart(@PathVariable Long id) {
		mchartService.deleteMchart(id);
	}
	
	@RequestMapping(value = "/deleteMchartsByPatientId/{patientId}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteMchartByPatient(@PathVariable Long patientId) {
		mchartService.deleteMchartByPatient(patientId);
	}
	
}
