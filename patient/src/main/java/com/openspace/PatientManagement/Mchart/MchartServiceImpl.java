package com.openspace.PatientManagement.Mchart;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.DoctorManagement.Patient;
import com.openspace.Model.ParentModule.Mchart.Mchart;
import com.openspace.Model.ParentModule.Mchart.MchartLookup;
import com.openspace.Model.ParentModule.Mchart.MchartObjectDto;
import com.openspace.Model.ParentModule.Mchart.MchartResultDto;
import com.openspace.Model.ParentModule.Mchart.Repositories.MchartRepository;
import com.openspace.Model.PatientMgnt.Repositories.PatientRepository;
import com.openspace.Model.Util.ErrorMessageHandler;

@Service
public class MchartServiceImpl implements MchartService {

	@Autowired
	private MchartRepository mchartRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public void saveMchart(MchartObjectDto mchartObject) {

		Patient dbPatient = patientRepository.findOne(mchartObject.getId());
		if (dbPatient == null) {
			throw new RuntimeException("Patient Doesnot Exist");
		}
		List<Mchart> dbMchart = mchartRepository.findByPatient_Id(dbPatient.getId());
		if (dbMchart.size() == 0) {
			List<Mchart> list2 = new ArrayList<Mchart>();
			for (MchartLookup mchartLookup1 : mchartObject.getMchartLookup()) {
				Mchart mchart1 = new Mchart();
				mchart1.setName(mchartLookup1.getName());
				mchart1.setSelectedAnswer(mchartLookup1.getSelectedAnswer());
				mchart1.setMchartLookupId(mchartLookup1.getId());
				mchart1.setPatient(dbPatient);
				mchart1.setDate(LocalDate.now());
				mchartRepository.save(mchart1);
				list2.add(mchart1);
			}
		} else if (dbMchart.size() > 0) {
			for (Mchart mchart1 : mchartObject.getMchart()) {
				Mchart dbMchart1 = mchartRepository.findOne(mchart1.getId());
				if (dbMchart1 != null) {
					dbMchart1.setSelectedAnswer(mchart1.getSelectedAnswer());
					mchartRepository.save(dbMchart1);
				}
			}
		}
	}

	@Override
	public List<Mchart> getAllMchart(Long id) {
		return mchartRepository.findByPatient_Id(id);
	}

	@Override
	public void deleteMchart(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public MchartResultDto getMchartCountByPatientId(Long id) {

		MchartResultDto mchartResultDto = new MchartResultDto();
		int count = 0;
		List<Object> list = new ArrayList<Object>();
		list.add(2L);
		list.add(7L);
		list.add(9L);
		list.add(13L);
		list.add(14L);
		list.add(15L);

		List<Mchart> mchartlist = mchartRepository.findByPatient_Id(id);
		int numberOfFails = (int) mchartlist.stream().filter(chart -> (chart.getMchartLookupId() == 11 && chart.getSelectedAnswer() == true) || (chart.getMchartLookupId() == 18 &&chart.getSelectedAnswer() == true) || (chart.getMchartLookupId() == 20 &&chart.getSelectedAnswer() == true) || (chart.getMchartLookupId() == 22 &&chart.getSelectedAnswer() == true) || (chart.getSelectedAnswer() == false && chart.getMchartLookupId() != 22 && chart.getMchartLookupId() != 20 && chart.getMchartLookupId() != 18 && chart.getMchartLookupId() != 11 )).count();
		mchartResultDto.setNumberOfFails(numberOfFails);
		
		for (Object obj : list) {
			Mchart mchat = mchartRepository.findByPatient_IdAndMchartLookupIdAndSelectedAnswer(id, obj, false);
			if (mchat != null) {
				count++;
			}
			if(mchartlist != null && mchartlist.size() > 0){
				
				mchartResultDto.setQuestion2answer(mchartlist.get(1).getSelectedAnswer());
				mchartResultDto.setQuestion7answer(mchartlist.get(6).getSelectedAnswer());
				mchartResultDto.setQuestion9answer(mchartlist.get(8).getSelectedAnswer());
				mchartResultDto.setQuestion13answer(mchartlist.get(12).getSelectedAnswer());
				mchartResultDto.setQuestion14answer(mchartlist.get(13).getSelectedAnswer());
				mchartResultDto.setQuestion15answer(mchartlist.get(14).getSelectedAnswer());
			}
		}
		if (count >= 2) {
			mchartResultDto.setResult("This patient is in critical condition, means he/she effected with autisum");
		} else {
			mchartResultDto.setResult("This patient is not effected with autisum");
		}
		return mchartResultDto;
	}

	@Override
	public void deleteMchartByPatient(Long patientId) {
		// TODO Auto-generated method stub
		Patient dbPatient = patientRepository.findOne(patientId);
		if (dbPatient == null) {
			throw new RuntimeException("Patient Doesnot Exist");
		}
		List<Mchart> mchatList = mchartRepository.findByPatient_Id(dbPatient.getId());
		mchartRepository.delete(mchatList);

	}

	@Override
	public List<Mchart> getAllMchartsByPatientId(Long id) {
		// TODO Auto-generated method stub
		return mchartRepository.findByPatient_Id(id);
	}

	@Override
	public Set<String> getEvalutionSheetDatesByPatientId(Long patientId) {
		Patient patient = patientRepository.findOne(patientId);
		if (patient == null) {
			throw new RuntimeException(ErrorMessageHandler.patientDoesNotExists);
		}
		TreeSet<String> dates = new TreeSet<String>();
		List<Mchart> evalutionSheets = mchartRepository.findByPatient_Id( patientId);
		for (Mchart evalutionSheet : evalutionSheets) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
			if (evalutionSheet.getDate() != null) {
				dates.add(evalutionSheet.getDate().format(formatter));
			}
		}
		return dates;
	}

}
