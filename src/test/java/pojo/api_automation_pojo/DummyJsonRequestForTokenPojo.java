package pojo.api_automation_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyJsonRequestForTokenPojo {

    private String username;
    private String password;

    public DummyJsonRequestForTokenPojo(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public DummyJsonRequestForTokenPojo(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DummyJsonRequestForToken{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
