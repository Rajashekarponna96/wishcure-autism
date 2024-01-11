package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.PatientRegistrationType;
import com.openspace.Model.Lookups.CategoryType;
import com.openspace.Model.Lookups.Department;
import com.openspace.Model.PatientMgnt.Repositories.CategoryTypeServiceRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class CategoryTypeServiceImpl implements CategoryTypeService {

	@Autowired
	private CategoryTypeServiceRepository categoryTypeServiceRepository;

	@Override
	public void add(CategoryType categoryType) {
		// TODO Auto-generated method stub
		CategoryType dbCategoryType = categoryTypeServiceRepository.findByName(categoryType.getName());
		if (dbCategoryType != null) {
			throw new RuntimeException(ErrorMessageHandler.categoryTypeAlreadyExists);
		}
		categoryType.setStatus(true);
		
		categoryTypeServiceRepository.save(categoryType);
	}

	@Override
	public List<CategoryType> getAll() {
		// TODO Auto-generated method stub
		return (List<CategoryType>) categoryTypeServiceRepository.findAll();
	}

	@Override
	public void deleteCategory(Long id) {
		// TODO Auto-generated method stub
		categoryTypeServiceRepository.delete(id);
	}

	@SuppressWarnings("unused")
	@Override
	public void updateCategory(CategoryType categoryType) {
		// TODO Auto-generated method stub
		CategoryType dbCategoryType = categoryTypeServiceRepository.findOne(categoryType.getId());
		if (dbCategoryType == null) {
			throw new RuntimeException(ErrorMessageHandler.categoryTypeDoesNotExists);
		}
		CategoryType dbCategoryType1 = categoryTypeServiceRepository.findByName(categoryType.getName());  
		if(dbCategoryType1 == null){
			dbCategoryType.setName(categoryType.getName());
			dbCategoryType.setDescription(categoryType.getDescription());
			dbCategoryType.setStatus(categoryType.getStatus());
			dbCategoryType.setPatientRegistrationType(categoryType.getPatientRegistrationType());
		} else if(dbCategoryType1 != null){
			dbCategoryType.setName(categoryType.getName());
			dbCategoryType.setDescription(categoryType.getDescription());
			dbCategoryType.setStatus(categoryType.getStatus());
			dbCategoryType.setPatientRegistrationType(categoryType.getPatientRegistrationType());
		}else{
			throw new RuntimeException(ErrorMessageHandler.categoryTypeAlreadyExists);
		}
		dbCategoryType.setName(categoryType.getName());
		dbCategoryType.setDescription(categoryType.getDescription());
		dbCategoryType.setStatus(categoryType.getStatus());
		dbCategoryType.setPatientRegistrationType(categoryType.getPatientRegistrationType());
		categoryTypeServiceRepository.save(dbCategoryType);
	}

	@Override
	public List<CategoryType> getAllByStatus() {	
		List<CategoryType> categoryType = categoryTypeServiceRepository.findByStatus(true);
		return categoryType;
	}

	@Override
	public List<CategoryType> getAllCategoryTypesListByRegistartionType(Long id) {
		// TODO Auto-generated method stub
		System.out.println("Data "+categoryTypeServiceRepository.findByPatientRegistrationType_Id(id).get(0));
		return categoryTypeServiceRepository.findByPatientRegistrationType_Id(id);
	}

	
	
	
}
