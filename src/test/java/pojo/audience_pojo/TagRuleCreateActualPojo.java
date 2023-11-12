package pojo.audience_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagRuleCreateActualPojo {

    private TagRuleActualInnerPojo data;

    public TagRuleCreateActualPojo(TagRuleActualInnerPojo data) {
        this.data = data;
    }
    public TagRuleCreateActualPojo(){

    }

    public TagRuleActualInnerPojo getData() {
        return data;
    }

    public void setData(TagRuleActualInnerPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TagRuleCreateActualPojo{" +
                "data=" + data +
                '}';
    }
}
