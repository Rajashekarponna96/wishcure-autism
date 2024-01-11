package com.openspace.PatientManagement.Scales;

import java.util.List;

import com.openspace.Model.MentalReferenceProfile.PatientMentalScale;
import com.openspace.Model.Scales.MentalScaleDto;
import com.openspace.Model.Scales.MentalScaleResultDto;
import com.openspace.Model.Scales.MentalScaleTemplateDto;
import com.openspace.Model.Scales.MentalScales;
import com.openspace.Model.Scales.MentalScalesCountGraphDto;

public interface MentalScalesService {

	void saveMentalScales(MentalScaleDto mentalScaleDto);

	List<MentalScales> getAllMentalScales();

	void updateMentalScales(MentalScales mentalScales);

	void deleteMentalScales(Long id);

	void updateMentalScalesWithCluster(MentalScaleDto mentalScaleDto);

	void assignMentalScaleTemplateToPatient(MentalScaleTemplateDto mentalScaleTemplateDto);

	/*List<MentalClusterCount> getMentalClusterCount(Long id, Long patientId);*/

	List<PatientMentalScale> getAllPatientMentalScalesByPatientId(Long patientId);

	void updatePatientMentalScales(List<PatientMentalScale> patientMentalScale,Long patientId);

	void getMentaAgeOfChild();

	List<MentalScaleResultDto> getAllPatientMentalClusterCountResult(Long patientId);

	List<MentalScalesCountGraphDto> getAllPatientMentalClusterCountForGraph(Long patientId);
	
}