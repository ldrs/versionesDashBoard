package rd.huma.dashboard.model.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimePersistenceConverter implements AttributeConverter<LocalDateTime, java.sql.Timestamp>{

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
		 return java.sql.Timestamp.valueOf(entityValue);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp databaseValue) {
		 return databaseValue.toLocalDateTime();
	}
}