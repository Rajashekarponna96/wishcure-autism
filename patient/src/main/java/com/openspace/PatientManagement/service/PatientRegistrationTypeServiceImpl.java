package com.openspace.PatientManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.PatientRegistrationType;
import com.openspace.Model.Lookups.CategoryType;
import com.openspace.Model.Lookups.SubCategoryType;
import com.openspace.Model.PatientMgnt.Repositories.PatientRegistrationTypeRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class PatientRegistrationTypeServiceImpl  implements PatientRegistrationTypeService{
	
	@Autowired
	private PatientRegistrationTypeRepository patientRegistrationTypeRepository;

	@Override
	public List<PatientRegistrationType> getAllPatientRegistrationTypes() {
		// TODO Auto-generated method stub
		return  (List<PatientRegistrationType>) patientRegistrationTypeRepository.findAll();
	}

	@Override
	public void add(PatientRegistrationType patientRegistrationType) {
		// TODO Auto-generated method stub
		System.out.println("entered in add patient reg");
		PatientRegistrationType dbPatientRegistrationType = patientRegistrationTypeRepository.findByName(patientRegistrationType.getName());
		
		if(dbPatientRegistrationType != null)
		{
			throw new RuntimeException(ErrorMessageHandler.patientRegTypeAlreadyExists);
		}
		patientRegistrationType.setStatus(true);
		patientRegistrationTypeRepository.save(patientRegistrationType);
		
		
	}

	@Override
	public void deleteRegistration(Long id) {
		// TODO Auto-generated method stub
		patientRegistrationTypeRepository.delete(id);
	}

	@Override
	public void updateRegistration(PatientRegistrationType patientRegistrationType) {
		// TODO Auto-generated method stub
		
		PatientRegistrationType dbpType = patientRegistrationTypeRepository.findOne(patientRegistrationType.getId());
		
		if(dbpType == null)
		{
			throw new RuntimeException(ErrorMessageHandler.patientRegTypeDoesNotExists);
		}
		
		PatientRegistrationType pRType = patientRegistrationTypeRepository.findByName(patientRegistrationType.getName());
		
		if(pRType == null)
		{
			dbpType.setName(patientRegistrationType.getName());
			dbpType.setDescription(patientRegistrationType.getDescription());
			dbpType.setStatus(patientRegistrationType.isStatus());
			
		}
		else if(pRType != null)
		{
			dbpType.setName(patientRegistrationType.getName());
			dbpType.setDescription(patientRegistrationType.getDescription());
			dbpType.setStatus(patientRegistrationType.isStatus());
		}
		
		patientRegistrationTypeRepository.save(dbpType);
		
	}

	@Override
	public List<PatientRegistrationType> getAllByStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}
