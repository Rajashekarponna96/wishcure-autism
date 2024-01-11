package com.openspace.Model.DateConverters;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements
		AttributeConverter<LocalDateTime, Timestamp> {

	public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
		return (attribute == null) ? null : java.sql.Timestamp
				.valueOf(attribute);
	}

	public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
		if (dbData == null) {
			return null;
		}
		LocalDateTime fromDb = dbData.toLocalDateTime();
		return (dbData == null) ? null : dbData.toLocalDateTime().truncatedTo(
				ChronoUnit.SECONDS);
	}
}
