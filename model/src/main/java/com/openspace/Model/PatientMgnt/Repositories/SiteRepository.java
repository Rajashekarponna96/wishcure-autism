package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.Lookups.Site;

@Repository
public interface SiteRepository extends PagingAndSortingRepository<Site, Long> {

	Site findBySiteName(String name);
	List<Site> findByCompany_Id(Long id);
	List<Site> findByCompany_IdAndStatus(Long id,boolean status);
	Page<Site> findByCompany_Id(Long id,Pageable pageable);
	Site findBySiteNameAndCompany_Id(String siteName, Long id);
	List<Site> findByCompany_Id(Long id, boolean b);
}

