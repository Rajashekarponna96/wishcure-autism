package com.openspace.Model.DateConverters;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	public Date convertToDatabaseColumn(LocalDate date) {
		return (date == null) ? null : Date.valueOf(date);
	}

	public LocalDate convertToEntityAttribute(Date dbData) {
		return (dbData == null) ? null : dbData.toLocalDate();
	}
}
