package com.openspace.HospitalMgnt.EvalutionCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.Template.EvalutionCategory;

@RestController
public class EvalutionCategoryController {

	@Autowired
	private EvalutionCategoryService evalutionCategoryService;
	
	@RequestMapping(value=RestURIConstants.SAVE_EVALUTION_CATEGORY, method=RequestMethod.POST)
	public @ResponseBody void saveEvalutionCategory(@RequestBody EvalutionCategory evalutionCategory ){
		evalutionCategoryService.saveEvalutionCategory(evalutionCategory);
	}
	@RequestMapping(value=RestURIConstants.GET_ALL_EVALUTION_CATEGORYS, method=RequestMethod.GET)
	public @ResponseBody List<EvalutionCategory> getAllCategorys(){
		return evalutionCategoryService.getAllCategorys();
	}
	@RequestMapping(value=RestURIConstants.UPDATE_EVALUTION_CATEGORY, method=RequestMethod.PUT)
	public @ResponseBody void updateEvalutionCategory(@RequestBody EvalutionCategory evalutionCategory){
		evalutionCategoryService.updateEvalutionCategory(evalutionCategory);
	}
	@RequestMapping(value=RestURIConstants.DELETE_EVALUTION_CATEGORY,method=RequestMethod.DELETE)
	public @ResponseBody void deleteEvalutionCategory(@PathVariable Long id){
		evalutionCategoryService.deleteEvalutionCategory(id);
	}
	
}
