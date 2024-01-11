package com.openspace.PatientManagement.ParentModule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.ParentModule.Repositories.CareTakerCategoryLookupRepository;
import com.openspace.Model.ParentModule.caretaker.CareTakerCategoryLookup;
import com.openspace.Model.UserManagement.Role;

@Service
public class CareTakerCategoryLookupServiceImpl implements CareTakerCategoryLookupService {
	
    @Autowired
	private CareTakerCategoryLookupRepository careTakerCategoryLookupRepository;

	@Override
	public void saveCareTakerCategoryLookup(CareTakerCategoryLookup careTakerCategoryLookup) {
		CareTakerCategoryLookup dbcareTakerCategoryLookup = careTakerCategoryLookupRepository.findByName(careTakerCategoryLookup.getName());
		if (dbcareTakerCategoryLookup != null) {
			throw new RuntimeException("CareTakerCategoryLookup Already Exists");
		} else {
			CareTakerCategoryLookup categoryLookup = new CareTakerCategoryLookup();
			categoryLookup.setName(careTakerCategoryLookup.getName());
			categoryLookup.setDescription(careTakerCategoryLookup.getDescription());
			careTakerCategoryLookupRepository.save(categoryLookup);
		}
	}

	@Override
	public List<CareTakerCategoryLookup> getCareTakerCategoryLookups() {
		return (List<CareTakerCategoryLookup>) careTakerCategoryLookupRepository.findAll();
	}

	@Override
	public void updateCareTakerCategoryLookup(CareTakerCategoryLookup careTakerCategoryLookup) {
		CareTakerCategoryLookup dbCareTakerCategoryLookup = careTakerCategoryLookupRepository
				.findOne(careTakerCategoryLookup.getId());
		if (dbCareTakerCategoryLookup == null) {
			throw new RuntimeException("CareTakerCategoryLookup Doesnot Exists");
		}
		CareTakerCategoryLookup lookup=careTakerCategoryLookupRepository.findByName(careTakerCategoryLookup.getName());
		if (lookup == null) {
				dbCareTakerCategoryLookup.setName(careTakerCategoryLookup.getName());
				dbCareTakerCategoryLookup.setDescription(careTakerCategoryLookup.getDescription());
				careTakerCategoryLookupRepository.save(dbCareTakerCategoryLookup);
			} else if(lookup.getId().equals(careTakerCategoryLookup.getId())) {
				dbCareTakerCategoryLookup.setName(careTakerCategoryLookup.getName());
				dbCareTakerCategoryLookup.setDescription(careTakerCategoryLookup.getDescription());
				careTakerCategoryLookupRepository.save(dbCareTakerCategoryLookup);
		} else {
			throw new RuntimeException("CareTakerCategoryLookup Already Exists");
		}
	}
	
	


	@Override
	public void deleteCareTakerCategoryLookup(Long id) {
		CareTakerCategoryLookup dbCareTakerCategoryLookup = careTakerCategoryLookupRepository.findOne(id);
		if (dbCareTakerCategoryLookup != null) {
			careTakerCategoryLookupRepository.delete(dbCareTakerCategoryLookup.getId());
		} else {
			throw new RuntimeException("CareTakerCategoryLookup Doesn't Exists");
		}
	}

}
