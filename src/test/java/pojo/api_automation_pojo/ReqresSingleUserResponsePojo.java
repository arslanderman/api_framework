package pojo.api_automation_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqresSingleUserResponsePojo {

    private ReqresSingleUserResponseDataPojo data;
    private ReqresSingleUserResponseSupportPojo support;

    public ReqresSingleUserResponsePojo(ReqresSingleUserResponseDataPojo data, ReqresSingleUserResponseSupportPojo support) {
        this.data = data;
        this.support = support;
    }

    public ReqresSingleUserResponsePojo(){

    }

    public ReqresSingleUserResponseDataPojo getData() {
        return data;
    }

    public void setData(ReqresSingleUserResponseDataPojo data) {
        this.data = data;
    }

    public ReqresSingleUserResponseSupportPojo getSupport() {
        return support;
    }

    public void setSupport(ReqresSingleUserResponseSupportPojo support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return "ReqresSingleUserResponse{" +
                "data=" + data +
                ", support=" + support +
                '}';
    }
}
