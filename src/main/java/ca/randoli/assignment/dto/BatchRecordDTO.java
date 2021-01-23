package ca.randoli.assignment.dto;

import java.util.List;

public class BatchRecordDTO {

    private String batchId;
    private List<RecordDTO> records;

    public BatchRecordDTO() { }

    public BatchRecordDTO(String batchId, List<RecordDTO> records) {
        this.batchId = batchId;
        this.records = records;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public List<RecordDTO> getRecords() {
        return records;
    }

    public void setRecords(List<RecordDTO> records) {
        this.records = records;
    }
}
