package com.openspace.HospitalMgnt.ClientTypes;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.openspace.Model.Lookups.ClientType;

public interface ClientTypeRepository extends PagingAndSortingRepository<ClientType, Long>{
	ClientType findByClientTypeName(String name);
	List<ClientType> findById(Long id);
	List<ClientType> findByStatus(boolean b);
}
