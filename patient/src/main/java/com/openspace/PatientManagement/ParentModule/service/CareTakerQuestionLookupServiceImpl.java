package com.openspace.PatientManagement.ParentModule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.ParentModule.Repositories.CareTakerAnswerLookupRepository;
import com.openspace.Model.ParentModule.Repositories.CareTakerCategoryLookupRepository;
import com.openspace.Model.ParentModule.Repositories.CareTakerQuestionLookupRepository;
import com.openspace.Model.ParentModule.caretaker.CareTakerAnswerLookup;
import com.openspace.Model.ParentModule.caretaker.CareTakerQuestionLookup;
@Service
public class CareTakerQuestionLookupServiceImpl implements CareTakerQuestionLookupService{
	
    @Autowired
	private CareTakerQuestionLookupRepository careTakerQuestionLookupRepository;
    
    @Autowired
   	private CareTakerCategoryLookupRepository careTakerCategoryLookupRepository ;
       
    
    @Autowired
   	private CareTakerAnswerLookupRepository careTakerAnswerLookupRepository;

	@Override
	public void saveCareTakerQuestionLookup(CareTakerQuestionLookup careTakerQuestionLookup) {
		CareTakerQuestionLookup dbcareTakerCategoryLookup = careTakerQuestionLookupRepository.findByName(careTakerQuestionLookup.getName());
		if (dbcareTakerCategoryLookup != null) {
			throw new RuntimeException("CareTakerCategoryLookup Already Exists");
		} else {
			CareTakerQuestionLookup careTakerQuestionlookup = new CareTakerQuestionLookup();
			careTakerQuestionlookup.setName(careTakerQuestionLookup.getName());
			System.out.println(careTakerQuestionLookup.getName());
			System.out.println(careTakerQuestionLookup.getCareTakerCategoryLookup().getName());
			careTakerQuestionlookup.setCareTakerCategoryLookup(careTakerQuestionLookup.getCareTakerCategoryLookup());
			careTakerQuestionLookupRepository.save(careTakerQuestionlookup);
			if(careTakerQuestionLookup.getCareTakerAnswerLookups()!=null){
			List<CareTakerAnswerLookup> answerList=careTakerQuestionLookup.getCareTakerAnswerLookups();
			for(CareTakerAnswerLookup answers:answerList){
				CareTakerAnswerLookup  careTakerAnswerLookup=new CareTakerAnswerLookup();
				careTakerAnswerLookup.setName(answers.getName());
				System.out.println(answers.getName());
				answers.setCareTakerQuestionLookup(careTakerQuestionlookup);
				careTakerAnswerLookupRepository.save(answers);
			}
			}
			
		}
	}

	@Override
	public List<CareTakerQuestionLookup> getCareTakerQuestionLookups() {
		List<CareTakerQuestionLookup> list= (List<CareTakerQuestionLookup>) careTakerQuestionLookupRepository.findAll();
		List<CareTakerQuestionLookup> careTakerQuestionLookupList = new ArrayList<CareTakerQuestionLookup>();
		for (CareTakerQuestionLookup careTakerQuestionLookup : list) {
			CareTakerQuestionLookup careTakerQuestionlookup = new CareTakerQuestionLookup();
			careTakerQuestionlookup.setId(careTakerQuestionLookup.getId());
			careTakerQuestionLookup.setName(careTakerQuestionLookup.getName());

			List<CareTakerAnswerLookup> answers = careTakerQuestionLookup.getCareTakerAnswerLookups();
			List<CareTakerAnswerLookup> answerList = new ArrayList<CareTakerAnswerLookup>();
			for (CareTakerAnswerLookup answer : answers) {
				CareTakerAnswerLookup careTakerAnswerLookup = new CareTakerAnswerLookup();
				careTakerAnswerLookup.setId(answer.getId());
				careTakerAnswerLookup.setName(answer.getName());
				answerList.add(careTakerAnswerLookup);
			}
			careTakerQuestionLookup.setCareTakerAnswerLookups(answerList);
			careTakerQuestionLookupList.add(careTakerQuestionLookup);
		}
		return careTakerQuestionLookupList;
	}

	@Override
	public void updateCareTakerQuestionLookup(CareTakerQuestionLookup careTakerQuestionLookup) {
		CareTakerQuestionLookup dbCareTakerQuestionLookup = careTakerQuestionLookupRepository
				.findOne(careTakerQuestionLookup.getId());
		if (dbCareTakerQuestionLookup != null) {
			if (dbCareTakerQuestionLookup.getId().equals(careTakerQuestionLookup.getId())) {
				dbCareTakerQuestionLookup.setName(careTakerQuestionLookup.getName());
				dbCareTakerQuestionLookup.setCareTakerCategoryLookup(careTakerQuestionLookup.getCareTakerCategoryLookup());
				careTakerQuestionLookupRepository.save(dbCareTakerQuestionLookup);
				List<CareTakerAnswerLookup> answerLookups=careTakerQuestionLookup.getCareTakerAnswerLookups();
				for(CareTakerAnswerLookup careTakerAnswerLookup:answerLookups){
					careTakerAnswerLookup.setCareTakerQuestionLookup(dbCareTakerQuestionLookup);
					careTakerAnswerLookupRepository.save(careTakerAnswerLookup);
				}
				
			} else {
				dbCareTakerQuestionLookup.setName(careTakerQuestionLookup.getName());
				dbCareTakerQuestionLookup.setCareTakerCategoryLookup(careTakerQuestionLookup.getCareTakerCategoryLookup());
				careTakerQuestionLookupRepository.save(dbCareTakerQuestionLookup);
				List<CareTakerAnswerLookup> answerLookups=careTakerQuestionLookup.getCareTakerAnswerLookups();
				for(CareTakerAnswerLookup careTakerAnswerLookup:answerLookups){
					careTakerAnswerLookup.setCareTakerQuestionLookup(dbCareTakerQuestionLookup);
					careTakerAnswerLookupRepository.save(careTakerAnswerLookup);
				}
			}
		} else {
			throw new RuntimeException("CareTakerCategoryLookup Doesn't Exists");

		}
		
		
	}

	@Override
	public void deleteCareTakerQuestionLookup(Long id) {
		CareTakerQuestionLookup careTakerQuestionLookup = careTakerQuestionLookupRepository.findOne(id);
		if (careTakerQuestionLookup != null) {
			careTakerQuestionLookupRepository.delete(careTakerQuestionLookup.getId());
		} else {
			throw new RuntimeException("CareTakerCategoryLookup Doesn't Exists");
		}
	}


	
}
