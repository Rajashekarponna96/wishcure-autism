package com.openspace.PatientManagement.Scales;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.openspace.Model.Clusters.MotorCluster;
import com.openspace.Model.Clusters.Repositories.MotorClusterRepository;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.MotorReferenceProfile.MotorReferenceProfile;
import com.openspace.Model.MotorReferenceProfile.MotorResult;
import com.openspace.Model.MotorReferenceProfile.MotorScaleStatus;
import com.openspace.Model.MotorReferenceProfile.PatientMotorScale;
import com.openspace.Model.MotorReferenceProfile.ReferenceMotorCluster;
import com.openspace.Model.MotorReferenceProfile.Repository.MotorReferenceProfileRepository;
import com.openspace.Model.MotorReferenceProfile.Repository.MotorResultRepository;
import com.openspace.Model.MotorReferenceProfile.Repository.PatientMotorScaleRepository;
import com.openspace.Model.MotorReferenceProfile.Repository.ReferenceMotorClusterRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.Scales.MotorClusterCount;
import com.openspace.Model.Scales.MotorScale;
import com.openspace.Model.Scales.MotorScaleAgeRFDto;
import com.openspace.Model.Scales.MotorScaleTemplateDto;
import com.openspace.Model.ScalesRepositories.MotorClusterCountRepository;
import com.openspace.Model.ScalesRepositories.MotorScaleRepository;

@Service
public class MotorScaleServiceImpl implements MotorScaleService {

	@Autowired
	private MotorScaleRepository motorScaleRepository;

	@Autowired
	private MotorClusterRepository motorClusterRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private MotorResultRepository motorResultRepository;

	@Autowired
	private PatientMotorScaleRepository patientMotorScaleRepository;

	@Autowired
	private MotorClusterCountRepository motorClusterCountRepository;

	@Autowired
	private MotorClusterRepository motorClusterRepositories;

	@Autowired
	private MotorReferenceProfileRepository motorReferenceProfileRepository;

	@Autowired
	private ReferenceMotorClusterRepository referenceMotorClusterRepository;

	@Override
	public void saveMotorScale(MotorScale motorScale) {
		MotorScale dbMotorCluster = motorScaleRepository.findByItemDescripation(motorScale.getItemDescripation());
		if (dbMotorCluster != null) {
			throw new RuntimeException("MotorScale Already Exists with " + motorScale.getItemDescripation());
		}
		motorScaleRepository.save(motorScale);
	}

	@Override
	public List<MotorScale> getAllMotorScales() {
		return (List<MotorScale>) motorScaleRepository.findAll();
	}

	@Override
	public void updateMotorScale(MotorScale motorScale) {
		MotorScale dbMotorScale = motorScaleRepository.findOne(motorScale.getId());
		if (dbMotorScale == null) {
			throw new RuntimeException("MotorCluster Does not Exists!!!");
		}

		MotorScale dbmotorScale1 = motorScaleRepository.findByItemDescripation(motorScale.getItemDescripation());
		if (dbmotorScale1 == null) {
			dbMotorScale.setItemDescripation(motorScale.getItemDescripation());
		} else if (dbmotorScale1.getId().equals(dbMotorScale.getId())) {
			dbMotorScale.setItemDescripation(motorScale.getItemDescripation());
		} else {
			throw new RuntimeException("MotorCluster Already Exists!!");
		}

		dbMotorScale.setItemDescripation(motorScale.getItemDescripation());
		dbMotorScale.setItemNo(motorScale.getItemNo());
		if (motorScale.getMotorCluster() != null) {
			dbMotorScale.setMotorCluster(motorScale.getMotorCluster());
		}
		dbMotorScale.setPercent3(motorScale.getPercent3());
		dbMotorScale.setPercent50(motorScale.getPercent50());
		dbMotorScale.setPercent97(motorScale.getPercent97());
		dbMotorScale.setPercent97Rank(motorScale.getPercent97Rank());
		motorScaleRepository.save(dbMotorScale);

	}

	@Override
	public void deleteMotorScale(Long id) {
		motorScaleRepository.delete(id);
	}

	@Override
	public Page<MotorScale> getAllPaginatedMotorScales(int page, int size) {
		return motorScaleRepository.findAll(new PageRequest(page, size));
	}

