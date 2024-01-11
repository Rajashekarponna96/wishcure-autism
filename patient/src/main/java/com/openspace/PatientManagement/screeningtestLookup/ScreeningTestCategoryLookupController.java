package com.openspace.PatientManagement.screeningtestLookup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Dto.ScreeningTestCategoryLookupDto;
import com.openspace.Model.ParentModule.screeningtest.ScreeningTestCategoryLookup;

@RestController
public class ScreeningTestCategoryLookupController {
	@Autowired
	private ScreeningTestCategoryLookupService screeningTestLookupService;
	
	@RequestMapping(value = "/saveScreeningTestLookup", method = RequestMethod.POST)
	public @ResponseBody void saveScreeningTestLookup(@RequestBody ScreeningTestCategoryLookup screeningTestCategoryLookup){
		screeningTestLookupService.saveScreeningTestLookup(screeningTestCategoryLookup);
	}
	
	@RequestMapping(value = "/getAllScreeningTestLookup", method = RequestMethod.GET)
	public @ResponseBody List<ScreeningTestCategoryLookup> getAllScreeningTestLookup(){
		return screeningTestLookupService.getAllScreeningTestLookup();
	}

	@RequestMapping(value = "/getAllScreeningTestLookupByCategoryStatus/{id}", method = RequestMethod.GET)
	public @ResponseBody List<ScreeningTestCategoryLookupDto> getAllScreeningTestLookupByCategoryStatus(@PathVariable Long id){
		return screeningTestLookupService.getAllScreeningTestLookupByCategoryStatus(id);
	}
	
	@RequestMapping(value = "/updateScreeningTestLookup", method = RequestMethod.PUT)
	public @ResponseBody void updateScreeningTestLookup(@RequestBody ScreeningTestCategoryLookup screeningTestCategoryLookup){
		screeningTestLookupService.updateScreeningTestLookup(screeningTestCategoryLookup);
	}

	@RequestMapping(value = "/deleteScreeningTestLookup/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteScreeningTestLookup(@PathVariable Long id){
		screeningTestLookupService.deleteScreeningTestLookup(id);
	}
}
