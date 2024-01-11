package com.openspace.PatientManagement.childobservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.ParentModule.Repositories.ChildObservationCategoryRepository;
import com.openspace.Model.ParentModule.childobservation.ChildObservationCategory;

@Service
public class ChildObservationCategoryServiceImpl implements ChildObservationCategoryService{
	
	@Autowired
	private ChildObservationCategoryRepository childObservationCategoryRepository;

	@Override
	public void saveChildObservationCategory(ChildObservationCategory childObservationCategory) {
		// TODO Auto-generated method stub
		ChildObservationCategory dbChildObservationCategory=childObservationCategoryRepository.findByName(childObservationCategory.getName());
		if(dbChildObservationCategory!=null){
			throw new RuntimeException("childObservationCategory already Exists!");
		}
		childObservationCategoryRepository.save(childObservationCategory);
	}

	@Override
	public void updateChildObservationCategory(ChildObservationCategory childObservationCategory) {
		// TODO Auto-generated method stub
		ChildObservationCategory dbChildObservationCategory=childObservationCategoryRepository.findOne(childObservationCategory.getId());
		if(dbChildObservationCategory==null){
			throw new RuntimeException("childObservationCategory doesn't exist");
		}
		ChildObservationCategory dbChildObservationCategory2=childObservationCategoryRepository.findByName(childObservationCategory.getName());
		if(dbChildObservationCategory2==null){
			dbChildObservationCategory.setName(childObservationCategory.getName());
		}else if(dbChildObservationCategory2.getId().equals(childObservationCategory.getId())){
			dbChildObservationCategory.setName(childObservationCategory.getName());
		}else{
			throw new RuntimeException("childObservationCategory already Exists!");
		}
		dbChildObservationCategory.setName(childObservationCategory.getName());
		dbChildObservationCategory.setStatus(childObservationCategory.getStatus());
		childObservationCategoryRepository.save(dbChildObservationCategory);
	}

	@Override
	public void deleteChildObservationCategory(Long id) {
		// TODO Auto-generated method stub
		ChildObservationCategory dbChildObservationCategory=childObservationCategoryRepository.findOne(id);
		if(dbChildObservationCategory==null){
			throw new RuntimeException("childObservationCategory doesn't exist");
		}
		childObservationCategoryRepository.delete(id);
	}

	@Override
	public List<ChildObservationCategory> getAllChildObservationCategories() {
		// TODO Auto-generated method stub
		return (List<ChildObservationCategory>) childObservationCategoryRepository.findAll();
	}

}
