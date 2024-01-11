package com.openspace.Model.MentalReferenceProfile.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openspace.Model.MentalReferenceProfile.PatientMentalScale;
import com.openspace.Model.Scales.MentalClusterCount;

@Repository
public interface PatientMentalScaleRepository extends PagingAndSortingRepository<PatientMentalScale, Long> {

	public static String GET_MENTAL_CLUSTER_COUNT = "select new com.openspace.Model.Scales.MentalClusterCount(mentalClusterId , count(mentalClusterId)) from PatientMentalScale where mentalResult.id =:resultId and percent97Rank <=:orgRF and mentalScaleStatus='P'  group by mentalClusterId";

	@Query(value = GET_MENTAL_CLUSTER_COUNT)
	public List<MentalClusterCount> getCount(@Param("resultId") Long id,@Param("orgRF") Integer orgRf);

	public List<PatientMentalScale> findByMentalResult_Id(Long id);
	
}
