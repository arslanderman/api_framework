package pojo.audience_pojo;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseTagFindAllInnerPojo {

    private String id;
    private String name;
    private String color;
    private Boolean active;
    private String tagGroupId;

    public ResponseTagFindAllInnerPojo(String id, String name, String color, Boolean active, String tagGroupId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.active = active;
        this.tagGroupId = tagGroupId;
    }

    public ResponseTagFindAllInnerPojo(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getTagGroupId() {
        return tagGroupId;
    }

    public void setTagGroupId(String tagGroupId) {
        this.tagGroupId = tagGroupId;
    }

    @Override
    public String toString() {
        return "ResponseTagFindAllInnerPojo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", active=" + active +
                ", tagGroupId='" + tagGroupId + '\'' +
                '}';
    }
}
