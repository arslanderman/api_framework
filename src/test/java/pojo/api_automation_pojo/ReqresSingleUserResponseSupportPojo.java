package pojo.api_automation_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqresSingleUserResponseSupportPojo {

    private String url;
    private String text;

    public ReqresSingleUserResponseSupportPojo(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public ReqresSingleUserResponseSupportPojo(){

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ReqresSingleUserResponseSupportPojo{" +
                "url='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
