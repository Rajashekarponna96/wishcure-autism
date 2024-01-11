package com.openspace.PatientManagement.Clusters;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openspace.Model.Clusters.VSMSCluster;
import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.Dto.VSMSChildPerformanceDTO;
import com.openspace.Model.Dto.VSMSReportGraphDTO;
import com.openspace.Model.VSMS.PatientVSMSQuestion;
import com.openspace.Model.VSMS.VSMSAnswerCountAndScoringSheet;
import com.openspace.PatientManagement.Scales.VSMSAnswerCountAndScoringSheetService;
import com.openspace.PatientManagement.Scales.VSMSQuestionsService;
import com.openspace.PatientManagement.service.PatientService;

@RestController
public class VSMSClustersController {
	@Autowired
	private VSMSClustersService vsmsClustersService;
	
	@Autowired
	private VSMSQuestionsService vsmsQuestionsService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private VSMSAnswerCountAndScoringSheetService vSMSAnswerCountAndScoringSheetService;
	
	private static DecimalFormat doubleFormat = new DecimalFormat("#.##");
	

	@RequestMapping(value = "/saveVSMSCluster", method = RequestMethod.POST)
	public @ResponseBody void saveVSMSCluster(@RequestBody VSMSCluster vsmsClusters) {
		vsmsClustersService.saveVSMSCluster(vsmsClusters);
	}
	
	@RequestMapping(value = "/getAllVSMSCluster", method = RequestMethod.GET)
	public @ResponseBody List<VSMSCluster> getAllVSMSCluster() {
		
		return vsmsClustersService.getAllVSMSCluster();
	}

	@RequestMapping(value = "/getVSMReportData/{patientId}", method = RequestMethod.GET)
	public @ResponseBody VSMSReportGraphDTO getVSMReportData(@PathVariable Long patientId) {
		VSMSReportGraphDTO vSMSReportGraphDTO = new VSMSReportGraphDTO();
		List<VSMSChildPerformanceDTO> vsmsChildPerformanceDTOList = new ArrayList<>();
		List<PatientVSMSQuestion> patientVSMSQuestionList = vsmsQuestionsService.getAllPatientVSMSQuestions(patientId);
		Patient patient = patientService.findById(patientId);
		int answerCount = (int) patientVSMSQuestionList.stream().filter(question -> "P".equals(question.getVsmsQuestionStatus().toString()) ).count();
		VSMSAnswerCountAndScoringSheet  vSMSAnswerCountAndScoringSheet  = vSMSAnswerCountAndScoringSheetService.findByAnswerCount(answerCount);
		vSMSReportGraphDTO.setSocialAge(vSMSAnswerCountAndScoringSheet.getScoringAge()+1);
		double scoringAge = Double.parseDouble(vSMSAnswerCountAndScoringSheet.getScoringAge().replace("months", "").replaceAll("\\s", ""));
		double patientAge = Double.parseDouble(patient.getAge().replace("months", "").replaceAll("\\s", ""));
		double quotient  = 0;
		if (patientAge != 0) {
			quotient  = (scoringAge/patientAge)*100;
			BigDecimal bd = new BigDecimal(quotient).setScale(2, RoundingMode.HALF_UP);
			quotient = bd.doubleValue();
			
		}
		
		vSMSReportGraphDTO.setVsmsQuotient(quotient);
		
		List<VSMSCluster> vsmsClusters = vsmsClustersService.getAllVSMSCluster();
		vsmsClusters.forEach(cluster ->{
			VSMSChildPerformanceDTO vSMSChildPerformanceDTO = new VSMSChildPerformanceDTO();
			long clusterCount = vsmsQuestionsService.findByVSMSClusterId(cluster.getId()).stream().count();
			vSMSChildPerformanceDTO.setClusterCount((int) clusterCount);
			vSMSChildPerformanceDTO.setNameOfCluster(cluster.getName());
			double passedQuestionForCluster =  patientVSMSQuestionList.stream().filter(question -> "P".equals(question.getVsmsQuestionStatus().toString()) && cluster.getId().equals(question.getVsmsQuestion().getVsmsCluster().getId())).count();
			double totalQuestionForCluster =  patientVSMSQuestionList.stream().filter(question ->  cluster.getId().equals(question.getVsmsQuestion().getVsmsCluster().getId())).count();
			vSMSChildPerformanceDTO.setTotalNoOfItemsPassed((int) passedQuestionForCluster);
			double percentile = 0;
			System.out.println("passedQuestionForCluster "+passedQuestionForCluster);
			System.out.println("totalQuestionForCluster "+totalQuestionForCluster);
			if (totalQuestionForCluster != 0) {
				percentile = (passedQuestionForCluster/totalQuestionForCluster)*100;
				BigDecimal bd = new BigDecimal(percentile).setScale(2, RoundingMode.HALF_UP);
				percentile = bd.doubleValue();
			}
			if (percentile == 100) {
				vSMSChildPerformanceDTO.setRemarks("Normal");
			}else if(percentile <100) {
				vSMSChildPerformanceDTO.setRemarks("Delayed");
			}
			vSMSChildPerformanceDTO.setPercentile(percentile);
			vsmsChildPerformanceDTOList.add(vSMSChildPerformanceDTO);
		});
		vSMSReportGraphDTO.setVsmsChildPerformanceDTOList(vsmsChildPerformanceDTOList);
		
		return vSMSReportGraphDTO;
	}

	@RequestMapping(value = "/updateVSMSCluster", method = RequestMethod.PUT)
	public @ResponseBody void updateMentalCluster(@RequestBody VSMSCluster vsmsClusters) {
		vsmsClustersService.updateVSMSCluster(vsmsClusters);
	}

	@RequestMapping(value = "/deleteVSMSCluster/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteMentalCluster(@PathVariable Long id) {
		vsmsClustersService.deleteVSMSCluster(id);
	}
}
