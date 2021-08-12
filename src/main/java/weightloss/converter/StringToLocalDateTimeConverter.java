package weightloss.converter;

import org.modelmapper.AbstractConverter;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class StringToLocalDateTimeConverter extends AbstractConverter<String, LocalDateTime> {

    @Override
    protected LocalDateTime convert(String date) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDateTime dateTime = LocalDate.parse(date, parser).atStartOfDay();
        return dateTime;
    }

}