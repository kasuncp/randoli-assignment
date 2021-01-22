package ca.randoli.assignment;

import ca.randoli.assignment.dto.EventDTO;
import ca.randoli.assignment.dto.RecordDTO;
import ca.randoli.assignment.response.EventResponse;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfiguration {

    @Bean
    public JacksonDataFormat eventDtoDataFormat() {
        return new JacksonDataFormat(EventDTO.class);
    }

    @Bean
    public JacksonDataFormat eventResponseDataFormat() {
        return new JacksonDataFormat(EventResponse.class);
    }

    @Bean
    public JacksonDataFormat recordDtoDataFormat() {
        return new JacksonDataFormat(RecordDTO.class);
    }

    @Bean
    public JacksonDataFormat eventsListDataFormat() {
        return new JacksonDataFormat(RecordDTO.class);
    }

    @Bean
    public ExecutorService executorThreadPool() {
        return Executors.newCachedThreadPool();
    }
}
