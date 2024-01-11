package com.openspace.PatientManagement.Scales;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.Clusters.MentalClusters;
import com.openspace.Model.Clusters.Repositories.MentalClustersRepositories;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.MentalReferenceProfile.MentalReferenceProfile;
import com.openspace.Model.MentalReferenceProfile.MentalResult;
import com.openspace.Model.MentalReferenceProfile.MentalScaleStatus;
import com.openspace.Model.MentalReferenceProfile.PatientMentalScale;
import com.openspace.Model.MentalReferenceProfile.ReferenceMentalCluster;
import com.openspace.Model.MentalReferenceProfile.Repository.MentalReferenceProfileRepository;
import com.openspace.Model.MentalReferenceProfile.Repository.MentalResultRepository;
import com.openspace.Model.MentalReferenceProfile.Repository.PatientMentalScaleRepository;
import com.openspace.Model.MentalReferenceProfile.Repository.ReferenceMentalClusterRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.Scales.MentalClusterCount;
import com.openspace.Model.Scales.MentalScaleAgeRFDto;
import com.openspace.Model.Scales.MentalScaleDto;
import com.openspace.Model.Scales.MentalScaleResultDto;
import com.openspace.Model.Scales.MentalScaleTemplateDto;
import com.openspace.Model.Scales.MentalScales;
import com.openspace.Model.Scales.MentalScalesCountGraphDto;
import com.openspace.Model.ScalesRepositories.MentalClusterCountRepository;
import com.openspace.Model.ScalesRepositories.MentalScalesRepositories;

@Service
public class MentalScalesServiceImpl implements MentalScalesService {

	@Autowired
	private MentalScalesRepositories mentalScalesRepositories;

	@Autowired
	private MentalClustersRepositories mentalClustersRepositories;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private MentalResultRepository mentalResultRepository;

	@Autowired
	private PatientMentalScaleRepository patientMentalScaleRepository;

	@Autowired
	private MentalReferenceProfileRepository mentalReferenceProfileRepository;

	@Autowired
	private ReferenceMentalClusterRepository referenceMentalClusterRepository;

	@Autowired
	private MentalClusterCountRepository mentalClusterCountRepository;

	@Override
	public void saveMentalScales(MentalScaleDto mentalScaleDto) {

		for (MentalScales mentalScales : mentalScaleDto.getMentalScales()) {
			MentalScales dbMentalScales = mentalScalesRepositories
					.findByItemDescripation(mentalScales.getItemDescripation());
			if (dbMentalScales != null) {
				throw new RuntimeException("Mental Scale Questions Already Exist...!");
			}
			MentalScales mentalScales1 = new MentalScales();
			mentalScales1.setItemDescripation(mentalScales.getItemDescripation());
			mentalScales1.setItemNo(mentalScales.getItemNo());
			mentalScales1.setPercent3(mentalScales.getPercent3());
			mentalScales1.setPercent50(mentalScales.getPercent50());
			mentalScales1.setPercent97(mentalScales.getPercent97());
			mentalScales1.setPercent97Rank(mentalScales.getPercent97Rank());
			mentalScalesRepositories.save(mentalScales1);
		}
	}

	@Override
	public List<MentalScales> getAllMentalScales() {
		return (List<MentalScales>) mentalScalesRepositories.findAll();
	}

	@Override
	public void updateMentalScales(MentalScales mentalScales) {

		MentalScales dbMentalScales = mentalScalesRepositories.findOne(mentalScales.getId());

		if (dbMentalScales == null) {
			throw new RuntimeException("Mental Scale Questions Not Exist...!");
		} else {
			dbMentalScales.setItemDescripation(mentalScales.getItemDescripation());
			dbMentalScales.setItemNo(mentalScales.getItemNo());
			dbMentalScales.setPercent3(mentalScales.getPercent3());
			dbMentalScales.setPercent50(mentalScales.getPercent50());
			dbMentalScales.setPercent97(mentalScales.getPercent97());
			dbMentalScales.setPercent97Rank(mentalScales.getPercent97Rank());
			mentalScalesRepositories.save(dbMentalScales);
		}

	}

