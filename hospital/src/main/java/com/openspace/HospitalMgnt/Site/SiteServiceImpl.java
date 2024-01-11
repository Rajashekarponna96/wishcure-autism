package com.openspace.HospitalMgnt.Site;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Person;
import com.openspace.Model.Lookups.Address1;
import com.openspace.Model.Lookups.Site;
import com.openspace.Model.PatientMgnt.Repositories.CityRepository;
import com.openspace.Model.PatientMgnt.Repositories.CountryRepository;
import com.openspace.Model.PatientMgnt.Repositories.RoleRepository;
import com.openspace.Model.PatientMgnt.Repositories.SiteRepository;
import com.openspace.Model.PatientMgnt.Repositories.StateRepository;
import com.openspace.Model.PatientMgnt.Repositories.UserAccountRepository;
import com.openspace.Model.UserManagement.Role;
import com.openspace.Model.UserManagement.UserAccount;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void saveSite(Site site) {

		Site dbSite = siteRepository.findBySiteName(site.getSiteName());
		if (dbSite != null) {
			throw new RuntimeException(ErrorMessageHandler.siteNameAlreadyExists);
		}
		site.setStatus(true);
		site.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		site.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
		siteRepository.save(site);
	}

	@Override
	public List<Site> getAllSites() {
		return (List<Site>) siteRepository.findAll();
	}

	@Override
	public void deleteSite(Long id) {
		Site dbSite = siteRepository.findOne(id);
		if (dbSite == null) {
			throw new RuntimeException(ErrorMessageHandler.siteDoesNotExists);
		}
		siteRepository.delete(dbSite);
	}

	@Override
	public List<Site> getAllSitesByCompanyId(Long companyId) {
		return siteRepository.findByCompany_Id(companyId);
	}

	@Override
	public void saveSite(SiteDto siteDto) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(siteDto.getCompanyAdminUsername());
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		Address1 address = new Address1();
		address.setAddress1(siteDto.getAddress1());
		address.setAddress2(siteDto.getAddress2());
		address.setCity(siteDto.getCity());
		address.setState(siteDto.getState());
		address.setCountry(siteDto.getCountry());
		address.setZipcode(siteDto.getZipcode());

		Site site = new Site();
		Site dbSite = siteRepository.findBySiteNameAndCompany_Id(siteDto.getSiteName(),dbUserAccount.getCompany().getId());
		if (dbSite != null) {
			throw new RuntimeException(ErrorMessageHandler.siteAlreadyExists);
		}
		site.setSiteName(siteDto.getSiteName());
		site.setAddress(address);
		site.setStatus(true);
		site.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		site.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
		if (dbUserAccount.getCompany() != null) {
			site.setCompany(dbUserAccount.getCompany());
		} else {
			throw new RuntimeException(ErrorMessageHandler.siteCanootBeCreatedBecauseCompanyDoesNotExists);
		}
		siteRepository.save(site);
	}

	@Override
	public List<SiteDto> getAllSitesByCompanyUserName(String name) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(name);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<Site> sitesList = new ArrayList<Site>();
		List<SiteDto> siteDtosList = new ArrayList<SiteDto>();
		if (dbUserAccount.getCompany() != null) {
			sitesList = siteRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
			for (Site site : sitesList) {
				SiteDto siteDto = new SiteDto();
				siteDto.setId(site.getId());
				siteDto.setAddress1(site.getAddress().getAddress1());
				siteDto.setAddress2(site.getAddress().getAddress2());
				siteDto.setCity(site.getAddress().getCity());
				siteDto.setCompany(site.getCompany());
				siteDto.setCountry(site.getAddress().getCountry());
				siteDto.setSiteName(site.getSiteName());
				siteDto.setState(site.getAddress().getState());
				siteDto.setStatus(site.getStatus());
				siteDto.setZipcode(site.getAddress().getZipcode());
				siteDto.setCompanyAdminUsername(dbUserAccount.getUsername());
				siteDtosList.add(siteDto);
			}
		} else {
			throw new RuntimeException(ErrorMessageHandler.companyDoesNotExists);
		}
		return siteDtosList;
	}

	
	@Override//change from Sitedto to site //By Naveen
	public void updateSite(Site site) {
		Site dbSite = siteRepository.findOne(site.getId());
		if (dbSite == null) {
			throw new RuntimeException(ErrorMessageHandler.siteDoesNotExists);
		}
		Site dbSite2 = siteRepository.findBySiteName(site.getSiteName());
		/*if (dbSite2 == null) {
		
		} else if (dbSite2.getId().equals(site.getId())) {
			
		} else {
			throw new RuntimeException("Site Already Exist!");
		}*/
		if(dbSite2 != null&&!dbSite2.getId().equals(site.getId())){
			throw new RuntimeException(ErrorMessageHandler.siteAlreadyExists);
		}
		Address1 address = site.getAddress();
		/*address.setAddress1(site.getAddress().getAddress1());
		address.setAddress2(site.getAddress().getAddress2());
		address.setCity(site.getAddress().getCity());
		address.setState(site.getAddress().getState());
		address.setCountry(site.getAddress().getCountry());
		address.setZipcode(site.getAddress().getZipcode());*/
		
		dbSite.setAddress(address);
		dbSite.setSiteName(site.getSiteName());
		dbSite.setStatus(site.getStatus());
		//dbSite=site;
		siteRepository.save(dbSite);
		
		
		
	}

	@Override
	public Page<Site> getAllSitesByCompanyUserName(String name, int page, int size) {

		UserAccount dbUserAccount=userAccountRepository.findByUsername(name);
		if(dbUserAccount==null){
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		
		
		return siteRepository.findByCompany_Id(dbUserAccount.getCompany().getId(), new PageRequest(page, size,Sort.Direction.DESC,"id"));
	}

	@Override
	public List<SiteDto> getAllSitesByCompanyIdAndStatus(String name, boolean status) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(name);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<Site> sitesList = new ArrayList<Site>();
		List<SiteDto> siteDtosList = new ArrayList<SiteDto>();
		if (dbUserAccount.getCompany() != null) {
			sitesList = siteRepository.findByCompany_IdAndStatus(dbUserAccount.getCompany().getId(),status);
			for (Site site : sitesList) {
				SiteDto siteDto = new SiteDto();
				siteDto.setId(site.getId());
				siteDto.setAddress1(site.getAddress().getAddress1());
				siteDto.setAddress2(site.getAddress().getAddress2());
				siteDto.setCity(site.getAddress().getCity());
				siteDto.setCompany(site.getCompany());
				siteDto.setCountry(site.getAddress().getCountry());
				siteDto.setSiteName(site.getSiteName());
				siteDto.setState(site.getAddress().getState());
				siteDto.setStatus(site.getStatus());
				siteDto.setZipcode(site.getAddress().getZipcode());
				siteDto.setCompanyAdminUsername(dbUserAccount.getUsername());
				siteDtosList.add(siteDto);
			}
		} else {
			throw new RuntimeException(ErrorMessageHandler.companyDoesNotExists);
		}
		return siteDtosList;
	}

	@Override
	public List<Site> getAllSitesByCompanyIdAndStatus(String name) {
		UserAccount dbUserAccount = userAccountRepository.findByUsername(name);
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}	
		List<Site> siteList = siteRepository.findByCompany_Id(dbUserAccount.getCompany().getId());
		siteList = siteList.stream().filter(site ->site.getCompany().getUserAccounts().isEmpty()).collect(Collectors.toList());
		return siteList;
	}

	@Override
	public int getAllActiveSitesByCompanyUserName(String name) {
		
			int i = 0;
			UserAccount dbUserAccount = userAccountRepository.findByUsername(name);
			
			if (dbUserAccount == null) {
				throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
			}
			List<Site> sites = siteRepository.findByCompany_IdAndStatus(dbUserAccount.getCompany().getId(),true);
			

			
			i = sites.size();
			return i;
		}
	

	@Override
	public int getAllInActiveSitesByCompanyUserName(String name) {
		int i = 0;
		UserAccount dbUserAccount = userAccountRepository.findByUsername(name);
		
		if (dbUserAccount == null) {
			throw new RuntimeException(ErrorMessageHandler.userDoesNotExists);
		}
		List<Site> sites = siteRepository.findByCompany_IdAndStatus(dbUserAccount.getCompany().getId(),false);
		

		
		i = sites.size();
		return i;
	}

}
