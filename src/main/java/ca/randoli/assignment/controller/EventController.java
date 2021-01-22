package ca.randoli.assignment.controller;

import ca.randoli.assignment.dto.EventDTO;
import ca.randoli.assignment.model.Event;
import ca.randoli.assignment.response.GenericResponse;
import ca.randoli.assignment.service.EventService;
import ca.randoli.assignment.service.RecordService;
import ca.randoli.assignment.util.EventAggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
    @Qualifier("recordDtoDataFormat")
    private JacksonDataFormat recordDtoDataFormat;

    @Autowired
    private EventAggregationStrategy eventAggregationStrategy;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .enableCORS(true)
                .contextPath("/api")
                .apiContextPath("/swagger")
                .apiContextRouteId("swagger")
                .apiProperty("api.title", "Randoli Events API")
                .apiProperty("api.version", "1.0")
                .apiProperty("base.path", "api")
                .apiProperty("cors", "true")
                .port(environment.getProperty("server.port", "8080"))
                .bindingMode(RestBindingMode.json);

        rest("/events").description("Events REST API")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .consumes(MediaType.APPLICATION_JSON_VALUE)

                // GET /api/events
                .get("/")
                    .description("Get all events")
                    .outType(Event[].class)
                    .responseMessage()
                        .code(200)
                    .endResponseMessage()
                .route()
                .bean(EventService.class, "findAll")
                .endRest()

                // GET /api/events/{eventId}
                .get("/{eventId}")
                    .description("Get event by id")
                    .param()
                        .name("eventId")
                        .type(RestParamType.path)
                        .required(true)
                        .description("UUID of the event")
                    .endParam()
                    .responseMessage()
                        .code(200)
                        .responseModel(Event.class)
                    .endResponseMessage()
                .route()
                .bean(EventService.class, "findEventById(${header.eventId})")
                .endRest()

                // POST /api/events
                .post("/")
                    .description("Create new event")
                    .type(EventDTO.class)
                    .param()
                        .name("body")
                        .type(RestParamType.body)
                        .required(true)
                        .description("The event to create")
                    .endParam()
                    .responseMessage()
                        .code(201)
                        .responseModel(Event.class)
                    .endResponseMessage()
                .route()
                .marshal().json()
                .unmarshal(eventDtoDataFormat)
                .bean(EventService.class, "addEvent(${body})")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
                .endRest()

                // DELETE /api/events/{eventId}
                .delete("/{eventId}")
                    .description("Delete event by id")
                    .param()
                        .name("eventId")
                        .type(RestParamType.path)
                        .required(true)
                        .description("UUID of the event")
                    .endParam()
                    .responseMessage()
                        .code(204)
                        .responseModel(Event.class)
                    .endResponseMessage()
                .route()
                .bean(EventService.class, "removeEvent(${header.eventId})")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204))
                .endRest()

                // PATCH /api/events/{eventId}
                .patch("/{eventId}")
                    .description("Update event by id")
                    .type(EventDTO.class)
                    .param()
                        .name("eventId")
                        .type(RestParamType.path)
                        .required(true)
                        .description("UUID of the event")
                    .endParam()
                    .responseMessage()
                        .code(201)
                        .responseModel(Event.class)
                    .endResponseMessage()
                .route()
                .marshal().json()
                .unmarshal(eventDtoDataFormat)
                .bean(EventService.class, "updateEvent(${header.eventId}, ${body})")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
                .endRest();

        rest("/records").description("Records REST API")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .consumes(MediaType.APPLICATION_JSON_VALUE)

                // POST /api/records
                .post("/")
                    .description("Create events from records")
                .route()
                .to("direct:processEvents")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(202))
                .setBody().constant(new GenericResponse("Record is being processed"))
                .endRest();

        from("direct:processEvents")
                .split().jsonpath("$.records").executorService(threadPool)
                    .marshal().json()
                    .unmarshal(recordDtoDataFormat)
                    .bean(RecordService.class, "extractEventDtos")
                    .split(body(), eventAggregationStrategy).executorService(threadPool)
                        .bean(EventService.class, "convertToEvent(${body})")
                    .end()
                    .bean(EventService.class, "addEvents(${body})")
                .end();



    }
}
