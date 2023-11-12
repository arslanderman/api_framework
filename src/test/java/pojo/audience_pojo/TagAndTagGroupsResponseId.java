package pojo.audience_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagAndTagGroupsResponseId {

    private String id;

    public TagAndTagGroupsResponseId(String id) {
        this.id = id;
    }
    public TagAndTagGroupsResponseId(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TagAndTagGroupsResponseId{" +
                "id='" + id + '\'' +
                '}';
    }
}
