package com.openspace.PatientManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Lookups.DocumentTypeLookup;
import com.openspace.PatientManagement.service.DocumentTypeLookupService;

@RestController
@RequestMapping(value = "/documentTypeLookup")
public class DocumentTypeLookupController {

	@Autowired
	private DocumentTypeLookupService documentTypeLookupService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody void add(@RequestBody DocumentTypeLookup documentTypeLookup) {
		documentTypeLookupService.add(documentTypeLookup);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody Page<DocumentTypeLookup>  getAll(@RequestParam("page")int page,@RequestParam("size")int size ) {
		return documentTypeLookupService.getall(page,size);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody void updateDocumentTypeLookup(@RequestBody DocumentTypeLookup documentTypeLookup) {
		documentTypeLookupService.updateDocumentTypeLookup(documentTypeLookup);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteDocumentTypeLookup(@PathVariable("id") Long id) {
		documentTypeLookupService.deleteDocumentTypeLookup(id);
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public @ResponseBody List<DocumentTypeLookup>  findAll() {
		return documentTypeLookupService.getAll();
	}
	
 }
