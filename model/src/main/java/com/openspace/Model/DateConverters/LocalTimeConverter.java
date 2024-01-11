package com.openspace.Model.DateConverters;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

	public Time convertToDatabaseColumn(LocalTime localTime) {
		return (localTime == null) ? null : Time.valueOf(localTime);
	}

	public LocalTime convertToEntityAttribute(Time dbData) {
		return (dbData == null) ? null : LocalTime.parse(dbData.toLocalTime()
				.toString());
	}

}
