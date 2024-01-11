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
import com.openspace.Model.ParentModule.caretaker.CareTakerQuestionLookup;
import com.openspace.PatientManagement.ParentModule.service.CareTakerQuestionLookupService;
@RestController
public class CareTakerQuestionLookupController {
	@Autowired
	private CareTakerQuestionLookupService careTakerQuestionLookupService;

	@RequestMapping(value = "/saveCareTakerQuestionLookup", method = RequestMethod.POST)
	public @ResponseBody void saveCareTakerQuestionLookup(@RequestBody CareTakerQuestionLookup careTakerQuestionLookup) {
		careTakerQuestionLookupService.saveCareTakerQuestionLookup(careTakerQuestionLookup);
	}

	@RequestMapping(value = "/getCareTakerQuestionLookups", method = RequestMethod.GET)
	public @ResponseBody List<CareTakerQuestionLookup> getCareTakerQuestionLookup() {
		return careTakerQuestionLookupService.getCareTakerQuestionLookups();
	}

	@RequestMapping(value = "/updateCareTakerQuestionLookup", method = RequestMethod.PUT)
	public @ResponseBody void updateCareTakerQuestionLookups(@RequestBody CareTakerQuestionLookup careTakerQuestionLookup) {
		careTakerQuestionLookupService.updateCareTakerQuestionLookup(careTakerQuestionLookup);
	}

	@RequestMapping(value = "/deleteCareTakerQuestionLookup/{id}")
	public@ResponseBody  void deleteCareTakerQuestionLookups(@PathVariable Long id) {
		careTakerQuestionLookupService.deleteCareTakerQuestionLookup(id);
	}
	



}
