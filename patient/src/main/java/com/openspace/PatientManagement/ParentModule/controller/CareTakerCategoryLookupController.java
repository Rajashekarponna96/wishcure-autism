package com.openspace.PatientManagement.ParentModule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.ParentModule.caretaker.CareTakerCategoryLookup;
import com.openspace.PatientManagement.ParentModule.service.CareTakerCategoryLookupService;
@RestController
public class CareTakerCategoryLookupController {
	@Autowired
	private CareTakerCategoryLookupService careTakerCategoryLookupService;

	@RequestMapping(value = "/saveCareTakerCategoryLookup", method = RequestMethod.POST)
	public @ResponseBody void saveCareTakerCategoryLookup(@RequestBody CareTakerCategoryLookup careTakerCategoryLookup) {
		System.out.println(careTakerCategoryLookup.getName());
		careTakerCategoryLookupService.saveCareTakerCategoryLookup(careTakerCategoryLookup);
	}

	@RequestMapping(value = "/getCareTakerCategoryLookups", method = RequestMethod.GET)
	public @ResponseBody List<CareTakerCategoryLookup> getCareTakerCategoryLookups() {
		return careTakerCategoryLookupService.getCareTakerCategoryLookups();
	}

	@RequestMapping(value = "/updateCareTakerCategoryLookup", method = RequestMethod.PUT)
	public @ResponseBody void updateCareTakerCategoryLookup(@RequestBody CareTakerCategoryLookup careTakerCategoryLookup) {
		careTakerCategoryLookupService.updateCareTakerCategoryLookup(careTakerCategoryLookup);
	}

	@RequestMapping(value = "/deleteCareTakerCategoryLookup/{id}")
	public@ResponseBody  void deleteCareTakerCategoryLookup(@PathVariable Long id) {
		careTakerCategoryLookupService.deleteCareTakerCategoryLookup(id);
	}

}
