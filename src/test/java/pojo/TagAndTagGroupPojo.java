package pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagAndTagGroupPojo {
   // create private variables

    private Integer organizerId;
    private String id;
    private String name;
    private String parentId;
    private String color;
    private Boolean active;
    private String tagGroupId;

    //create constructor

    public TagAndTagGroupPojo(Integer organizerId, String id, String name, String parentId, String color, Boolean active, String tagGroupId) {
        this.organizerId = organizerId;
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.color = color;
        this.active = active;
        this.tagGroupId = tagGroupId;
    }

    //generate getters and setters


    public Integer getOrganizerId() {
        return organizerId;
    }
    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
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
    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    // generate toString

    @Override
    public String toString() {
        return "TagAndTAgGroupPojo{" +
                "organizerId=" + organizerId +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", color='" + color + '\'' +
                ", active=" + active +
                ", tagGroupId='" + tagGroupId + '\'' +
                '}';
    }
}
