package com.openspace.HospitalMgnt.ClientTypes;

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
import com.openspace.Model.Lookups.ClientType;

@RestController
public class ClientTypesController {
	
	@Autowired
	private ClientTypeService clientTypeService;

	@RequestMapping(value = RestURIConstants.SAVE_CLIENT_TYPE, method = RequestMethod.POST)
	public @ResponseBody void saveClientType(@RequestBody ClientType clientType) {
		clientTypeService.saveClientType(clientType);;
	}

	@RequestMapping(value = RestURIConstants.GET_ALL_CLIENT_TYPES, method = RequestMethod.GET)
	public @ResponseBody Page<ClientType> getAllClientTypes(@RequestParam("page")int page,@RequestParam("size")int size) {
		return clientTypeService.getAllClientTypes(page, size);
	}
	
	@RequestMapping(value = RestURIConstants.GET_ALL_CLIENT_TYPES_LIST, method = RequestMethod.GET)
	public @ResponseBody List<ClientType> getAllClientTypes() {
		return clientTypeService.getAllClientTypes();
	}

	@RequestMapping(value = RestURIConstants.UPDATE_CLIENT_TYPE, method = RequestMethod.PUT)
	public @ResponseBody void updateClientType(@RequestBody ClientType clientType) {
		clientTypeService.updateClientType(clientType);
	}

	@RequestMapping(value = RestURIConstants.DELETE_CLIENT_TYPE, method = RequestMethod.DELETE)
	public @ResponseBody void deleteClientType(@PathVariable Long id) {
		clientTypeService.deleteClientType(id);;
	}

	

}
