package com.openspace.PatientManagement.childobservationLookup;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.ParentModule.Repositories.ChildObservationCategoryLookupRepository;
import com.openspace.Model.ParentModule.Repositories.ChildObservationCategoryRepository;
import com.openspace.Model.ParentModule.Repositories.ChildObservationQuestionRepository;
import com.openspace.Model.ParentModule.childobservation.ChildObservationCategory;
import com.openspace.Model.ParentModule.childobservation.ChildObservationCategoryLookup;
import com.openspace.Model.ParentModule.childobservation.ChildObservationQuestion;

@Service
public class ChildObservationCategoryLookupServiceImpl implements ChildObservationCategoryLookupService{
	
	@Autowired
	private ChildObservationCategoryLookupRepository childObservationCategoryLookupRepository;
	
	@Autowired
	private ChildObservationQuestionRepository questionRepository;

	@Override
	public void saveChildObservationCategoryLookup(ChildObservationCategoryLookup childObservationCategoryLookup) {
		// TODO Auto-generated method stub
		ChildObservationCategoryLookup dbChildObservationCategoryLookup=childObservationCategoryLookupRepository.findByName(childObservationCategoryLookup.getName());
		if(dbChildObservationCategoryLookup!=null){
			throw new RuntimeException("childObservationCategoryLookup already Exists!");
		}
		childObservationCategoryLookupRepository.save(childObservationCategoryLookup);
	}

	@Override
	public void updateChildObservationCategoryLookup(ChildObservationCategoryLookup childObservationCategoryLookup) {
		// TODO Auto-generated method stub
		ChildObservationCategoryLookup dbChildObservationCategoryLookup=childObservationCategoryLookupRepository.findOne(childObservationCategoryLookup.getId());
		if(dbChildObservationCategoryLookup==null){
			throw new RuntimeException("Child-ObservationCategory-Lookup doesn't Exists!");
		}
		ChildObservationCategoryLookup dbChildObservationCategoryLookup2=childObservationCategoryLookupRepository.findByName(childObservationCategoryLookup.getName());
		if(dbChildObservationCategoryLookup2==null){
				dbChildObservationCategoryLookup.setName(childObservationCategoryLookup.getName());
		}else if(dbChildObservationCategoryLookup2.getId().equals(childObservationCategoryLookup.getId())){
			dbChildObservationCategoryLookup.setName(childObservationCategoryLookup.getName());
		}else{
			throw new RuntimeException("childObservationCategoryLookup already Exists!");
		}
		dbChildObservationCategoryLookup.setName(childObservationCategoryLookup.getName());
		dbChildObservationCategoryLookup.setStatus(childObservationCategoryLookup.getStatus());
		childObservationCategoryLookupRepository.save(dbChildObservationCategoryLookup);
	}

	@Override
	public void deleteChildObservationCategoryLookup(Long id) {
		// TODO Auto-generated method stub
		ChildObservationCategoryLookup dbChildObservationCategoryLookup=childObservationCategoryLookupRepository.findOne(id);
		if(dbChildObservationCategoryLookup==null){
			throw new RuntimeException("childObservationCategoryLookup doesn't Exists!");
		}
		/*if(dbChildObservationCategoryLookup.){
			throw new RuntimeException("Please Delete Question Goals First");
		}*/
		List<ChildObservationQuestion> dbChildObservationQuestion=questionRepository.findByChildObservationCategery_Id(id);
		for(ChildObservationQuestion question:dbChildObservationQuestion){
		if(question.getChildObservationCategery().getId()==dbChildObservationCategoryLookup.getId()){
			throw new RuntimeException("Patient Question Goals Are Attached To This Category..! Please Delete Patient Question Goals First");
		}
		}
		childObservationCategoryLookupRepository.delete(id);
	}

	@Override
	public List<ChildObservationCategoryLookup> getAllChildObservationCategoryLookups() {
		// TODO Auto-generated method stub
		return (List<ChildObservationCategoryLookup>) childObservationCategoryLookupRepository.findAll();
	}

	@Override
	public List<Long> getAllChildObservationCategoryLookupsIds() {
		// TODO Auto-generated method stub
		List<ChildObservationCategoryLookup> idsList=(List<ChildObservationCategoryLookup>) childObservationCategoryLookupRepository.findAll();
		List<Long> list=new ArrayList<Long>();
		for(ChildObservationCategoryLookup id:idsList){
			Long a=id.getId();
			list.add(a);
		}
		return list;
	}

	

}
