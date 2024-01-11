
package com.openspace.HospitalMgnt.Site;

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
import com.openspace.Model.Lookups.Site;

@RestController
public class SiteController {

	@Autowired
	private SiteService siteService;

	//@RequestMapping(value = RestURIConstants.SAVE_SITE, method = RequestMethod.POST)
	public @ResponseBody void saveSite(@RequestBody Site site) {
		siteService.saveSite(site);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_SITES, method = RequestMethod.GET)
	public @ResponseBody List<Site> getAllSites() {
		return siteService.getAllSites();
	}

	@RequestMapping(value = RestURIConstants.UPDATE_SITE, method = RequestMethod.PUT)
	public @ResponseBody void updateSite(@RequestBody Site siteDto) {
		siteService.updateSite(siteDto);
	}

	@RequestMapping(value = RestURIConstants.DELETE_SITE, method = RequestMethod.DELETE)
	public @ResponseBody void deleteSite(@PathVariable Long id) {
		siteService.deleteSite(id);
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_SITES_BY_COMPANYID, method = RequestMethod.GET)
	public @ResponseBody List<Site> getAllSitesByCompanyId(@PathVariable Long companyId) {
		return siteService.getAllSitesByCompanyId(companyId);
	}
	@RequestMapping(value = RestURIConstants.SAVE_SITE, method = RequestMethod.POST)
		public @ResponseBody void saveSite(@RequestBody SiteDto siteDto) {
			siteService.saveSite(siteDto);
		}

	@RequestMapping(value = RestURIConstants.GET_ALL_SITES_BY_COMPANYUSERNAME, method = RequestMethod.GET)
	public @ResponseBody List<SiteDto> getAllSitesByCompanyUserName(@PathVariable String name) {
		return siteService.getAllSitesByCompanyUserName(name);
	}

	
	@RequestMapping(value = RestURIConstants.GET_ALL_SITES_BY_COMPANYUSERNAME_AND_STATUS, method = RequestMethod.GET)
	public @ResponseBody List<Site> getAllSitesByCompanyUserNameAndStatus(@PathVariable String name) {
		return siteService.getAllSitesByCompanyIdAndStatus(name);
	}
	 
		@RequestMapping(value = RestURIConstants.GET_ALL_SITES_BY_COMPANYUSERNAME_PAGE, method = RequestMethod.GET)
	public @ResponseBody Page<Site> getAllSitesByCompanyUserName(@PathVariable String name,@RequestParam("page")int page,@RequestParam("size")int size) {
		return siteService.getAllSitesByCompanyUserName(name,page,size);
	}
		@RequestMapping(value = RestURIConstants.GET_ALL_SITES_PAGE_BY_SUPERADMIN_COMPANY, method = RequestMethod.GET)
		public @ResponseBody List<SiteDto> getAllSitesByCompanyIdAndStatus(@PathVariable String adminUsername,@PathVariable String roleName,@PathVariable Boolean active,@RequestParam("page")int page,@RequestParam("size")int size) {
			return siteService.getAllSitesByCompanyIdAndStatus(adminUsername,active);
		}
		@RequestMapping(value = RestURIConstants.GET_ALL_ACTIVE_SITES_BY_COMPANY_USERNAME, method = RequestMethod.GET)
		public @ResponseBody int getAllActiveSitesByCompanyUserName(@PathVariable String adminUsername) {
			return siteService.getAllActiveSitesByCompanyUserName(adminUsername);
		}
		@RequestMapping(value = RestURIConstants.GET_ALL_INACTIVE_SITES_BY_COMPANY_USERNAME, method = RequestMethod.GET)
		public @ResponseBody int getAllInActiveSitesByCompanyUserName(@PathVariable String adminUsername) {
			return siteService.getAllInActiveSitesByCompanyUserName(adminUsername);
		}
		
		
}
