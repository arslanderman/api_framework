package pojo.subscription_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionCreatePojo {

    private SubscriptionCreateInnerPojo data;

    public SubscriptionCreatePojo(SubscriptionCreateInnerPojo data) {
        this.data = data;
    }
    public SubscriptionCreatePojo(){

    }

    public SubscriptionCreateInnerPojo getData() {
        return data;
    }

    public void setData(SubscriptionCreateInnerPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SubscriptionCreatePojo{" +
                "data=" + data +
                '}';
    }
}
