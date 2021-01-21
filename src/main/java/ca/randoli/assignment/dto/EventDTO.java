package ca.randoli.assignment.dto;

public class EventDTO {

    private String transId;
    private String transTms;
    private String rcNum;
    private String clientId;
    private long eventCnt;
    private String locationCd;
    private String locationId1;
    private String locationId2;
    private String addrNbr;

    public EventDTO() {}

    public EventDTO(
            String transId,
            String transTms,
            String rcNum,
            String clientId,
            long eventCnt,
            String locationCd,
            String locationId1,
            String locationId2,
            String addrNbr
    ) {
        this.transId = transId;
        this.transTms = transTms;
        this.rcNum = rcNum;
        this.clientId = clientId;
        this.eventCnt = eventCnt;
        this.locationCd = locationCd;
        this.locationId1 = locationId1;
        this.locationId2 = locationId2;
        this.addrNbr = addrNbr;
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

    public long getEventCnt() {
        return eventCnt;
    }

    public void setEventCnt(long eventCnt) {
        this.eventCnt = eventCnt;
    }

    public String getLocationCd() {
        return locationCd;
    }

    public void setLocationCd(String locationCd) {
        this.locationCd = locationCd;
    }

    public String getLocationId1() {
        return locationId1;
    }

    public void setLocationId1(String locationId1) {
        this.locationId1 = locationId1;
    }

    public String getLocationId2() {
        return locationId2;
    }

    public void setLocationId2(String locationId2) {
        this.locationId2 = locationId2;
    }

    public String getAddrNbr() {
        return addrNbr;
    }

    public void setAddrNbr(String addrNbr) {
        this.addrNbr = addrNbr;
    }

    @Override
    public String toString() {
        return "EventDTO { " +
                "transId='" + transId + '\'' +
                ", transTms='" + transTms + '\'' +
                ", rcNum='" + rcNum + '\'' +
                ", clientId='" + clientId + '\'' +
                ", eventCnt=" + eventCnt +
                ", locationCd='" + locationCd + '\'' +
                ", locationId1='" + locationId1 + '\'' +
                ", locationId2='" + locationId2 + '\'' +
                ", addrNbr='" + addrNbr + '\'' +
                " }";
    }
}
