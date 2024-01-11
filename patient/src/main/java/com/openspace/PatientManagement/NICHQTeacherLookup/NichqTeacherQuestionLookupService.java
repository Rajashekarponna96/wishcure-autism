package com.openspace.PatientManagement.NICHQTeacherLookup;

import java.util.List;
import com.openspace.Model.NICHQTeacherLookup.NichqTeacherQuestionLookup;

public interface NichqTeacherQuestionLookupService {

	void addQuestionLookup(NichqTeacherQuestionLookup nichqTeacherQuestionLookup);

	List<NichqTeacherQuestionLookup> getAllQuestionLookups();

	void updateNichqTeacherQuestionLookup(NichqTeacherQuestionLookup nichqTeacherQuestionLookup);

	void deleteNichqTeacherQuestionLookup(Long id);

	List<NichqTeacherQuestionLookup> getAllQuestionLookupsByCategory(Long categoryId);
	
}
