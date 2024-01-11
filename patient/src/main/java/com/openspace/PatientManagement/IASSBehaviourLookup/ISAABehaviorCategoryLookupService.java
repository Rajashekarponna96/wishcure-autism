package com.openspace.PatientManagement.IASSBehaviourLookup;

import java.util.List;

import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategoryLookup;
import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorCategoryLookupDto;

public interface ISAABehaviorCategoryLookupService {


	void saveISAABehaviorLookup(ISAABehaviorCategoryLookup iSAABehaviorCategoryLookup);
	
	List<ISAABehaviorCategoryLookup> getAllISAABehaviorLookup();

	List<ISAABehaviorCategoryLookupDto> getAllISAABehaviorLookupByCategoryStatus(Long id);

	void updateISAABehaviorLookup(ISAABehaviorCategoryLookup iSAABehaviorCategoryLookup);

	void deleteISAABehaviorLookup(Long id);
}
