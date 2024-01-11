package com.openspace.PatientManagement.Mchart;

import java.util.List;

import com.openspace.Model.ParentModule.Mchart.MchartLookup;

public interface MchartLookupService {

	void saveMchartLookup(MchartLookup mchartLookup);

	List<MchartLookup> getAllMchartLookup();

	void updateMchartLookup(MchartLookup mchartLookup);

	void deleteMchartLookup(Long id);
}
