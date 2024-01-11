package com.openspace.HospitalMgnt.Country;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.Country;
import com.openspace.Model.PatientMgnt.Repositories.CountryRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<Country> getAllCountries() {
		return (List<Country>) countryRepository.findAll();
	}

	@Override
	public void updateCountry(Country country) {
		// TODO Auto-generated method stub
		Country dbCountry = countryRepository.findOne(country.getId());
		if (dbCountry == null) {
			throw new RuntimeException(ErrorMessageHandler.companyDoesNotExists);
		}
		dbCountry.setName(country.getName());
		countryRepository.save(dbCountry);
	}

	@Override
	public Country getCountry(Long id) {
		return countryRepository.findOne(id);
	}

	@Override
	public List<Country> getCountryByIsdCode() {
		return countryRepository.findByIsdcode("+1");
	}

}
