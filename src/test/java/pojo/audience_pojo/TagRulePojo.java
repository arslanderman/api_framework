package pojo.audience_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagRulePojo {

    private String name;
    private String criteria;
    private List<String> tagsAdded;
    private List<String> tagsRemoved;
    private Integer organizerId;
    private Integer sort;

    public TagRulePojo(String name, String criteria, List<String> tagsAdded, List<String> tagsRemoved, Integer organizerId, Integer sort) {
        this.name = name;
        this.criteria = criteria;
        this.tagsAdded = tagsAdded;
        this.tagsRemoved = tagsRemoved;
        this.organizerId = organizerId;
        this.sort = sort;
    }

    public TagRulePojo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public List<String> getTagsAdded() {
        return tagsAdded;
    }

    public void setTagsAdded(List<String> tagsAdded) {
        this.tagsAdded = tagsAdded;
    }

    public List<String> getTagsRemoved() {
        return tagsRemoved;
    }

    public void setTagsRemoved(List<String> tagsRemoved) {
        this.tagsRemoved = tagsRemoved;
    }

    public Integer getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Integer organizerId) {
        this.organizerId = organizerId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "TagRulePojo{" +
                "name='" + name + '\'' +
                ", criteria='" + criteria + '\'' +
                ", tagsAdded=" + tagsAdded +
                ", tagsRemoved=" + tagsRemoved +
                ", organizerId=" + organizerId +
                ", sort=" + sort +
                '}';
    }
}
