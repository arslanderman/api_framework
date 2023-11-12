package pojo.audience_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagAndTagGroupResponseDataPositive {

    private TagAndTagGroupsResponseId data;

    public TagAndTagGroupResponseDataPositive(TagAndTagGroupsResponseId data) {
        this.data = data;
    }
    public TagAndTagGroupResponseDataPositive(){

    }

    public TagAndTagGroupsResponseId getData() {
        return data;
    }

    public void setData(TagAndTagGroupsResponseId data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TagAndTagGroupResponseData{" +
                "data=" + data +
                '}';
    }
}
