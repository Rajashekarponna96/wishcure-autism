package com.openspace.Model.DateConverters;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

	@Override
	public void serialize(LocalDateTime dateTime, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {

		String formattedDate = dateTime.format(DateTimeFormatter
				.ofPattern("dd-MM-yyyy HH:mm:ss"));

		jgen.writeString(formattedDate);
	}
}
