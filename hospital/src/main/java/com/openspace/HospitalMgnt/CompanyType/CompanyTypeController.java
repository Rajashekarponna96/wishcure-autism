package com.openspace.HospitalMgnt.CompanyType;

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

import com.openspace.HospitalMgnt.common.RestURIConstants;
import com.openspace.Model.Lookups.CompanyType;

@RestController
public class CompanyTypeController {

	@Autowired
	private CompanyTypeService companyTypeService;
	
	@RequestMapping(value=RestURIConstants.SAVE_COMPANY_TYPE,method=RequestMethod.POST)
	public @ResponseBody void saveCompanyType(@RequestBody CompanyType companyType){
		companyTypeService.saveCompanyType(companyType);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_COMPANY_TYPES, method = RequestMethod.GET)
	public @ResponseBody Page<CompanyType> getAllCompanyTypes(@RequestParam("page")int page,@RequestParam("size")int size){
		return companyTypeService.getAllCompanyTypes(page,size);
		
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_COMPANY_TYPES_LIST, method = RequestMethod.GET)
	public @ResponseBody List<CompanyType> getAllCompanyTypes(){
		return companyTypeService.getAllCompanyTypes();
		
	}
	
	@RequestMapping(value = RestURIConstants.UPDATE_COMPANY_TYPE, method = RequestMethod.PUT)
	public @ResponseBody void updateCompanyType(@RequestBody CompanyType companyType){
		companyTypeService.updateCompanyType(companyType);
	}
	@RequestMapping(value = RestURIConstants.DELETE_COMPANY_TYPE, method = RequestMethod.DELETE)
	public @ResponseBody void deletgeCompanyType(@PathVariable Long id){
		companyTypeService.deleteCompanyType(id);
	}
}
