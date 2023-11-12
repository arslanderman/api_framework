package pojo.audience_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagRuleActualInnerPojo {

    private String id;

    public TagRuleActualInnerPojo(String id) {
        this.id = id;
    }
    public TagRuleActualInnerPojo(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TagRuleActualInnerPojo{" +
                "id='" + id + '\'' +
                '}';
    }
}
