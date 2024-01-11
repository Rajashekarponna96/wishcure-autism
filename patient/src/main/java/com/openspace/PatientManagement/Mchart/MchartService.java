package com.openspace.PatientManagement.Mchart;

import java.util.List;
import java.util.Set;

import com.openspace.Model.ParentModule.Mchart.Mchart;
import com.openspace.Model.ParentModule.Mchart.MchartObjectDto;
import com.openspace.Model.ParentModule.Mchart.MchartResultDto;

public interface MchartService {


	void saveMchart(MchartObjectDto mchartObject);

	List<Mchart> getAllMchart(Long id);

	void deleteMchart(Long id);

	MchartResultDto getMchartCountByPatientId(Long id);

	void deleteMchartByPatient(Long patientId);

	List<Mchart> getAllMchartsByPatientId(Long id);

	Set<String> getEvalutionSheetDatesByPatientId(Long patientId);

}