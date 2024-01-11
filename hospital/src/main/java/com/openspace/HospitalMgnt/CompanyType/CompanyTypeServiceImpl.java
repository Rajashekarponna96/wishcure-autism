package com.openspace.HospitalMgnt.CompanyType;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.CompanyType;
import com.openspace.Model.PatientMgnt.Repositories.CompanyTypeRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class CompanyTypeServiceImpl implements CompanyTypeService {

	@Autowired
	private CompanyTypeRepository companyTypeRepository;

	@Override
	public void saveCompanyType(CompanyType companyType) {
		CompanyType dbCompanyType = companyTypeRepository.findByCompanyTypeName(companyType.getCompanyTypeName());
		if (dbCompanyType != null) {
			throw new RuntimeException(ErrorMessageHandler.companyTypeAlreadyExists);
		}
		companyType.setCreatedDate(LocalDate.now());
		companyType.setStatus(true);
		companyTypeRepository.save(companyType);
	}

	@Override
	public Page<CompanyType> getAllCompanyTypes(int page,int size) {
		// TODO Auto-generated method stub
		return (Page<CompanyType>) companyTypeRepository.findAll(new PageRequest(page, size,Sort.Direction.DESC,"id"));
	}

	@Override
	public List<CompanyType> getAllCompanyTypes() {
		// TODO Auto-generated method stub
		return (List<CompanyType>) companyTypeRepository.findByStatus(true);
	}

	@Override
	public void updateCompanyType(CompanyType companyType) {

		CompanyType dbCompanyType = companyTypeRepository.findOne(companyType.getId());
		if (dbCompanyType == null) {
			throw new RuntimeException(ErrorMessageHandler.companyTypeDoesNotExists);
		}
		CompanyType dbCompanyType2 = companyTypeRepository.findByCompanyTypeName(companyType.getCompanyTypeName());
		if (dbCompanyType2 == null) {
			dbCompanyType.setCompanyTypeName(companyType.getCompanyTypeName());
			dbCompanyType.setCompanyTypeDescription(companyType.getCompanyTypeDescription());
			dbCompanyType.setStatus(companyType.getStatus());
			dbCompanyType.setModifiedDate(LocalDate.now());
		} else if (dbCompanyType2.getId().equals(companyType.getId())) {
			dbCompanyType.setCompanyTypeName(companyType.getCompanyTypeName());
			dbCompanyType.setCompanyTypeDescription(companyType.getCompanyTypeDescription());
			dbCompanyType.setStatus(companyType.getStatus());
			dbCompanyType.setModifiedDate(LocalDate.now());
		} else {
			throw new RuntimeException(ErrorMessageHandler.companyTypeAlreadyExists);
		}
		dbCompanyType.setCompanyTypeName(companyType.getCompanyTypeName());
		dbCompanyType.setCompanyTypeDescription(companyType.getCompanyTypeDescription());
		dbCompanyType.setStatus(companyType.getStatus());
		dbCompanyType.setModifiedDate(LocalDate.now());
		companyTypeRepository.save(dbCompanyType);
	}

	@Override
	public void deleteCompanyType(Long id) {

		CompanyType dbCompanyType = companyTypeRepository.findOne(id);
		if (dbCompanyType == null) {
			throw new RuntimeException(ErrorMessageHandler.companyTypeDoesNotExists);
		}
		try{
			companyTypeRepository.delete(dbCompanyType);
		}catch (Exception e) {
			throw new RuntimeException("Can't Delete, It's Already In Use....!!");
		}
		
	}

}
