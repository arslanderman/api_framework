package pojo.subscription_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionCreateInnerPojo {

    private String id;


    public SubscriptionCreateInnerPojo(String id) {
        this.id = id;
    }
    public SubscriptionCreateInnerPojo(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SubscriptionCreateInnerPojo{" +
                "id='" + id + '\'' +
                '}';
    }
}
