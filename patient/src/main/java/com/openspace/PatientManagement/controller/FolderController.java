package com.openspace.PatientManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Parent.Folder;
import com.openspace.PatientManagement.service.FolderService;

@RestController
@RequestMapping(value = "/folder")
public class FolderController {

	@Autowired
	private FolderService folderService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void add(@RequestBody Folder folder) {
		folderService.add(folder);
	}

	@RequestMapping(value = "/all/{patientId}", method = RequestMethod.GET)
	public List<Folder> getAllFoldersByPatientId(@PathVariable("patientId") Long patientId) {
		return folderService.getAllFoldersByPatientId(patientId);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updateFolder(@RequestBody Folder folder) {
		 folderService.updateFolder(folder);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void deleteFolder(@PathVariable("id") Long id) {
		 folderService.deleteFolder(id);
	}
	
	
	

}
