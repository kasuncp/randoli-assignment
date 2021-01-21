package ca.randoli.assignment.service;

import ca.randoli.assignment.dto.EventDTO;
import ca.randoli.assignment.dto.RecordDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    public List<EventDTO> extractEventDtos(RecordDTO recordDTO) {
        List<EventDTO> eventDtos =  recordDTO.getEvent();
        for (EventDTO eventDto : eventDtos) {
            eventDto.setTransId(recordDTO.getTransId());
            eventDto.setTransTms(recordDTO.getTransTms());
            eventDto.setRcNum(recordDTO.getRcNum());
            eventDto.setClientId(recordDTO.getClientId());
        }

        return eventDtos;
    }
}
