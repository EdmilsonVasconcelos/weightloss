package weightloss.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import weightloss.converter.LocalDatetimeToStringConverter;
import weightloss.converter.StringToLocalDateTimeConverter;

@Configuration
public class ModalMapperConfig {

    @Autowired
    private LocalDatetimeToStringConverter localDatetimeToStringConverter;

    @Autowired
    private StringToLocalDateTimeConverter stringToLocalDateTimeConverter;

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(localDatetimeToStringConverter);
        modelMapper.addConverter(stringToLocalDateTimeConverter);

        return modelMapper;
    }

}