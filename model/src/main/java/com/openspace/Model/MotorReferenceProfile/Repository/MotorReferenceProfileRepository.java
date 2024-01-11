package com.openspace.Model.MotorReferenceProfile.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openspace.Model.MotorReferenceProfile.MotorReferenceProfile;

@Repository
public interface MotorReferenceProfileRepository extends PagingAndSortingRepository<MotorReferenceProfile, Long> {
	public static String GET_MOTORREFERENCE_BETWEEN_AGERANGE = "SELECT mrp FROM  MotorReferenceProfile mrp WHERE :childAge BETWEEN  mrp.minAge and mrp.maxAge";

	@Query(GET_MOTORREFERENCE_BETWEEN_AGERANGE)
	public MotorReferenceProfile getMotorReferenceProfileByAgeLimit(@Param("childAge") float age);

}
