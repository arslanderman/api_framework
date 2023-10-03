package pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FindAllTagsPositivePojo {

    private List<FindAllTagsLInkedEntityInnerPojo> data;

    public FindAllTagsPositivePojo(List<FindAllTagsLInkedEntityInnerPojo> data) {
        this.data = data;
    }
    public FindAllTagsPositivePojo(){

    }

    public List<FindAllTagsLInkedEntityInnerPojo> getData() {
        return data;
    }

    public void setData(List<FindAllTagsLInkedEntityInnerPojo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FindAllTagsPositivePojo{" +
                "data=" + data +
                '}';
    }
}