package ca.randoli.assignment.service;

import ca.randoli.assignment.dto.EventDTO;
import ca.randoli.assignment.model.Event;
import ca.randoli.assignment.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }

    public Event findEventById(String eventId) {
        Optional<Event> event = getEventById(eventId);
        return event.isPresent() ? event.get() : null;
    }

    public Event addEvent(EventDTO eventDTO) {
        LOGGER.info(eventDTO.toString());
        Event event = updateEventFromDto(eventDTO, new Event());
        return this.eventRepository.save(event);
    }

    public Event removeEvent(String eventId) {
        Optional<Event> event = getEventById(eventId);
        if (event.isPresent()) {
            this.eventRepository.delete(event.get());
            return event.get();
        }
        return null;
    }

    public Event updateEvent(String eventId, EventDTO eventDTO) {
        Optional<Event> event = getEventById(eventId);
        if (event.isPresent()) {
            Event updatedEvent = this.eventRepository.save(updateEventFromDto(eventDTO, event.get()));
            return updatedEvent;
        }
        return null;
    }

    private Optional<Event> getEventById(String eventId) {
        UUID id;
        try {
            id = UUID.fromString(eventId);
        }
        catch (IllegalArgumentException ex) {
            LOGGER.error(ex.getMessage(), ex);
            return null;
        }
        return this.eventRepository.findById(id);
    }

    private Event updateEventFromDto(EventDTO eventDTO, Event event) {

        if (eventDTO.getTransId() != null &&
                !eventDTO.getTransId().equals(event.getTransId())) {
            event.setTransId(eventDTO.getTransId());
        }

        if (eventDTO.getTransTms() != null &&
                !eventDTO.getTransTms().equals(event.getTransTms())) {
            event.setTransTms(eventDTO.getTransTms());
        }

        if (eventDTO.getRcNum() != null &&
                !eventDTO.getRcNum().equals(event.getRcNum())) {
            event.setRcNum(eventDTO.getRcNum());
        }

        if (eventDTO.getClientId() != null &&
                !eventDTO.getClientId().equals(event.getClientId())) {
            event.setClientId(eventDTO.getClientId());
        }

        if (eventDTO.getEventCnt() != event.getEventCnt()) {
            event.setEventCnt(eventDTO.getEventCnt());
        }

        if (eventDTO.getLocationCd() != null &&
                !eventDTO.getLocationCd().equals(event.getLocationCd())) {
            event.setLocationCd(eventDTO.getLocationCd());
        }

        if (eventDTO.getLocationId1() != null &&
                !eventDTO.getLocationId1().equals(event.getLocationId1())) {
            event.setLocationId1(eventDTO.getLocationId1());
        }

        if (eventDTO.getLocationId2() != null &&
                !eventDTO.getLocationId2().equals(event.getLocationId2())) {
            event.setLocationId2(eventDTO.getLocationId2());
        }

        if (eventDTO.getAddrNbr() != null &&
                !eventDTO.getAddrNbr().equals(event.getAddrNbr())) {
            event.setAddrNbr(eventDTO.getAddrNbr());
        }

        return event;
    }
}
