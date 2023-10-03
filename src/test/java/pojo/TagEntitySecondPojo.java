package pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagEntitySecondPojo {

    private Integer entityId;
    private String entityType;
    private Integer organizerId;

    public TagEntitySecondPojo(Integer entityId, String entityType, Integer organizerId) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.organizerId = organizerId;
    }

    public TagEntitySecondPojo(){

    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    @Override
    public String toString() {
        return "TagEntitySecondPojo{" +
                "entityId=" + entityId +
                ", entityType='" + entityType + '\'' +
                ", organizerId=" + organizerId +
                '}';
    }
}
