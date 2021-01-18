package ca.randoli.assignment.controller;

import ca.randoli.assignment.service.EventService;
import org.apache.camel.builder.RouteBuilder;
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
                .get("/").description("List all events")
                .route()
                .bean(EventService.class, "getEventNames")
                .endRest();

    }
}
