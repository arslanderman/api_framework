package pojo.api_automation_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqresSingleUserResponseDataPojo {

    private Integer id;
    private String email;
    private String last_name;
    private String avatar;

    public ReqresSingleUserResponseDataPojo(Integer id, String email, String last_name, String avatar) {
        this.id = id;
        this.email = email;
        this.last_name = last_name;
        this.avatar = avatar;
    }
    public ReqresSingleUserResponseDataPojo(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "ReqresSingleUserResponseDataPojo{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", last_name='" + last_name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
