package com.openspace.PatientManagement.Mchart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.ParentModule.Mchart.MchartLookup;

@RestController
public class MchartLookupController {
	@Autowired
	private MchartLookupService mchartLookupService;
	
	@RequestMapping(value = "/saveMchartLookup", method = RequestMethod.POST)
	public @ResponseBody void saveMchartLookup(@RequestBody MchartLookup mchartLookup){
		mchartLookupService.saveMchartLookup(mchartLookup);
	}
	
	@RequestMapping(value = "/getAllMchartLookup", method = RequestMethod.GET)
	public @ResponseBody List<MchartLookup> getAllMchartLookup(){
		return mchartLookupService.getAllMchartLookup();
	}

	
	@RequestMapping(value = "/updateMchartLookup", method = RequestMethod.PUT)
	public @ResponseBody void updatemchartLookup(@RequestBody MchartLookup mchartLookup){
		mchartLookupService.updateMchartLookup(mchartLookup);
	}

	@RequestMapping(value = "/deleteMchartLookup/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteMchartLookup(@PathVariable Long id){
		mchartLookupService.deleteMchartLookup(id);
	}
}
