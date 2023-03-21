package cc.sample.batch.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class MapperConfig {

    // task 서버와 날짜타입 맞추려면 task 에서 동일한 설정 사용 필요
    @Bean
    public ObjectMapper objectMapper() {
        Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = Jackson2ObjectMapperBuilder.json();
        jackson2ObjectMapperBuilder.serializationInclusion(JsonInclude.Include.USE_DEFAULTS);
        jackson2ObjectMapperBuilder.failOnEmptyBeans(false);
        jackson2ObjectMapperBuilder.failOnUnknownProperties(false);
        jackson2ObjectMapperBuilder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return jackson2ObjectMapperBuilder.build();
    }
}
