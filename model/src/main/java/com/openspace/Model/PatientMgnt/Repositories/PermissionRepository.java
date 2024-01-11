package com.openspace.Model.PatientMgnt.Repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.UserManagement.Permission;

@Repository
public interface PermissionRepository extends PagingAndSortingRepository<Permission, Long> {


	Permission findByCategory_CategoryName(String name);

	List<Permission> findByRole_Id(Long id);
}
