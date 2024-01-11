package com.openspace.PatientManagement.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.Lookups.DocumentTypeLookup;
import com.openspace.Model.Parent.Folder;
import com.openspace.Model.PatientMgnt.Repositories.DocumentTypeLookupRepository;
import com.openspace.Model.PatientMgnt.Repositories.FolderRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.Util.ErrorMessageHandler;
import com.openspace.PatientManagement.s3.S3Bucket;

@Service
public class FolderServiceImpl implements FolderService {

	@Autowired
	private FolderRepository folderRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DocumentTypeLookupRepository documentTypeLookupRepository;
	
	@Autowired
	private PatientService patientService;

	@Override
	public void add(Folder folder) {
		if (folder != null) {
			Folder dbFolder = folderRepository.findByNameAndPatient_Id(folder.getName(), folder.getPatient().getId());
			if (dbFolder != null) {
				throw new RuntimeException(ErrorMessageHandler.folderExistsForThisPatient);
			}
			//S3Bucket s3Bucket = new S3Bucket();
			List<Folder> foldersList = new ArrayList<Folder>();
			foldersList.add(folder);
			List<DocumentTypeLookup> documnetTypeList = (List<DocumentTypeLookup>) documentTypeLookupRepository
					.findAll();
			if (documnetTypeList.size() > 0) {
				for (DocumentTypeLookup documentTypeLookup : documnetTypeList) {
					Folder folder1 = folderRepository.findByNameAndPatient_Id(documentTypeLookup.getFolderName(),
							folder.getPatient().getId());
					if (folder1 == null) {
						Folder folder2 = new Folder();
						folder2.setDescription(documentTypeLookup.getDescription());
						folder2.setName(documentTypeLookup.getFolderName());
						folder2.setPatient(folder.getPatient());
						foldersList.add(folder2);
						patientService.addFolder(folder);
					}
				}
			}
			patientService.addFolder(folder);
			folderRepository.save(foldersList);
		}
	}

	@Override
	public List<Folder> getAllFoldersByPatientId(Long patientId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		List<Folder> foldersList = new ArrayList<Folder>();
		List<Folder> folders = folderRepository.findByPatient_Id(dbPatient.getId());
		if (folders.size() == 0) {
			List<DocumentTypeLookup> documnetTypeList = (List<DocumentTypeLookup>) documentTypeLookupRepository
					.findAll();
			if (documnetTypeList.size() > 0) {
				for (DocumentTypeLookup documentTypeLookup : documnetTypeList) {
					Folder folder2 = new Folder();
					folder2.setDescription(documentTypeLookup.getDescription());
					folder2.setName(documentTypeLookup.getFolderName());
					folder2.setPatient(dbPatient);
					foldersList.add(folder2);
				}
			}
		} else {
			foldersList.addAll(folders);
		}
		return foldersList;
	}

	@Override
	public void updateFolder(Folder folder) {
		// TODO Auto-generated method stub
		Folder folder1 = folderRepository.findOne(folder.getId());
		if (folder1 == null) {
			throw new RuntimeException(ErrorMessageHandler.folderDoesNotExists);
		}
		Folder folder2 = folderRepository.findByName(folder.getName());
		if (folder2 == null) {
			folder1.setDescription(folder.getDescription());
			folder1.setName(folder.getName());
		} else if (folder2.getId().equals(folder.getId())) {
			folder1.setDescription(folder.getDescription());
			folder1.setName(folder.getName());
		} else {
			throw new RuntimeException(ErrorMessageHandler.folderExistsForThisPatient);
		}
		folderRepository.save(folder1);
	}

	@Override
	public void deleteFolder(Long id) {
		Folder dbFolder = folderRepository.findOne(id);
		if (dbFolder != null) {
			folderRepository.delete(dbFolder);
		}
	}

}
