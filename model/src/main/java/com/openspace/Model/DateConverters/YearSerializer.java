package com.openspace.Model.DateConverters;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class YearSerializer extends JsonSerializer<Year> {

	@Override
	public void serialize(Year year, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {

		Integer integerYear = year.getValue();

		jgen.writeNumber(integerYear);
	}
}
