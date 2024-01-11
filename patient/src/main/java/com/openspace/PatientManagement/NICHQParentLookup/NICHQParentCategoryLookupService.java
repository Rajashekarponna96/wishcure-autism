package com.openspace.PatientManagement.NICHQParentLookup;

import java.util.List;

import com.openspace.Model.NICHQParent.NICHQParentCategoryLookup;
import com.openspace.Model.NICHQParent.NICHQParentCategoryLookupDto;

public interface NICHQParentCategoryLookupService {

	void saveNICHQParentCategoryLookup(NICHQParentCategoryLookup nichqParentCategoryLookup);

	void deleteNICHQParentCategoryLookup(Long id);

	void updateNICHQParentCategoryLookup(NICHQParentCategoryLookup nichqParentCategoryLookup);

	List<NICHQParentCategoryLookup> getAllNICHQParentCategoryLookup();

	List<NICHQParentCategoryLookupDto> getAllNichqParentCategoryLookupByStatus(Long id);

}
