package pojo.audience_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseTagAndTagGroupPojo {
    private  String error_code;
    private String concept;
    private  String message;
    private String id;

    public ResponseTagAndTagGroupPojo(String error_code, String concept, String message, String id) {
        this.error_code = error_code;
        this.concept = concept;
        this.message = message;
        this.id = id;
    }
    public ResponseTagAndTagGroupPojo(){

    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrganizerNotFoundPojo{" +
                "error_code='" + error_code + '\'' +
                ", concept='" + concept + '\'' +
                ", message='" + message + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
