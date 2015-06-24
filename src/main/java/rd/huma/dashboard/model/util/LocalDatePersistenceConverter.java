package rd.huma.dashboard.model.util;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, java.sql.Date>{

	@Override
	public Date convertToDatabaseColumn(LocalDate entityValue) {
		 return java.sql.Date.valueOf(entityValue);
	}

	@Override
	public LocalDate convertToEntityAttribute(Date databaseValue) {
		 return databaseValue.toLocalDate();
	}
}