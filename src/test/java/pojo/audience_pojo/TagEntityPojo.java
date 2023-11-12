package pojo.audience_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TagEntityPojo {

    private String tagId;
    private List<Integer> entityIdList;
    private String entityType;
    private Integer employeeId;
    private Integer organizerId;

    public TagEntityPojo(String tagId, List<Integer> entityIdList, String entityType, Integer employeeId, Integer organizerId) {
        this.tagId = tagId;
        this.entityIdList = entityIdList;
        this.entityType = entityType;
        this.employeeId = employeeId;
        this.organizerId = organizerId;
    }

    public TagEntityPojo(){

    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public List<Integer> getEntityIdList() {
        return entityIdList;
    }

    public void setEntityIdList(List<Integer> entityIdList) {
        this.entityIdList = entityIdList;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    @Override
    public String toString() {
        return "TagEntityPojo{" +
                "tagId='" + tagId + '\'' +
                ", entityIdList=" + entityIdList +
                ", entityType='" + entityType + '\'' +
                ", employeeId=" + employeeId +
                ", organizerId=" + organizerId +
                '}';
    }
}