	@Override
	public void deleteMentalScales(Long id) {
		MentalScales dbMentalScales = mentalScalesRepositories.findOne(id);
		if (dbMentalScales == null) {
			throw new RuntimeException("Mental cluster Not exists!!");
		} else {
			mentalScalesRepositories.delete(id);
		}
	}

	@Override
	public void updateMentalScalesWithCluster(MentalScaleDto mentalScaleDto) {

		MentalClusters dbMentalClusters = mentalClustersRepositories
				.findOne(mentalScaleDto.getMentalClusters().getId());

		for (MentalScales mentalScales : mentalScaleDto.getMentalScales()) {
			MentalScales dbMentalScales = mentalScalesRepositories
					.findByItemDescripation(mentalScales.getItemDescripation());
			if (dbMentalScales == null) {
				throw new RuntimeException("Mental Scale Questions Not Exist...!");
			}
			dbMentalScales.setMentalClusters(dbMentalClusters);
			mentalScalesRepositories.save(dbMentalScales);
		}
	}

	@Override
	public void assignMentalScaleTemplateToPatient(MentalScaleTemplateDto mentalScaleTemplateDto) {
		Patient patient = mentalScaleTemplateDto.getPatient();
		List<MentalScales> mentalScales = mentalScaleTemplateDto.getMentalScales();
		Patient dbPatient = patientRepository.findOne(patient.getId());
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exists!");
		}
		List<MentalResult> mentalResults = new ArrayList<MentalResult>();
		if (mentalScales != null && mentalScales.size() > 0) {
			MentalResult mentalResult = new MentalResult();
			mentalResult.setPatient(dbPatient);
			mentalResultRepository.save(mentalResult);

			List<PatientMentalScale> patientMentalScales = convertToPatientMentalScales(mentalResult, mentalScales,
					patient);
			
			if (patientMentalScales != null || patientMentalScales.size() > 0) {
				mentalResult.setPatientMentalScales(patientMentalScales);
				mentalResultRepository.save(mentalResult);
				updatePatientMentalScales(patientMentalScales,dbPatient.getId());
			}

		}
	}

	public List<PatientMentalScale> convertToPatientMentalScales(MentalResult mentalResult,
			List<MentalScales> mentalScales, Patient patient) {
		List<PatientMentalScale> patientMentalScales = new ArrayList<PatientMentalScale>();
		for (MentalScales mentalScale : mentalScales) {
			PatientMentalScale patientMentalScale = new PatientMentalScale();
			if (mentalScale != null) {
				if (mentalScale.getItemDescripation() != null) {
					patientMentalScale.setItemDescripation(mentalScale.getItemDescripation());
				}
				if (mentalResult != null) {
					patientMentalScale.setMentalResult(mentalResult);
				}
				if (mentalScale.getMentalScaleStatus() != null) {
					patientMentalScale.setMentalScaleStatus(mentalScale.getMentalScaleStatus());
				}
				if (mentalScale.getPercent50() != null) {
					patientMentalScale.setPercent50(mentalScale.getPercent50());
				}

				if (mentalScale.getPercent3() != null) {
					patientMentalScale.setPercent3(mentalScale.getPercent3());
				}
				if (mentalScale.getPercent97() != null) {
					patientMentalScale.setPercent97(mentalScale.getPercent97());
				}
				if (mentalScale.getPercent97Rank() != null) {
					patientMentalScale.setPercent97Rank(mentalScale.getPercent97Rank());
				}if (mentalScale.getNote() != null) {
					patientMentalScale.setNote(mentalScale.getNote());
				}if (mentalScale.getMentalScaleStatus() != null) {
					patientMentalScale.setMentalScaleStatus(mentalScale.getMentalScaleStatus());
				}
				
				if (mentalScale.getId() != null) {
					patientMentalScale.setMentalScaleId(mentalScale.getId());
				}
				if (mentalScale.getMentalClusters() != null && mentalScale.getMentalClusters().getId() != null) {
					patientMentalScale.setMentalClusterId(mentalScale.getMentalClusters().getId());
				}
				patientMentalScaleRepository.save(patientMentalScale);

			}
			patientMentalScales.add(patientMentalScale);
		}
		return patientMentalScales;
	}

	public List<MentalClusterCount> getMentalClusterCount(Long resultId, Long patientId, Integer orgRf) {
		MentalResult dbMentalResult = mentalResultRepository.findOne(resultId);
		if (dbMentalResult == null) {
			throw new RuntimeException("MentalResult Does not Exist");
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
		List<MentalClusterCount> clustercount = new ArrayList<MentalClusterCount>();
		List<MentalClusterCount> listOfClustersByResult = mentalClusterCountRepository.findByMentalResult_Id(resultId);
		if (listOfClustersByResult != null && listOfClustersByResult.size() > 1) {
			clustercount = listOfClustersByResult;
		} else {
			clustercount = patientMentalScaleRepository.getCount(resultId, orgRf);
		}

		List<MentalClusters> mentalClusters = (List<MentalClusters>) mentalClustersRepositories.findAll();
		List<MentalClusters> selectedMentalClusters = new ArrayList<MentalClusters>();
		if (clustercount.size() <= 10) {
			for (MentalClusters mentalClusters1 : mentalClusters) {
				for (MentalClusterCount mentalClusterCount : clustercount) {
					if (mentalClusters1.getId() == mentalClusterCount.getClusterId()) {
						selectedMentalClusters.add(mentalClusters1);
					}

				}
			}
		}
		mentalClusters.removeAll(selectedMentalClusters);
		for (MentalClusters mentalClustersToSave : mentalClusters) {
			MentalClusterCount mentalClusterCountNewInsert = new MentalClusterCount();
			mentalClusterCountNewInsert.setClusterId(mentalClustersToSave.getId());
			mentalClusterCountNewInsert.setClusterCount(0L);
			clustercount.add(mentalClusterCountNewInsert);
			// mentalClusterCountRepository.save(mentalClusterCountNewInsert);
		}

		MentalReferenceProfile mentalReferenceProfile = mentalReferenceProfileRepository
				.getMentalReferenceProfileByAgeLimit(ageOfChild);
		System.out.println("mentalReferenceProfile1 " + mentalReferenceProfile.getMaxAge() + " "
				+ mentalReferenceProfile.getMinAge());
		System.out.println("mentalReferenceProfile2 " + mentalReferenceProfile.getReferenceMentalCluster().size());
		List<MentalClusterCount> updatedclustercount = new ArrayList<MentalClusterCount>();
		if (mentalReferenceProfile != null) {
			if (clustercount != null && clustercount.size() > 0) {
				long n = 0;
				long count = 0;
				for (MentalClusterCount mentalClusterCount : clustercount) {
					// select ReferenceMentalClusterList only based on clusterId
					// and reference profileId //5 records only
					List<ReferenceMentalCluster> selectedList = referenceMentalClusterRepository
							.findByMentalReferenceProfile_IdAndMentalClusterId(mentalReferenceProfile.getId(),
									mentalClusterCount.getClusterId());
					List<ReferenceMentalCluster> duplicatelist = new ArrayList<ReferenceMentalCluster>();
					for (ReferenceMentalCluster referenceMentalCluster : selectedList) {
						if (mentalClusterCount.getClusterId().equals(referenceMentalCluster.getMentalClusterId())) {
							if (mentalClusterCount.getClusterCount().equals(referenceMentalCluster.getResultValue())) {
								duplicatelist.add(referenceMentalCluster);
							}
							if (referenceMentalCluster.getResultValue() > mentalClusterCount.getClusterCount()) {
								mentalClusterCount.setPercentile("< 10");
								mentalClusterCount.setRemarks("DELAYED");
								mentalClusterCount.setGraphCount(10L);
							} else if (referenceMentalCluster.getResultValue() < mentalClusterCount.getClusterCount()) {
								mentalClusterCount.setPercentile("90");
								mentalClusterCount.setRemarks("NORMAL");
								mentalClusterCount
										.setGraphCount(Long.parseLong(referenceMentalCluster.getResultPercentile()));
							} else {
								if (referenceMentalCluster.getResultValue()
										.equals(Math.ceil(mentalClusterCount.getClusterCount()))) {
									mentalClusterCount.setPercentile(referenceMentalCluster.getResultPercentile());
									mentalClusterCount.setRemarks("NORMAL");
									mentalClusterCount.setGraphCount(
											Long.parseLong(referenceMentalCluster.getResultPercentile()));
								}
							}
						}

					}
					if (duplicatelist != null && duplicatelist.size() > 0) {
						ReferenceMentalCluster lateDuplicateCluster = duplicatelist.get(duplicatelist.size() - 1);
						mentalClusterCount.setPercentile(lateDuplicateCluster.getResultPercentile());
						System.out
								.println("************************ duplicate % " + mentalClusterCount.getPercentile());
						mentalClusterCount.setRemarks("NORMAL");
						mentalClusterCount.setGraphCount(Long.parseLong(lateDuplicateCluster.getResultPercentile()));
					}
					updatedclustercount.add(mentalClusterCount);
				}
			}
		}

		System.out.println(mentalReferenceProfile.getMaxAge() + "  " + mentalReferenceProfile.getMinAge());
		System.out.println("size" + mentalReferenceProfile.getReferenceMentalCluster().size());
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

	public MentalScaleAgeRFDto calculateMentalAge(List<PatientMentalScale> patientMentalScales) {
		Integer falseCount = 0;
		Integer totalfalseCount = 0;
		Integer falseRFCount = 0;
		Integer falseRFListCount = 0;
		Integer orginalRefenecepoint = 0;
		Integer duplicateRFRefenecepoint = 0;
		int i;
		Integer count = 0;
		Double mentalAge = 0.0;
		boolean flag = false;
		boolean rf2flag = false;
		MentalScaleAgeRFDto mentalScaleAgeRFDto = new MentalScaleAgeRFDto();

		List<PatientMentalScale> patientRFMentalScales = new ArrayList<PatientMentalScale>();
		List<PatientMentalScale> patientRfMentalScaleList = new ArrayList<PatientMentalScale>();
		if (patientMentalScales != null && patientMentalScales.size() > 0) {
			for (PatientMentalScale patientMentalScale : patientMentalScales) {
				if (patientMentalScale.getMentalScaleStatus() != null) {
					if (patientMentalScale.getMentalScaleStatus().name() == "RF") {
						patientRFMentalScales.add(patientMentalScale);
						falseRFCount++;
						rf2flag = true;
					}
					if (rf2flag == false && patientMentalScale.getMentalScaleStatus().name() == "F") {
						// false count based on with out consider of between two
						// rf
						// points
						falseCount++;
					}
					if (patientMentalScale.getMentalScaleStatus().name() == "F") {
						totalfalseCount++;
					}
				}
			}
			if (falseRFCount >= 2) {
				PatientMentalScale RF1MentalScale = patientRFMentalScales.get(0);
				PatientMentalScale RFnMentalScale = patientRFMentalScales.get(patientRFMentalScales.size() - 1);
				Integer RF1ClusterId = (RF1MentalScale.getPercent97Rank());
				Integer RFnClusterId = (RFnMentalScale.getPercent97Rank());
				if (RF1ClusterId > RFnClusterId) {
					falseRFListCount = RF1ClusterId - RFnClusterId;
				} else if (RF1ClusterId < RFnClusterId) {
					falseRFListCount = RFnClusterId - RF1ClusterId;
				}
				for (i = RF1ClusterId; i <= RFnClusterId; i++) {
					for (PatientMentalScale patientMentalScale : patientRFMentalScales) {
						if ((patientMentalScale.getPercent97Rank()) == i) {
							if (patientMentalScale.getMentalScaleStatus().name().equals("P")) {
								orginalRefenecepoint = patientMentalScale.getPercent97Rank();
								System.out.println("orginalRefenecepoint " + orginalRefenecepoint);
							} else if (patientMentalScale.getMentalScaleStatus().name().equals("F")) {
								count++;
							}
						}
					}
				}
				if (count == patientRFMentalScales.size() - falseRFCount) {
					duplicateRFRefenecepoint = RF1MentalScale.getPercent97Rank();
					List<PatientMentalScale> selectedPatientMentalScales = patientMentalScales.subList(0, RF1ClusterId);
					Collections.reverse(selectedPatientMentalScales);

					for (int k = duplicateRFRefenecepoint; k > 0; k--) {
						for (PatientMentalScale pms : selectedPatientMentalScales) {
							int j = pms.getPercent97Rank();
							if (flag == false && j == k) {
								if (pms.getMentalScaleStatus().name() == "P") {
									orginalRefenecepoint = pms.getPercent97Rank();
									flag = true;
								}
							}
						}
					}
					for (PatientMentalScale patientMentalScale : selectedPatientMentalScales) {
						if (patientMentalScale.getMentalScaleStatus() == null) {
							patientMentalScale.setMentalScaleStatus(MentalScaleStatus.P);
							patientMentalScaleRepository.save(patientMentalScale);
						}
					}
					Collections.reverse(selectedPatientMentalScales);
					PatientMentalScale patientMentalScale2 = selectedPatientMentalScales
							.get((orginalRefenecepoint - 1) - falseCount);
					mentalAge = Double.parseDouble(patientMentalScale2.getPercent50());
					System.out.println("mental age123 ********* " + mentalAge);
					mentalScaleAgeRFDto.setAge(mentalAge);
					mentalScaleAgeRFDto.setOrginalRefenecepoint(orginalRefenecepoint);
				}

			} else if (falseRFCount == 1) {
				PatientMentalScale patientMentalScale = patientRFMentalScales.get(0);
				Integer firstRf = patientMentalScale.getPercent97Rank();
				List<PatientMentalScale> selectedPatientMentalScales = patientMentalScales.subList(0, firstRf);
				Collections.reverse(selectedPatientMentalScales);
				for (int k = firstRf; k > 0; k--) {
					for (PatientMentalScale pms : selectedPatientMentalScales) {
						int j = pms.getPercent97Rank();
						if (flag == false && j == k) {
							if (pms.getMentalScaleStatus().name() == "P") {
								orginalRefenecepoint = pms.getPercent97Rank();
								flag = true;
							}
							;
						}
					}
				}
				for (PatientMentalScale patientMentalScale1 : selectedPatientMentalScales) {
					if (patientMentalScale1.getMentalScaleStatus() == null) {
						patientMentalScale1.setMentalScaleStatus(MentalScaleStatus.P);
						patientMentalScaleRepository.save(patientMentalScale1);
					}
				}
				Collections.reverse(selectedPatientMentalScales);
				PatientMentalScale patientMentalScale2 = selectedPatientMentalScales
						.get((orginalRefenecepoint - 1) - falseCount);
				mentalAge = Double.parseDouble(patientMentalScale2.getPercent50());
				System.out.println("mental age " + mentalAge);
				mentalScaleAgeRFDto.setAge(mentalAge);
				mentalScaleAgeRFDto.setOrginalRefenecepoint(orginalRefenecepoint);

			} else {
				throw new RuntimeException("Please Select at least one RF ");
			}

		}
		return mentalScaleAgeRFDto;

	}

	@Override
	public List<PatientMentalScale> getAllPatientMentalScalesByPatientId(Long patientId) {
		
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		MentalResult dbMentalResult = mentalResultRepository.findByPatient_Id(patientId);
		if (dbMentalResult == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		List<PatientMentalScale> patientMentalScaleList = patientMentalScaleRepository
				.findByMentalResult_Id(dbMentalResult.getId());
		return patientMentalScaleList;
	}

	

	@Override
	public void updatePatientMentalScales(List<PatientMentalScale> patientMentalScale, Long patientId) {

		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		MentalResult dbMentalResult = mentalResultRepository.findByPatient_Id(patientId);
		if (dbMentalResult == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		List<PatientMentalScale> dbPatientMentalScale = patientMentalScaleRepository
				.findByMentalResult_Id(dbMentalResult.getId());
		List<PatientMentalScale> updatedPatientMentalScales = new ArrayList<PatientMentalScale>();
		for (PatientMentalScale patientMentalScale1 : patientMentalScale) {
			for (PatientMentalScale PatientMentalScale2 : dbPatientMentalScale) {
				Integer pms1Id = patientMentalScale1.getPercent97Rank();
				Integer pms2Id = PatientMentalScale2.getPercent97Rank();
				if (pms1Id.equals(pms2Id)) {
					PatientMentalScale2.setMentalScaleStatus(patientMentalScale1.getMentalScaleStatus());
					updatedPatientMentalScales.add(PatientMentalScale2);
					patientMentalScaleRepository.save(PatientMentalScale2);
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

		if (updatedPatientMentalScales != null && updatedPatientMentalScales.size() > 0) {
			MentalScaleAgeRFDto mentalScaleAgeRFDto = calculateMentalAge(updatedPatientMentalScales);
			List<MentalClusterCount> resultset = getMentalClusterCount(dbMentalResult.getId(), patientId,
					mentalScaleAgeRFDto.getOrginalRefenecepoint());
			Double mentalAge = mentalScaleAgeRFDto.getAge();
			Double mentalQuotient = (mentalAge * 100) / ageOfChild;
			dbMentalResult.setMentalAge(mentalScaleAgeRFDto.getAge());
			dbMentalResult.setMentalClusterCountList(resultset);
			dbMentalResult.setMentalQuotient(Double.valueOf(df2.format(mentalQuotient)));
			mentalResultRepository.save(dbMentalResult);
			for (MentalClusterCount MentalClusterCount : resultset) {
				MentalClusterCount.setMentalResult(dbMentalResult);
				mentalClusterCountRepository.save(MentalClusterCount);
			}
		}
	}

	@Override
	public void getMentaAgeOfChild() {
		Integer falseCount = 0;
		Integer falseRFCount = 0;
		Integer falseRFListCount = 0;
		Integer orginalRefenecepoint = 0;
		Double mentalAge = 0.0;
		boolean flag = false;
		List<PatientMentalScale> patientMentalScales = (List<PatientMentalScale>) patientMentalScaleRepository
				.findAll();
		List<PatientMentalScale> patientRFMentalScales = new ArrayList<PatientMentalScale>();
		List<PatientMentalScale> patientRfMentalScaleList = new ArrayList<PatientMentalScale>();
		if (patientMentalScales != null && patientMentalScales.size() > 0) {
			for (PatientMentalScale patientMentalScale : patientMentalScales) {
				if (patientMentalScale.getMentalScaleStatus().name() == "RF") {
					patientRFMentalScales.add(patientMentalScale);
					falseRFCount++;
				}
				if (patientMentalScale.getMentalScaleStatus().name() == "F") {
					falseCount++;
				}
			}

			if (falseRFCount >= 2) {
				PatientMentalScale RF1MentalScale = patientRFMentalScales.get(0);
				PatientMentalScale RFnMentalScale = patientRFMentalScales.get(patientRFMentalScales.size() - 1);
				Integer RF1ClusterId = RF1MentalScale.getPercent97Rank();
				Integer RFnClusterId = RFnMentalScale.getPercent97Rank();
				if (RF1ClusterId > RFnClusterId) {
					falseRFListCount = RF1ClusterId - RFnClusterId;

				} else if (RF1ClusterId < RFnClusterId) {
					falseRFListCount = RFnClusterId - RF1ClusterId;
				}
			} else if (falseRFCount == 1) {
				PatientMentalScale patientMentalScale = patientRFMentalScales.get(0);
				Integer firstRf = patientMentalScale.getPercent97Rank();
				List<PatientMentalScale> selectedPatientMentalScales = patientMentalScales.subList(0, firstRf);
				Collections.reverse(selectedPatientMentalScales);
				for (int i = firstRf; i > 0; i--) {
					for (PatientMentalScale pms : selectedPatientMentalScales) {
						Integer j = pms.getPercent97Rank();
						if (flag == false && j == i) {
							if (pms.getMentalScaleStatus().name() == "P") {
								orginalRefenecepoint = pms.getPercent97Rank();
								flag = true;
							}
							;

						}
					}
				}
				PatientMentalScale patientMentalScale2 = selectedPatientMentalScales.get(orginalRefenecepoint - 1);
				mentalAge = Double.parseDouble(patientMentalScale2.getPercent50());
				System.out.println("mental age " + mentalAge);

			}

		}

	}

	@Override
	public List<MentalScaleResultDto> getAllPatientMentalClusterCountResult(Long patientId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		MentalResult dbMentalResult = mentalResultRepository.findByPatient_Id(patientId);
		if (dbMentalResult == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		List<MentalClusterCount> dbMentalClusterCount = mentalClusterCountRepository
				.findByMentalResult_Id(dbMentalResult.getId());
		if (dbMentalClusterCount == null) {
			throw new RuntimeException("No List Found With This Patient............!!!!!!!!");
		}
		ArrayList<MentalScaleResultDto> mentalScaleResult = new ArrayList<MentalScaleResultDto>();
		for (MentalClusterCount mentalClusterCount : dbMentalClusterCount) {
			MentalClusters mentalClusters = mentalClustersRepositories.findOne(mentalClusterCount.getClusterId());

			List<MentalScales> mentalScalesByCluster = mentalScalesRepositories
					.findByMentalClusters_Id(mentalClusters.getId());
			System.out.println("edabhfsgfiuyrshgfiuyrsgfyiurhgyfr" + mentalScalesByCluster.size());

			MentalScaleResultDto mentalScaleResultDto = new MentalScaleResultDto();
			mentalScaleResultDto.setClusterName(mentalClusters.getName());
			mentalScaleResultDto.setClusterCount(mentalClusterCount.getClusterCount());
			mentalScaleResultDto.setPercentile(mentalClusterCount.getPercentile());
			mentalScaleResultDto.setRemarks(mentalClusterCount.getRemarks());
			mentalScaleResultDto.setMentalAge(dbMentalResult.getMentalAge());
			mentalScaleResultDto.setGraphCount(mentalClusterCount.getGraphCount());
			mentalScaleResultDto.setMentalQuotient(dbMentalResult.getMentalQuotient());
			if (mentalScalesByCluster != null) {
				mentalScaleResultDto.setScaleCount(mentalScalesByCluster.size());
			}
			mentalScaleResult.add(mentalScaleResultDto);
		}

		return mentalScaleResult;
	}

	@Override
	public List<MentalScalesCountGraphDto> getAllPatientMentalClusterCountForGraph(Long patientId) {
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		MentalResult dbMentalResult = mentalResultRepository.findByPatient_Id(patientId);
		if (dbMentalResult == null) {
			throw new RuntimeException("Patient Does not Exist");
		}
		List<MentalClusterCount> dbMentalClusterCount = mentalClusterCountRepository
				.findByMentalResult_Id(dbMentalResult.getId());
		if (dbMentalClusterCount == null) {
			throw new RuntimeException("No List Found With This Patient............!!!!!!!!");
		}
		ArrayList<MentalScalesCountGraphDto> mentalScaleResult = new ArrayList<MentalScalesCountGraphDto>();
		for (MentalClusterCount mentalClusterCount : dbMentalClusterCount) {
			MentalScalesCountGraphDto mentalScalesCountGraphDto = new MentalScalesCountGraphDto();
			mentalScalesCountGraphDto.setClusterId(mentalClusterCount.getClusterId());
			if (mentalClusterCount.getPercentile().equals("< 10")) {
				mentalScalesCountGraphDto.setGraphCount(8L);
			} else {
				mentalScalesCountGraphDto.setGraphCount(mentalClusterCount.getGraphCount());
			}
			mentalScaleResult.add(mentalScalesCountGraphDto);
		}

		return mentalScaleResult;
	}

}
