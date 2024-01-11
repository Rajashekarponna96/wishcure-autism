package com.openspace.HospitalMgnt.ClientTypes;

import java.util.List;

import org.springframework.data.domain.Page;

import com.openspace.Model.Lookups.ClientType;

public interface ClientTypeService {
	void saveClientType(ClientType clientType);

	Page<ClientType> getAllClientTypes(int page,int size);
	
	List<ClientType> getAllClientTypes();

	void updateClientType(ClientType clientType);

	void deleteClientType(Long id);
}
