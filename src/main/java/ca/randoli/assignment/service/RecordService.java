package ca.randoli.assignment.service;

import ca.randoli.assignment.dto.EventDTO;
import ca.randoli.assignment.dto.PartialEventDTO;
import ca.randoli.assignment.dto.RecordDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordService {

    public List<EventDTO> extractEventDtos(RecordDTO recordDTO) {
        List<PartialEventDTO> partialEventDtos =  recordDTO.getEvent();
        List<EventDTO> eventDtos = new ArrayList<>();
        for (PartialEventDTO partialEventDTO : partialEventDtos) {
            EventDTO eventDto = new EventDTO();

            eventDto.setTransId(recordDTO.getTransId());
            eventDto.setTransTms(recordDTO.getTransTms());
            eventDto.setRcNum(recordDTO.getRcNum());
            eventDto.setClientId(recordDTO.getClientId());
            eventDto.setEventCnt(partialEventDTO.getEventCnt());
            eventDto.setLocationCd(partialEventDTO.getLocationCd());
            eventDto.setLocationId1(partialEventDTO.getLocationId1());
            eventDto.setLocationId2(partialEventDTO.getLocationId2());
            eventDto.setAddrNbr(partialEventDTO.getAddrNbr());

            eventDtos.add(eventDto);
        }

        return eventDtos;
    }
}
