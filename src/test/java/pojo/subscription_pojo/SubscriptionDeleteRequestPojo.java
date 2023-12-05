package pojo.subscription_pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionDeleteRequestPojo {

    private String subscriptionSettingId;

    public SubscriptionDeleteRequestPojo(String subscriptionSettingId) {
        this.subscriptionSettingId = subscriptionSettingId;
    }

    public SubscriptionDeleteRequestPojo(){

    }

    public String getSubscriptionSettingId() {
        return subscriptionSettingId;
    }

    public void setSubscriptionSettingId(String subscriptionSettingId) {
        this.subscriptionSettingId = subscriptionSettingId;
    }

    @Override
    public String toString() {
        return "SubscriptionDeleteRequestPojo{" +
                "subscriptionSettingId='" + subscriptionSettingId + '\'' +
                '}';
    }
}
