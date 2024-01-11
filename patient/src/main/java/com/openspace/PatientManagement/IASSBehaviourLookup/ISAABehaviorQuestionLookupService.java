package com.openspace.PatientManagement.IASSBehaviourLookup;

import java.util.List;

import com.openspace.Model.ParentModule.ISAABehaviour.ISAABehaviorQuestionLookup;

public interface ISAABehaviorQuestionLookupService {

	void saveISAABehaviorQuestionLookup(ISAABehaviorQuestionLookup iSAABehaviorQuestionLookup);

	List<ISAABehaviorQuestionLookup> getAllISAABehaviorQuestionLookup();

	void updateISAABehaviorLookup(ISAABehaviorQuestionLookup iSAABehaviorQuestionLookup);

	void deleteISAABehaviorQuestionLookup(Long id);

	List<ISAABehaviorQuestionLookup> getAllISAABehaviorQuestionLookupByCategoryId(Long categoryId);

}
