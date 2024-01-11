package com.openspace.HospitalMgnt.ClientTypes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openspace.Model.Lookups.ClientType;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class ClientTypeServiceImpl implements ClientTypeService {
	@Autowired
	private ClientTypeRepository clientTypeRepository;

	@Override
	public void saveClientType(ClientType clientType) {
		ClientType dbclientType = clientTypeRepository.findByClientTypeName(clientType.getClientTypeName());
		if (dbclientType != null) {
			throw new RuntimeException(ErrorMessageHandler.clientTypeAlreadyExists);
		}
		clientType.setStatus(true);
		clientTypeRepository.save(clientType);
	}

	@Override
	public Page<ClientType> getAllClientTypes(int page,int size) {
		return (Page<ClientType>) clientTypeRepository.findAll(new PageRequest(page, size,Sort.Direction.DESC,"id"));
	}

	@Override
	public List<ClientType> getAllClientTypes() {
		return (List<ClientType>) clientTypeRepository.findByStatus(true);
	}

	@Override
	public void updateClientType(ClientType clientType) {
		ClientType dbClientType = clientTypeRepository.findOne(clientType.getId());
		if (dbClientType == null) {
			throw new RuntimeException(ErrorMessageHandler.clientTypeDoesNotExists);
		}

		ClientType dbClientType2 = clientTypeRepository.findByClientTypeName(clientType.getClientTypeName());

		if (dbClientType2 == null) {
			dbClientType.setClientTypeName(clientType.getClientTypeName());
			dbClientType.setClientTypeDescription(clientType.getClientTypeDescription());
			dbClientType.setStatus(clientType.getStatus());

		} else if (dbClientType2.getId().equals(clientType.getId())) {
			dbClientType.setClientTypeName(clientType.getClientTypeName());
			dbClientType.setClientTypeDescription(clientType.getClientTypeDescription());
			dbClientType.setStatus(clientType.getStatus());
		} else {
			throw new RuntimeException(ErrorMessageHandler.clientTypeAlreadyExists);
		}
		dbClientType.setClientTypeName(clientType.getClientTypeName());
		dbClientType.setClientTypeDescription(clientType.getClientTypeDescription());
		dbClientType.setStatus(clientType.getStatus());

		clientTypeRepository.save(dbClientType);
	}

	@Override
	public void deleteClientType(Long id) {
		ClientType dbClientType = clientTypeRepository.findOne(id);
		if (dbClientType == null) {
			throw new RuntimeException(ErrorMessageHandler.clientTypeDoesNotExists);
		}
		try{
			clientTypeRepository.delete(dbClientType);
		}catch (Exception e) {
			throw new RuntimeException("Can't Delete, It's Already In Use....!!");
		}
		
	}

}
