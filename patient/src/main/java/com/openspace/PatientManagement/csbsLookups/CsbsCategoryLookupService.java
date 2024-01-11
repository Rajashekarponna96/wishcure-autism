package com.openspace.PatientManagement.csbsLookups;

import java.util.List;

import com.openspace.Model.ParentModule.csbs.CsbsCategoryLookup;
import com.openspace.Model.ParentModule.csbs.CsbsCategoryLookupDto;

public interface CsbsCategoryLookupService {


	void saveCsbsLookup(CsbsCategoryLookup csbsCategoryLookup);

	List<CsbsCategoryLookup> getAllCsbsLookup();

	void updateCsbsLookup(CsbsCategoryLookup csbsCategoryLookup);
	
	void deleteCsbsLookup(Long id);

	List<CsbsCategoryLookupDto> getAllCsbsLookupByCategoryStatus(Long id);
}
