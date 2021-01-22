package ca.randoli.assignment.controller;

import ca.randoli.assignment.service.EventService;
import ca.randoli.assignment.service.RecordService;
import ca.randoli.assignment.util.EventAggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Component
public class EventController extends RouteBuilder {

    @Autowired
    private Environment environment;

    @Autowired
    private ExecutorService threadPool;

    @Autowired
    @Qualifier("eventDtoDataFormat")
    private JacksonDataFormat eventDtoDataFormat;

    @Autowired
    @Qualifier("eventResponseDataFormat")
    private JacksonDataFormat eventResponseDataFormat;

    @Autowired
    @Qualifier("recordDtoDataFormat")
    private JacksonDataFormat recordDtoDataFormat;

    @Autowired
    @Qualifier("eventsListDataFormat")
    private JacksonDataFormat eventsListDataFormat;

    @Autowired
    private EventAggregationStrategy eventAggregationStrategy;

    @Override
    public void configure() throws Exception {
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
                .unmarshal(eventDtoDataFormat)
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
                .unmarshal(eventDtoDataFormat)
                .bean(EventService.class, "updateEvent(${header.eventId}, ${body})")
                .endRest();

        rest("/records").description("Records REST API")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .consumes(MediaType.APPLICATION_JSON_VALUE)

                // POST /api/records
                .post("/").description("Create events from records")
                .route()
                .setHeader("correlation-id", constant(UUID.randomUUID()))
                .to("direct:processEvents")
                .setBody(constant("{ \"message\": \"Record will be processed in the background\" }"))
                .endRest();

        from("direct:processEvents")
                .split().jsonpath("$.records").streaming()
                .executorService(threadPool)
                .marshal().json()
                .unmarshal(recordDtoDataFormat)
                .split().method(RecordService.class, "extractEventDtos")
                .executorService(threadPool)
                .bean(EventService.class, "convertToEvent(${body})")
                .aggregate(header("correlation-id"), eventAggregationStrategy).completionSize(9)
                .bean(EventService.class, "addEvents(${body})");


    }
}
