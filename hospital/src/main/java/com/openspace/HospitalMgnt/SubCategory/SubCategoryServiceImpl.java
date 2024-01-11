package com.openspace.HospitalMgnt.SubCategory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.CategoryType;
import com.openspace.Model.Lookups.SubCategoryType;
import com.openspace.Model.PatientMgnt.Repositories.CategoryTypeServiceRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
	
	@Autowired
	SubCategoryRepository subCategoryRepository;
	
	@Autowired
	CategoryTypeServiceRepository catRepository;

	@Override
	public void saveSubCategory(SubCategoryType subCategory) {
		// TODO Auto-generated method stub
		
		SubCategoryType dbSubCat = subCategoryRepository.findByName(subCategory.getName());
		
		if (dbSubCat != null) {
			throw new RuntimeException("Sub Category already exists");
		}
		
		subCategory.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		subCategory.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
		subCategory.setStatus(true);
		
		CategoryType cat = catRepository.findOne(subCategory.getCategoryType().getId());
		
		if(cat == null)
		{
			throw new RuntimeException("Category Type Does not Exists");
		}
		else {
				subCategory.setCategoryType(cat);
		}
		
		subCategoryRepository.save(subCategory);
		
		
	}

	@Override
	public List<SubCategoryType> getAllSubCategorys() {
		// TODO Auto-generated method stub
		return (List<SubCategoryType>)subCategoryRepository.findAll();
	}

	@Override
	public void updateSubCategory(SubCategoryType subcategory) {
		// TODO Auto-generated method stub
		
		SubCategoryType dbSubCattype = subCategoryRepository.findOne(subcategory.getId());
		
		
		if (dbSubCattype == null) {
			throw new RuntimeException(ErrorMessageHandler.subCategoryTypeDoesNotExists);
		}
		SubCategoryType dbSubCattype1 = subCategoryRepository.findByName(subcategory.getName());  
		if(dbSubCattype1 == null){
			dbSubCattype.setName(subcategory.getName());
			dbSubCattype.setDescription(subcategory.getDescription());
			dbSubCattype.setStatus(subcategory.getStatus());
			
			CategoryType cat = catRepository.findOne(subcategory.getCategoryType().getId());
			
			if(cat == null)
			{
				throw new RuntimeException("Category Type Does not Exists");
			}
			else {
				dbSubCattype.setCategoryType(cat);
				
			}
			
			
		} else if(dbSubCattype1 != null){
			dbSubCattype.setName(subcategory.getName());
			dbSubCattype.setDescription(subcategory.getDescription());
			dbSubCattype.setStatus(subcategory.getStatus());
			
			CategoryType cat = catRepository.findOne(subcategory.getCategoryType().getId());
			
			if(cat == null)
			{
				throw new RuntimeException("Category Type Does not Exists");
			}
			else {
				dbSubCattype.setCategoryType(cat);
				
			}
		}
		subCategoryRepository.save(dbSubCattype);
		
	}

	@Override
	public void deleteSubCategory(Long id) {
		// TODO Auto-generated method stub
		subCategoryRepository.delete(id);
	}

	@Override
	public List<SubCategoryType> getAllSubCategoryTypesListByCategoryType(Long id) {
		// TODO Auto-generated method stub
		List<SubCategoryType> subList = subCategoryRepository.findByCategoryType_Id(id);
		System.out.println("list size -->"+subList.size());
		return subCategoryRepository.findByCategoryType_Id(id);
	}
	
	

}
