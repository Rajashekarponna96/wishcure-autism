package com.openspace.Model.ParentModule.Mchart;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Convert;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openspace.Model.DateConverters.LocalDateConverter;
import com.openspace.Model.DateConverters.LocalDateDeserializer;
import com.openspace.Model.DateConverters.LocalDateSerializer;

public class MchartObjectDto {

	private Long id;

	private List<MchartLookup> mchartLookup;

	private List<Mchart> mchart;
	
	private LocalDate date;
	
	@Convert(converter = LocalDateConverter.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MchartLookup> getMchartLookup() {
		return mchartLookup;
	}

	public void setMchartLookup(List<MchartLookup> mchartLookup) {
		this.mchartLookup = mchartLookup;
	}

	public List<Mchart> getMchart() {
		return mchart;
	}

	public void setMchart(List<Mchart> mchart) {
		this.mchart = mchart;
	}

}
