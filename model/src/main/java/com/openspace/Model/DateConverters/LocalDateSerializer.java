package com.openspace.Model.DateConverters;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {

	@Override
	public void serialize(LocalDate date, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {

		String formattedDate = date.format(DateTimeFormatter
				.ofPattern("dd-MM-yyyy"));

		jgen.writeString(formattedDate);
	}
}
