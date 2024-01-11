package com.openspace.PatientManagement.NICHQParentLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.NICHQParent.NICHQParentCategoryLookup;
import com.openspace.Model.NICHQParent.NICHQParentCategoryLookupDto;

@RestController
public class NICHQParentCategoryLookupController {
	
	@Autowired
	private NICHQParentCategoryLookupService nichqParentCategoryLookupService;
	
	@RequestMapping(value = "/saveNICHQParentCategoryLookup", method = RequestMethod.POST)
	public @ResponseBody void saveNICHQParentCategoryLookup(@RequestBody NICHQParentCategoryLookup nichqParentCategoryLookup){
		nichqParentCategoryLookupService.saveNICHQParentCategoryLookup(nichqParentCategoryLookup);
	}
	
	@RequestMapping(value = "/getAllNICHQParentCategoryLookup", method = RequestMethod.GET)
	public @ResponseBody List<NICHQParentCategoryLookup> getAllNICHQParentCategoryLookup(){
		return nichqParentCategoryLookupService.getAllNICHQParentCategoryLookup();
	}

	@RequestMapping(value = "/getAllNichqParentCategoryLookupByStatus/{id}", method = RequestMethod.GET)
	public @ResponseBody List<NICHQParentCategoryLookupDto> getAllNichqParentCategoryLookupByStatus(@PathVariable Long id){
		return nichqParentCategoryLookupService.getAllNichqParentCategoryLookupByStatus(id);
	}
	
	@RequestMapping(value = "/updateNICHQParentCategoryLookup", method = RequestMethod.PUT)
	public @ResponseBody void updateNICHQParentCategoryLookup(@RequestBody NICHQParentCategoryLookup nichqParentCategoryLookup){
		nichqParentCategoryLookupService.updateNICHQParentCategoryLookup(nichqParentCategoryLookup);
	}

	@RequestMapping(value = "/deleteNICHQParentCategoryLookup/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteNICHQParentCategoryLookup(@PathVariable Long id){
		nichqParentCategoryLookupService.deleteNICHQParentCategoryLookup(id);
	}
	
 }
