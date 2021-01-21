package ca.randoli.assignment.dto;

import java.util.List;

public class RecordDTO {
    private String transId;
    private String transTms;
    private String rcNum;
    private String clientId;
    private List<EventDTO> event;

    public RecordDTO() { }

    public RecordDTO(String transId, String transTms, String rcNum, String clientId, List<EventDTO> event) {
        this.transId = transId;
        this.transTms = transTms;
        this.rcNum = rcNum;
        this.clientId = clientId;
        this.event = event;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getTransTms() {
        return transTms;
    }

    public void setTransTms(String transTms) {
        this.transTms = transTms;
    }

    public String getRcNum() {
        return rcNum;
    }

    public void setRcNum(String rcNum) {
        this.rcNum = rcNum;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<EventDTO> getEvent() {
        return event;
    }

    public void setEvent(List<EventDTO> event) {
        this.event = event;
    }
}
