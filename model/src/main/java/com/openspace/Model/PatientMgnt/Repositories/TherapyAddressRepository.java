package com.openspace.Model.PatientMgnt.Repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.openspace.Model.DoctorManagement.TherapyAddress;

@Repository
public interface TherapyAddressRepository extends PagingAndSortingRepository<TherapyAddress, Long> {

}
