package com.openspace.Model.MentalReferenceProfile.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openspace.Model.MentalReferenceProfile.MentalReferenceProfile;

@Repository
public interface MentalReferenceProfileRepository extends PagingAndSortingRepository<MentalReferenceProfile, Long> {

	public static String GET_MENTALREFERENCE_BETWEEN_AGERANGE = "SELECT mrp FROM  MentalReferenceProfile mrp WHERE :childAge BETWEEN  mrp.minAge and mrp.maxAge";

	@Query(GET_MENTALREFERENCE_BETWEEN_AGERANGE)
	public MentalReferenceProfile getMentalReferenceProfileByAgeLimit(@Param("childAge") float age);

}
