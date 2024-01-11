package com.openspace.Model.DateConverters;

import java.time.Year;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, Integer> {

	public Integer convertToDatabaseColumn(Year year) {
		System.out.println("integre value===============  "+year.getValue());
		return (year == null) ? null : new Integer(year.getValue());
	}

	public Year convertToEntityAttribute(Integer dbData) {
		return (dbData == null) ? null : Year.of(dbData);
	}

	
}
