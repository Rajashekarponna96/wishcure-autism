package com.openspace.PatientManagement.Scales;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.MotorReferenceProfile.PatientMotorScale;
import com.openspace.Model.Scales.MotorScale;
import com.openspace.Model.Scales.MotorScaleTemplateDto;

public interface MotorScaleService {

	void saveMotorScale(MotorScale motorScale);

	List<MotorScale> getAllMotorScales();

	void updateMotorScale(MotorScale motorScale);

	void deleteMotorScale(Long id);

	Page<MotorScale> getAllPaginatedMotorScales(int page, int size);

	void assignedToMotorCluster(List<MotorScale> motorScalesList, Long motorClusterId);

	void assignMotorScaleTemplateToPatient(MotorScaleTemplateDto motorScaleTemplateDto);

	List<PatientMotorScale> getAllPatientMotorScalesByPatientId(Long patientId);

	void updatePatientMotorScales(List<PatientMotorScale> patientMotorScale, Long patientId);

	List<MotorScaleResultDto> getAllPatientMotorClusterCountResult(Long patientId);

	List<MotorScalesCountGraphDto> getAllPatientMotorClusterCountForGraph(Long patientId);

}
