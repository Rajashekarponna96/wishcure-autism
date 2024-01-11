package com.openspace.Model.DateConverters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TemporalConverter implements AttributeConverter<Temporal, String> {

	public String convertToDatabaseColumn(Temporal temporal) {
		String value = null;

		if (temporal instanceof LocalDateTime) {
			LocalDateTime localDateTime = (LocalDateTime) temporal;
			value = localDateTime.format(DateTimeFormatter
					.ofPattern("dd-MM-yyyy HH:mm:ss"));
		}

		if (temporal instanceof LocalDate) {
			LocalDate localDate = (LocalDate) temporal;
			value = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}

		if (temporal instanceof YearMonth) {
			YearMonth yearMonth = (YearMonth) temporal;
			value = yearMonth.format(DateTimeFormatter.ofPattern("MM-yyyy"));
		}

		if (temporal instanceof Year) {
			Year year = (Year) temporal;
			value = String.valueOf(year.getValue());
		}

		return value;
	}

	public Temporal convertToEntityAttribute(String value) {
		Temporal temporal = null;

		if(value == null) {
			return null;
		}
		
		if (value.contains(":")) {
			temporal = LocalDateTime.parse(value,
					DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		} else if (value.split("-").length == 3) {
			temporal = LocalDate.parse(value,
					DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		} else if (value.split("-").length == 2) {
			temporal = YearMonth.parse(value,
					DateTimeFormatter.ofPattern("MM-yyyy"));
		} else {
			temporal = Year.of(Integer.parseInt(value));
		}

		return temporal;
	}
}
