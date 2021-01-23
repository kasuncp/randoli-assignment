package ca.randoli.assignment.dto;

public class PartialEventDTO {
    private long eventCnt;
    private String locationCd;
    private String locationId1;
    private String locationId2;
    private String addrNbr;

    public PartialEventDTO() { }

    public PartialEventDTO(long eventCnt, String locationCd, String locationId1, String locationId2, String addrNbr) {
        this.eventCnt = eventCnt;
        this.locationCd = locationCd;
        this.locationId1 = locationId1;
        this.locationId2 = locationId2;
        this.addrNbr = addrNbr;
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
}
