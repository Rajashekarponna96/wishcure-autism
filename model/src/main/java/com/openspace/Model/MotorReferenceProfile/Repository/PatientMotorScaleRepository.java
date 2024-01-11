package com.openspace.Model.MotorReferenceProfile.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openspace.Model.MotorReferenceProfile.PatientMotorScale;
import com.openspace.Model.Scales.MotorClusterCount;

@Repository
public interface PatientMotorScaleRepository extends PagingAndSortingRepository<PatientMotorScale, Long> {

	public static String GET_MOTOR_CLUSTER_COUNT = "select new com.openspace.Model.Scales.MotorClusterCount(motorClusterId , count(motorClusterId)) from PatientMotorScale where motorResult.id =:resultId and percent97Rank <=:orgRF and motorScaleStatus='P'  group by motorClusterId";

	@Query(value = GET_MOTOR_CLUSTER_COUNT)
	public List<MotorClusterCount> getCount(@Param("resultId") Long id,@Param("orgRF") Integer orgRf);

	public List<PatientMotorScale> findByMotorResult_Id(Long id);
	
}
