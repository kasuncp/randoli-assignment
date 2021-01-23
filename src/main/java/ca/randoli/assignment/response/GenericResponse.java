package ca.randoli.assignment.response;

public class GenericResponse {

    public enum ResponseStatus {
        SUCCESS,
        ERROR
    }

    private ResponseStatus status;
    private String message;

    public GenericResponse(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public GenericResponse(String message) {
        this.status = ResponseStatus.SUCCESS;
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
