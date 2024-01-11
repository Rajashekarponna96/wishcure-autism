package com.openspace.PatientManagement.Mchart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openspace.Model.ParentModule.Mchart.MchartLookup;
import com.openspace.Model.ParentModule.Mchart.Repositories.MchartLookupRepository;

@Service
public class MchartLookupServiceImpl implements MchartLookupService {

	@Autowired
	private MchartLookupRepository mchartLookupRepository;

	@Override
	public void saveMchartLookup(MchartLookup mchartLookup) {
		// TODO Auto-generated method stub
		MchartLookup dbMchartLookup = mchartLookupRepository.findByName(mchartLookup.getName());
		if (dbMchartLookup != null) {
			throw new RuntimeException("M-chart Question Already Exist...!");
		}
		mchartLookupRepository.save(mchartLookup);
	}

	@Override
	public List<MchartLookup> getAllMchartLookup() {
		// TODO Auto-generated method stub
		return (List<MchartLookup>) mchartLookupRepository.findAll();
	}

	@Override
	public void updateMchartLookup(MchartLookup mchartLookup) {
		// TODO Auto-generated method stub
		MchartLookup dbMchartLookup = mchartLookupRepository.findOne(mchartLookup.getId());
		if (dbMchartLookup == null) {
			throw new RuntimeException("M-chart Question doesn't Exist");
		}
		MchartLookup dbMchartLookup2 = mchartLookupRepository.findByName(mchartLookup.getName());
		if (dbMchartLookup2 == null) {
			
		} else if (dbMchartLookup2.getId().equals(mchartLookup.getId())) {
			
		} else {
			throw new RuntimeException("M-chart Question Already Exist!");
		}

		dbMchartLookup.setName(mchartLookup.getName());
		dbMchartLookup.setSelectedAnswer(mchartLookup.getSelectedAnswer());
		mchartLookupRepository.save(dbMchartLookup);
	}

	@Override
	public void deleteMchartLookup(Long id) {
		// TODO Auto-generated method stub
		MchartLookup dbMchartLookup = mchartLookupRepository.findOne(id);
		if (dbMchartLookup == null) {
			throw new RuntimeException("M-chart doesn't Exist");
		}
		mchartLookupRepository.delete(dbMchartLookup);
	}

}