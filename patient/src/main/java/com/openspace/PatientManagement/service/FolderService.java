package com.openspace.PatientManagement.service;

import java.util.List;

import com.openspace.Model.Parent.Folder;

public interface FolderService {

	void add(Folder folder);

	List<Folder> getAllFoldersByPatientId(Long patientId);

	void updateFolder(Folder folder);

	void deleteFolder(Long id);

}
