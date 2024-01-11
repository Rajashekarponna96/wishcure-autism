package com.openspace.PatientManagement.childobservationLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.ParentModule.Repositories.ChildObservationAnswerLookupRepository;
import com.openspace.Model.ParentModule.Repositories.ChildObservationCategoryLookupRepository;
import com.openspace.Model.ParentModule.Repositories.ChildObservationQuestionLookupRepository;
import com.openspace.Model.ParentModule.childobservation.ChildObservationAnswerLookup;
import com.openspace.Model.ParentModule.childobservation.ChildObservationCategoryLookup;
import com.openspace.Model.ParentModule.childobservation.ChildObservationQuestionLookup;

@Service
public class ChildObservationQuestionLookupServiceImpl implements ChildObservationQuestionLookupService{
	
	@Autowired
	private ChildObservationQuestionLookupRepository childObservationQuestionLookupRepository;
	
	@Autowired
	private ChildObservationCategoryLookupRepository childObservationCategoryLookupRepository;
	
	@Autowired
	private ChildObservationAnswerLookupRepository childObservationAnswerLookupRepository;

	@Override
	public void saveChildObservationQuestionLookup(ChildObservationQuestionLookup childObservationQuestionLookup) {
		// TODO Auto-generated method stub
		ChildObservationQuestionLookup dbChildObservationQuestionLookup=childObservationQuestionLookupRepository.findByName(childObservationQuestionLookup.getName());
		if(dbChildObservationQuestionLookup!=null){
			throw new RuntimeException("ChildObservationQuestion Already Exists!");
		}
		ChildObservationCategoryLookup dbChildObservationCategoryLookup=childObservationCategoryLookupRepository.findOne(childObservationQuestionLookup.getChildObservationCategoryLookup().getId());
		
		/*childObservationQuestionLookup.setChildObservationCategoryLookup(dbChildObservationCategoryLookup);
		childObservationQuestionLookupRepository.save(childObservationQuestionLookup);*/
		ChildObservationQuestionLookup childObservationQuestionLookup2=new ChildObservationQuestionLookup();
		childObservationQuestionLookup2.setName(childObservationQuestionLookup.getName());
		childObservationQuestionLookup2.setChildObservationCategoryLookup(dbChildObservationCategoryLookup);
		childObservationQuestionLookup2.setAnswer(childObservationQuestionLookup.getAnswer());
		childObservationQuestionLookupRepository.save(childObservationQuestionLookup2);
		
		List<ChildObservationAnswerLookup> childObservationAnswerLookups = childObservationQuestionLookup.getChildObservationAnswerLookups();
		System.out.println(childObservationAnswerLookups.size());
		for(ChildObservationAnswerLookup observationAnswerLookup : childObservationAnswerLookups){
			//ChildObservationAnswerLookup observationAnswerLookup2 = new ChildObservationAnswerLookup();
			//observationAnswerLookup.setName(observationAnswerLookup.getName());
			//observationAnswerLookup.setSelectedAnswer(observationAnswerLookup.isSelectedAnswer());
			observationAnswerLookup.setChildObservationQuestionLookup(childObservationQuestionLookup2);
			childObservationAnswerLookupRepository.save(observationAnswerLookup);
			
		}
	}

	@Override
	public void updateChildObservationQuestionLookup(ChildObservationQuestionLookup childObservationQuestionLookup) {
		// TODO Auto-generated method stub
		ChildObservationQuestionLookup dbChildObservationQuestionLookup=childObservationQuestionLookupRepository.findOne(childObservationQuestionLookup.getId());
		if(dbChildObservationQuestionLookup==null){
			throw new RuntimeException("ChildObservationQuestion Doesn't Exists!");
		}
		ChildObservationQuestionLookup dbChildObservationQuestionLookup2=childObservationQuestionLookupRepository.findByName(childObservationQuestionLookup.getName());
		if(dbChildObservationQuestionLookup2==null){
			dbChildObservationQuestionLookup.setName(childObservationQuestionLookup.getName());
		}else if(dbChildObservationQuestionLookup2.getId().equals(childObservationQuestionLookup.getId())){
			dbChildObservationQuestionLookup.setName(childObservationQuestionLookup.getName());
		}else{
			throw new RuntimeException("ChildObservationQuestion Already Exists!");	
		}
		dbChildObservationQuestionLookup.setName(childObservationQuestionLookup.getName());
		dbChildObservationQuestionLookup.setAnswer(childObservationQuestionLookup.getAnswer());
		dbChildObservationQuestionLookup.setChildObservationCategoryLookup(childObservationQuestionLookup.getChildObservationCategoryLookup());
		childObservationQuestionLookupRepository.save(dbChildObservationQuestionLookup);
		
		List<ChildObservationAnswerLookup> childObservationAnswerLookups = childObservationQuestionLookup.getChildObservationAnswerLookups();
				for(ChildObservationAnswerLookup observationAnswerLookup : childObservationAnswerLookups){
					//ChildObservationAnswerLookup observationAnswerLookup2 = new ChildObservationAnswerLookup();
					//observationAnswerLookup.setName(observationAnswerLookup.getName());
					observationAnswerLookup.setChildObservationQuestionLookup(dbChildObservationQuestionLookup);
					childObservationAnswerLookupRepository.save(observationAnswerLookup);
				}
		
	}

	@Override
	public void deleteChildObservationQuestionLookup(Long id) {
		// TODO Auto-generated method stub
		ChildObservationQuestionLookup dbChildObservationQuestionLookup=childObservationQuestionLookupRepository.findOne(id);	
		if(dbChildObservationQuestionLookup==null){
			throw new RuntimeException("ChildObservationQuestion Doesn't Exists!");
		}
		childObservationQuestionLookupRepository.delete(id);
	}

	@Override
	public List<ChildObservationQuestionLookup> getAllChildObservationQuestionLookups() {
		// TODO Auto-generated method stub
		return (List<ChildObservationQuestionLookup>) childObservationQuestionLookupRepository.findAll();
	}

	@Override
	public List<ChildObservationQuestionLookup> getAllChildObservationQuestionLookupsByStatus(Long categorystatus) {
		// TODO Auto-generated method stub
		System.out.println(categorystatus);
		List<ChildObservationQuestionLookup> childObservationQuestionLookups=childObservationQuestionLookupRepository.findByChildObservationCategoryLookup_Id(categorystatus);
	//	List<ChildObservationQuestionLookup> questionLookups=new ArrayList<ChildObservationQuestionLookup>();
		
		return childObservationQuestionLookups;
	}

	

	

}
