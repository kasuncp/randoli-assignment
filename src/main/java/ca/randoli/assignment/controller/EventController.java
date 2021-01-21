package ca.randoli.assignment.controller;

import ca.randoli.assignment.dto.EventDTO;
import ca.randoli.assignment.service.EventService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class EventController extends RouteBuilder {

    @Autowired
    private Environment environment;

    @Override
    public void configure() {
        restConfiguration()
                .contextPath(environment.getProperty("camel.component.servlet.mapping.contextPath", "/api/*"))
                .component("servlet")
                .apiProperty("api.title", "Randoli Events API")
                .apiProperty("api.version", "1.0")
                .apiProperty("cors", "true")
                .port(environment.getProperty("server.port", "8080"))
                .bindingMode(RestBindingMode.json);

        rest("/events").description("Events REST API")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .consumes(MediaType.APPLICATION_JSON_VALUE)

                // GET /api/events
                .get("/").description("Get all events")
                .route()
                .bean(EventService.class, "findAll")
                .endRest()

                // GET /api/events/{eventId}
                .get("/{eventId}").description("Get event by id")
                .route()
                .bean(EventService.class, "findEventById(${header.eventId})")
                .endRest()

                // POST /api/events
                .post("/").description("Create new event")
                .route()
                .marshal().json()
                .unmarshal(getJacksonDataFormat(EventDTO.class))
                .bean(EventService.class, "addEvent(${body})")
                .endRest()

                // DELETE /api/events/{eventId}
                .delete("/{eventId}").description("Delete event by id")
                .route()
                .bean(EventService.class, "removeEvent(${header.eventId})")
                .endRest()

                // PATCH /api/events/{eventId}
                .patch("/{eventId}").description("Update event by id")
                .route()
                .marshal().json()
                .unmarshal(getJacksonDataFormat(EventDTO.class))
                .bean(EventService.class, "updateEvent(${header.eventId}, ${body})")
                .endRest();

    }

    private JacksonDataFormat getJacksonDataFormat(Class<?> type) {
        JacksonDataFormat format = new JacksonDataFormat();
        format.setUnmarshalType(type);
        return format;
    }
}
