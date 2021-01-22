package ca.randoli.assignment.util;

import ca.randoli.assignment.model.Event;
import ca.randoli.assignment.response.EventResponse;
import ca.randoli.assignment.response.EventsListResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventAggregationStrategy implements AggregationStrategy {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(EventAggregationStrategy.class);

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            Event thisMessage = newExchange.getIn().getBody(Event.class);
            List<Event> eventList = new ArrayList<>();
            eventList.add(thisMessage);
            newExchange.getIn().setBody(eventList);
            return newExchange;
        }

        List<Event> aggregatedMessages = oldExchange.getIn().getBody(ArrayList.class);
        Event thisMessage = newExchange.getIn().getBody(Event.class);

        aggregatedMessages.add(thisMessage);
        oldExchange.getIn().setBody(aggregatedMessages);

        return oldExchange;
    }
}
