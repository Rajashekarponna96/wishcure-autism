package com.openspace.HospitalMgnt.Site;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.Lookups.Site;

public interface SiteService {

	void saveSite(Site site);

	List<Site> getAllSites();

	//void updateSite(Site site);

	void deleteSite(Long id);

	List<Site> getAllSitesByCompanyId(Long companyId);
	
	
	//List<Site> getAllSitesByCompanyName(String  name);
	
	//List<Site> getAllSitesByUserName(String name);

	void saveSite(SiteDto siteDto);

	List<SiteDto> getAllSitesByCompanyUserName(String name);
	List<SiteDto> getAllSitesByCompanyIdAndStatus(String name, boolean status);

	Page<Site> getAllSitesByCompanyUserName(String name,int page,int size);
	
	//void updateSite(SiteDto siteDto);
	void updateSite(Site site);

	List<Site> getAllSitesByCompanyIdAndStatus(String name);

	int getAllActiveSitesByCompanyUserName(String name);

	int getAllInActiveSitesByCompanyUserName(String name);




}

