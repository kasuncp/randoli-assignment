package ca.randoli.assignment.response;

import java.util.List;

public class EventsListResponse {

    private List<EventResponse> events;

    public EventsListResponse() { }

    public EventsListResponse(List<EventResponse> events) {
        this.events = events;
    }

    public List<EventResponse> getEvents() {
        return events;
    }

    public void setEvents(List<EventResponse> events) {
        this.events = events;
    }

    public void addEvent(EventResponse eventResponse) {
        if (this.events != null) {
            this.events.add(eventResponse);
        }
    }
}