	@Override
	public void assignedToMotorCluster(List<MotorScale> motorScalesList, Long motorClusterId) {
		MotorCluster dbMotorCluster = motorClusterRepository.findOne(motorClusterId);
		if (dbMotorCluster == null) {
			throw new RuntimeException("MotorCluster Does not Exists!");
		}
		for (MotorScale motorScale : motorScalesList) {
			motorScale.setMotorCluster(dbMotorCluster);
		}
		dbMotorCluster.setMotorScales(motorScalesList);
		motorClusterRepository.save(dbMotorCluster);
	}

	@Override
	public void assignMotorScaleTemplateToPatient(MotorScaleTemplateDto motorScaleTemplateDto) {
		Patient patient = motorScaleTemplateDto.getPatient();
		List<MotorScale> motorScales = motorScaleTemplateDto.getMotorScale();
		Patient dbPatient = patientRepository.findOne(patient.getId());
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exists!");
		}
		List<MotorResult> motorResults = new ArrayList<MotorResult>();
		if (motorScales != null && motorScales.size() > 0) {
			MotorResult motorResult = new MotorResult();
			motorResult.setPatient(dbPatient);
			motorResultRepository.save(motorResult);

			List<PatientMotorScale> patientMotorScales = convertToPatientMotorScales(motorResult, motorScales, patient);

			if (patientMotorScales != null || patientMotorScales.size() > 0) {
				motorResult.setPatientMotorScales(patientMotorScales);
				motorResultRepository.save(motorResult);
				updatePatientMotorScales(patientMotorScales,dbPatient.getId());
			}

		}
	}

	public List<PatientMotorScale> convertToPatientMotorScales(MotorResult motorResult, List<MotorScale> motorScales,
			Patient patient) {
		List<PatientMotorScale> patientMotorScales = new ArrayList<PatientMotorScale>();
		for (MotorScale motorScale : motorScales) {
			PatientMotorScale patientMotorScale = new PatientMotorScale();
			if (motorScale != null) {
				if (motorScale.getItemDescripation() != null) {
					patientMotorScale.setItemDescripation(motorScale.getItemDescripation());
				}
				if (motorResult != null) {
					patientMotorScale.setMotorResult(motorResult);
				}
				if (motorScale.getMotorScaleStatus() != null) {
					patientMotorScale.setMotorScaleStatus(motorScale.getMotorScaleStatus());
				}
				if (motorScale.getPercent50() != null) {
					patientMotorScale.setPercent50(motorScale.getPercent50());
				}

				if (motorScale.getPercent3() != null) {
					patientMotorScale.setPercent3(motorScale.getPercent3());
				}
				if (motorScale.getPercent97() != null) {
					patientMotorScale.setPercent97(motorScale.getPercent97());
				}
				if (motorScale.getPercent97Rank() != null) {
					patientMotorScale.setPercent97Rank(motorScale.getPercent97Rank());
				}
				if (motorScale.getId() != null) {
					patientMotorScale.setMotorScaleId(motorScale.getId());
				}
				if (motorScale.getMotorCluster() != null && motorScale.getMotorCluster().getId() != null) {
					patientMotorScale.setMotorClusterId(motorScale.getMotorCluster().getId());
				}
				if (motorScale.getNote() != null) {
					patientMotorScale.setNote(motorScale.getNote());
				}
				
				patientMotorScaleRepository.save(patientMotorScale);

			}
			patientMotorScales.add(patientMotorScale);
		}
		return patientMotorScales;
	}

	@Override
	public List<PatientMotorScale> getAllPatientMotorScalesByPatientId(Long patientId) {

		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		MotorResult dbMotorResult = motorResultRepository.findByPatient_Id(patientId);
		if (dbMotorResult == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		List<PatientMotorScale> patientMotorScale = patientMotorScaleRepository
				.findByMotorResult_Id(dbMotorResult.getId());
		return patientMotorScale;
	}

	public List<MotorClusterCount> getMotorClusterCount(Long resultId, Long patientId, Integer orgRf) {
		MotorResult dbMotorResult = motorResultRepository.findOne(resultId);
		if (dbMotorResult == null) {
			throw new RuntimeException("MotorResult Does not Exist");
		}
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist with ");
		}
		// age calculation
		String dob = dbPatient.getDateOfBirth();
		String actDate = dob.substring(0, 10);
		String[] list = actDate.split("-");
		LocalDate pdate = LocalDate.of(Integer.parseInt(list[0]), Integer.parseInt(list[1]), Integer.parseInt(list[2]));
		LocalDate now = LocalDate.now();
		Period diff = Period.between(pdate, now);
		int months = diff.getYears() * 12;
		int totalmonths = months + diff.getMonths();
		float ageOfChild = totalmonths + convertDaysToMonth(diff.getDays());
		System.out.println("age of child " + ageOfChild);
		System.out.println("original rf id is:::: " + orgRf);
		List<MotorClusterCount> clustercount = new ArrayList<MotorClusterCount>();
		List<MotorClusterCount> listOfClustersByResult = motorClusterCountRepository.findByMotorResult_Id(resultId);
		if (listOfClustersByResult != null && listOfClustersByResult.size() > 1) {
			clustercount = listOfClustersByResult;
		} else {
			clustercount = patientMotorScaleRepository.getCount(resultId, orgRf);
		}
		List<MotorCluster> motorClusters = (List<MotorCluster>) motorClusterRepositories.findAll();
		List<MotorCluster> selectedMotorClusters = new ArrayList<MotorCluster>();
		if (clustercount.size() <= 10) {
			for (MotorCluster motorClusters1 : motorClusters) {
				for (MotorClusterCount motorClusterCount : clustercount) {
					if (motorClusters1.getId() == motorClusterCount.getClusterId()) {
						selectedMotorClusters.add(motorClusters1);
					}

				}
			}
		}
		motorClusters.removeAll(selectedMotorClusters);
		for (MotorCluster motorClustersToSave : motorClusters) {
			MotorClusterCount motorClusterCountNewInsert = new MotorClusterCount();
			motorClusterCountNewInsert.setClusterId(motorClustersToSave.getId());
			motorClusterCountNewInsert.setClusterCount(0L);
			clustercount.add(motorClusterCountNewInsert);
			// motorClusterCountRepository.save(motorClusterCountNewInsert);
		}

		MotorReferenceProfile motorReferenceProfile = motorReferenceProfileRepository
				.getMotorReferenceProfileByAgeLimit(ageOfChild);
		System.out.println("motorReferenceProfile1 " + motorReferenceProfile.getMaxAge() + " "
				+ motorReferenceProfile.getMinAge());
		System.out.println("motorReferenceProfile2 " + motorReferenceProfile.getReferenceMotorCluster().size());
		List<MotorClusterCount> updatedclustercount = new ArrayList<MotorClusterCount>();
		if (motorReferenceProfile != null) {
			if (clustercount != null && clustercount.size() > 0) {
				long n = 0;
				long count = 0;
				for (MotorClusterCount motorClusterCount : clustercount) {
					// select ReferenceMotorClusterList only based on clusterId
					// and reference profileId //5 records only
					List<ReferenceMotorCluster> selectedList = referenceMotorClusterRepository
							.findByMotorReferenceProfile_IdAndMotorClusterId(motorReferenceProfile.getId(),
									motorClusterCount.getClusterId());
					List<ReferenceMotorCluster> duplicatelist = new ArrayList<ReferenceMotorCluster>();
					for (ReferenceMotorCluster referenceMotorCluster : selectedList) {
						if (motorClusterCount.getClusterId().equals(referenceMotorCluster.getMotorClusterId())) {
							if (motorClusterCount.getClusterCount().equals(referenceMotorCluster.getResultValue())) {
								duplicatelist.add(referenceMotorCluster);
							}
							if (referenceMotorCluster.getResultValue() > motorClusterCount.getClusterCount()) {
								motorClusterCount.setPercentile("< 10");
								motorClusterCount.setRemarks("DELAYED");
								motorClusterCount.setGraphCount(10L);
							} else if (referenceMotorCluster.getResultValue() < motorClusterCount.getClusterCount()) {
								motorClusterCount.setPercentile("90");
								motorClusterCount.setRemarks("NORMAL");
								motorClusterCount
										.setGraphCount(Long.parseLong(referenceMotorCluster.getResultPercentile()));
							} else {
								if (referenceMotorCluster.getResultValue()
										.equals(Math.ceil(motorClusterCount.getClusterCount()))) {
									motorClusterCount.setPercentile(referenceMotorCluster.getResultPercentile());
									motorClusterCount.setRemarks("NORMAL");
									motorClusterCount
											.setGraphCount(Long.parseLong(referenceMotorCluster.getResultPercentile()));
								}
							}
						}

					}
					if (duplicatelist != null && duplicatelist.size() > 0) {
						ReferenceMotorCluster lateDuplicateCluster = duplicatelist.get(duplicatelist.size() - 1);
						motorClusterCount.setPercentile(lateDuplicateCluster.getResultPercentile());
						System.out.println("************************ duplicate % " + motorClusterCount.getPercentile());
						motorClusterCount.setRemarks("NORMAL");
						motorClusterCount.setGraphCount(Long.parseLong(lateDuplicateCluster.getResultPercentile()));
					}
					updatedclustercount.add(motorClusterCount);
				}
			}
		}

		System.out.println(motorReferenceProfile.getMaxAge() + "  " + motorReferenceProfile.getMinAge());
		System.out.println("size" + motorReferenceProfile.getReferenceMotorCluster().size());
		return updatedclustercount;
	}

	public float convertDaysToMonth(int days) {
		float month = 0;
		if (days == 15) {
			month = (float) 0.5;
		} else if (1 < days && 4 > days) {
			month = (float) 0.1;
		} else if (4 <= days && 9 > days) {
			month = (float) 0.2;
		} else if (9 <= days && 11 > days) {
			month = (float) 0.3;
		} else if (11 <= days && 15 > days) {
			month = (float) 0.4;
		} else if (16 <= days && 20 > days) {
			month = (float) 0.6;
		} else if (20 <= days && 24 > days) {
			month = (float) 0.7;
		} else if (24 <= days && 26 > days) {
			month = (float) 0.8;
		} else if (26 <= days && 30 > days) {
			month = (float) 0.9;
		} else {
			month = (float) 1;
		}
		System.out.println("days in month " + month);
		return month;
	}

	public MotorScaleAgeRFDto calculateMotorAge(List<PatientMotorScale> patientMotorScales) {
		Integer falseCount = 0;
		Integer totalfalseCount = 0;
		Integer falseRFCount = 0;
		Integer falseRFListCount = 0;
		Integer orginalRefenecepoint = 0;
		Integer duplicateRFRefenecepoint = 0;
		int i;
		Integer count = 0;
		Double motorAge = 0.0;
		boolean flag = false;
		boolean rf2flag = false;
		MotorScaleAgeRFDto motorScaleAgeRFDto = new MotorScaleAgeRFDto();

		List<PatientMotorScale> patientRFMotorScales = new ArrayList<PatientMotorScale>();
		List<PatientMotorScale> patientRfMotorScaleList = new ArrayList<PatientMotorScale>();
		if (patientMotorScales != null && patientMotorScales.size() > 0) {
			for (PatientMotorScale patientMotorScale : patientMotorScales) {
				if (patientMotorScale.getMotorScaleStatus() != null) {
					if (patientMotorScale.getMotorScaleStatus().name() == "RF") {
						patientRFMotorScales.add(patientMotorScale);
						falseRFCount++;
						rf2flag = true;
					}
					if (rf2flag == false && patientMotorScale.getMotorScaleStatus().name() == "F") {
						// false count based on with out consider of between two
						// rf
						// points
						falseCount++;
					}
					if (patientMotorScale.getMotorScaleStatus().name() == "F") {
						totalfalseCount++;
					}
				}
			}
			if (falseRFCount >= 2) {
				PatientMotorScale RF1MotorScale = patientRFMotorScales.get(0);
				PatientMotorScale RFnMotorScale = patientRFMotorScales.get(patientRFMotorScales.size() - 1);
				Integer RF1ClusterId = (RF1MotorScale.getPercent97Rank());
				Integer RFnClusterId = (RFnMotorScale.getPercent97Rank());
				if (RF1ClusterId > RFnClusterId) {
					falseRFListCount = RF1ClusterId - RFnClusterId;
				} else if (RF1ClusterId < RFnClusterId) {
					falseRFListCount = RFnClusterId - RF1ClusterId;
				}
				for (i = RF1ClusterId; i <= RFnClusterId; i++) {
					for (PatientMotorScale patientMotorScale : patientRFMotorScales) {
						if ((patientMotorScale.getPercent97Rank()) == i) {
							if (patientMotorScale.getMotorScaleStatus().name().equals("P")) {
								orginalRefenecepoint = patientMotorScale.getPercent97Rank();
								System.out.println("orginalRefenecepoint " + orginalRefenecepoint);
							} else if (patientMotorScale.getMotorScaleStatus().name().equals("F")) {
								count++;
							}
						}
					}
				}
				if (count == patientRFMotorScales.size() - falseRFCount) {
					duplicateRFRefenecepoint = RF1MotorScale.getPercent97Rank();
					List<PatientMotorScale> selectedPatientMotorScales = patientMotorScales.subList(0, RF1ClusterId);
					Collections.reverse(selectedPatientMotorScales);

					for (int k = duplicateRFRefenecepoint; k > 0; k--) {
						for (PatientMotorScale pms : selectedPatientMotorScales) {
							int j = pms.getPercent97Rank();
							if (flag == false && j == k) {
								if (pms.getMotorScaleStatus().name() == "P") {
									orginalRefenecepoint = pms.getPercent97Rank();
									flag = true;
								}
							}
						}
					}
					for (PatientMotorScale patientMotorScale : selectedPatientMotorScales) {
						if (patientMotorScale.getMotorScaleStatus() == null) {
							patientMotorScale.setMotorScaleStatus(MotorScaleStatus.P);
							patientMotorScaleRepository.save(patientMotorScale);
						}
					}
					Collections.reverse(selectedPatientMotorScales);
					PatientMotorScale patientMotorScale2 = selectedPatientMotorScales
							.get((orginalRefenecepoint - 1) - falseCount);
					motorAge = Double.parseDouble(patientMotorScale2.getPercent50());
					System.out.println("motor age123 ********* " + motorAge);

					motorScaleAgeRFDto.setAge(motorAge);
					motorScaleAgeRFDto.setOrginalRefenecepoint(orginalRefenecepoint);
				}

			} else if (falseRFCount == 1) {
				PatientMotorScale patientMotorScale = patientRFMotorScales.get(0);
				Integer firstRf = patientMotorScale.getPercent97Rank();
				List<PatientMotorScale> selectedPatientMotorScales = patientMotorScales.subList(0, firstRf);
				Collections.reverse(selectedPatientMotorScales);
				for (int k = firstRf; k > 0; k--) {
					for (PatientMotorScale pms : selectedPatientMotorScales) {
						int j = pms.getPercent97Rank();
						if (flag == false && j == k) {
							if (pms.getMotorScaleStatus().name() == "P") {
								orginalRefenecepoint = pms.getPercent97Rank();
								flag = true;
							}
							;
						}
					}
				}
				for (PatientMotorScale patientMotorScale1 : selectedPatientMotorScales) {
					if (patientMotorScale1.getMotorScaleStatus() == null) {
						patientMotorScale1.setMotorScaleStatus(MotorScaleStatus.P);
						patientMotorScaleRepository.save(patientMotorScale1);
					}
				}
				Collections.reverse(selectedPatientMotorScales);
				PatientMotorScale patientMotorScale2 = selectedPatientMotorScales
						.get((orginalRefenecepoint - 1) - falseCount);
				motorAge = Double.parseDouble(patientMotorScale2.getPercent50());
				System.out.println("motor age " + motorAge);
				motorScaleAgeRFDto.setAge(motorAge);
				motorScaleAgeRFDto.setOrginalRefenecepoint(orginalRefenecepoint);

			} else {
				throw new RuntimeException("Please Select at least one RF ");
			}

		}
		return motorScaleAgeRFDto;

	}

	@Override
	public void updatePatientMotorScales(List<PatientMotorScale> patientMotorScale, Long patientId) {

		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		MotorResult dbMotorResult = motorResultRepository.findByPatient_Id(patientId);
		if (dbMotorResult == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		List<PatientMotorScale> dbPatientMotorScale = patientMotorScaleRepository
				.findByMotorResult_Id(dbMotorResult.getId());
		List<PatientMotorScale> updatedPatientMotorScales = new ArrayList<PatientMotorScale>();
		for (PatientMotorScale patientMotorScale1 : patientMotorScale) {
			for (PatientMotorScale PatientMotorScale2 : dbPatientMotorScale) {
				Integer pms1Id = patientMotorScale1.getPercent97Rank();
				Integer pms2Id = PatientMotorScale2.getPercent97Rank();
				if (pms1Id.equals(pms2Id)) {
					PatientMotorScale2.setMotorScaleStatus(patientMotorScale1.getMotorScaleStatus());
					updatedPatientMotorScales.add(PatientMotorScale2);
					patientMotorScaleRepository.save(PatientMotorScale2);
				}
			}

		}
		 DecimalFormat df2 = new DecimalFormat(".##");
		String dob = dbPatient.getDateOfBirth();
		String actDate = dob.substring(0, 10);
		String[] list = actDate.split("-");
		LocalDate pdate = LocalDate.of(Integer.parseInt(list[0]), Integer.parseInt(list[1]), Integer.parseInt(list[2]));
		LocalDate now = LocalDate.now();
		Period diff = Period.between(pdate, now);
		int months = diff.getYears() * 12;
		int totalmonths = months + diff.getMonths();
		float ageOfChild = totalmonths + convertDaysToMonth(diff.getDays());

		if (updatedPatientMotorScales != null && updatedPatientMotorScales.size() > 0) {
			MotorScaleAgeRFDto motorScaleAgeRFDto = calculateMotorAge(updatedPatientMotorScales);

			List<MotorClusterCount> resultset = getMotorClusterCount(dbMotorResult.getId(), patientId,
					motorScaleAgeRFDto.getOrginalRefenecepoint());

			Double motorAge = motorScaleAgeRFDto.getAge();
			Double motorQuotient=(motorAge*100)/ageOfChild;
			
			dbMotorResult.setMotorQuotient(Double.valueOf(df2.format(motorQuotient)));
			dbMotorResult.setMotorAge(motorScaleAgeRFDto.getAge());
			dbMotorResult.setMotorClusterCountList(resultset);
			motorResultRepository.save(dbMotorResult);

			for (MotorClusterCount MotorClusterCount : resultset) {
				MotorClusterCount.setMotorResult(dbMotorResult);
				motorClusterCountRepository.save(MotorClusterCount);
			}
		}
	}

	@Override
	public List<MotorScaleResultDto> getAllPatientMotorClusterCountResult(Long patientId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		MotorResult dbMotorResult = motorResultRepository.findByPatient_Id(patientId);
		if (dbMotorResult == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		List<MotorClusterCount> dbMotorClusterCount = motorClusterCountRepository
				.findByMotorResult_Id(dbMotorResult.getId());
		if (dbMotorClusterCount == null) {
			throw new RuntimeException("No List Found With This Patient............!!!!!!!!");
		}
		ArrayList<MotorScaleResultDto> motorScaleResult = new ArrayList<MotorScaleResultDto>();
		for (MotorClusterCount motorClusterCount : dbMotorClusterCount) {
			MotorCluster motorClusters = motorClusterRepositories.findOne(motorClusterCount.getClusterId());

			List<MotorScale> motorScalesByCluster = motorScaleRepository.findByMotorCluster_Id(motorClusters.getId());
			MotorScaleResultDto motorScaleResultDto = new MotorScaleResultDto();
			motorScaleResultDto.setClusterName(motorClusters.getName());
			motorScaleResultDto.setClusterCount(motorClusterCount.getClusterCount());
			motorScaleResultDto.setPercentile(motorClusterCount.getPercentile());
			motorScaleResultDto.setRemarks(motorClusterCount.getRemarks());
			motorScaleResultDto.setMotorAge(dbMotorResult.getMotorAge());
			motorScaleResultDto.setGraphCount(motorClusterCount.getGraphCount());
			motorScaleResultDto.setMotorQuotient(dbMotorResult.getMotorQuotient());
			if (motorScalesByCluster != null) {
				motorScaleResultDto.setScaleCount(motorScalesByCluster.size());
			}
			motorScaleResult.add(motorScaleResultDto);
		}
		return motorScaleResult;
	}

	@Override
	public List<MotorScalesCountGraphDto> getAllPatientMotorClusterCountForGraph(Long patientId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		MotorResult dbMotorResult = motorResultRepository.findByPatient_Id(patientId);
		if (dbMotorResult == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		List<MotorClusterCount> dbMotorClusterCount = motorClusterCountRepository
				.findByMotorResult_Id(dbMotorResult.getId());
		if (dbMotorClusterCount == null) {
			throw new RuntimeException("No List Found With This Patient............!!!!!!!!");
		}
		ArrayList<MotorScalesCountGraphDto> motorScaleResult = new ArrayList<MotorScalesCountGraphDto>();
		for (MotorClusterCount motorClusterCount : dbMotorClusterCount) {
			MotorScalesCountGraphDto motorScalesCountGraphDto = new MotorScalesCountGraphDto();
			motorScalesCountGraphDto.setClusterId(motorClusterCount.getClusterId());
			if (motorClusterCount.getPercentile().equals("< 10")) {
				motorScalesCountGraphDto.setGraphCount(8L);
			} else {
				motorScalesCountGraphDto.setGraphCount(motorClusterCount.getGraphCount());
			}
			motorScaleResult.add(motorScalesCountGraphDto);
		}

		return motorScaleResult;
	}

}
